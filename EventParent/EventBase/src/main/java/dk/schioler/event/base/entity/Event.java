package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Event extends AbstractEntityParentChild {

   private Integer eventTemplateId;
   
   private String note;

   private LocalDateTime eventTS;

   private String dose;

   private UNIT unit;

   public Event() {
      super();
   }

   public Event(String note, LocalDateTime eventTS, String dose, UNIT unit) {
      super();
      this.note = note;
      this.eventTS = eventTS;
      this.dose = dose;
      this.unit = unit;
   }

   
   
   public Integer getEventTemplateId() {
      return eventTemplateId;
   }

   public void setEventTemplateId(Integer eventTemplateId) {
      this.eventTemplateId = eventTemplateId;
   }

   @Override
   public List<AbstractEntityParentChild> getChildren() {
      throw new EventEntityException("Events has no children");
   }

   @Override
   public void removeChild(AbstractEntityParentChild child) {
      throw new EventEntityException("Event has no children");
   }

   @Override
   public void addChild(AbstractEntityParentChild child) {
      throw new EventEntityException("Event has no children");

   }

   @Override
   public void setParent(AbstractEntityParentChild parent) {
      if (parent instanceof EventType) {
         super.setParent(parent);
      } else if (parent == null) {
         super.setParent(null);
      } else {
         throw new EventEntityException("setParent: recieved an in-compatible parent object: " + parent);
      }
   }

   
   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   public LocalDateTime getEventTS() {
      return eventTS;
   }

   public void setEventTS(LocalDateTime eventTS) {
      this.eventTS = eventTS;
   }

   public String getEventTSDate() {
      if (this.eventTS != null){
         return getDateFormatter().format(getEventTS());
      } else {
         return "eventTS has not been set";         
      }
   }

   public String getEventTSTime() {
      if (this.eventTS != null){
         return  getTimeFormatter().format(getEventTS());         
      } else {
         return "eventTS has not been set";         
      }

   }


   
   public String getDose() {
      return dose;
   }

   public void setDose(String dose) {
      this.dose = dose;
   }

   public UNIT getUnit() {
      return unit;
   }

   public void setUnit(UNIT unit) {
      this.unit = unit;
   }

   /**
    * Event has no children...
    */
   @Override
   public String getChildString() {
      throw new EventEntityException("Event can have no children...");
//		return "Event has no children...";
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
//      builder.append("Event [ ");
      builder.append("\n eventTemplateId=").append(eventTemplateId);
      builder.append("\n note=").append(note);
      builder.append(", eventTS=").append(eventTS);
      builder.append(", dose=").append(dose);
      
      builder.append(", unit=").append(unit);
//      builder.append("]");
      return builder.toString();
   }



   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(dose, eventTS, eventTemplateId, note, unit);
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
      Event other = (Event) obj;
      return Objects.equals(dose, other.dose) && Objects.equals(eventTS, other.eventTS) && Objects.equals(eventTemplateId, other.eventTemplateId)
            && Objects.equals(note, other.note) && unit == other.unit;
   }

   @Override
   public AbstractEntityParentChild instantiateParent() {
      return new EventType();
   }

}
