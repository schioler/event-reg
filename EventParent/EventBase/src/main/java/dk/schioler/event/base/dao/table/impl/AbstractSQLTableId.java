package dk.schioler.event.base.dao.table.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.table.BaseSQLTableId;
import dk.schioler.event.base.entity.AbstractEntityId;

public abstract class AbstractSQLTableId<T extends AbstractEntityId> implements BaseSQLTableId<T> {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   protected List<String> insertColumns = new ArrayList<String>();
   protected List<String> selectColumns = new ArrayList<String>();
   protected List<String> orderByColumns = new ArrayList<String>();

   public AbstractSQLTableId() {
      super();
      insertColumns.add(FLD_LOGIN_ID);
      insertColumns.add(FLD_CREATED);

      selectColumns.add(FLD_ID);
      selectColumns.add(FLD_LOGIN_ID);
      selectColumns.add(FLD_CREATED);

      orderByColumns.add(FLD_ID);
   }

   public List<String> getSelectColumns() {
      return selectColumns;
   }

   public List<String> getInsertColumns() {
      return insertColumns;
   }

   public List<String> getOrderBy() {
      return orderByColumns;
   }

   // *************************

   @Override
   public StringBuffer getInsertSQL() {

      StringBuffer sql = new StringBuffer();

      sql.append(INSERT_INTO).append(SPACE).append(getTableName()).append(SPACE);
      sql.append(LEFT_PARENTHIS);

      List<String> columns = getInsertColumns();
      int size = columns.size();
      for (int i = 0; i < size; i++) {
         String column = columns.get(i);
         if (!FLD_ID.equals(column)) {
            sql.append(column);
            if (i + 1 < size) {
               sql.append(SEP);
            }
         }
      }
      sql.append(RIGHT_PARENTHIS).append(SPACE);
      sql.append(VALUES).append(SPACE);
      sql.append(LEFT_PARENTHIS);
      size = columns.size();
      for (int i = 0; i < size; i++) {
         String column = columns.get(i);
         if (!FLD_ID.equals(column)) {
            sql.append(BIND).append(column);
            if (i + 1 < size) {
               sql.append(SEP);
            }
         }
      }
      sql.append(RIGHT_PARENTHIS).append(SPACE);

      logger.debug("sql=" + sql.toString());
      return sql;
   }

   @Override
   public Map<String, Object> getInsertMappings(T type) {
      Map<String, Object> map = new HashMap<String, Object>();
      if (type.getLoginId() != null) {
         map.put(FLD_LOGIN_ID, type.getLoginId());
      }
      if (type.getCreated() != null) {
         map.put(FLD_CREATED, type.getCreated());
      }

      return map;

   }

   // *************************

   @Override
   public StringBuffer getUpdateSQL() {
      StringBuffer sql = new StringBuffer();
      sql.append(UPDATE).append(SPACE).append(getTableName()).append(SPACE);
      sql.append(SET).append(SPACE);

      List<String> updateColumns = getSelectColumns();
      int size = updateColumns.size();
      for (int i = 0; i < size; i++) {
         String column = updateColumns.get(i);
         if (!FLD_ID.equals(column)) {
            sql.append(column).append(EQ).append(BIND).append(column);
            if (i + 1 < size) {
               sql.append(SEP);
            } else {
               sql.append(SPACE);
            }
         }
      }
      sql.append(WHERE).append(SPACE);
      sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

      logger.debug("sql=" + sql.toString());
      return sql;
   }

   @Override
   public Map<String, Object> getUpdateMappings(T type) {
      Map<String, Object> map = new HashMap<String, Object>();

      if (type.getId() != null) {
         map.put(FLD_ID, type.getId());
      }
      if (type.getLoginId() != null) {
         map.put(FLD_LOGIN_ID, type.getLoginId());
      }
      if (type.getCreated() != null) {
         map.put(FLD_CREATED, type.getCreated());
      }

      return map;
   }

   // *************************

   @Override
   public StringBuffer getDeleteSQL() {
      StringBuffer sb = new StringBuffer();
      sb.append(DELETE).append(SPACE);
      sb.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
      sb.append(WHERE).append(SPACE);
      sb.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

      return sb;
   }

   // *************************

   @Override
   public Map<String, Object> getIdMapping(Integer id, Integer loginId) {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put(FLD_ID, id);
      map.put(FLD_LOGIN_ID, loginId);

      return map;
   }

