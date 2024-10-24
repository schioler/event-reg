 package dk.schioler.event.base.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.table.impl.EventTableImpl;
import dk.schioler.event.base.entity.Event;

@Service
public class EventDAOImpl extends AbstractDAOImpl<Event> implements EventDAO {

//	public static final String TABLE = "EVENT";
//
//	public static final String FLD_EVENT_TEMPLATE_ID = "EVENT_TEMPLATE_ID";
//	public static final String FLD_NOTE = "NOTE";
//	public static final String FLD_NAME = "NAME";
//	public static final String FLD_SHORT_NAME = "SHORT_NAME";
//	public static final String FLD_DOSE = "DOSE";
//	public static final String FLD_UNIT = "UNIT";
//	public static final String FLD_EVENT_DATE = "EVENT_DATE";
//
//	private static final List<String> SELECT_COLS;
//
//	private static final List<String> UPDATE_COLS;
//
//	static {
//		List<String> cols = new ArrayList<String>();
//		cols.add(FLD_ID);
//		cols.add(FLD_EVENT_TEMPLATE_ID);
//		cols.add(FLD_EVENT_DATE);
//		cols.add(FLD_NAME);
//		cols.add(FLD_SHORT_NAME);
//		cols.add(FLD_DOSE);
//		cols.add(FLD_UNIT);
//		cols.add(FLD_NOTE);
//
//		SELECT_COLS = Collections.unmodifiableList(cols);
//
//		cols = new ArrayList<String>();
//		cols.add(FLD_EVENT_TEMPLATE_ID);
//		cols.add(FLD_EVENT_DATE);
//		cols.add(FLD_NAME);
//		cols.add(FLD_SHORT_NAME);
//		cols.add(FLD_DOSE);
//		cols.add(FLD_UNIT);
//		cols.add(FLD_NOTE);
//		UPDATE_COLS = Collections.unmodifiableList(cols);
//	}

	public EventDAOImpl() {
		super(new EventTableImpl());
	}

//	@Override
//	public List<String> getOrderBy() {
//		
//		return Collections.singletonList(Fld);
//	}

	@Override
	protected boolean validateInsertObject(Event type) {
		boolean retVal = false;
		if (type != null) {
			if (type.getEventTemplateId() != null) {
				if (type.getName() != null) {
					if (StringUtils.isNotEmpty(type.getDose())) {
						if (StringUtils.isNotEmpty(type.getUnit())) {
							retVal = true;
						}
						
					}
				}

			}

		}
		return retVal;
	}

//	@Override
//	public String getInsertSQL(Event event) {
//		if (validateInsertObject(event)) {
//
//			StringBuffer sb = new StringBuffer();
//
//			sb.append(INSERT_INTO).append(TABLE).append(SPACE);
//
//			sb.append(LEFT_PARENTHIS);
//
//			sb.append(FLD_EVENT_TEMPLATE_ID).append(SEP);
//
//			sb.append(FLD_EVENT_DATE).append(SEP);
//
//			sb.append(FLD_NAME).append(SEP);
//			sb.append(FLD_SHORT_NAME).append(SEP);
//			sb.append(FLD_DOSE).append(SEP);
//			sb.append(FLD_UNIT).append(SEP);
//			sb.append(FLD_NOTE);
//			sb.append(RIGHT_PARENTHIS).append(SPACE);
//
//			sb.append(VALUES).append(SPACE);
//			sb.append(LEFT_PARENTHIS);
//			sb.append(BIND).append(FLD_EVENT_TEMPLATE_ID).append(SEP);
//			sb.append(BIND).append(FLD_EVENT_DATE).append(SEP);
//			sb.append(BIND).append(FLD_NAME).append(SEP);
//			sb.append(BIND).append(FLD_SHORT_NAME).append(SEP);
//			sb.append(BIND).append(FLD_DOSE).append(SEP);
//			sb.append(BIND).append(FLD_UNIT).append(SEP);
//			sb.append(BIND).append(FLD_NOTE);
//			sb.append(RIGHT_PARENTHIS).append(SPACE);
//
//			return sb.toString();
//		} else {
//			throw new EventDAOException("Insert received invalid objet=" + event);
//		}
//	}

//	@Override
//	public Map<String, Object> getInsertMappings(Event event) {
//		Map<String, Object> mappings = new TreeMap<String, Object>();
//		mappings.put(FLD_EVENT_TEMPLATE_ID, event.getEventTemplateId());
//
//		if (event.getEventTS() != null) {
//			mappings.put(FLD_EVENT_DATE, event.getEventTS());
//		}
//
//		mappings.put(FLD_NOTE, event.getNote());
//		mappings.put(FLD_NAME, event.getName());
//		mappings.put(FLD_SHORT_NAME, event.getShortName());
//		mappings.put(FLD_DOSE, event.getDose());
//		mappings.put(FLD_UNIT, event.getUnit());
//
//		return mappings;
//	}

//	@Override
//	public Map<String, Object> getUpdateMappings(Event type) {
//		Map<String, Object> insertMappings = this.getInsertMappings(type);
//		return insertMappings;
//	}

//	@Override
//	public RowMapper<Event> getRowMapper() {
//		return eventRowMapper;
//	}
//
//	private RowMapper<Event> eventRowMapper = new RowMapper<Event>() {
//
//		@Override
//		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Event eventTmpl = new Event();
//			eventTmpl.setId(rs.getInt(FLD_ID));
//			eventTmpl.setEventTemplateId(rs.getInt(FLD_EVENT_TEMPLATE_ID));
//			eventTmpl.setDose(rs.getString(FLD_DOSE));
//			eventTmpl.setUnit(rs.getString(FLD_UNIT));
//			eventTmpl.setName(rs.getString(FLD_NAME));
//			eventTmpl.setShortName(rs.getString(FLD_SHORT_NAME));
//			eventTmpl.setNote(rs.getString(FLD_NOTE));
//			eventTmpl.setEventTS(rs.getTimestamp(FLD_EVENT_DATE).toLocalDateTime());
//			return eventTmpl;
//		}
//
//	};

	@Override
	public void refreshCache() {
		// TODO Auto-generated method stub

	}

}
