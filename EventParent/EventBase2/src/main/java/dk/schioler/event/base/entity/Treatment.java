package dk.schioler.event.base.entity;

public interface Treatment extends EntityBase   {

   public void setSupplierId(Integer supplierId);
   public Integer geSupplierId();
   
   public void setTreatmentName(String name);
   public String getTreatmentName();
   
   public void setTreatmentDescription(String description);
   public String getTreatmentDescription();
   
   
   
   
   
}
