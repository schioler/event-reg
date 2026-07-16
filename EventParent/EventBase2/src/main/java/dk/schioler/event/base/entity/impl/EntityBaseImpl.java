package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.entity.BaseEventHelper;
import dk.schioler.event.base.entity.EntityBase;

public class EntityBaseImpl implements EntityBase {

   public static final Integer DEFAULT_ID = Integer.valueOf(-1);

   protected Logger logger = LoggerFactory.getLogger(getClass());

   private Integer id;

   private Integer ownerId;

   private LocalDateTime createdTs;

   public EntityBaseImpl(Integer id, Integer loginId, LocalDateTime created) {
      super();
      this.id = id;
      this.ownerId = loginId;
      this.createdTs = created;
   }

   @Override
   public LocalDateTime getCreatedTS() {
      return createdTs;
   }

   @Override
   public void setCreatedTS(LocalDateTime created) {
      this.createdTs = created;
   }

   @Override
   public Integer getId() {
      return id;
   }

   @Override
   public void setId(Integer id) {
      this.id = id;
   }

   @Override
   public Integer getOwnerId() {
      return ownerId;
   }

   @Override
   public void setOwnerId(Integer ownerId) {
      this.ownerId = ownerId;
   }

   @Override
   public String getCreatedTime() {
      BaseEventHelper bh = new BaseEventHelperImpl();
      return bh.formatTime(getCreatedTS());
   }

   @Override
   public String getCreatedDate() {
      BaseEventHelper bh = new BaseEventHelperImpl();
      return bh.formatDate(getCreatedTS());
      
   }

   @Override
   public int hashCode() {
      return Objects.hash(createdTs, id, ownerId);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      EntityBaseImpl other = (EntityBaseImpl) obj;
      return Objects.equals(createdTs, other.createdTs) && Objects.equals(id, other.id) && Objects.equals(ownerId, other.ownerId);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EntityBaseImpl [id=");
      builder.append(id);
      builder.append(", ownerId=");
      builder.append(ownerId);
//      builder.append(", createdTs=");
//      builder.append(createdTs.toString());
      builder.append("]");
      return builder.toString();
   }

   
}
