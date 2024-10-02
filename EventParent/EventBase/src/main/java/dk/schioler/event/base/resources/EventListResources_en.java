package dk.schioler.event.base.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EventListResources_en extends AbstractEventListResources {

	private Properties props = new Properties();

	public EventListResources_en() {
		super();
		String file = "/event_en.properties";
		logger.debug("loadMessages;" + file);
		InputStream is = null;
		try {
			is = EventListResources.class.getResourceAsStream(file);

			Properties props = new Properties();
			props.load(is);

			this.props = props;
			logger.debug("messages:" + this.props);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
//		return new Object[][] {
//				// LOCALIZE THE SECOND STRING OF EACH ARRAY (e.g., "OK")
//				{ "home.welcome", "WelCm_en" }, { "dummy", "Yummi" },
//				// END OF MATERIAL TO LOCALIZE
//		};
//	}
}
