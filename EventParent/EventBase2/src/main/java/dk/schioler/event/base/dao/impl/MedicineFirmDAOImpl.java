package dk.schioler.event.base.dao.impl;

import dk.schioler.event.base.dao.MedicineFirmDAO;
import dk.schioler.event.base.dao.table.impl.MedicineFirmTableImpl;
import dk.schioler.event.base.entity.MedicineFirm;

public class MedicineFirmDAOImpl<T extends MedicineFirm> extends EntityBaseDAOImpl<T> implements MedicineFirmDAO<T> {

   public MedicineFirmDAOImpl() {      
      super(new MedicineFirmTableImpl<T>());
   }

}
