package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.entity.Event;

@Service
public class EventDAOImpl extends AbstractDAOImpl<Event> implements EventDAO {
	public static final String TABLE = "EVENT";

	public static final String FLD_ID = COL_ID;
	public static final String FLD_EVENT_TEMPLATE_ID = "EVENT_TEMPLATE_ID";
	public static final String FLD_NOTE = "NOTE";
	public static final String FLD_NAME = "NAME";
	public static final String FLD_SHORT_NAME = "SHORT_NAME";
	public static final String FLD_DOSE = "DOSE";
	public static final String FLD_UNIT = "UNIT";
	public static final String FLD_EVENT_DATE = "EVENT_DATE";

	private static final List<String> EVENT_COLS;

	static {
		List<String> cols = new ArrayList<String>();
		cols.add(FLD_ID);
		cols.add(FLD_EVENT_TEMPLATE_ID);
		cols.add(FLD_EVENT_DATE);
		cols.add(FLD_NAME);
		cols.add(FLD_SHORT_NAME);
		cols.add(FLD_DOSE);
		cols.add(FLD_UNIT);
		cols.add(FLD_NOTE);

		EVENT_COLS = Collections.unmodifiableList(cols);
	}

	public EventDAOImpl() {

	}

	
	
	@Override
	public String getOrderBy() {
		return FLD_NAME;
	}



	@Override
	public String getInsertSQL(Event event) {
		boolean useEventDate = event != null && event.getEventTS() != null;
		StringBuffer sb = new StringBuffer();

		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
		sb.append(LEFT_PARENTHIS);
		sb.append(FLD_EVENT_TEMPLATE_ID).append(SEP);
		if (useEventDate) {
			sb.append(FLD_EVENT_DATE).append(SEP);
		}
		sb.append(FLD_NAME).append(SEP);
		sb.append(FLD_SHORT_NAME).append(SEP);
		sb.append(FLD_DOSE).append(SEP);
		sb.append(FLD_UNIT).append(SEP);
		sb.append(FLD_NOTE);
		sb.append(RIGHT_PARENTHIS).append(SPACE);
		sb.append(VALUES).append(SPACE);
		sb.append(LEFT_PARENTHIS);
		sb.append(BIND).append(FLD_EVENT_TEMPLATE_ID).append(SEP);
		if (useEventDate) {
			sb.append(BIND).append(FLD_EVENT_DATE).append(SEP);
		}
		sb.append(BIND).append(FLD_NAME).append(SEP);
		sb.append(BIND).append(FLD_SHORT_NAME).append(SEP);
		sb.append(BIND).append(FLD_DOSE).append(SEP);
		sb.append(BIND).append(FLD_UNIT).append(SEP);
		sb.append(BIND).append(FLD_NOTE);
		sb.append(RIGHT_PARENTHIS).append(SPACE);

		return sb.toString();
	}

	@Override
	public Map<String, Object> getInsertMappings(Event event) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_EVENT_TEMPLATE_ID, event.getEventTemplateId());

		if (event.getEventTS() != null) {
			mappings.put(FLD_EVENT_DATE, event.getEventTS());
		}

		mappings.put(FLD_NOTE, event.getNote());
		mappings.put(FLD_NAME, event.getName());
		mappings.put(FLD_SHORT_NAME, event.getShortName());
		mappings.put(FLD_DOSE, event.getDose());
		mappings.put(FLD_UNIT, event.getUnit());

		return mappings;
	}

	@Override
	public String getUpdateSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getUpdateMappings(Event type) {
		// TODO Auto-generated method stub
		return null;
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
			eventTmpl.setEventTemplateId(rs.getInt(FLD_EVENT_TEMPLATE_ID));
			eventTmpl.setDose(rs.getString(FLD_DOSE));
			eventTmpl.setUnit(rs.getString(FLD_UNIT));
			eventTmpl.setName(rs.getString(FLD_NAME));
			eventTmpl.setShortName(rs.getString(FLD_SHORT_NAME));
			eventTmpl.setNote(rs.getString(FLD_NOTE));
			eventTmpl.setEventTS(rs.getTimestamp(FLD_EVENT_DATE).toLocalDateTime());
			return eventTmpl;
		}

	};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public List<String> getSelectColumns() {
		return EVENT_COLS;
	}


	@Override
	public void refreshCache() {
		// TODO Auto-generated method stub
		
	}

	
}
