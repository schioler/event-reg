package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.EventCategoryTableImpl;
import dk.schioler.event.base.entity.EventCategory;
import dk.schioler.event.base.entity.impl.EventCategoryImpl;

public class EventCategoryRowMapper<T extends EventCategory> extends EventCategoryTableImpl<T> implements RowMapper<T> {

//      return new RowMapper<T>() {

   @SuppressWarnings("unchecked")
   @Override
   public T mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt(FLD_ID);
      int ownerId = rs.getInt(FLD_OWNER_ID);
      LocalDateTime localDateTime = rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime();
     
      EventCategory mf = new EventCategoryImpl(id, ownerId,localDateTime);
      
//      mf.setId(rs.getInt(FLD_ID));
//      mf.setOwnerId(rs.getInt(FLD_OWNER_ID));
//      mf.setCreatedTS(rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime());

 
      mf.setName(rs.getString(FLD_NAME));
      mf.setDescription(rs.getString(FLD_DESCRIPTION));

      return (T) mf;
   }
//      };

//   @Override
//   public MedicineFirm mapRow(ResultSet rs, int rowNum) throws SQLException {
//      
//      MedicineFirm medFirm = new MedicineFirmImpl();
//      medFirm.setId(rs.getInt(FLD_ID));
//      
//      return medFirm;
//
//   }

}
