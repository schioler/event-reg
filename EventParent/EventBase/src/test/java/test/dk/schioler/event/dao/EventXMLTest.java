package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.entity.AbstractEntity;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.xml.EventTypeXMLHelper;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EventXMLTest extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());


	@Test
	public void testParseInputStream() {
		EventTypeXMLHelper xmlHelper = new EventTypeXMLHelper();
		
		String filePath = "src/main/data/event-base-data.xml";
		
		List<AbstractEntity> eventTypes = xmlHelper.buildEventTypes(filePath);
		
		assertEquals(6, eventTypes.size());
		
		
		EventType eType = (EventType) eventTypes.get(0);
		assertEquals("Medicin-parkinson", eType.getName());
		assertEquals("MEDICIN-PARK", eType.getShortName());
				
		List<AbstractEntity> templates = eType.getChildren();
		assertEquals(7, templates.size());
				
		EventTemplate template = (EventTemplate) templates.get(0);		
		assertEquals("Sinemet 25/100", template.getName());
		assertEquals("SINE_25100",template.getShortName() );
		
		template = (EventTemplate) templates.get(6);
		
		assertEquals("Entacapone", template.getName());
		assertEquals("ENTACAP-200",template.getShortName() );

		eType = (EventType) eventTypes.get(4);
		 
		assertEquals("Fysik", eType.getName());
		assertEquals("FYSIK", eType.getShortName());
	}

}
