package dk.schioler.event.base.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.table.impl.EventTypeTableImpl;
import dk.schioler.event.base.entity.EventType;

@Service
public class EventTypeDAOImpl extends AbstractDAOImpl<EventType> implements EventTypeDAO {

	public EventTypeDAOImpl() {
		super(new EventTypeTableImpl());
	}


	@Override
	protected boolean validateInsertObject(EventType type) throws EventDAOException {
		if (type == null) {
			throw new EventDAOException("Instance can not be null");
		}
		super.verifyLoginId(type.getLoginId());
		
		if (StringUtils.isBlank(type.getName())) {
				throw new EventDAOException("Name must be specified");
		}
		if (StringUtils.isBlank(type.getShortName())) {
				throw new EventDAOException("ShortName must be specified");
		}
		
		return true;
	}

}
