package dk.schioler.secure.table.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.secure.entity.UserProfile;
import dk.schioler.secure.entity.impl.UserProfileImpl;
import dk.schioler.secure.table.UserProfileTable;

public class UserProfileTableImpl extends AbstractSecureSQLTable<UserProfile> implements UserProfileTable {

	protected static List<String> insertColumns = new ArrayList<String>();
	protected static List<String> selectColumns = new ArrayList<String>();
	protected static List<String> orderByColumns = new ArrayList<String>();

	static {
//		insertColumns.add(FLD_START_TS);
//		insertColumns.add(FLD_END_TS);
		insertColumns.add(FLD_START_TS);

		insertColumns.add(FLD_FIRST_NAME);
		insertColumns.add(FLD_LAST_NAME);

		insertColumns.add(FLD_PRIMARY_PHONE);
		insertColumns.add(FLD_PRIMARY_EMAIL);

		insertColumns.add(FLD_PRIMARY_STREET_1);
		insertColumns.add(FLD_PRIMARY_STREET_2);

		insertColumns.add(FLD_PRIMARY_CITY);
		insertColumns.add(FLD_PRIMARY_POSTAL_CODE);
		insertColumns.add(FLD_PRIMARY_COUNTRY);

		selectColumns.add(FLD_ID);
		selectColumns.add(FLD_END_TS);
		selectColumns.addAll(insertColumns);

		orderByColumns.add(FLD_LAST_NAME);
		orderByColumns.add(FLD_FIRST_NAME);

	}

	@Override
	public String getTableName() {
		return TABLE;
	}

	@Override
	public Map<String, Object> getInsertMappings(UserProfile event) {
		Map<String, Object> mappings = new TreeMap<String, Object>();

		if (event.getStartTS() != null) {
			mappings.put(FLD_START_TS, event.getStartTS());
		}

		mappings.put(FLD_LAST_NAME, event.getLastName());
		mappings.put(FLD_FIRST_NAME, event.getFirstName());

		mappings.put(FLD_PRIMARY_PHONE, event.getPrimaryPhone());
		mappings.put(FLD_PRIMARY_EMAIL, event.getPrimaryEmail());

		mappings.put(FLD_PRIMARY_STREET_1, event.getPrimaryStreet1());
		mappings.put(FLD_PRIMARY_STREET_2, event.getPrimaryStreet2());
		mappings.put(FLD_PRIMARY_CITY, event.getPrimaryCity());
		mappings.put(FLD_PRIMARY_POSTAL_CODE, event.getPrimaryPostalCode());
		mappings.put(FLD_PRIMARY_COUNTRY, event.getPrimaryCountry());

		return mappings;

	}

	@Override
	public Map<String, Object> getUpdatetMappings(UserProfile type) {
		Map<String, Object> map = getInsertMappings(type);
		map.put(FLD_ID, type.getId());
		map.put(FLD_END_TS, type.getEndTS());
		

		return map;
	}

	@Override
	public RowMapper<UserProfile> getRowMapper() {
		return eventRowMapper;
	}

	private RowMapper<UserProfile> eventRowMapper = new RowMapper<UserProfile>() {

		@Override
		public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserProfile eventTmpl = new UserProfileImpl();
			eventTmpl.setId(rs.getInt(FLD_ID));

			Timestamp startTS = rs.getTimestamp(FLD_START_TS);
			if (startTS != null) {
				eventTmpl.setStartTS(startTS.toLocalDateTime());
			}

			Timestamp endTS = rs.getTimestamp(FLD_END_TS);
			if (endTS != null) {
				eventTmpl.setEndTS(endTS.toLocalDateTime());
			}

			eventTmpl.setLastName(rs.getString(FLD_LAST_NAME));
			eventTmpl.setFirstName(rs.getString(FLD_FIRST_NAME));

			eventTmpl.setPrimaryPhone(rs.getString(FLD_PRIMARY_PHONE));
			eventTmpl.setPrimaryEmail(rs.getString(FLD_PRIMARY_EMAIL));

			eventTmpl.setPrimaryStreet1(rs.getString(FLD_PRIMARY_STREET_1));
			eventTmpl.setPrimaryStreet2(rs.getString(FLD_PRIMARY_STREET_2));
			eventTmpl.setPrimaryCity(rs.getString(FLD_PRIMARY_CITY));
			eventTmpl.setPrimaryPostalCode(rs.getString(FLD_PRIMARY_POSTAL_CODE));
			eventTmpl.setPrimaryCountry(rs.getString(FLD_PRIMARY_COUNTRY));

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

		return orderByColumns;
	}

}
