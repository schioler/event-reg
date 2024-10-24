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

import dk.schioler.event.base.dao.BaseEventDAO;
import dk.schioler.event.base.dao.EventDAOException;
import dk.schioler.event.base.dao.table.ISQLTable;
import dk.schioler.event.base.entity.AbstractEntity;

@Service
public abstract class AbstractDAOImpl<T extends AbstractEntity> implements BaseEventDAO<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected final int CACHE_VALID_HOURS = 1;

	protected ISQLTable<T> table;

	protected AbstractDAOImpl(ISQLTable<T> table) {
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

	@Override
	public T insert(T type) {
		logger.trace("insert of " + type);
		try {
			if (validateInsertObject(type)) {
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

		Map<String, Object> updateMappings = table.getUpdatetMappings(type);

		logger.trace("update: sql=" + sql);
		logger.trace("updateMappings=" + updateMappings);

		int retVal = jdbcTemplate.update(sql.toString(), updateMappings);
		return retVal;

	}

	@Override
	public int delete(Integer id) {
		StringBuffer sb = table.getDeleteSQL();
		Map<String, Object> deleteMapping = table.getIdMapping(id);

		logger.trace("delete: sql=" + sb.toString());
		logger.trace("delete: map=" + deleteMapping);

		MapSqlParameterSource paramSource = new MapSqlParameterSource(deleteMapping);

		return jdbcTemplate.update(sb.toString(), paramSource);
	}

	@Override
	public List<T> retrieve(Map<String, Object> criteria) {
		StringBuffer retrieveSQL = table.getRetrieveSQL(criteria, 0);
		logger.debug("retrieve: sql = "+ retrieveSQL);
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource(criteria);

		return jdbcTemplate.query(retrieveSQL.toString(), paramSource, table.getRowMapper());
		
	}

	@Override
	public T get(Integer id) {
		String sql = table.getFromIdSQL(id);
		Map<String, Object> map = table.getIdMapping(id);

		MapSqlParameterSource paramSource = new MapSqlParameterSource(map);

		List<T> query = jdbcTemplate.query(sql.toString(), paramSource, table.getRowMapper());

		return query.get(0);
	}

//	public StringBuffer getColumnsAsUpdateSQL() {
//		StringBuffer sql = new StringBuffer();
//
//		List<String> updateColumns = table.getUpdateColumns();
//		int size = updateColumns.size();
//		for (int i = 0; i < size; i++) {
//			String column = updateColumns.get(i);
//			if (!FLD_ID.equals(column)) {
//				sql.append(column).append(EQ).append(BIND).append(column);
//				if (i + 1 < size) {
//					sql.append(SEP);
//				} else {
//					sql.append(SPACE);
//				}
//			}
//		}
//
//		return sql;
//	}

//	@Override
//	public StringBuffer getInsertSqlFromColumns(List<String> columns) {
//		StringBuffer sql = new StringBuffer();
//
//		sql.append(LEFT_PARENTHIS);
//		int size = columns.size();
//		for (int i = 0; i < size; i++) {
//			String column = columns.get(i);
//			if (!FLD_ID.equals(column)) {
//				sql.append(column);
//				if (i + 1 < size) {
//					sql.append(SEP);
//				}
//			}
//		}
//		sql.append(RIGHT_PARENTHIS).append(SPACE);
//		sql.append(VALUES).append(SPACE);
//		sql.append(LEFT_PARENTHIS);
//		size = columns.size();
//		for (int i = 0; i < size; i++) {
//			String column = columns.get(i);
//			if (!FLD_ID.equals(column)) {
//				sql.append(BIND).append(column);
//				if (i + 1 < size) {
//					sql.append(SEP);
//				}
//			}
//		}
//		sql.append(RIGHT_PARENTHIS).append(SPACE);
//
//		return sql;
//	}

//	@Override
//	public StringBuffer getSelectSqlFromColumns(List<String> columns) {
//		StringBuffer sql = new StringBuffer();
//
//		int size = columns.size();
//		for (int i = 0; i < size;) {
//			String column = columns.get(i);
//			sql.append(column);
//			i++;
//			if (i < size) {
//				sql.append(SEP);
//			} else {
//				sql.append(SPACE);
//			}
//		}
//
//		return sql;
//	}

//	public abstract Map<String, Object> getUpdateMappings(T t);

//	public abstract String getInsertSQL(T type);

//	public abstract Map<String, Object> getInsertMappings(T t);

	protected abstract boolean validateInsertObject(T type);

	@Override
	public List<T> lookup() {
		StringBuffer sql = table.getRetrieveSQL(null, 20);
//		StringBuffer sql = new StringBuffer();
//		sql.append(SELECT).append(SPACE);
//		sql.append(getSelectSqlFromColumns(table.getSelectColumns())).append(SPACE);
//		sql.append(FROM).append(SPACE).append(getSQLTableName()).append(SPACE);
//
//		String orderBy = getOrderBy();
//		if (StringUtils.isNoneEmpty(orderBy)) {
//			sql.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
//		}
		List<T> query = jdbcTemplate.query(sql.toString(), table.getRowMapper());

		return query;
	}

}
