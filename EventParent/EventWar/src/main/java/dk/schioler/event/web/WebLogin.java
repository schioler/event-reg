package dk.schioler.event.web;


import java.time.LocalDateTime;
import java.util.Objects;

import dk.schioler.shared.security.entity.Login;



public class WebLogin {
   private Login owner; 
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
	
	
	
	public Login getOwner() {
      return owner;
   }

   public void setOwner(Login owner) {
      this.owner = owner;
   }

   @Override
   public int hashCode() {
      return Objects.hash(authenticateTime, isAuthenticated, login, owner);
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
      return Objects.equals(authenticateTime, other.authenticateTime) && isAuthenticated == other.isAuthenticated && Objects.equals(login, other.login)
            && Objects.equals(owner, other.owner);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("WebLogin [owner=");
      builder.append(owner);
      builder.append(", login=");
      builder.append(login);
      builder.append(", authenticateTime=");
      builder.append(authenticateTime);
      builder.append(", isAuthenticated=");
      builder.append(isAuthenticated);
      builder.append("]");
      return builder.toString();
   }

	
}
