package dk.schioler.event.web.filter;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class LoggingFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public LoggingFilter() {
		logger.trace("filter:constructor");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
//		HttpSession session = req.getSession();
		String random = RandomStringUtils.random(6);
		logger.debug("doFilter: start ***************************************************" + random);

//		HttpServletResponse resp = (HttpServletResponse) response;

		String requestURI = req.getRequestURI();
		StringBuffer reqURL = req.getRequestURL();

		String contextPath = req.getContextPath();
		String servletPath = req.getServletPath();
		String pathInfo = req.getPathInfo();

		logger.debug("reqURI=" + requestURI);
		logger.debug("reqURL=" + reqURL);
		logger.debug("ContextPath=" + contextPath);
		logger.debug("ServletPath=" + servletPath);
		logger.debug("PathInfo=" + pathInfo);

		chain.doFilter(request, response);
//		logger.debug("doFilter:exit");
		logger.debug("doFilter: end ***************************************************" + random);

	}

}
