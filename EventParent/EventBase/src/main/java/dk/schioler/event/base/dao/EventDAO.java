package dk.schioler.event.base.dao;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.Event;

@Service
public interface EventDAO extends BaseEventDAO<Event> {

}
