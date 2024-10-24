package dk.schioler.event.base.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
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
import dk.schioler.event.base.entity.AbstractEntity;

@Service
public abstract class AbstractDAOImplOld<T extends AbstractEntity> implements BaseEventDAO<T>, DAOUtil<T>, SQLConstructs {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected final int CACHE_VALID_HOURS = 1;
	
	protected static String SQL_TABLE_NAME;

	protected static List<String> SELECT_COLUMNS = new ArrayList<String>();

	protected static List<String> SQL_UPDATE_COLUMNS = new ArrayList<String>();

	protected AbstractDAOImplOld(String SQLTableName, List<String> sqlSelectColumns, List<String> sqlUpdateColumns) {
		
		SQL_TABLE_NAME = SQLTableName;
		SELECT_COLUMNS = sqlSelectColumns;
		SQL_UPDATE_COLUMNS = sqlUpdateColumns;
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

				StringBuffer sql = new StringBuffer();
				sql.append(INSERT_INTO).append(SPACE).append(getSQLTableName()).append(SPACE);
				sql.append(getInsertSqlFromColumns(SELECT_COLUMNS)).append(SPACE);
				logger.debug("sql=" + sql.toString());

				Map<String, Object> map = this.getInsertMappings(type);
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
		StringBuffer sql = new StringBuffer();
		sql.append(UPDATE).append(SPACE).append(this.SQL_TABLE_NAME).append(SPACE);
		sql.append(SET).append(SPACE);
		sql.append(getColumnsAsUpdateSQL());
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

		Map<String, Object> updateMappings = getUpdateMappings(type);
		updateMappings.put(FLD_ID, type.getId());

		logger.trace("update: sql=" + sql);
		logger.trace("updateMappings=" + updateMappings);

		int retVal = jdbcTemplate.update(sql.toString(), updateMappings);
		return retVal;

	}

	@Override
	public int delete(Integer id) {
		StringBuffer sb = new StringBuffer();
		sb.append(DELETE).append(SPACE).append(FROM).append(SPACE).append(getSQLTableName()).append(SPACE);
		sb.append(WHERE).append(SPACE).append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(FLD_ID, id);
		return jdbcTemplate.update(sb.toString(), paramSource);
	}

	@Override
	public List<T> retrieve(Map<String, Object> criteria) {
		return null;
	}

	@Override
	public List<T> lookup() {
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);
		sql.append(getSelectSqlFromColumns(SELECT_COLUMNS)).append(SPACE);
		sql.append(FROM).append(SPACE).append(getSQLTableName()).append(SPACE);

		String orderBy = getOrderBy();
		if (StringUtils.isNoneEmpty(orderBy)) {
			sql.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
		}
		List<T> query = jdbcTemplate.query(sql.toString(), getRowMapper());

		return query;
	}

	@Override
	public T get(Integer id) {

		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);
		sql.append(getSelectSqlFromColumns(SELECT_COLUMNS)).append(SPACE);
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(FLD_ID, id);

		List<T> query = jdbcTemplate.query(sql.toString(), paramSource, getRowMapper());

		return query.get(0);
	}

	public StringBuffer getColumnsAsUpdateSQL() {
		StringBuffer sql = new StringBuffer();

		int size = SQL_UPDATE_COLUMNS.size();
		for (int i = 0; i < size; i++) {
			String column = SQL_UPDATE_COLUMNS.get(i);
			if (!FLD_ID.equals(column)) {
				sql.append(column).append(EQ).append(BIND).append(column);
				if (i + 1 < size) {
					sql.append(SEP);
				} else {
					sql.append(SPACE);
				}
			}
		}

		return sql;
	}

	@Override
	public StringBuffer getInsertSqlFromColumns(List<String> columns) {
		StringBuffer sql = new StringBuffer();

		sql.append(LEFT_PARENTHIS);
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

		return sql;
	}

	@Override
	public StringBuffer getSelectSqlFromColumns(List<String> columns) {
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

	@Override
	public String getSQLTableName() {
		return this.SQL_TABLE_NAME;
	}

	@Override
	public List<String> getSQLSelectColumns() {
		return this.SELECT_COLUMNS;
	}

	@Override
	public List<String> getSQLInsertColumns() {
		return this.SQL_UPDATE_COLUMNS;
	}

	public abstract Map<String, Object> getUpdateMappings(T t);

//	public abstract String getInsertSQL(T type);

	public abstract Map<String, Object> getInsertMappings(T t);

	protected abstract boolean validateInsertObject(T type);
}
