package dk.schioler.event.base.dao.table;


import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.base.entity.AbstractEntityId;

public interface BaseSQLTableId<T extends AbstractEntityId> extends SQLConstructs {

   public static final String FLD_ID = "ID";
   public static final String FLD_LOGIN_ID = "LOGIN_ID";
   public static final String FLD_CREATED = "CREATED";

   public static final String FLD_DOSE_MIN = "DOSE_MIN";
   public static final String FLD_DOSE_MAX = "DOSE_MAX";
   
   public String getTableName();

   // *************************

   public StringBuffer getInsertSQL();

   // *************************

   public StringBuffer getUpdateSQL();

   public Map<String, Object> getInsertMappings(T type);

   public Map<String, Object> getUpdateMappings(T type);

   // *************************

   public StringBuffer getDeleteSQL();

   // *************************

   public Map<String, Object> getIdMapping(Integer id, Integer loginId);

   public String getFromIdSQL();

   // *************************

   public StringBuffer getRetrieveSQL(AbstractIdCriteria criteria, int maxRows);

   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria criteria);
   
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria type);

   public RowMapper<T> getRowMapper();
}
