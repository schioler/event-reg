package dk.schioler.event.tools.main;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.tools.configuration.EventToolsConfiguration;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.dao.UserProfileDAO;
import dk.schioler.shared.security.encrypt.Encrypter;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.security.entity.Password;
import dk.schioler.shared.security.entity.ROLE;
import dk.schioler.shared.security.entity.UserProfile;
import dk.schioler.shared.security.entity.impl.LoginImpl;
import dk.schioler.shared.security.entity.impl.PasswordImpl;
import dk.schioler.shared.security.entity.impl.UserProfileImpl;

//@Configuration(EventToolsConfiguration.class)
public class EstablishBaseUser implements SQLConstructs {
   private static Map<String, Map<String, String>> domainValues = new TreeMap<String, Map<String, String>>();;
   private static final String FIRSTNAME="FIRSTNAME";
   private static final String LASTNAME="LASTNAME";
   private static final String FIRST_TELEPHONE = "FIRST_TELEPHONE";
   private static final String TOKEN = "TOKEN";
   private static final String KEY_ROLE = "ROLE";
   private static final String PASSWORD = "PASSWORD";

   static {
      System.getProperties().setProperty("event.env", "dev");
   }
   static {
      
      Map<String, String> ownerValues = new TreeMap<String, String>();
      ownerValues.put(FIRSTNAME, "Owner");
      ownerValues.put(LASTNAME, "The Man");
      ownerValues.put(FIRST_TELEPHONE, "45 1234 4321");
      ownerValues.put(TOKEN, "o");
      ownerValues.put(KEY_ROLE, ROLE.OWNER.toString());
      ownerValues.put(PASSWORD, "o");
      domainValues.put(ROLE.OWNER.toString(), ownerValues);
      
      Map<String, String> adminValues = new TreeMap<String, String>();
      adminValues.put(FIRSTNAME, "Admin");
      adminValues.put(LASTNAME, "Admiral");
      adminValues.put(FIRST_TELEPHONE, "45 3232 1234");
      adminValues.put(TOKEN, "a");
      adminValues.put(KEY_ROLE, ROLE.ADMIN.toString());
      adminValues.put(PASSWORD, "a");
      domainValues.put(ROLE.ADMIN.toString(), adminValues);
      
      Map<String, String> userValues = new TreeMap<String, String>();
      userValues.put(FIRSTNAME, "User");
      userValues.put(LASTNAME, "UserMan");
      userValues.put(FIRST_TELEPHONE, "45 11223344");
      userValues.put(TOKEN, "u");
      userValues.put(KEY_ROLE, ROLE.USER.toString());
      userValues.put(PASSWORD, "u");
      domainValues.put(ROLE.USER.toString(), userValues);
     
   }

   private static Logger logger = LoggerFactory.getLogger(EstablishBaseUser.class);

   protected static AnnotationConfigApplicationContext ctx;

