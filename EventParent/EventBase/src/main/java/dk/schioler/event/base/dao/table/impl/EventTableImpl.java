package dk.schioler.event.base.dao.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.criteria.AbstractCriteria;
import dk.schioler.event.base.dao.table.EventTable;
import dk.schioler.event.base.entity.Event;

public class EventTableImpl extends AbstractSQLTable<Event> implements EventTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();

	static {
		insertColumns.add(FLD_EVENT_TEMPLATE_ID);
		insertColumns.add(FLD_LOGIN_ID);
		
		insertColumns.add(FLD_NAME);
		insertColumns.add(FLD_NOTE);
		insertColumns.add(FLD_SHORT_NAME);

		insertColumns.add(FLD_DOSE);
		insertColumns.add(FLD_UNIT);
				
		
		selectColumns.add(FLD_ID);
		selectColumns.add(FLD_EVENT_TS);
		
		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_NAME);
	}

	
	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Map<String, Object> getInsertMappings(Event event) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_EVENT_TEMPLATE_ID, event.getParentId());
		mappings.put(FLD_LOGIN_ID, event.getLoginId());

		if (event.getEventTS() != null) {
			mappings.put(FLD_EVENT_TS, event.getEventTS());
		}

		mappings.put(FLD_NOTE, event.getNote());
		mappings.put(FLD_NAME, event.getName());
		mappings.put(FLD_SHORT_NAME, event.getShortName());
		mappings.put(FLD_DOSE, event.getDose());
		mappings.put(FLD_UNIT, event.getUnit());

		return mappings;
	}

	@Override
	public Map<String, Object> getUpdatetMappings(Event type) {
		Map<String, Object> map = getInsertMappings(type);
		map.put(FLD_ID, type.getId());
		return map;
	}
	
	
	
	@Override
	public Map<String, Object> addSpecificRetrieveMappings(AbstractCriteria criteria) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (criteria != null) {

		
		}
		return map;
	}
	

	@Override
	public List<StringBuffer> addSpecificRetrieveCriteria(AbstractCriteria criteria) {
		List<StringBuffer> sql = new ArrayList<StringBuffer>();

		if (criteria == null) {
			throw new EventDAOException("criteria can not be null");
		}

		return sql;

	}
	
	

	@Override
	public RowMapper<Event> getRowMapper() {
		return eventRowMapper;
	}

	private RowMapper<Event> eventRowMapper = new RowMapper<Event>() {

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event eventTmpl = new Event();
			eventTmpl.setId(rs.getInt(FLD_ID));
			eventTmpl.setLoginId(rs.getInt(FLD_LOGIN_ID));
			eventTmpl.setParentId(rs.getInt(FLD_EVENT_TEMPLATE_ID));
			eventTmpl.setDose(rs.getString(FLD_DOSE));
			eventTmpl.setUnit(rs.getString(FLD_UNIT));
			eventTmpl.setName(rs.getString(FLD_NAME));
			eventTmpl.setShortName(rs.getString(FLD_SHORT_NAME));
			eventTmpl.setNote(rs.getString(FLD_NOTE));
			eventTmpl.setEventTS(rs.getTimestamp(FLD_EVENT_TS).toLocalDateTime());
			return eventTmpl;
		}

	};

	@Override
	public List<String> getSelectColumns() {
		return selectColumns;
	}

	@Override
	public List<String> getUpdateColumns() {
		return insertColumns;
	}

	@Override
	public List<String> getOrderBy() {
		List<String> orderBy =  new ArrayList<String>();
		orderBy.add(FLD_NAME);
		
		return orderBy;
	}

}
