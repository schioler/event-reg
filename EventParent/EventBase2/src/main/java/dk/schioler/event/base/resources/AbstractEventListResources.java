package dk.schioler.event.base.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEventListResources extends ListResourceBundle {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Properties props = new Properties();

	public AbstractEventListResources(Locale locale) {
		super();
		String file = null;

		if (locale == null) {
			file = "/event.properties";
		} else if (locale.getLanguage().equalsIgnoreCase("da")) {
			file = "/event_da.properties";
		} else if (locale.equals(Locale.UK)) {
			file = "/event_en.properties";
		} else if (locale.equals(Locale.US)) {
			file = "/event_en.properties";

		}

		logger.debug("attempting: loadMessages from;" + file);
		InputStream is = null;
		is = EventListResources.class.getResourceAsStream(file);
		if (is == null) {
			logger.debug("found no " + file);
			String file2 = "/properties" + file;
			logger.debug("attempting: loadMessages from;" + file2);
			is = EventListResources.class.getResourceAsStream(file2);
		}

		if (is != null) {
			try {
				Properties props = new Properties();
				props.load(is);

				this.props = props;
				logger.debug("messages:" + this.props);
			} catch (Exception e) {
				throw new EventResourceException(e.getMessage(), e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			throw new EventResourceException("could not find any resource bundle");
		}

	}

//	protected abstract Properties getProps(); 

	protected Object[][] getContents() {

		Set<Entry<Object, Object>> entrySet = props.entrySet();

		Object[][] keyVals = new Object[entrySet.size()][entrySet.size()];
		int i = 0;
		for (Entry<Object, Object> entry : entrySet) {
			Object key = entry.getKey();
			Object val = entry.getValue();
			keyVals[i][0] = key;
			keyVals[i][1] = val;
			i++;
		}
		return keyVals;
	}

	// return new Object[][] {
//				// LOCALIZE THE SECOND STRING OF EACH ARRAY (e.g., "OK")
//				{ "home.welcome", "WelCm_def" }, { "dummy", "Yummi" },
//				// END OF MATERIAL TO LOCALIZE
//		};
}
