package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.MEDICINE_FORM;
import dk.schioler.event.base.entity.MedicineType;

public class MedicineTypeImpl extends EntityBaseImpl implements MedicineType {

   public MedicineTypeImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
      // TODO Auto-generated constructor stub
   }

   private Integer medicineFirmId;
   private String medicineType;
   private String name;
   private String description;
   private MEDICINE_FORM form;

   @Override
   public void setMedicineForm(MEDICINE_FORM form) {
      this.form = form;

   }

   @Override
   public MEDICINE_FORM getMedicineFormId() {
      return form;
   }

   @Override
   public void setMedicineFirmId(Integer medicineFirmId) {
      this.medicineFirmId = medicineFirmId;

   }

   @Override
   public Integer getMedicineFirmId() {

      return medicineFirmId;
   }

   @Override
   public void setMedicineType(String type) {
      this.medicineType = type;

   }

   @Override
   public String getMedicineType() {

      return this.medicineType;
   }

   @Override
   public void setName(String name) {
      this.name = name;

   }

   @Override
   public String getName() {

      return this.name;
   }

   @Override
   public void setDescription(String description) {
      this.description = description;

   }

   @Override
   public String getDescription() {
      return this.description;
   }

}
