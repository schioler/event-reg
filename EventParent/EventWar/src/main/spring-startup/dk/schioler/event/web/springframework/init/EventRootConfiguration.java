package dk.schioler.event.web.springframework.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@EnableWebMvc					
//@ComponentScan("dk.schioler.event.web")
@PropertySource("classpath:/event-web-${event.env}.properties")
@Configuration()
public class EventRootConfiguration {

   Logger logger = LoggerFactory.getLogger(getClass());

   public EventRootConfiguration() {
      logger.debug("***************************");
      logger.trace("EventWebconfiguration:conztructor called");
      String property = System.getProperty("event.env");
      logger.debug("event.env=" + property);
      logger.debug("Configuration:" + this.toString());
      logger.debug("***************************");
   }

	@Value("${db.user}")
	private String dbUser;
//
//	@Value("${db.password}")
//	private String dbPwd;
//
//	@Value("${db.url}")
//	private String dbUrl;
//
//	@Value("${salt}")
//	private String salt;
//
////	@Value("${posting.dir.process}")
////	private String dirPostingProcess;
//
//	@Value("${event-type.upload.directory}")
//	private String eventTypeUploadDirectory;

//	public void postConstruct() {
//		logger.debug("postConstruct: economy.env=" + economyEnv);
//		String sysEnv = System.getProperty("economy.env");
//
//		if (StringUtils.isBlank(sysEnv)) {
//			if (StringUtils.isNotBlank(economyEnv)) {
////				logger.debug("Will set economy.env on system level, with value=" + economyEnv);
//				System.setProperty("economy,env", economyEnv);
//			} else {
////				logger.debug("Can not set economy.env on system level, as currently it is null, even on local level");
//			}
//		} else {
////			logger.debug("economy,env has been set at system level");
//		}
//
//	}

//	@Bean
//	public DataSource getDataSource() {
//		logger.debug("getDatasource: dbUrl = " + this.dbUrl);
//		logger.debug("getDatasource: dbUser = " + this.dbUser);
//		BasicDataSource dataSource = new BasicDataSource();
////		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setDriverClassName("org.postgresql.Driver");
//		dataSource.setUrl(this.dbUrl);
//		dataSource.setUsername(this.dbUser);
//		dataSource.setPassword(this.dbPwd);
//
//		dataSource.setPoolPreparedStatements(true);
//		dataSource.setMaxOpenPreparedStatements(5);
//		dataSource.setLogAbandoned(true);
//		dataSource.setInitialSize(5);
//		dataSource.setDefaultAutoCommit(Boolean.TRUE);
//
//		return dataSource;
//	}
//
//	@Bean
//	public JdbcTransactionManager getTransactionManager() {
//		JdbcTransactionManager tx = new JdbcTransactionManager(getDataSource());
//
//		tx.setDefaultTimeout(20);
//		tx.setGlobalRollbackOnParticipationFailure(true);
//		tx.setValidateExistingTransaction(true);
//		tx.setRollbackOnCommitFailure(true);
//		tx.setTransactionSynchronization(org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRED);
//		return tx;
//
//	}

//   @Bean(name = "multipartResolver")
//   public MultipartResolver multipartResolver() {
//      StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
//
//      return resolver;
//   }
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

//	@Bean
//	public MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasenames("messages");
//		messageSource.addBasenames("locale/messages");
//		messageSource.setDefaultEncoding("UTF-8");
//		return messageSource;
//	}

}
