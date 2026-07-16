package dk.schioler.event.base.entity;

public interface MedicineType extends EntityBase   {

   public void setMedicineForm(MEDICINE_FORM form);
   public MEDICINE_FORM getMedicineFormId();
   
   
   public void setMedicineFirmId(Integer medicineFirmId);
   public Integer getMedicineFirmId();
   
   public void setMedicineType(String type);
   public String getMedicineType(); 

   
   public void setName(String name);
   public String getName(); 
   
   public void setDescription(String description);
   public String getDescription();
   
   
   
   
   
}
