package dk.schioler.event.main.test.data;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dk.schioler.configuration.EventBaseConfiguration;
import dk.schioler.event.base.dao.impl.SQLConstructs;
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

//@RunWith(SpringJUnit4ClassRunner.class)

//@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
//@Component
public class EstablishBaseUser implements SQLConstructs {

   static {
      System.getProperties().setProperty("event.env", "dev1");
   }

   private static Logger logger = LoggerFactory.getLogger(EstablishBaseUser.class);

   protected static ApplicationContext ctx;

   public static void main(String[] args) {
      try {
         ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);

         userprofileDAO = ctx.getBean(UserProfileDAO.class);

         loginDAO = ctx.getBean(LoginDAO.class);

         passwordDAO = ctx.getBean(PasswordDAO.class);

         encrypter = ctx.getBean(Encrypter.class);

         EstablishBaseUser self = new EstablishBaseUser();

         String domain = "b";
         Login root = self.lookupRoot();
         
         Login ownerlogin = self.establishBaseUserSet(root, domain, ROLE.OWNER, 1);
         Login admlogin = self.establishBaseUserSet(ownerlogin, domain, ROLE.ADMIN, 1);
         Login admlogin2 = self.establishBaseUserSet(ownerlogin, domain, ROLE.ADMIN, 2);
         
         Login usr1login = self.establishBaseUserSet(admlogin, domain, ROLE.USER, 1);
         Login usr2login = self.establishBaseUserSet(admlogin, domain, ROLE.USER, 2);
         Login usr3login = self.establishBaseUserSet(admlogin, domain, ROLE.USER, 3);
         Login usr4login = self.establishBaseUserSet(admlogin, domain, ROLE.USER, 4);
         
         
         Login monilogin = self.establishBaseUserSet(usr1login, domain, ROLE.MONITOR, 1);
         Login monilogin2 = self.establishBaseUserSet(usr1login, domain, ROLE.MONITOR, 2);        
         
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

   public Login establishBaseUserSet(Login parentLogin, String domain, ROLE role, int idx) {
      UserProfile up = null;
      Login newLogin = null;
      try {
         String token = StringUtils.substring(role.toString(), 0,1) + idx + "@" + domain;
         up = new UserProfileImpl();
         up.setStartTS(LocalDateTime.now());
         up.setFirstName(role.toString()+idx);
         up.setLastName(domain);
         up.setPrimaryEmail(token);
         up.setPrimaryPhone("12341234");
         up = userprofileDAO.insert(up);

         String pwd = token;
         newLogin = new LoginImpl(null, LocalDateTime.now(), role, token );
         newLogin.setUserProfileId(up.getId());
         newLogin.setParentId(parentLogin.getId());

         newLogin = loginDAO.insert(newLogin);

         Password ownerPwd = new PasswordImpl();
         String enc = encrypter.encrypt(pwd);
         ownerPwd.setPwd(enc);
         ownerPwd.setStartTS(LocalDateTime.now());
         ownerPwd.setLoginId(newLogin.getId());
         ownerPwd = passwordDAO.insert(ownerPwd);
         return newLogin;
         
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
