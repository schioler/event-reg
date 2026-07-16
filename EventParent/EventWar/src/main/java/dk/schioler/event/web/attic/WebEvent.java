package dk.schioler.event.web.attic;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.UNIT;

@Deprecated
public class WebEvent {
   
   private Event e;
   
   public WebEvent(Event e) {
      super();
      this.e = e;
   }

   public Event getEvent() {
      return this.e;
   }
   
   
   public LocalDateTime getEventTS() {
      return e.getEventTS();
   }

   public String getEventTSDate() {
      return e.getEventTSDate();
   }

   public String getEventTSTime() {
      return e.getEventTSTime();
   }

   public String getName() {
      return e.getName();
   }

   public String getDescription() {
      return e.getDescription();
   }

   public String getShortName() {
      return e.getShortName();
   }

   public Integer getParentId() {
      return e.getParentId();
   }

   public LocalDateTime getCreated() {
      return e.getCreated();
   }

   public Integer getId() {
      return e.getId();
   }

   public Integer getLoginId() {
      return e.getLoginId();
   }

   public String getNote() {
      return e.getNote();
   }

   public String getDose() {
      return e.getDose();
   }

   public UNIT getUnit() {
      return e.getUnit();
   }
   

   
}
