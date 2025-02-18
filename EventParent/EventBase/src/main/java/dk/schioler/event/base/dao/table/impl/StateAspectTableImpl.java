package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.StateAspectRowMapper;
import dk.schioler.event.base.dao.table.StateAspectTable;
import dk.schioler.event.base.entity.StateAspect;

public class StateAspectTableImpl extends AbstractSQLTableName<StateAspect> implements StateAspectTable {

   public StateAspectTableImpl() {
      super();

      orderByColumns.add(FLD_ID);
      orderByColumns.add(FLD_NAME);
   }

   @Override
   public Map<String, Object> getInsertMappings(StateAspect type) {
      Map<String, Object> map = super.getInsertMappings(type);

      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(StateAspect type) {
      Map<String, Object> map = super.getUpdateMappings(type);
//      map.put(FLD_STATE_ASPECT_TS, type.getAspectTS());
      return map;
   }

   @Override
   public String getTableName() {
      return TABLE;
   }

   @Override
   public RowMapper<StateAspect> getRowMapper() {

      return new StateAspectRowMapper();
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      List<StringBuffer> crit = super.addLevelSpecificCriteriaFrom(idCrit);
      logger.debug("StateAspect.addLevelSpecificCriteriaFrom: crit=" + crit);
      return crit;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      logger.debug("StateAspect.addLevelSpecificRetrieveMappings.StateAspect" + criteria);
      return super.addLevelSpecificRetrieveMappings(criteria);
   }

}
