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

import dk.schioler.event.base.dao.BaseIdDAO;
import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.table.BaseSQLTableId;
import dk.schioler.event.base.entity.AbstractEntityId;
import dk.schioler.event.base.exception.AbstractIdDAOException;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.ROLE;

@Service
public abstract class AbstractIdDAOImpl<T extends AbstractEntityId> implements BaseIdDAO<T> {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   protected BaseSQLTableId<T> table;

   protected AbstractIdDAOImpl(BaseSQLTableId<T> table) {
      this.table = table;
   }

   private NamedParameterJdbcTemplate jdbcTemplate;

   @Autowired
   public void setDataSource(DataSource dataSource) {
      jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
      logger.trace("setDataSource:" + jdbcTemplate);
   }

   protected NamedParameterJdbcTemplate getJDBCTemplate() {
      return jdbcTemplate;
   }

   @Autowired
   protected LoginDAO loginDAO;

   @Override
   public T insert(T type) {
      logger.trace("insert of " + type);
      try {
         if (isValidInsertObject(type)) {
            StringBuffer sql = table.getInsertSQL();
            Map<String, Object> map = table.getInsertMappings(type);
            logger.debug(sql.toString());
            logger.debug("values=" + map);

            MapSqlParameterSource paramSource = new MapSqlParameterSource();
            paramSource.addValues(map);

            KeyHolder keyHolder = new GeneratedKeyHolder();

            int count = jdbcTemplate.update(sql.toString(), paramSource, keyHolder);

            logger.trace("insert added " + count + " rows");

            Map<String, Object> keys = keyHolder.getKeys();

            for (Entry<String, Object> entry : keys.entrySet()) {
               logger.trace("keyHolder.key=" + entry.toString());
            }
            Integer id = (Integer) keys.get("id");
            if (id != null) {
               type.setId(id);
            } else {
               logger.error("generated keyset:" + keys);
               throw new EventDAOException("insert: generated more than one key ");
            }

            logger.trace("type=" + type);

            return type;
         } else {
            logger.error("received object in invalid state:" + type);
            throw new EventDAOException("invalid object received");
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

      int retVal = jdbcTemplate.update(sql.toString(), updateMappings);
      return retVal;

   }

   @Override
   public int delete(Integer id, Integer loginId) {
      StringBuffer sb = table.getDeleteSQL();
      Map<String, Object> deleteMapping = table.getIdMapping(id, loginId);

      logger.trace("delete: sql=" + sb.toString());
      logger.trace("delete: map=" + deleteMapping);

      MapSqlParameterSource paramSource = new MapSqlParameterSource(deleteMapping);

      return jdbcTemplate.update(sb.toString(), paramSource);
   }

   @Override
   public List<T> retrieve(AbstractIdCriteria criteria, int maxRows) {
      StringBuffer retrieveSQL = table.getRetrieveSQL(criteria, maxRows);
      logger.debug("retrieve: sql = " + retrieveSQL);

      Map<String, Object> retrieveMappings = table.addLevelSpecificRetrieveMappings(criteria);
      logger.debug("retrieve mappings: " + retrieveMappings);

      MapSqlParameterSource paramSource = new MapSqlParameterSource(retrieveMappings);

      return jdbcTemplate.query(retrieveSQL.toString(), paramSource, table.getRowMapper());

   }

   @Override
   public T get(Integer id, Integer loginId) {
      String sql = table.getFromIdSQL();

      Map<String, Object> map = table.getIdMapping(id, loginId);

      logger.debug("sql=" + sql.toString());

      MapSqlParameterSource paramSource = new MapSqlParameterSource(map);

      List<T> query = jdbcTemplate.query(sql.toString(), paramSource, table.getRowMapper());

      return query.get(0);
   }

   protected boolean isValidInsertObject(T type) throws EventDAOException {
      boolean retVal = false;

      if (type != null) {
         if (type.getLoginId() != null) {
            retVal = isLoginValidAsOwner(type.getLoginId());
         } else {
            throw new AbstractIdDAOException("LoginId can not be null");
         }
      } else {
         throw new AbstractIdDAOException("No object to insert - received type=null");
      }

      return retVal;
   };

   protected boolean isLoginValidAsOwner(Integer loginId) {
      boolean isValid = false;
      if (loginId != null) {
         if (loginId.intValue() > 0) {
            Login login = loginDAO.get(loginId);
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
