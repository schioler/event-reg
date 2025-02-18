package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.criteria.AbstractNameCriteria;
import dk.schioler.event.base.dao.table.BaseSQLTableName;
import dk.schioler.event.base.entity.AbstractEntityName;

public abstract class AbstractSQLTableName<T extends AbstractEntityName> extends AbstractSQLTableId<T> implements BaseSQLTableName<T> {

   public AbstractSQLTableName() {
      super();
      insertColumns.add(FLD_NAME);
      insertColumns.add(FLD_SHORT_NAME);
      insertColumns.add(FLD_DESCRIPTION);

      selectColumns.add(FLD_NAME);
      selectColumns.add(FLD_SHORT_NAME);
      selectColumns.add(FLD_DESCRIPTION);

   }

   @Override
   public Map<String, Object> getInsertMappings(T type) {
      Map<String, Object> map = super.getInsertMappings(type);
      if (type.getDescription() != null) {
         map.put(FLD_DESCRIPTION, type.getDescription());
      }

      if (type.getName() != null) {
         map.put(FLD_NAME, type.getName());
      }
      if (type.getShortName() != null) {
         map.put(FLD_SHORT_NAME, type.getShortName());
      }

      return map;

   }

   @Override
   public Map<String, Object> getUpdateMappings(T type) {
      Map<String, Object> map = super.getUpdateMappings(type);

      if (type.getName() != null) {
         map.put(FLD_NAME, type.getName());
      }
      if (type.getShortName() != null) {
         map.put(FLD_SHORT_NAME, type.getShortName());
      }
      if (type.getDescription() != null) {
         map.put(FLD_DESCRIPTION, type.getDescription());
      }
      return map;
   }

   // **********************************************************************
//   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractNameCriteria criteria) {
//      logger.debug("buildLevelSpecificRetrieveCriteriaFrom:" + criteria);
//      List<StringBuffer> critList = super.addLevelSpecificCriteriaFrom(criteria);
//
////      List<Integer> ids = criteria.getIds();
////      StringBuffer idCrit = buildIntegerCriteria(ids, FLD_ID);
////      critList.add(idCrit);
////
////      List<Integer> loginIds = criteria.getLoginIds();
////      StringBuffer loginIdCrit = buildIntegerCriteria(loginIds, FLD_LOGIN_ID);
////      critList.add(loginIdCrit);
//
////      List<StringBuffer> specificCriteria = addNameCriteria(criteria);
////      critList.addAll(specificCriteria);
//
//      logger.debug("buildRetrieveSQLFromCriteriaInstance: critList=" + critList);
//      return critList;
//   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      logger.debug("addLevelSpecificCriteriaFrom:" + idCrit);
      List<StringBuffer> critList = super.addLevelSpecificCriteriaFrom(idCrit);

      AbstractNameCriteria criteria = (AbstractNameCriteria) idCrit;

      String name = criteria.getName();
      if (StringUtils.isNotEmpty(name)) {
         StringBuffer nameCrit = createStringCriteria(FLD_NAME);
         critList.add(nameCrit);
      }

      String description = criteria.getDescription();
      if (StringUtils.isNotEmpty(description)) {
         StringBuffer descCrit = createStringCriteria(FLD_DESCRIPTION);
         critList.add(descCrit);
      }

      String shortName = criteria.getShortName();
      if (StringUtils.isNotEmpty(shortName)) {
         StringBuffer shortCrit = createStringCriteria(FLD_SHORT_NAME);
         critList.add(shortCrit);
      }

      logger.debug("addLevelSpecificCriteriaFrom: critList=" + critList);
      return critList;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      logger.debug("addLevelSpecificRetrieveMappings.NAME: criterua=" + criteria);
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      AbstractNameCriteria crit = (AbstractNameCriteria) criteria;

      if (crit.getName() != null) {
         map.put(FLD_NAME, crit.getName());
      }

      if (crit.getShortName() != null) {
         map.put(FLD_SHORT_NAME, crit.getShortName());
      }

      if (crit.getDescription() != null) {
         map.put(FLD_DESCRIPTION, crit.getDescription());
      }

      return map;
   }

}
