
package dk.schioler.event.web.entity;

public interface WebEntityLogin extends WebEntityId {

   public String getRole();

   public void setRole(String role);

   public void setEndTs(String endTs);

   public String getEndTs();

   

   // ENTITY NEUTRAL REQ PARAMETERS
   public static final String REQ_LOGIN_TOKEN = "login-token";
   public static final String REQ_PASSWORD = "password";

   public static final String SES_LOGIN = "sesLogin";
   public static final String SES_LOGIN_ID = "login-id";

   public static final String SES_LOGINS = "sesLogins";


   //
//   public static final String LOGIN_JSP = "05-login.jsp";

//   public static final String KEY_LOGIN = "KEY_LOGIN";

   public static final String SES_ACCOUNT = "sesAccount";
   public static final String SES_ACCOUNTS = "sesAccounts";


}
