package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEntityId {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   private Integer id;

   private Integer loginId;
   
   private LocalDateTime created;
  
   public AbstractEntityId(Integer id, Integer loginId, LocalDateTime created) {
      super();
      this.id = id;
      this.loginId = loginId;
      this.created = created;
   }

   public AbstractEntityId() {
      super();
   }

   public LocalDateTime getCreated() {
      return created;
   }

   public void setCreated(LocalDateTime created) {
      this.created = created;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getLoginId() {
      return loginId;
   }

   public void setLoginId(Integer loginId) {
      this.loginId = loginId;
   }

   
   @Override
   public int hashCode() {
      return Objects.hash(created, id, loginId);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      AbstractEntityId other = (AbstractEntityId) obj;
      return Objects.equals(created, other.created) && Objects.equals(id, other.id) && Objects.equals(loginId, other.loginId);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("AbstractIdEntity [id=");
      builder.append(id);
      builder.append(", loginId=");
      builder.append(loginId);
      builder.append(", created=").append(created);
      builder.append("]");
      return builder.toString();
   }



}
