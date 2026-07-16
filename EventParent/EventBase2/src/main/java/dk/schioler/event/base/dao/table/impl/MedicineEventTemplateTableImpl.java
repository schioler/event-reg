package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.criteria.MedicineEventTemplateCriteria;
import dk.schioler.event.base.dao.table.MedicineEventTemplateTable;
import dk.schioler.event.base.entity.MedicineEventTemplate;

@Service
public class MedicineEventTemplateTableImpl<T extends MedicineEventTemplate> extends EntityBaseTableImpl<T> implements MedicineEventTemplateTable<T> {

   public MedicineEventTemplateTableImpl() {
      super();

      insertColumns.add(FLD_DOSE);
      insertColumns.add(FLD_UNIT);
      insertColumns.add(FLD_MEDICINE_ID);
      insertColumns.add(FLD_MEDICINE_NAME);
      insertColumns.add(FLD_IS_FAVOURITE);

      selectColumns.add(FLD_DOSE);
      selectColumns.add(FLD_UNIT);
      selectColumns.add(FLD_MEDICINE_ID);
      selectColumns.add(FLD_MEDICINE_NAME);
      selectColumns.add(FLD_IS_FAVOURITE);

      orderByColumns.add(0, FLD_CREATED_TS);
   }

   @Override
   public String getTableName() {
      return MedicineEventTemplateTable.TABLE;
   }

   @Override
   public Map<String, Object> getInsertMappings(MedicineEventTemplate base) {
      Map<String, Object> map = super.getInsertMappings(base);

      if (base instanceof MedicineEventTemplate) {
         MedicineEventTemplate tmpl = (MedicineEventTemplate) base;

         map.put(FLD_MEDICINE_ID, tmpl.getMedicineId());
         map.put(FLD_MEDICINE_NAME, tmpl.getMedicineName());
         map.put(FLD_DOSE, tmpl.getDose());
         map.put(FLD_UNIT, tmpl.getUnit().toString());
         map.put(FLD_IS_FAVOURITE, tmpl.isFavorite());

      } else {
      }
      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(MedicineEventTemplate base) {
      Map<String, Object> map = super.getUpdateMappings(base);

      map.put(FLD_MEDICINE_ID, base.getMedicineId());
      map.put(FLD_MEDICINE_NAME, base.getMedicineName());
      map.put(FLD_IS_FAVOURITE, base.isFavorite());
      map.put(FLD_DOSE, base.getDose());
      map.put(FLD_UNIT, base.getUnit().toString());
      return map;
   }

   @Override
   public RowMapper<MedicineEventTemplate> getRowMapper() {

      return null;
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> crits = super.addLevelSpecificCriteriaFrom(idCrit);
      if (idCrit instanceof MedicineEventTemplateCriteria) {
         MedicineEventTemplateCriteria ec = (MedicineEventTemplateCriteria) idCrit;
         logger.debug("received Criteria=" + ec);

//         List<Integer> eventIds = ec.getEventIds();
//         List<String> eventTypes = ec.getEventTypes();
//         List<Integer> medicineIds = ec.getMedicineIds();
//         List<UNIT> units = ec.getUnits();
         Boolean favourite = ec.isFavourite();
         if (favourite != null) {

            StringBuffer buffer = createBooleanCriteria(FLD_IS_FAVOURITE);
            crits.add(buffer);
         }

      }

      return crits;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria != null) {
         MedicineEventTemplateCriteria ec = (MedicineEventTemplateCriteria) criteria;

//         BigDecimal doseMin = ec.getDoseMin();
//         BigDecimal doseMax = ec.getDoseMax();
//         if (doseMin != null && doseMax != null) {
//            map.put(FLD_DOSE_MIN, doseMin);
//            map.put(FLD_DOSE_MAX, doseMax);
//         }
         Boolean favourite = ec.isFavourite();

         if (favourite != null) {
            map.put(FLD_IS_FAVOURITE, favourite.toString());

         }

      }

      return map;

   }

}
