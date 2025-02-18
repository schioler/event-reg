package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.web.WebLogin;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.Password;
import dk.schioler.shared.security.entity.ROLE;
import dk.schioler.shared.security.search.AbstractSearchCriteria.DateTimeCriteria;
import dk.schioler.shared.security.search.AbstractSearchCriteria.OPERATOR;
import dk.schioler.shared.security.search.LoginSearchCriteria;
import dk.schioler.shared.security.search.PasswordSearchCriteria;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserAuthenticateController extends AbstractController {

   public UserAuthenticateController() {
      super();

   }

   public static final String LOGIN_JSP = "redirect:login.jsp";

   public static final String PUBLIC_USER_AUTHENTICATE = "public/user-authenticate.do";

   public static final String FAVORITES_SHOW = "redirect:../favorites-show.do";

   @RequestMapping(value = PUBLIC_USER_AUTHENTICATE, method = RequestMethod.POST)
   public String userAuthenticate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(PUBLIC_USER_AUTHENTICATE + ": params=" + params);

      String retVal = LOGIN_JSP;

      HttpSession session = request.getSession();

      WebLogin authenticatedLogin = getAuthenticatedLogin(session);
      if (authenticatedLogin == null) {

         String token = (String) params.get(LOGIN);
         String password = (String) params.get(PASSWORD);

         if (StringUtils.isBlank(token) || StringUtils.isBlank(password)) {
            logger.info("token or password provided was empty");
         } else if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(password)) {
            
            logger.debug("neither token nor password was empty");
            // login.token and password has been provided... willl look up
            LoginSearchCriteria lsc = new LoginSearchCriteria();
            lsc.setToken(token);
            DateTimeCriteria dtc = new DateTimeCriteria();
            dtc.setOperator(OPERATOR.IS_NULL);
            lsc.setEndTS(dtc);
            List<Login> loginList = loginDAO.retrieve(lsc, 0);
            logger.debug("Looked up in login(s)=" + loginList);

            Login login = null;
            if (loginList.size() == 1) {
               login = loginList.get(0);
               logger.debug("lookend up 1 token=" + login.getToken());

               PasswordSearchCriteria psc = new PasswordSearchCriteria();
               psc.addToLoginIdList(login.getId());
               DateTimeCriteria endTSIsNull = new DateTimeCriteria();
               endTSIsNull.setOperator(OPERATOR.IS_NULL);
               psc.setEndTS(endTSIsNull);
               List<Password> pwdList = passwordDAO.retrieve(psc, 0);
               Password lookedUpPwd = null;
               if (pwdList != null) {
                  if (pwdList.size() == 1) {
                     lookedUpPwd = pwdList.get(0);
                     logger.debug("lookedUp     =" + lookedUpPwd.getPwd());

                     // posted pwd
                     String encrypted = encrypter.encrypt(password);
                     logger.debug("pwd.encrypted=" + encrypted);
                     
                     if (encrypted.equals(lookedUpPwd.getPwd())) {
                        logger.debug("posted password matches looked up password");
                        // we have a match
                        WebLogin wl = new WebLogin(login, LocalDateTime.now(), true);
                        logger.info("looking up owner login on id = " + login.getId());

                        List<Login> loginTreeTopDown = loginDAO.getLoginTreeTopDown(login.getId());
                        for (Login login2 : loginTreeTopDown) {
                           logger.debug("OWNER lookup: found login:" + login2.toString());
                           if (ROLE.OWNER.equals(login2.getRole())) {
                              wl.setOwner(login2);
                           }
                        }
                        logger.debug("WebLogin=" + wl);
                        setAuthenticatedLogin(session, wl);
                        retVal = FAVORITES_SHOW;
                     } else {
                        logger.error("did not receive exactly one password - that is not correct");
//                        return LOGIN_JSP;
                        retVal = LOGIN_JSP;
                     }
                  } else {
                     logger.debug("received not exactly 1 password");
                     retVal = LOGIN_JSP;
                  }
               } else {
                  logger.debug("looked up a null list of passwords");
                  retVal = LOGIN_JSP;
               }

            } else {
               logger.warn("found more than 1 logins on token=" + token);
               retVal = LOGIN_JSP;
            }

         } else {
            logger.info("recived blank token or password.");
            retVal = LOGIN_JSP;
         }
      } else {
         retVal = FAVORITES_SHOW;
      }
      logger.debug("returns:" + retVal);

      return retVal;
   }

   private static final String LOG_OUT = "logout.do";

   @RequestMapping(value = LOG_OUT, method = RequestMethod.GET)
   public String userLogout(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(LOG_OUT + " called");
      String retval = "redirect:public/login.jsp";

      request.getSession().invalidate();

      return retval;
   }

}