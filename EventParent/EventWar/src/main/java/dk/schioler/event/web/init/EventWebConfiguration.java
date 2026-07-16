package dk.schioler.event.web.init;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import dk.schioler.event.configuration.EventConfigConfiguration;

@EnableWebMvc
//@PropertySource("classpath:/event-${event.env}.properties")
@PropertySource("classpath:/event-web-${event.env}.properties")
//@ComponentScan("dk.schioler.shared.bits, dk.schioler.shared.security, dk.schioler.shared.timeline, dk.schioler.event.base,dk.schioler.event.web")
@ComponentScan(basePackages = "dk.schioler.event.web")
@Import(EventConfigConfiguration.class)
@Configuration()
public class EventWebConfiguration implements WebMvcConfigurer {

   private static PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();

   @Bean
   public static PropertySourcesPlaceholderConfigurer getPropertySourcesPlaceholderConfigurer() {
      return pspc;
   }

//   @Override
//   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//      configurer.enable();
//   }

   Logger logger = LoggerFactory.getLogger(getClass());

   public EventWebConfiguration() {
      logger.debug("***************************");
      logger.trace("EventWebconfiguration: Conztructor called");
      String property = System.getProperty("event.env");
      logger.debug("event.env=" + property);
      logger.debug("Configuration:" + this.toString());
//      logger.debug("dbUser=" + dbUser);
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

   @Value("${gui-show-session-variables}")
   private String guiShowSessionVariables;

//	@Value("${posting.dir.process}")
//	private String dirPostingProcess;

//   @Value("${event-type.upload.directory}")
//   private String eventTypeUploadDirectory;

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

//   @Bean
//   public DataSource getDataSource() {
//      logger.debug("getDatasource: dbUser = " + dbUser);
//      BasicDataSource dataSource = new BasicDataSource();
////		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
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

   @Bean
   public Boolean getShowSessionVars() {
      Boolean showSesVars = null;
      showSesVars = BooleanUtils.toBooleanObject(guiShowSessionVariables);
      if (showSesVars == null) {
         showSesVars = Boolean.FALSE;
      }

      return showSesVars;
   }



   private UrlBasedViewResolver resolver = new UrlBasedViewResolver();

//   private ViewResolver resolver = new MyViewResolver();

   @Bean("mvcViewResolver")
   public ViewResolver getViewResolver() {
      logger.debug("getViewResolver called");
      
      resolver.setViewClass(JstlView.class);
      resolver.setPrefix("/WEB-INF/views");
      resolver.setSuffix(".jsp");
      
      resolver.setRedirectContextRelative(true);

      return resolver;
   }

//   @Bean(name = "multipartResolver")
//   public MultipartResolver multipartResolver() {
//      StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
//
//      return resolver;
//   }

//   @Bean
//   public MessageSource messageSource() {
//      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//      messageSource.setBasenames("language");
//      messageSource.addBasenames("locale/messages");
//      messageSource.setDefaultEncoding("UTF-8");
//      return messageSource;
//   }

   @Bean
   public MessageSource messageSource() {
      ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
      messageSource.setBasenames("language");
      messageSource.addBasenames("locale/messages");
      messageSource.setDefaultEncoding("UTF-8");
      return messageSource;
   }

}
