package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
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

	@Test
	public void testStoreEventObjects() {
		Integer eventTypeIid = null;
		try {
			EventType et = new EventType(null, "eventType1", "et1", "EventypeTest nr 1");
			EventType eventType = eventTypeDAO.insert(et);
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
			eventTemplate.setEventTypeId(eventTypeIid);
			
			eventTemplate = eventTemplateDAO.insert(eventTemplate);
			
			EventTemplate eventTemplate2 = eventTemplateDAO.get(eventTemplate.getId());
			logger.debug("found template:" +eventTemplate2);
			eventTemplate2.setDescription("trut");
			eventTemplateDAO.update(eventTemplate2);
			
			Event event = new Event();
			event.setName("TemplateName");
			event.setShortName("SHORT");
			event.setNote("DDESC");
			event.setDose("25");
			event.setUnit("mg");
			event.setEventTemplateId(eventTemplate2.getId());
			
			Event e = eventDAO.insert(event); 
			e.setNote("jpd");
			eventDAO.update(e);
			
			Event event2 = eventDAO.get(e.getId());
			
			logger.debug("e="+event2);
			
//			eventDAO.delete(event2.getId());
//			eventTemplateDAO.delete(eventTemplate2.getId());
//			eventTypeDAO.delete(eventTypeIid);  
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {
			
		}
	}

}