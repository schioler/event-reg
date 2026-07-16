package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.MedicineFirm;

public class MedicineFirmImpl extends EntityBaseImpl implements MedicineFirm {

   private String name;
   private String brand;

   
   
   public MedicineFirmImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
   
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getBrand() {
      return this.brand;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

}
