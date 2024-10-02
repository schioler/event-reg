package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Password extends AbstractUserEntity {

	private Integer loginId;

	private String pwd;
	

	public Password(Integer id, LocalDateTime startTS, LocalDateTime endTS, Integer loginId, String pwd) {
		super(id, startTS, endTS);
		this.loginId = loginId;
		this.pwd = pwd;
	}

	public Password(Integer id, LocalDateTime startTS, LocalDateTime endTS) {
		this(id, startTS, endTS, null, null);
	}
	
	public Password() {
		this(null, null, null, null, null);
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(loginId, pwd);
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
		Password other = (Password) obj;
		return Objects.equals(loginId, other.loginId) && Objects.equals(pwd, other.pwd);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Password [loginId=");
		builder.append(loginId);
		builder.append(", pwd=******");
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}
