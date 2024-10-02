package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.EventType;

@Service
public class EventTypeDAOImpl extends AbstractDAOImpl<EventType> implements EventTypeDAO {
	public static final String TABLE = "EVENT_TYPE";
	public static final String FLD_ID = COL_ID;
	public static final String FLD_NAME = "NAME";
	public static final String FLD_SHORT_NAME = "SHORT_NAME";
	public static final String FLD_DESCRIPTION = "DESCRIPTION";

	public static final String ALIAS_ET_ID = TABLE + "_" + FLD_ID;
	public static final String ALIAS_ET_NAME = TABLE + "_" + FLD_NAME;
	public static final String ALIAS_ET_SHORT_NAME = TABLE + "_" + FLD_SHORT_NAME;
	public static final String ALIAS_ET_DESCRIPTION = TABLE + "_" + FLD_DESCRIPTION;

	private static final List<String> EVENT_TYPE_COLS;

	static {
		List<String> COLS = new ArrayList<String>();
		COLS.add(FLD_ID);
		COLS.add(FLD_NAME);
		COLS.add(FLD_SHORT_NAME);
		COLS.add(FLD_DESCRIPTION);
		EVENT_TYPE_COLS = Collections.unmodifiableList(COLS);
	}

	public EventTypeDAOImpl() {

	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public List<String> getSelectColumns() {
		return EVENT_TYPE_COLS;
	}

	@Override
	public String getInsertSQL(EventType type) {
		StringBuffer sb = new StringBuffer();

		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
		sb.append(LEFT_PARENTHIS);
		sb.append(FLD_NAME).append(SEP);
		sb.append(FLD_SHORT_NAME).append(SEP);
		sb.append(FLD_DESCRIPTION);
		sb.append(RIGHT_PARENTHIS).append(SPACE);
		sb.append(VALUES).append(SPACE);
		sb.append(LEFT_PARENTHIS);
		sb.append(BIND).append(FLD_NAME).append(SEP);
		sb.append(BIND).append(FLD_SHORT_NAME).append(SEP);
		sb.append(BIND).append(FLD_DESCRIPTION);
		sb.append(RIGHT_PARENTHIS).append(SPACE);

		return sb.toString();
	}

	@Override
	public Map<String, Object> getInsertMappings(EventType eventType) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_NAME, eventType.getName());
		mappings.put(FLD_DESCRIPTION, eventType.getDescription());
		mappings.put(FLD_SHORT_NAME, eventType.getShortName());
		return mappings;
	}

	@Override
	public String getUpdateSQL() {
		StringBuffer sb = new StringBuffer();
		sb.append(UPDATE).append(SPACE).append(TABLE).append(SPACE);
		sb.append(SET).append(SPACE);
		sb.append(FLD_NAME).append(EQ).append(BIND).append(FLD_NAME).append(SEP);
		sb.append(FLD_SHORT_NAME).append(EQ).append(BIND).append(FLD_SHORT_NAME).append(SEP);
		sb.append(FLD_DESCRIPTION).append(EQ).append(BIND).append(FLD_DESCRIPTION).append(SPACE);
		sb.append(WHERE).append(SPACE);
		sb.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);
		return sb.toString();
	}

	@Override
	public Map<String, Object> getUpdateMappings(EventType type) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_ID, type.getId());
		mappings.put(FLD_NAME, type.getName());
		mappings.put(FLD_SHORT_NAME, type.getShortName());
		mappings.put(FLD_DESCRIPTION, type.getDescription());
		return mappings;
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

	@Override
	public String getOrderBy() {
	
		return FLD_NAME;
	}
	
	
}
