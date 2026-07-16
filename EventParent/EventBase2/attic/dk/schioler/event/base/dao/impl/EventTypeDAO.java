package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.EventCategory;
import dk.schioler.event.base.temp.BaseNameDAO;

@Service
public interface EventTypeDAO extends BaseNameDAO<EventCategory> {

}
