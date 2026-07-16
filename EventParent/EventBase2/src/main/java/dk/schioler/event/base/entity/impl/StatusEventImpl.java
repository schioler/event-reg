package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.StatusEvent;
import dk.schioler.event.base.entity.TREE_TYPE;

public class StatusEventImpl extends BaseEventImpl implements StatusEvent {

   private int rating;
   private String description;

   public StatusEventImpl(Integer id, Integer loginId, LocalDateTime created, String name, TREE_TYPE treeType) {
      super(id, loginId, created, EVENT_TYPE.STATUS, name, treeType);

   }

   @Override
   public EVENT_TYPE getEventType() {
      return EVENT_TYPE.STATUS;
   }

   @Override
   public int getRating() {

      return rating;
   }

   @Override
   public void setRating(int rating) {
      this.rating = rating;

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
