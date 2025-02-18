package dk.schioler.secure.entity.impl;

import java.time.LocalDateTime;
import java.util.Objects;

import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;

public class PasswordImpl extends AbstractSecureEntity implements Password {

//	private Integer loginId;

	private Login login;

	private String pwd;

//	public PasswordImpl(Integer id, LocalDateTime startTS, LocalDateTime endTS, Integer loginId, String pwd) {
//		super(id, startTS, endTS);
//		this.loginId = loginId;
//		this.pwd = pwd;
//	}

//	public PasswordImpl(Integer id, LocalDateTime startTS, LocalDateTime endTS) {
//		this(id, startTS, endTS, null, null);
//	}

	public PasswordImpl() {
		super(null, null, null);
	}

	@Override
	public Integer getLoginId() {
		if (login != null) {
			return login.getId();
		} else {
			return null;
		}

	}

	@Override
	public void setLoginId(Integer loginId) {
		if (login == null) {
			this.login = new LoginImpl();
		}

		this.login.setId(loginId);

	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String getPwd() {
		return pwd;
	}

	@Override
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(login, pwd);
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
		PasswordImpl other = (PasswordImpl) obj;
		return Objects.equals(login, other.login) && Objects.equals(pwd, other.pwd);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PasswordImpl [login=");
		builder.append(login);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append("]");
		return builder.toString();
	}

	
}
