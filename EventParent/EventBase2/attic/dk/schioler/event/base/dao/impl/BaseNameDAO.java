package dk.schioler.event.base.dao.impl;


import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EntityBaseDAO;
import dk.schioler.event.base.entity.AbstractEntityName;

@Service
public interface BaseNameDAO<T extends AbstractEntityName> extends EntityBaseDAO<T>{
//	public T insert(T type);
//	public int update(T type);
//	public int delete(Integer id, Integer loginId);
//	
//	public T get(Integer id, Integer loginId);
//	public List<T> retrieve(AbstractNameCriteria criteria, int maxRows);

//	public List<T> lookup();
//	public void refreshCache(); 
	
	
//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);
//	
}
