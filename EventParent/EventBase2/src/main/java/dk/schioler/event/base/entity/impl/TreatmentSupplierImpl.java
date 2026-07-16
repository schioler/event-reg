package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.TreatmentSupplier;

public class TreatmentSupplierImpl extends EntityBaseImpl implements TreatmentSupplier {

   public TreatmentSupplierImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
      // TODO Auto-generated constructor stub
   }

   private String supplierName;
   private String brand;

   @Override
   public void setSupplierName(String name) {
      this.supplierName = name;

   }

   @Override
   public String getSupplierName() {

      return this.supplierName;

   }

   @Override
   public void setBrand(String brand) {
      this.brand = brand;

   }

   @Override
   public String getBrand() {

      return this.brand;
   }

}
