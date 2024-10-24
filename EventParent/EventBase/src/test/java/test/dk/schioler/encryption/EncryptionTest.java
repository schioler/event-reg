package test.dk.schioler.encryption;

import static org.junit.Assert.assertEquals;
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

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/ApplicationContext.xml")
@ContextConfiguration(classes = EventBaseConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class EncryptionTest extends AbstractJUnit4SpringContextTests {

	static {
		System.getProperties().setProperty("event.env", "dev1");
	}

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	Encrypter encrypter;

	@Test
	public void testEnc() {
		logger.debug("testEnc called");
		String password = "password";
		try {
			String encrypted = encrypter.encrypt(password);
			logger.info("password, encrypted=" + encrypted);
			
			String encrypted2 = encrypter.encrypt(password);
			logger.info("password, encrypted=" + encrypted2);
			
			assertEquals(encrypted, encrypted2);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}
		logger.debug("testEnc, done");
	}

}