   public static void main(String[] args) {
      try {
         ctx = new AnnotationConfigApplicationContext(EventToolsConfiguration.class);

         userprofileDAO = ctx.getBean(UserProfileDAO.class);

         loginDAO = ctx.getBean(LoginDAO.class);

         passwordDAO = ctx.getBean(PasswordDAO.class);

         encrypter = ctx.getBean(Encrypter.class);

         EstablishBaseUser self = new EstablishBaseUser();

         Login root = self.lookupRoot();

//         ********************************************************''
         
         DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
         
         
         // Owner:
         Map<String, String> ownerValues = domainValues.get(ROLE.OWNER.toString());
         
         UserProfile ownerUp = new UserProfileImpl();
         ownerUp.setStartTS(LocalDateTime.now());
         ownerUp.setFirstName(ownerValues.get(FIRSTNAME));
         ownerUp.setLastName(ownerValues.get(LASTNAME));
         ownerUp.setPrimaryPhone(ownerValues.get(FIRST_TELEPHONE));

         Login ownerLogin = new LoginImpl();
         ownerLogin.setStartTS(LocalDateTime.now());
         ownerLogin.setToken(ownerValues.get(TOKEN));
         ownerLogin.setRole(ROLE.OWNER);
         ownerLogin.setLevel(root.getLevel() + 1);
         ownerLogin.setParent(root);

         Password ownerPassword = new PasswordImpl();
         String ownerEnc = encrypter.encrypt(ownerValues.get(PASSWORD));
         ownerPassword.setPwd(ownerEnc);
         ownerPassword.setStartTS(LocalDateTime.now());

         self.persistUserSet(ownerUp, root, ownerLogin, ownerPassword);
//       ********************************************************''
         
         // Admin:
         Map<String, String> adminValues = domainValues.get(ROLE.ADMIN.toString());
         
         UserProfile adminUp = new UserProfileImpl();
         adminUp.setStartTS(LocalDateTime.now());
         adminUp.setFirstName(adminValues.get(FIRSTNAME));
         adminUp.setLastName(adminValues.get(LASTNAME));
         adminUp.setPrimaryPhone(adminValues.get(FIRST_TELEPHONE));

         Login admlogin = new LoginImpl();
         admlogin.setStartTS(LocalDateTime.now());
         admlogin.setToken(adminValues.get(TOKEN));
         admlogin.setRole(ROLE.ADMIN);
         admlogin.setLevel(ownerLogin.getLevel() + 1);
         admlogin.setParent(ownerLogin);

         Password admPassword = new PasswordImpl();
         String admEnc = encrypter.encrypt(adminValues.get(PASSWORD));
         admPassword.setPwd(admEnc);
         admPassword.setStartTS(LocalDateTime.now());

         self.persistUserSet(adminUp, ownerLogin, admlogin, admPassword);
         
         
//       ********************************************************''
         
         // User:
         Map<String, String> userValues = domainValues.get(ROLE.USER.toString());
         
         UserProfile userUp = new UserProfileImpl();
         userUp.setStartTS(LocalDateTime.now());
         userUp.setFirstName(userValues.get(FIRSTNAME));
         userUp.setLastName(userValues.get(LASTNAME));
         userUp.setPrimaryPhone(userValues.get(FIRST_TELEPHONE));

         Login ulogin = new LoginImpl();
         ulogin.setStartTS(LocalDateTime.now());
         ulogin.setToken(userValues.get(TOKEN));
         ulogin.setRole(ROLE.USER);
         ulogin.setLevel(admlogin.getLevel() + 1);
         ulogin.setParent(admlogin);

         Password uPassword = new PasswordImpl();
         String uEnc = encrypter.encrypt(userValues.get(PASSWORD));
         uPassword.setPwd(uEnc);
         uPassword.setStartTS(LocalDateTime.now());

         self.persistUserSet(userUp, admlogin, ulogin, uPassword);
         
         
         
         
         
         
//       ********************************************************''

      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
   }

   /*********************************************/

   static private UserProfileDAO userprofileDAO;

   static private LoginDAO loginDAO;

   static private PasswordDAO passwordDAO;

   static private Encrypter encrypter;

   public Login lookupRoot() {
      return loginDAO.getRootLogin();
   }
   
   @Transactional
   public Login persistUserSet(UserProfile up, Login parentLogin, Login login, Password password) {

      try {
//         String token = StringUtils.substring(role.toString(), 0,1) + idx + "@" + token;
//         up = new UserProfileImpl();
//         up.setStartTS(LocalDateTime.now());
//         up.setFirstName(role.toString()+idx);
//         up.setLastName(token);
//         up.setPrimaryEmail(token);
//         up.setPrimaryPhone("12341234");
         up = userprofileDAO.insert(up);
         login.setUserProfileId(up.getId());
//         String pwd = token;
//         newLogin = new LoginImpl(null, LocalDateTime.now(), role, token );
//         newLogin.setUserProfileId(up.getId());
//         newLogin.setParentId(parentLogin.getId());

         login = loginDAO.insert(login);
         parentLogin.addChild(login);

//         Password ownerPwd = new PasswordImpl();
//         String enc = encrypter.encrypt(password);
//         ownerPwd.setPwd(enc);
//         ownerPwd.setStartTS(LocalDateTime.now());
//         ownerPwd.setLoginId(newLogin.getId());

         password.setLoginId(login.getId());
         password = passwordDAO.insert(password);
         return login;

      } catch (Exception e) {
         logger.error("caught:" + e.getMessage(), e);
         return null;
      } finally {
//         if (up != null) {
//            userprofileDAO.delete(up.getId());
//         }
      }
   }

//		UserProfile up = new UserProfileImpl();
//		up.setFirstName("ROOT");
//		up.setLastName("BaseUser");
//		up.setPrimaryEmail("root@event.dk");
//		up.setStartTS(LocalDateTime.now());
//		
//		Map<String, Object> upValues = new HashMap<String, Object>();
//		upValues.put(UserProfileTable.FLD_FIRST_NAME, up.getFirstName());
//		upValues.put(UserProfileTable.FLD_LAST_NAME, up.getLastName());
//		upValues.put(UserProfileTable.FLD_PRIMARY_EMAIL, up.getPrimaryEmail());
//		upValues.put(UserProfileTable.FLD_START_TS, up.getStartTS());		
//		up = (UserProfile) persistInstance(up, UserProfileTable.TABLE, upValues);
//
//		/********************************************************/
//		Login login = new LoginImpl();
//		login.setUserProfileId(up.getId());
//		login.setLogin("admin@event.dk");
//		login.setRole(ROLE.ADMIN);
//		login.setStartTS(LocalDateTime.now());
//					
//		Map<String, Object> adminValues = new HashMap<String, Object>();
//		adminValues.put(LoginTable.FLD_USER_PROFILE_ID, login.getUserProfileId());
//		adminValues.put(LoginTable.FLD_LOGIN, login.getLogin());
//		adminValues.put(LoginTable.FLD_ROLE, RoleUtil.getRoleAsString(login.getRole()));
//		adminValues.put(LoginTable.FLD_START_TS, login.getStartTS());
//
//		login = (Login) persistInstance(login, LoginTable.TABLE, adminValues);
//		
//		Password pwd = new PasswordImpl();
//		pwd.setLoginId(login.getId());
//		pwd.setPwd(encrypter.encrypt("Abcdefg"));
//		pwd.setStartTS(LocalDateTime.now());
//		
//		Map<String, Object> pwdValues = new HashMap<String, Object>();
//		pwdValues.put(PasswordTable.FLD_LOGIN_ID, pwd.getLoginId());
//		pwdValues.put(PasswordTable.FLD_PWD, pwd.getPwd());
//		pwdValues.put(PasswordTable.FLD_START_TS, pwd.getStartTS());
//		
//		pwd = (Password) persistInstance(pwd, PasswordTable.TABLE, pwdValues);

   /********************************************************/

//		Map<String, Object> plainValues = new HashMap<String, Object>();
//		login = self.establishLogin(plainValues);
//		pwd = self.establishPassword(plainValues);
//		up.addLogin(login);
//		login.addPassword(pwd);
//
//		Map<String, Object> monitorValues = new HashMap<String, Object>();
//		login = self.establishLogin(monitorValues);
//		pwd = self.establishPassword(monitorValues);
//		up.addLogin(login);
//		login.addPassword(pwd);
//
//		Map<String, Object> ownerValues = new HashMap<String, Object>();
//		login = self.establishLogin(ownerValues);
//		pwd = self.establishPassword(ownerValues);
//		up.addLogin(login);
//		login.addPassword(pwd);

//	private StringBuffer getInsertSQL(String table, Map<String, Object> values) {
//		List<String> columns = new ArrayList<String>(values.keySet());
//		StringBuffer sql = new StringBuffer();
//		sql.append(INSERT_INTO).append(table).append(SPACE);
//		sql.append(LEFT_PARENTHIS);
//		int i = 1;
//		int size = columns.size();
//		for (String col : columns) {
//			sql.append(col);
//			if (i < size) {
//				sql.append(SEP);
//			}
//			i++;
//		}
//		sql.append(RIGHT_PARENTHIS).append(SPACE);
//		sql.append(VALUES).append(SPACE);
//		sql.append(LEFT_PARENTHIS);
//		i = 1;
//		for (String col : columns) {
//			sql.append(BIND).append(col);
//			if (i < size) {
//				sql.append(SEP);
//			}
//			i++;
//		}
//
//		sql.append(RIGHT_PARENTHIS).append(SPACE);
//		return sql;
//	}
//
//	private SecureEntity persistInstance(SecureEntity entity, String table, Map<String, Object> baseValues) {
//
//		StringBuffer upSql = getInsertSQL(table, baseValues);
//
//		logger.debug("sql="+upSql);
//		
//		MapSqlParameterSource map = new MapSqlParameterSource(baseValues);
//		GeneratedKeyHolder upKeyHolder = new GeneratedKeyHolder();
//
//		int upInsert = jdbcTemplate.update(upSql.toString(), map, upKeyHolder);
//		logger.debug("Inserted " + upInsert + " row(s)");
//
//		if (upInsert != 1) {
//			throw new EventBaseException("Insert of UserProfile generated more than one update");
//		}
//
//		Integer id = (Integer) upKeyHolder.getKeys().get("id");
//		entity.setId(id);
//
//		logger.debug("Persisted:" + entity);
//
//		return entity;
//	}

}
