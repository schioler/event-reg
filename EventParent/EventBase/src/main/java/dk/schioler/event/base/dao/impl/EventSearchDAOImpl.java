package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.EventSearchDAO;
import dk.schioler.event.base.entity.Event;

@Service
public class EventSearchDAOImpl implements EventSearchDAO {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		logger.debug("setDataSource:" + jdbcTemplate);
	}

	public EventSearchDAOImpl() {

	}

	@Override
	public List<Event> searchEvents(LocalDateTime startTime, LocalDateTime endTime, List<Integer> templateIds, Integer loginId)
			throws EventDAOException {
		logger.debug("searchEvents: startTime=" + startTime + ", endTime=" + endTime + " templateIds=" + templateIds);

//		StringBuffer sb = new StringBuffer();
//		
//		sb.append(SELECT).append(SPACE);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_ID).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_EVENT_TEMPLATE_ID).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_EVENT_DATE).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_NAME).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_DOSE).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_UNIT).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_NOTE).append(SPACE);
//		sb.append(FROM).append(SPACE);
//		sb.append(EventDAOImpl.TABLE).append(SPACE);
//		sb.append(WHERE).append(SPACE);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_EVENT_DATE).append(SPACE);
//		sb.append(BETWEEN).append(SPACE);
//		sb.append(BIND).append(START_DATE);
//		sb.append(SPACE).append(AND).append(SPACE);
//		sb.append(BIND).append(END_DATE).append(SPACE);
//		if (templateIds != null && templateIds.size() > 0) {
//			sb.append(AND).append(SPACE);
//			sb.append(EventDAOImpl.FLD_EVENT_TEMPLATE_ID).append(SPACE);
//			sb.append(IN).append(LEFT_PARENTHIS);
//			String x = "";
//			String ids = "";
//			for (Integer integer : templateIds) {
//				ids = ids + x + integer;
//				x = ",";
//			}
//			sb.append(ids).append(RIGHT_PARENTHIS);
//		}
//
//		logger.debug("sql=" + sb.toString());
//
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//
//		ZonedDateTime zdt = ZonedDateTime.of(startTime, ZoneId.systemDefault());
//		long startMilli = zdt.toInstant().toEpochMilli();
//		Date sd = new Date(startMilli);
//
//		ZonedDateTime eZdt = ZonedDateTime.of(endTime, ZoneId.systemDefault());
//		long endMilli = eZdt.toInstant().toEpochMilli();
//		Date ed = new Date(endMilli);
//
//		paramMap.put(START_DATE, sd);
//		paramMap.put(END_DATE, ed);
//		logger.debug("BindVars:" + paramMap.toString());
//		try {
//
//			List<Event> query = jdbcTemplate.query(sb.toString(), paramMap, eventRowMapper);
//			return query;
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
		return null;
	}

	private RowMapper<Event> eventRowMapper = new RowMapper<Event>() {

		@Override
		public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
			Event event = new Event();
			event.setId(rs.getInt(1));
			event.setParentId(rs.getInt(2));
			event.setEventTS(rs.getTimestamp(3).toLocalDateTime());
			event.setName(rs.getString(4));
			event.setDose(rs.getString(5));
			event.setUnit(rs.getString(6));
			event.setNote(rs.getString(7));
			logger.debug("row=" + event);
			return event;
		}

	};

//	private StringBuffer buildSelectEventTemplateStartString() {
//		StringBuffer sb = new StringBuffer();
//
//		sb.append(SELECT).append(SPACE);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_ID).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_EVENT_TEMPLATE_ID).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_NAME).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_DOSE).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_UNIT).append(SEP);
//		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_NOTE).append(SPACE);
////		sb.append(EventDAOImpl.TABLE).append(DOT).append(EventDAOImpl.FLD_DESCRIPTION).append(SPACE);
//		sb.append(FROM).append(SPACE);
//		sb.append(EventDAOImpl.TABLE).append(SPACE);
//		return sb;
//	}
//
//	@Override
//	public List<EventTemplate> lookupEventTemplates() throws EventDAOException {
//		StringBuffer sql = buildSelectEventTemplateStartString();
//		logger.debug("sql=" + sql);
//
//		List<EventTemplate> query = jdbcTemplate.query(sql.toString(), eventTemplateRowMapper);
//		return query;
//
//	}

//	@Override
//	public List<EventTemplate> lookupEventTemplates(Integer eventTypeId) throws EventDAOException {
//		StringBuffer sb = buildSelectEventTemplateStartString();
//		sb.append(WHERE).append(SPACE);
//		sb.append(EventTemplateDAOImpl.FLD_EVENT_TYPE_ID).append(EQ).append(BIND).append(EventTemplateDAOImpl.FLD_EVENT_TYPE_ID);
//
//		logger.debug("sql=" + sb.toString());
//		MapSqlParameterSource pSrc = new MapSqlParameterSource();
//		pSrc.addValue(EventTemplateDAOImpl.FLD_EVENT_TYPE_ID, eventTypeId);
//		
//		List<EventTemplate> query = jdbcTemplate.query(sb.toString(), pSrc,);
//		return query;
//
//	}

}
