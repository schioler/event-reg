package dk.schioler.event.base.entity.impl;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

import dk.schioler.event.base.entity.BaseEventHelper;

public class DateFormatterTest {

   @Test
   public void testFormatTime() {
      BaseEventHelper beh = new BaseEventHelperImpl();

      LocalDateTime ldt = LocalDateTime.of(2026, 07, 07, 23, 30, 30);
      String asString = "2026-07-07 23:30:30";
      System.out.println(asString);

      String time = ldt.format(beh.getTimeFormatter());
      System.out.println("time:" + time);
      String date = ldt.format(beh.getDateFormatter());
      System.out.println("Date=" + date);
      String full = ldt.format(beh.getFullFormatter());
      System.out.println("full=" + full);

   }

}
