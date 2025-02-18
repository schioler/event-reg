package dk.schioler.event.base.entity;

import java.util.Objects;

//@Component
public abstract class AbstractEntityName extends AbstractEntityId {

   private String name;
   
   private String shortName;

   private String description;

   
   public AbstractEntityName() {
      super();
   }

   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String shortName) {
      this.description = shortName;
   }

   

   public String getShortName() {
      return shortName;
   }


   public void setShortName(String shortNname) {
      this.shortName = shortNname;
   }


   

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(description, name, shortName);
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
      AbstractEntityName other = (AbstractEntityName) obj;
      return Objects.equals(description, other.description) && Objects.equals(name, other.name) && Objects.equals(shortName, other.shortName);
   }


   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString()+  " [name=");
      builder.append(name);
      builder.append(", shortName=");
      builder.append(shortName);
      builder.append(", description=");
      builder.append(description);
      builder.append("]");
      return builder.toString();
   }

   

}
