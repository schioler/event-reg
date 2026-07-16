package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.entity.MedicineFirm;
import dk.schioler.event.base.entity.MedicineType;
import dk.schioler.event.base.entity.impl.MedicineFirmImpl;

public class EventTypeRowMapper<T extends MedicineType> extends EventType implements RowMapper<MedicineType> {

   @Override
   public MedicineType mapRow(ResultSet rs, int rowNum) throws SQLException {
      MedicineType mf = new MedicineTypeImpl();

      mf.setId(rs.getInt());
      mf.setOwnerId(rs.getInt(FLD_OWNER_ ID));
      mf.setCreatedTS(rs.getTimestamp(FLD_CREATED).toLocalDateTime());
      
      mf.setName(rs.getString(FLD_NAME));
      mf.setBrand(rs.getString(FLD_SHORT_NAME));
            
      return mf;
   }

}
