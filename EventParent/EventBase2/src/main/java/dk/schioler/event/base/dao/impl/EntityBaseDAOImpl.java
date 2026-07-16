package dk.schioler.event.base.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.DAOException;
import dk.schioler.event.base.dao.EntityBaseDAO;
import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.table.EntityBaseTable;
import dk.schioler.event.base.entity.EntityBase;
import dk.schioler.event.base.exception.DAOOwnerIdMissingException;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.ROLE;

@Service
public class EntityBaseDAOImpl<T extends EntityBase> implements EntityBaseDAO<T> {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   protected EntityBaseTable<T> table;

   protected EntityBaseDAOImpl(EntityBaseTable<T> table) {
      this.table = table;
   }

   private NamedParameterJdbcTemplate jdbcTemplate;
   private DataSource dataSource;

   @Autowired
   public void setDataSource(DataSource dataSource) {
      this.dataSource = dataSource;
      logger.trace("setDataSource:" + dataSource);
      jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
   }

   @Override
   public DataSource getDataSource() {
      return this.dataSource;
   }

   protected NamedParameterJdbcTemplate getJDBCTemplate() {
      return jdbcTemplate;

   }

   @Autowired
   protected LoginDAO loginDAO;

   @Override
   public T insert(T entityBase) {
      logger.trace("insert of " + entityBase);
      try {
         if (isValidInsertObject(entityBase)) {
            StringBuffer sql = table.getInsertSQL();
            Map<String, Object> map = table.getInsertMappings(entityBase);
            logger.debug(sql.toString());
            logger.debug("values=" + map);

            MapSqlParameterSource paramSource = new MapSqlParameterSource();
            paramSource.addValues(map);

            KeyHolder keyHolder = new GeneratedKeyHolder();

            int count = getJDBCTemplate().update(sql.toString(), paramSource, keyHolder);

            logger.trace("insert added " + count + " rows");

            Map<String, Object> keys = keyHolder.getKeys();

            for (Entry<String, Object> entry : keys.entrySet()) {
               logger.trace("keyHolder.key=" + entry.toString());
            }
            Integer id = (Integer) keys.get("id");
            if (id != null) {
               entityBase.setId(id);
            } else {
               logger.error("generated keyset:" + keys);
               throw new DAOException("insert: generated more than one key ");
            }

            logger.trace("type=" + entityBase);

            return entityBase;
         } else {
            logger.error("received object in invalid state:" + entityBase);
            throw new DAOException("invalid object received");
         }
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
         throw e;
      }

   }

   @Override
   public int update(T type) {
      StringBuffer sql = table.getUpdateSQL();

      Map<String, Object> updateMappings = table.getUpdateMappings(type);

      logger.trace("update: sql=" + sql);
      logger.trace("updateMappings=" + updateMappings);

      int retVal = getJDBCTemplate().update(sql.toString(), updateMappings);
      return retVal;

   }

   @Override
   public int delete(Integer id, Integer loginId) {
      StringBuffer sb = table.getDeleteSQL();
      Map<String, Object> deleteMapping = table.getSingleIdMapping(id, loginId);

      logger.trace("delete: sql=" + sb.toString());
      logger.trace("delete: map=" + deleteMapping);

      MapSqlParameterSource paramSource = new MapSqlParameterSource(deleteMapping);

      return getJDBCTemplate().update(sb.toString(), paramSource);
   }

   @SuppressWarnings("unchecked")
   @Override
   public List<T> retrieve(EntityBaseCriteria criteria, int maxRows) {
      StringBuffer retrieveSQL = table.getRetrieveSQL(criteria, maxRows);
      logger.debug("retrieve: sql = " + retrieveSQL);

      Map<String, Object> retrieveMappings = table.addLevelSpecificRetrieveMappings(criteria);
      logger.debug("retrieve mappings: " + retrieveMappings);

      MapSqlParameterSource paramSource = new MapSqlParameterSource(retrieveMappings);

      List<T> query = (List<T>) getJDBCTemplate().query(retrieveSQL.toString(), paramSource, table.getRowMapper());
      return query;
   }

   @Override
   public T get(Integer id, Integer loginId) {
      String sql = table.getFromIdSQL();

      Map<String, Object> map = table.getSingleIdMapping(id, loginId);

      logger.debug("sql=" + sql.toString());

      MapSqlParameterSource paramSource = new MapSqlParameterSource(map);

      @SuppressWarnings("unchecked")
      List<T> query = (List<T>) getJDBCTemplate().query(sql.toString(), paramSource, table.getRowMapper());

      return query.get(0);
   }

   protected boolean isValidInsertObject(T type) throws DAOException {
      boolean retVal = false;

      if (type != null) {
         if (type.getOwnerId() != null) {
            retVal = hasValidOwner(type.getOwnerId());
         } else {
            throw new DAOOwnerIdMissingException("OwnweId can not be null");
         }
      } else {
         throw new DAOOwnerIdMissingException("No object to insert - received type=null");
      }

      return retVal;
   };

   protected boolean hasValidOwner(Integer ownerId) {
      boolean isValid = false;
      if (ownerId != null) {
         if (ownerId.intValue() > 0) {
            Login login = loginDAO.get(ownerId);
            if (login != null) {
               if (ROLE.OWNER.equals(login.getRole())) {
                  isValid = true;
               }
            }
         }

      }

      return isValid;
   }

}
