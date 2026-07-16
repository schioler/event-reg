package dk.schioler.event.base.dao.criteria;

import java.util.List;

import org.springframework.stereotype.Component;

import dk.schioler.event.base.entity.UNIT;

@Component
public class MedicineCriteria extends EntityBaseCriteria {

   private List<Integer> medicineTypeIds;
   private String name;
   private UNIT unit;
   private String dose;
   
   
   public List<Integer>  getMedicineTypeIds() {
      return medicineTypeIds;
   }
   
   public void addMedicineTypeId(Integer medicineTypeId) {
      this.medicineTypeIds.add(medicineTypeId);
   }
   
   
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   
   
   public UNIT getUnit() {
      return unit;
   }
   public void setUnit(UNIT unit) {
      this.unit = unit;
   }
   public String getDose() {
      return dose;
   }
   public void setDose(String dose) {
      this.dose = dose;
   }

	
}
