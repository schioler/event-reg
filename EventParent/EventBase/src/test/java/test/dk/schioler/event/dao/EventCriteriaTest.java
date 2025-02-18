package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.criteria.EventCriteria;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EventCriteriaTest {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   Logger logger = LoggerFactory.getLogger(getClass());

//   @Autowired
//   TestUserSetupUtil userSetupUtil;
//   

   @Autowired
   LoginDAO loginDAO;

   @Autowired
   EventTypeDAO eTypeDAO;

   @Autowired
   EventTemplateDAO eTmplDAO;

   @Autowired
   EventDAO eventDAO;

   public Event createEvent(EventTemplate tmpl, LocalDateTime eventTS, BigDecimal dose, UNIT unit) {
      Event e = new Event();
      e.setLoginId(tmpl.getLoginId());
      e.setCreated(LocalDateTime.now());
      e.setName(tmpl.getName());
      e.setShortName(tmpl.getShortName());
      e.setParentId(tmpl.getId());
      e.setDescription("descr");
      if (eventTS != null) {
         e.setEventTS(eventTS);
      } else {
         e.setEventTS(LocalDateTime.now());
      }

      if (dose != null) {
         e.setDose(dose);
      } else {
         e.setDose(tmpl.getDose());
      }

      if (unit != null) {
         e.setUnit(unit);
      } else {
         e.setUnit(tmpl.getUnit());
      }

      e = eventDAO.insert(e);
      return e;

   }

   @Test
   public void test() {
      Login owner = null;
      List<Login> ownerLogins = loginDAO.getOwnerLogins();
      for (Login login : ownerLogins) {
         logger.debug("login=" + login);
         owner = login;
         break;
      }

      EventType eventTypePark = null;
      EventType eventTypeAndMed = null;
      EventType eventTypeAndBeh = null;
      EventTemplate eventTmplSinemet = null;
      EventTemplate eventTmplRopinirol = null;
      EventTemplate eventTmplFysEne = null;

      Event event1 = null;
      
      Event event2 = null;
      Event event3 = null;
      Event event4 = null;
      Event event5 = null;
      Event event6 = null;
      Event event7 = null;
      Event event8 = null;
      Event event9 = null;

      List<Event> events = new ArrayList<Event>();
      List<EventTemplate> templates = new ArrayList<EventTemplate>();
      List<EventType> types = new ArrayList<EventType>();

      try {
         boolean doCreate = false;
         boolean doDelete = false;

         if (doCreate) {

            eventTypePark = new EventType();
            eventTypePark.setLoginId(owner.getId());
            eventTypePark.setName("Parkinson Medicin");
            eventTypePark.setShortName("PARMED");
            eventTypePark.setDescription("All medicin, target directly at Mr P");
            eventTypePark.setCreated(LocalDateTime.now());
            eventTypePark = eTypeDAO.insert(eventTypePark);
            types.add(eventTypePark);

            eventTypeAndMed = new EventType();
            eventTypeAndMed.setLoginId(owner.getId());
            eventTypeAndMed.setName("Anden Medicin");
            eventTypeAndMed.setShortName("ANDMED");
            eventTypeAndMed.setDescription("All other medicin");
            eventTypeAndMed.setCreated(LocalDateTime.now());
            eventTypeAndMed = eTypeDAO.insert(eventTypeAndMed);
            types.add(eventTypeAndMed);

            eventTypeAndBeh = new EventType();
            eventTypeAndBeh.setLoginId(owner.getId());
            eventTypeAndBeh.setName("Anden behandling");
            eventTypeAndBeh.setShortName("ANDBEH");
            eventTypeAndBeh.setDescription("Anden behandling, ex fysioterapeut ");
            eventTypeAndBeh.setCreated(LocalDateTime.now());
            eventTypeAndBeh = eTypeDAO.insert(eventTypeAndBeh);
            types.add(eventTypeAndBeh);

            eventTmplSinemet = new EventTemplate();
            eventTmplSinemet.setCreated(LocalDateTime.now());
            eventTmplSinemet.setLoginId(owner.getId());
            eventTmplSinemet.setName("Sinemet 25/100");
            eventTmplSinemet.setDescription("A very common pill based treatment of mr P");
            eventTmplSinemet.setShortName("SIN-25/100");
            eventTmplSinemet.setFavorite(true);
            eventTmplSinemet.setDose(new BigDecimal("0.25"));
            eventTmplSinemet.setUnit(UNIT.MILLIGRAM);
            eventTmplSinemet.setParentId(eventTypePark.getId());
            eventTmplSinemet = eTmplDAO.insert(eventTmplSinemet);
            templates.add(eventTmplSinemet);

            eventTmplRopinirol = new EventTemplate();
            eventTmplRopinirol.setCreated(LocalDateTime.now());
            eventTmplRopinirol.setLoginId(owner.getId());
            eventTmplRopinirol.setName("Ropinirol");
            eventTmplRopinirol.setDescription("A very common pill based treatment of mr P");
            eventTmplRopinirol.setShortName("PARROP");
            eventTmplRopinirol.setDose(new BigDecimal("8"));
            eventTmplRopinirol.setUnit(UNIT.MILLIGRAM);
            eventTmplRopinirol.setParentId(eventTypePark.getId());
            eventTmplRopinirol = eTmplDAO.insert(eventTmplRopinirol);
            templates.add(eventTmplRopinirol);

            eventTmplFysEne = new EventTemplate();
            eventTmplFysEne.setCreated(LocalDateTime.now());
            eventTmplFysEne.setLoginId(owner.getId());
            eventTmplFysEne.setName("Fysioterapi, ene behandling");
            eventTmplFysEne.setDescription("Ene fys");
            eventTmplFysEne.setShortName("FYSENE");
            eventTmplFysEne.setDose(new BigDecimal("0.5"));
            eventTmplFysEne.setUnit(UNIT.HOURS);
            eventTmplFysEne.setParentId(eventTypeAndBeh.getId());
            eventTmplFysEne = eTmplDAO.insert(eventTmplFysEne);
            templates.add(eventTmplFysEne);

            event1 = createEvent(eventTmplSinemet, null, null, null);
            event2 = createEvent(eventTmplSinemet, LocalDateTime.of(2024, 12, 24, 13, 02, 25), null, null);
            event3 = createEvent(eventTmplRopinirol, null, null, null);
            event4 = createEvent(eventTmplRopinirol, null, new BigDecimal(10), UNIT.MILLIGRAM);
            event5 = createEvent(eventTmplSinemet, LocalDateTime.of(2024, 12, 23, 13, 02, 25), BigDecimal.valueOf(12), UNIT.MILLIGRAM);
            event6 = createEvent(eventTmplFysEne, null, null, null);
            event7 = createEvent(eventTmplSinemet, LocalDateTime.of(2024, 12, 23, 15, 32, 25), null, null);
            event8 = createEvent(eventTmplRopinirol, LocalDateTime.of(2024, 12, 23, 10, 02, 25), null, null);
            event9 = createEvent(eventTmplFysEne, LocalDateTime.of(2024, 12, 12, 13, 02, 25), null, null);

            events.add(event1);
            events.add(event2);
            events.add(event3);
            events.add(event4);
            events.add(event5);
            events.add(event6);
            events.add(event7);
            events.add(event7);
            events.add(event8);
            events.add(event9);

         } else {

         }

         EventTypeCriteria eTypeCrit = new EventTypeCriteria();
         eTypeCrit.setShortName("PARMED");
         List<EventType> list = eTypeDAO.retrieve(eTypeCrit, 0);
         for (EventType e : list) {
            logger.debug(e.toString());
         }

         assertNotNull(list);
         assertTrue(list.size() == 1);
         EventType eventType = list.get(0);
         assertEquals("Parkinson Medicin", eventType.getName());
         logger.debug("typeCrit1");
         
         EventTemplateCriteria tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.addId(7);
         tmplCriteria.addId(10);
         List<EventTemplate> tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }

         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 1);
         EventTemplate eventTemplate = tmplList.get(0);
         assertTrue(eventTemplate.getId().equals(7));
         assertTrue(eventTemplate.isFavorite());
         logger.debug("tmplCrit1");
         
         tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.setFavourite(true);
         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }
         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 1);
         eventTemplate = tmplList.get(0);
         assertTrue("SIN-25/100".equals(eventTemplate.getShortName()));
         logger.debug("tmplCrit2");
         
         tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.setFavourite(false);
         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }
         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 2);
         logger.debug("tmplCrit3");
         
         tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.setDoseMin(BigDecimal.valueOf(0));
         tmplCriteria.setDoseMax(BigDecimal.valueOf(1));         
         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }
         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 2);
         logger.debug("tmplCrit4");
         
         tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.setDoseMin(BigDecimal.valueOf(5));
         tmplCriteria.setDoseMax(BigDecimal.valueOf(10));         
         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }
         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 1);
         logger.debug("tmplCrit5");
         
         tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.setDoseMin(BigDecimal.valueOf(0));
         tmplCriteria.setDoseMax(BigDecimal.valueOf(1));         
         tmplCriteria.setUnit(UNIT.MILLIGRAM);
         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }
         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 1);
         logger.debug("tmplCrit6");
         
         // EVENTS: **********************************''''
         EventCriteria ec = null;
         
         ec = new EventCriteria();
         ec.setName("Ropinirol");
         List<Event> eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         assertNotNull(eventList);
         assertEquals(3, eventList.size());
         logger.debug("eventCrit1");
         
         ec = new EventCriteria();
         ec.addEventTemplateId(7);
         eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         assertNotNull(eventList);
         assertEquals(4, eventList.size());
         logger.debug("eventCrit2");
         
         ec = new EventCriteria();
         LocalDateTime start = LocalDateTime.of(2024, 12, 23, 0, 0, 0);
         LocalDateTime end = LocalDateTime.of(2024, 12, 23, 23, 59, 59);
         ec.setEventTSStartDate(start);
         ec.setEventTSEndDate(end);
         eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         assertNotNull(eventList);
         assertEquals(3, eventList.size());
         logger.debug("eventCrit3");

         ec = new EventCriteria();
         ec.addEventTemplateId(Integer.valueOf(9));
          eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         assertNotNull(eventList);
         assertEquals(2, eventList.size());
         logger.debug("eventCrit4");

      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      } finally {
//         if (doDelete) {
//            if (events != null) {
//               for (Event e : events) {
//                  eventDAO.delete(e.getId(), null);
//               }
//            }
//            if (templates != null) {
//               for (EventTemplate tmpl : templates) {
//                  eTmplDAO.delete(tmpl.getId(), null);
//               }
//            }
//
//            if (types != null) {
//               for (EventType eventType : types) {
//                  eTypeDAO.delete(eventType.getId(), eventType.getLoginId());
//               }
//            }
//         }

      }
   }

}
