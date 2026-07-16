package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TreatmentEventCriteria extends BaseEventCriteria {

   private List<Integer> eventTemplateIds = new ArrayList<Integer>();
   
//   private List<Integer> eventTypeIds = new ArrayList<Integer>();

//	private LocalDateTime eventTSIntervalStartDate = DEFAULT_DATE_TIME;
//	
//	private LocalDateTime eventTSIntervalEndDate = DEFAULT_DATE_TIME;

   private LocalDateTime eventTSIntervalStartDate = null;

   private LocalDateTime eventTSIntervalEndDate = null;

   public List<Integer> getEventTemplateIds() {
      return eventTemplateIds;
   }

   public void  setEventTemplateIds(List<Integer> tmplIds) {
      this.eventTemplateIds.addAll(tmplIds);
   }

   
   public void addEventTemplateId(Integer eventTemplateId) {
      this.eventTemplateIds.add(eventTemplateId);
   }
   
   
   public LocalDateTime getEventTSStartDate() {
      return eventTSIntervalStartDate;
   }

   public LocalDateTime getEventTSEndDate() {
      return eventTSIntervalEndDate;
   }

   public void setEventTSInterval(LocalDateTime eventTSIntervalStartDate, LocalDateTime eventTSIntervalEndDate) {
      this.eventTSIntervalStartDate = eventTSIntervalStartDate;
      this.eventTSIntervalEndDate = eventTSIntervalEndDate;
   }



   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(eventTSIntervalEndDate, eventTSIntervalStartDate, eventTemplateIds);
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
      TreatmentEventCriteria other = (TreatmentEventCriteria) obj;
      return Objects.equals(eventTSIntervalEndDate, other.eventTSIntervalEndDate)
            && Objects.equals(eventTSIntervalStartDate, other.eventTSIntervalStartDate)
            && Objects.equals(eventTemplateIds, other.eventTemplateIds);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EventCriteria [");
      builder.append(super.toString());
//      builder.append(", eventTypeIds=");
//      builder.append(eventTypeIds);
      builder.append(", eventTemplateIds=");
      builder.append(eventTemplateIds);
      builder.append(", eventTSStartDate=");
      builder.append(eventTSIntervalStartDate);
      builder.append(", eventTSEndDate=");
      builder.append(eventTSIntervalEndDate);
      builder.append("]");
      return builder.toString();
   }

}
