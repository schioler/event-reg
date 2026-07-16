package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.MedicineForm;

public class MedicineFormImpl extends EntityBaseImpl implements MedicineForm {

   public MedicineFormImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
      // TODO Auto-generated constructor stub
   }

   private String name;
   private String form;

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getForm() {
      return this.form;
   }

   public void setForm(String form) {
      this.form = form;
   }

}
