package dk.schioler.event.base.dao.secure;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.secure.entity.Login;

@Service
public interface SecureEventTemplateDAO extends SecureBaseEventDAO<EventTemplate> {
	public List<EventTemplate> getFromEventTypeId(Integer eventTypeId, Login login);

}
