package dk.schioler.event.web2.common;

import java.time.LocalDateTime;

public class WebLogin {
   private String password;
	private String ownerToken;
	private String loginToken;
	private LocalDateTime authenticateTime;
	private boolean isAuthenticated;

	public WebLogin(String loginToken, LocalDateTime authenticateTime, boolean isAuthenticated) {
		super();
		this.isAuthenticated = isAuthenticated;
		this.loginToken = loginToken;
		this.authenticateTime = authenticateTime;
	}

	public String getLoginToken() {
		return loginToken;
	}
	
	public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public LocalDateTime getAuthenticateTime() {
		return authenticateTime;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public String getOwner() {
		return ownerToken;
	}

	public void setOwnerToken(String ownerToken) {
		this.ownerToken = ownerToken;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WebLogin \n");
		if (ownerToken != null) {
			builder.append("[ownerToken="+ ownerToken+" \n");
		} else {
			builder.append("[ownerToken= null]\n");
		}
		if (loginToken != null) {
			builder.append("[loginToken:" + loginToken + "\n");
		} else {
			builder.append("[loginToken= null]\n");
		}
		builder.append(", authenticateTime=").append(authenticateTime);
		builder.append(", isAuthenticated=").append(isAuthenticated);
		builder.append("]\n");
		return builder.toString();
	}

}
