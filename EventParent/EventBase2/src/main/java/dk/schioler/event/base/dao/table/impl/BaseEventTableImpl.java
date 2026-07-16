package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.BaseEventCriteria;
import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.table.BaseEventTable;
import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.EntityBase;

@Service
public abstract class BaseEventTableImpl<T extends BaseEvent> extends EntityBaseTableImpl<EntityBase> implements BaseEventTable<T> {

   public BaseEventTableImpl() {
      super();
      
      insertColumns.add(FLD_NAME);
      insertColumns.add(FLD_EVENT_CATEGORY_ID);
      insertColumns.add(FLD_EVENT_ID);
      insertColumns.add(FLD_EVENT_TYPE);
      

      selectColumns.add(FLD_NAME);
      selectColumns.add(FLD_EVENT_CATEGORY_ID);
      selectColumns.add(FLD_EVENT_ID);
      selectColumns.add(FLD_EVENT_TYPE);

   }

   @Override
   public Map<String, Object> getInsertMappings(EntityBase type) {
      Map<String, Object> map = super.getInsertMappings(type);
      BaseEvent be = (BaseEvent) type;

      Integer eventCategoryId = be.getEventCategoryId();
      if (eventCategoryId != null) {
         map.put(FLD_EVENT_CATEGORY_ID, be.getEventCategoryId());
      }

      Integer eventId = be.getEventId();
      if (eventId != null) {
         map.put(FLD_EVENT_ID, be.getEventId());
      }

      String name = be.getName();
      if (StringUtils.isNotEmpty(name)) {
         map.put(FLD_NAME, name);
      }

      EVENT_TYPE t = be.getEventType();
      if (t != null) {
         map.put(FLD_EVENT_TYPE, EVENT_TYPE.eventTypeAsString(t));
      }
      return map;

   }

   @Override
   public Map<String, Object> getUpdateMappings(EntityBase entityType) {
      Map<String, Object> map = super.getUpdateMappings(entityType);
      BaseEvent be = (BaseEvent) entityType;
      Integer eventCategoryId = be.getEventCategoryId();
      if (eventCategoryId != null) {
         map.put(FLD_EVENT_CATEGORY_ID, be.getEventCategoryId());
      }

      Integer eventId = be.getEventId();
      if (eventId != null) {
         map.put(FLD_EVENT_ID, be.getEventId());
      }

      String name = be.getName();
      if (StringUtils.isNotEmpty(name)) {
         map.put(FLD_NAME, name);
      }

      EVENT_TYPE t = be.getEventType();
      if (t != null) {
         map.put(FLD_EVENT_TYPE, t);
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
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      logger.debug("addLevelSpecificCriteriaFrom:" + idCrit);
      List<StringBuffer> critList = super.addLevelSpecificCriteriaFrom(idCrit);

      if (idCrit != null) {

         BaseEventCriteria criteria = (BaseEventCriteria) idCrit;

         String name = criteria.getName();
         if (StringUtils.isNotEmpty(name)) {
            StringBuffer nameCrit = createStringCriteria(FLD_NAME);
            critList.add(nameCrit);
         }

         List<String> t = criteria.getEventTypes();
         if (t != null && !t.isEmpty()) {
            StringBuffer nameCrit = createStringCriteria(FLD_NAME);
            critList.add(nameCrit);
         }

         List<Integer> eventIds = criteria.getEventIds();
         if (eventIds != null && !eventIds.isEmpty()) {
            StringBuffer integerCriteria = createIntegerCriteria(FLD_EVENT_ID, eventIds);
            critList.add(integerCriteria);
         }

         List<Integer> eCids = criteria.getEventCategoryIds();
         if (eCids != null && !eCids.isEmpty()) {
            StringBuffer integerCriteria = createIntegerCriteria(FLD_EVENT_CATEGORY_ID, eCids);
            critList.add(integerCriteria);
         }

      }
      logger.debug("addLevelSpecificCriteriaFrom: critList=" + critList);
      return critList;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      logger.debug("addLevelSpecificRetrieveMappings.NAME: criteria=" + criteria);

      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria != null) {

         BaseEventCriteria crit = (BaseEventCriteria) criteria;

         String name = crit.getName();
         List<Integer> eid = crit.getEventIds();
         List<Integer> eCIds = crit.getEventCategoryIds();
         List<String> types = crit.getEventTypes();

         if (StringUtils.isNotBlank(name)) {
            map.put(FLD_NAME, name);
         }

         if (eid != null && !eid.isEmpty()) {
            map.put(FLD_EVENT_ID, eid);
         }

         if (eCIds != null && !eCIds.isEmpty()) {
            map.put(FLD_EVENT_CATEGORY_ID, eCIds);
         }
         
         if (types != null && !types.isEmpty()) {
            map.put(FLD_EVENT_TYPE, types);
         }

      }

      return map;
   }

}
