package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.StateRegistrationTableImpl;
import dk.schioler.event.base.entity.StateRegistration;

public class StateRegistrationRowMapper extends StateRegistrationTableImpl implements RowMapper<StateRegistration>  {

   @Override
   public StateRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
      StateRegistration eventType = new StateRegistration();

      eventType.setId(rs.getInt(FLD_ID));
      eventType.setLoginId(rs.getInt(FLD_LOGIN_ID));
      eventType.setCreated(rs.getTimestamp(FLD_CREATED).toLocalDateTime());
      
      eventType.setRegistrationTS(rs.getTimestamp(FLD_STATE_REGISTRATION_TS).toLocalDateTime());
      return eventType;
   }

//   @Override
//   public EventType mapRow(ResultSet rs, int rowNum) throws SQLException {
//      Event event = new Event();
//      event.setId(rs.getInt(FLD_ID));
//      event.setLoginId(rs.getInt(FLD_LOGIN_ID));
//      event.setName(rs.getString(FLD_NAME));
//      event.setShortName(rs.getString(FLD_SHORT_NAME));
//      event.setDescription(rs.getString(FLD_DESCRIPTION));
//
//      event.setParentId(rs.getInt(FLD_EVENT_TEMPLATE_ID));
//
//      event.setDose(rs.getString(FLD_DOSE));
//      event.setUnit(rs.getString(FLD_UNIT));
//      event.setNote(rs.getString(FLD_NOTE));
//      event.setEventTS(rs.getTimestamp(FLD_EVENT_TS).toLocalDateTime());
//
//      return event;
//
//   }


}
