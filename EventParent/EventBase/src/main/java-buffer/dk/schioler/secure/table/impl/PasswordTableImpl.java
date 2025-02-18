package dk.schioler.secure.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.impl.PasswordImpl;
import dk.schioler.secure.table.PasswordTable;

public class PasswordTableImpl extends AbstractSecureSQLTable<Password> implements PasswordTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();

	static {
		insertColumns.add(FLD_LOGIN_ID);

		insertColumns.add(FLD_START_TS);
//		insertColumns.add(FLD_END_TS);

		insertColumns.add(FLD_PWD);

		selectColumns.add(FLD_ID);
		selectColumns.add(FLD_END_TS);
		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_LOGIN_ID);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Map<String, Object> getInsertMappings(Password event) {
		Map<String, Object> mappings = super.getInsertMappings(event);

		mappings.put(FLD_LOGIN_ID, event.getLoginId());

		
		mappings.put(FLD_PWD, event.getPwd());

		return mappings;

	}

	@Override
	public Map<String, Object> getUpdatetMappings(Password type) {
		Map<String, Object> map = getInsertMappings(type);
		map.put(FLD_ID, type.getId());

		map.put(FLD_END_TS, type.getEndTS());

		return map;
	}

	@Override
	public RowMapper<Password> getRowMapper() {
		return eventRowMapper;
	}

	private RowMapper<Password> eventRowMapper = new RowMapper<Password>() {

		@Override
		public Password mapRow(ResultSet rs, int rowNum) throws SQLException {
			Password eventTmpl = new PasswordImpl();
			eventTmpl.setId(rs.getInt(FLD_ID));

			Timestamp startTS = rs.getTimestamp(FLD_START_TS);
			if (startTS != null) {
				eventTmpl.setStartTS(startTS.toLocalDateTime());
			}

			Timestamp endTS = rs.getTimestamp(FLD_END_TS);
			if (endTS != null) {
				eventTmpl.setStartTS(endTS.toLocalDateTime());
			}

			eventTmpl.setLoginId(rs.getInt(FLD_LOGIN_ID));

			eventTmpl.setPwd(rs.getString(FLD_PWD));

			return eventTmpl;
		}

	};

	@Override
	public StringBuffer getPasswordFromLoginSQL(Integer loginId, Map<String, Object> values, boolean includeHistoric) {
		StringBuffer sql = new StringBuffer();

		sql.append(SELECT).append(SPACE);
		sql.append(getSelectSqlFromColumns(selectColumns)).append(SPACE);
		sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_LOGIN_ID).append(EQ).append(BIND).append(FLD_LOGIN_ID);
		if (!includeHistoric) {
			sql.append(SPACE).append(AND).append(SPACE);
			sql.append(FLD_END_TS).append(SPACE).append(ISNULL);
		}

		values.put(FLD_LOGIN_ID, loginId);
		
		return sql;
	}

	@Override
	public List<String> getSelectColumns() {
		return selectColumns;
	}

	@Override
	public List<String> getUpdateColumns() {
		return insertColumns;
	}

	@Override
	public List<String> getOrderBy() {
		List<String> orderBy = new ArrayList<String>();
		orderBy.add(FLD_LOGIN_ID);

		return orderBy;
	}

	@Override
	public Map<String, Object> getRetrieveMappings(Password type) {
		Map<String,Object> map = super.getCommonMappings(type);
		Integer loginId = type.getLoginId();
		String pwd = type.getPwd();
		if (loginId != null) {
			map.put(FLD_LOGIN_ID, loginId);
		}
		if (StringUtils.isNotBlank(pwd)) {
			map.put(FLD_PWD, pwd);
		}
		
		return map;
	}

}
