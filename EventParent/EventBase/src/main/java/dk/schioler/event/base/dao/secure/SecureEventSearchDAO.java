package dk.schioler.event.base.dao.secure;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.base.entity.Event;

@Service
public interface SecureEventSearchDAO extends SQLConstructs {

	public List<Event> searchEvents(LocalDateTime startTime, LocalDateTime endTime, List<Integer> templateIds) throws EventDAOSecureException;
	
	
//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);

}
