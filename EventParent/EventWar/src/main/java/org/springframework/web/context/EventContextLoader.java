package org.springframework.web.context;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import jakarta.servlet.ServletContext;

public class EventContextLoader extends ContextLoader {

   Logger logger = LoggerFactory.getLogger(getClass());

   @Override
   public void setContextInitializers(ApplicationContextInitializer<?>... initializers) {

      logger.trace("setContextIitializers: intializers:" + initializers);
      super.setContextInitializers(initializers);
      logger.trace("setContextIitializers: intializers:" + initializers);
   }

   @Override
   public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
      logger.trace("initWebApplicationContext: context=:" + servletContext);
      WebApplicationContext initWebApplicationContext = super.initWebApplicationContext(servletContext);
      logger.trace("initWebApplicationContext: context=:" + initWebApplicationContext);
      return initWebApplicationContext;
   }

   @Override
   protected WebApplicationContext createWebApplicationContext(ServletContext sc) {
      logger.trace("createWebApplicationContext: context=:" + sc);
      WebApplicationContext webApplicationContext = super.createWebApplicationContext(sc);
      logger.trace("createWebApplicationContext: context=:" + webApplicationContext);
      return webApplicationContext;
   }

   @Override
   protected Class<?> determineContextClass(ServletContext servletContext) {
      logger.trace("determineContextClass: context=:" + servletContext);
      Class<?> determineContextClass = super.determineContextClass(servletContext);
      logger.trace("determineContextClass: contextclass=:" + determineContextClass);
      return determineContextClass;
   }

   @Override
   protected void configureAndRefreshWebApplicationContext(ConfigurableWebApplicationContext wac, ServletContext sc) {
      logger.debug("configureAndRefreshWebApplicationContext:" + wac + ", ctx=" + sc);
      super.configureAndRefreshWebApplicationContext(wac, sc);
   }

   @Override
   protected void customizeContext(ServletContext sc, ConfigurableWebApplicationContext wac) {
      logger.trace("customizeContext(ServletContext sc, ConfigurableWebApplicationContext wac");
      logger.trace("sc=" + sc + ", wac=" + wac);
      super.customizeContext(sc, wac);
   }

   @Override
   protected List<Class<ApplicationContextInitializer<ConfigurableApplicationContext>>> determineContextInitializerClasses(ServletContext servletContext) {

      List<Class<ApplicationContextInitializer<ConfigurableApplicationContext>>> determineContextInitializerClasses = super.determineContextInitializerClasses(
            servletContext);

      logger.trace("determineContextInitializerClasses=" + determineContextInitializerClasses);
      return determineContextInitializerClasses;
   }

   @Override
   protected ApplicationContext loadParentContext(ServletContext servletContext) {
      logger.trace("loadParentContext:" + servletContext);
      ApplicationContext parentContext = super.loadParentContext(servletContext);

      logger.trace("loadParentContext:" + parentContext);
      return parentContext;

   }

   @Override
   public void closeWebApplicationContext(ServletContext servletContext) {
      super.closeWebApplicationContext(servletContext);
   }

}
