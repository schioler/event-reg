package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dk.schioler.event.base.entity.UNIT;

public class MedicineEventCriteria extends BaseEventCriteria {

   private List<Integer> eventIds = new ArrayList<Integer>();
   
   private List<String>  eventTypes = new ArrayList<String>();  

   private List<Integer> medicineIds = new ArrayList<Integer>();

   private List<Integer> medicineEventTemplateIds = new ArrayList<Integer>();
   
   private List<UNIT> units = new ArrayList<UNIT>();

//	private LocalDateTime eventTSIntervalStartDate = DEFAULT_DATE_TIME;
//	
//	private LocalDateTime eventTSIntervalEndDate = DEFAULT_DATE_TIME;

   private LocalDateTime eventTSIntervalStartDate = null;

   private LocalDateTime eventTSIntervalEndDate = null;


   public List<Integer> getEventTemplateIds() {
      return medicineEventTemplateIds;
   }

   public void  setEventTemplateIds(List<Integer> tmplIds) {
      this.medicineEventTemplateIds.addAll(tmplIds);
   }

   
   public void addEventTemplateId(Integer eventTemplateId) {
      this.medicineEventTemplateIds.add(eventTemplateId);
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

   public List<Integer> getEventIds() {
      return eventIds;
   }

   public void setEventIds(List<Integer> eventIds) {
      this.eventIds = eventIds;
   }

   public List<String> getEventTypes() {
      return eventTypes;
   }

   public void setEventTypes(List<String> eventTypes) {
      this.eventTypes = eventTypes;
   }

   public List<Integer> getMedicineIds() {
      return medicineIds;
   }

   public void setMedicineIds(List<Integer> medicineIds) {
      this.medicineIds = medicineIds;
   }

   public List<Integer> getMedicineEventTemplateIds() {
      return medicineEventTemplateIds;
   }

   public void setMedicineEventTemplateIds(List<Integer> medicineEventTemplateIds) {
      this.medicineEventTemplateIds = medicineEventTemplateIds;
   }

   public LocalDateTime getEventTSIntervalStartDate() {
      return eventTSIntervalStartDate;
   }

   public void setEventTSIntervalStartDate(LocalDateTime eventTSIntervalStartDate) {
      this.eventTSIntervalStartDate = eventTSIntervalStartDate;
   }

   public LocalDateTime getEventTSIntervalEndDate() {
      return eventTSIntervalEndDate;
   }

   public void setEventTSIntervalEndDate(LocalDateTime eventTSIntervalEndDate) {
      this.eventTSIntervalEndDate = eventTSIntervalEndDate;
   }

   public void addUnit(UNIT unit) {
      this.units.add(unit);
   }
   
   public List<UNIT> getUnits(){
      return units;
   }

}
