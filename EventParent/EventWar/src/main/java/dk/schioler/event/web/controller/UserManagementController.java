package dk.schioler.event.web.controller;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.web.WebLogin;
//import dk.schioler.event.web.WebLogin;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.impl.OwnerTreeToListVisitor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * Involved in usermanagement; 
 *    UserProfile: 
 *       Denotes "a living individual",, who can be found on an address and via a tlf no. 
 *    Login: 
 *       Logins are attached to a UserProfile and has the ability to, in combination with a password, 
 *       to provide access to the system. with due respect to what ROLE has been given the Login 
 *       A UserProfile can have multiple logins attached, but each with diff role.
 *       Logins has a startDate and an endDate. 
 *       StartDate is set at creationtime. 
 *       EndDate when the login is expired. 
 *       Once the EndDate is set, it requires an owner role to re-activate. 
 *       All logins, except ROOT, which is the mother of all Logins, has a parent. 
 *       Each parent can have a set of children.
 *       The role of each child, must be of a less grade than the parent. 
 *       Supports these roles, in hieraichal order: 
 *       NO DATA, owmed by one owner, MUST EVER BE visible or editable by another OWNER. 
 *       The only login with these priivileges are ROOT.
 *       
 *       (system) ROOT (parent of all logins, and with no relevancy in normal use.)
 *             Privileges: ALL! Runs "create new organisation - new set of data for an
 *             organisation to be able to use system. 
 *       (business) OWNER: 
 *             Only a name.
 *             One For each organisation, that will use this system, there must be one amd only one OWNER. 
 *             OWNER roles are responsible for proper use of system. 
 *             IE CTO of a company involved in creating new organisation. 
 *             Privileges of OWNER: none 
 *       ADMIN 
 *             Crete EventType, EventTemplate, Events create diagnostics
 *             create users adapt type and template setting. import/export data.
 *       USER create events, create status's, perform searches 
 *       MONITOR Only inspection
 *    Password: Holds the encrypted passwords of all logins. 
 *       For each login, there is only one with endDate== null. That's the active one. 
 *       Changing password will set endDate for the current active and add a new entry. 
 *       Passwords can not be repeated. (unique(pwd, login_id)
 * 
 * Rules in usermanagement - 
 *    UserProfile objects can exist on their own: (You can create a whole team and add logins as req.
 * 
 * - All login-objecs *must* have a UserProfile
 * 
 * FunctionList: UserProfile: create, update, delte, retrieve Login create,
 * update, delete, retrieve, Password crud. * -
 */

@Controller
public class UserManagementController extends AbstractController {

//   @Autowired
//   private LoginDAO loginDAO;
//
//   @Autowired
//   private PasswordDAO passwordDAO;
//
//   @Autowired
//   private Encrypter encrypter;

   public UserManagementController() {
      super();

   }

   
   public static final String LOGIN_TREE_JSP = "redirect:login-tree.jsp";
   public static final String SES_LOGIN_TREE = "sesLoginTree";
   public static final String LOGIN_TREE_SHOW = "login-tree-show.do";

   public static final String USER_PROFILE_SHOW = "user-profile-show.do";
   public static final String USER_PROFILE_SAVE = "user-profile-save.do";
   public static final String USER_PROFILE_DELETE = "user-profile-delete.do";

   public static final String FAVORITES_SHOW = "redirect:../favorites-show.do";

   @RequestMapping(value = LOGIN_TREE_SHOW, method = RequestMethod.GET)
   public String loginTreeShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      
      
      HttpSession session = request.getSession();
      WebLogin wl = this.getAuthenticatedLogin(session);
      if(wl != null) {
         Login ownerTree = loginDAO.getOwnerTree(wl.getOwner().getId());
         OwnerTreeToListVisitor visitor = new OwnerTreeToListVisitor();
         ownerTree.accept(visitor);
         List<Login> asList = visitor.getAsList();
         logger.debug("OwnerAsList=" +asList);
         session.setAttribute(SES_LOGIN_TREE, asList );
         
         return LOGIN_TREE_JSP;
      }

      return PUBLIC_LOGIN_JSP;
   }

//   @RequestMapping(value = "public/set-new-password.do", method = RequestMethod.POST)
//   public String userSetNewPassword(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug("set-new-password.dk: params=" + params);
//
//      String retVal = "redirect:login.jsp";
//      String loginId = (String) params.get(LOGIN);
//      String password1 = (String) params.get(PASSWORD + 1);
//      String password2 = (String) params.get(PASSWORD + 2);
//
//      if (StringUtils.isNotBlank(loginId)) {
//         Login login = loginDAO.getLogin(loginId);
//         logger.debug("login" + login);
//         if (login != null) {
//            logger.debug("login found=" + login);
//            if (password1 != null && password2 != null) {
//               logger.debug("provided pwds: 1=" + password1 + ", 2=" + password2);
//               if (password1.equals(password2)) {
//                  logger.debug("1+2 are equal");
//                  Password lookedUpPwd = null;
//
//                  logger.debug("will cancel current pwd");
//
//                  List<Password> passwordList = passwordDAO.getPassword(login.getId(), false);
//                  logger.debug("pwdLIst=" + passwordList);
//                  if (passwordList != null) {
//
//                     if (passwordList.size() == 1) {
//                        logger.debug("will update current pwd");
//                        lookedUpPwd = passwordList.get(0);
//                        lookedUpPwd.setEndTS(LocalDateTime.now());
//                        passwordDAO.update(lookedUpPwd);
//                     }
//                  }
//
//                  String encrypt = encrypter.encrypt(password1);
//                  PasswordImpl newPwd = new PasswordImpl();
//                  newPwd.setLoginId(login.getId());
//                  newPwd.setPwd(encrypt);
//                  Password inserted = passwordDAO.insert(newPwd);
//                  logger.debug("Login, password, inserted=" + inserted);
//                  retVal = "redirect:login.jsp";
//               }
//            }
//         }
//      }
//      logger.debug("returns:" + retVal);
//      return retVal;
//   }

//   @RequestMapping(value = "public/show-forgot-password.do", method = RequestMethod.GET)
//   public String userShowForgotPassword(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug("public/show-forgot-password.dk: params=" + params);
//
//      String retVal = "redirect:forgot-password.jsp";
//      logger.debug("returns:" + retVal);
//      return retVal;
//   }

   @RequestMapping(value = "logintree-show.do", method = RequestMethod.GET)
   public String userLogout(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      String retval = "redirect:public/login.jsp";

      request.getSession().invalidate();

      return retval;
   }

}