package dk.schioler.event.web.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
@PropertySource("classpath:/event-web-${event.env}.properties")
@ComponentScan("dk.schioler.event.web")
public class EventWebConfiguration implements WebMvcConfigurer {

	Logger logger = LoggerFactory.getLogger(getClass());

	public EventWebConfiguration() {
		logger.trace("EventWebconfiguration:conztructor called");
		String property = System.getProperty("event.env");
		logger.debug("event.env=" + property);
		
		String clsPath = System.getProperty("java.class.path");
		logger.debug("***************************");
		logger.debug(clsPath);
		logger.debug("***************************");
	}
//
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/home.jsp").setViewName("home");
//	}

	@Value("${event.db.user}")
	private String dbUser;

	@Value("${event.db.password}")
	private String dbPwd;

	@Value("${event.db.url}")
	private String dbUrl;

	@Bean
	public DataSource getDataSource() {
		logger.debug("getDatasource: dbUrl = " + dbUrl);
		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPwd);

		dataSource.setPoolPreparedStatements(true);
		dataSource.setMaxOpenPreparedStatements(5);
		dataSource.setLogAbandoned(true);
		dataSource.setInitialSize(5);
		dataSource.setDefaultAutoCommit(Boolean.TRUE);

		return dataSource;
	}

//	@Override
//	public void configureViewResolvers(ViewResolverRegistry registry) {
//
//		WebMvcConfigurer.super.configureViewResolvers(registry);
//	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		messageSource.addBasenames("locale/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
