package test.dk.schioler.event.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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

	@Test
	public void testParseInputStream() {
		Integer eventTypeIid = null;
		Integer eventTmplid = null;
		Integer eventId = null;
		try {
			EventType et = new EventType(null, "eventType1", "et1", "EventypeTest nr 1");
			EventType insert = eventTypeDAO.insert(et);
			logger.debug("et=" + et);
			logger.debug("insert=" + insert);
			assertTrue(insert != null);
			assertTrue(insert.getId() != null);
			eventTypeIid = insert.getId();

			EventTemplate eTmpl = new EventTemplate(null, eventTypeIid, "eTmpl", "Simet", "desc", "unit", "dose");
			EventTemplate insert2 = eventTemplateDAO.insert(eTmpl);
			eventTmplid = insert2.getId();

			Event e = new Event();
			//null, eventTmplid, "" , null
			e.setEventTemplateId(eTmpl.getId());
			e.setName("eName");
			e.setShortName("sName");
			e.setNote("note");
			e.setDose("dose");
			e.setUnit("unit");
			Event e1 = eventDAO.insert(e);
			eventId = e1.getId();

		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {
			eventDAO.delete(eventId);
			eventTemplateDAO.delete(eventTmplid);
			eventTypeDAO.delete(eventTypeIid);  
			
		}
	}

}