package test.dk.schioler.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGetLocalizedMessages {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		try {

			String bundle = "dk.schioler.event.base.resources.EventListResources";

			Locale defLoc = Locale.getDefault();
			
//			Locale[] availableLocales = Locale.getAvailableLocales();
//			for (Locale locale : availableLocales) {
//				if ("da".equalsIgnoreCase(locale.getLanguage())) {
//				logger.debug("" + locale);
//			
//				}
//			}
			
			String key = "home.welcome";
			String noLangKey = "event.general";
			
			Locale usLoc = Locale.US;
			Locale daLoc = new Locale.Builder().setLanguage("da").build();

			ResourceBundle.clearCache();
			ResourceBundle resourceBundleDa = ResourceBundle.getBundle(bundle, daLoc);
			String welcomeMsg = resourceBundleDa.getString(key);
			logger.debug(key + "=" + welcomeMsg);
			assertEquals("Velkommen_da",welcomeMsg);
			
			String noLangMsg = resourceBundleDa.getString(noLangKey);
			logger.debug(key + "=" + noLangMsg);
			assertEquals("event_general_noloc", noLangMsg);	
			
			
			
			ResourceBundle.clearCache();
			ResourceBundle resourceBundleUS = ResourceBundle.getBundle(bundle, usLoc);
			welcomeMsg = resourceBundleUS.getString(key);
			logger.debug(key + "=" + welcomeMsg);
			
			noLangMsg = resourceBundleDa.getString(noLangKey);
			logger.debug(key + "=" + noLangMsg);
			assertEquals("event_general_noloc", noLangMsg);	
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			fail(e.getMessage());
		}

	}

}
