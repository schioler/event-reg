package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.MedicineTypeTableImpl;
import dk.schioler.event.base.entity.MedicineType;
import dk.schioler.event.base.entity.impl.MedicineTypeImpl;

public class MedicineTypeRowMapper<T extends MedicineType> extends MedicineTypeTableImpl<T> implements RowMapper<T> {
  
   @Override
   public T mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt(FLD_ID);
      int ownerId = rs.getInt(FLD_OWNER_ID);
      LocalDateTime localDateTime = rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime();
     
      
      MedicineType mf = new MedicineTypeImpl(id, ownerId, localDateTime);
//
//      mf.setId(rs.getInt(FLD_ID));
//      mf.setOwnerId(rs.getInt(FLD_OWNER_ID));
//      mf.setCreatedTS(rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime());
      
  
      
      mf.setMedicineFirmId(rs.getInt(FLD_MEDICINE_FIRM_ID));
      mf.setMedicineType(rs.getString(FLD_MEDICINE_TYPE));
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
