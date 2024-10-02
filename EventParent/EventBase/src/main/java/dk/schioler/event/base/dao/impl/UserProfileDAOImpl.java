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

import dk.schioler.event.base.dao.UserProfileDAO;
import dk.schioler.event.base.entity.UserProfile;

@Service
public class UserProfileDAOImpl extends AbstractTSDAOImpl<UserProfile> implements UserProfileDAO {
	public static final String TABLE = "USER_PROFILE";

	public static final String FLD_ID = COL_ID;
	public static final String FLD_START_TS = "START_TS";
	public static final String FLD_END_TS = "END_TS";
	public static final String FLD_FIRST_NAME = "FIRST_NAME";
	public static final String FLD_LAST_NAME = "LAST_NAME";
	public static final String FLD_PRIMARY_PHONE = "PRIMARY_PHONE";
	public static final String FLD_PRIMARY_STREET_1 = "PRIMARY_STREET_1";
	public static final String FLD_PRIMARY_STREET_2 = "PRIMARY_STREET_2";
	public static final String FLD_PRIMARY_POSTAL_CODE = "PRIMARY_POSTAL_CODE";
	public static final String FLD_PRIMARY_CITY = "PRIMARY_CITY"; 
	public static final String FLD_PRIMARY_COUNTRY = "PRIMARY_COUNTRY";

	private static final List<String> USER_PROFILE_COLS;

	static {
		List<String> cols = new ArrayList<String>();
		cols.add(FLD_ID);
		cols.add(FLD_START_TS);
		cols.add(FLD_END_TS);
		cols.add(FLD_FIRST_NAME);
		cols.add(FLD_LAST_NAME);
		cols.add(FLD_PRIMARY_PHONE);
		cols.add(FLD_PRIMARY_STREET_1);
		cols.add(FLD_PRIMARY_STREET_2);
		cols.add(FLD_PRIMARY_POSTAL_CODE);
		cols.add(FLD_PRIMARY_CITY);
		cols.add(FLD_PRIMARY_COUNTRY);
		
		USER_PROFILE_COLS = Collections.unmodifiableList(cols);
	}

	public UserProfileDAOImpl() {

	}

	@Override
	public String getInsertSQL(UserProfile event) {
		StringBuffer sb = new StringBuffer();

		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
		sb.append(LEFT_PARENTHIS);
				
		sb.append(FLD_FIRST_NAME).append(SEP);
		sb.append(FLD_LAST_NAME).append(SEP);
		sb.append(FLD_PRIMARY_PHONE).append(SEP);
		sb.append(FLD_PRIMARY_STREET_1).append(SEP);
		sb.append(FLD_PRIMARY_STREET_2).append(SEP);
		sb.append(FLD_PRIMARY_POSTAL_CODE).append(SEP);
		sb.append(FLD_PRIMARY_CITY).append(SEP);
		sb.append(FLD_PRIMARY_COUNTRY);
		
		sb.append(RIGHT_PARENTHIS).append(SPACE);
		sb.append(VALUES).append(SPACE);
		sb.append(LEFT_PARENTHIS);
		
		sb.append(BIND).append(FLD_FIRST_NAME).append(SEP);
		sb.append(BIND).append(FLD_LAST_NAME).append(SEP);
		sb.append(BIND).append(FLD_PRIMARY_PHONE).append(SEP);
		sb.append(BIND).append(FLD_PRIMARY_STREET_1).append(SEP);
		sb.append(BIND).append(FLD_PRIMARY_STREET_2).append(SEP);
		sb.append(BIND).append(FLD_PRIMARY_POSTAL_CODE).append(SEP);
		sb.append(BIND).append(FLD_PRIMARY_CITY).append(SEP);
		sb.append(BIND).append(FLD_PRIMARY_COUNTRY);
		
		sb.append(RIGHT_PARENTHIS).append(SPACE);

		return sb.toString();
	}

	@Override
	public Map<String, Object> getInsertMappings(UserProfile event) {
		Map<String, Object> mappings = new TreeMap<String, Object>();
		mappings.put(FLD_FIRST_NAME, event.getFirstName());
		mappings.put(FLD_LAST_NAME, event.getLastName());
		mappings.put(FLD_PRIMARY_PHONE, event.getPrimaryPhone());
		mappings.put(FLD_PRIMARY_STREET_1, event.getPrimaryStreet1());
		mappings.put(FLD_PRIMARY_STREET_2, event.getPrimaryStreet2());
		mappings.put(FLD_PRIMARY_POSTAL_CODE, event.getPrimaryPostalCode());
		mappings.put(FLD_PRIMARY_CITY, event.getPrimaryCity());
		mappings.put(FLD_PRIMARY_COUNTRY, event.getPrimaryCountry());

		return mappings;
	}

	@Override
	public String getUpdateSQL() {

		return null;
	}

	@Override
	public Map<String, Object> getUpdateMappings(UserProfile type) {
		Map<String,Object> insertMappings = getInsertMappings(type);
		insertMappings.put(FLD_ID, type.getId());
		return insertMappings;
	}

	@Override
	public RowMapper<UserProfile> getRowMapper() {
		return userProfileMapper;
	}

	private RowMapper<UserProfile> userProfileMapper = new RowMapper<UserProfile>() {

		@Override
		public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserProfile eventTmpl = new UserProfile();
			
			eventTmpl.setId(rs.getInt(FLD_ID));
			eventTmpl.setStartTS(rs.getTimestamp(FLD_START_TS).toLocalDateTime());
			
			Timestamp timestamp = rs.getTimestamp(FLD_END_TS);
			if (timestamp!= null) {
				eventTmpl.setEndTS(timestamp.toLocalDateTime());				
			}
			
			eventTmpl.setFirstName(rs.getString(FLD_FIRST_NAME));
			eventTmpl.setLastName(rs.getString(FLD_LAST_NAME));
			eventTmpl.setPrimaryPhone(rs.getString(FLD_PRIMARY_PHONE));
			
			eventTmpl.setPrimaryStreet1(rs.getString(FLD_PRIMARY_STREET_1));
			eventTmpl.setPrimaryStreet2(rs.getString(FLD_PRIMARY_STREET_2));
			
			eventTmpl.setPrimaryCity(rs.getString(FLD_PRIMARY_CITY));
			eventTmpl.setPrimaryPostalCode(rs.getString(FLD_PRIMARY_POSTAL_CODE));
			eventTmpl.setPrimaryCountry(rs.getString(FLD_PRIMARY_COUNTRY));
			return eventTmpl;
		}

	};

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public List<String> getSelectColumns() {
		return USER_PROFILE_COLS;
	}

	@Override
	public void refreshCache() {
		// no cache

	}

	@Override
	public String getOrderBy() {

		return FLD_FIRST_NAME;
	}

	
}
