package dk.schioler.event.web.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.support.ResourcePropertySource;

//public class ConfigInitializer {
//
//}
public class ConfigInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
   protected Logger logger = LoggerFactory.getLogger(getClass());
   @Override
   public void initialize(ConfigurableApplicationContext ctx) {

      try {
         // rule over all the others:
         ctx.getEnvironment().getPropertySources().addFirst(new ResourcePropertySource("file:/etc/conf/your_custom.properties"));

         // rule over application.properties but not argument or systemproperties etc
         ctx.getEnvironment().getPropertySources().addBefore("applicationConfig: [classpath:/application.properties]",
               new ResourcePropertySource("classpath:your_custom.properties"));

         // names can be discovered by placing a breakpoint somewhere here and watch
         // ctx.getEnvironment().getPropertySources().propertySourceList members,
         // each has a name ...
      } catch (Exception e) {
         logger.error(e.getMessage(), e);
      }
   }
}