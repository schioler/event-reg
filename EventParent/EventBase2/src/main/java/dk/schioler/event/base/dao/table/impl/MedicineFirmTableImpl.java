package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.criteria.MedicineFirmCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.MedicineFirmRowMapper;
import dk.schioler.event.base.dao.table.MedicineFirmTable;
import dk.schioler.event.base.entity.MedicineFirm;

@Service
public class MedicineFirmTableImpl<T extends MedicineFirm> extends EntityBaseTableImpl<T> implements MedicineFirmTable<T> {

   public MedicineFirmTableImpl() {
      super();
   }

   @Override
   public String getTableName() {
      return MedicineFirmTable.TABLE;
   }

   @Override
   public RowMapper<T> getRowMapper() {
      return new MedicineFirmRowMapper<T>();

//       RowMapper<T>() {
//         
//         @Override
//         public T mapRow(ResultSet rs, int rowNum) throws SQLException {
//            MedicineFirm mf = new MedicineFirmImpl();
//            mf.setId(rs.getInt(FLD_ID));
//            return (T) mf;
//         }
//      };
   }

   @Override
   public List<String> getOrderBy() {

      return orderByColumns;
   }

   @Override
   public Map<String, Object> getInsertMappings(T type) {
      Map<String, Object> map = super.getInsertMappings(type);
      if (type instanceof MedicineFirm) {
         MedicineFirm mf = (MedicineFirm) type;
         String name = mf.getName();
         String brand = mf.getBrand();
         map.put(FLD_NAME, name);
         map.put(FLD_BRAND, brand);
      }

      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(T type) {
      Map<String, Object> map = super.getUpdateMappings(type);
      if (type instanceof MedicineFirm) {
         MedicineFirm mf = (MedicineFirm) type;
         String brand = mf.getBrand();
         String name = mf.getName();

         map.put(FLD_BRAND, brand);
         map.put(FLD_NAME, name);
      }

      return map;
   }

//   *******************************************************'

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> criteria = super.addLevelSpecificCriteriaFrom(idCrit);

      if (idCrit instanceof MedicineFirmCriteria) {
         MedicineFirmCriteria mfc = (MedicineFirmCriteria) idCrit;
         
      }
      return criteria;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);
      
      if (criteria instanceof MedicineFirmCriteria) {
         MedicineFirmCriteria mfc = (MedicineFirmCriteria) criteria;
         
      }
      
      return map;
   }
}
