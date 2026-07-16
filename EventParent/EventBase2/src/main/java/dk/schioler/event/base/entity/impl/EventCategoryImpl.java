package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.EventCategory;

public class EventCategoryImpl extends EntityBaseImpl implements EventCategory {

   private String name;
   private String description;

   public EventCategoryImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
   }

   @Override
   public String getName() {

      return name;
   }

   @Override
   public void setName(String name) {
      this.name = name;

   }

   @Override
   public String getDescription() {
      return description;
   }

   @Override
   public void setDescription(String description) {
      this.description = description;

   }

}
