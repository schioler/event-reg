package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.TreatmentEventTemplate;
import dk.schioler.event.base.entity.UNIT;

public class TreatmentEventTemplateImpl extends EntityBaseImpl implements TreatmentEventTemplate {

   public TreatmentEventTemplateImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
      // TODO Auto-generated constructor stub
   }

   private Integer treatmentId;
   private boolean isFavorite;
   private UNIT unit;
   private String duration;
   
   
   @Override
   public Integer getTreatmentId() {
      return treatmentId;
   }

   @Override
   public void setTreatmentId(Integer id) {
      this.treatmentId = id;

   }

   @Override
   public boolean isFavorite() {

      return this.isFavorite; 
      
   }

   @Override
   public void setFavorite(boolean isFavorite) {
      this.isFavorite = isFavorite;

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
   public String getDuration() {

      return this.duration;
   }

   @Override
   public void setDuration(String duration) {
      this.duration = duration;

   }

}
