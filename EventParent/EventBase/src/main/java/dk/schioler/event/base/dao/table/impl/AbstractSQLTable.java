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
import dk.schioler.event.base.dao.criteria.AbstractCriteria;
import dk.schioler.event.base.dao.table.ISQLTable;
import dk.schioler.event.base.entity.AbstractEntity;

public abstract class AbstractSQLTable<T extends AbstractEntity> implements ISQLTable<T> {

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
	public Map<String, Object> getIdMapping(Integer id, Integer loginId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(FLD_ID, id);
		map.put(FLD_LOGIN_ID, loginId);
		return map;
	}

	@Override
	public StringBuffer getInsertSQL() {
		List<String> columns = getUpdateColumns();

		StringBuffer sql = new StringBuffer();

		sql.append(INSERT_INTO).append(SPACE).append(getTableName()).append(SPACE);

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

	@Override
	public StringBuffer getRetrieveSQL(AbstractCriteria criteria, int maxRows) {
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);
		StringBuffer columns = getSelectSqlFromColumns(getSelectColumns());
		sql.append(columns).append(SPACE);
		sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sql.append(WHERE).append(SPACE);
//		sql.append(FLD_LOGIN_ID).append(EQ).append(BIND).append(FLD_LOGIN_ID).append(SPACE);

		List<StringBuffer> retrieveCriteriaFromInstance = buildRetrieveCriteriaFromInstance(criteria);
		int size = retrieveCriteriaFromInstance.size();

		for (int j = 0; j < retrieveCriteriaFromInstance.size(); j++) {
			StringBuffer c = retrieveCriteriaFromInstance.get(j);
			sql.append(c);
			if (j < (size - 1)) {
				sql.append(SPACE).append(AND).append(SPACE);
			}
		}

		List<String> orderBy2 = getOrderBy();
		String orderBy = orderBy2.get(0);
		if (StringUtils.isNoneEmpty(orderBy)) {
			sql.append(SPACE).append(ORDER_BY).append(SPACE).append(orderBy).append(SPACE);
		}

		return sql;
	}

	public List<StringBuffer> buildRetrieveCriteriaFromInstance(AbstractCriteria criteria) {
		List<StringBuffer> critList = new ArrayList<StringBuffer>();

		Integer id = criteria.getId();
		List<Integer> loginIds = criteria.getLoginIds();
		String name = criteria.getName();
		

		if (id != null) {
			StringBuffer c = new StringBuffer();
			c.append(SPACE).append(FLD_ID).append(EQ).append(BIND).append(FLD_ID).append(SPACE);
			critList.add(c);
		}
		
		if(loginIds != null && loginIds.size()>0) {
			StringBuffer c = new StringBuffer();
			c.append(SPACE).append(FLD_LOGIN_ID).append(SPACE).append(IN).append(SPACE).append(LEFT_PARENTHIS);
			
			int size = loginIds.size();			
			for (int i = 0; i < size; i++) {
				c.append(BIND).append(FLD_LOGIN_ID).append(i);
				if (i<size-1) {
					c.append(SEP);
				}
			}
			c.append(RIGHT_PARENTHIS).append(SPACE);
			
			critList.add(c);
		}
		
		if(StringUtils.isNotBlank(name)) {
			StringBuffer c = new StringBuffer();
			c.append(SPACE).append(FLD_NAME).append(SPACE).append(LIKE).append(SPACE);
			c.append(CONCAT).append(LEFT_PARENTHIS);
			c.append(HYPHEN).append(SQL_WILDCARD).append(HYPHEN).append(SEP);
			c.append(BIND).append(FLD_NAME).append(SEP);
			c.append(HYPHEN).append(SQL_WILDCARD).append(HYPHEN);
			c.append(RIGHT_PARENTHIS);
			critList.add(c);
		}
		

		List<StringBuffer> specificCriteria = addSpecificRetrieveCriteria(criteria);
		critList.addAll(specificCriteria);
		return critList;
	}

	
	public abstract List<StringBuffer> addSpecificRetrieveCriteria(AbstractCriteria criteria);
	public abstract Map<String, Object> addSpecificRetrieveMappings(AbstractCriteria criteria);
	
	@Override
	public Map<String, Object> getRetrieveMappings(AbstractCriteria criteria) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (criteria == null) {
			throw new EventDAOException("criteria can not be null");
		}
		if (criteria.getId() != null) {
			map.put(FLD_ID, criteria.getId());
		}
		List<Integer> loginIds = criteria.getLoginIds();
		if (loginIds != null && !loginIds.isEmpty()) {
			for (int i = 0; i < loginIds.size(); i++) {
				map.put(FLD_LOGIN_ID + i, loginIds.get(i));
			}
		}

		if (StringUtils.isNotBlank(criteria.getName())) {
			map.put(FLD_NAME, criteria.getName());
		}
		map.putAll(addSpecificRetrieveMappings(criteria));
		logger.debug("getRetriveMapping=" + map);
		return map;

		
	}


	@Override
	public String getFromIdSQL() {
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);

		StringBuffer columns = getSelectSqlFromColumns(getSelectColumns());
		sql.append(columns).append(SPACE);
		sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID).append(SPACE);
		sql.append(AND).append(SPACE).append(FLD_LOGIN_ID).append(EQ).append(BIND).append(FLD_LOGIN_ID).append(SPACE);
		return sql.toString();
	}

	public abstract List<String> getSelectColumns();

	public abstract List<String> getUpdateColumns();

	public abstract List<String> getOrderBy();

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

}
