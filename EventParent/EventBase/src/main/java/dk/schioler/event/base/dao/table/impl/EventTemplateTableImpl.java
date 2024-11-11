package dk.schioler.event.base.dao.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.criteria.AbstractCriteria;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.table.EventTemplateTable;
import dk.schioler.event.base.entity.EventTemplate;

public class EventTemplateTableImpl extends AbstractSQLTable<EventTemplate> implements EventTemplateTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();

	static {
		insertColumns.add(FLD_EVENT_TYPE_ID);
		insertColumns.add(FLD_LOGIN_ID);
		insertColumns.add(FLD_NAME);
		insertColumns.add(FLD_DESCRIPTION);
		insertColumns.add(FLD_SHORT_NAME);

		insertColumns.add(FLD_DOSE);
		insertColumns.add(FLD_UNIT);
		insertColumns.add(FLD_IS_FAVOURITE);
		insertColumns.add(FLD_SORT_ORDER);

		selectColumns.add(FLD_ID);
		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_NAME);
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
			eventTmpl.setLoginId(rs.getInt(FLD_LOGIN_ID));
			eventTmpl.setParentId(rs.getInt(FLD_EVENT_TYPE_ID));
			eventTmpl.setName(rs.getString(FLD_NAME));
			eventTmpl.setDose(rs.getString(FLD_DOSE));
			eventTmpl.setUnit(rs.getString(FLD_UNIT));
			eventTmpl.setShortName(rs.getString(FLD_SHORT_NAME));
			eventTmpl.setDescription(rs.getString(FLD_DESCRIPTION));
			eventTmpl.setFavorite(rs.getBoolean(FLD_IS_FAVOURITE));
			eventTmpl.setSortOrder(rs.getInt(FLD_SORT_ORDER));
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FLD_EVENT_TYPE_ID, type.getParentId());
		map.put(FLD_LOGIN_ID, type.getLoginId());
		map.put(FLD_NAME, type.getName());
		map.put(FLD_SHORT_NAME, type.getShortName());
		map.put(FLD_DESCRIPTION, type.getDescription());
		map.put(FLD_DOSE, type.getDose());
		map.put(FLD_UNIT, type.getUnit());
		map.put(FLD_SORT_ORDER, type.getSortOrder());
		map.put(FLD_IS_FAVOURITE, type.isFavorite());

		return map;
	}

	@Override
	public Map<String, Object> getUpdatetMappings(EventTemplate type) {
		Map<String, Object> map = getInsertMappings(type);
		map.put(FLD_ID, type.getId());

		return map;
	}

	@Override
	public Map<String, Object> addSpecificRetrieveMappings(AbstractCriteria criteria) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (criteria != null) {
			EventTemplateCriteria etC = (EventTemplateCriteria) criteria;
			Boolean favourite = etC.isFavourite();
			if (favourite!= null) {
				map.put(FLD_IS_FAVOURITE, favourite);
			}

		}
		return map;
	}

	@Override
	public List<StringBuffer> addSpecificRetrieveCriteria(AbstractCriteria criteria) {

		List<StringBuffer> sql = new ArrayList<StringBuffer>();

		if (criteria == null) {
			throw new EventDAOException("criteria can not be null");
		}

		EventTemplateCriteria ec = (EventTemplateCriteria) criteria;
		Boolean favourite = ec.isFavourite();
		if (favourite != null) {

			StringBuffer c = new StringBuffer();
			c.append(SPACE).append(FLD_IS_FAVOURITE).append(EQ).append(BIND).append(FLD_IS_FAVOURITE).append(SPACE);
			sql.add(c);
		}
	
		return sql;

	}

}
