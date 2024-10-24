package dk.schioler.event.base.dao.secure;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.EventType;

@Service
public interface SecureEventTypeDAO extends SecureBaseEventDAO<EventType> {

}
