package dk.schioler.event.base.entity;

public interface MedicineEvent extends BaseEvent {
   
//   public static final String TYPE = "MEDICINE";
//   public static final String FLD_TYPE = "TYPE";   
   
   public Integer getMedicineId();
   public void setMedicineId(Integer medicineId);
   
   public Integer getMedicineEventTemplateId();
   public void setMedicineEventTemplateId(Integer id);
   
   public String getNote();
   public void setNote(String note);
   
   public UNIT getUnit();
   public void setUnit(UNIT unit);
   
   public String getDose();
   public void setDose(String dose);
   
   
}
