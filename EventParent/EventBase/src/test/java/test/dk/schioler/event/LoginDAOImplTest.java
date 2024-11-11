package test.dk.schioler.event;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
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

public class LoginDAOImplTest {

	static {
		System.setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserProfileDAO userProfileDAO;

	@Autowired
	LoginDAO loginDAO;

	@Autowired
	PasswordDAO passwordDAO;

	@Test
	public void test() {

		UserProfile up = null;
		Login login = null;
		Password pwd = null;
		try {
			up = new UserProfileImpl();
			up.setFirstName("Firstname");
			up.setLastName("LastName");
			up.setPrimaryPhone("12345678");
			up.setPrimaryEmail("lars@schioler.dk");

			up = userProfileDAO.insert(up);

			up.setPrimaryCountry("DK");
			userProfileDAO.update(up);

			login = new LoginImpl();
			login.setLogin("login");
			login.setRole(ROLE.ADMIN);
			login.setStartTS(LocalDateTime.now());
			login.setUserProfileId(up.getId());

			login = loginDAO.insert(login);

			pwd = new PasswordImpl();
			pwd.setLoginId(login.getId());
			pwd.setPwd("pwd");
			pwd = passwordDAO.insert(pwd);
			
			

		} catch (Exception e) {
			fail(e.getMessage());
		} finally {
			passwordDAO.delete(pwd.getId());
			loginDAO.delete(login.getId());
			userProfileDAO.delete(up.getId());
		}
	}
}
