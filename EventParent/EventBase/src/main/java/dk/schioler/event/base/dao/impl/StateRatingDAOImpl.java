 package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.StateRatingDAO;
import dk.schioler.event.base.dao.table.impl.StateRatingTableImpl;
import dk.schioler.event.base.entity.StateRating;

@Service
public class StateRatingDAOImpl extends AbstractIdDAOImpl<StateRating> implements StateRatingDAO {

	public StateRatingDAOImpl() {
		super(new StateRatingTableImpl());
	}

	@Override
	protected boolean isValidInsertObject(StateRating type) {
		boolean retVal = super.isValidInsertObject(type);

		return retVal;
	}


}
