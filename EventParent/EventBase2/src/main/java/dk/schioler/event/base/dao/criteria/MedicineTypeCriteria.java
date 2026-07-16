package dk.schioler.event.base.dao.criteria;

import org.springframework.stereotype.Component;

@Component
public class MedicineTypeCriteria extends EntityBaseCriteria {
   private Integer medicineFirmId;
   private String medicineType;
   private String name;
   private String description;

   public Integer getMedicineFirmId() {
      return medicineFirmId;
   }

   public void setMedicineFirmId(Integer medicineFirmId) {
      this.medicineFirmId = medicineFirmId;
   }

   public String getMedicineType() {
      return medicineType;
   }

   public void setMedicineType(String medicineType) {
      this.medicineType = medicineType;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

}
