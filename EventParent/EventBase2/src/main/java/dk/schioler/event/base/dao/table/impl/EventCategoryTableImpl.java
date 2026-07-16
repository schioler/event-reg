package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.EventCategoryRowMapper;
import dk.schioler.event.base.dao.table.EventCategoryTable;
import dk.schioler.event.base.entity.EventCategory;

@Service
public class EventCategoryTableImpl<T extends EventCategory> extends EntityBaseTableImpl<T> implements EventCategoryTable<T> {
//   public abstract class EntityBaseTableImpl<T extends EntityBase> implements EntityBaseTable<T > {

   public EventCategoryTableImpl() {
      super();
   }
   
   

 



   @Override
   public String getTableName() {
      return EventCategory.TABLE;
   }

   @Override
   public RowMapper<EventCategory> getRowMapper() {
      return new EventCategoryRowMapper<EventCategory>();
      
//            new EventCategoryRowMapper<T>();

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
      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(T type) {
      Map<String, Object> map = super.getUpdateMappings(type);
      return map;
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> criteria = super.addLevelSpecificCriteriaFrom(idCrit);

      return criteria;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      return super.addLevelSpecificRetrieveMappings(criteria);
   }
}
