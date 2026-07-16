package dk.schioler.event.tools.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import dk.schioler.event.base.EventBaseConfiguration;
import dk.schioler.event.configuration.EventConfigConfiguration;

@Configuration()
@ComponentScan("dk.schioler.shared.security, dk.schioler.shared.timeline, dk.schioler.shared.bits")
@Import({ EventBaseConfiguration.class, EventConfigConfiguration.class })
@PropertySource("classpath:/event-${event.env}-tools.properties")
public class EventToolsConfiguration extends EventConfigConfiguration {

   private static PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

   @Bean
   public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
      return pspc;
   }

   Logger logger = LoggerFactory.getLogger(getClass());

   public EventToolsConfiguration() {
      logger.debug("***************************");
      logger.trace("dk.schioler.event.tools.EventToolsConfiguration: Conztructor called");
      String property = System.getProperty("event.env");
      logger.debug("event.env=" + property);
      logger.debug("Configuration:" + this.toString());
      logger.debug("***************************");
   }

//   @Value("${db.user}")
//   private String dbUser;
//
//   @Value("${db.password}")
//   private String dbPwd;
//
//   @Value("${db.url}")
//   private String dbUrl;
//
//   @Value("${salt}")
//   private String salt;

//   @Bean
//   public DataSource getDataSource() {
//      logger.debug("getDatasource: dbUser = " + dbUser);
//      BasicDataSource dataSource = new BasicDataSource();
//      dataSource.setDriverClassName("org.postgresql.Driver");
//      dataSource.setUrl(this.dbUrl);
//      dataSource.setUsername(this.dbUser);
//      dataSource.setPassword(this.dbPwd);
//
//      dataSource.setPoolPreparedStatements(true);
//      dataSource.setMaxOpenPreparedStatements(5);
//      dataSource.setLogAbandoned(true);
//      dataSource.setInitialSize(5);
//      dataSource.setDefaultAutoCommit(Boolean.TRUE);
//
//      return dataSource;
//   }

//   @Bean
//   public JdbcTransactionManager getTransactionManager() {
//      JdbcTransactionManager tx = new JdbcTransactionManager(getDataSource());
//
//      tx.setDefaultTimeout(20);
//      tx.setGlobalRollbackOnParticipationFailure(true);
//      tx.setValidateExistingTransaction(true);
//      tx.setRollbackOnCommitFailure(true);
//      tx.setTransactionSynchronization(org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED);
//      return tx;
//
//   }

//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//
//		WebMvcConfigurer.super.configureViewResolvers(registry);
//	}
//

//   @Bean
//   public MessageSource messageSource() {
//      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//      messageSource.setBasenames("language");
//      messageSource.addBasenames("locale/messages");
//      messageSource.setDefaultEncoding("UTF-8");
//      return messageSource;
//   }

}
