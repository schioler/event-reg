package dk.schioler.event.web.init;

import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import dk.schioler.event.web.configuration.EventWebConfiguration;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;

public class EventAnnotationConfigDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
   private Logger logger = LoggerFactory.getLogger(getClass());

   public EventAnnotationConfigDispatcherServletInitializer() {
      super();
      logger.trace("constructor called");
      logger.trace("*******************************************");
      logger.trace("*******************************************");
      logger.trace("System.env:");
      Map<String, String> getenv = System.getenv();
      for (Map.Entry<String, String> entry : getenv.entrySet()) {
         String key = entry.getKey();
         String val = entry.getValue();
         logger.trace(key + "=" + val);
      }
      logger.trace("*************************************");
      logger.trace("*************************************");
      logger.trace("System.properties:");
      Properties properties = System.getProperties();
      for (Map.Entry<Object, Object> entry : properties.entrySet()) {
         Object key = entry.getKey();
         Object val = entry.getValue();
         logger.trace(key + "=" + val);
      }
      logger.trace("*************************************");
      logger.trace("*************************************");
   }

//   @Override
//   protected Class<?>[] getRootConfigClasses() {
////	   return new Class<?>[]{AnnotationConfigWebApplicationContext.class};
//      return new Class<?>[] {};
//
//   }

//   @Override
//   protected Class<?>[] getServletConfigClasses() {
//      return new Class[] { EventWebConfiguration.class };
//   }
//
//   @Override
//   protected String[] getServletMappings() {
//      return new String[] { "*.do" };
//   }

//   @Override
//   protected WebApplicationContext createRootApplicationContext() {
//   
//      return super.createRootApplicationContext();
//   }
//
//   @Override
//   protected WebApplicationContext createServletApplicationContext() {
//      return super.createServletApplicationContext();
//   }
//
//   @Override
//   protected void registerDispatcherServlet(ServletContext servletContext) {
//      logger.debug("servletContext=" + servletContext);
//      super.registerDispatcherServlet(servletContext);
//   }

//
//   @Override
//   protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
//      FrameworkServlet dispatcherServlet = super.createDispatcherServlet(servletAppContext);
//      ServletConfig servletConfig = dispatcherServlet.getServletConfig();
//      logger.debug("servletConfig:" + servletConfig);
//      
//      ServletContext servletContext = dispatcherServlet.getServletContext();
//      logger.debug("servletContext:" + servletContext);
//
//      dispatcherServlet.set
//      dispatcherServlet.setEnableLoggingRequestDetails(true);
//      return dispatcherServlet;
//   }

//
//   @Override
//   protected void registerContextLoaderListener(ServletContext servletContext) {
//      logger.debug("servletContext:" + servletContext);
//      super.registerContextLoaderListener(servletContext);
//   }

//
//   @Override
//   protected ApplicationContextInitializer<?>[] getRootApplicationContextInitializers() {
//      return super.getRootApplicationContextInitializers();
//
//   }

}
