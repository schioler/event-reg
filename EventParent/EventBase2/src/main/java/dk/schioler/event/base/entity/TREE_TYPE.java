package dk.schioler.event.base.entity;

import org.apache.commons.lang3.StringUtils;

public enum TREE_TYPE {
   PARENT_ID(), PARENT_NAME();

   private TREE_TYPE() {
    
   };

   public static TREE_TYPE getEventTypeFromString(String u) {
      if (StringUtils.isNotBlank(u)) {
         if (PARENT_ID.toString().equalsIgnoreCase(u)) {
            return PARENT_ID;
         } else if (PARENT_NAME.toString().equalsIgnoreCase(u) ) {
            return PARENT_NAME;
         } else {

            throw new BaseEventException("No TREE_TYPE matched parameter: " + u);
         }
      } else {
         throw new BaseEventException("Received null argument");
//         return null;
      }
   }

  

  }
