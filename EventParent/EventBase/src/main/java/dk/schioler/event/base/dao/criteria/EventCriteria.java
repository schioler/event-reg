package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventCriteria extends AbstractMeasureCriteria {

	private List<Integer> eventTemplateId = new ArrayList<Integer>();
	
	private LocalDateTime eventTSStartDate;
	
	private LocalDateTime eventTSEndDate;

	public List<Integer> getEventTemplateIds() {
		return eventTemplateId;
	}

	public void addEventTemplateId(Integer eventTemplateId) {
		this.eventTemplateId.add(eventTemplateId);
	}

   
   public LocalDateTime getEventTSStartDate() {
      return eventTSStartDate;
   }

   public void setEventTSStartDate(LocalDateTime eventTSStartDate) {
      this.eventTSStartDate = eventTSStartDate;
   }

   public LocalDateTime getEventTSEndDate() {
      return eventTSEndDate;
   }

   public void setEventTSEndDate(LocalDateTime eventTSEndDate) {
      this.eventTSEndDate = eventTSEndDate;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(eventTSEndDate, eventTSStartDate, eventTemplateId);
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
      EventCriteria other = (EventCriteria) obj;
      return Objects.equals(eventTSEndDate, other.eventTSEndDate) && Objects.equals(eventTSStartDate, other.eventTSStartDate)
            && Objects.equals(eventTemplateId, other.eventTemplateId);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append("EventCriteria [eventTemplateId=");
      builder.append(eventTemplateId);
      builder.append(", eventTSStartDate=");
      builder.append(eventTSStartDate);
      builder.append(", eventTSEndDate=");
      builder.append(eventTSEndDate);
      builder.append("]");
      return builder.toString();
   }
   
   
}
