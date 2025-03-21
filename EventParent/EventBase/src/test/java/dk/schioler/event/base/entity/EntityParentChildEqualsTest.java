package dk.schioler.event.base.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityParentChildEqualsTest {
   Logger logger = LoggerFactory.getLogger(getClass());

   @Test
   public void testEntityEquals() {
      Event e1 = null;
      Event e2 = null;
      EventTemplate tmpl1 = null;
      EventTemplate tmpl2 = null;
      EventType etyp1 = null;
      EventType etyp2 = null;

      try {
         etyp1 = new EventType();
         etyp2 = new EventType();
         assertEquals(etyp1, etyp2);

         etyp1.setId(1);
         etyp2.setId(1);
         assertEquals(etyp1, etyp2);
         
         etyp1.setLoginId(12);
         etyp2.setLoginId(12);
         assertEquals(etyp1, etyp2);
         
         etyp1.setName("yummi");
         etyp2.setName("yummi");
         assertEquals(etyp1, etyp2);
         
         etyp1.setShortName("short");
         etyp2.setShortName("short");
         assertEquals(etyp1, etyp2);
         
         tmpl1 = new EventTemplate();
         tmpl2 = new EventTemplate();
         assertEquals(tmpl1, tmpl2);

         logger.debug("----------------- START addChild");
         etyp1.addChild(tmpl1);
         logger.debug("----------------- DONE addChild");

         logger.debug("----------------- START addChild");
         etyp2.addChild(tmpl2);
         logger.debug("----------------- DONE addChild");
         logger.debug("etyp1:"+ etyp1);
         logger.debug("etyp2:"+ etyp2);

         
         assertEquals(etyp1, etyp2);
         assertEquals(tmpl1, tmpl2);

         etyp1.removeChild(tmpl1);
         etyp2.removeChild(tmpl2);
         
         tmpl1.setId(10);
         tmpl2.setId(10);
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setDose(BigDecimal.valueOf(2));
         tmpl2.setDose(BigDecimal.valueOf(2));
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setUnit(UNIT.DECILITER);
         tmpl2.setUnit(UNIT.DECILITER);
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setFavorite(true);
         tmpl2.setFavorite(true);
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setSortOrder(Integer.valueOf(120));
         tmpl2.setSortOrder(Integer.valueOf(120));
         assertEquals(tmpl1, tmpl2);

         
         tmpl1.setId(10);
         tmpl2.setId(1);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setId(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setId(10);
         assertEquals(tmpl1, tmpl2);
         
         
         tmpl1.setLoginId(12);
         tmpl2.setLoginId(1);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setLoginId(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setLoginId(12);
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setName("NAME");
         tmpl2.setName("NAME1");
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setName(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setName("NAME");
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setShortName("SHORTNAME");
         tmpl2.setShortName("SHORT-NAME1");
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setShortName(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setShortName("SHORTNAME");
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setDose(BigDecimal.valueOf(1.2));
         tmpl2.setDose(BigDecimal.valueOf(10));
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setDose(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setDose(BigDecimal.valueOf(1.2));
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setUnit(UNIT.CENTILITER);
         tmpl2.setUnit(UNIT.MILLILITER);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setUnit(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setUnit(UNIT.CENTILITER);
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setFavorite(false);
         tmpl2.setFavorite(true);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setFavorite(false);
         assertEquals(tmpl1, tmpl2);
         
         tmpl1.setSortOrder(123);
         tmpl2.setSortOrder(23);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setSortOrder(null);
         assertNotEquals(tmpl1, tmpl2);
         tmpl2.setSortOrder(123);
         assertEquals(tmpl1, tmpl2);

         // Event
         e1 = new Event();
         e2 = new Event();
         assertEquals(e1, e2);

         e1.setId(6);
         e2.setId(6);

         logger.debug("----------------- START addChild");
         tmpl1.addChild(e1);
         List<AbstractEntityParentChild> children = tmpl1.getChildren();
         assertEquals(1, children.size());
         logger.debug("----------------- DONE addChild");
         logger.debug("tmpl1=" + tmpl1.toString());

         logger.debug("----------------- START removeChild");
         tmpl1.removeChild(e1);
         logger.debug("----------------- DONE removeChild");
         children = tmpl1.getChildren();
         assertEquals(0, children.size());
         logger.debug("tmpl1=" + tmpl1.toString());
         
         
         logger.debug("etyp1="+etyp1.toString());        
         List<AbstractEntityParentChild> children2 = etyp1.getChildren();
         assertEquals(0, children2.size());
         
         logger.debug("----------------- START removeChild");
         etyp1.removeChild(tmpl1);
         logger.debug("----------------- DONE removeChild");
         logger.debug("etyp1=" + etyp1.toString());
         
         etyp1.setId(9);
         tmpl1.setId(10);
         tmpl2.setId(11);
         
         etyp1.addChild(tmpl1);
         etyp1.addChild(tmpl2);
         
         children2 = etyp1.getChildren();
         
         assertEquals(2, children2.size());
         
         assertEquals(Integer.valueOf(10), children2.get(0).getId());
         assertEquals(Integer.valueOf(11), children2.get(1).getId());
         
         etyp1.removeChild(tmpl1);
         etyp1.removeChild(tmpl2);
         
         children2 = etyp1.getChildren();
         assertEquals(0, children2.size());

         tmpl1.setParent(etyp1);
         tmpl2.setParent(etyp1);

         children2 = etyp1.getChildren();
         assertEquals(2, children2.size());
         
         
         
      } catch (Exception e) {
         fail(e.getMessage());
      }
   }

}
