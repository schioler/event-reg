package dk.schioler.event.base.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.table.impl.EventTypeTableImpl;
import dk.schioler.event.base.entity.EventType;

@Service
public class EventTypeDAOImpl extends AbstractDAOImpl<EventType> implements EventTypeDAO {

	public EventTypeDAOImpl() {
		super(new EventTypeTableImpl());
	}

//	@Override
//	public String getSQLTableName() {
//		return TABLE;
//	}
//
//	@Override
//	public List<String> getSQLSelectColumns() {
//		return SELECT_COLS;
//	}

//	@Override
//	public String getInsertSQL(EventType type) {
//		StringBuffer sb = new StringBuffer();
//
//		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
//		sb.append(LEFT_PARENTHIS);
//		sb.append(FLD_NAME).append(SEP);
//		sb.append(FLD_SHORT_NAME).append(SEP);
//		sb.append(FLD_DESCRIPTION);
//		sb.append(RIGHT_PARENTHIS).append(SPACE);
//		sb.append(VALUES).append(SPACE);
//		sb.append(LEFT_PARENTHIS);
//		sb.append(BIND).append(FLD_NAME).append(SEP);
//		sb.append(BIND).append(FLD_SHORT_NAME).append(SEP);
//		sb.append(BIND).append(FLD_DESCRIPTION);
//		sb.append(RIGHT_PARENTHIS).append(SPACE);
//
//		return sb.toString();
//	}

//	@Override
//	public Map<String, Object> getInsertMappings(EventType eventType) {
//
//		return table.getInsertMappings(eventType);
//	}

//	@Override
//	public String getUpdateSQL() {
//		StringBuffer sb = new StringBuffer();
//		sb.append(UPDATE).append(SPACE).append(TABLE).append(SPACE);
//		sb.append(SET).append(SPACE);
//		sb.append(FLD_NAME).append(EQ).append(BIND).append(FLD_NAME).append(SEP);
//		sb.append(FLD_SHORT_NAME).append(EQ).append(BIND).append(FLD_SHORT_NAME).append(SEP);
//		sb.append(FLD_DESCRIPTION).append(EQ).append(BIND).append(FLD_DESCRIPTION).append(SPACE);
//		sb.append(WHERE).append(SPACE);
//		sb.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);
//		return sb.toString();
//	}

//	@Override
//	public Map<String, Object> getUpdateMappings(EventType type) {
//		Map<String, Object> mappings = new TreeMap<String, Object>();
//		mappings.put(FLD_ID, type.getId());
//		mappings.put(FLD_NAME, type.getName());
//		mappings.put(FLD_SHORT_NAME, type.getShortName());
//		mappings.put(FLD_DESCRIPTION, type.getDescription());
//		return mappings;
//		return table.getUpdatetMappings(type);
//	}

//	@Override
//	public RowMapper<EventType> getRowMapper() {
//		return eventTypeRowMapper;
//	}
//
//	private RowMapper<EventType> eventTypeRowMapper = new RowMapper<EventType>() {
//
//		@Override
//		public EventType mapRow(ResultSet rs, int rowNum) throws SQLException {
//			EventType eventType = new EventType();
//			eventType.setId(rs.getInt(1));
//			eventType.setName(rs.getString(2));
//			eventType.setShortName(rs.getString(3));
//			eventType.setDescription(rs.getString(4));
//			return eventType;
//		}
//
//	};

	@Override
	public List<EventType> retrieve(Map<String, Object> criteria) {
		return super.retrieve(criteria);
	}

	@Override
	public void refreshCache() {
		logger.debug("refreshCache called. current nextLookupTime=" + nextLookupTime);
		synchronized (cacheLock) {
			logger.debug("Will refresh cache");
			eventTypes = super.lookup();
			nextLookupTime = LocalDateTime.now().plusHours(CACHE_VALID_HOURS);
			logger.debug("refreshCache: next lookupTime=" + nextLookupTime);
		}
	}

	private Object cacheLock = new Object();

	private List<EventType> eventTypes = null;

	private LocalDateTime nextLookupTime = LocalDateTime.now();

//	@Override
//	public List<T> lookup() {
//		StringBuffer sql = new StringBuffer();
//		sql.append(SELECT).append(SPACE);
//		sql.append(getSelectSqlFromColumns(SELECT_COLUMNS)).append(SPACE);
//		sql.append(FROM).append(SPACE).append(getSQLTableName()).append(SPACE);
//
//		String orderBy = getOrderBy();
//		if (StringUtils.isNoneEmpty(orderBy)) {
//			sql.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
//		}
//		List<T> query = jdbcTemplate.query(sql.toString(), getRowMapper());
//
//		return query;
//	}

	public List<EventType> lookup() {
		LocalDateTime now = LocalDateTime.now();
		logger.debug("lookup(), now=" + now.toString() + ", nextLookup=" + nextLookupTime);

		synchronized (cacheLock) {
			if (now.isAfter(nextLookupTime)) {
				logger.debug("lookup will look into db");
				eventTypes = super.lookup();
				nextLookupTime = LocalDateTime.now().plusHours(CACHE_VALID_HOURS);
				logger.debug("lookup(): next lookupTime=" + nextLookupTime);
			}
		}
		return eventTypes;
	}

//	@Override
//	public String getOrderBy() {
//
//		return FLD_NAME;
//	}

	@Override
	protected boolean validateInsertObject(EventType type) {
		boolean retVal = false;
		if (type != null) {
			if (StringUtils.isNotBlank(type.getName())) {
				retVal = true;
			}
		}
		return retVal;
	}

}
