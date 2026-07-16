package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.MedicineEventTemplate;
import dk.schioler.event.base.entity.UNIT;

public class MedicineEventTemplateImpl extends EntityBaseImpl implements MedicineEventTemplate {

   public MedicineEventTemplateImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
      // TODO Auto-generated constructor stub
   }

   private Integer medicineId;
   private String medicineName;
   private UNIT unit; 
   private String dose;
   private boolean isFavorite;

   public Integer getMedicineId() {
      return this.medicineId;
   }

   public void setMedicineId(Integer id) {
      this.medicineId = id;
   }

   public String getMedicineName (){
      return this.medicineName ;
   }
   
   public void setMedicineName (String medicineName){
      this.medicineName = medicineName;
   }
   public boolean isFavorite() {
      return isFavorite;
   }

   public void setFavorite(boolean isFavorite) {
      this.isFavorite = isFavorite;
   }

   public UNIT getUnit() {
      return unit;
   }

   public void setUnit(UNIT unit) {
      this.unit = unit;
   }

   public String getDose() {
      return this.dose;
   }

   public void setDose(String dose) {
      this.dose = dose;
   }

}
