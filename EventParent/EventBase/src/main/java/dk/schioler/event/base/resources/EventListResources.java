package dk.schioler.event.base.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EventListResources extends AbstractEventListResources {

	private Properties props = new Properties();
	
	public EventListResources() {
		super();
		String file = "/event.properties";
		logger.trace("loadMessages;" + file);
		InputStream is = null;
		try {
			is = EventListResources.class.getResourceAsStream(file);

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

	}

	@Override
	protected Properties getProps() {
		return this.props;
	}

//	protected Object[][] getContents() {
//		
//		
//		Set<Entry<Object,Object>> entrySet = props.entrySet();
//		
//		Object[][] keyVals = new Object[entrySet.size()][entrySet.size()];
//		int i = 0;
//		for (Entry<Object, Object> entry : entrySet) {
//			Object key = entry.getKey();
//			Object val = entry.getValue();
//			keyVals[i][0] = key;
//			keyVals[i][1] = val;
//			i++;
//			
//		}m
//		return keyVals;
////		return new Object[][] {
////				// LOCALIZE THE SECOND STRING OF EACH ARRAY (e.g., "OK")
////				{ "home.welcome", "WelCm_def" }, { "dummy", "Yummi" },
////				// END OF MATERIAL TO LOCALIZE
////		};
//	}
}
