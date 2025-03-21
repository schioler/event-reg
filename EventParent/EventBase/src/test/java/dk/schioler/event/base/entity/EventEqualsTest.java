package dk.schioler.event.base.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventEqualsTest {
   Logger logger = LoggerFactory.getLogger(getClass());

   @Test
   public void testEntityEquals() {
      Event e1 = null;
      Event e2 = null;

      try {
         e1 = new Event();
         e2 = new Event();

         assertEquals(e1, e2);
         
         // Abstract entity ID level
         assertEquals(e1.getId(), e2.getId());
         assertEquals(e1.getLoginId(), e2.getLoginId());
         assertNotEquals(e1.getCreated(), e2.getCreated());
         
         e1.setId(12);
         assertNotEquals(e1, e2);
         e2.setId(12);
         assertEquals(e1, e2);
         
         e1.setLoginId(31);
         assertNotEquals(e1, e2);
         e2.setLoginId(31);;
         assertEquals(e1, e2);

         // next level abstract Entity Name
         // name, shortName, description
         
         
         // parent-child will be taken care of in a specific testfile
         
         // Event 
         // eventTs, note, dose, unit 
         LocalDateTime ldt1 = LocalDateTime.of(2025, 02, 01, 21, 9, 12);
         e1.setEventTS(ldt1);
         e2.setEventTS(ldt1);
         
         assertEquals(e1, e2);
         
         e1.setEventTS(null);
         assertNotEquals(e1, e2);
         e1.setEventTS(ldt1);
         assertEquals(e1, e2);
         

         
        
      } catch (Exception e) {
         fail(e.getMessage());
      }
   }
   
}
