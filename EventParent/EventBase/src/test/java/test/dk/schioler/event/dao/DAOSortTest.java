package test.dk.schioler.event.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class DAOSortTest {
	
	static {
		System.setProperty("event.env","dev1");
	}
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	EventTypeDAO eventTypeDAO;
		
	@Autowired
	EventTemplateDAO eventTemplateDAO;
	
		

	@Test
	public void test() {
		List<EventType> lookup = eventTypeDAO.lookup();
		for (EventType l : lookup) {
			
			logger.debug(l.getName());
		}
		
		List<EventTemplate> lookup2 = eventTemplateDAO.lookup();
		for (EventTemplate eventTemplate : lookup2) {
			logger.debug(eventTemplate.getName() );
			
		}
	}

}
