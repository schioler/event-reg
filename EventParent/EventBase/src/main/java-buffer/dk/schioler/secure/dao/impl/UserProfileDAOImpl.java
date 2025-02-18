package dk.schioler.secure.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.secure.dao.UserProfileDAO;
import dk.schioler.secure.entity.UserProfile;
import dk.schioler.secure.table.impl.UserProfileTableImpl;

@Service
public class UserProfileDAOImpl extends AbstractSecureBaseDAOImpl<UserProfile> implements UserProfileDAO {

	public UserProfileDAOImpl() {
		super(new UserProfileTableImpl());
	}

	
	
//	@Override
//	public String getInsertSQL(UserProfile event) {
//		StringBuffer sb = new StringBuffer();
//
//		sb.append(INSERT_INTO).append(TABLE).append(SPACE);
//		sb.append(LEFT_PARENTHIS);
//				
//		sb.append(FLD_FIRST_NAME).append(SEP);
//		sb.append(FLD_LAST_NAME).append(SEP);
//		sb.append(FLD_PRIMARY_PHONE).append(SEP);
//		sb.append(FLD_PRIMARY_STREET_1).append(SEP);
//		sb.append(FLD_PRIMARY_STREET_2).append(SEP);
//		sb.append(FLD_PRIMARY_POSTAL_CODE).append(SEP);
//		sb.append(FLD_PRIMARY_CITY).append(SEP);
//		sb.append(FLD_PRIMARY_COUNTRY);
//		
//		sb.append(RIGHT_PARENTHIS).append(SPACE);
//		sb.append(VALUES).append(SPACE);
//		sb.append(LEFT_PARENTHIS);
//		
//		sb.append(BIND).append(FLD_FIRST_NAME).append(SEP);
//		sb.append(BIND).append(FLD_LAST_NAME).append(SEP);
//		sb.append(BIND).append(FLD_PRIMARY_PHONE).append(SEP);
//		sb.append(BIND).append(FLD_PRIMARY_STREET_1).append(SEP);
//		sb.append(BIND).append(FLD_PRIMARY_STREET_2).append(SEP);
//		sb.append(BIND).append(FLD_PRIMARY_POSTAL_CODE).append(SEP);
//		sb.append(BIND).append(FLD_PRIMARY_CITY).append(SEP);
//		sb.append(BIND).append(FLD_PRIMARY_COUNTRY);
//		
//		sb.append(RIGHT_PARENTHIS).append(SPACE);
//
//		return sb.toString();
//	}

//	@Override
//	public Map<String, Object> getInsertMappings(UserProfile event) {
//		Map<String, Object> mappings = new TreeMap<String, Object>();
//		mappings.put(FLD_FIRST_NAME, event.getFirstName());
//		mappings.put(FLD_LAST_NAME, event.getLastName());
//		mappings.put(FLD_PRIMARY_PHONE, event.getPrimaryPhone());
//		mappings.put(FLD_PRIMARY_STREET_1, event.getPrimaryStreet1());
//		mappings.put(FLD_PRIMARY_STREET_2, event.getPrimaryStreet2());
//		mappings.put(FLD_PRIMARY_POSTAL_CODE, event.getPrimaryPostalCode());
//		mappings.put(FLD_PRIMARY_CITY, event.getPrimaryCity());
//		mappings.put(FLD_PRIMARY_COUNTRY, event.getPrimaryCountry());
//
//		return mappings;
//	}

//	@Override
//	public String getUpdateSQL() {
//
//		return null;
//	}

//	@Override
//	public Map<String, Object> getUpdateMappings(UserProfile type) {
//		Map<String, Object> insertMappings = getInsertMappings(type);
//		insertMappings.put(FLD_ID, type.getId());
//		return insertMappings;
//	}

//	@Override
//	public RowMapper<UserProfileImpl> getRowMapper() {
//		return userProfileMapper;
//	}
//
//	private RowMapper<UserProfileImpl> userProfileMapper = new RowMapper<UserProfileImpl>() {
//
//		@Override
//		public UserProfileImpl mapRow(ResultSet rs, int rowNum) throws SQLException {
//			UserProfileImpl eventTmpl = new UserProfileImpl();
//
//			eventTmpl.setId(rs.getInt(FLD_ID));
//			
//			eventTmpl.setStartTS(rs.getTimestamp(FLD_START_TS).toLocalDateTime());
//
//			Timestamp timestamp = rs.getTimestamp(FLD_END_TS);
//			if (timestamp != null) {
//				eventTmpl.setEndTS(timestamp.toLocalDateTime());
//			}
//
//			eventTmpl.setFirstName(rs.getString(FLD_FIRST_NAME));
//			eventTmpl.setLastName(rs.getString(FLD_LAST_NAME));
//			eventTmpl.setPrimaryPhone(rs.getString(FLD_PRIMARY_PHONE));
//
//			eventTmpl.setPrimaryStreet1(rs.getString(FLD_PRIMARY_STREET_1));
//			eventTmpl.setPrimaryStreet2(rs.getString(FLD_PRIMARY_STREET_2));
//
//			eventTmpl.setPrimaryCity(rs.getString(FLD_PRIMARY_CITY));
//			eventTmpl.setPrimaryPostalCode(rs.getString(FLD_PRIMARY_POSTAL_CODE));
//			eventTmpl.setPrimaryCountry(rs.getString(FLD_PRIMARY_COUNTRY));
//			return eventTmpl;
//		}
//
//	};

//	@Override
//	public String getSQLTableName() {
//		return TABLE;
//	}
//
//	@Override
//	public List<String> getSQLSelectColumns() {
//		return SELECT_COLS;
//	}
//
//	@Override
//	public void refreshCache() {
//		// no cache
//
//	}
//
//	@Override
//	public String getOrderBy() {
//		return FLD_LAST_NAME;
//	}

	@Override
	protected boolean validateInsertObject(UserProfile type) {
		boolean retVal = false;
		if (type != null) {
			if (StringUtils.isNotBlank(type.getFirstName())) {
				if (StringUtils.isNotBlank(type.getLastName())) {
					retVal = true;

				}
			}
		}
		return retVal;
	}

}
