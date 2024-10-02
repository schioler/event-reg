package dk.schioler.event.web.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dk.schioler.event.base.entity.Login;
import dk.schioler.event.web.controller.WebTokens;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class UserFilter implements Filter, WebTokens {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	public UserFilter() {
		logger.trace("filter:constructor");
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("doFilter");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		HttpSession session = httpServletRequest.getSession();
		@SuppressWarnings("unused")
		Login login = (Login) session.getAttribute(KEY_LOGIN);
		
		if (login == null) {
			
		}
		
		
	}

}
