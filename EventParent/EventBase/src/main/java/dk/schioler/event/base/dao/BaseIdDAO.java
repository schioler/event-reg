package dk.schioler.event.base.dao;


import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.entity.AbstractEntityId;

@Service
public interface BaseIdDAO<T extends AbstractEntityId> {
	public T insert(T type);
	public int update(T type);
	public int delete(Integer id, Integer loginId);
	
	public T get(Integer id, Integer loginId);
	public List<T> retrieve(AbstractIdCriteria criteria, int maxRows);

	public void setDataSource(DataSource ds);
	public DataSource getDataSource();
}
