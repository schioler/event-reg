package dk.schioler.event.web;

import java.time.LocalDateTime;
import java.util.Objects;

import dk.schioler.secure.entity.Login;

public class WebLogin {
	private final Login login;
	private final LocalDateTime authenticateTime;
	private final boolean isAuthenticated;
	
	public WebLogin(Login login, LocalDateTime authenticateTime, boolean isAuthenticated) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.login = login;
		this.authenticateTime = authenticateTime;
	}
	public Login getLogin() {
		return login;
	}
	public LocalDateTime getAuthenticateTime() {
		return authenticateTime;
	}
	
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
		
	@Override
	public int hashCode() {
		return Objects.hash(authenticateTime, isAuthenticated, login);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebLogin other = (WebLogin) obj;
		return Objects.equals(authenticateTime, other.authenticateTime) && isAuthenticated == other.isAuthenticated
				&& Objects.equals(login, other.login);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebLogin [login=");
		builder.append(login);
		builder.append(", authenticateTime=");
		builder.append(authenticateTime);
		builder.append(", isAuthenticated=");
		builder.append(isAuthenticated);
		builder.append("]");
		return builder.toString();
	}

	
	
}
