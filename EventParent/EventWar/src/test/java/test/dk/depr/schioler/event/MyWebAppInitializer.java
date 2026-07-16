package test.dk.depr.schioler.event;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;

public class MyWebAppInitializer { 
//extends AbstractAnnotationConfigDispatcherServletInitializer implements WebApplicationInitializer {

//   @Override
//   public void onStartup(ServletContext servletContext) throws ServletException {
//      super.onStartup(servletContext);
//      Filter f = new UserFilter();
//      this.registerServletFilter(servletContext, f);
//      f  = new JspFilter();
//      this.registerServletFilter(servletContext, f);
//   }

//   @Override
//   protected Class<?>[] getRootConfigClasses() {
//
//      return null;
//   }
//
//   @Override
//   protected Class<?>[] getServletConfigClasses() {
//      Class<?>[] c = new Class[1];
//      c[0] = EventWebConfiguration.class;
//      return c;
//   }
//
//   @Override
//   protected WebApplicationContext createRootApplicationContext() {
//      return null;
//   }

//   
//   @Override
//   protected WebApplicationContext createServletApplicationContext() {
////      XmlWebApplicationContext cxt = new XmlWebApplicationContext();
////      cxt.setConfigLocation("/WEB-INF/spring/dispatcher-config.xml");
//      AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//      ctx.scan("dk.schioler.shared.bits", "dk.schioler.shared.security", "dk.schioler.shared.timeline", "dk.schioler.event.base", "dk.schioler.event.web");
//     
//      
//      
//      return ctx;
//   }
//
//   @Override
//   protected String[] getServletMappings() {
//      return new String[] { "*.do" };
//   }
//
//   @Override
//   protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
//
//      return super.createDispatcherServlet(servletAppContext);
//   }

   

   
}
