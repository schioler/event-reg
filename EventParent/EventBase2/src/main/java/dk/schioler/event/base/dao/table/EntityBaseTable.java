package dk.schioler.event.base.dao.table;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.base.entity.EntityBase;

@Service
public interface EntityBaseTable<T extends EntityBase> extends SQLConstructs {

   public static final String FLD_ID = "ID";
   public static final String FLD_OWNER_ID = "OWNER_ID";
   public static final String FLD_CREATED_TS = "CREATED_TS";

   public String getTableName();

   public List<String> getSelectColumns();

   public List<String> getInsertColumns();

   public List<String> getOrderBy();

   // *************************

   public StringBuffer getInsertSQL();

   public Map<String, Object> getInsertMappings(T type);

   // *************************

   public StringBuffer getUpdateSQL();

   public Map<String, Object> getUpdateMappings(T type);

   // *************************

   public StringBuffer getDeleteSQL();

   // *************************

   public Map<String, Object> getSingleIdMapping(Integer id, Integer loginId);

   public String getFromIdSQL();

   // *************************

   public StringBuffer getRetrieveSQL(EntityBaseCriteria criteria, int maxRows);

   // **************************************

   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria criteria);

   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria);

   public RowMapper<? extends EntityBase> getRowMapper();
}
