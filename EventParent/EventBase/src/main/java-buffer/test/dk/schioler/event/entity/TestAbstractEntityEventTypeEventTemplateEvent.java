package test.dk.schioler.event.entity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;

public class TestAbstractEntityEventTypeEventTemplateEvent {

	Logger logger = LoggerFactory.getLogger(getClass());

	private List<EventType> createEventType(int count) {
		List<EventType> list = new ArrayList<EventType>();

		for (int i = 0; i < count; i++) {
			EventType et1 = new EventType();
			et1.setId(i + 1);
			et1.setName("eType" + (i + 1));
			list.add(et1);
//			logger.debug("addded " + et1.getName() + " to list");
		}
		return list;
	}

	private List<EventTemplate> createEventTemplate(int no) {
		List<EventTemplate> list = new ArrayList<EventTemplate>();

		for (int i = 0; i < no; i++) {
			EventTemplate etmpl1 = new EventTemplate();
			etmpl1.setId(i + 1);
			etmpl1.setName("eTmpl" + (i + 1));
			list.add(etmpl1);
//			logger.debug("created template=" + etmpl1);

		}
		return list;
	}

	private List<Event> createEvents(int count) {
		List<Event> events = new ArrayList<Event>();
		for (int i = 0; i < count; i++) {
			Event event = new Event();
			event.setId(i + 1);
			event.setName("Event" + i);
			events.add(event);
		}

		return events;
	}

	/*      
	 * 
	 */
	@Test
	public void testSetEventTypeId() {
		List<EventType> types = createEventType(3);
//		logger.debug("eventTypes=" + types.size());

		EventType eType1 = types.get(0);
		EventType eType2 = types.get(1);
		EventType eType3 = types.get(2);

		List<EventTemplate> templates = createEventTemplate(5);
//		logger.debug("templates.size()=" + templates.size());

		EventTemplate eTmpl1 = templates.get(0);
		EventTemplate eTmpl2 = templates.get(1);
		EventTemplate eTmpl3 = templates.get(2);
		EventTemplate eTmpl4 = templates.get(3);
		EventTemplate eTmpl5 = templates.get(4);

		List<Event> events = createEvents(5);

		Event event1 = events.get(0);
		Event event2 = events.get(1);
		Event event3 = events.get(2);
		Event event4 = events.get(3);
		Event event5 = events.get(4);

		logger.debug("type="+ eType1);
		logger.debug("");
		logger.debug("tmpl="+eTmpl1);
		logger.debug("");
		logger.debug("event="+event1);
		
	}


}
