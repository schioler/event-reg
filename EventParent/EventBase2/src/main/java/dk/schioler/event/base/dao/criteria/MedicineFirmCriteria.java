package dk.schioler.event.base.dao.criteria;

import org.springframework.stereotype.Component;

@Component
public class MedicineFirmCriteria extends EntityBaseCriteria {
   private String name;
   private String brand;
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getBrand() {
      return brand;
   }
   public void setBrand(String brand) {
      this.brand = brand;
   }
   
   

	
}
