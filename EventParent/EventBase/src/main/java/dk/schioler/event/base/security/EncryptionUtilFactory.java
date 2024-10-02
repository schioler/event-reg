package dk.schioler.event.base.security;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtilFactory {
	private static Logger LOG = LoggerFactory.getLogger(EncryptionUtilFactory.class);

	private static final String MY_FILE_NAME = "event-key.properties";

	private static final String MY_KEY_LOOKUP_KEY = "encKey";

	
	private static EncryptionUtil encodingUtil = null;
	
	static {
		String env = System.getProperty("event.env");

		String fileName = env + "-" + MY_FILE_NAME;
		LOG.debug("fileName=" + fileName);

		try {
			InputStream resourceAsStream = EncUtil.class.getResourceAsStream(fileName);
			Properties props = new Properties();
			props.load(resourceAsStream);
			String myKeyVal = props.getProperty(MY_KEY_LOOKUP_KEY);
			if (StringUtils.isAllBlank(myKeyVal)) {
				LOG.info("found no value for key....");
			}
			
			
			
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}

	}
	
	public static final EncryptionUtil getEncodingUtil() {
		return encodingUtil;
	}
}
