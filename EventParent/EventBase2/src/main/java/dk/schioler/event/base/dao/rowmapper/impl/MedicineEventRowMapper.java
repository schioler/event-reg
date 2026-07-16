package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.MedicineEventTableImpl;
import dk.schioler.event.base.entity.MedicineEvent;
import dk.schioler.event.base.entity.TREE_TYPE;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.base.entity.impl.MedicineEventImpl;

public class MedicineEventRowMapper<T extends MedicineEvent> extends MedicineEventTableImpl<T> implements RowMapper<T> {

//      return new RowMapper<T>() {

   @Override
   public T mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt(FLD_ID);
      int ownerId = rs.getInt(FLD_OWNER_ID);
      LocalDateTime localDateTime = rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime();
      
      MedicineEvent mf = new MedicineEventImpl(id, ownerId, localDateTime, rs.getString(FLD_NAME), TREE_TYPE.PARENT_NAME);
      
      

      mf.setEventId(rs.getInt(FLD_EVENT_ID));
      String string = rs.getString(FLD_EVENT_TYPE);
      
//      mf.setEventType(EVENT_TYPE.getEventTypeFromString(string));
      
      mf.setMedicineId(rs.getInt(FLD_MEDICINE_ID));
      mf.setMedicineEventTemplateId(rs.getInt(FLD_MEDICINE_EVENT_TEMPLATE_ID));
      
      String u = rs.getString(FLD_UNIT);
      mf.setUnit(UNIT.getUnitFromString(u));
      mf.setDose(rs.getString(FLD_DOSE));
//      mf.setName(rs.getString(FLD_NAME));
      mf.setNote(rs.getString(FLD_NOTE));

      return  (T) mf;
   }


}
