package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.table.impl.EventTypeTableImpl;
import dk.schioler.event.base.entity.EventType;

@Service
public class EventTypeDAOImpl extends AbstractNameDAOImpl<EventType> implements EventTypeDAO {

	public EventTypeDAOImpl() {
		super(new EventTypeTableImpl());
	}


	@Override
	protected boolean isValidInsertObject(EventType type) throws EventDAOException {
	   boolean isValid = super.isValidInsertObject(type);
	   
		
		
		
		return isValid;
	}

}
