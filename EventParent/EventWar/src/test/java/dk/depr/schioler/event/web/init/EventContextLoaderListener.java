package dk.depr.schioler.event.web.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;

public class EventContextLoaderListener extends ContextLoaderListener  {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public EventContextLoaderListener() {
		logger.trace("EventContextLoaderListener.Constructor");
	}
	
	

	@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
	}

	@Override	
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		return super.initWebApplicationContext(servletContext);

	}

//	@Override
//	protected WebApplicationContext createWebApplicationContext(ServletContext sc) {
//		logger.trace("EventContextLoaderListener.createWebACtx");
//		AnnotationConfigWebApplicationContext acac = new AnnotationConfigWebApplicationContext();
//		acac.setServletContext(sc);
//		
////		acac.s
//		return acac;
////		return super.createWebApplicationContext(sc);
//	}

//	@Override
//	public void closeWebApplicationContext(ServletContext servletContext) {
//
//		@SuppressWarnings("unchecked")
//		List<String> chartfilesToBeDeleted = (List<String>) servletContext.getAttribute(CTX_CHARTS_TO_BE_DELETED);
//
//		if (chartfilesToBeDeleted != null) {
//			for (String chartFile : chartfilesToBeDeleted) {
//				File f = new File(chartFile);
//				try {
//					boolean del = f.delete();
//					logger.info("deleted " + chartFile + ", res=" + del);
//				} catch (Exception e) {
//					logger.error(e.getMessage(), e);
//				}
//			}
//		}
//
//		super.closeWebApplicationContext(servletContext);
//	}

}
