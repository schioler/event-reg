package dk.schioler.event.base.main;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;

@Component
public class GenerateTestEventsMain {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	private static Logger logger = LoggerFactory.getLogger(GenerateTestEventsMain.class);

	@Autowired
	EventTypeDAO eventTypeDAO;
	
	@Autowired
	EventTemplateDAO eventTemplateDAO;
	
	@Autowired
	EventDAO eventDAO;
	
	static ApplicationContext ctx; 


	public GenerateTestEventsMain() {
		
	}

	public static void main(String[] args) {
		try {
			int count = 100000;
			ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);
			
			GenerateTestEventsMain self = ctx.getBean(GenerateTestEventsMain.class); 
						
			self.generate(count);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			System.exit(1);
		}
	}
	

	
	


	
	public void generate(int count ) {
		List<EventTemplate> eventTemplate = eventTemplateDAO.lookup();

		UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

		for (int i = 0; i < count; i++) {
			int nextInt = rng.nextInt(0, eventTemplate.size());

			EventTemplate tmpl = eventTemplate.get(nextInt);
			Event randomEvent = generateRandomEvent(tmpl);
			eventDAO.insert(randomEvent);
		}
	}

	public Event generateRandomEvent(EventTemplate template) {
		Event e = new Event();	
		e.setEventTemplateId(template.getId());
		e.setName(template.getName());
		e.setShortName(template.getShortName());
		e.setDose(template.getDose());
		e.setUnit(template.getUnit());

		UniformRandomProvider rng = RandomSource.XO_RO_SHI_RO_128_PP.create();

		int year = rng.nextInt(2020, 2023);
		int month = rng.nextInt(1, 13);
		int day = rng.nextInt(1, 28);
		int hour = rng.nextInt(0, 12);
		int minute = rng.nextInt(1, 60);
		int sec = rng.nextInt(1, 60);
		

		LocalDateTime eventTS = LocalDateTime.of(year, month, day, hour, minute, sec);
		
		e.setEventTS(eventTS);
		return e;

	}
}
