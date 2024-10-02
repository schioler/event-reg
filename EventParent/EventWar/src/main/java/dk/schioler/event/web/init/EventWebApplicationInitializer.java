package dk.schioler.event.web.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class EventWebApplicationInitializer implements WebApplicationInitializer  {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	public EventWebApplicationInitializer() {
		logger.trace("Constructor");
	}


	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		String configuration = "";
		ctx.setConfigLocation(configuration);
		
		
		
	}
	
	
		
	
}
