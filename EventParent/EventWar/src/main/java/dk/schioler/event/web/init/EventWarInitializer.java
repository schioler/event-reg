package dk.schioler.event.web.init;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import dk.schioler.event.web.configuration.EventWebConfiguration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class EventWarInitializer implements WebApplicationInitializer {

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
      AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
      rootContext.register(EventWebConfiguration.class);

      DispatcherServlet dispatcherServlet = new DispatcherServlet(rootContext);
      ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);
      dispatcher.setLoadOnStartup(1);
      dispatcher.addMapping("*.do");

   }

}
