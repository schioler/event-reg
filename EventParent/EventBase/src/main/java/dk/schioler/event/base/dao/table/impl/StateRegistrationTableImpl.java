package dk.schioler.event.base.dao.table.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.criteria.StateRegistrationCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.StateRegistrationRowMapper;
import dk.schioler.event.base.dao.table.StateRegistrationTable;
import dk.schioler.event.base.entity.StateRegistration;

public class StateRegistrationTableImpl extends AbstractSQLTableId<StateRegistration> implements StateRegistrationTable {

   public StateRegistrationTableImpl() {
      super();
      insertColumns.add(FLD_STATE_REGISTRATION_TS);

      selectColumns.add(FLD_STATE_REGISTRATION_TS);

      orderByColumns.add(FLD_ID);
   }

   @Override
   public String getTableName() {
      return TABLE;
   }

   @Override
   public Map<String, Object> getInsertMappings(StateRegistration type) {
      Map<String, Object> map = super.getInsertMappings(type);

      map.put(FLD_STATE_REGISTRATION_TS, type.getRegistrationTS());

      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(StateRegistration type) {
      Map<String, Object> map = super.getUpdateMappings(type);

      if (type.getRegistrationTS() != null) {
         map.put(FLD_STATE_REGISTRATION_TS, type.getRegistrationTS());
      }

      return map;
   }

   @Override
   public RowMapper<StateRegistration> getRowMapper() {
      return new StateRegistrationRowMapper();
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      List<StringBuffer> sql = super.addLevelSpecificCriteriaFrom(idCrit);
      if (idCrit != null) {
         StateRegistrationCriteria regCrit = (StateRegistrationCriteria) idCrit;
         
         LocalDateTime registrationTSStart = regCrit.getRegistrationTSStart();
         LocalDateTime registrationTSEnd = regCrit.getRegistrationTSEnd();
         if (registrationTSStart != null && registrationTSEnd != null) {
            StringBuffer registrationTSCriteria = createLocalDateTimeCriteria(START_DATE, END_DATE, FLD_STATE_REGISTRATION_TS);
         
            if (registrationTSCriteria != null) {
               sql.add(registrationTSCriteria);
            }
         }
      }

      return sql;

   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);
      if (criteria != null) {
         StateRegistrationCriteria srCrit = (StateRegistrationCriteria) criteria;
         LocalDateTime registrationTSStart = srCrit.getRegistrationTSStart();
         LocalDateTime registrationTSEnd = srCrit.getRegistrationTSEnd();
         if (registrationTSStart != null && registrationTSEnd != null) {
            map.put(START_DATE, registrationTSStart);
            map.put(END_DATE, registrationTSEnd);
         }
      }

      return map;
   }

}
