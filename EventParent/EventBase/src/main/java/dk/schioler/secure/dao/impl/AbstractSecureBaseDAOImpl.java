package dk.schioler.secure.dao.impl;

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

import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.secure.dao.SecureBaseDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.SecureEntity;
import dk.schioler.secure.table.SecureTable;

@Service
public abstract class AbstractSecureBaseDAOImpl<T extends SecureEntity> implements SecureBaseDAO<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private NamedParameterJdbcTemplate jdbcTemplate;

	protected SecureTable<T> table;

	public AbstractSecureBaseDAOImpl(SecureTable<T> table) {
		this.table = table;
	}

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		logger.trace("setDataSource:" + jdbcTemplate);
	}

	protected NamedParameterJdbcTemplate getJDBCTemplate() {
		return jdbcTemplate;
	}
	
	

	public SecureTable<T> getTable() {
		return table;
	}

	protected abstract boolean validateInsertObject(T type);

	@Override
	public T insert(T type, Login login) {
		logger.trace("insert of " + type);
		try {
			if (validateInsertObject(type)) {
				StringBuffer insertSQL = table.getInsertSQL();
				Map<String, Object> insertMappings = table.getInsertMappings(type);

				logger.debug(insertSQL.toString());
				logger.debug("values=" + insertMappings);

				NamedParameterJdbcTemplate jdbcTemplate = getJDBCTemplate();

				MapSqlParameterSource paramValues = new MapSqlParameterSource(insertMappings);

				KeyHolder keyHolder = new GeneratedKeyHolder();

				int count = jdbcTemplate.update(insertSQL.toString(), paramValues, keyHolder);

				logger.trace("insert added " + count + " rows");

				Map<String, Object> keys = keyHolder.getKeys();

				for (Entry<String, Object> entry : keys.entrySet()) {
					logger.trace("keyHolder.keys=" + entry.toString());
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

			} else

			{
				logger.error("received object in invalid state:" + type);
				throw new EventDAOException("invalid object received");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public int update(T type, Login login) {
		StringBuffer sql = table.getUpdateSQL();

		Map<String, Object> updateMappings = table.getUpdatetMappings(type);

		logger.trace("update: sql=" + sql);
		logger.trace("updateMappings=" + updateMappings);

		int retVal = jdbcTemplate.update(sql.toString(), updateMappings);
		return retVal;

	}

	@Override
	public int delete(Integer id, Login login) {
		StringBuffer sb = table.getDeleteSQL();
		Map<String, Object> deleteMapping = table.getIdMapping(id);

		logger.trace("delete: sql=" + sb.toString());
		logger.trace("delete: map=" + deleteMapping);

		MapSqlParameterSource paramSource = new MapSqlParameterSource(deleteMapping);

		return jdbcTemplate.update(sb.toString(), paramSource);
	}

	@Override
	public List<T> retrieve(Map<String, Object> criteria, Login login) {
		StringBuffer retrieveSQL = table.getRetrieveSQL(criteria, 0);
		logger.debug("retrieve: sql = " + retrieveSQL);

		MapSqlParameterSource paramSource = new MapSqlParameterSource(criteria);

		return jdbcTemplate.query(retrieveSQL.toString(), paramSource, table.getRowMapper());

	}

	@Override
	public T get(Integer id, Login login) {
		String sql = table.getFromIdSQL(id);
		Map<String, Object> map = table.getIdMapping(id);

		MapSqlParameterSource paramSource = new MapSqlParameterSource(map);

		List<T> query = jdbcTemplate.query(sql.toString(), paramSource, table.getRowMapper());

		return query.get(0);
	}

}
