package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.Medicine;
import dk.schioler.event.base.entity.UNIT;

public class MedicineImpl extends EntityBaseImpl implements Medicine {

   public MedicineImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
      // TODO Auto-generated constructor stub
   }

   private Integer medicineTypeId;
   private String name;
   private UNIT unit;
   private String dose;

   @Override
   public void setMedicineTypeId(Integer id) {
      this.medicineTypeId = id;
   }

   @Override
   public Integer getMedicineTypeId() {

      return this.medicineTypeId;
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
   public void setDose(String dose) {
      this.dose = dose;

   }

   @Override
   public String getDose() {

      return this.dose;
   }

   @Override
   public void setUnit(UNIT unit) {
      this.unit = unit;

   }

   @Override
   public UNIT getUnit() {

      return this.unit;
   }

}
