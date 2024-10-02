package dk.schioler.event.web.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class UserFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	public UserFilter() {
		logger.trace("filter:constructor");
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("doFilter");

	}

}
