package dk.schioler.event.base.entity;

import org.apache.commons.lang3.StringUtils;

public enum EVENT_TYPE {
   MEDICINE("MED"), TREATMENT("TREAT"), STATUS("STAT");

   public final String shrt;

   private EVENT_TYPE(String abbreviation) {
      this.shrt = abbreviation;
   };

   public static EVENT_TYPE getEventTypeFromString(String u) {
      if (StringUtils.isNotBlank(u)) {
         if (MEDICINE.toString().equalsIgnoreCase(u) || MEDICINE.shrt.equalsIgnoreCase(u)) {
            return MEDICINE;
         } else if (TREATMENT.toString().equalsIgnoreCase(u) || TREATMENT.shrt.equalsIgnoreCase(u)) {
            return TREATMENT;
         } else if (STATUS.toString().equalsIgnoreCase(u) || STATUS.shrt.equalsIgnoreCase(u)) {
            return STATUS;
         } else {
//            return null;
            throw new BaseEventException("No EventType matched parameter");
         }
      } else {
         throw new BaseEventException("Received null argument");
//         return null;
      }
   }

   public static EVENT_TYPE getEventTypeFromAbbrString(String abbr) {
      if (StringUtils.isNotBlank(abbr)) {
         if (MEDICINE.toString().equalsIgnoreCase(abbr) || MEDICINE.shrt.equalsIgnoreCase(abbr)) {
            return MEDICINE;
         } else if (TREATMENT.toString().equalsIgnoreCase(abbr) || TREATMENT.shrt.equalsIgnoreCase(abbr)) {
            return TREATMENT;
         } else if (STATUS.toString().equalsIgnoreCase(abbr) || STATUS.shrt.equalsIgnoreCase(abbr)) {
            return STATUS;
         } else {
            throw new BaseEventException("No EventType matched parameter");
         }
      } else {
//         return null;
         throw new BaseEventException("Received null argument");
      }
   }

   public static String eventTypeAsAbbreviatedString(EVENT_TYPE u) {
      if (u != null) {
         if (MEDICINE.equals(u)) {
            return MEDICINE.shrt;
         } else if (TREATMENT.equals(u)) {
            return TREATMENT.shrt;
         } else if (STATUS.equals(u)) {
            return STATUS.shrt;
         } else {
            throw new BaseEventException("Parameter matched no EVENT_TYPE");
         }
      } else {
         throw new BaseEventException("Received null argument");
      }

   }

   public static String eventTypeAsString(EVENT_TYPE u) {
      if (u != null) {
         if (MEDICINE.equals(u)) {
            return MEDICINE.toString();
         } else if (TREATMENT.equals(u)) {
            return TREATMENT.toString();
         } else if (STATUS.equals(u)) {
            return STATUS.toString();
         } else {
            throw new BaseEventException("Parameter matched no EVENT_TYPE");
         }
      } else {
         throw new BaseEventException("Received null argument");
      }

   }
}
