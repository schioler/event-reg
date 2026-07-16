package dk.schioler.event.base.entity;

import org.apache.commons.lang3.StringUtils;

public enum MEDICINE_FORM {
   PILL(), FLUENT(), GEL(),IV(), STEAM(), SUPPOSITORY();

   private MEDICINE_FORM() {
    
   };

   public static MEDICINE_FORM getEventTypeFromString(String u) {
      if (StringUtils.isNotBlank(u)) {
         if (PILL.toString().equalsIgnoreCase(u)) {
            return PILL;
         } else if (FLUENT.toString().equalsIgnoreCase(u) ) {
            return FLUENT;
         } else if (GEL.toString().equalsIgnoreCase(u) ) {
            return GEL;
         } else {

            throw new MedicineFormException("No Medicineform matched parameter: " + u);
         }
      } else {
         throw new MedicineFormException("Received null argument");
//         return null;
      }
   }

  

  }
