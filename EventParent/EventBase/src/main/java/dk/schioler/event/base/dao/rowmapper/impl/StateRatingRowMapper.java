package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.StateRatingTableImpl;
import dk.schioler.event.base.entity.StateRating;

public class StateRatingRowMapper extends StateRatingTableImpl implements RowMapper<StateRating>  {

   @Override
   public StateRating mapRow(ResultSet rs, int rowNum) throws SQLException {
      StateRating stateRating = new StateRating();

      stateRating.setId(rs.getInt(FLD_ID));
      stateRating.setLoginId(rs.getInt(FLD_LOGIN_ID));
      stateRating.setCreated(rs.getTimestamp(FLD_CREATED).toLocalDateTime());
      
      stateRating.setStateAspectId(rs.getInt(FLD_STATE_ASPECT_ID));
      stateRating.setStateRegistrationId(rs.getInt(FLD_STATE_REGISTRATION_ID));
      stateRating.setRating(rs.getInt(FLD_RATING));

      return stateRating;
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
