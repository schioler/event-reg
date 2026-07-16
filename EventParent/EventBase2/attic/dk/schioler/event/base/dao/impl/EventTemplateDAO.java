package dk.schioler.event.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.MedicineEventTemplate;
import dk.schioler.event.base.temp.BaseNameDAO;

@Service
public interface EventTemplateDAO extends BaseNameDAO<MedicineEventTemplate> {
	public List<MedicineEventTemplate> getFromEventTypeId(Integer eventTypeId, Integer loginId);
	public List<MedicineEventTemplate> getFavourites(Integer loginId);
}
