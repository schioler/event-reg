package dk.schioler.economy.web;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import dk.schioler.economy.web.configuration.EconomyWebConfiguration;
import dk.schioler.economy.web.view.EconomyDispatcherServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class EconomyWebApplicationInitializer implements WebApplicationInitializer {

	private Logger logger = LoggerFactory.getLogger(getClass());
	public EconomyWebApplicationInitializer() {

	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.debug("onStartup");
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		registerConfiguration(context);
		servletContext.addListener(new ContextLoaderListener(context));
		logger.debug("before addServlet()");
		ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",
				new EconomyDispatcherServlet(context, true));
		logger.debug("after addServlet");
		registration.setLoadOnStartup(1);
		registration.addMapping("/*");
	}

	private void registerConfiguration(AnnotationConfigWebApplicationContext context) {
		context.register(EconomyWebConfiguration.class);
	}

}
