package dk.schioler.event.web.springframework.init;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import dk.schioler.event.web.init.EventBaseConfiguration;
import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import jakarta.servlet.ServletRegistration.Dynamic;


public class DispatcherWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   private final Logger logger = LoggerFactory.getLogger(getClass());

//   @Value("${dir.temp}")
//   private String dirTemp;

   public DispatcherWebAppInitializer() {
      super();
      logger.debug("*****************************************");
      logger.debug("DispatcherWebAppInitializer: constructor: called:");
      logger.debug("event.env="+ System.getProperty("entity.env"));
      logger.debug("*****************************************");
   }

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
//      super.onStartup(servletContext);
      logger.debug("onstartup called. ");
      logger.debug("event.env="+ System.getProperty("entity.env"));
      // WebApplicationInitializer
      
      // Create the 'root' Spring application context
      AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
      rootContext.register(EventRootConfiguration.class);
      logger.debug("rootContext has been registered");

      // Manage the lifecycle of the root application context
      servletContext.addListener(new ContextLoaderListener(rootContext));

      // Create the dispatcher servlet's Spring application context
      AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
      dispatcherContext.register(EventBaseConfiguration.class);
      logger.debug("webContext has been registered");

      // Register and map the dispatcher servlet
      DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
      
      
      ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher", dispatcherServlet);

      dispatcher.setLoadOnStartup(1);
      dispatcher.addMapping("/");
//      dispatcher.addMapping("/secure/*");     
      long maxsize = 10l * 1024 * 1024 * 1024;
      dispatcher.setMultipartConfig(new MultipartConfigElement(null, maxsize, maxsize, 0));
   }

   @Override
   protected Class<?>[] getRootConfigClasses() {
      Class<?>[] configClass = new Class<?>[1];
      configClass[0] = EventRootConfiguration.class;
      return configClass;
//      return null;
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      Class<?>[] configClass = new Class<?>[1];
      configClass[0] = EventBaseConfiguration.class;
//      configClass[0] = EventBaseConfiguration.class;
      return configClass;
//      return null;
   }

   @Override
   protected String[] getServletMappings() {
      String[] mappings = new String[1];
      mappings[0] = "/";
//		mappings[1] = "/secure/*";
      return mappings;
//      return null;
      
   }

//   @Override
//   protected String getServletName() {
//      String servletName = super.getServletName();
//      logger.debug(servletName);
//      return servletName;
//   }

   @Override
   protected Filter[] getServletFilters() {
      Filter[] filters = super.getServletFilters();
      if (filters != null && filters.length > 0) {

         for (int i = 0; i < filters.length; i++) {
            logger.debug("filter:" + filters[i]);
         }
      }
      return filters;
   }

   @Override
   protected void customizeRegistration(Dynamic registration) {
      logger.debug("customizeRegistration(registration");
      
      Map<String, String> initParameters = registration.getInitParameters();
      logger.debug("initParametrers:");
      Set<Entry<String, String>> entrySet = initParameters.entrySet();
      for (Entry<String, String> entry : entrySet) {
         logger.debug(entry.getKey() + "" + entry.getValue());
      }
//		registration.setInitParameter(economyEnv, dirPostingProcess)
//      registration.setLoadOnStartup(1);'

      Collection<String> mappings = registration.getMappings();
      logger.debug("mappings:");
      for (String string : mappings) {
         logger.debug("mapping=" + string);
      }
      
//      registration.setMultipartConfig(new MultipartConfigElement(dirTemp));

//      registration.setMultipartConfig(new MultipartConfigElement((String) null));
//      MultipartConfigElement cElement = new MultipartConfigElement(".", 555555, 555555, 5555555);
//
//      registration.setMultipartConfig(cElement);
      
   }

}
