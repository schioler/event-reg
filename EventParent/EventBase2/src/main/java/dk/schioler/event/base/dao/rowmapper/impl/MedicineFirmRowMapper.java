package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.MedicineFirmTableImpl;
import dk.schioler.event.base.entity.MedicineFirm;
import dk.schioler.event.base.entity.impl.MedicineFirmImpl;

public class MedicineFirmRowMapper<T extends MedicineFirm> extends MedicineFirmTableImpl<T> implements RowMapper<T> {

//      return new RowMapper<T>() {

   @Override
   public T mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt(FLD_ID);
      int ownerId = rs.getInt(FLD_OWNER_ID);
      LocalDateTime localDateTime = rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime();
      
      MedicineFirm mf = new MedicineFirmImpl(id, ownerId, localDateTime);
      
      

      mf.setName(rs.getString(FLD_NAME));
      mf.setBrand(rs.getString(FLD_BRAND));

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
