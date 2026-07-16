package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import dk.schioler.event.base.EventBaseConfiguration;
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
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EventCriteriaTest {

   static {
      System.getProperties().setProperty("event.env", "dev");
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

   public Event createEvent(EventType type, EventTemplate tmpl, LocalDateTime eventTS, String dose, UNIT unit) {
      Event e = new Event();
      e.setLoginId(tmpl.getLoginId());
      e.setCreated(LocalDateTime.now());
      e.setName(tmpl.getName());
      e.setShortName(tmpl.getShortName());
      e.setParentId(type.getId());
      e.setEventTemplateId(tmpl.getId());
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

      String typeName1 ="Parkinson Medicin";
      String typeShortName1 = "PARMED";
      
      String typeName2 ="Anden Medicin";
      String typeShortName2 = "ANDMED";
      
      String typeName3 ="Anden behandling";
      String typeShortName3 = "ANDBEH";
      
      String eTmplName1="Sinemet 25/100"; // is favorite = true
      String eTmplShortName1="SIN-25/100";
      
      String eTmplName2="Ropinirol";
      String eTmplShortName2 ="PARROP";
      
      String eTmplName3="Fysioterapi, ene behandling";
      String eTmplShortName3 ="FYSENE";
      
      boolean doCreate = true;
      boolean doDelete = true;
      
      try {

         if (doCreate) {
//          3 eventyper
//               
            
//          3 eventTemplates
//
//          9 events 
/*
*          2024 1212 130225
*          2024 1223 100225 
*          2024 1223,130225
*          2024 1223 153225
*          2024 1224 130225
*          4 x now()  
*/
//            
            eventTypePark = new EventType();
            eventTypePark.setLoginId(owner.getId());
            eventTypePark.setName(typeName1);
            eventTypePark.setShortName(typeShortName1);
            eventTypePark.setDescription("All medicin, target directly at Mr P");
            eventTypePark.setCreated(LocalDateTime.now());
            eventTypePark = eTypeDAO.insert(eventTypePark);
            types.add(eventTypePark);

            
            
            eventTypeAndMed = new EventType();
            eventTypeAndMed.setLoginId(owner.getId());
            eventTypeAndMed.setName(typeName2);
            eventTypeAndMed.setShortName(typeShortName2);
            eventTypeAndMed.setDescription("All other medicin");
            eventTypeAndMed.setCreated(LocalDateTime.now());
            eventTypeAndMed = eTypeDAO.insert(eventTypeAndMed);
            types.add(eventTypeAndMed);

            
            eventTypeAndBeh = new EventType();
            eventTypeAndBeh.setLoginId(owner.getId());
            eventTypeAndBeh.setName(typeName3);
            eventTypeAndBeh.setShortName(typeShortName3);
            eventTypeAndBeh.setDescription("Anden behandling, ex fysioterapeut ");
            eventTypeAndBeh.setCreated(LocalDateTime.now());
            eventTypeAndBeh = eTypeDAO.insert(eventTypeAndBeh);
            types.add(eventTypeAndBeh);

             
            eventTmplSinemet = new EventTemplate();
            eventTmplSinemet.setCreated(LocalDateTime.now());
            eventTmplSinemet.setLoginId(owner.getId());
            eventTmplSinemet.setName(eTmplName1);
            eventTmplSinemet.setDescription("A very common pill based treatment of mr P");
            eventTmplSinemet.setShortName(eTmplShortName1);
            eventTmplSinemet.setFavorite(true);
            eventTmplSinemet.setDose(new String("0.25"));
            eventTmplSinemet.setUnit(UNIT.MILLIGRAM);
            eventTmplSinemet.setParentId(eventTypePark.getId());
            eventTmplSinemet = eTmplDAO.insert(eventTmplSinemet);
            templates.add(eventTmplSinemet);

            eventTmplRopinirol = new EventTemplate();
            eventTmplRopinirol.setCreated(LocalDateTime.now());
            eventTmplRopinirol.setLoginId(owner.getId());
            eventTmplRopinirol.setName(eTmplName2);
            eventTmplRopinirol.setDescription("A very common pill based treatment of mr P");
            eventTmplRopinirol.setShortName(eTmplShortName2);
            eventTmplRopinirol.setDose(new String("8"));
            eventTmplRopinirol.setUnit(UNIT.MILLIGRAM);
            eventTmplRopinirol.setParentId(eventTypePark.getId());
            eventTmplRopinirol = eTmplDAO.insert(eventTmplRopinirol);
            templates.add(eventTmplRopinirol);

            
            eventTmplFysEne = new EventTemplate();
            eventTmplFysEne.setCreated(LocalDateTime.now());
            eventTmplFysEne.setLoginId(owner.getId());
            eventTmplFysEne.setName(eTmplName3);
            eventTmplFysEne.setDescription("Ene fys");
            eventTmplFysEne.setShortName(eTmplShortName3);
            eventTmplFysEne.setDose(new String("0.5"));
            eventTmplFysEne.setUnit(UNIT.HOURS);
            eventTmplFysEne.setParentId(eventTypeAndBeh.getId());
            eventTmplFysEne = eTmplDAO.insert(eventTmplFysEne);
            templates.add(eventTmplFysEne);

            event1 = createEvent(eventTypePark, eventTmplSinemet, null, null, null);
            event3 = createEvent(eventTypePark,eventTmplRopinirol, null, null, null);
            event4 = createEvent(eventTypePark,eventTmplRopinirol, null, new String("10"), UNIT.MILLIGRAM);
            event6 = createEvent(eventTypeAndBeh, eventTmplFysEne, null, null, null);
            event9 = createEvent(eventTypeAndBeh, eventTmplFysEne, LocalDateTime.of(2024, 12, 12, 13, 02, 25), null, null);
            event8 = createEvent(eventTypePark,eventTmplRopinirol, LocalDateTime.of(2024, 12, 23, 10, 02, 25), null, null);
            event5 = createEvent(eventTypePark,eventTmplSinemet, LocalDateTime.of(2024, 12, 23, 13, 02, 25), String.valueOf(12), UNIT.MILLIGRAM);
            event7 = createEvent(eventTypePark,eventTmplSinemet, LocalDateTime.of(2024, 12, 23, 15, 32, 25), null, null);
            event2 = createEvent(eventTypePark,eventTmplSinemet, LocalDateTime.of(2024, 12, 24, 13, 02, 25), null, null);

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
            logger.debug("doCreate=false");
         }

         EventTypeCriteria eTypeCrit = new EventTypeCriteria();
         eTypeCrit.setShortName(typeShortName1);
         List<EventType> list = eTypeDAO.retrieve(eTypeCrit, 0);
         for (EventType e : list) {
            logger.debug(e.toString());
         }

         assertNotNull(list);
         assertTrue(list.size() == 1);
         EventType eventType = list.get(0);
         assertEquals(typeName1, eventType.getName());
         logger.debug("typeCrit1");
         

         EventTemplateCriteria tmplCriteria = new EventTemplateCriteria();
         tmplCriteria.addId(eventTmplRopinirol.getId());
         tmplCriteria.addId(eventTmplFysEne.getId());
               
         List<EventTemplate> tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
         
         for (EventTemplate e : tmplList) {
            logger.debug(e.toString());
         }


         assertNotNull(tmplList);
         assertTrue(tmplList.size() == 2);
         
         EventTemplate eventTemplate = tmplList.get(0);
         assertTrue(eventTemplate.getId().equals(eventTmplRopinirol.getId()));
         assertFalse(eventTemplate.isFavorite());
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
         assertTrue(eTmplShortName1.equals(eventTemplate.getShortName()));
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
         
//         tmplCriteria = new EventTemplateCriteria();
//         tmplCriteria.setDoseMin("0");
//         tmplCriteria.setDoseMax("1");         
//         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
//         for (EventTemplate e : tmplList) {
//            logger.debug(e.toString());
//         }
//         assertNotNull(tmplList);
//         assertTrue(tmplList.size() == 2);
//         logger.debug("tmplCrit4");
//         
//         tmplCriteria = new EventTemplateCriteria();
//         tmplCriteria.setDoseMin(String.valueOf(5));
//         tmplCriteria.setDoseMax(String.valueOf(10));         
//         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
//         for (EventTemplate e : tmplList) {
//            logger.debug(e.toString());
//         }
//         assertNotNull(tmplList);
//         assertTrue(tmplList.size() == 1);
//         logger.debug("tmplCrit5");
//         
//         tmplCriteria = new EventTemplateCriteria();
//         tmplCriteria.setDoseMin(String.valueOf(0));
//         tmplCriteria.setDoseMax(String.valueOf(1));         
//         tmplCriteria.setUnit(UNIT.MILLIGRAM);
//         tmplList = eTmplDAO.retrieve(tmplCriteria, 0);
//         for (EventTemplate e : tmplList) {
//            logger.debug(e.toString());
//         }
//         assertNotNull(tmplList);
//         assertTrue(tmplList.size() == 1);
//         logger.debug("tmplCrit6");
//         
         // EVENTS: **********************************''''
         logger.debug("**************************");
         logger.debug("criteria.  name = Ropinirol");
         EventCriteria ec = null;         
         ec = new EventCriteria();
         ec.setName("Ropinirol");
         logger.debug("eventCriteria="+ec);
         List<Event> eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         
         assertNotNull(eventList);
         assertEquals(3, eventList.size());
         logger.debug("eventCrit1");
         
         
         ec = new EventCriteria();
         ec.addEventTemplateId(eventTmplFysEne.getId());
         eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         assertNotNull(eventList);
         assertEquals(2, eventList.size());
         logger.debug("eventCrit2");
         
         ec = new EventCriteria();
         LocalDateTime start = LocalDateTime.of(2024, 12, 23, 0, 0, 0);
         LocalDateTime end = LocalDateTime.of(2024, 12, 23, 23, 59, 59);
         ec.setEventTSInterval(start, end);
         
         eventList = eventDAO.retrieve(ec, 0);
         for (Event e : eventList) {
            logger.debug(e.toString());
         }
         assertNotNull(eventList);
         assertEquals(3, eventList.size());
         logger.debug("eventCrit3");

         ec = new EventCriteria();
         ec.addEventTemplateId(eventTmplFysEne.getId());
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
         if (doDelete) {
            if (events != null) {
               for (Event e : events) {
                  eventDAO.delete(e.getId(), null);
               }
            }
            if (templates != null) {
               for (EventTemplate tmpl : templates) {
                  eTmplDAO.delete(tmpl.getId(), null);
               }
            }

            if (types != null) {
               for (EventType eventType : types) {
                  eTypeDAO.delete(eventType.getId(), eventType.getLoginId());
               }
            }
         }

      }
   }

}
