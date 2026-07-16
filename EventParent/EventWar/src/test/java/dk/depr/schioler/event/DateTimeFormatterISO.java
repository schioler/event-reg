package dk.depr.schioler.event;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class DateTimeFormatterISO {

   @Test
   public void test() {
      String dateTimeString = "2025-09-01T01:02";
      LocalDateTime startDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);
      System.out.println("date time. year=" + startDateTime.getYear());
      
   }

}
