package dk.schioler.event.base.dao.secure;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.Event;

@Service
public interface SecureEventDAO extends SecureBaseEventDAO<Event> {

}
