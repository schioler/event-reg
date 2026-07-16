package dk.schioler.event.base.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.base.entity.MedicineFirm;

@Service
public interface EventSearchDAO extends SQLConstructs {

	public List<MedicineFirm> searchEvents(LocalDateTime startTime, LocalDateTime endTime, List<Integer> templateIds, Integer loginId) throws EventDAOException;
	
	
//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);

}
