package test.dk.schioler.event.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.entity.AbstractEntity;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;

public class TestAbstractEntityEventTypeEventTemplateParentChild {

	Logger logger = LoggerFactory.getLogger(getClass());

	private List<EventType> createEventType(int count) {
		List<EventType> list = new ArrayList<EventType>();

		for (int i = 0; i < count; i++) {
			EventType et1 = new EventType();
			et1.setId(i + 1);
			et1.setName("eType" + (i + 1));
			list.add(et1);
			logger.debug("addded " + et1.getName() + " to list");
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
			logger.debug("created template=" + etmpl1);

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
		logger.debug("eventTypes=" + types.size());

		EventType eType1 = types.get(0);
		EventType eType2 = types.get(1);
		EventType eType3 = types.get(2);

		List<EventTemplate> templates = createEventTemplate(5);
		logger.debug("templates.size()=" + templates.size());

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

//		/*
//		 * Adding from typeSide
//		 */
		// ******************************************''''
		eType1.addChild(eTmpl1);
		logger.debug("eType1=" + eType1);
		logger.debug("eTmpl1=" + eTmpl1);

		assertEquals(Integer.valueOf(1), eType1.getId());
		List<AbstractEntity> children = eType1.getChildren();
		assertTrue(children.size() == 1);
		assertEquals(children.get(0), eTmpl1);

		assertTrue(eTmpl1.getParentId() != null);
		assertTrue(eTmpl1.getParent().getId().equals(1));

		/**
		 * Adding from tmplSide
		 */

		// ******************************************''''
		eTmpl2.setParent(eType1);
		logger.debug("eType1=" + eType1);
		logger.debug("eTmpl1=" + eTmpl1);
		logger.debug("eTmpl2=" + eTmpl2);

		List<AbstractEntity> type1Children = eType1.getChildren();
		assertTrue(type1Children.size() == 2);

		assertTrue(type1Children.contains(eTmpl1));
		assertTrue(type1Children.contains(eTmpl2));

		assertEquals("eType1.id", eType1.getId(), Integer.valueOf(1));
		assertEquals("eTmpl1.id", eTmpl1.getId(), Integer.valueOf(1));
		assertEquals("eTmpl2.id", eTmpl2.getId(), Integer.valueOf(2));

		/**
		 * Adding eTmpl1 to another type - will it clean up?
		 */
		eType2.addChild(eTmpl1);

		assertEquals("eType1.id", eType1.getId(), Integer.valueOf(1));
		assertEquals("eType2.id", eType2.getId(), Integer.valueOf(2));
		assertEquals("eTmpl1.id", eTmpl1.getId(), Integer.valueOf(1));
		assertEquals("eTmpl2.id", eTmpl2.getId(), Integer.valueOf(2));

		type1Children = eType1.getChildren();
		assertTrue(type1Children.size() == 1);

		assertTrue(type1Children.contains(eTmpl2));

		List<AbstractEntity> eType2Children = eType2.getChildren();
		assertTrue(eType2Children.size() == 1);
		assertTrue(eType2Children.contains(eTmpl1));

		// ******************************************'

		eType3.addChild(eTmpl5);
		eType3.addChild(eTmpl4);

		List<AbstractEntity> eType3Children = eType3.getChildren();
		assertNotNull(eType3Children);
		assertEquals(eType3Children.size(), 2);
		AbstractEntity abstractEntity = eType3Children.get(0);
		assertEquals(eTmpl5, abstractEntity);
		abstractEntity = eType3Children.get(1);
		assertEquals(eTmpl4, abstractEntity);

		logger.debug("children:" + eType3Children);
		eTmpl4.addChild(event1);
		eTmpl4.addChild(event3);
		eTmpl4.addChild(event2);

		logger.debug("tmpl4=" + eTmpl4);
		logger.debug("tmpl4.parent=" + eTmpl4.getParent());
		logger.debug("tmpl4.children="+ eTmpl4.getChildren());
		// parent == eType
		assertTrue(eTmpl4.getParent() != null);
		AbstractEntity parent = eTmpl4.getParent();
		assertEquals(Integer.valueOf(3), parent.getId());
		
		
	}

	@Test
	public void testSetEventType() {

	}

}
