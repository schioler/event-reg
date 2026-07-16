package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.criteria.MedicineEventTemplateCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.EventTemplateRowMapper;
import dk.schioler.event.base.dao.table.MedicineEventTemplateTable;
import dk.schioler.event.base.entity.EntityBase;
import dk.schioler.event.base.entity.MedicineEventTemplate;
import dk.schioler.event.base.entity.UNIT;

@Deprecated
@Service
public class EventTemplateTableImpl<T extends Ev> extends EntityBaseTableImpl<T> implements MedicineEventTemplateTable<MedicineEventTemplate> {

   public EventTemplateTableImpl() {
      super();
      insertColumns.add(FLD_EVENT_TYPE_ID);
      insertColumns.add(FLD_DOSE);
      insertColumns.add(FLD_UNIT);
      insertColumns.add(FLD_IS_FAVOURITE);
//      insertColumns.add(FLD_SORT_ORDER);

      selectColumns.add(FLD_EVENT_TYPE_ID);
      selectColumns.add(FLD_DOSE);
      selectColumns.add(FLD_UNIT);
      selectColumns.add(FLD_IS_FAVOURITE);
//      selectColumns.add(FLD_SORT_ORDER);

//      orderByColumns.add(FLD_SORT_ORDER);
   }

   @Override
   public String getTableName() {
      return TABLE;
   }

   @Override
   public RowMapper<MedicineEventTemplate> getRowMapper() {
      return new EventTemplateRowMapper();
   }

   @Override
   public Map<String, Object> getInsertMappings(MedicineEventTemplate type) {
      Map<String, Object> map = super.getInsertMappings(type);

      map.put(FLD_DESCRIPTION, type.getDescription());
      map.put(FLD_EVENT_TYPE_ID, type.getParentId());
      map.put(FLD_DOSE, type.getDose());
      map.put(FLD_UNIT, type.getUnit().toString());
      map.put(FLD_SORT_ORDER, type.getSortOrder());
      map.put(FLD_IS_FAVOURITE, type.isFavorite());

      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(MedicineEventTemplate type) {
      Map<String, Object> map = super.getUpdateMappings(type);

      map.put(FLD_EVENT_TYPE_ID, type.getParentId());
      map.put(FLD_DOSE, type.getDose());
      map.put(FLD_UNIT, type.getUnit().toString());
      map.put(FLD_SORT_ORDER, type.getSortOrder());
      map.put(FLD_IS_FAVOURITE, type.isFavorite());
      map.put(FLD_DESCRIPTION, type.getDescription());
      return map;
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> retList = super.addLevelSpecificCriteriaFrom(idCrit);
      if (idCrit != null) {
         MedicineEventTemplateCriteria templCrit = (MedicineEventTemplateCriteria) idCrit;

//         BigDecimal doseMin = templCrit.getDoseMin();
//         BigDecimal doseMax = templCrit.getDoseMax();
//         if (doseMin != null && doseMax != null) {
//            StringBuffer sql = createDoseCriteria(FLD_DOSE_MIN, FLD_DOSE_MAX, FLD_DOSE);
//            retList.add(sql);
//         }

         UNIT unit = templCrit.getUnit();
         if (unit != null) {
            StringBuffer unitC = createStringCriteria(FLD_UNIT);
            if (unitC != null) {
               retList.add(unitC);
            }
         }

         List<Integer> eventTypeIds = templCrit.getMedicineIds();
         if (eventTypeIds != null & eventTypeIds.size() > 0) {
            StringBuffer integerCriteria = createIntegerCriteria(FLD_EVENT_TYPE_ID, eventTypeIds);
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
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria != null) {
         MedicineEventTemplateCriteria templCrit = (MedicineEventTemplateCriteria) criteria;

         UNIT unit = templCrit.getUnit();
         if (unit != null) {
            map.put(FLD_UNIT, unit.toString().toUpperCase());
         }

//         BigDecimal doseMin = templCrit.getDoseMin();
//         BigDecimal doseMax = templCrit.getDoseMax();
//         if (doseMin != null && doseMax != null) {
//            map.put(FLD_DOSE_MIN, doseMin);
//            map.put(FLD_DOSE_MAX, doseMax);
//
//         }

         List<Integer> eventTypeIds = templCrit.getMedicineIds();
         if (eventTypeIds.size() > 0) {
            Map<String, Object> integerMappings = createIntegerMappings(FLD_EVENT_TYPE_ID, eventTypeIds);
            map.putAll(integerMappings);
         }

         Boolean favourite = templCrit.getFavourite();
         if (favourite != null) {
            map.put(FLD_IS_FAVOURITE, favourite);
         }

      }

      return map;
   }
}
