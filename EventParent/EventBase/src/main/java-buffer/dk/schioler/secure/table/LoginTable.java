package dk.schioler.secure.table;

import java.util.Map;

import dk.schioler.secure.entity.Login;

public interface LoginTable extends SecureTable<Login> {
	public static final String TABLE = "LOGIN";

	public static final String FLD_USER_PROFILE_ID = "USER_PROFILE_ID";

	public static final String FLD_LOGIN = "LOGIN";
	public static final String FLD_ROLE = "ROLE";
	
	public String getSecLoginSQL(String login, Map<String, Object> values);
	
}
