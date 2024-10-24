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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HTMLFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public HTMLFilter() {
		logger.trace("filter:constructor");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		
		String contextPath = req.getContextPath();
		String redirectUrl = contextPath + "/public/login.jsp";
		logger.debug("HTMLFilter: will send redirect to "+ redirectUrl);

		resp.sendRedirect(redirectUrl);
		logger.debug("doFilter:exit");
	}

}
