package dk.schioler.event.web.entity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.web.entity.WebEntityEventTemplate;

public class WebEntityEventTemplateImpl extends WebEntityNamedImpl implements WebEntityEventTemplate {

   private String eventTypeId;

   private List<String> eventIds = new ArrayList<String>();

   private String unit;
   private String dose;
   private String isFavorite;
   private String sortOrder;
   
   
   
   public WebEntityEventTemplateImpl() {
      super();
      }

   public WebEntityEventTemplateImpl(EventTemplate template) {
      super(template);
      this.unit = template.getUnit().toString();
      this.dose = template.getDose();
      this.isFavorite = Boolean.toString(template.isFavorite());
      this.sortOrder = template.getSortOrder().toString();
   }

   @Override
   public void addEventId(String event) {
      eventIds.add(event);
   }

   @Override
   public List<String> getEventIds() {

      return this.eventIds;
   }

   @Override
   public void grabRequestValues(Map<String, String> params) {
      super.grabRequestValues(params);
      this.eventTypeId = params.get(REQ_EVENT_TYPE_ID);
      this.unit = params.get(REQ_UNIT);
      this.dose = params.get(REQ_DOSE);
      this.isFavorite = params.get(REQ_IS_FAVORITE);
      this.sortOrder = params.get(REQ_SORT_ORDER);

   }

   @Override
   public String getEventTypeId() {
      return this.eventTypeId;

   }

   @Override
   public void setEventTypeId(String value) {
      this.eventTypeId = value;

   }

   @Override
   public String getUnit() {

      return this.unit;
   }

   @Override
   public void setUnit(String value) {
      this.unit = value;

   }

   @Override
   public String getDose() {

      return this.dose;
   }

   @Override
   public void setDose(String value) {
      this.dose = value;

   }

   @Override
   public String isFavorite() {

      return this.isFavorite;
   }

   @Override
   public void setFavorite(String value) {
      this.isFavorite = value;

   }

   @Override
   public String getSortOrder() {

      return sortOrder;
   }

   @Override
   public void setSortOrder(String value) {
      this.sortOrder = value;

   }

//
//   public WebEntityEventTemplate(EventTemplate template) {
//      super(template);
//      if (template != null) {
//
//         if (template.getParentId() != null) {
//            this.eventTypeId = template.getParentId().toString();
//         }
//         if (template.getUnit() != null) {
//            this.unit = template.getUnit().toString();
//         }
//         this.dose = template.getDose();
//
//         if (template.isFavorite() == false) {
//            this.isFavorite = Boolean.valueOf(false).toString();
//         } else {
//            this.isFavorite = Boolean.valueOf(true).toString();
////         } else {
////            this.isFavorite = Boolean.FALSE.toString();
//         }
//
//         if (template.getSortOrder() != null) {
//            this.sortOrder = template.getSortOrder().toString();
//         } else {
//            this.sortOrder = "0";
//         }
//         
//         if (!template.getChildren().isEmpty()) {
//            for (AbstractEntityParentChild child : template.getChildren()) {
//               Integer childId = child.getId();
//               this.eventIds.add(childId.toString());
//            }
//         }
//      }
//   }
//
//   public EventTemplate getAsEntity(EventTemplate template) {
//      EventTemplate tmpl = (EventTemplate) super.getAsEntity(template);
//      
//      tmpl.setDose(this.dose);
//      
//      if (StringUtils.isNotBlank(this.unit)) {
//         tmpl.setUnit(
//               UNIT.getUnitFromString(this.unit));
//      }
//      
//      if(StringUtils.isNotBlank(isFavorite)) {
//         tmpl.setFavorite( BooleanUtils.toBoolean(isFavorite));
//      }
//      
//      if ( StringUtils.isNotBlank(sortOrder)) {
//         tmpl.setSortOrder(Integer.parseInt(sortOrder));
//      } else {
//         tmpl.setSortOrder(0);         
//      }
//      
//      
//      return tmpl;
//   }
//
//   public void addEventId(String eventId) {
//      this.eventIds.add(eventId);
//   }
//
//   
//   
//   public List<String> getEventIds() {
//      return eventIds;
//   }
//
//   public void setEvents(List<String> eventIds) {
//      this.eventIds = eventIds;
//   }
//
//   public String getEventTypeId() {
//      return eventTypeId;
//   }
//
//   public void setEventTypeId(String eventTypeId) {
//      this.eventTypeId = eventTypeId;
//   }
//
//   public String getDose() {
//      return dose;
//   }
//
//   public void setDose(String dose) {
//      this.dose = dose;
//   }
//
//   public String getUnit() {
//      return unit;
//   }
//
//   public void setUnit(String unit) {
//      this.unit = unit;
//   }
//
//   public String getIsFavorite() {
//      return isFavorite;
//   }
//
//   public void setIsFavorite(String isFavorite) {
//      this.isFavorite = isFavorite;
//   }
//
//   public String getSortOrder() {
//      return sortOrder;
//   }
//
//   public void setSortOrder(String sortOrder) {
//      this.sortOrder = sortOrder;
//   }
//
//   @Override
//   public int hashCode() {
//      final int prime = 31;
//      int result = super.hashCode();
//      result = prime * result + Objects.hash(dose, eventTypeId, isFavorite, sortOrder, unit);
//      return result;
//   }
//
//   @Override
//   public boolean equals(Object obj) {
//      if (this == obj)
//         return true;
//      if (!super.equals(obj))
//         return false;
//      if (getClass() != obj.getClass())
//         return false;
//      WebEntityEventTemplate other = (WebEntityEventTemplate) obj;
//      return Objects.equals(dose, other.dose) && Objects.equals(eventTypeId, other.eventTypeId) && Objects.equals(isFavorite, other.isFavorite)
//            && Objects.equals(sortOrder, other.sortOrder) && Objects.equals(unit, other.unit);
//   }
//
//   @Override
//   public String toString() {
//      StringBuilder builder = new StringBuilder();
//      builder.append(super.toString());
//      builder.append(", eventTypeId=");
//      builder.append(eventTypeId);
//      builder.append(", unit=");
//      builder.append(unit);
//      builder.append(", dose=");
//      builder.append(dose);
//      builder.append(", isFavorite=");
//      builder.append(isFavorite);
//      builder.append(", sortOrder=");
//      builder.append(sortOrder);
//      builder.append("]");
//      return builder.toString();
//   }

}
