package dk.schioler.event.base.dao;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.AbstractEntity;

@Service
public interface BaseEventDAO<T extends AbstractEntity> {
	public T insert(T type);
	public int update(T type);
	public int delete(Integer id);
	
	public T get(Integer id);
	public List<T> retrieve(Map<String, Object> criteria);

	public List<T> lookup();
	public void refreshCache(); 
	
	
//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);
//	
}
