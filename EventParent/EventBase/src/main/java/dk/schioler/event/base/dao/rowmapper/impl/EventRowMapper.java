package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.EventTableImpl;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.UNIT;

public class EventRowMapper extends EventTableImpl implements RowMapper<Event>  {

   @Override
   public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
      Event event = new Event();
      event.setId(rs.getInt(FLD_ID));
      event.setLoginId(rs.getInt(FLD_LOGIN_ID));
      event.setCreated(rs.getTimestamp(FLD_CREATED).toLocalDateTime());
      
      
      event.setName(rs.getString(FLD_NAME));
      event.setShortName(rs.getString(FLD_SHORT_NAME));
      event.setDescription(rs.getString(FLD_DESCRIPTION));

      event.setParentId(rs.getInt(FLD_EVENT_TEMPLATE_ID));

      event.setDose(rs.getBigDecimal(FLD_DOSE));
      event.setUnit(UNIT.getUnit(rs.getString(FLD_UNIT)));
      event.setNote(rs.getString(FLD_NOTE));
      event.setEventTS(rs.getTimestamp(FLD_EVENT_TS).toLocalDateTime());

      return event;

   }


}
