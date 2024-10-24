package dk.schioler.secure.table;

import dk.schioler.secure.entity.UserProfile;

public interface UserProfileTable extends SecureTable<UserProfile> {
	public static final String TABLE = "USER_PROFILE";

//	public static final String FLD_START_TS = "START_TS";
//	public static final String FLD_END_TS = "END_TS";

	
	public static final String FLD_FIRST_NAME = "FIRST_NAME";
	public static final String FLD_LAST_NAME = "LAST_NAME";
	
	public static final String FLD_PRIMARY_PHONE = "PRIMARY_PHONE";
	public static final String FLD_PRIMARY_EMAIL = "PRIMARY_EMAIL";
	
	public static final String FLD_PRIMARY_STREET_1 = "PRIMARY_STREET_1";
	public static final String FLD_PRIMARY_STREET_2 = "PRIMARY_STREET_2";
	public static final String FLD_PRIMARY_POSTAL_CODE = "PRIMARY_POSTAL_CODE";
	public static final String FLD_PRIMARY_CITY= "PRIMARY_CITY";
	public static final String FLD_PRIMARY_COUNTRY = "PRIMARY_COUNTRY";

}
