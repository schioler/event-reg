package dk.schioler.event.base.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncUtil {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String MY_FILE_NAME = "event-key.properties";

	private static final String MY_KEY_LOOKUP_KEY = "encKey";

	private SecretKey key;

	private static EncUtil encUtil;

	static {
		encUtil = new EncUtil();
	}

	private EncUtil() {
		String env = System.getProperty("event.env");

		String fileName = env + "-" + MY_FILE_NAME;
		logger.debug("fileName=" + fileName);

		try {
			InputStream resourceAsStream = EncUtil.class.getResourceAsStream(fileName);
			Properties props = new Properties();
			props.load(resourceAsStream);
			String myKeyVal = props.getProperty(MY_KEY_LOOKUP_KEY);
			if (StringUtils.isAllBlank(myKeyVal)) {
				logger.info("found no value for key....");
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

	public void generateKey() throws NoSuchAlgorithmException {
		// Generating objects of KeyGenerator &
		// SecretKey
		KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		SecretKey myDesKey = keygenerator.generateKey();
//		this.key 
	}

	public static void storeKey() {

	}

}
