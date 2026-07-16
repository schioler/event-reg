package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.DAOException;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.table.impl.MedicineFirmTableImpl;
import dk.schioler.event.base.entity.EventCategory;

@Service
public class EventTypeDAOImpl extends AbstractNameDAOImpl<EventCategory> implements EventTypeDAO {

	public EventTypeDAOImpl() {
		super(new MedicineFirmTableImpl());
	}


	@Override
	protected boolean isValidInsertObject(EventCategory type) throws DAOException {
	   boolean isValid = super.isValidInsertObject(type);
	   		
		return isValid;
	}

}
