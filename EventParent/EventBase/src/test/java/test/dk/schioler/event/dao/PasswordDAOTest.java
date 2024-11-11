package test.dk.schioler.event.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

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
import dk.schioler.secure.entity.impl.LoginImpl;
import dk.schioler.secure.entity.impl.PasswordImpl;
import dk.schioler.secure.entity.impl.UserProfileImpl;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class PasswordDAOTest extends AbstractJUnit4SpringContextTests {

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
		
		try {
			UserProfileImpl profile = new UserProfileImpl();
			profile.setFirstName("Poul");
			profile.setLastName("Hansen");
			profile.setPrimaryStreet1("Hans Hansensvej 12");
			profile.setPrimaryPostalCode("1234");
			profile.setPrimaryCity("Hanserød");
			profile.setPrimaryCountry("Denmark");
			profile.setPrimaryPhone("+45 51517997");

			UserProfile insert = userProfileDAO.insert(profile);
			assertNotNull(insert);
			assertNotNull(insert.getId());
			assertNotNull(insert.getStartTS());
			assertNull(insert.getEndTS());

			Login login = new LoginImpl();
			login.setLogin("hans@hansen.dk");
			login.setRole(ROLE.ADMIN);
			login.setUserProfileId(insert.getId());
			login = loginDAO.insert(login);
			
			assertNotNull(login);
			assertNotNull(login.getId());
			assertNotNull(login.getStartTS());
			assertNull(login.getEndTS());

			String pwdEnc = encrypter.encrypt("password");
			
			Password pwd = new PasswordImpl();
			pwd.setPwd(pwdEnc);
			pwd.setLoginId(login.getId());
			pwd = passwordDAO.insert(pwd);
			
			String pwdVerify = encrypter.encrypt("password");
			
			Password password = passwordDAO.get(pwd.getId());
			assertEquals(pwdVerify, password.getPwd());			
			
			
		} catch (Exception e) {
			logger.error("", e);
			fail(e.getMessage());
		} finally {

		}
	}

}