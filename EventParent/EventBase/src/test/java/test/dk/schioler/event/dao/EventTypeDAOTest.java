package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

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
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.secure.dao.LoginDAO;
import dk.schioler.secure.dao.UserProfileDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.ROLE;
import dk.schioler.secure.entity.UserProfile;
import dk.schioler.secure.entity.impl.LoginImpl;
import dk.schioler.secure.entity.impl.UserProfileImpl;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EventTypeDAOTest extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	EventTypeDAO eventTypeDAO;

	@Autowired
	EventTemplateDAO eventTemplateDAO;

	@Autowired
	EventDAO eventDAO;

	@Autowired
	UserProfileDAO userProfileDAO;

	@Autowired
	LoginDAO loginDAO;

	public EventTypeDAOTest() {
		super();
		logger.debug("loginDAO=" + loginDAO);
	}

	@Test
	public void testStoreEventObjects() {
		Event event2 = null;
		EventTemplate eventTemplate2 = null;
		EventType eventType = null;
		Integer eventTypeIid = null;
		UserProfile up = null;
		Login login = null;
		try {
			up = new UserProfileImpl();
			up.setFirstName("test");
			up.setLastName("testesen");
			up.setStartTS(LocalDateTime.now());
			up = userProfileDAO.insert(up);

			login = new LoginImpl();
			login.setLogin("myLogin");
			login.setUserProfile(up);
			login.setRole(ROLE.ADMIN);
			login.setStartTS(LocalDateTime.now());
			login = loginDAO.insert(login);

			EventType et = new EventType();
			et.setName("eventType1");
			et.setShortName("et1");
			et.setDescription("EventypeTest nr 1");
			et.setLoginId(login.getId());
			eventType = eventTypeDAO.insert(et);
			logger.debug("et=" + et);
			logger.debug("insert=" + eventType);
			assertTrue(eventType != null);
			assertTrue(eventType.getId() != null);
			eventTypeIid = eventType.getId();

			eventType.setDescription("updated");
			int count = eventTypeDAO.update(eventType);
			assertEquals(1, count);

			EventTemplate eventTemplate = new EventTemplate();
			eventTemplate.setName("TemplateName");
			eventTemplate.setShortName("SHORT");
			eventTemplate.setDescription("DDESC");
			eventTemplate.setDose("25");
			eventTemplate.setUnit("mg");
			eventTemplate.setParentId(et.getId());
			eventTemplate.setLoginId(login.getId());

			eventTemplate = eventTemplateDAO.insert(eventTemplate);

			
			eventTemplate2 = eventTemplateDAO.get(eventTemplate.getId(), login.getId());
			logger.debug("found template:" + eventTemplate2);
			eventTemplate2.setDescription("trut");
			eventTemplateDAO.update(eventTemplate2);

			Event event = new Event();
			event.setName("TemplateName");
			event.setShortName("SHORT");
			event.setNote("DDESC");
			event.setDose("25");
			event.setUnit("mg");
			event.setParentId(eventTemplate2.getId());
			event.setLoginId(login.getId());

			Event e = eventDAO.insert(event);
			e.setNote("jpd");
			eventDAO.update(e);

			event2 = eventDAO.get(e.getId(), login.getId());

			logger.debug("e=" + event2);

		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {

			eventDAO.delete(event2.getId(), login.getId());
			eventTemplateDAO.delete(eventTemplate2.getId(), login.getId());
			eventTypeDAO.delete(eventTypeIid, login.getId());
			
			loginDAO.delete(login.getId());
			userProfileDAO.delete(up.getId());
		}
	}

}