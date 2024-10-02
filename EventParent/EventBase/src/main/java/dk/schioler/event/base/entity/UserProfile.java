package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserProfile extends AbstractTSEntity {

	private String firstName;

	private String lastName;

	private String primaryPhone;
	
	private String primaryStreet1;
	
	private String primaryStreet2;
	
	private String primaryPostalCode;
	
	private String primaryCity;
	
	private String primaryCountry;

	
	public UserProfile(Integer id, LocalDateTime startTS, LocalDateTime endTS, String firstName, String lastName, String primaryPhone, String primaryStreet1,
			String primaryStreet2, String primaryPostalCode, String primaryCity, String primaryCountry) {
		super(id, startTS, endTS);
		this.firstName = firstName;
		this.lastName = lastName;
		this.primaryPhone = primaryPhone;
		this.primaryStreet1 = primaryStreet1;
		this.primaryStreet2 = primaryStreet2;
		this.primaryPostalCode = primaryPostalCode;
		this.primaryCity = primaryCity;
		this.primaryCountry = primaryCountry;
	}

	public UserProfile() {
		super(null, null, null);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getPrimaryStreet1() {
		return primaryStreet1;
	}

	public void setPrimaryStreet1(String primaryStreet1) {
		this.primaryStreet1 = primaryStreet1;
	}

	public String getPrimaryStreet2() {
		return primaryStreet2;
	}

	public void setPrimaryStreet2(String primaryStreet2) {
		this.primaryStreet2 = primaryStreet2;
	}

	public String getPrimaryPostalCode() {
		return primaryPostalCode;
	}

	public void setPrimaryPostalCode(String primaryPostalCode) {
		this.primaryPostalCode = primaryPostalCode;
	}

	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}
	
	

	public String getPrimaryCountry() {
		return primaryCountry;
	}

	public void setPrimaryCountry(String primaryCountry) {
		this.primaryCountry = primaryCountry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(firstName, lastName, primaryCity, primaryCountry, primaryPhone,
				primaryPostalCode, primaryStreet1, primaryStreet2);
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
		UserProfile other = (UserProfile) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(primaryCity, other.primaryCity)
				&& Objects.equals(primaryCountry, other.primaryCountry)
				&& Objects.equals(primaryPhone, other.primaryPhone)
				&& Objects.equals(primaryPostalCode, other.primaryPostalCode)
				&& Objects.equals(primaryStreet1, other.primaryStreet1)
				&& Objects.equals(primaryStreet2, other.primaryStreet2);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserProfile [firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", primaryPhone=");
		builder.append(primaryPhone);
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
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

		

}
