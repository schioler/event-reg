package dk.schioler.event.web2.common;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;



public class WebEntityUtil {
   
   private static final String TIME_PATTERN = "HH:mm:ss";
   private static final String DATE_PATTERN = "yyyy-MM-dd";
   private static final String DATE_TIME_PATTERN = DATE_PATTERN + "'T'" + TIME_PATTERN;

   private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
   private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
   private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);

   public static List<Integer> toIntList(List<String> list) {
      List<Integer> retVal = new ArrayList<Integer>();
      for (String s : list) {
         Integer i = Integer.parseInt(s);
         retVal.add(i);

      }

      return retVal;
   }

//   public static DateTimeFormatter getDtFormatter() {
//      return DATE_TIME_FORMATTER;
//   }

   public static String getDateFrom(LocalDateTime ldt) {
      return DATE_FORMATTER.format(ldt);
   }

   public static String getTimeFrom(LocalDateTime ldt) {
      return TIME_FORMATTER.format(ldt);
   }

   public static String getDateTimeFrom(LocalDateTime ldt) {
      return DATE_TIME_FORMATTER.format(ldt);
   }

   public static LocalDateTime getLocalDateTimeFrom(String dateTimeString) {
      return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMATTER);
   }

}