   @Override
   public String getFromIdSQL() {
      StringBuffer sql = new StringBuffer();
      sql.append(SELECT).append(SPACE);

      StringBuffer columns = getListStringsAsColumnsWithSeparator(getSelectColumns());
      sql.append(columns).append(SPACE);
      sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
      sql.append(WHERE).append(SPACE);
      sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID).append(SPACE);
      sql.append(AND).append(SPACE).append(FLD_LOGIN_ID).append(EQ).append(BIND).append(FLD_LOGIN_ID).append(SPACE);
      return sql.toString();
   }

   // *************************

   @Override
   public StringBuffer getRetrieveSQL(AbstractIdCriteria criteria, int maxRows) {
      logger.debug("get retrieveSQL: criteria=" + criteria);
      StringBuffer sql = new StringBuffer();
      sql.append(SELECT).append(SPACE);
      StringBuffer columns = getListStringsAsColumnsWithSeparator(getSelectColumns());
      sql.append(columns).append(SPACE);
      sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);

      List<StringBuffer> criteriaList = addLevelSpecificCriteriaFrom(criteria);
      int size = criteriaList.size();

      if (size > 0) {
         sql.append(WHERE).append(SPACE);
         for (int j = 0; j < criteriaList.size(); j++) {
            StringBuffer c = criteriaList.get(j);
            sql.append(c);
            if (j < (size - 1)) {
               sql.append(SPACE).append(AND).append(SPACE);
            }
         }
      }

      List<String> orderBy2 = getOrderBy();
      String orderBy = orderBy2.get(0);
      if (StringUtils.isNoneEmpty(orderBy)) {
         sql.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
      }

      return sql;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      logger.debug("addLevelSpecificRetrieveMappingsID: criteria=" + criteria);

      Map<String, Object> map = new HashMap<String, Object>();

      if (criteria != null) {

         List<Integer> ids = criteria.getIds();
         if (ids != null && ids.size() > 0) {
            Map<String, Object> idMappings = createIntegerMappings(FLD_ID, ids);
            if (idMappings.size() > 0) {
               map.putAll(idMappings);
            }
         }

         List<Integer> loginIds = criteria.getLoginIds();
         if (loginIds != null && loginIds.size() > 0) {
            Map<String, Object> loginMap = createIntegerMappings(FLD_LOGIN_ID, loginIds);
            if (loginMap.size() > 0) {
               map.putAll(loginMap);
            }
         }

         LocalDateTime createdStart = criteria.getCreatedStart();
         LocalDateTime createdEndTime = criteria.getCreatedEndTime();
         if (createdStart != null && createdEndTime != null) {
            map.put(CREATED_START_DATE, createdStart);
            map.put(CREATED_END_DATE, createdEndTime);
         }

         return map;
      } else {
         throw new EventDAOException("criteria can not be null");
      }
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria criteria) {
      logger.debug("buildLevelSpecificRetrieveCriteriaFrom:" + criteria);
      List<StringBuffer> critList = new ArrayList<StringBuffer>();

      if (criteria != null) {
         List<Integer> ids = criteria.getIds();
         StringBuffer idCrit = createIntegerCriteria(ids, FLD_ID);
         if (idCrit != null) {
            critList.add(idCrit);
         }

         List<Integer> loginIds = criteria.getLoginIds();
         StringBuffer loginIdCrit = createIntegerCriteria(loginIds, FLD_LOGIN_ID);
         if (loginIdCrit != null) {
            critList.add(loginIdCrit);
         }

         LocalDateTime endTime = criteria.getCreatedEndTime();
         LocalDateTime startTime = criteria.getCreatedStart();
         if (endTime != null && startTime != null) {
            StringBuffer createdCrit = createLocalDateTimeCriteria(CREATED_START_DATE, CREATED_END_DATE, FLD_CREATED);
            if (createdCrit != null) {
               critList.add(createdCrit);
            }
         }

         logger.debug("buildRetrieveSQLFromCriteriaInstance: critList=");
         for (StringBuffer crit : critList) {
            logger.debug(crit.toString());
         }
         return critList;
      } else {
         throw new EventDAOException("criteria can not be null");
      }
   }

