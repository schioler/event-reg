package test.dk.schioler.event.dao;

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

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EventDAOTest extends AbstractJUnit4SpringContextTests {

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
	LoginDAO loginDAO;

	@Test
	public void testStoreEventObjects() {
		Integer eventTypeIid = null;
		Integer eventTmplid = null;
		Integer eventId = null;
		try {
			Login login = loginDAO.getLogin("");
			
			EventType et = new EventType();
			et.setName("eventType1");
			et.setDescription("et");
			et.setDescription("EventypeTest nr 1");
			EventType eventType = eventTypeDAO.insert(et);
			logger.debug("et=" + et);
			logger.debug("insert=" + eventType);
			assertTrue(eventType != null);
			assertTrue(eventType.getId() != null);
			eventTypeIid = eventType.getId();
			
			eventType.setDescription("updated");
			eventTypeDAO.update(eventType);

			EventTemplate eTmpl = new EventTemplate();
			// null, eventTypeIid, "eTmpl", "Simet", "desc", "unit", "dose"
			eTmpl.setParentId(et.getId());
			eTmpl.setLoginId(login.getId());			
			eTmpl.setName("Sinemet");
			eTmpl.setDescription("SIN");
			eTmpl.setDescription("Tmpl Description");
			eTmpl.setDose("dose");
			eTmpl.setUnit("unit");
			
			EventTemplate eventTmpl = eventTemplateDAO.insert(eTmpl);
			eventTmplid = eventTmpl.getId();

			eventTmpl.setDescription("added description");
			eventTemplateDAO.update(eventTmpl);
			
			Event e = new Event();
			//null, eventTmplid, "" , null
			e.setParentId(eTmpl.getId());
			e.setLoginId(login.getId());
			e.setName("eName");
			e.setDescription("sName");
			e.setNote("note");
			e.setDose("dose");
			e.setUnit("unit");
			e.setEventTS(LocalDateTime.now());
			
			Event e1 = eventDAO.insert(e);
			eventId = e1.getId();

			
			
			e1.setNote("note added");
			int update = eventDAO.update(e1);
			
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {
//			eventDAO.delete(eventId);
//			eventTemplateDAO.delete(eventTmplid);
//			eventTypeDAO.delete(eventTypeIid);  
			
		}
	}

}