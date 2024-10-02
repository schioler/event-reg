package dk.schioler.event.base.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:/event-base-${event.env}.properties")
@ComponentScan("dk.schioler.event.base")
public class EventBaseConfiguration  {

	Logger logger = LoggerFactory.getLogger(getClass());

	public EventBaseConfiguration() {
		logger.trace("EventConfiguration:conztructor called");
		String property = System.getProperty("event.env");
		logger.debug("event.env=" + property);
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

	

}
