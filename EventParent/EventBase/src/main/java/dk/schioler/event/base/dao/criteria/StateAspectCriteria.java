package dk.schioler.event.base.dao.criteria;

public class StateAspectCriteria extends AbstractNameCriteria {

   @Override
   public int hashCode() {
      return super.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      return true;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder(super.toString());
      builder.append("StateAspectCriteria []");
      return builder.toString();
   }  
}
