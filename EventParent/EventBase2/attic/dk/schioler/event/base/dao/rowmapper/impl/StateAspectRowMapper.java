package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.StateAspectTableImpl;
import dk.schioler.event.base.entity.MedicineEvent;

public class StateAspectRowMapper extends StateAspectTableImpl implements RowMapper<MedicineEvent>  {

   @Override
   public MedicineEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
      MedicineEvent stateAspect = new MedicineEvent();

      stateAspect.addEventId(rs.getInt(FLD_ID));
      stateAspect.setLoginId(rs.getInt(FLD_OWNER_ID));
      stateAspect.setCreatedTS(rs.getTimestamp(FLD_CREATED).toLocalDateTime());
      
      stateAspect.setName(rs.getString(FLD_NAME));
      stateAspect.setDescription(rs.getString(FLD_SHORT_NAME));
      stateAspect.setDescription(rs.getString(FLD_DESCRIPTION));

      return stateAspect;
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
