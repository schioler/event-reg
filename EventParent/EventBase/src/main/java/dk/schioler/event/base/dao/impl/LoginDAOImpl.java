package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.EventBaseException;
import dk.schioler.event.base.dao.LoginDAO;
import dk.schioler.event.base.entity.Login;
import dk.schioler.event.base.entity.ROLE;
import dk.schioler.event.base.entity.RoleUtil;

@Service
public class LoginDAOImpl extends AbstractTSDAOImpl<Login> implements LoginDAO {
	public static final String TABLE = "LOGIN";

	public static final String FLD_ID = COL_ID;
	public static final String FLD_START_TS = "START_TS";
	public static final String FLD_END_TS = "END_TS";
	public static final String FLD_USER_PROFILE_ID = "USER_PROFILE_ID";
	public static final String FLD_ROLE = "ROLE";
	public static final String FLD_LOGIN = "LOGIN";

	private static final List<String> EVENT_COLS;

	static {
		List<String> cols = new ArrayList<String>();
		cols.add(FLD_ID);
		cols.add(FLD_START_TS);
		cols.add(FLD_END_TS);
		cols.add(FLD_USER_PROFILE_ID);
		cols.add(FLD_ROLE);
		cols.add(FLD_LOGIN);

		EVENT_COLS = Collections.unmodifiableList(cols);
	}

	public LoginDAOImpl() {

	}

	@Override
	public String getInsertSQL(Login event) {
//		boolean useEventDate = event != null && event.geteventts() != null;
		StringBuffer sb = new StringBuffer();

		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
		sb.append(LEFT_PARENTHIS);

		sb.append(FLD_USER_PROFILE_ID).append(SEP);
		sb.append(FLD_ROLE).append(SEP);
		sb.append(FLD_LOGIN);

		sb.append(RIGHT_PARENTHIS).append(SPACE);
		sb.append(VALUES).append(SPACE);
		sb.append(LEFT_PARENTHIS);

		sb.append(BIND).append(FLD_USER_PROFILE_ID).append(SEP);
		sb.append(BIND).append(FLD_ROLE).append(SEP);
		sb.append(BIND).append(FLD_LOGIN);

		sb.append(RIGHT_PARENTHIS).append(SPACE);

		return sb.toString();
	}

	@Override
	public Map<String, Object> getInsertMappings(Login event) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_USER_PROFILE_ID, event.getUserProfileId());
		mappings.put(FLD_ROLE, event.getRole().toString());
		mappings.put(FLD_LOGIN, event.getLogin());

		return mappings;
	}

	@Override
	public String getUpdateSQL() {

		return null;
	}

	@Override
	public Map<String, Object> getUpdateMappings(Login type) {
		Map<String, Object> insertMappings = getInsertMappings(type);
		insertMappings.put(FLD_ID, type.getId());
		return insertMappings;
	}

	@Override
	public RowMapper<Login> getRowMapper() {
		return eventRowMapper;
	}

	private RowMapper<Login> eventRowMapper = new RowMapper<Login>() {

		@Override
		public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
			Login login = new Login();
			
			login.setId(rs.getInt(FLD_ID));
			login.setStartTS(rs.getTimestamp(FLD_START_TS).toLocalDateTime());
			
			Timestamp timestamp = rs.getTimestamp(FLD_END_TS);
			if (timestamp!= null) {
				login.setEndTS(timestamp.toLocalDateTime());				
			}
			
			login.setUserProfileId(rs.getInt(FLD_USER_PROFILE_ID));
			login.setLogin(rs.getString(FLD_LOGIN));

			
			String roleStr = rs.getString(FLD_ROLE);
			ROLE role = RoleUtil.getROLE(roleStr);
			
			if(role != null) {
				login.setRole(role);
			
			} else {
				throw new EventBaseException("Role is not reckognized:"+roleStr);
				
			}
			return login;
		}	
	};

	@Override
	public List<String> getSelectColumns() {
		return EVENT_COLS;
	}

	@Override
	public void refreshCache() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOrderBy() {

		return FLD_LOGIN;
	}

	@Override
	public String getTableName() {

		return TABLE;
	}

	
}
