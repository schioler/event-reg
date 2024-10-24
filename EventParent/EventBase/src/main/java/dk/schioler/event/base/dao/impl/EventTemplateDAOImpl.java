package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.table.impl.EventTemplateTableImpl;
import dk.schioler.event.base.entity.EventTemplate;

@Service
public class EventTemplateDAOImpl extends AbstractDAOImpl<EventTemplate> implements EventTemplateDAO {
//	public static final String TABLE = "EVENT_TEMPLATE";
//
//	public static final String FLD_EVENT_TYPE_ID = "EVENT_TYPE_ID";
//	public static final String FLD_NAME = "NAME";
//	public static final String FLD_SHORT_NAME = "SHORT_NAME";
//	public static final String FLD_DESCRIPTION = "DESCRIPTION";
//	public static final String FLD_DOSE = "DOSE";
//	public static final String FLD_UNIT = "UNIT";
//
//	public static final String ALIAS_TMPL_ID = TABLE + "_" + FLD_ID;
//	public static final String ALIAS_TMPL_ET_ID = TABLE + "_" + FLD_EVENT_TYPE_ID;
//	public static final String ALIAS_TMPL_NAME = TABLE + "_" + FLD_NAME;
//	public static final String ALIAS_TMPL_SHORT_NAME = TABLE + "_" + FLD_SHORT_NAME;
//	public static final String ALIAS_TMPL_DESCRIPTION = TABLE + "_" + FLD_DESCRIPTION;
//	public static final String ALIAS_TMPL_DOSE = TABLE + "_" + FLD_DOSE;
//	public static final String ALIAS_TMPL_UNIT = TABLE + "_" + FLD_UNIT;
//
//	private static final List<String> SELECT_COLS;
//
//	private static final List<String> UPDATE_COLS;
//
//	static {
//		List<String> cols = new ArrayList<String>();
//		cols.add(FLD_ID);
//		cols.add(FLD_EVENT_TYPE_ID);
//		cols.add(FLD_NAME);
//		cols.add(FLD_SHORT_NAME);
//		cols.add(FLD_DESCRIPTION);
//		cols.add(FLD_DOSE);
//		cols.add(FLD_UNIT);
//
//		SELECT_COLS = Collections.unmodifiableList(cols);
//
//		cols = new ArrayList<String>();
//		cols.add(FLD_EVENT_TYPE_ID);
//		cols.add(FLD_NAME);
//		cols.add(FLD_SHORT_NAME);
//		cols.add(FLD_DESCRIPTION);
//		cols.add(FLD_DOSE);
//		cols.add(FLD_UNIT);
//
//		UPDATE_COLS = Collections.unmodifiableList(cols);
//
//	}

	public EventTemplateDAOImpl() {
		super(new EventTemplateTableImpl());
	}

