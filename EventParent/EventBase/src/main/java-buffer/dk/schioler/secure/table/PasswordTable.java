package dk.schioler.secure.table;

import java.util.Map;

import dk.schioler.secure.entity.Password;

public interface PasswordTable extends SecureTable<Password> {
	public static final String TABLE = "PASSWORD";

	public static final String FLD_LOGIN_ID = "LOGIN_ID";
	
	
	public static final String FLD_PWD = "PWD";
	
	public StringBuffer getPasswordFromLoginSQL(Integer loginId, Map<String, Object> values, boolean includeHistoric);

}
