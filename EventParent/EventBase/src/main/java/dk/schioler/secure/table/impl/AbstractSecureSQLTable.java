package dk.schioler.secure.table.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.secure.entity.SecureEntity;
import dk.schioler.secure.table.SecureTable;

public abstract class AbstractSecureSQLTable<T extends SecureEntity> implements SecureTable<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public StringBuffer getDeleteSQL() {
		StringBuffer sb = new StringBuffer();
		sb.append(DELETE).append(SPACE);
		sb.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sb.append(WHERE).append(SPACE);
		sb.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

		return sb;
	}

	@Override
	public Map<String, Object> getIdMapping(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FLD_ID, id);
		return map;
	}

	@Override
	public StringBuffer getInsertSQL() {
		List<String> updateColumns = getUpdateColumns();
		StringBuffer sql = new StringBuffer();
		sql.append(INSERT_INTO).append(SPACE).append(getTableName()).append(SPACE);

		StringBuffer insertSqlFromColumns = getInsertSqlFromColumns(updateColumns);
		sql.append(insertSqlFromColumns).append(SPACE);
		logger.debug("sql=" + sql.toString());
		return sql;
	}

	@Override
	public StringBuffer getUpdateSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append(UPDATE).append(SPACE).append(getTableName()).append(SPACE);
		sql.append(SET).append(SPACE);

		List<String> updateColumns = getUpdateColumns();
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
	public StringBuffer getRetrieveSQL(Map<String, Object> criteria, int maxRows) {
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);
		StringBuffer columns = getSelectSqlFromColumns(getSelectColumns());
		sql.append(columns).append(SPACE);
		sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);

//		if (criteria != null) {
//			if(!criteria.isEmpty()) {
//				sql.append(WHERE).
//			}
//		}

		List<String> orderBy2 = getOrderBy();
		String orderBy = orderBy2.get(0);
		if (StringUtils.isNoneEmpty(orderBy)) {
			sql.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
		}

		return sql;
	}

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
	public String getFromIdSQL(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);

		StringBuffer columns = getSelectSqlFromColumns(getSelectColumns());
		sql.append(columns).append(SPACE);
		sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID).append(SPACE);
		return sql.toString();
	}

	public abstract List<String> getSelectColumns();

	public abstract List<String> getUpdateColumns();

	public abstract List<String> getOrderBy();

}
