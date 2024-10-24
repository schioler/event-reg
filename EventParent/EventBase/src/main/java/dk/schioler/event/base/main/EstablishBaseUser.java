package dk.schioler.event.base.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import dk.schioler.event.base.EventBaseException;
import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.encrypt.Encrypter;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.ROLE;
import dk.schioler.secure.entity.RoleUtil;
import dk.schioler.secure.entity.SecureEntity;
import dk.schioler.secure.entity.UserProfile;
import dk.schioler.secure.entity.impl.LoginImpl;
import dk.schioler.secure.entity.impl.PasswordImpl;
import dk.schioler.secure.entity.impl.UserProfileImpl;
import dk.schioler.secure.table.LoginTable;
import dk.schioler.secure.table.PasswordTable;
import dk.schioler.secure.table.SQLConstructs;
import dk.schioler.secure.table.UserProfileTable;

@Component
public class EstablishBaseUser implements SQLConstructs {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	private static Logger logger = LoggerFactory.getLogger(EstablishBasisEventTypesAndTmplsMain.class);

	protected static ApplicationContext ctx;

	public static void main(String[] args) {
		try {
			ctx = new AnnotationConfigApplicationContext(EventBaseConfiguration.class);

			EstablishBaseUser self = ctx.getBean(EstablishBaseUser.class);
			self.establishBaseUser();
			
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/*********************************************/

	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	private Encrypter encrypter;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		logger.trace("setDataSource:" + jdbcTemplate);
	}

	private void establishBaseUser() {
		
		UserProfile up = new UserProfileImpl();
		up.setFirstName("ROOT");
		up.setLastName("BaseUser");
		up.setPrimaryEmail("root@event.dk");
		up.setStartTS(LocalDateTime.now());
		
		Map<String, Object> upValues = new HashMap<String, Object>();
		upValues.put(UserProfileTable.FLD_FIRST_NAME, up.getFirstName());
		upValues.put(UserProfileTable.FLD_LAST_NAME, up.getLastName());
		upValues.put(UserProfileTable.FLD_PRIMARY_EMAIL, up.getPrimaryEmail());
		upValues.put(UserProfileTable.FLD_START_TS, up.getStartTS());		
		up = (UserProfile) persistInstance(up, UserProfileTable.TABLE, upValues);

		/********************************************************/
		Login login = new LoginImpl();
		login.setUserProfileId(up.getId());
		login.setLogin("admin@event.dk");
		login.setRole(ROLE.ADMIN);
		login.setStartTS(LocalDateTime.now());
					
		Map<String, Object> adminValues = new HashMap<String, Object>();
		adminValues.put(LoginTable.FLD_USER_PROFILE_ID, login.getUserProfileId());
		adminValues.put(LoginTable.FLD_LOGIN, login.getLogin());
		adminValues.put(LoginTable.FLD_ROLE, RoleUtil.getRoleAsString(login.getRole()));
		adminValues.put(LoginTable.FLD_START_TS, login.getStartTS());

		login = (Login) persistInstance(login, LoginTable.TABLE, adminValues);
		
		Password pwd = new PasswordImpl();
		pwd.setLoginId(login.getId());
		pwd.setPwd(encrypter.encrypt("Abcdefg"));
		pwd.setStartTS(LocalDateTime.now());
		
		Map<String, Object> pwdValues = new HashMap<String, Object>();
		pwdValues.put(PasswordTable.FLD_LOGIN_ID, pwd.getLoginId());
		pwdValues.put(PasswordTable.FLD_PWD, pwd.getPwd());
		pwdValues.put(PasswordTable.FLD_START_TS, pwd.getStartTS());
		
		pwd = (Password) persistInstance(pwd, PasswordTable.TABLE, pwdValues);
		
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

		
	}

	private StringBuffer getInsertSQL(String table, Map<String, Object> values) {
		List<String> columns = new ArrayList<String>(values.keySet());
		StringBuffer sql = new StringBuffer();
		sql.append(INSERT_INTO).append(table).append(SPACE);
		sql.append(LEFT_PARENTHIS);
		int i = 1;
		int size = columns.size();
		for (String col : columns) {
			sql.append(col);
			if (i < size) {
				sql.append(SEP);
			}
			i++;
		}
		sql.append(RIGHT_PARENTHIS).append(SPACE);
		sql.append(VALUES).append(SPACE);
		sql.append(LEFT_PARENTHIS);
		i = 1;
		for (String col : columns) {
			sql.append(BIND).append(col);
			if (i < size) {
				sql.append(SEP);
			}
			i++;
		}

		sql.append(RIGHT_PARENTHIS).append(SPACE);
		return sql;
	}

	private SecureEntity persistInstance(SecureEntity entity, String table, Map<String, Object> baseValues) {

		StringBuffer upSql = getInsertSQL(table, baseValues);

		logger.debug("sql="+upSql);
		
		MapSqlParameterSource map = new MapSqlParameterSource(baseValues);
		GeneratedKeyHolder upKeyHolder = new GeneratedKeyHolder();

		int upInsert = jdbcTemplate.update(upSql.toString(), map, upKeyHolder);
		logger.debug("Inserted " + upInsert + " row(s)");

		if (upInsert != 1) {
			throw new EventBaseException("Insert of UserProfile generated more than one update");
		}

		Integer id = (Integer) upKeyHolder.getKeys().get("id");
		entity.setId(id);

		logger.debug("Persisted:" + entity);

		return entity;
	}

}
