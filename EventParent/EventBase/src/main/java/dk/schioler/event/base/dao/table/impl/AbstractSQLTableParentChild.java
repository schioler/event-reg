package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.criteria.AbstractNameCriteria;
import dk.schioler.event.base.dao.table.BaseSQLTableParentChild;
import dk.schioler.event.base.entity.AbstractEntityParentChild;

public abstract class AbstractSQLTableParentChild<T extends AbstractEntityParentChild> extends AbstractSQLTableName<T> 
   implements BaseSQLTableParentChild<T> {
   
   
   public Map<String, Object> getInsertMappings(T type){
      Map<String, Object> mappings = super.getInsertMappings(type);
      return mappings;
      
   }
   public Map<String, Object> getUpdateMappings(T type){
      Map<String, Object> map = super.getUpdateMappings(type);
    return map;
   }

//   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractNameCriteria criteria) {
//      logger.debug("buildLevelSpecificRetrieveCriteriaFrom:" + criteria);
//      List<StringBuffer> critList = super.addLevelSpecificCriteriaFrom(criteria);

//      List<Integer> ids = criteria.getIds();
//      StringBuffer idCrit = buildIntegerCriteria(ids, FLD_ID);
//      critList.add(idCrit);
//
//      List<Integer> loginIds = criteria.getLoginIds();
//      StringBuffer loginIdCrit = buildIntegerCriteria(loginIds, FLD_LOGIN_ID);
//      critList.add(loginIdCrit);

//      List<StringBuffer> specificCriteria = addNameCriteria(criteria);
//      critList.addAll(specificCriteria);

//      return critList;
//   }
   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      logger.debug("addLevelSpecificCriteriaFrom: idCrit=" + idCrit);
      return super.addLevelSpecificCriteriaFrom(idCrit);
   }
   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      logger.debug("addLevelSpecificRetrieveMappings: criteria=" + criteria);
      return super.addLevelSpecificRetrieveMappings(criteria);
   }

   
}
