package dk.schioler.event.base.dao.impl;

import dk.schioler.event.base.dao.MedicineTypeDAO;
import dk.schioler.event.base.dao.table.impl.MedicineTypeTableImpl;
import dk.schioler.event.base.entity.MedicineType;

public class MedicineFormDAOImpl<T extends MedicineType> extends EntityBaseDAOImpl<T> implements MedicineTypeDAO<T> {

   public MedicineFormDAOImpl() {      
      super(new MedicineTypeTableImpl<T>());
   }

}
