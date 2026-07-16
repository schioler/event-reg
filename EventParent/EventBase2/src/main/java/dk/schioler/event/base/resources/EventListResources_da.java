package dk.schioler.event.base.resources;

import org.apache.commons.lang3.LocaleUtils;

public class EventListResources_da extends AbstractEventListResources {

//	private Properties props = new Properties();
	
	public EventListResources_da() {
		super(LocaleUtils.toLocale("da"));
//		String file = "/event_da.properties";
//		logger.trace("loadMessages;" + file);
//		InputStream is = null;
//		try {
//			is = EventListResources.class.getResourceAsStream(file);
//
//			Properties props = new Properties();
//			props.load(is);
//
//			this.props = props;
//			logger.debug("messages:" + this.props);
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw new EventResourceException(e.getMessage(), e);
//		} finally {
//			if (is != null) {
//				try {
//					is.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}

	}

//	@Override
//	protected Properties getProps() {
//		return props;
//	}

	
}
