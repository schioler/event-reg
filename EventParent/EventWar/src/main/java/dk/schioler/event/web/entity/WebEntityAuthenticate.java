package dk.schioler.event.web.entity;

public interface WebEntityAuthenticate {
   
   
   public static final String LOG_OUT = "logout.do";
   public static final String USER_AUTHENTICATE = "user-authenticate.do";

   // ENTITY NEUTRAL REQ PARAMETERS
   public static final String REQ_LOGIN_TOKEN = "req-login-token";
   public static final String REQ_PASSWORD = "req-password";

   public static final String SES_LOGIN = "sesLogin";
   public static final String SES_LOGIN_ID = "login-id";

   public static final String SES_LOGINS = "sesLogins";
   public static final String SES_LOGIN_TREE = "sesLoginTree";

   public static final String LOGIN_JSP = "05-login.jsp";
//   public static final String PUBLIC_LOGIN_JSP = "login.jsp";

//   public static final String KEY_LOGIN = "KEY_LOGIN";

   public static final String SES_ACCOUNT = "sesAccount";
   public static final String SES_ACCOUNTS = "sesAccounts";

   public static final String LOGIN_TREE_JSP = "05-login-tree.jsp";
   public static final String LOGIN_TREE_SHOW = "login-tree-show.do";

   public static final String LOGIN_LIST_SHOW = "login-list-show.do";
   public static final String LOGIN_LIST_JSP = "05-login-list.jsp";

   public static final String SES_LOGIN_SEARCH_SHOW = "login-search-show.do";
   public static final String LOGIN_SEARCH_JSP = "05-login-search.jsp";
   

}
