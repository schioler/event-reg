package test.dk.schioler.event.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EventDAOTest {

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

   @Test
   public void test() {
      Login owner = null;
      List<Login> ownerLogins = loginDAO.getOwnerLogins();
      for (Login login : ownerLogins) {
         logger.debug("login=" + login);
         owner = login;
         break;
      }

      EventType eventType = null;
      EventTemplate eventTmpl = null;
      Event event = null;

      try {
         eventType = new EventType();
         eventType.setLoginId(owner.getId());
         eventType.setName("EventType: Parkinson Medicin");
         eventType.setShortName("EVTY-PMED");
         eventType.setDescription("All medicin, target directly at Mr P");
         eventType.setCreated(LocalDateTime.now());

         eventType = eTypeDAO.insert(eventType);

         eventType.setDescription("Edited eType desription");
         eTypeDAO.update(eventType);

         
         eventTmpl = new EventTemplate();
         eventTmpl.setCreated(LocalDateTime.now());
         eventTmpl.setLoginId(owner.getId());
         eventTmpl.setName("Sinemet 25/100");
         eventTmpl.setDescription("A very common pill based treatment of mr P");
         eventTmpl.setShortName("SIN-25/100");
         eventTmpl.setDose(new BigDecimal("0.25"));
         eventTmpl.setUnit(UNIT.MILLIGRAM);
         eventTmpl.setParentId(eventType.getId());
         
         eventTmpl = eTmplDAO.insert(eventTmpl);
         
         eventTmpl.setUnit(UNIT.KILOGRAMME);
         eTmplDAO.update(eventTmpl);

         event = new Event();
         event.setCreated(LocalDateTime.now());
         event.setLoginId(owner.getId());

         event.setName(eventTmpl.getName());
         event.setShortName(eventTmpl.getShortName());
         event.setDescription(eventTmpl.getDescription());
         
         event.setParent(eventTmpl);
         event.setUnit(eventTmpl.getUnit());
         event.setDose(eventTmpl.getDose());
         event.setEventTS(LocalDateTime.now());
         
         event = eventDAO.insert(event);
         
         event.setName("12");
         eventDAO.update(event);
      } catch (Exception e) {
         logger.error(e.getMessage(), e); 
      } finally {
         if (event != null) {
            eventDAO.delete(event.getId(), null);
         }
         if (eventTmpl != null) {
            eTmplDAO.delete(eventTmpl.getId(), null);
         }

         if (eventType != null) {
            eTypeDAO.delete(eventType.getId(), eventType.getLoginId());
         }

//         if (sa1 != null) {
//            saDAO.delete(sa1.getId(), sa1.etLoginId());
//         }
//         if (sa2 != null) {
//            saDAO.delete(sa2.getId(), sa2.getLoginId());
//         }

      }
   }

}
