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

import dk.schioler.event.base.dao.PasswordDAO;
import dk.schioler.event.base.entity.Password;

@Service
public class PasswordDAOImpl extends AbstractTSDAOImpl<Password> implements PasswordDAO {
	public static final String TABLE = "PASSWORD";

	public static final String FLD_ID = COL_ID;
	public static final String FLD_START_TS = "START_TS";
	public static final String FLD_END_TS = "END_TS";
	public static final String FLD_LOGIN_ID = "LOGIN_ID";
	public static final String FLD_PWD = "PWD";

	private static final List<String> EVENT_COLS;

	static {
		List<String> cols = new ArrayList<String>();
		cols.add(FLD_ID);
		cols.add(FLD_START_TS);
		cols.add(FLD_END_TS);
		cols.add(FLD_LOGIN_ID);
		cols.add(FLD_PWD);

		EVENT_COLS = Collections.unmodifiableList(cols);
	}

	public PasswordDAOImpl() {

	}

	@Override
	public String getInsertSQL(Password event) {
		StringBuffer sb = new StringBuffer();

		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
		sb.append(LEFT_PARENTHIS);

		sb.append(FLD_LOGIN_ID).append(SEP);
		sb.append(FLD_PWD);

		sb.append(RIGHT_PARENTHIS).append(SPACE);
		sb.append(VALUES).append(SPACE);
		sb.append(LEFT_PARENTHIS);

		sb.append(BIND).append(FLD_LOGIN_ID).append(SEP);
		sb.append(BIND).append(FLD_PWD);

		sb.append(RIGHT_PARENTHIS).append(SPACE);

		return sb.toString();
	}

	@Override
	public Map<String, Object> getInsertMappings(Password password) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_LOGIN_ID, password.getLoginId());

		mappings.put(FLD_PWD, password.getPwd());

		return mappings;
	}

	@Override
	public String getUpdateSQL() {

		return null;
	}

	@Override
	public Map<String, Object> getUpdateMappings(Password type) {
		Map<String, Object> insertMappings = getInsertMappings(type);
		insertMappings.put(FLD_ID, type.getId());
		return insertMappings;
	}

	@Override
	public RowMapper<Password> getRowMapper() {
		return eventRowMapper;
	}

	private RowMapper<Password> eventRowMapper = new RowMapper<Password>() {

		@Override
		public Password mapRow(ResultSet rs, int rowNum) throws SQLException {
			Password pwd = new Password();

			pwd.setId(rs.getInt(FLD_ID));
			pwd.setStartTS(rs.getTimestamp(FLD_START_TS).toLocalDateTime());

			Timestamp timestamp = rs.getTimestamp(FLD_END_TS);
			if (timestamp != null) {
				pwd.setEndTS(timestamp.toLocalDateTime());
			}

			pwd.setLoginId(rs.getInt(FLD_LOGIN_ID));

			pwd.setPwd(rs.getString(FLD_PWD));
			return pwd;
		}

	};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public List<String> getSelectColumns() {
		return EVENT_COLS;
	}

	@Override
	public void refreshCache() {
		// no cache on password

	}

	@Override
	public String getOrderBy() {

		return FLD_LOGIN_ID;
	}

}
