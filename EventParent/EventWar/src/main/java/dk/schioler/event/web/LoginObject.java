package dk.schioler.event.web;


import java.time.LocalDateTime;
import java.util.Objects;

import dk.schioler.event.web.controller.WebTokens;
import dk.schioler.shared.security.entity.Login;


public class LoginObject implements WebTokens {

	public final static String EVENT_SESSION_WRAPPER_SESSION_KEY = "EVENT_SESSION_WRAPPER_SESSION_KEY";

	private final LocalDateTime loginTime;

	private final boolean isAuthenticated;

	private final Login login;

	public LoginObject(LocalDateTime loginTime, boolean isAuthenticated, Login login) {
		super();
		this.loginTime = loginTime;
		this.isAuthenticated = isAuthenticated;
		this.login = login;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public Login getLogin() {
		return login;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isAuthenticated, login, loginTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoginObject other = (LoginObject) obj;
		return isAuthenticated == other.isAuthenticated && Objects.equals(login, other.login)
				&& Objects.equals(loginTime, other.loginTime);
	}

	@Override
	public String toString() {
		return "LoginObject [loginTime=" + loginTime + ", isAuthenticated=" + isAuthenticated + ", login=" + login
				+ "]";
	}



}
