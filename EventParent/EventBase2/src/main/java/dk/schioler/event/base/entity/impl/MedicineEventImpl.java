package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.MedicineEvent;
import dk.schioler.event.base.entity.TREE_TYPE;
import dk.schioler.event.base.entity.UNIT;

public class MedicineEventImpl extends BaseEventImpl implements MedicineEvent {

   private Integer medicineId;
   private Integer medicineEventTemplateId;
   private UNIT unit;
   private String dose;
   private String note;
   
   
   public MedicineEventImpl(Integer id, Integer loginId, LocalDateTime created, String name, TREE_TYPE treeType) {
      super(id, loginId, created, EVENT_TYPE.MEDICINE, name, treeType);
   }

   @Override
   public EVENT_TYPE getEventType() {
      return EVENT_TYPE.MEDICINE;
   }

   @Override
   public Integer getMedicineId() {

      return medicineId;
   }

   @Override
   public void setMedicineId(Integer medicineId) {
      this.medicineId = medicineId;
   }

   @Override
   public Integer getMedicineEventTemplateId() {

      return this.medicineEventTemplateId;
   }

   @Override
   public void setMedicineEventTemplateId(Integer id) {
      this.medicineEventTemplateId = id;
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
   public String getDose() {

      return this.dose;
   }

   @Override
   public void setDose(String dose) {
      this.dose = dose;
   }

   @Override
   public String getNote() {
      return note;
   }

   @Override
   public void setNote(String note) {
      this.note = note;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("MedicineEventImpl ");
      builder.append(super.toString());
      builder.append(",[medicineId=" + " medicineId=");
      builder.append(medicineId);
      builder.append(", medicineEventTemplateId=");
      builder.append(medicineEventTemplateId);
      builder.append(", unit=");
      builder.append(unit);
      builder.append(", dose=");
      builder.append(dose);
      builder.append(", note=");
      builder.append(note);
      builder.append("]");
      return builder.toString();
   }

}
