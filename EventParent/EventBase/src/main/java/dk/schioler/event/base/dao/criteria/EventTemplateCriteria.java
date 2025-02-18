package dk.schioler.event.base.dao.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventTemplateCriteria extends AbstractMeasureCriteria {

   private Boolean favourite;

   private List<Integer> eventTypeIds = new ArrayList<Integer>();


   public List<Integer> getEventTypeIds() {
      return eventTypeIds;
   }

   public void addEventTypeId(Integer eventTypeId) {
      this.eventTypeIds.add(eventTypeId);
   }

   public Boolean isFavourite() {
      return favourite;
   }

   public Boolean getFavourite() {
      return favourite;
   }

   public void setFavourite(Boolean favourite) {
      this.favourite = favourite;
   }


   public void setEventTypeIds(List<Integer> eventTypeIds) {
      this.eventTypeIds = eventTypeIds;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(eventTypeIds, favourite);
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
      EventTemplateCriteria other = (EventTemplateCriteria) obj;
      return Objects.equals(eventTypeIds, other.eventTypeIds) && Objects.equals(favourite, other.favourite);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append("EventTemplateCriteria [favourite=");
      builder.append(favourite);
      builder.append(", eventTypeIds=");
      builder.append(eventTypeIds);
      builder.append("]");
      return builder.toString();
   }


}
