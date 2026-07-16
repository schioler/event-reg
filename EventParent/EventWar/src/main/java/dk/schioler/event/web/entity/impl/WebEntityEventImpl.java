package dk.schioler.event.web.entity.impl;

import java.util.Map;

import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.common.WebEntityUtil;
import dk.schioler.event.web.entity.WebEntityEvent;

public class WebEntityEventImpl extends WebEntityNamedImpl implements WebEntityEvent {
   private String eventTemplateId;

   private String unit;
   private String dose;

   private String eventTs;
   private String note;

   public WebEntityEventImpl(Event event) {
      super(event);
      Integer parentId = event.getParentId();
      if (parentId != null) {
         eventTemplateId = parentId.toString();
      }
      this.dose = event.getDose();
      this.unit = event.getUnit().toString();
      this.note = event.getNote();
      this.eventTs = WebEntityUtil.getDateTimeFrom(event.getEventTS());
   }

   public WebEntityEventImpl() {
      super();
   }

//
//   public WebEntityEventImpl(Map<String, String> params) {
//      super(params);
//      grabRequestValues(params);
//   }
//

   public void grabRequestValues(Map<String, String> params) {
      super.grabRequestValues(params);
      String eventDose = params.get(REQ_DOSE);
      String eventUnit = params.get(REQ_UNIT);
      String eventTs = params.get(REQ_EVENT_TS);
      String eventNote = params.get(REQ_EVENT_NOTE);

      this.setDose(eventDose);
      this.setUnit(eventUnit);

      this.setEventTs(eventTs);
      this.setNote(eventNote);
   }

   public Event getThisAsBaseEntity(Event event) {
//      Event event = new Event();

      Event e = (Event) super.getAsEntity(event);

      if (e != null) {

         UNIT unit2 = UNIT.getUnitFromString(this.getUnit());
         if (unit2 != null) {
            e.setUnit(unit2);
         }
         e.setDose(this.getDose());

         String etemplId = this.getEventTemplateId();

         Integer parentId = null;
         try {
            parentId = Integer.parseInt(etemplId);
            e.setParentId(parentId);
         } catch (NumberFormatException ne) {
            logger.error("UnSuccessful attempt to parse int. Value=" + etemplId);
            e.setParentId(null);
         }

         e.setNote(this.getNote());
         e.setEventTS(WebEntityUtil.getLocalDateTimeFrom(this.eventTs));
      }
      return e;
   }

   public String getEventTemplateId() {
      return eventTemplateId;
   }

   public void setEventTemplateId(String eventTemplateId) {
      this.eventTemplateId = eventTemplateId;
   }

   public String getUnit() {
      return unit;
   }

   public void setUnit(String unit) {
      this.unit = unit;
   }

   public String getDose() {
      return dose;
   }

   public void setDose(String dose) {
      this.dose = dose;
   }

   public String getEventTs() {
      return eventTs;
   }

   public void setEventTs(String eventTs) {
      this.eventTs = eventTs;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("WebEntityEventImpl ");
      builder.append(super.toString());
      builder.append(", eventTemplateId=");
      builder.append(eventTemplateId);
      builder.append("\b, unit=");
      builder.append(unit);
      builder.append(", dose=");
      builder.append(dose);
      builder.append("\b, eventTs=");
      builder.append(eventTs);
      builder.append("\b, note=");
      builder.append(note);
      builder.append("]");
      return builder.toString();
   }

   
}
