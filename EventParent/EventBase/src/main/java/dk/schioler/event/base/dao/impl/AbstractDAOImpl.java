package dk.schioler.event.base.dao.impl;

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
import dk.schioler.event.base.dao.SQLConstructs;
import dk.schioler.event.base.entity.AbstractEntity;

@Service
public abstract class AbstractDAOImpl<T extends AbstractEntity> implements BaseEventDAO<T>, SQLConstructs {

	protected final int CACHE_VALID_HOURS = 1;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractDAOImpl() {

	}

	protected NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		logger.trace("setDataSource:" + jdbcTemplate);
	}

	@Override
	public T insert(T type) {
		try {
			logger.trace("insert of " + type.toString());
			String insertSQL = getInsertSQL(type);
			Map<String, Object> map = this.getInsertMappings(type);
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValues(map);

			KeyHolder keyHolder = new GeneratedKeyHolder();

			int count = jdbcTemplate.update(insertSQL, paramSource, keyHolder);

			logger.trace("insert added " + count + " rows");

			Map<String, Object> keys = keyHolder.getKeys();

			for (Entry<String, Object> entry : keys.entrySet()) {
				logger.trace("keyHolder.keys=" + entry.toString());
			}
			Integer id = (Integer) keys.get("id");
			if (id != null) {
				type.setId(id);
			}
			logger.trace("type=" + type);

			return type;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

	}

	@Override
	public void update(T type) {
		jdbcTemplate.update(getUpdateSQL(), getUpdateMappings(type));

	}

	@Override
	public void delete(Integer id) {

		StringBuffer sb = new StringBuffer();
		sb.append(DELETE).append(SPACE).append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sb.append(WHERE).append(SPACE).append(COL_ID).append(EQ).append(BIND).append(COL_ID);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(COL_ID, id);
		jdbcTemplate.update(sb.toString(), paramSource);
	}

	@Override
	public List<T> retrieve(Map<String, Object> criteria) {
		return null;
	}

	protected StringBuffer getSelectSQLStart() {
		StringBuffer sb = new StringBuffer();
		sb.append(SELECT).append(SPACE);
		List<String> columns = getSelectColumns();
		int size = columns.size();
		for (int i = 0; i < size;) {
			String column = columns.get(i);
			sb.append(column);
			i++;
			if (i < size) {
				sb.append(SEP);
			} else {
				sb.append(SPACE);
			}
		}
		sb.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		return sb;
	}

	@Override
	public List<T> lookup() {
		StringBuffer sb = getSelectSQLStart();
		
		String orderBy = getOrderBy();
		if(StringUtils.isNoneEmpty(orderBy)) {
			sb.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
		}
		List<T> query = jdbcTemplate.query(sb.toString(), getRowMapper());

		return query;
	}

	@Override
	public T get(Integer id) {
		StringBuffer sql = getSelectSQLStart();
		sql.append(WHERE).append(SPACE);
		sql.append(COL_ID).append(EQ).append(BIND).append(COL_ID);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(COL_ID, id);

		List<T> query = jdbcTemplate.query(sql.toString(), paramSource, getRowMapper());

		return query.get(0);
	}

	public abstract List<String> getSelectColumns();

	public abstract String getInsertSQL(T type);

	public abstract Map<String, Object> getInsertMappings(T t);

	public abstract String getUpdateSQL();

	public abstract Map<String, Object> getUpdateMappings(T t);

	public abstract String getOrderBy(); 
}
