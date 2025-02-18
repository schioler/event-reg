package test.dk.schioler.event.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import dk.schioler.configuration.EventBaseConfiguration;
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

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class PasswordDAOGetOnLoginIdTest extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserProfileDAO userProfileDAO;

	@Autowired
	LoginDAO loginDAO;

	@Autowired
	PasswordDAO passwordDAO;

	@Autowired
	Encrypter encrypter;

	@Test
	public void testParseInputStream() {
		UserProfile profile = null;
		Login login = null;
		Password pwd = null;
		Password pwd2 = null;
		Password pwd3 = null;
		try {
			String loginStr = "ls@ls.dk";
			ROLE role = ROLE.ADMIN;
			Login secLogin = loginDAO.getLogin(loginStr);
			
			profile = new UserProfileImpl();
			profile.setFirstName("Poul");
			profile.setLastName("Hansen");
			profile.setPrimaryStreet1("Hans Hansensvej 12");
			profile.setPrimaryPostalCode("1234");
			profile.setPrimaryCity("Hanserød");
			profile.setPrimaryCountry("Denmark");
			profile.setPrimaryPhone("+45 51517997");
			profile.setStartTS(LocalDateTime.now());

			profile = userProfileDAO.insert(profile);
			assertNotNull(profile);
			assertNotNull(profile);
			assertNotNull(profile);
			assertNull(profile);

			login = new LoginImpl();
			login.setToken("hans@hansen.dk");
			login.setRole(ROLE.ADMIN);
			login.setUserProfileId(profile.getId());
			login = loginDAO.insert(login);

			assertNotNull(login);
			assertNotNull(login.getId());
			assertNotNull(login.getStartTS());
			assertNull(login.getEndTS());

			String pwdEnc = encrypter.encrypt("password");

			pwd = new PasswordImpl();
			pwd.setPwd(pwdEnc);
			pwd.setLoginId(login.getId());
			pwd = passwordDAO.insert(pwd);

			String pwdVerify = encrypter.encrypt("password");

			Password password = passwordDAO.get(pwd.getId());
			assertEquals(pwdVerify, password.getPwd());

			
			pwd2 = new PasswordImpl();
			pwd2.setPwd(pwdEnc);
			pwd2.setLoginId(login.getId());
			pwd2 = passwordDAO.insert(pwd2);
			
			
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {

		}
	}

}