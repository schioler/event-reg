package dk.schioler.event.base.dao.rowmapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.table.impl.EventTemplateTableImpl;
import dk.schioler.event.base.entity.MedicineEventTemplate;
import dk.schioler.event.base.entity.UNIT;

public class EventTemplateRowMapper extends EventTemplateTableImpl implements RowMapper<MedicineEventTemplate> {

   @Override
   public MedicineEventTemplate mapRow(ResultSet rs, int rowNum) throws SQLException {
      MedicineEventTemplate eventTemplate = new MedicineEventTemplate();
      eventTemplate.addEventId(rs.getInt(FLD_ID));
      eventTemplate.setLoginId(rs.getInt(FLD_OWNER_ID));
      eventTemplate.setCreatedTS(rs.getTimestamp(FLD_CREATED).toLocalDateTime());

      eventTemplate.setName(rs.getString(FLD_NAME));
      eventTemplate.setShortName(rs.getString(FLD_SHORT_NAME));
      eventTemplate.setDescription(rs.getString(FLD_DESCRIPTION));

      eventTemplate.setParentId(rs.getInt(FLD_EVENT_TYPE_ID));

      eventTemplate.setDose(rs.getString(FLD_DOSE));
      eventTemplate.setUnit(UNIT.getUnitFromString(rs.getString(FLD_UNIT)));
      eventTemplate.setFavorite(rs.getBoolean(FLD_IS_FAVOURITE));
      eventTemplate.setSortOrder(rs.getInt(FLD_SORT_ORDER));
            
      return eventTemplate;

   }

}
