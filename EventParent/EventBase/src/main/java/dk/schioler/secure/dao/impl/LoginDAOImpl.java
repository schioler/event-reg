package dk.schioler.secure.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import dk.schioler.secure.dao.LoginDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.table.LoginTable;
import dk.schioler.secure.table.impl.LoginTableImpl;

@Service
public class LoginDAOImpl extends AbstractSecureBaseDAOImpl<Login> implements LoginDAO {

	@Override
	public Login getSecLogin(String login) {
		LoginTable lt = (LoginTable) table;
		Map<String, Object> values = new HashMap<String, Object>();

		String secLoginSQL = lt.getSecLoginSQL(login, values);
		NamedParameterJdbcTemplate jdbcTemplate = getJDBCTemplate();

		List<Login> query = jdbcTemplate.query(secLoginSQL, values, lt.getRowMapper());

		return query.get(0);
	}

	public LoginDAOImpl() {
		super(new LoginTableImpl());
	}
//	public StringBuffer getSelectColsAndWhere() {
//		StringBuffer sql = new StringBuffer();
//		sql.append(SELECT).append(SPACE);
//		sql.append(FLD_ID).append(SEP);
//		sql.append(FLD_USER_PROFILE_ID).append(SEP);
//		sql.append(FLD_START_TS).append(SEP);
//		sql.append(FLD_END_TS).append(SEP);
//		sql.append(FLD_LOGIN).append(SEP);
//		sql.append(FLD_ROLE).append(SPACE);
//		sql.append(FROM).append(SPACE).append(TABLE).append(SPACE);
//		sql.append(WHERE).append(SPACE);
//		return sql;
//	}
//
//	@Override
//	public LoginImpl getLogin(String login) {
//		StringBuffer sql = getSelectColsAndWhere();
//		sql.append(FLD_LOGIN).append(EQ).append(BIND).append(FLD_LOGIN);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put(FLD_LOGIN, login);
//
//		RowMapper<LoginImpl> rowMapper = getRowMapper();
//
//		List<LoginImpl> query = getJDBCTemplate().query(sql.toString(), map, rowMapper);
//		LoginImpl retVal = null;
//		if (query != null) {
//			if (query.size() == 1) {
//				retVal = query.get(0);
//				List<PasswordImpl> password = passwordDAO.getPassword(retVal.getId(), false);
//				PasswordImpl pwd = password.get(0);
//				retVal.addPasswords(pwd);
//			}
//		}
//
//		return retVal;
//	}
//
//	@Override
//	public List<LoginImpl> getLogins(Integer userProfileId) {
//
//		StringBuffer sql = getSelectColsAndWhere();
//		sql.append(FLD_USER_PROFILE_ID).append(EQ).append(BIND).append(FLD_USER_PROFILE_ID);
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put(FLD_USER_PROFILE_ID, userProfileId);
//
//		RowMapper<LoginImpl> rowMapper = getRowMapper();
//
//		List<LoginImpl> query = getJDBCTemplate().query(sql.toString(), map, rowMapper);
//
//		return query;
//	}
//
//	@Override
//	public Map<String, Object> getInsertMappings(LoginImpl event) {
//		Map<String, Object> mappings = new TreeMap<String, Object>();
//		mappings.put(FLD_USER_PROFILE_ID, event.getUserProfileId());
//		mappings.put(FLD_ROLE, event.getRole().toString());
//		mappings.put(FLD_LOGIN, event.getLogin());
//
//		return mappings;
//	}
//
//	@Override
//	public Map<String, Object> getUpdateMappings(LoginImpl type) {
//		Map<String, Object> insertMappings = getInsertMappings(type);
//		insertMappings.put(FLD_ID, type.getId());
//		return insertMappings;
//	}
//
//	@Override
//	public RowMapper<LoginImpl> getRowMapper() {
//		return eventRowMapper;
//	}
//
//	private RowMapper<LoginImpl> eventRowMapper = new RowMapper<LoginImpl>() {
//
//		@Override
//		public LoginImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
//			LoginImpl login = new LoginImpl();
//
//			login.setId(rs.getInt(FLD_ID));
//			login.setStartTS(rs.getTimestamp(FLD_START_TS).toLocalDateTime());
//
//			Timestamp timestamp = rs.getTimestamp(FLD_END_TS);
//			if (timestamp != null) {
//				login.setEndTS(timestamp.toLocalDateTime());
//			}
//
//			login.setUserProfileId(rs.getInt(FLD_USER_PROFILE_ID));
//			login.setLogin(rs.getString(FLD_LOGIN));
//
//			String roleStr = rs.getString(FLD_ROLE);
//			ROLE role = RoleUtil.getROLE(roleStr);
//
//			if (role != null) {
//				login.setRole(role);
//
//			} else {
//				throw new EventBaseException("Role is not reckognized:" + roleStr);
//
//			}
//			return login;
//		}
//	};

//	@Override
//	public LoginImpl getActiveLogin(String login) {
//		return null;
//	}

	@Override
	protected boolean validateInsertObject(Login type) {
		boolean retVal = false;
		if (type != null) {
			if (StringUtils.isNotBlank(type.getLogin())) {
				if (type.getRole() != null) {
					retVal = true;
				}
			}
		}
		return retVal;
	}

}
