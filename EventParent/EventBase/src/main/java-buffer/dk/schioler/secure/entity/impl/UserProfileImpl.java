package dk.schioler.secure.entity.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.UserProfile;

public class UserProfileImpl extends AbstractSecureEntity implements UserProfile {

	private String firstName;

	private String lastName;

	private String primaryPhone;
	
	private String primaryEmail;
	
	private String primaryStreet1;
	
	private String primaryStreet2;
	
	private String primaryPostalCode;
	
	private String primaryCity;
	
	private String primaryCountry;

	private List<Login> logins = new ArrayList<Login>();
	
	public UserProfileImpl() {
		super(null, null, null);
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	
	
	@Override
	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	@Override
	public String getPrimaryStreet1() {
		return primaryStreet1;
	}

	public void setPrimaryStreet1(String primaryStreet1) {
		this.primaryStreet1 = primaryStreet1;
	}

	@Override
	public String getPrimaryStreet2() {
		return primaryStreet2;
	}

	public void setPrimaryStreet2(String primaryStreet2) {
		this.primaryStreet2 = primaryStreet2;
	}

	@Override
	public String getPrimaryPostalCode() {
		return primaryPostalCode;
	}

	public void setPrimaryPostalCode(String primaryPostalCode) {
		this.primaryPostalCode = primaryPostalCode;
	}

	@Override
	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}
	
	

	@Override
	public String getPrimaryCountry() {
		return primaryCountry;
	}

	public void setPrimaryCountry(String primaryCountry) {
		this.primaryCountry = primaryCountry;
	}

	
	public void addLogin(Login login) {
		this.logins.add(login);
	}
	
	@Override
	public List<Login> getLogins() {
		return logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(firstName, lastName, logins, primaryCity, primaryCountry, primaryEmail,
				primaryPhone, primaryPostalCode, primaryStreet1, primaryStreet2);
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
		UserProfileImpl other = (UserProfileImpl) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(logins, other.logins) && Objects.equals(primaryCity, other.primaryCity)
				&& Objects.equals(primaryCountry, other.primaryCountry)
				&& Objects.equals(primaryEmail, other.primaryEmail) && Objects.equals(primaryPhone, other.primaryPhone)
				&& Objects.equals(primaryPostalCode, other.primaryPostalCode)
				&& Objects.equals(primaryStreet1, other.primaryStreet1)
				&& Objects.equals(primaryStreet2, other.primaryStreet2);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProfileImpl [");
		builder.append(super.toString());
		builder.append("firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", primaryPhone=");
		builder.append(primaryPhone);
		builder.append(", primaryEmail=");
		builder.append(primaryEmail);
		builder.append(", primaryStreet1=");
		builder.append(primaryStreet1);
		builder.append(", primaryStreet2=");
		builder.append(primaryStreet2);
		builder.append(", primaryPostalCode=");
		builder.append(primaryPostalCode);
		builder.append(", primaryCity=");
		builder.append(primaryCity);
		builder.append(", primaryCountry=");
		builder.append(primaryCountry);
		builder.append(", logins=");
		builder.append(logins);
		builder.append("]");
		return builder.toString();
	}

		

}
