package dk.schioler.event.base.resources;

import java.util.ListResourceBundle;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEventListResources extends ListResourceBundle {

//	public final static String KEY_HOME_WELCOME = "home.welcome";
//	public final static String KEY_EVENTTYPE_NAME = "eventtype.name";

	protected Logger logger = LoggerFactory.getLogger(getClass());

//	protected Properties props = new Properties();

	public AbstractEventListResources() {
		super();

	}

	protected abstract Properties getProps(); 
	
	protected Object[][] getContents() {

		Set<Entry<Object, Object>> entrySet = getProps().entrySet();

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
