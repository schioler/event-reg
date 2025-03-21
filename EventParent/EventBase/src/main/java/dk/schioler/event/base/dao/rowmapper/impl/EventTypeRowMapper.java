package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.EventTypeTableImpl;
import dk.schioler.event.base.entity.EventType;

public class EventTypeRowMapper extends EventTypeTableImpl implements RowMapper<EventType>  {

   @Override
   public EventType mapRow(ResultSet rs, int rowNum) throws SQLException {
      EventType eventType = new EventType();

      eventType.setId(rs.getInt(FLD_ID));
      eventType.setLoginId(rs.getInt(FLD_LOGIN_ID));
      eventType.setCreated(rs.getTimestamp(FLD_CREATED).toLocalDateTime());
      
      eventType.setName(rs.getString(FLD_NAME));
      eventType.setShortName(rs.getString(FLD_SHORT_NAME));
      eventType.setDescription(rs.getString(FLD_DESCRIPTION));
      
      return eventType;
   }




}
