package dk.schioler.event.base.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.EventTemplate;

@Service
public interface EventTemplateDAO extends BaseNameDAO<EventTemplate> {
	public List<EventTemplate> getFromEventTypeId(Integer eventTypeId, Integer loginId);
	public List<EventTemplate> getFavourites(Integer loginId);
}
