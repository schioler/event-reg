package dk.schioler.event.base.entity;

import org.apache.commons.lang3.StringUtils;

public enum UNIT {
   KILOGRAMME("KG"), GRAM("G"), MILLIGRAM("MG"), MICROGRAM("MCG"), POUND("LB"), OUNCES("OZ"), LITER("L"), DECILITER("DL"), CENTILITER("CL"), MILLILITER("ML"),
   YEARS("YRS"), MONTHS("MTHS"), DAYS("DAYS"), HOURS("HRS"), MINUTES("MIN"), SECONDS("SEC");

   public final String shrt;

    
   private UNIT(String abbreviation) {
      this.shrt = abbreviation;

   };
   
   public static UNIT getUnit(String u) {
      if (StringUtils.isNotBlank(u)) {
         if (KILOGRAMME.toString().equalsIgnoreCase(u) || 
               KILOGRAMME.shrt.equalsIgnoreCase(u)
               ) {
            return KILOGRAMME;
         } else if (GRAM.toString().equalsIgnoreCase(u) || 
               GRAM.shrt.equalsIgnoreCase(u)
               ) {
            return GRAM;
         } else if (MILLIGRAM.toString().equalsIgnoreCase(u) || 
               MILLIGRAM.shrt.equalsIgnoreCase(u)
               ) {
            return MILLIGRAM;
         } else if (MICROGRAM.toString().equalsIgnoreCase(u) || 
               MICROGRAM.shrt.equalsIgnoreCase(u)
               ) {
            return MICROGRAM;
         } else if (POUND.toString().equalsIgnoreCase(u) || 
               POUND.shrt.equalsIgnoreCase(u)
               ) {
            return POUND;
         } else if (OUNCES.toString().equalsIgnoreCase(u) || 
               OUNCES.shrt.equalsIgnoreCase(u)
               ) {
            return OUNCES;
         } else if (LITER.toString().equalsIgnoreCase(u) || 
               LITER.shrt.equalsIgnoreCase(u)
               ) {
            return LITER;
         } else if (DECILITER.toString().equalsIgnoreCase(u) || 
               DECILITER.shrt.equalsIgnoreCase(u)
               ) {
            return DECILITER;
         } else if (CENTILITER.toString().equalsIgnoreCase(u) || 
               CENTILITER.shrt.equalsIgnoreCase(u)
               ) {
            return CENTILITER;
         } else if (MILLILITER.toString().equalsIgnoreCase(u) || 
              MILLILITER.shrt.equalsIgnoreCase(u)
               ) {
            return MILLILITER;
         } else if (YEARS.toString().equalsIgnoreCase(u) || 
               YEARS.shrt.equalsIgnoreCase(u)
               ) {
            return YEARS;
         } else if (MONTHS.toString().equalsIgnoreCase(u) || 
               MONTHS.shrt.equalsIgnoreCase(u)
               ) {
            return MONTHS;
         } else if (DAYS.toString().equalsIgnoreCase(u) || 
               DAYS.shrt.equalsIgnoreCase(u)
               ) {
            return DAYS;
         } else if (HOURS.toString().equalsIgnoreCase(u) || 
               HOURS.shrt.equalsIgnoreCase(u)
               ) {
            return HOURS;
         } else if (MINUTES.toString().equalsIgnoreCase(u) || 
               MINUTES.shrt.equalsIgnoreCase(u)
               ) {
            return MINUTES;
         } else if (SECONDS.toString().equalsIgnoreCase(u) || 
               SECONDS.shrt.equalsIgnoreCase(u)
               ) {
            return SECONDS;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
   
   public static String unitAsAbbreviatedString(UNIT u) {
      if (KILOGRAMME.equals(u)) {
         return KILOGRAMME.shrt;
      } else if (GRAM.equals(u)) {
         return GRAM.shrt;
      } else if (MILLIGRAM.equals(u)) {
         return MILLIGRAM.shrt;
      } else if (MICROGRAM.equals(u)) {
         return MICROGRAM.shrt;
      } else if (POUND.equals(u)) {
         return POUND.shrt;
      } else if (OUNCES.equals(u)) {
         return OUNCES.shrt;
      } else if (LITER.equals(u)) {
         return LITER.shrt;
      } else if (DECILITER.equals(u)) {
         return DECILITER.shrt;
      } else if (CENTILITER.equals(u)) {
         return CENTILITER.shrt;
      } else if (MILLILITER.equals(u)) {
         return MILLILITER.shrt;
      } else if (YEARS.equals(u)) {
         return YEARS.shrt;
      } else if (MONTHS.equals(u)) {
         return MONTHS.shrt;
      } else if (DAYS.equals(u)) {
         return DAYS.shrt;
      } else if (HOURS.equals(u)) {
         return HOURS.shrt;
      } else if (MINUTES.equals(u)) {
         return MINUTES.shrt;
      } else if (SECONDS.equals(u)) {
         return SECONDS.shrt;
      } else {
         return null;
      }      
   }
   
   public static String unitAsString(UNIT u) {
      if (KILOGRAMME.equals(u)) {
         return KILOGRAMME.toString();
      } else if (GRAM.equals(u)) {
         return GRAM.toString();
      } else if (MILLIGRAM.equals(u)) {
         return MILLIGRAM.toString();
      } else if (MICROGRAM.equals(u)) {
         return MICROGRAM.toString();
      } else if (POUND.equals(u)) {
         return POUND.toString();
      } else if (OUNCES.equals(u)) {
         return OUNCES.toString();
      } else if (LITER.equals(u)) {
         return LITER.toString();
      } else if (DECILITER.equals(u)) {
         return DECILITER.toString();
      } else if (CENTILITER.equals(u)) {
         return CENTILITER.toString();
      } else if (MILLILITER.equals(u)) {
         return MILLILITER.toString();
      } else if (YEARS.equals(u)) {
         return YEARS.toString();
      } else if (MONTHS.equals(u)) {
         return MONTHS.toString();
      } else if (DAYS.equals(u)) {
         return DAYS.toString();
      } else if (HOURS.equals(u)) {
         return HOURS.toString();
      } else if (MINUTES.equals(u)) {
         return MINUTES.toString();
      } else if (SECONDS.equals(u)) {
         return SECONDS.toString();
      } else {
         return null;
      }      
   }
}
