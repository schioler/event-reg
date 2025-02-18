package dk.schioler.event.base.dao;


import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.AbstractNameCriteria;
import dk.schioler.event.base.entity.AbstractEntityName;

@Service
public interface BaseNameDAO<T extends AbstractEntityName> extends BaseIdDAO<T>{
	public T insert(T type);
	public int update(T type);
	public int delete(Integer id, Integer loginId);
	
	public T get(Integer id, Integer loginId);
	public List<T> retrieve(AbstractNameCriteria criteria, int maxRows);

//	public List<T> lookup();
//	public void refreshCache(); 
	
	
//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);
//	
}
