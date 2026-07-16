package dk.schioler.event.base.entity;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventTemplateEqualsTest {
   Logger logger = LoggerFactory.getLogger(getClass());

   @Test
   public void testEntityEquals() {
      Event e1 = null;
      Event e2 = null;
      EventTemplate et1 = null;
      EventTemplate et2 = null;
      EventType etyp1 = null;
      EventType etyp2 = null;

      try {
         etyp1 = new EventType();
         etyp2 = new EventType();
         assertEquals(etyp1, etyp2);

         etyp1.setLoginId(1);
         etyp2.setLoginId(1);
         assertEquals(etyp1, etyp2);

         etyp1.setName("TYPE");
         etyp2.setName("TYPE");
         assertEquals(etyp1, etyp2);

         etyp1.setShortName("SHORT");
         etyp2.setShortName("SHORT");
         assertEquals(etyp1, etyp2);

         etyp1.setDescription("DESC");
         etyp2.setDescription("DESC");
         assertEquals(etyp1, etyp2);

         etyp1.setName("diff name");
         assertNotEquals(etyp1, etyp2);

         etyp1.setName("TYPE");
         assertEquals(etyp1, etyp2);

         et1 = new EventTemplate();
         et2 = new EventTemplate();
         assertEquals(et1, et2);

         etyp1.addChild(et1);
         assertNotEquals(etyp1, etyp2);

         etyp1.removeChild(et1);
         assertEquals(etyp1, etyp2);

         etyp1.addChild(et1);
         etyp2.addChild(et2);

         assertEquals(etyp1, etyp2);

//         et1.setDose(BigDecimal.valueOf(50));
//         et1.setUnit(UNIT.MILLIGRAM);
//
//         et2.setDose(BigDecimal.valueOf(50));
//         et2.setUnit(UNIT.MILLIGRAM);
//
//         assertEquals(et1, et2);
//         assertEquals(etyp1, etyp2);

         et1.setFavorite(false);
         et2.setFavorite(true);
         assertNotEquals(et1, et2);

         et1.setFavorite(true);
         assertEquals(et1, et2);

         et1.setSortOrder(1);
         et2.setSortOrder(2);
         assertNotEquals(et1, et2);

         et1.setSortOrder(2);
         assertEquals(et1, et2);

         e1 = new Event();
         e2 = new Event();

         assertEquals(e1, e2);

         LocalDateTime ldt1 = LocalDateTime.of(2025, 02, 01, 21, 9, 12);
         e1.setEventTS(ldt1);
         e2.setEventTS(ldt1);
         
         assertEquals(e1, e2);
         
         e1.setEventTS(null);
         assertNotEquals(e1, e2);
         e1.setEventTS(ldt1);
         assertEquals(e1, e2);
         
         e1.setId(12);
         assertNotEquals(e1, e2);
         e2.setId(12);
         assertEquals(e1, e2);

         assertEquals(et1, et2);
         
         et1.addChild(e1);
         et2.addChild(e2);
         assertEquals(e1, e2);
         assertEquals(et1, et2);
         assertEquals(etyp1, etyp2);
         
        
      } catch (Exception e) {
         fail(e.getMessage());
      }
   }
   
}
