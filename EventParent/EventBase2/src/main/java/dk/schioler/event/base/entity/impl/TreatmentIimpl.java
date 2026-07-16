package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.TREE_TYPE;
import dk.schioler.event.base.entity.Treatment;

public class TreatmentIimpl extends BaseEventImpl implements Treatment {

   protected TreatmentIimpl(Integer id, Integer loginId, LocalDateTime created, EVENT_TYPE eventType, String name, TREE_TYPE treeType) {
      super(id, loginId, created, eventType, name, treeType);

   }

   private Integer supplierId;
   private String name;
   private String description;

   @Override
   public void setSupplierId(Integer supplierId) {
      this.supplierId = supplierId;

   }

   @Override
   public Integer geSupplierId() {
      return this.supplierId;
   }

   @Override
   public void setTreatmentName(String name) {
      this.name = name;
   }

   @Override
   public String getTreatmentName() {

      return this.name;
   }

   @Override
   public void setTreatmentDescription(String description) {
      this.description = description;

   }

   @Override
   public String getTreatmentDescription() {
      return this.description;
   }

}
