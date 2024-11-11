package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
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
public class EventTypeDAORetrieveTest extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	EventTypeDAO eventTypeDAO;

	@Autowired
	UserProfileDAO userProfileDAO;

	@Autowired
	LoginDAO loginDAO;

	public EventTypeDAORetrieveTest() {
		super();
		logger.debug("loginDAO=" + loginDAO);
	}

	public Login createLogin(UserProfile up, String loginId, ROLE role) {
		Login login = new LoginImpl();
		login.setLogin(loginId);
		login.setUserProfile(up);
		login.setRole(role);
		login.setStartTS(LocalDateTime.now());
		login = loginDAO.insert(login);
		logger.debug("created login=" + login);
		return login;
	}

	public UserProfile establishUserAndLogin() {
		UserProfile up = null;

		try {
			up = new UserProfileImpl();
			up.setFirstName("test");
			up.setLastName("testesen");
			up.setStartTS(LocalDateTime.now());
			up = userProfileDAO.insert(up);
			logger.debug("created userProfile=" + up);

			Login login = createLogin(up, "admin@schioler.dk", ROLE.ADMIN);
			up.addLogin(login);

			login = createLogin(up, "user@schioler.dk", ROLE.PLAIN);
			up.addLogin(login);

			logger.debug("UP.getlogins=" + up.getLogins());
			return up;
		} catch (Exception e) {
			throw new EventDAOException(e.getMessage(), e);
		}
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

	@Test
	public void testRetrieve() {
		EventType first = null;
		EventType last = null;
		List<EventType> eventTypes1 = null;
		List<EventType> eventTypes2 = null;
		List<EventType> eventTypes3 = null;

		UserProfile up = null;
		Login login1 = null;
		Login login2 = null;
		try {

			up = establishUserAndLogin();
			logger.debug("logins=" + up);
			login1 = up.getLogins().getFirst();
			login2 = up.getLogins().getLast();

			if (login1 == null) {
				fail("login1 not established properly");
			} else {
				if (login1.getId() == null) {
					fail("login1 has no ID set...");
				} else {
					logger.debug("login1 is present and has ID set");
				}

			}
			if (login2 == null) {
				fail("login2 not established properly");
			} else {
				if (login1.getId() == null) {
					fail("login1 has no ID set...");
				} else {
					logger.debug("login1 is present and has ID set");
				}

			}

			logger.debug("login1=" + login1);
			logger.debug("login2=" + login2);

			eventTypes1 = establishXEventTypes(login1, 10, "ET1-1");
			eventTypes3 = establishXEventTypes(login1, 20, "ET1-2");
			eventTypes2 = establishXEventTypes(login2, 5, "ET2-1");

			
			EventTypeCriteria criteria = new EventTypeCriteria();
			criteria.addLoginId(login1.getId());

			List<EventType> list = eventTypeDAO.retrieve(criteria, 0);
			logger.debug("eventTypeList=" + list);
			assertEquals(30, list.size());
			
			criteria = new EventTypeCriteria();
			criteria.addLoginId(login2.getId());

			list = eventTypeDAO.retrieve(criteria, 0);
			logger.debug("eventTypeList=" + list);
			assertEquals(5, list.size());
			
			criteria = new EventTypeCriteria();
			criteria.addLoginId(login1.getId());
			criteria.addLoginId(login2.getId());

			list = eventTypeDAO.retrieve(criteria, 0);
			logger.debug("eventTypeList=" + list);
			assertEquals(35, list.size());

			criteria = new EventTypeCriteria();
			criteria.addLoginId(login1.getId());
			criteria.setName("ET1-2");
    
			list = eventTypeDAO.retrieve(criteria, 0);
			logger.debug("eventTypeList=" + list);
			 assertEquals(20	, list.size());
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {

			if (eventTypes1 != null) {
				if (login1 != null) {
					Integer id = login1.getId();
					for (EventType et : eventTypes1) {
						eventTypeDAO.delete(et.getId(), id);
					}
				}
			}
			
			if (eventTypes3 != null) {
				if (login1 != null) {
					Integer id = login1.getId();
					for (EventType et : eventTypes3) {
						eventTypeDAO.delete(et.getId(), id);
					}
				}
			}
			
			if (eventTypes2 != null) {
				if (login2 != null) {
					Integer id = login2.getId();
					for (EventType et : eventTypes2) {
						eventTypeDAO.delete(et.getId(), id);
					}
				}
			}
			if (login1 != null) {
				loginDAO.delete(login1.getId());
			}
			if (login2 != null) {
				loginDAO.delete(login2.getId());
			}
			if (up != null && up.getId() != null) {
				userProfileDAO.delete(up.getId());
			}

		}
	}

}