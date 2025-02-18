package test.dk.schioler.event.dao;



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

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventSearchDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class SearchDAOTest extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	EventSearchDAO searchDAO;

	@Autowired
	EventTemplateDAO templateDAO;
	
	@Autowired
	EventTypeDAO eventTypeDAO;
	
	@Test
	public void testParseInputStream() {
		try {
//			TimelineDataContainerFactory factory = new TimelineDataContainerFactoryEventImpl();
//			LocalDateTime startdt= LocalDateTime.of(2024, 6, 1, 10, 10);
//			Timeline timeline = new Timeline("testTL", TIMESLOT_LENGTH.MONTH, startdt,12 , factory);
//			logger.debug("timeline="+timeline.toString());
//			
//			List<Event> searchEvents = searchDAO.searchEvents(timeline.getStartDate(), timeline.getEndDate(), null, null);
//			
//			for (Event event : searchEvents) {
//				logger.debug("eI="+event);
//			}
			
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {
			
		}
	}

}
