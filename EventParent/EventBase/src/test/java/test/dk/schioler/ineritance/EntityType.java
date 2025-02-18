package test.dk.schioler.ineritance;

import java.time.LocalDateTime;
import java.util.Objects;

public class EntityType extends EntityName {
   
   private LocalDateTime typeTS;

   public EntityType(LocalDateTime typeTS) {
      super();
      this.typeTS = typeTS;
   }

   public EntityType() {
      super();
    }

   public EntityType(Integer id, LocalDateTime created, LocalDateTime typeTS) {
      super(id, created);
      this.typeTS = typeTS;
   }

   public LocalDateTime getTypeTS() {
      return typeTS;
   }

   public void setTypeTS(LocalDateTime typeTS) {
      this.typeTS = typeTS;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(typeTS);
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
      EntityType other = (EntityType) obj;
      return Objects.equals(typeTS, other.typeTS);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EntityType [typeTS=");
      builder.append(typeTS);
      builder.append("]");
      return builder.toString();
   }

   
   
   

}