	@Override
	protected boolean validateInsertObject(EventTemplate type) {
		boolean retVal = false;
		if (type != null) {
			if (type.getEventTypeId() != null) {
				if (StringUtils.isNotBlank(type.getName())) {
					if(StringUtils.isNotBlank(type.getUnit())) {
						if(StringUtils.isNotBlank(type.getDose())) {
							retVal = true;
							
						}
					}
				}
			}
		}
		return retVal;
	}



//	@Override
//	public String getInsertSQL(EventTemplate template) {
//		StringBuffer sb = new StringBuffer();
//
//		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
//		sb.append(LEFT_PARENTHIS);
//		sb.append(FLD_EVENT_TYPE_ID).append(SEP);
//		sb.append(FLD_NAME).append(SEP);
//		sb.append(FLD_SHORT_NAME).append(SEP);
//		sb.append(FLD_DOSE).append(SEP);
//		sb.append(FLD_UNIT).append(SEP);
//		sb.append(FLD_DESCRIPTION);
//		sb.append(RIGHT_PARENTHIS).append(SPACE);
//		sb.append(VALUES).append(SPACE);
//		sb.append(LEFT_PARENTHIS);
//		sb.append(BIND).append(FLD_EVENT_TYPE_ID).append(SEP);
//		sb.append(BIND).append(FLD_NAME).append(SEP);
//		sb.append(BIND).append(FLD_SHORT_NAME).append(SEP);
//		sb.append(BIND).append(FLD_DOSE).append(SEP);
//		sb.append(BIND).append(FLD_UNIT).append(SEP);
//		sb.append(BIND).append(FLD_DESCRIPTION);
//		sb.append(RIGHT_PARENTHIS).append(SPACE);
//
//		return sb.toString();
//	}

//	@Override
//	public Map<String, Object> getInsertMappings(EventTemplate eventType) {
//		Map<String, Object> mappings = new TreeMap<String, Object>();
//		mappings.put(FLD_EVENT_TYPE_ID, eventType.getEventTypeId());
//		mappings.put(FLD_NAME, eventType.getName());
//		mappings.put(FLD_DOSE, eventType.getDose());
//		mappings.put(FLD_UNIT, eventType.getUnit());
//		mappings.put(FLD_DESCRIPTION, eventType.getDescription());
//		mappings.put(FLD_SHORT_NAME, eventType.getShortName());
//		return mappings;
//	}

//	@Override
//	public String getUpdateSQL() {
//		StringBuffer sb = new StringBuffer();
//		sb.append(UPDATE).append(SPACE).append(TABLE).append(SPACE);
//		sb.append(SET).append(SPACE);
//		sb.append(FLD_EVENT_TYPE_ID).append(EQ).append(BIND).append(FLD_EVENT_TYPE_ID).append(SEP);
//		sb.append(FLD_NAME).append(EQ).append(BIND).append(FLD_NAME).append(SEP);
//		sb.append(FLD_SHORT_NAME).append(EQ).append(BIND).append(FLD_SHORT_NAME).append(SEP);
//		sb.append(FLD_DOSE).append(EQ).append(BIND).append(FLD_DOSE).append(SEP);
//		sb.append(FLD_UNIT).append(EQ).append(BIND).append(FLD_UNIT).append(SEP);
//		sb.append(FLD_DESCRIPTION).append(EQ).append(BIND).append(FLD_DESCRIPTION).append(SPACE);
//		sb.append(WHERE).append(SPACE);
//		sb.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);
//		return sb.toString();
//
//	}

//	@Override
//	public Map<String, Object> getUpdateMappings(EventTemplate type) {
//
//		Map<String, Object> mappings = new TreeMap<String, Object>();
//
//		mappings.put(FLD_ID, type.getId());
//		mappings.put(FLD_EVENT_TYPE_ID, type.getEventTypeId());
//		mappings.put(FLD_NAME, type.getName());
//		mappings.put(FLD_SHORT_NAME, type.getShortName());
//		mappings.put(FLD_DESCRIPTION, type.getDescription());
//		mappings.put(FLD_UNIT, type.getUnit());
//		mappings.put(FLD_DOSE, type.getDose());
//
//		return mappings;
//	}

//	private RowMapper<EventTemplate> eventTemplateRowMapper = new RowMapper<EventTemplate>() {
//
//		@Override
//		public EventTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
//			EventTemplate eventTmpl = new EventTemplate();
//			eventTmpl.setId(rs.getInt(FLD_ID));
//			eventTmpl.setEventTypeId(rs.getInt(FLD_EVENT_TYPE_ID));
//			eventTmpl.setName(rs.getString(FLD_NAME));
//			eventTmpl.setDose(rs.getString(FLD_DOSE));
//			eventTmpl.setUnit(rs.getString(FLD_UNIT));
//			eventTmpl.setShortName(rs.getString(FLD_SHORT_NAME));
//			eventTmpl.setDescription(rs.getString(FLD_DESCRIPTION));
//			return eventTmpl;
//		}
//
//	};
//
//	@Override
//	public RowMapper<EventTemplate> getRowMapper() {
//		return eventTemplateRowMapper;
//	}

	@Override
	public List<EventTemplate> getFromEventTypeId(Integer eventTypeId) {
		List<EventTemplate> lookup = this.lookup();
		List<EventTemplate> retVal = new ArrayList<EventTemplate>();
		for (EventTemplate templ : lookup) {
			if (templ.getEventTypeId().equals(eventTypeId)) {
				retVal.add(templ);
			}
		}
		return retVal;
	}

	private Object cacheLock = new Object();

	private List<EventTemplate> eventTemplates = null;

	private LocalDateTime nextLookupTime = LocalDateTime.now();

	@Override
	public void refreshCache() {
//		LocalDateTime now = LocalDateTime.now();
		logger.debug("refreshCache called. current nextLookupTime=" + nextLookupTime);

		synchronized (cacheLock) {
			logger.debug("Will refresh cache");
			eventTemplates = super.lookup();
			nextLookupTime = LocalDateTime.now().plusHours(CACHE_VALID_HOURS);
			logger.debug("refreshCache: next lookupTime=" + nextLookupTime);

		}
	}

	@Override
	public List<EventTemplate> lookup() {
		LocalDateTime now = LocalDateTime.now();
		logger.debug("lookup(), now=" + now.toString() + ", nextLookup=" + nextLookupTime);

		synchronized (cacheLock) {
			if (now.isAfter(nextLookupTime)) {
				logger.debug("lookup will look into db");
				eventTemplates = super.lookup();
				nextLookupTime = LocalDateTime.now().plusHours(CACHE_VALID_HOURS);
				logger.debug("lookup(): next lookupTime=" + nextLookupTime);
			}
		}
		return eventTemplates;
	}


}
