package dk.schioler.event.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@PropertySource("classpath:/event-${event.env}-config.properties")
@ComponentScan("dk.schioler.shared.bits, dk.schioler.shared.security, dk.schioler.shared.timeline")
//@ComponentScan("org.springframework.jdbc , org.springframework.transaction")
@ComponentScan("dk.schioler.event.configuration")
@Configuration()
public class EventConfigConfiguration {

   private static PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

   @Bean
   public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
      return pspc;
   }
 

   private Logger logger = LoggerFactory.getLogger(getClass());

   public EventConfigConfiguration() {
      logger.debug("***************************");
      logger.trace("EventBaseConfiguration: Conztructor called");
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

      
}
