
package dk.schioler.event.web.entity.impl;

import dk.schioler.event.web.entity.WebEntityLogin;

public class WebEntityLoginImpl extends WebEntityIdImpl implements WebEntityLogin {

   private String role;
   private String endTs;
  
   
   @Override
   public String getRole() {
      return role;
   }
   @Override
   public void setRole(String role) {
      this.role = role;
      
   }
   @Override
   public void setEndTs(String endTs) {
      this.endTs = endTs;
      
   }
   @Override
   public String getEndTs() {
      return this.endTs;
   }
   
  
   
  
  
}
