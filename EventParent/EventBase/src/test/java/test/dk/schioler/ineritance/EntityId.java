package test.dk.schioler.ineritance;

import java.time.LocalDateTime;
import java.util.Objects;

public class EntityId {
   private Integer id;
   private LocalDateTime created;

   public EntityId() {
      super();
   }

   public EntityId(Integer id, LocalDateTime created) {
      super();
      this.id = id;
      this.created = created;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public LocalDateTime getCreated() {
      return created;
   }

   public void setCreated(LocalDateTime created) {
      this.created = created;
   }

   @Override
   public int hashCode() {
      return Objects.hash(created, id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      EntityId other = (EntityId) obj;
      return Objects.equals(created, other.created) && Objects.equals(id, other.id);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EntityId [id=");
      builder.append(id);
      builder.append(", created=");
      builder.append(created);
      builder.append("]");
      return builder.toString();
   }

}
