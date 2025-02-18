package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class StateRegistration extends AbstractEntityId {

   private LocalDateTime registrationTS;

   public StateRegistration() {
      super();
   }

   public LocalDateTime getRegistrationTS() {
      return registrationTS;
   }

   public void setRegistrationTS(LocalDateTime stateTS) {
      this.registrationTS = stateTS;
   }



   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(registrationTS);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      StateRegistration other = (StateRegistration) obj;
      return Objects.equals(registrationTS, other.registrationTS);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("StateRegistration ["+ super.toString());

      builder.append(", registrationTime=").append(registrationTS);
      builder.append("]");
      return builder.toString();
   }


   

}
