package dk.schioler.event.base.dao.criteria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseEventCriteria extends EntityBaseCriteria {

   private List<String> types = new ArrayList<String>();

   private String name;

   private List<Integer> eventIds = new ArrayList<Integer>();

   private List<Integer> eventCategoryIds = new ArrayList<Integer>();

   public BaseEventCriteria() {
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Integer> getEventIds() {
      return eventIds;
   }

   public void addEventId(Integer eventId) {
      this.eventIds.add(eventId);
   }

   public List<Integer> getEventCategoryIds() {
      return eventCategoryIds;
   }

   public void addEventCategoryId(Integer eventCategory) {
      this.eventCategoryIds.add(eventCategory);
   }

   public List<String> getEventTypes() {
      return this.types;
   }

   public void addEventType(String eventId) {
      this.types.add(eventId);
   }
   
}
