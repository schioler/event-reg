package test.dk.schioler.event.populate;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.encrypt.Encrypter;
import dk.schioler.event.base.entity.AbstractEntity;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
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

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class TestPopulateEventWithTestData extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	EventDAO eventDAO;

	@Autowired
	EventTemplateDAO eventTemplateDAO;

	@Autowired
	EventTypeDAO eventTypeDAO;

	@Autowired
	UserProfileDAO userProfileDAO;

	@Autowired
	LoginDAO loginDAO;

	@Autowired
	PasswordDAO passwordDAO;

	@Autowired
	Encrypter encrypter;

	public TestPopulateEventWithTestData() {
		super();

	}

	public Login createLogin(UserProfile up, String loginId, ROLE role) {
		Login login = new LoginImpl();
		login.setLogin(loginId);
		login.setUserProfile(up);
		login.setRole(role);
		login.setStartTS(LocalDateTime.now());
		login = loginDAO.insert(login);
		up.addLogin(login);
		logger.debug("created login=" + login);
		return login;
	}

	public UserProfile createUserProfile(String name) {
		UserProfile up = null;

		try {
			up = new UserProfileImpl();
			up.setFirstName(name);
			up.setLastName(name + "last");
			up.setStartTS(LocalDateTime.now());
			up = userProfileDAO.insert(up);
			logger.debug("created userProfile=" + up);

			return up;
		} catch (Exception e) {
			throw new EventDAOException(e.getMessage(), e);
		}
	}

	public Password createPassword(Login login, String password) {
		Password pwd = new PasswordImpl();

		String encrypted = encrypter.encrypt(password);

		pwd.setLoginId(login.getId());
		pwd.setPwd(encrypted);
		pwd.setStartTS(LocalDateTime.now());

		pwd = passwordDAO.insert(pwd);
		login.addPassword(pwd);
		return pwd;
	}

	public List<EventType> establishXEventTypes(Login login, int count, String namePrefix) {
		List<EventType> eventTypes = new ArrayList<EventType>();
		for (int i = 0; i < count; i++) {
			EventType et = new EventType();
			et.setName(namePrefix + "EventType" + i);
			et.setShortName("Short" + i);
			et.setDescription("EventypeTest nr " + i);
			et.setLoginId(login.getId());
			et = eventTypeDAO.insert(et);
			logger.debug("et=" + et);
			eventTypes.add(et);
		}
		return eventTypes;
	}

	public List<EventTemplate> establishXEventTemplates(EventType eventType, int count, String namePrefix) {
		List<EventTemplate> templates = new ArrayList<EventTemplate>();
		for (int i = 0; i < count; i++) {
			EventTemplate et = new EventTemplate();
			et.setName(namePrefix + "EventTemplate" + i);
			et.setShortName("Short" + i);
			et.setLoginId(eventType.getLoginId());
			et.setParent(eventType);
			et.setDose("dose");
			et.setUnit("unit");
			et = eventTemplateDAO.insert(et);
			logger.debug("et=" + et);
			templates.add(et);
		}
		return templates;
	}

	public List<Event> establishXEvents(EventTemplate eventTemplate, int count, String namePrefix) {
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < count; i++) {
			Event et = new Event();
			et.setName(namePrefix + "Event" + i);
			et.setShortName("Short" + i);
			et.setLoginId(eventTemplate.getLoginId());
			et.setParent(eventTemplate);
			et.setDose("dose");
			et.setUnit("unit");
			et = eventDAO.insert(et);
			logger.debug("et=" + et);
			events.add(et);
		}
		return events;
	}

	@Test
	public void testRetrieve() {

		UserProfile medUp = null;
		Login medLogin1 = null;
		Login medLogin2 = null;
		List<EventType> medEventTypes1 = null;
		List<EventType> mewEventTypes2 = null;
		List<EventType> medEventTypes3 = null;

		UserProfile datUp = null;
		Login datLogin1 = null;
		Login datLogin2 = null;
		List<EventType> datEventTypes1 = null;
		List<EventType> datEventTypes2 = null;
		List<EventType> datEventTypes3 = null;

		try {
			/*
			 * **************************''''''
			 */

			medUp = createUserProfile("MediUser");
			medLogin1 = createLogin(medUp, "ma", ROLE.ADMIN);
			createPassword(medLogin1, "ma");

			medLogin2 = createLogin(medUp, "mp", ROLE.PLAIN);
			createPassword(medLogin2, "mp");

			logger.debug("medUp=" + medUp);
			/*
			 * **************************''''''
			 */

			datUp = createUserProfile("DataUser");
			datLogin1 = createLogin(datUp, "da", ROLE.ADMIN);
			createPassword(datLogin1, "da");

			datLogin2 = createLogin(datUp, "dp", ROLE.PLAIN);
			createPassword(datLogin2, "dp");

			logger.debug("medUp=" + medUp);
			/*
			 * **************************''''''
			 */

			String prefix1 = "ET1-1";
			medEventTypes1 = establishXEventTypes(medLogin1, 4, prefix1);

			for (EventType eventType : medEventTypes1) {
				List<EventTemplate> medEventTemplates1 = establishXEventTemplates(eventType, 5, prefix1);
			}
			
			for (EventType eventType : medEventTypes1) {
				List<AbstractEntity> eventTemplates = eventType.getChildren();
				for (AbstractEntity ae : eventTemplates) {
					EventTemplate tmpl = (EventTemplate) ae;
					establishXEvents(tmpl, 2, prefix1);
				}
			}
			

//			eventTypes3 = establishXEventTypes(login1, 20, "ET1-2");
//			eventTypes2 = establishXEventTypes(login2, 5, "ET2-1");
//
//			EventTypeCriteria criteria = new EventTypeCriteria();
//			criteria.addLoginId(login1.getId());
//
//			List<EventType> list = eventTypeDAO.retrieve(criteria, 0);
//			logger.debug("eventTypeList=" + list);
//			assertEquals(30, list.size());
//
//			criteria = new EventTypeCriteria();
//			criteria.addLoginId(login2.getId());
//
//			list = eventTypeDAO.retrieve(criteria, 0);
//			logger.debug("eventTypeList=" + list);
//			assertEquals(5, list.size());
//
//			criteria = new EventTypeCriteria();
//			criteria.addLoginId(login1.getId());
//			criteria.addLoginId(login2.getId());
//
//			list = eventTypeDAO.retrieve(criteria, 0);
//			logger.debug("eventTypeList=" + list);
//			assertEquals(35, list.size());
//
//			criteria = new EventTypeCriteria();
//			criteria.addLoginId(login1.getId());
//			criteria.setName("ET1-2");
//
//			list = eventTypeDAO.retrieve(criteria, 0);
//			logger.debug("eventTypeList=" + list);
//			assertEquals(20, list.size());
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {

//			if (eventTypes1 != null) {
//				if (login1 != null) {
//					Integer id = login1.getId();
//					for (EventType et : eventTypes1) {
//						eventTypeDAO.delete(et.getId(), id);
//					}
//				}
//			}
//
//			if (eventTypes3 != null) {
//				if (login1 != null) {
//					Integer id = login1.getId();
//					for (EventType et : eventTypes3) {
//						eventTypeDAO.delete(et.getId(), id);
//					}
//				}
//			}
//
//			if (eventTypes2 != null) {
//				if (login2 != null) {
//					Integer id = login2.getId();
//					for (EventType et : eventTypes2) {
//						eventTypeDAO.delete(et.getId(), id);
//					}
//				}
//			}
//			if (login1 != null) {
//				loginDAO.delete(login1.getId());
//			}
//			if (login2 != null) {
//				loginDAO.delete(login2.getId());
//			}
//			if (medUp != null && medUp.getId() != null) {
//				userProfileDAO.delete(medUp.getId());
//			}
//
		}
	}

}