package dk.schioler.event.base.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.xpath.operations.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.encrypt.Encrypter;
import dk.schioler.event.base.entity.AbstractEntity;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.xml.EventTypeXMLHelper;
import dk.schioler.event.base.xml.EventXMLException;
import dk.schioler.secure.dao.LoginDAO;
import dk.schioler.secure.dao.PasswordDAO;
import dk.schioler.secure.dao.UserProfileDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.ROLE;
import dk.schioler.secure.entity.UserProfile;
import dk.schioler.secure.entity.impl.LoginImpl;
import dk.schioler.secure.entity.impl.PasswordImpl;
import dk.schioler.secure.entity.impl.UserProfileImpl;

@Component
public class EstablishBasisEventTypesAndTmplsMain {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	private static Logger logger = LoggerFactory.getLogger(EstablishBasisEventTypesAndTmplsMain.class);

	@Autowired
	EventTypeDAO eventTypeDAO;

	@Autowired
	EventTemplateDAO eventTemplateDAO;

	@Autowired
	UserProfileDAO userProfileDAO;

	@Autowired
	LoginDAO loginDAO;

	@Autowired
	Encrypter encrypter;

	@Autowired
	PasswordDAO passwordDAO;

	static ApplicationContext ctx;

//	static String[] cfgFiles = new String[] { "src/main/resources/ApplicationContext.xml" };

	public EstablishBasisEventTypesAndTmplsMain() {

	}

	private static String eventFile = "src/main/data/event-base-data.xml";

	public static void main(String[] args) {
		try {
			ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);

			EstablishBasisEventTypesAndTmplsMain self = ctx.getBean(EstablishBasisEventTypesAndTmplsMain.class);

			Login login = self.establishLogin("dd", "dd");
			List<AbstractEntity> eventTypesAndTemplates = self.readEventTypesAndTemplates(eventFile);

			List<AbstractEntity> persistedEventTypes = self.persistEventTypes(eventTypesAndTemplates, login);

			for (AbstractEntity absE : persistedEventTypes) {
				EventType eType = (EventType) absE;
				logger.debug("etype=" + eType);
				List<AbstractEntity> children = eType.getChildren();
				logger.debug("children=" + children.size());
				for (AbstractEntity abstractEntity : children) {
					EventTemplate tmpl = (EventTemplate) abstractEntity;
					tmpl.setLoginId(login.getId());
					logger.debug("CHILD=" + tmpl);
					self.eventTemplateDAO.insert(tmpl);

				}

			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			System.exit(1);
		}
	}

	public Login establishLogin(String login, String password) {
		UserProfile up = new UserProfileImpl();
		up.setFirstName("test1");
		up.setLastName("Lastname");
		up.setPrimaryEmail("yummi@event.dk");
		up.setStartTS(LocalDateTime.now());
		up = userProfileDAO.insert(up);

		Login loginInst = new LoginImpl();
		loginInst.setLogin(login);
		loginInst.setRole(ROLE.ADMIN);
		loginInst.setUserProfile(up);
		loginDAO.insert(loginInst);

		Password pwd = new PasswordImpl();
		pwd.setStartTS(LocalDateTime.now());
		pwd.setLoginId(loginInst.getId());
		pwd.setPwd(encrypter.encrypt(password));
		pwd = passwordDAO.insert(pwd);
		loginInst.addPassword(pwd);

		return loginInst;
	}

	public List<AbstractEntity> persistEventTypes(List<AbstractEntity> types, Login login) {
		List<AbstractEntity> list = new ArrayList<AbstractEntity>();
		for (AbstractEntity abstrEventType : types) {
			List<AbstractEntity> children = abstrEventType.getChildren();
			EventType eventType = (EventType) abstrEventType;
			eventType.setLoginId(login.getId());
			EventType typeInserted = eventTypeDAO.insert(eventType);

			list.add(typeInserted);
		}
		return list;
	}

	public void persistEventTypesAndTemplates(List<AbstractEntity> types, Login login) {
		for (AbstractEntity abstrEventType : types) {
			EventType eventType = (EventType) abstrEventType;
			eventType.setLoginId(login.getId());
			EventType typeInserted = eventTypeDAO.insert(eventType);
			List<AbstractEntity> templates = eventType.getChildren();
			for (AbstractEntity e : templates) {
				EventTemplate tmpl = (EventTemplate) e;
				e.setParentId(typeInserted.getId());
				e.setLoginId(login.getId());
				eventTemplateDAO.insert(tmpl);
			}
		}
	}

	public List<AbstractEntity> readEventTypesAndTemplates(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(new File(fileName));
			EventTypeXMLHelper xmlHelper = new EventTypeXMLHelper();
			List<AbstractEntity> eventTypes = xmlHelper.buildEventTypesFromXML(fis);
			return eventTypes;
		} catch (IOException e) {
			throw new EventXMLException(e.getMessage(), e);
		}
	}
}
