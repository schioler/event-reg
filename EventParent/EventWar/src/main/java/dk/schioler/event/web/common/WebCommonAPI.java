package dk.schioler.event.web.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.web.controller.exception.EventWebControllerException;
import jakarta.servlet.http.HttpSession;

public class WebCommonAPI {
   public static final String SES_AUTHENTICATED_USER = "sesAuthenticatedUser";
   public static final String SES_AUTHENTICATED_TOKEN = "sesAuthenticatedToken";

   public static final String SES_STATUS_MSG_LIST = "sesStatusMsgList";

   public static final String SES_SHOW_SESSION_VARIABLES = "sesShowSessionVariables";

   private static Logger logger = LoggerFactory.getLogger(WebCommonAPI.class);

   public static boolean isLoginAuthenticated(HttpSession session) {
      WebLogin login = getAuthenticatedLogin(session);
      if (login != null) {
         return login.isAuthenticated();
      } else {
         return false;
      }

   }

   public static void setShowSessionVariables(HttpSession session, Boolean doShow) {
      if (doShow != null) {
         if (doShow) {
            session.setAttribute(SES_SHOW_SESSION_VARIABLES, Boolean.TRUE);
         } else {
            session.setAttribute(SES_SHOW_SESSION_VARIABLES, Boolean.FALSE);
         }
      } else {
         session.setAttribute(SES_SHOW_SESSION_VARIABLES, Boolean.FALSE);
      }
   }

   public static void setAuthenticatedLogin(HttpSession session, WebLogin weblogin) {
//      logger.debug("setAuthenticatedLogin(): weblogin=" + weblogin);
      if (session != null) {
         if (weblogin.isAuthenticated()) {
            session.setAttribute(SES_AUTHENTICATED_USER, weblogin);
//            session.setAttribute(SES_AUTHENTICATED_TOKEN, weblogin.getLogin().getToken());
         }
      }
   }

   public static WebLogin getAuthenticatedLogin(HttpSession session) {
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

   public static boolean isSecuredURL(String requestURI) {
      boolean retval = false;
      logger.debug("isSecureURL:" + requestURI);

//		if (requestURI.indexOf("public") >= 0) {
//			retval = false;
//		} else if (requestURI.indexOf("user-authenticate.do") >= 0) {
//			retval = false;
//		} else
      if (requestURI.indexOf("secure") >= 0) {
         retval = true;
      }
//		else if (requestURI.indexOf(".css") >= 0) {
//			retval = false;
//      } else if (requestURI.indexOf(".png") >= 0) {
//         retval = false;
//		}

      logger.debug("isSecure(" + requestURI + ", returning " + retval);
      return retval;

   }

   public static void removeObjectFromSession(HttpSession session, String key) {
      if (session != null) {
         if (StringUtils.isNotBlank(key)) {
            session.removeAttribute(key);
         } else {
            logger.info("removeObject: received key = null");
         }
      } else {
         logger.info("removeObject: received session == null");
      }

   }

   public static void addObjectToSession(HttpSession session, String key, Object object) {
      String msg = null;
      if (session != null) {
         if (StringUtils.isNotBlank(key)) {
            if (object != null) {
               session.setAttribute(key, object);
            } else {
               session.removeAttribute(key);
            }
         } else {
            msg = "addObject: received key = null";
            logger.info(msg);
            throw new EventWebControllerException(msg);
         }

      } else {
         msg = "addObject: received session == null";
         logger.info(msg);
         throw new EventWebControllerException(msg);
      }
   }

   public static Object getObjectFromSession(HttpSession session, String key) {
      if (StringUtils.isNotEmpty(key)) {
         Object obj = session.getAttribute(key);

//			if (obj instanceof List) {
//				List list = (List) obj;
//				return list;
//			} else {
//				return null;
//			}
         return obj;

      } else {
         throw new InsufficientInputDataException();
      }
   }

   public static void addStringToSession(HttpSession session, String key, String object) {
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


   public static String getStringFromSession(HttpSession session, String key) {
      if (StringUtils.isNotEmpty(key)) {
         String obj = (String) session.getAttribute(key);

         return obj;

      } else {
         throw new InsufficientInputDataException();
      }
   }

   public static void addIntegerToSession(HttpSession session, String key, Integer object) {
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

   
   public static Integer getIntegerFromSession(HttpSession session, String key) {
      if (StringUtils.isNotEmpty(key)) {
         Integer obj = (Integer) session.getAttribute(key);

         return obj;

      } else {
         throw new InsufficientInputDataException();
      }
   }
   
   public static void loginTokenAdd(HttpSession session, String loginName) {

      addObjectToSession(session, SES_AUTHENTICATED_TOKEN, loginName);
   }

   public static void statusAdd(HttpSession session, String msg) {
      @SuppressWarnings("unchecked")
      List<String> listFromSession = (List<String>) getObjectFromSession(session, SES_STATUS_MSG_LIST);
      if (listFromSession == null) {
         listFromSession = new ArrayList<String>();
         addObjectToSession(session, SES_STATUS_MSG_LIST, listFromSession);
      }
      listFromSession.add(msg);

      addObjectToSession(session, SES_STATUS_MSG_LIST, listFromSession);
   }

   public static void statusSet(HttpSession session, String msg) {
      @SuppressWarnings("unchecked")
      List<String> listFromSession = (List<String>) getObjectFromSession(session, SES_STATUS_MSG_LIST);
      if (listFromSession == null) {
         listFromSession = new ArrayList<String>();
         
//         addObjectToSession(session, SES_STATUS_MSG_LIST, listFromSession);
      }
      listFromSession.clear();
      listFromSession.add(msg);

      addObjectToSession(session, SES_STATUS_MSG_LIST, listFromSession);
   }

   
   public static void statusReset(HttpSession session) {

      @SuppressWarnings("unchecked")
      List<String> listFromSession = (List<String>) getObjectFromSession(session, SES_STATUS_MSG_LIST);

      if (listFromSession == null) {
         listFromSession = new ArrayList<String>();
      } else {
         listFromSession.clear();
      }
      session.setAttribute(SES_STATUS_MSG_LIST, listFromSession);
   }

}
