package dk.schioler.event.base.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.xml.EventTypeXMLHelper;
import dk.schioler.event.base.xml.EventXMLException;

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
	
	static ApplicationContext ctx; 

//	static String[] cfgFiles = new String[] { "src/main/resources/ApplicationContext.xml" };

	public EstablishBasisEventTypesAndTmplsMain() {
		
	}

	private static String eventFile = "src/main/data/event-base-data.xml";

	public static void main(String[] args) {
		try {
			ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);
			
			EstablishBasisEventTypesAndTmplsMain self = ctx.getBean(EstablishBasisEventTypesAndTmplsMain.class); 
					
			List<EventType> eventTypesAndTemplates = self.readEventTypesAndTemplates(eventFile);
			self.persistEventTypesAndTemplates(eventTypesAndTemplates);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			System.exit(1);
		}
	}
	
	public void persistEventTypesAndTemplates(List<EventType> types) {
		for (EventType eventType : types) {
			EventType typeInserted = eventTypeDAO.insert(eventType);
			List<EventTemplate> templates = eventType.getEventTemplates();
			for (EventTemplate tmpl : templates) {
				tmpl.setEventTypeId(typeInserted.getId());
				eventTemplateDAO.insert(tmpl);
			}
		}
	}

	public List<EventType> readEventTypesAndTemplates(String fileName) {
		try {
			FileInputStream fis = new FileInputStream(new File(fileName));
			EventTypeXMLHelper xmlHelper = new EventTypeXMLHelper();
			List<EventType> eventTypes = xmlHelper.buildEventTypesFromXML(fis);
			return eventTypes;
		} catch (IOException e) {
			throw new EventXMLException(e.getMessage(), e);
		}
	}
}