// **************************************************'
//   UTIL METHODS
//   MAPPINGS:
   public Map<String, Object> createIntegerMappings(String field, List<Integer> ids) {
      logger.debug("createIntegerMappings(field=" + field + ", list=" + ids + ")");
      Map<String, Object> map = new HashMap<String, Object>();
      if (StringUtils.isNotBlank(field)) {
         if (ids != null && ids.size() > 0) {
            if (ids.size() == 1) {
               map.put(field, ids.get(0));
            } else if (ids.size() > 1) {
               int size = ids.size();
               for (int i = 1; i <= size; i++) {
                  map.put(field + i, ids.get(i - 1));
               }
            }
         }
      }

      return map;
   }

   public StringBuffer createIntegerCriteria(List<Integer> ids, String field) {
      if (ids != null && ids.size() > 0) {
         StringBuffer c = new StringBuffer();

         int size = ids.size();
         if (size == 1) {
            c.append(SPACE).append(field).append(SPACE).append(EQ).append(SPACE).append(BIND).append(field);
         } else {
//            
            c.append(field).append(IN).append(LEFT_PARENTHIS);
            for (int i = 0; i < size; i++) {
               c.append(BIND).append(field).append(i + 1);
               if (i < size - 1) {
                  c.append(SEP);
               }
            }
            c.append(RIGHT_PARENTHIS).append(SPACE);
//            for  (int i = 0; i < size; i++) {
//              c.append(field).append(EQ).append(BIND).append(field+(i+1));
//              if(i+1 < size) {
//                 c.append(SPACE).append(OR).append(SPACE);
//                 
//              }
//            }
         }

         return c;
      } else {
         return null;
      }

   }

   
   
   public StringBuffer createBooleanCriteria(String field) {
      StringBuffer sql = null;
      if (StringUtils.isNotBlank(field)) {
         sql = new StringBuffer();
         sql.append(field).append(SPACE).append(EQ).append(SPACE).append(BIND).append(field);
         return sql;
      } else {
         return sql;
      }

   }

   public StringBuffer createGeneralCriteria(String field) {
      StringBuffer sql = null;
      if (StringUtils.isNotBlank(field)) {
         sql = new StringBuffer();
         sql.append(field).append(SPACE).append(EQ).append(SPACE).append(BIND).append(field);
      }
      return sql;
   }

   public StringBuffer createLocalDateTimeCriteria(String startDateToken, String endDateToken, String field) {
      StringBuffer c = null;

      if (startDateToken != null && endDateToken != null) {
         if (StringUtils.isNotBlank(field)) {
            c = new StringBuffer();
            // field => startDate and field <= endDate
            c.append(field).append(SPACE).append(LARGER_THAN).append(EQ).append(SPACE).append(BIND).append(startDateToken);
            c.append(SPACE).append(AND).append(SPACE);
            c.append(field).append(SPACE).append(SMALLER_THAN).append(EQ).append(SPACE).append(BIND).append(endDateToken);
         }
      }

      return c;
   }

   public StringBuffer createStringCriteria(String field) {
      StringBuffer c = null;

      if (StringUtils.isNotBlank(field)) {
         c = new StringBuffer();
         c.append(SPACE).append(field).append(SPACE).append(LIKE).append(SPACE);
         c.append(CONCAT).append(LEFT_PARENTHIS);
         c.append(HYPHEN).append(SQL_WILDCARD).append(HYPHEN).append(SEP);
         c.append(BIND).append(field).append(SEP);
         c.append(HYPHEN).append(SQL_WILDCARD).append(HYPHEN);
         c.append(RIGHT_PARENTHIS);
      }

      return c;
   }

  
   public StringBuffer createDoseCriteria(String doseMin, String doseMax, String field) {
      StringBuffer criteria = null;
      if (doseMin != null && doseMax != null && StringUtils.isNotBlank(field)) {

         criteria = new StringBuffer();

         criteria.append(field).append(SPACE).append(LARGER_THAN).append(SPACE).append(BIND).append(doseMin).append(SPACE);
         criteria.append(AND).append(SPACE);
         criteria.append(field).append(SPACE).append(SMALLER_THAN).append(SPACE).append(BIND).append(doseMax).append(SPACE);

      }

      return criteria;
   }

   public StringBuffer getListStringsAsColumnsWithSeparator(List<String> columns) {
      StringBuffer sql = new StringBuffer();

      int size = columns.size();
      for (int i = 0; i < size;) {
         String column = columns.get(i);
         sql.append(column);
         i++;
         if (i < size) {
            sql.append(SEP);
         } else {
            sql.append(SPACE);
         }
      }

      return sql;
   }
}
