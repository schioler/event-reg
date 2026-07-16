package dk.schioler.event.base.dao.table.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.criteria.MedicineTypeCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.MedicineTypeRowMapper;
import dk.schioler.event.base.dao.table.MedicineTypeTable;
import dk.schioler.event.base.entity.MedicineType;

@Service
public class MedicineTypeTableImpl<T extends MedicineType> extends EntityBaseTableImpl<T> implements MedicineTypeTable<T> {

   public MedicineTypeTableImpl() {
      super();
   }

   @Override
   public String getTableName() {
      return MedicineTypeTable.TABLE;
   }

   @Override
   public RowMapper<T> getRowMapper() {
      return new MedicineTypeRowMapper<T>();
   }

   @Override
   public List<String> getOrderBy() {

      return orderByColumns;
   }

   @Override
   public Map<String, Object> getInsertMappings(T type) {
      Map<String, Object> map = super.getInsertMappings(type);

      if (type instanceof MedicineType) {
         MedicineType mt = (MedicineType) type;
         Integer medicineFirmId = mt.getMedicineFirmId();
         String medicineType = mt.getMedicineType();
         String name = mt.getName();
         String description = mt.getDescription();

         map.put(FLD_MEDICINE_FIRM_ID, medicineFirmId);
         map.put(FLD_MEDICINE_TYPE, medicineType);
         map.put(FLD_NAME, name);
         map.put(FLD_DESCRIPTION, description);

      }
      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(T type) {
      Map<String, Object> map = super.getUpdateMappings(type);
      if (type instanceof MedicineType) {
         MedicineType mt = type;
         Integer medicineFirmId = mt.getMedicineFirmId();
         String medicineType = mt.getMedicineType();

         String name = mt.getName();
         String description = mt.getDescription();

         map.put(FLD_DESCRIPTION, description);
         map.put(FLD_NAME, name);

         map.put(FLD_MEDICINE_FIRM_ID, medicineFirmId);
         map.put(FLD_MEDICINE_TYPE, medicineType);
      }
      return map;
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> criteria = super.addLevelSpecificCriteriaFrom(idCrit);

      if (idCrit instanceof MedicineTypeCriteria) {
         MedicineTypeCriteria mtc = (MedicineTypeCriteria) idCrit;

         Integer medicineFirmId = mtc.getMedicineFirmId();
         String medicineType = mtc.getMedicineType();
         String name = mtc.getName();
//         String description = mtc.getDescription();

         if (medicineFirmId != null) {
            List<Integer> list = new ArrayList<Integer>();
            list.add(medicineFirmId);
            StringBuffer medicineFirmIdCrit = createIntegerCriteria(FLD_MEDICINE_FIRM_ID, list);
            criteria.add(medicineFirmIdCrit);
         }

         if (StringUtils.isNotBlank(medicineType)) {
            StringBuffer stringCriteria = createStringCriteria(FLD_MEDICINE_TYPE);
            criteria.add(stringCriteria);
         }

         if (StringUtils.isNotBlank(name)) {
            StringBuffer stringCriteria = createStringCriteria(name);
            criteria.add(stringCriteria);
         }
      }
      return criteria;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      Map<String,Object> levelSpecificRetrieveMappings = super.addLevelSpecificRetrieveMappings(criteria);
      
      if (criteria instanceof MedicineTypeCriteria) {
         MedicineTypeCriteria mtc = (MedicineTypeCriteria) criteria;
         
         Integer medicineFirmId = mtc.getMedicineFirmId();
         String medicineType = mtc.getMedicineType();
         String name = mtc.getName();
         
         if(medicineFirmId != null) {
            levelSpecificRetrieveMappings.put(FLD_MEDICINE_FIRM_ID, medicineFirmId);            
         }
         
         if(StringUtils.isNotBlank(name)) {
            levelSpecificRetrieveMappings.put(FLD_NAME, name);
         }
         
         if(StringUtils.isNotBlank(medicineType)) {
            levelSpecificRetrieveMappings.put(FLD_MEDICINE_TYPE, medicineType);
         }
         
         
         
      }

      return levelSpecificRetrieveMappings;
   }
}
