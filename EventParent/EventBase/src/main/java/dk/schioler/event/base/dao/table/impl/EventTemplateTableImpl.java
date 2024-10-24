package dk.schioler.event.base.dao.table.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.EventTemplateTable;
import dk.schioler.event.base.entity.EventTemplate;

public class EventTemplateTableImpl extends AbstractSQLTable<EventTemplate> implements EventTemplateTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();

	static {
		insertColumns.add(FLD_EVENT_TYPE_ID);
		
		insertColumns.add(FLD_NAME);
		insertColumns.add(FLD_DESCRIPTION);
		insertColumns.add(FLD_SHORT_NAME);

		insertColumns.add(FLD_DOSE);
		insertColumns.add(FLD_UNIT);
		
		selectColumns.add(FLD_ID);
		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_NAME);
	}

	
	public EventTemplateTableImpl() {
		super();
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public List<String> getUpdateColumns() {	
		return insertColumns;
	}

	
	@Override
	public List<String> getSelectColumns() {
		return selectColumns;
	}


	private RowMapper<EventTemplate> eventTemplateRowMapper = new RowMapper<EventTemplate>() {

		@Override
		public EventTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
			EventTemplate eventTmpl = new EventTemplate();
			eventTmpl.setId(rs.getInt(FLD_ID));
			eventTmpl.setEventTypeId(rs.getInt(FLD_EVENT_TYPE_ID));
			eventTmpl.setName(rs.getString(FLD_NAME));
			eventTmpl.setDose(rs.getString(FLD_DOSE));
			eventTmpl.setUnit(rs.getString(FLD_UNIT));
			eventTmpl.setShortName(rs.getString(FLD_SHORT_NAME));
			eventTmpl.setDescription(rs.getString(FLD_DESCRIPTION));
			return eventTmpl;
		}

	};

	@Override
	public RowMapper<EventTemplate> getRowMapper() {
		return eventTemplateRowMapper;
	}


	@Override
	public List<String> getOrderBy() {

		return orderByColumns;
	}


	@Override
	public Map<String, Object> getInsertMappings(EventTemplate type) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(FLD_EVENT_TYPE_ID, type.getEventTypeId());
		map.put(FLD_NAME, type.getName());
		map.put(FLD_SHORT_NAME, type.getShortName());
		map.put(FLD_DESCRIPTION, type.getDescription());
		map.put(FLD_DOSE, type.getDose());
		map.put(FLD_UNIT, type.getUnit());
		
		return map;
	}


	@Override
	public Map<String, Object> getUpdatetMappings(EventTemplate type) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put(FLD_ID, type.getId());
		map.put(FLD_NAME, type.getName());
		map.put(FLD_SHORT_NAME, type.getShortName());
		map.put(FLD_DESCRIPTION, type.getDescription());
		map.put(FLD_DOSE, type.getDose());
		map.put(FLD_UNIT, type.getUnit());
		map.put(FLD_EVENT_TYPE_ID, type.getEventTypeId());
		return map;
	}

}
