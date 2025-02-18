package dk.schioler.event.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.web.controller.WebTokens;
import jakarta.servlet.http.HttpSession;

public class BaseWebCommon implements WebTokens {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   protected boolean isLoginAuthenticated(HttpSession session) {
      WebLogin login = getAuthenticatedLogin(session);
      if (login != null) {
         return login.isAuthenticated();
      } else {
         return false;
      }

   }

   protected void setAuthenticatedLogin(HttpSession session, WebLogin weblogin) {
      if (session != null) {
         if(weblogin.isAuthenticated()) {            
            session.setAttribute(SES_AUTHENTICATED_USER, weblogin);
         }
      }
   }

   protected WebLogin getAuthenticatedLogin(HttpSession session) {
      if (session != null) {
         WebLogin webLogin = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);

         if (webLogin != null) {
            if (webLogin.isAuthenticated()) {
               return webLogin;
            } else {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   protected boolean isPublicURL(String requestURI) {
      boolean retval = false;
      logger.debug("isPublic:" + requestURI);
      if (requestURI.indexOf("public") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("colss.css") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("styles.css") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("forgot-password.do") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("show-forgot-password.do") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("user-authenticate.do") >= 0) {
         retval = true;
      }

      logger.debug("isPublic: returning " + retval);
      return retval;

   }

}
