package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
import dk.schioler.event.base.dao.StateAspectDAO;
import dk.schioler.event.base.dao.StateRatingDAO;
import dk.schioler.event.base.dao.StateRegistrationDAO;
import dk.schioler.event.base.dao.criteria.StateAspectCriteria;
import dk.schioler.event.base.dao.criteria.StateRatingCriteria;
import dk.schioler.event.base.entity.StateAspect;
import dk.schioler.event.base.entity.StateRating;
import dk.schioler.event.base.entity.StateRegistration;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class StateCriteriaDAOTest {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   LoginDAO loginDAO;

   @Autowired
   StateAspectDAO aspectDAO;

   @Autowired
   StateRegistrationDAO regDAO;

   @Autowired
   StateRatingDAO rateDAO;

   private Login getOwnerLogin() {
      Login owner = null;
      List<Login> ownerLogins = loginDAO.getOwnerLogins();
      for (Login login : ownerLogins) {
         logger.debug("login=" + login);
         owner = login;
         break;
      }
      return owner;
   }

   private StateAspect createAspect(Integer ownerId, String name, String shortName, String description) {
      StateAspect aspect = new StateAspect();
      aspect.setLoginId(ownerId);
      aspect.setName(name);
      aspect.setShortName(shortName);
      aspect.setDescription(description);
      aspect.setCreated(LocalDateTime.now());

      aspect = aspectDAO.insert(aspect);

      return aspect;
   }

   private StateRegistration createRegistration(Integer ownerId, LocalDateTime regTS) {
      StateRegistration reg = new StateRegistration();
      reg.setLoginId(ownerId);
      reg.setCreated(LocalDateTime.now());
      reg.setRegistrationTS(regTS);

      reg = regDAO.insert(reg);

      return reg;
   }

   private StateRating createRating(Integer ownerId, Integer aspectId, Integer registrationId, Integer rating, LocalDateTime ratingTS) {
      StateRating rating1 = new StateRating();
      rating1.setLoginId(ownerId);
      rating1.setCreated(LocalDateTime.now());
      rating1.setStateAspectId(aspectId);
      rating1.setStateRegistrationId(registrationId);
      rating1.setRating(rating);
      rating1.setRatingTS(ratingTS);

      rating1 = rateDAO.insert(rating1);
      return rating1;
   }

   @Test
   public void test() {
      boolean remove = false;
      boolean create = false;

      Login owner = getOwnerLogin();

      StateAspect nauseaAspect = null;
      StateAspect heacheAspect = null;
      StateAspect digestionAspect = null;
      StateAspect limbMoveAspect = null;

      StateRegistration reg1 = null;
      StateRegistration reg2 = null;
      StateRegistration reg3 = null;

      StateRating rating1 = null;
      StateRating rating2 = null;
      StateRating rating3 = null;
      StateRating rating4 = null;
      StateRating rating5 = null;
      StateRating rating6 = null;
      StateRating rating7 = null;
      StateRating rating8 = null;

      try {
         if (create) {
            nauseaAspect = createAspect(owner.getId(), "Nausea", "NAU", "1=quite ok, 10=Vomitting all over");
            heacheAspect = createAspect(owner.getId(), "Headache", "HA", "1: no headache, 10: migraine ");
            digestionAspect = createAspect(owner.getId(), "Digestion", "DIG", "1: normal shit, 10: no shit sherlock ");
            limbMoveAspect = createAspect(owner.getId(), "Moveability", "MOV", "1= 100% free movenment, 10: very locked");

            reg1 = createRegistration(owner.getId(), LocalDateTime.of(2024, 7, 6, 07, 35));
            rating1 = createRating(owner.getId(), nauseaAspect.getId(), reg1.getId(), 1, LocalDateTime.of(2024, 7, 6, 07, 35));
            rating4 = createRating(owner.getId(), limbMoveAspect.getId(), reg1.getId(), 4, LocalDateTime.of(2024, 7, 6, 07, 35));
            rating8 = createRating(owner.getId(), nauseaAspect.getId(), reg1.getId(), 4, LocalDateTime.of(2024, 7, 6, 07, 35));

            reg2 = createRegistration(owner.getId(), LocalDateTime.of(2024, 9, 6, 02, 35));
            rating2 = createRating(owner.getId(), heacheAspect.getId(), reg2.getId(), 1, LocalDateTime.of(2024, 9, 6, 02, 35));
            rating5 = createRating(owner.getId(), digestionAspect.getId(), reg2.getId(), 10, LocalDateTime.of(2024, 9, 6, 02, 35));

            reg3 = createRegistration(owner.getId(), LocalDateTime.of(2024, 11, 6, 17, 35));
            rating3 = createRating(owner.getId(), nauseaAspect.getId(), reg3.getId(), 7, LocalDateTime.of(2024, 11, 6, 17, 35));
            rating6 = createRating(owner.getId(), limbMoveAspect.getId(), reg3.getId(), 8, LocalDateTime.of(2024, 11, 6, 17, 35));
            rating7 = createRating(owner.getId(), digestionAspect.getId(), reg3.getId(), 8, LocalDateTime.of(2024, 11, 6, 17, 35));

         }
         StateAspectCriteria aspectCrit = null;
         List<StateAspect> list = null;
         StateAspect stateAspect = null;

         // CRITERIA: NAME
         aspectCrit = new StateAspectCriteria();
         aspectCrit.setName("Headache");
         list = aspectDAO.retrieve(aspectCrit, 0);
         assertNotNull(list);
         assertEquals(1, list.size());
         stateAspect = list.get(0);
         logger.debug("StateAspect=" + stateAspect);
         assertEquals("Headache", stateAspect.getName());
         assertEquals(Integer.valueOf(2), ((Integer) stateAspect.getLoginId()));
         LocalDateTime created = stateAspect.getCreated();
         assertEquals(2025, created.getYear());
         assertEquals(2, created.getMonthValue());
         assertEquals(14, created.getDayOfMonth());
         assertEquals(10, created.getHour());
         assertEquals(36, created.getMinute());
         assertEquals(14, created.getSecond());

         // CRITERIA SHORT NAME
         aspectCrit = new StateAspectCriteria();
         aspectCrit.setShortName("DIG");
         list = aspectDAO.retrieve(aspectCrit, 0);
         assertNotNull(list);
         assertEquals(1, list.size());
         stateAspect = list.get(0);
         logger.debug("StateAspect=" + stateAspect);
         assertEquals("Digestion", stateAspect.getName());
         assertEquals("1: normal shit, 10: no shit sherlock ", stateAspect.getDescription());

         // Criteria CreatedTime
         aspectCrit = new StateAspectCriteria();
         aspectCrit.setCreatedStartTime(LocalDateTime.of(2025, 2, 14, 10, 00));
         aspectCrit.setCreatedEndTime(LocalDateTime.of(2025, 2, 14, 11, 00));
         list = aspectDAO.retrieve(aspectCrit, 0);
         assertEquals(4, list.size());

         // Criteria: LoginId
         aspectCrit = new StateAspectCriteria();
         aspectCrit.addLoginId(2);
         list = aspectDAO.retrieve(aspectCrit, 0);
         assertEquals(5, list.size());
         
         // Criteria: SingleId, present
         aspectCrit = new StateAspectCriteria();
         aspectCrit.addId(26);
         list = aspectDAO.retrieve(aspectCrit, 0);
         if (list != null) {
            for (StateAspect sa : list) {
               logger.debug(sa.toString());
            }
         } else {
            logger.error("received null list of Aspects");
            fail("received unexpected aspects");
         }
         assertEquals(1, list.size());
         
         // Criteria: SingleId, not present
         aspectCrit = new StateAspectCriteria();
         aspectCrit.addId(126);
         list = aspectDAO.retrieve(aspectCrit, 0);
         if (list != null) {
            for (StateAspect sa : list) {
               logger.debug(sa.toString());
            }
         } else {
            logger.error("received null list of Aspects");
            fail("received unexpected aspects");
         }
         assertEquals(0, list.size());  

         // Criteria: MultipleIds
         aspectCrit = new StateAspectCriteria();
         aspectCrit.addId(12);
         aspectCrit.addId(23);
         aspectCrit.addId(24);
         aspectCrit.addId(125);
         list = aspectDAO.retrieve(aspectCrit, 0);
         if (list != null) {
            for (StateAspect sa : list) {
               logger.debug(sa.toString());
            }
         } else {
            logger.error("received null list of Aspects");
         }
         assertEquals(2, list.size());
         
         // MULTIPLE DIFFERENT CRITERIA
         aspectCrit = new StateAspectCriteria();
         aspectCrit.setCreatedStartTime(LocalDateTime.of(2025, 2, 14, 10, 00));
         aspectCrit.setCreatedEndTime(LocalDateTime.of(2025, 2, 14, 11, 00));
         aspectCrit.addId(24);
         aspectCrit.addLoginId(2);
         aspectCrit.setName("Headache");
         list = aspectDAO.retrieve(aspectCrit, 0);
         if (list != null) {
            for (StateAspect sa : list) {
               logger.debug(sa.toString());
            }
         } else {
            logger.error("received null list of Aspects");
         }
         assertEquals(1, list.size());

//         StateRegistrationCriteria registrationCriteria = new StateRegistrationCriteria();
//         LocalDateTime startTS = LocalDateTime.of(2024, 11, 6, 1, 00);
//         LocalDateTime endTS = LocalDateTime.of(2024, 11, 7, 8, 00);       
//         registrationCriteria.setRegistrationTSStart(startTS);
//         registrationCriteria.setRegistrationTSEnd(endTS);
//
//         List<StateRegistration> list2 = regDAO.retrieve(registrationCriteria, 0);
//         logger.debug("stateRegistrationList=" + list2);
//         
//         assertNotNull(list2);
//         assertEquals(1, list2.size());
//         StateRegistration stateRegistration = list2.get(0);
         StateRatingCriteria ratingCriteria = null;
         
         ratingCriteria = new StateRatingCriteria();
         ratingCriteria.addToStateAspectIdList(23);
//         ratingCriteria.addToStateRegistrationIdList(29);
         List<StateRating> list3 = rateDAO.retrieve(ratingCriteria, 0);
         for (StateRating sr : list3) {
            logger.debug("rates=" + sr.toString());            
         }
         
         assertNotNull(list3);
         assertEquals(3, list3.size());
         
         
         ratingCriteria = new StateRatingCriteria();
         ratingCriteria.addToStateAspectIdList(23);
         ratingCriteria.addToStateRegistrationIdList(29);
         list3 = rateDAO.retrieve(ratingCriteria, 0);
         for (StateRating sr : list3) {
            logger.debug("rates=" + sr.toString());            
         }
         
         assertNotNull(list3);
         assertEquals(2, list3.size());

//         LocalDateTime ldt = LocalDateTime.of(2024, 7, 6, 07, 35);
         
         
//         for (StateRating rating : list3) {
//            assertEquals(ldt, rating.getRatingTS());
//         }

      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         fail(e.getMessage());
      } finally {
         if (remove) {
            if (rating1 != null) {
               rateDAO.delete(rating1.getId(), null);
            }
            if (rating2 != null) {
               rateDAO.delete(rating2.getId(), null);
            }
            if (rating3 != null) {
               rateDAO.delete(rating3.getId(), null);
            }
            if (rating4 != null) {
               rateDAO.delete(rating4.getId(), null);
            }
            if (rating5 != null) {
               rateDAO.delete(rating5.getId(), null);
            }
            if (rating6 != null) {
               rateDAO.delete(rating6.getId(), null);
            }

            if (reg1 != null) {
               regDAO.delete(reg1.getId(), null);
            }
            if (reg2 != null) {
               regDAO.delete(reg2.getId(), null);
            }
            if (reg3 != null) {
               regDAO.delete(reg3.getId(), null);
            }

            if (nauseaAspect != null) {
               aspectDAO.delete(nauseaAspect.getId(), nauseaAspect.getLoginId());
            }

            if (heacheAspect != null) {
               aspectDAO.delete(heacheAspect.getId(), heacheAspect.getLoginId());
            }

            if (digestionAspect != null) {
               aspectDAO.delete(digestionAspect.getId(), digestionAspect.getLoginId());
            }
            if (limbMoveAspect != null) {
               aspectDAO.delete(limbMoveAspect.getId(), limbMoveAspect.getLoginId());
            }
         }
      }
   }

}
