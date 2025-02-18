 package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.StateRegistrationDAO;
import dk.schioler.event.base.dao.table.impl.StateRegistrationTableImpl;
import dk.schioler.event.base.entity.StateRegistration;

@Service
public class StateRegistrationDAOImpl extends AbstractIdDAOImpl<StateRegistration> implements StateRegistrationDAO {


	public StateRegistrationDAOImpl() {
		super(new StateRegistrationTableImpl());
	}


	@Override
	protected boolean isValidInsertObject(StateRegistration type) {
		boolean retVal = super.isValidInsertObject(type);
		return retVal;
	}


}
