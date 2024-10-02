package dk.schioler.event.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import dk.schioler.event.base.configuration.EventBaseConfiguration;
import dk.schioler.event.web.configuration.EventWebConfiguration;

public class EventAnnotationConfigDispatcherServletInitializer
		extends AbstractAnnotationConfigDispatcherServletInitializer {
	private Logger logger = LoggerFactory.getLogger(getClass());

	public EventAnnotationConfigDispatcherServletInitializer() {
		super();
		logger.trace("constructor called");
//		logger.trace("System.env:");
//		Map<String, String> getenv = System.getenv();
//		for (Map.Entry<String, String> entry : getenv.entrySet()) {
//			String key = entry.getKey();
//			String val = entry.getValue();
//			logger.trace(key + "=" + val);
//		}
//		logger.trace("System.properties:");
//		Properties properties = System.getProperties();
//		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
//			Object key = entry.getKey();
//			Object val = entry.getValue();
//			logger.trace(key + "=" + val);
//		}
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class<?>[]{EventWebConfiguration.class, EventBaseConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AnnotationConfigWebApplicationContext.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "*.do"};
	}

}
