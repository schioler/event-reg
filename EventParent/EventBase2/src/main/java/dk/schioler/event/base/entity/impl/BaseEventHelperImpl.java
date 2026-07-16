package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dk.schioler.event.base.entity.BaseEventHelper;

public class BaseEventHelperImpl implements BaseEventHelper {

   // YEAR-MONTH-DAY, ex 2018-12-29
   protected final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;

   protected final DateTimeFormatter tf = DateTimeFormatter.ISO_LOCAL_TIME;

   protected final DateTimeFormatter fullFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

   @Override
   public DateTimeFormatter getDateFormatter() {
      return df;
   }

   @Override
   public DateTimeFormatter getTimeFormatter() {
      return tf;
   }

   @Override
   public DateTimeFormatter getFullFormatter() {

      return fullFormatter;
   }
//   *********************************''''

   @Override
   public String formatTime(LocalDateTime time) {

      return time.format(tf);

   }

   @Override
   public String formatDate(LocalDateTime date) {
      return date.format(df);
   }

   @Override
   public String formatFull(LocalDateTime dateTime) {
      return dateTime.format(fullFormatter);
   }

//   ***********************************
   @Override
   public LocalDateTime parseDate(String dateTime) {
      return LocalDateTime.parse(dateTime, getDateFormatter());
   }

   @Override
   public LocalDateTime parseTime(String dateTime) {
      return LocalDateTime.parse(dateTime, getTimeFormatter());

   }

   @Override
   public LocalDateTime parseFull(String dateTime) {
      return LocalDateTime.parse(dateTime, getTimeFormatter());

   }

}
