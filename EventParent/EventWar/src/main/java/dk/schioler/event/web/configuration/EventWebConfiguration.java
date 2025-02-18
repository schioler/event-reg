package dk.schioler.event.web.configuration;


import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@PropertySource("classpath:/event-web-${event.env}.properties")
@ComponentScan("dk.schioler.shared.security, dk.schioler.shared.timeline, dk.schioler.event.base, dk.schioler.event.web")
@Configuration
public class EventWebConfiguration implements WebMvcConfigurer {

   private static PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

   @Bean
   public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
      return pspc;
   }

   @Autowired
   Environment env;

   Logger logger = LoggerFactory.getLogger(getClass());

   public EventWebConfiguration() {
      logger.trace("EventWebconfiguration:conztructor called");
      String property = System.getProperty("event.env");
      logger.debug("event.env=" + property);

//		String clsPath = System.getProperty("java.class.path");
//		logger.debug("***************************");
//		logger.debug(clsPath);
//		logger.debug("***************************");
   }

   @Value("${event.db.user}")
   private String dbUser;

   @Value("${event.db.password}")
   private String dbPwd;

   @Value("${event.db.url}")
   private String dbUrl;

   @Value("${event.salt}")
   private String salt;

   @Bean
   public DataSource getDataSource() {
      logger.debug("getDatasource: dbUrl = " + this.dbUrl);
      logger.debug("getDatasource: dbUser = " + this.dbUser);
      BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
      dataSource.setDriverClassName("org.postgresql.Driver");
      dataSource.setUrl(this.dbUrl);
      dataSource.setUsername(this.dbUser);
      dataSource.setPassword(this.dbPwd);

      dataSource.setPoolPreparedStatements(true);
      dataSource.setMaxOpenPreparedStatements(5);
      dataSource.setLogAbandoned(true);
      dataSource.setInitialSize(5);
      dataSource.setDefaultAutoCommit(Boolean.TRUE);

      return dataSource;
   }
   
//   @Bean
//   public Encrypter getEncrypter() {
//      EncrypterSHA256 enc = new EncrypterSHA256();
//      enc.setSalt(salt);
//      return enc;
//   }

//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//
//		WebMvcConfigurer.super.configureViewResolvers(registry);
//	}
//
//   private InternalResourceViewResolver resolver = new InternalResourceViewResolver("WEB-INF/views", ".jsp");

//   @Bean
//   public InternalResourceViewResolver getViewResolver() {
//      return resolver;
//   }

   
   
   @Bean
   public MessageSource messageSource() {
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasenames("messages");
      messageSource.addBasenames("locale/messages");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
   }

}
