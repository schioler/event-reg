package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.EventTypeRowMapper;
import dk.schioler.event.base.dao.table.EventTypeTable;
import dk.schioler.event.base.entity.EventType;

public class EventTypeTableImpl extends AbstractSQLTableParentChild<EventType> implements EventTypeTable {

	public EventTypeTableImpl() {
		super();
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public RowMapper<EventType> getRowMapper() {
		return new EventTypeRowMapper();
	}


	@Override
	public List<String> getOrderBy() {

		return orderByColumns;
	}

	@Override
	public Map<String, Object> getInsertMappings(EventType type) {
		Map<String, Object> map = super.getInsertMappings(type);
		return map;
	}

	@Override
	public Map<String, Object> getUpdateMappings(EventType type) {
		Map<String, Object> map = super.getUpdateMappings(type);
		return map;
	}


   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      List<StringBuffer> criteria = super.addLevelSpecificCriteriaFrom(idCrit);
      
      
      
      return criteria;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      return super.addLevelSpecificRetrieveMappings(criteria);
   }
}
