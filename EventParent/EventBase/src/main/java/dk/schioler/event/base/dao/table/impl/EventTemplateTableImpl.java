package dk.schioler.event.base.dao.table.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.EventTemplateRowMapper;
import dk.schioler.event.base.dao.table.EventTemplateTable;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.UNIT;

public class EventTemplateTableImpl extends AbstractSQLTableParentChild<EventTemplate> implements EventTemplateTable {

   public EventTemplateTableImpl() {
      super();
      insertColumns.add(FLD_EVENT_TYPE_ID);
      insertColumns.add(FLD_DOSE);
      insertColumns.add(FLD_UNIT);
      insertColumns.add(FLD_IS_FAVOURITE);
      insertColumns.add(FLD_SORT_ORDER);

      selectColumns.add(FLD_EVENT_TYPE_ID);
      selectColumns.add(FLD_DOSE);
      selectColumns.add(FLD_UNIT);
      selectColumns.add(FLD_IS_FAVOURITE);
      selectColumns.add(FLD_SORT_ORDER);

      orderByColumns.add(FLD_NAME);
   }

   @Override
   public String getTableName() {
      return TABLE;
   }

   @Override
   public RowMapper<EventTemplate> getRowMapper() {
      return new EventTemplateRowMapper();
   }

   @Override
   public Map<String, Object> getInsertMappings(EventTemplate type) {
      Map<String, Object> map = super.getInsertMappings(type);

      map.put(FLD_EVENT_TYPE_ID, type.getParentId());
      map.put(FLD_DOSE, type.getDose());
      map.put(FLD_UNIT, type.getUnit().toString());
      map.put(FLD_SORT_ORDER, type.getSortOrder());
      map.put(FLD_IS_FAVOURITE, type.isFavorite());

      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(EventTemplate type) {
      Map<String, Object> map = super.getUpdateMappings(type);

      map.put(FLD_EVENT_TYPE_ID, type.getParentId());
      map.put(FLD_DOSE, type.getDose());
      map.put(FLD_UNIT, type.getUnit().toString());
      map.put(FLD_SORT_ORDER, type.getSortOrder());
      map.put(FLD_IS_FAVOURITE, type.isFavorite());

      return map;
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      List<StringBuffer> retList = super.addLevelSpecificCriteriaFrom(idCrit);
      if (idCrit != null) {
         EventTemplateCriteria templCrit = (EventTemplateCriteria) idCrit;

         BigDecimal doseMin = templCrit.getDoseMin();
         BigDecimal doseMax = templCrit.getDoseMax();
         if (doseMin != null && doseMax != null) {
            StringBuffer sql = createDoseCriteria(FLD_DOSE_MIN, FLD_DOSE_MAX, FLD_DOSE);
            retList.add(sql);
         }

         UNIT unit = templCrit.getUnit();
         if (unit != null) {
            StringBuffer unitC = createStringCriteria(FLD_UNIT);
            if (unitC != null) {
               retList.add(unitC);
            }
         }

         List<Integer> eventTypeIds = templCrit.getEventTypeIds();
         if (eventTypeIds != null & eventTypeIds.size() > 0) {
            StringBuffer integerCriteria = createIntegerCriteria(eventTypeIds, FLD_EVENT_TYPE_ID);
            if (integerCriteria != null) {
               retList.add(integerCriteria);
            }
         }

       
         Boolean favourite = templCrit.getFavourite();
         if (favourite != null) {
            StringBuffer sql = createBooleanCriteria(FLD_IS_FAVOURITE);
            if (sql != null) {
               retList.add(sql);
            }
         }

      }
      return retList;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria != null) {
         EventTemplateCriteria templCrit = (EventTemplateCriteria) criteria;

         UNIT unit = templCrit.getUnit();
         if (unit != null) {
            map.put(FLD_UNIT, unit.toString().toUpperCase());
         }

         BigDecimal doseMin = templCrit.getDoseMin();
         BigDecimal doseMax = templCrit.getDoseMax();
         if (doseMin != null && doseMax != null) {
            map.put(FLD_DOSE_MIN, doseMin);
            map.put(FLD_DOSE_MAX, doseMax);

         }

         List<Integer> eventTypeIds = templCrit.getEventTypeIds();
         Map<String, Object> integerMappings = createIntegerMappings(FLD_EVENT_TYPE_ID, eventTypeIds);
         map.putAll(integerMappings);

         Boolean favourite = templCrit.getFavourite();
         if (favourite != null) {
            map.put(FLD_IS_FAVOURITE, favourite);
         }

      }

      return map;
   }
}
