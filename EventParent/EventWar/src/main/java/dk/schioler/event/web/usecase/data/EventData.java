package dk.schioler.event.web.usecase.data;

import java.util.List;

import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;

public interface EventData extends UseCaseData {

//   public List<WebEntityEventType> getEventTypes();
//
//   public void setWebEntityEventTypes(List<WebEntityEventType> eventTypes);
   
   
   public void setEventTypes(List<EventType> eventTypes);
   
   public List<EventType> getEventTypes();
   
   public void addEventType(EventType eventType);
   
   public void clearEventTypes();
   
   public Integer getSelectedEventTypeId();
   
   public void setSelectedEventTypeId(Integer selectedEventTypeId);

//   ***********************************************
   
   public List<EventTemplate> getSelectableEventTemplates();

   public void setSelectableEventTemplates(List<EventTemplate> eventTemplates);
   
   
   
   
   
   
   
    

}
