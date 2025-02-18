package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.Objects;

public class StateRegistrationCriteria extends AbstractIdCriteria {

   private LocalDateTime registrationTSStart;
   private LocalDateTime registrationTSEnd;

   public LocalDateTime getRegistrationTSStart() {
      return registrationTSStart;
   }

   public void setRegistrationTSStart(LocalDateTime registrationTSStart) {
      this.registrationTSStart = registrationTSStart;
   }

   public LocalDateTime getRegistrationTSEnd() {
      return registrationTSEnd;
   }

   public void setRegistrationTSEnd(LocalDateTime registrationTSEnd) {
      this.registrationTSEnd = registrationTSEnd;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(registrationTSEnd, registrationTSStart);
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
      StateRegistrationCriteria other = (StateRegistrationCriteria) obj;
      return Objects.equals(registrationTSEnd, other.registrationTSEnd) && Objects.equals(registrationTSStart, other.registrationTSStart);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder(super.toString());
      builder.append("StateRegistrationCriteria [registrationTSStart=");
      builder.append(registrationTSStart);
      builder.append(", registrationTSEnd=");
      builder.append(registrationTSEnd);
      builder.append("]");
      return builder.toString();
   }

   
}
