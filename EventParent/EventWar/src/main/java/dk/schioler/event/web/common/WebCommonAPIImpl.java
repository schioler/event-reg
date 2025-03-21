package dk.schioler.event.web.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dk.schioler.event.web.entity.WebLogin;
import jakarta.servlet.http.HttpSession;

@Component
public class WebCommonAPIImpl  implements WebCommonAPI {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   public boolean isLoginAuthenticated(HttpSession session) {
      WebLogin login = getAuthenticatedLogin(session);
      if (login != null) {
         return login.isAuthenticated();
      } else {
         return false;
      }

   }

   public void setAuthenticatedLogin(HttpSession session, WebLogin weblogin) {
      logger.debug("setAuthenticatedLogin(): weblogin=" + weblogin);
      if (session != null) {
         if (weblogin.isAuthenticated()) {
            session.setAttribute(SES_AUTHENTICATED_USER, weblogin);
         }
      }
   }

   public WebLogin getAuthenticatedLogin(HttpSession session) {
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

   public boolean isPublicURL(String requestURI) {
      boolean retval = false;
      logger.debug("isPublic:" + requestURI);
      if (requestURI.indexOf("public/login.jsp") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("event.css") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("event2.css") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("login.css") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("show-forgot-password.do") >= 0) {
         retval = true;
      } else if (requestURI.indexOf("user-authenticate.do") >= 0) {
         retval = true;
      }

      logger.debug("isPublic: returning " + retval);
      return retval;

   }
   
   public void addObjectToSession(HttpSession session, String key, Object object) {
      if (session != null) {
         if (StringUtils.isNotBlank(key)) {
            if (object != null) {
               session.setAttribute(key, object);
            } else {
               session.removeAttribute(key);
            }
         } else {
            logger.info("addObject: received key = null");
         }

      } else {
         logger.info("addObject: received session == null");
      }
   }

   
   @SuppressWarnings({ "rawtypes", "unchecked" })
   public List getListFromSession(HttpSession session, String key) {
      if (StringUtils.isNotEmpty(key)) {
         Object obj = session.getAttribute(key);

         if (obj instanceof List) {
            List list = (List) obj;
            return list;
         } else {
            return null;
         }

      } else {
         throw new EventInsufficientInputDataException();
      }
   }

   @SuppressWarnings("unchecked")
   public void addToStatus(HttpSession session, String msg) {
      List<Object> listFromSession = getListFromSession(session, SES_STATUS_MSG_LIST);
      if (listFromSession == null) {
         listFromSession = new ArrayList<Object>();
         addObjectToSession(session, SES_STATUS_MSG_LIST, listFromSession);
      }

      listFromSession.add(msg);
   }

   public void resetStatus(HttpSession session) {
      @SuppressWarnings("rawtypes")
      List listFromSession = getListFromSession(session, SES_STATUS_MSG_LIST);

      if (listFromSession == null) {
         listFromSession = new ArrayList<String>();
      } else {
         listFromSession.clear();
      }
      session.setAttribute(SES_STATUS_MSG_LIST, listFromSession);
   }



}
