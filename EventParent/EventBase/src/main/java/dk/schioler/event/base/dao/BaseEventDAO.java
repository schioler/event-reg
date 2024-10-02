package dk.schioler.event.base.dao;


import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.AbstractEntity;

@Service
public interface BaseEventDAO<T extends AbstractEntity> {
	public T insert(T type);
	public void update(T type);
	public void delete(Integer id);
	public T get(Integer id);
	public List<T> retrieve(Map<String, Object> criteria);

	public List<T> lookup();
	public void refreshCache(); 
	
	public RowMapper<T> getRowMapper();
	public String getTableName();
	public List<String> getSelectColumns();
	
//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);
//	
}
