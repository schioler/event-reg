package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.criteria.MedicineCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.MedicineRowMapper;
import dk.schioler.event.base.dao.table.MedicineTable;
import dk.schioler.event.base.entity.Medicine;
import dk.schioler.event.base.entity.UNIT;

@Service
public class MedicineTableImpl<T extends Medicine> extends EntityBaseTableImpl<T> implements MedicineTable<T> {

   public MedicineTableImpl() {
      super();
   }

   @Override
   public String getTableName() {
      return MedicineTable.TABLE;
   }

   @Override
   public RowMapper<Medicine> getRowMapper() {
      return new MedicineRowMapper<Medicine>();
   }

   @Override
   public List<String> getOrderBy() {

      return orderByColumns;
   }

   @Override
   public Map<String, Object> getInsertMappings(T type) {
      Map<String, Object> map = super.getInsertMappings(type);

      if (type instanceof Medicine) {
         Medicine mt = (Medicine) type;
         Integer medicineFirmId = mt.getMedicineTypeId();
         UNIT u = mt.getUnit();
         String name = mt.getName();
         String dose = mt.getDose();

         if (medicineFirmId != null) {
            map.put(FLD_MEDICINE_TYPE_ID, medicineFirmId);
         }

         if (u != null) {
            String unitAsString = UNIT.unitAsString(u);
            map.put(FLD_UNIT, unitAsString);
         }

         if (StringUtils.isNotBlank(dose)) {
            map.put(FLD_DOSE, dose);
         }

         if (StringUtils.isNotBlank(name)) {
            map.put(FLD_NAME, name);
         }

      }
      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(T type) {
      Map<String, Object> map = super.getUpdateMappings(type);
      if (type instanceof Medicine) {
         Medicine mt = type;

         Integer medicineTypeId = mt.getMedicineTypeId();
         UNIT u = mt.getUnit();
         String dose = mt.getDose();
         String name = mt.getName();

         if (medicineTypeId != null) {
            map.put(FLD_MEDICINE_TYPE_ID, medicineTypeId);
         }

         if (StringUtils.isNotBlank(name)) {
            map.put(FLD_NAME, name);
         }

         if (StringUtils.isNotBlank(dose)) {
            map.put(FLD_NAME, name);
         }

         if (u != null) {
            String unit = UNIT.unitAsString(u);
            map.put(FLD_UNIT, unit);
         }

      }
      return map;
   }

// *******************************************************'
   
   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> criteria = super.addLevelSpecificCriteriaFrom(idCrit);

      if (idCrit instanceof MedicineCriteria) {
         MedicineCriteria mtc = (MedicineCriteria) idCrit;

//         String dose = mtc.getDose();
         List<Integer> medicineTypeIds = mtc.getMedicineTypeIds();
         String name = mtc.getName();
//         UNIT unit = mtc.getUnit();
         

         if (medicineTypeIds != null && !medicineTypeIds.isEmpty()) {
            StringBuffer medicineFirmIdCrit = createIntegerCriteria(FLD_MEDICINE_TYPE_ID, medicineTypeIds);
            criteria.add(medicineFirmIdCrit);
         }

//         if (StringUtils.isNotBlank(medicineType)) {
//            StringBuffer stringCriteria = createStringCriteria(FLD_MEDICINE_TYPE);
//            criteria.add(stringCriteria);
//         }

         if (StringUtils.isNotBlank(name)) {
            StringBuffer stringCriteria = createStringCriteria(FLD_NAME);
            criteria.add(stringCriteria);
         }
      }
      return criteria;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      Map<String, Object> levelSpecificRetrieveMappings = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria instanceof MedicineCriteria) {
         MedicineCriteria mtc = (MedicineCriteria) criteria;

         List<Integer> medicineTypeId = mtc.getMedicineTypeIds();
         String dose = mtc.getDose();
         String name = mtc.getName();
         UNIT unit = mtc.getUnit();

         if (medicineTypeId != null) {
            levelSpecificRetrieveMappings.put(FLD_MEDICINE_TYPE_ID, medicineTypeId);
         }

         
         if (StringUtils.isNotBlank(name)) {
            levelSpecificRetrieveMappings.put(FLD_NAME, name);
         }

         if (StringUtils.isNotBlank(dose)) {
            levelSpecificRetrieveMappings.put(FLD_DOSE, dose);
         }
         
         if (unit !=null) {
            levelSpecificRetrieveMappings.put(FLD_UNIT, UNIT.unitAsString(unit));
         }

      }

      return levelSpecificRetrieveMappings;
   }
}
