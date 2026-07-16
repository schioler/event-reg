package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.MedicineTableImpl;
import dk.schioler.event.base.entity.Medicine;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.base.entity.impl.MedicineImpl;

public class MedicineRowMapper<T extends Medicine> extends MedicineTableImpl<T> implements RowMapper<T> {

   
   @Override
   public T mapRow(ResultSet rs, int rowNum) throws SQLException {
      int id = rs.getInt(FLD_ID);
      int ownerId = rs.getInt(FLD_OWNER_ID);
      LocalDateTime localDateTime = rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime();
      
      
      Medicine mf = new MedicineImpl(id, ownerId, localDateTime);

//      mf.setId(rs.getInt(FLD_ID));
//      mf.setOwnerId(rs.getInt(FLD_OWNER_ID));
//      mf.setCreatedTS(rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime());
//      
      int medTypeId = rs.getInt(FLD_MEDICINE_TYPE_ID);
      String name = rs.getString(FLD_NAME);
      String unit = rs.getString(FLD_UNIT);
      UNIT u = UNIT.getUnitFromString(unit);
      
      String dose = rs.getString(FLD_DOSE);
      
      mf.setMedicineTypeId(medTypeId);
      mf.setName(name);
      mf.setUnit(u);
      mf.setDose(dose);
         
      return (T) mf;
   }

//   @Override
//   public T mapRow(ResultSet rs, int rowNum) throws SQLException {
//      MedicineFirm mf = new MedicineFirmImpl();
//      
//      mf.setId(rs.getInt(FLD_ID));
//      mf.setOwnerId(rs.getInt(FLD_OWNER_ID));
//      mf.setCreatedTS(rs.getTimestamp(FLD_CREATED_TS).toLocalDateTime());
//
//      mf.setName(rs.getString(FLD_NAME));
//      mf.setBrand(rs.getString(FLD_BRAND));
//
//      return (T) mf;
//   }
////      };
//
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
