package test.dk.schioler.event.dao;



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

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.base.encrypt.Encrypter;
import dk.schioler.secure.dao.LoginDAO;
import dk.schioler.secure.dao.PasswordDAO;
import dk.schioler.secure.dao.UserProfileDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.ROLE;
import dk.schioler.secure.entity.UserProfile;
import dk.schioler.secure.entity.impl.UserProfileImpl;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class LoginDAOTest extends AbstractJUnit4SpringContextTests {

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
			profile = new UserProfileImpl();
			profile.setFirstName("Poul");
			profile.setLastName("Hansen");
			profile.setPrimaryStreet1("Hans Hansensvej 12");
			profile.setPrimaryPostalCode("1234");
			profile.setPrimaryCity("Hanserød");
			profile.setPrimaryCountry("Denmark");
			profile.setPrimaryPhone("+45 51517997");
			profile.setStartTS(LocalDateTime.now());

			profile =  userProfileDAO.insert(profile,null);
			
			
			String loginStr = "ls@ls.dk";
			ROLE role = ROLE.ADMIN;
			Login secLogin = loginDAO.getSecLogin(loginStr);
			
//			profile = new UserProfileImpl();
//			profile.setFirstName("Poul");
//			profile.setLastName("Hansen");
//			profile.setPrimaryStreet1("Hans Hansensvej 12");
//			profile.setPrimaryPostalCode("1234");
//			profile.setPrimaryCity("Hanserød");
//			profile.setPrimaryCountry("Denmark");
//			profile.setPrimaryPhone("+45 51517997");
//			profile.setStartTS(LocalDateTime.now());
//
//			profile = userProfileDAO.insert(profile,secLogin);
//			assertNotNull(profile);
//			assertNotNull(profile);
//			assertNotNull(profile);
//			assertNull(profile);
//
//			login = new LoginImpl();
//			login.setLogin("hans@hansen.dk");
//			login.setRole(ROLE.ADMIN);
//			login.setUserProfileId(profile.getId());
//			login = loginDAO.insert(login);
//
//			assertNotNull(login);
//			assertNotNull(login.getId());
//			assertNotNull(login.getStartTS());
//			assertNull(login.getEndTS());
//
//			String pwdEnc = encrypter.encrypt("password");
//
//			pwd = new PasswordImpl();
//			pwd.setPwd(pwdEnc);
//			pwd.setLoginId(login.getId());
//			pwd = passwordDAO.insert(pwd);
//
//			String pwdVerify = encrypter.encrypt("password");
//
//			Password password = passwordDAO.get(pwd.getId());
//			assertEquals(pwdVerify, password.getPwd());
//
//			
//			pwd2 = new PasswordImpl();
//			pwd2.setPwd(pwdEnc);
//			pwd2.setLoginId(login.getId());
//			pwd2 = passwordDAO.insert(pwd2);
			
			
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {

		}
	}

}