package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class StateAspect extends AbstractEntityName {
   
   private LocalDateTime aspectTS;

   public StateAspect() {
      super();
   }

   
  
   public LocalDateTime getAspectTS() {
      return aspectTS;
   }



   public void setAspectTS(LocalDateTime aspectTS) {
      this.aspectTS = aspectTS;
   }



   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(aspectTS);
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
      StateAspect other = (StateAspect) obj;
      return Objects.equals(aspectTS, other.aspectTS);
   }



   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("StateAspect [" + super.toString());
      builder.append(", aspectTs=").append(aspectTS);
      builder.append("]");
      return builder.toString();
   }

}
