package dk.schioler.event.web.usecase.data.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.usecase.data.EventData;

public class EventDataImpl extends UseCaseDataImpl implements EventData {

   @Autowired
   private List<EventType> eventTypes = new ArrayList<EventType>();
   private List<EventTemplate> eventTemplates = new ArrayList<EventTemplate>();

   private Integer selectedEventTypeId;

   @Override
   public void setEventTypes(List<EventType> eventTypes) {
      if (eventTypes != null) {
         this.eventTypes.clear();
         for (EventType eventType : eventTypes) {
            this.eventTypes.add(eventType);
         }
      }
   }

   @Override
   public List<EventType> getEventTypes() {
      List<EventType> et = new ArrayList<EventType>();
      et.addAll(eventTypes);
      return et;
   }

   @Override
   public void clearEventTypes() {
      this.eventTypes.clear();

   }

   @Override
   public void addEventType(EventType eventType) {
      this.eventTypes.add(eventType);
   }

   @Override
   public Integer getSelectedEventTypeId() {
      return this.selectedEventTypeId;
   }

   @Override
   public void setSelectedEventTypeId(Integer selectedEventTypeId) {
      this.selectedEventTypeId = selectedEventTypeId;
   }

//   ***************************************
   @Override
   public void setSelectableEventTemplates(List<EventTemplate> eventTemplates) {
      this.eventTemplates.clear();
      this.eventTemplates.addAll(eventTemplates);

   }

   @Override
   public List<EventTemplate> getSelectableEventTemplates() {
      return this.eventTemplates;
   }

   @Override
   public String toString() {
      final int maxLen = 2;
      StringBuilder builder = new StringBuilder();
      builder.append("EventDataImpl:\b");
      builder.append(super.toString());
      builder.append("\b, eventTypes=");
      builder.append(eventTypes != null ? eventTypes.subList(0, Math.min(eventTypes.size(), maxLen)) : null);
      builder.append("\b, eventTemplates=");
      builder.append(eventTemplates != null ? eventTemplates.subList(0, Math.min(eventTemplates.size(), maxLen)) : null);
      builder.append("\b, selectedEventTypeId=");
      builder.append(selectedEventTypeId);
      builder.append("]");
      return builder.toString();
   }

}
