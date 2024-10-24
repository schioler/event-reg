package dk.schioler.event.base.dao.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.EventTypeTable;
import dk.schioler.event.base.entity.EventType;

public class EventTypeTableImpl extends AbstractSQLTable<EventType> implements EventTypeTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();


	static {
		insertColumns.add(FLD_NAME);
		insertColumns.add(FLD_DESCRIPTION);
		insertColumns.add(FLD_SHORT_NAME);

		selectColumns.add(FLD_ID);
		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_NAME);
	}

	public EventTypeTableImpl() {
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


	@Override
	public RowMapper<EventType> getRowMapper() {
		return eventTypeRowMapper;
	}

	private RowMapper<EventType> eventTypeRowMapper = new RowMapper<EventType>() {

		@Override
		public EventType mapRow(ResultSet rs, int rowNum) throws SQLException {
			EventType eventType = new EventType();
			eventType.setId(rs.getInt(1));
			eventType.setName(rs.getString(2));
			eventType.setShortName(rs.getString(3));
			eventType.setDescription(rs.getString(4));
			return eventType;
		}

	};

	@Override
	public List<String> getOrderBy() {

		return orderByColumns;
	}

	@Override
	public Map<String, Object> getInsertMappings(EventType type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FLD_NAME, type.getName());
		map.put(FLD_SHORT_NAME, type.getShortName());
		map.put(FLD_DESCRIPTION, type.getDescription());
		return map;
	}

	@Override
	public Map<String, Object> getUpdatetMappings(EventType type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FLD_ID, type.getId());
		map.put(FLD_NAME, type.getName());
		map.put(FLD_SHORT_NAME, type.getShortName());
		map.put(FLD_DESCRIPTION, type.getDescription());
		return map;
	}

}
