package test.dk.schioler.ineritance;

import java.time.LocalDateTime;
import java.util.Objects;

public class EntityName extends EntityId {
   
   private String name;

   
   
   
   public EntityName() {
      super();
      // TODO Auto-generated constructor stub
   }

   public EntityName(Integer id, LocalDateTime created) {
      super(id, created);
   }

   public EntityName(String name) {
      super();
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(name);
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
      EntityName other = (EntityName) obj;
      return Objects.equals(name, other.name);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EntityName [name=");
      builder.append(name);
      builder.append("]");
      return builder.toString();
   }
   
   
}
