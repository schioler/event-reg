package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Login extends AbstractUserEntity {
	
	private ROLE role;
	
	private String login;
	
	private Integer userProfileId;
	
	public Login(Integer id, LocalDateTime startTS, LocalDateTime endTS, ROLE role, String login, Integer userProfileId) {
		super(id, startTS, endTS);
		this.role = role;
		this.login = login;
	}
	
	public Login() {
		this(null, null, null, null,null, null);
	}

	public ROLE getRole() {
		return role;
	}

	public void setRole(ROLE role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Integer getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Integer userProfileId) {
		this.userProfileId = userProfileId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(login, role, userProfileId);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(login, other.login) && role == other.role
				&& Objects.equals(userProfileId, other.userProfileId);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Login [role=");
		builder.append(role);
		builder.append(", login=");
		builder.append(login);
		builder.append(", userProfileId=");
		builder.append(userProfileId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	

}
