package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.TREE_TYPE;
import dk.schioler.event.base.entity.TreatmentEvent;
import dk.schioler.event.base.entity.UNIT;

public class TreatmentEventImpl extends BaseEventImpl implements TreatmentEvent {
         
   private Integer treatmentId;
   private String treatmentName;
   private String duration;
   private UNIT unit;
   private int rating;
   private String description;
   

   
   
   protected TreatmentEventImpl(Integer id, Integer loginId, LocalDateTime created, String name, TREE_TYPE treeType) {
      super(id, loginId, created, EVENT_TYPE.TREATMENT, name, treeType);
   }

   @Override
   public EVENT_TYPE getEventType() {

      return EVENT_TYPE.TREATMENT;
   }

   @Override
   public Integer getTreatmentId() {

      return treatmentId;
   }

   @Override
   public void setTreatmentId(Integer id) {
      this.treatmentId = id;
      
   }

   @Override
   public String getTreatmentName() {

      return this.treatmentName;
   }

   @Override
   public void setTreatmentName(String treatmentName) {
      this.treatmentName = treatmentName;
      
   }

   @Override
   public String getDuration() {

      return this.duration;
   }

   @Override
   public void setDuration(String duration) {
      this.duration = duration;
      
   }

   @Override
   public UNIT getUnit() {

      return this.unit;
   }

   @Override
   public void setUnit(UNIT unit) {
      this.unit = unit;
      
   }

   @Override
   public int getRating() {

      return this.rating;
   }

   @Override
   public void setRating(int rating) {
      this.rating = rating;
      
   }

   @Override
   public String getDescription() {

      return this.description;
   }

   @Override
   public void setDescription(String description) {

      this.description = description;
   }

   
}
