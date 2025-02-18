package dk.schioler.secure.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.ROLE;
import dk.schioler.secure.entity.RoleUtil;
import dk.schioler.secure.entity.impl.LoginImpl;
import dk.schioler.secure.table.LoginTable;
import io.micrometer.common.util.StringUtils;

public class LoginTableImpl extends AbstractSecureSQLTable<Login> implements LoginTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();

	static {
		insertColumns.add(FLD_USER_PROFILE_ID);

		insertColumns.add(FLD_START_TS);

		insertColumns.add(FLD_LOGIN);
		insertColumns.add(FLD_ROLE);

//		insertColumns.add(FLD_DOSE);
//		insertColumns.add(FLD_UNIT);

		selectColumns.add(FLD_ID);
		selectColumns.add(FLD_END_TS);

		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_LOGIN);
	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Map<String, Object> getInsertMappings(Login event) {
		Map<String, Object> mappings = super.getInsertMappings(event);
		mappings.put(FLD_USER_PROFILE_ID, event.getUserProfile().getId());

		mappings.put(FLD_LOGIN, event.getLogin());
		String roleAsString = RoleUtil.getRoleAsString(event.getRole());
		
		mappings.put(FLD_ROLE,roleAsString );

		return mappings;

	}

	@Override
	public Map<String, Object> getUpdatetMappings(Login type) {
		Map<String, Object> map = getInsertMappings(type);
		map.put(FLD_ID, type.getId());
		map.put(FLD_END_TS, type.getEndTS());
		return map;
	}
	@Override
	public Map<String, Object> getRetrieveMappings(Login criteria) {
		Map<String, Object> map  = super.getCommonMappings(criteria);
		
		
		String login = criteria.getLogin();
		ROLE role = criteria.getRole();
		
		Integer userProfileId = criteria.getUserProfileId();
		
		
		if (StringUtils.isNotEmpty(login)) {
			map.put(FLD_LOGIN, login);
		}
		if (role != null) {
			map.put(FLD_ROLE, RoleUtil.getRoleAsString(role));
		}
		if (userProfileId != null) {
			map.put(FLD_USER_PROFILE_ID, userProfileId);
		}
		
		return map;
	}

	@Override
	public RowMapper<Login> getRowMapper() {
		return eventRowMapper;
	}

	private RowMapper<Login> eventRowMapper = new RowMapper<Login>() {

		@Override
		public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
			Login eventTmpl = new LoginImpl();
			eventTmpl.setId(rs.getInt(FLD_ID));
			eventTmpl.setUserProfileId(rs.getInt(FLD_USER_PROFILE_ID));

			eventTmpl.setLogin(rs.getString(FLD_LOGIN));

			String role = rs.getString(FLD_ROLE);
			ROLE role2 = RoleUtil.getROLE(role);
			eventTmpl.setRole(role2);

			Timestamp startTS = rs.getTimestamp(FLD_START_TS);
			if (startTS != null) {
				eventTmpl.setStartTS(startTS.toLocalDateTime());
			}

			Timestamp endTS = rs.getTimestamp(FLD_END_TS);
			if (endTS != null) {
				eventTmpl.setEndTS(endTS.toLocalDateTime());
			}

			return eventTmpl;
		}

	};

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
		orderBy.add(FLD_LOGIN);

		return orderBy;
	}

	@Override
	public String getSecLoginSQL(String login, Map<String, Object> values) {
		values.put(FLD_LOGIN, login);
		StringBuffer sql = new StringBuffer();
		sql.append(SELECT).append(SPACE);
		sql.append(getSelectSqlFromColumns(selectColumns)).append(SPACE);
		sql.append(FROM).append(SPACE).append(getTableName()).append(SPACE);
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_LOGIN).append(EQ).append(BIND).append(FLD_LOGIN).append(SPACE);
//		sql.append(AND).append(SPACE);
//		sql.append(FLD_ROLE).append(EQ).append("'").append(RoleUtil.getRoleAsString(role)).append("'").append(SPACE);
		logger.debug("getSecLoginSQL:sql=" + sql.toString());
		
		return sql.toString();
	}


}
