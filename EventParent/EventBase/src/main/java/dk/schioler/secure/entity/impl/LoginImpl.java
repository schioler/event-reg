package dk.schioler.secure.entity.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.ROLE;
import dk.schioler.secure.entity.UserProfile;

public class LoginImpl extends AbstractSecureEntity implements Login {

	private ROLE role;

	private String login;

	private UserProfile userProfile;

	private List<Password> passwords = new ArrayList<Password>();

	public LoginImpl(Integer id, LocalDateTime startTS, LocalDateTime endTS, ROLE role, String login,
			Integer userProfileId) {
		super(id, startTS, endTS);
		this.role = role;
		this.login = login;
	}

	public LoginImpl() {
		this(null, null, null, null, null, null);
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

	public Integer getUserProfileId() {
		if (userProfile != null) {
			return userProfile.getId();
		} else {
			return null;
		}

	}

	@Override
	public void setLogin(String login) {
		this.login = login;

	}

	public void setUserProfileId(Integer userProfileId) {
		if(this.userProfile != null) {
			this.userProfile.setId(userProfileId);
		} else {
			this.userProfile = new UserProfileImpl();
			this.userProfile.setId(userProfileId);
		}
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public UserProfile getUserProfile() {
		return userProfile;
	}

	public List<Password> getPasswords() {
		return passwords;
	}

	public void setPasswords(List<Password> passwords) {
		this.passwords = passwords;
	}

	@Override
	public void addPassword(Password password) {
		this.passwords.add(password);

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(login, passwords, role, userProfile);
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
		LoginImpl other = (LoginImpl) obj;
		return Objects.equals(login, other.login) && Objects.equals(passwords, other.passwords) && role == other.role
				&& Objects.equals(userProfile, other.userProfile);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoginImpl [role=");
		builder.append(role);
		builder.append(", login=");
		builder.append(login);
		builder.append(", userProfile=");
		builder.append(userProfile);
		builder.append(", passwords=");
		builder.append(passwords);
		builder.append("]");
		return builder.toString();
	}

	
}
