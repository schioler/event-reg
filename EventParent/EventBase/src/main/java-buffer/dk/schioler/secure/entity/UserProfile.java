package dk.schioler.secure.entity;

import java.util.List;

public interface UserProfile extends SecureEntity {

	public String getFirstName();

	public String getLastName();

	public String getPrimaryPhone();

	public String getPrimaryEmail();

	public String getPrimaryStreet1();

	public String getPrimaryStreet2();

	public String getPrimaryPostalCode();

	public String getPrimaryCity();

	public String getPrimaryCountry();


	
	public void setFirstName(String param);

	public void setLastName(String param);

	public void setPrimaryPhone(String param);

	public void setPrimaryEmail(String param);

	public void setPrimaryStreet1(String param);

	public void setPrimaryStreet2(String param);

	public void setPrimaryPostalCode(String param);

	public void setPrimaryCity(String param);

	public void setPrimaryCountry(String param);


	public List<Login> getLogins();

	public void setLogins(List<Login> logins);
	
	public void addLogin(Login login);
	

	@Override
	public int hashCode() ;
	
	@Override
	public boolean equals(Object obj);

}