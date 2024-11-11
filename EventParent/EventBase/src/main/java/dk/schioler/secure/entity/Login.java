package dk.schioler.secure.entity;

import java.util.List;

public interface Login extends SecureEntity {
	public ROLE getRole();

	public void setRole(ROLE role);

	public String getLogin();

	public void setLogin(String login);

	public UserProfile getUserProfile();

	public void setUserProfile(UserProfile userProfile);

	public Integer getUserProfileId();

	public void setUserProfileId(Integer id);

	public void addPassword(Password password);

	public void setPasswords(List<Password> passwords);
	
	public List<Password> getPasswords();
	
	@Override
	public int hashCode() ;
	
	@Override
	public boolean equals(Object obj);


}
