package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.controller.exception.EventWebControllerException;
import dk.schioler.event.web.entity.WebEntityAuthenticate;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import dk.schioler.shared.security.criteria.LoginSearchCriteria;
import dk.schioler.shared.security.criteria.PasswordSearchCriteria;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.dao.UserProfileDAO;
import dk.schioler.shared.security.encrypt.Encrypter;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.Password;
import dk.schioler.shared.security.entity.ROLE;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAuthenticateController extends AbstractController implements WebEntityAuthenticate, WebEntitySymbolsShared {

   private static final String LOG_OUT = "logout.do";

   public static final String USER_AUTHENTICATE = "user-authenticate.do";
//
//   // ENTITY NEUTRAL REQ PARAMETERS
   public static final String REQ_LOGIN_TOKEN = "req-login-token";
   public static final String REQ_PASSWORD = "req-password";
//
//
//
//   public static final String SES_LOGIN = "sesLogin";
//   public static final String SES_LOGIN_ID = "login-id";
//
//   public static final String SES_LOGINS = "sesLogins";
//   public static final String SES_LOGIN_TREE = "sesLoginTree";
//
//   public static final String LOGIN_JSP = "05-login.jsp";
////   public static final String PUBLIC_LOGIN_JSP = "login.jsp";
//
////   public static final String KEY_LOGIN = "KEY_LOGIN";
//
//   public static final String SES_ACCOUNT = "sesAccount";
//   public static final String SES_ACCOUNTS = "sesAccounts";
//
//   public static final String LOGIN_TREE_JSP = "05-login-tree.jsp";
//   public static final String LOGIN_TREE_SHOW = "login-tree-show.do";
//
//   public static final String LOGIN_LIST_SHOW = "login-list-show.do";
//   public static final String LOGIN_LIST_JSP = "05-login-list.jsp";
//
//   public static final String SES_LOGIN_SEARCH_SHOW = "login-search-show.do";
//   public static final String LOGIN_SEARCH_JSP = "05-login-search.jsp";
//   

   public UserAuthenticateController() {
      super();

   }

   @Autowired
   private Boolean showSessionVars;

   @Autowired
   protected LoginDAO loginDAO;

   @Autowired
   protected PasswordDAO passwordDAO;

   @Autowired
   protected Encrypter encrypter;

   @Autowired
   protected UserProfileDAO userProfileDAO;

   private String getMandatoryParam(String key, Map<String, String> params) {
      if (StringUtils.isNotBlank(key)) {
         String value = params.get(key);

         if (StringUtils.isBlank(value)) {
            String msg = "found no value on key=" + key;
            logger.info(msg);
            throw new EventWebControllerException(msg);
         }
         return value;
      } else {
         String msg = "Provided key was empty or null";
         logger.info(msg);
         throw new EventWebControllerException(msg);
      }
   }

   private boolean verifyListSizeEqOne(List<?> list) {
      if (list == null) {
         return false;
      }
      if (list.size() == 0) {
         return false;
      }
      if (list.size() > 1) {
         return false;
      }
      return true;
   }

   private Login getActiveLogin(String token) {

//      login.token and password has been provided... willl look up
      LoginSearchCriteria lsc = new LoginSearchCriteria();
      lsc.setToken(token);
      lsc.setEndTS(null);

      List<Login> loginList = loginDAO.retrieve(lsc, 0);
      logger.debug("Looked up in login(s)=" + loginList);

      boolean sizeEqOne = verifyListSizeEqOne(loginList);
      Login login = null;
      if (sizeEqOne) {
         login = loginList.get(0);
      }

      return login;
   }

   private Password getActivePasswordOn(Login login) {

      PasswordSearchCriteria psc = new PasswordSearchCriteria();
      psc.addToLoginIdList(login.getId());
      psc.setEndTS(null);

      List<Password> pwdList = passwordDAO.retrieve(psc, 0);
      boolean sizeEqOne = verifyListSizeEqOne(pwdList);

      Password lookedUpPwd = null;
      if (sizeEqOne) {
         lookedUpPwd = pwdList.get(0);
      }

      return lookedUpPwd;
   }

   private WebLogin setOwner(WebLogin wl) {
      Login login = wl.getLogin();

      if (login.getRole().equals(ROLE.OWNER)) {
         wl.setOwner(login);
      } else {
         logger.info("looking up owner login on id = " + login.getId());
         Login owner = null;
         List<Login> loginTreeTopDown = loginDAO.getLoginTreeTopDown(login.getId());
         for (Login login2 : loginTreeTopDown) {
            logger.debug("OWNER lookup: found login:" + login2.toString());
            if (ROLE.OWNER.equals(login2.getRole())) {
               owner = login2;
               break;
            }
         }

         wl.setOwner(owner);
         logger.debug("WebLogin=" + wl);
      }
      return wl;

   }

   @RequestMapping(value = USER_AUTHENTICATE, method = RequestMethod.POST)
   public String userAuthenticate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request, HttpServletResponse response) {
      logger.debug(USER_AUTHENTICATE + ": params=" + params);

      String retVal = null;
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null && wl.getAuthenticateTime() != null) {
         logger.debug("user already authenticated - will forward");

         retVal = REDIRECT + SLASH + SECURE + SLASH + HOME_DO;

      } else {
         logger.debug("found no authenticated used in session - will do authentication check");
         String token = getMandatoryParam(REQ_LOGIN_TOKEN, params);
         String password = getMandatoryParam(REQ_PASSWORD, params);

         Login login = getActiveLogin(token);
         logger.debug("lookend up 1 token=" + login.getToken());

         Password lookedUpPwd = getActivePasswordOn(login);

         logger.debug("lookedUp.pwd=" + lookedUpPwd.getPwd());
         // posted pwd
         String encrypted = encrypter.encrypt(password);
         logger.debug(" pwd.encrypted=" + encrypted);

         if (!encrypted.equals(lookedUpPwd.getPwd())) {
            throw new EventWebControllerException("Passwords did not match!");
         }
         logger.debug("posted password matches looked up password");
         // we have a match
         wl = new WebLogin(login, LocalDateTime.now(), true);

         wl = setOwner(wl);

         WebCommonAPI.setShowSessionVariables(session, this.showSessionVars);
         WebCommonAPI.setAuthenticatedLogin(session, wl);
//         WebCommonAPI.loginTokenAdd(session, wl.getLogin().getToken());
         WebCommonAPI.statusAdd(session, "Successfully Logged in as " + wl.getLogin().getToken());

         retVal = REDIRECT + SLASH + SECURE + SLASH + HOME_DO;
         logger.info("returning:" + retVal);

      }

      logger.debug("Redirect=" + retVal);
      return retVal;

   }

   @RequestMapping(value = "/secure/home.do", method = RequestMethod.GET)
   public String showHomeDo(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug("/secure/home.do, called");

      return "/secure/00-home";
   }

   @RequestMapping(value = SECURE + SLASH + LOG_OUT, method = RequestMethod.GET)
   public String userLogout(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + LOG_OUT + " called");
      String retval = "redirect:/";

      request.getSession().invalidate();

      return retval;
   }

}