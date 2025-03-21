package dk.schioler.event.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.web.controller.api.WebTokens;
import dk.schioler.event.web.entity.WebLogin;
import jakarta.servlet.http.HttpSession;

public class __BaseWebCommon implements WebTokens {

   protected Logger logger = LoggerFactory.getLogger(getClass());

//   protected boolean isLoginAuthenticated(HttpSession session) {
//      WebLogin login = getAuthenticatedLogin(session);
//      if (login != null) {
//         return login.isAuthenticated();
//      } else {
//         return false;
//      }
//
//   }
//
//   protected void setAuthenticatedLogin(HttpSession session, WebLogin weblogin) {
//      logger.debug("setAuthenticatedLogin(): weblogin=" + weblogin);
//      if (session != null) {
//         if (weblogin.isAuthenticated()) {
//            session.setAttribute(SES_AUTHENTICATED_USER, weblogin);
//         }
//      }
//   }
//
//   protected WebLogin getAuthenticatedLogin(HttpSession session) {
//      if (session != null) {
//         WebLogin webLogin = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//
//         if (webLogin != null) {
//            if (webLogin.isAuthenticated()) {
//               return webLogin;
//            } else {
//               return null;
//            }
//         } else {
//            return null;
//         }
//      } else {
//         return null;
//      }
//   }
//
//   protected boolean isPublicURL(String requestURI) {
//      boolean retval = false;
//      logger.debug("isPublic:" + requestURI);
//      if (requestURI.indexOf("public/login.jsp") >= 0) {
//         retval = true;
//      } else if (requestURI.indexOf("event.css") >= 0) {
//         retval = true;
//      } else if (requestURI.indexOf("event2.css") >= 0) {
//         retval = true;
//      } else if (requestURI.indexOf("login.css") >= 0) {
//         retval = true;
//      } else if (requestURI.indexOf("show-forgot-password.do") >= 0) {
//         retval = true;
//      } else if (requestURI.indexOf("user-authenticate.do") >= 0) {
//         retval = true;
//      }
//
//      logger.debug("isPublic: returning " + retval);
//      return retval;
//
//   }

}
