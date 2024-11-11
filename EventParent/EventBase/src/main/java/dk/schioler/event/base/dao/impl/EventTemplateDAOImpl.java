package dk.schioler.event.base.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.table.impl.EventTemplateTableImpl;
import dk.schioler.event.base.entity.EventTemplate;

@Service
public class EventTemplateDAOImpl extends AbstractDAOImpl<EventTemplate> implements EventTemplateDAO {

	public EventTemplateDAOImpl() {
		super(new EventTemplateTableImpl());
	}

	@Override
	protected boolean validateInsertObject(EventTemplate type) {
		if (type == null) {
			throw new EventDAOException("EventTemplate can not be null");
		}
		super.verifyLoginId(type.getLoginId());

		if (type.getParentId() == null) {
			throw new EventDAOException("EventTypeId can not be empty");
		}
		if (StringUtils.isBlank(type.getName())) {
			throw new EventDAOException("Name can not be empty");
		}
		if (StringUtils.isBlank(type.getUnit())) {
			throw new EventDAOException("Unit can not be empty");
		}
		if (StringUtils.isBlank(type.getDose())) {
			throw new EventDAOException("Dose can not be empty");
		}
		return true;
	}

	@Override
	public List<EventTemplate> getFromEventTypeId(Integer eventTypeId, Integer loginId) {
		EventTemplateCriteria et = new EventTemplateCriteria();
		et.setEventTypeId(eventTypeId);
		et.addLoginId(loginId);
		List<EventTemplate> list = retrieve(et, 0);

		return list;
	}

	@Override
	public List<EventTemplate> getFavourites(Integer loginId) {
		EventTemplateCriteria et = new EventTemplateCriteria();
		
		et.addLoginId(loginId);
		et.setFavourite(true);
		List<EventTemplate> list = retrieve(et, 0);

		return list;

		
	}

}
