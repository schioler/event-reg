package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.StateAspectDAO;
import dk.schioler.event.base.dao.table.impl.StateAspectTableImpl;
import dk.schioler.event.base.entity.StateAspect;

@Service
public class StateAspectDAOImpl extends AbstractNameDAOImpl<StateAspect> implements StateAspectDAO {

   public StateAspectDAOImpl() {
      super(new StateAspectTableImpl());
   }

   @Override
   protected boolean isValidInsertObject(StateAspect type) {
      boolean retVal = super.isValidInsertObject(type);
      return retVal;
   }


}
