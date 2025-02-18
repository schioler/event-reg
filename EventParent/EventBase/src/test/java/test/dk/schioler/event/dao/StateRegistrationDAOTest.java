package test.dk.schioler.event.dao;

import static org.junit.Assert.fail;

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
import dk.schioler.event.base.configuration.TestUserSetupUtil;
import dk.schioler.event.base.dao.StateAspectDAO;
import dk.schioler.event.base.dao.StateRatingDAO;
import dk.schioler.event.base.dao.StateRegistrationDAO;
import dk.schioler.event.base.entity.StateAspect;
import dk.schioler.event.base.entity.StateRating;
import dk.schioler.event.base.entity.StateRegistration;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class StateRegistrationDAOTest {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   Logger logger = LoggerFactory.getLogger(getClass());


   @Autowired
   LoginDAO loginDAO;

   @Autowired
   StateAspectDAO saDAO;

   @Autowired
   StateRegistrationDAO srDAO;

   @Autowired
   StateRatingDAO rateDAO;

   @Test
   public void test() {
      Login owner = null;
      List<Login> ownerLogins = loginDAO.getOwnerLogins();
      for (Login login : ownerLogins) {
         logger.debug("login=" + login);
         owner = login;
         break;
      }

      StateAspect sa = null;
      StateAspect sa1 = null;
      StateAspect sa2 = null;
      
      StateRegistration sr = null;
      StateRating sRa = null;
      try {
         sa = new StateAspect();
         sa.setLoginId(owner.getId());
         sa.setName("Nausea");
         sa.setShortName("NAU");
         sa.setDescription("NAUSEA aSpect 1=quite ok, 10=Vomitting all over");
         sa.setCreated(LocalDateTime.now());

         sa = saDAO.insert(sa);
         
         sa.setDescription("Edited desription");
         saDAO.update(sa);
         
         
//         sa1 = new StateAspect();
//         sa1.setLoginId(owner.getId());
//         sa1.setName("Low");
//         sa1.setShortName("LOW");
//         sa1.setDescription("LOW, 1=slight inhibation, 10=strong inhibitation");
//         sa1.setCreated(LocalDateTime.now());
//
//         sa1 = saDAO.insert(sa1);
//
//         sa2 = new StateAspect();
//         sa2.setLoginId(owner.getId());
//         sa2.setName("Hard digestion");
//         sa2.setShortName("HARD_DIG");
//         sa2.setDescription("1=slight inhibation, 10=strong inhibitation");
//         sa2.setCreated(LocalDateTime.now());
//
//         sa2 = saDAO.insert(sa2);

         sr = new StateRegistration();
         sr.setCreated(LocalDateTime.now());
         sr.setLoginId(owner.getId());
         sr.setRegistrationTS(LocalDateTime.now());
         sr = srDAO.insert(sr);

         sr.setRegistrationTS(LocalDateTime.now());         
         srDAO.update(sr);
         
         sRa = new StateRating();
         sRa.setCreated(LocalDateTime.now());
         sRa.setStateAspectId(sa.getId());
         sRa.setLoginId(owner.getId());
         sRa.setStateRegistrationId(sr.getId());
         sRa.setRating(5);
         sRa.setRatingTS(LocalDateTime.now());
         
         sRa = rateDAO.insert(sRa);
         
         sRa.setRating(0);
         rateDAO.update(sRa);
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      } finally {
         if(sRa != null) {
            rateDAO.delete(
                  sRa.getId(), null);
         }
         if(sr != null) {
            srDAO.delete(sr.getId(), null);
         }
         
         if (sa != null) {
            saDAO.delete(sa.getId(), sa.getLoginId());
         }
         
         
//         if (sa1 != null) {
//            saDAO.delete(sa1.getId(), sa1.getLoginId());
//         }
//         if (sa2 != null) {
//            saDAO.delete(sa2.getId(), sa2.getLoginId());
//         }

      }
   }

}
