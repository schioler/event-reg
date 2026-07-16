package dk.schioler.event.base.entity;

public interface Medicine extends EntityBase   {

   public void setMedicineTypeId(Integer medicineFirmId);
   public Integer getMedicineTypeId();
   
   public void setName(String name);
   public String getName();
   
   public void setDose(String dose);
   public String getDose();
   
   public void setUnit(UNIT unit);
   public UNIT getUnit();
   
   
   
   
}
