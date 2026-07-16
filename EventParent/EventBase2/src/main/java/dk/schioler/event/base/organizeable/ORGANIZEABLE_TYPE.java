package dk.schioler.event.base.organizeable;

import org.apache.commons.lang3.StringUtils;

public enum ORGANIZEABLE_TYPE {
   PARENT_ID(), PARENT_NAME();

   private ORGANIZEABLE_TYPE() {
    
   };

   public static ORGANIZEABLE_TYPE getEventTypeFromString(String u) {
      if (StringUtils.isNotBlank(u)) {
         if (PARENT_ID.toString().equalsIgnoreCase(u)) {
            return PARENT_ID;
         } else if (PARENT_NAME.toString().equalsIgnoreCase(u) ) {
            return PARENT_NAME;
         } else {

            throw new OrganizeableException("No ORGANISATION_TYPE matched parameter: " + u);
         }
      } else {
         throw new OrganizeableException("Received null argument");
//         return null;
      }
   }

  

  }
