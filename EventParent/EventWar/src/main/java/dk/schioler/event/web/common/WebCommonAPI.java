package dk.schioler.event.web.common;

import java.util.List;

import org.springframework.stereotype.Component;

import dk.schioler.event.web.entity.WebLogin;
import jakarta.servlet.http.HttpSession;

@Component
public interface WebCommonAPI {
   public static String SES_AUTHENTICATED_USER = "sesAuthenticatedUser";
   public static final String SES_STATUS_MSG_LIST = "sesStatusMessageList";

   public boolean isLoginAuthenticated(HttpSession session);

   public void setAuthenticatedLogin(HttpSession session, WebLogin weblogin);

   public WebLogin getAuthenticatedLogin(HttpSession session);

   public boolean isPublicURL(String requestURI);

   public void addObjectToSession(HttpSession session, String key, Object object);

   public List<Object> getListFromSession(HttpSession session, String key);

   @SuppressWarnings("unchecked")
   public void addToStatus(HttpSession session, String msg);

   public void resetStatus(HttpSession session);

}
