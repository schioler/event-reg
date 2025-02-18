package dk.schioler.event.base.entity;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UNITTest {
   Logger logger = LoggerFactory.getLogger(getClass());

   @Test
   public void testGetUnit() {
      UNIT unit = UNIT.getUnit("KG");
      assertEquals(UNIT.KILOGRAMME, unit);
      
      unit = UNIT.MILLIGRAM;
      assertEquals(UNIT.MILLIGRAM, unit);
      logger.debug("unit.toString="+unit.toString());
   }

   @Test
   public void testUnitAsAbbreviatedString() {
      String abbr = UNIT.unitAsAbbreviatedString(UNIT.CENTILITER);
      assertEquals(UNIT.CENTILITER.shrt, abbr);
   }

   @Test
   public void testUnitAsString() {
      String unitAsString = UNIT.unitAsString(UNIT.POUND);
      assertEquals(unitAsString, UNIT.POUND.toString());
   }

}

