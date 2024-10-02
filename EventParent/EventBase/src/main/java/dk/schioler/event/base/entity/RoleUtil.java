package dk.schioler.event.base.entity;

public class RoleUtil {

	public static ROLE getROLE(String roleStr) {

		ROLE role = null;

		if (ROLE.ADMIN.toString().equals(roleStr)) {
			role = ROLE.ADMIN;
		} else if (ROLE.MONITOR.toString().equals(roleStr)) {
			role = ROLE.MONITOR;
		} else if (ROLE.OWNER.toString().equals(roleStr)) {
			role = ROLE.OWNER;
		} else if (ROLE.PLAIN.toString().equals(roleStr)) {
			role = ROLE.OWNER;
		}
		return role;
	}

	public static String getRoleAsString(ROLE role) {
		String retVal = null;
		if (role != null) {
			retVal = role.toString();
		}
		return retVal;
	}
}
