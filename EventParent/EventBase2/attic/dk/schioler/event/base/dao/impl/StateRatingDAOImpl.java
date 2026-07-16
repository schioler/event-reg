 package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.StateRatingDAO;
import dk.schioler.event.base.dao.table.impl.StateRatingTableImpl;
import dk.schioler.event.base.entity.BaseEvent;

@Service
public class StateRatingDAOImpl extends EntityBaseDAOImpl<BaseEvent> implements StateRatingDAO {

	public StateRatingDAOImpl() {
		super(new StateRatingTableImpl());
	}

	@Override
	protected boolean isValidInsertObject(BaseEvent type) {
		boolean retVal = super.isValidInsertObject(type);

		return retVal;
	}


}
