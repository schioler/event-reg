package dk.schioler.event.web.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class ToJspFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ToJspFilter() {
		logger.trace("filter:constructor");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.debug("doFilter");

//		Enumeration<String> attributeNames = request.getAttributeNames();
//		while (attributeNames.hasMoreElements()) {
//			String string = (String) attributeNames.nextElement();
//			
//			logger.debug(string);
//		}
//		Enumeration<String> parameterNames = request.getParameterNames();
//		while (parameterNames.hasMoreElements()) {
//			String string = (String) parameterNames.nextElement();
//			logger.debug(string);
//			
//		}

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if (session == null) {
			logger.info("Found no valid session - will forward to /");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
			requestDispatcher.forward(request, response);
		} else {

			String servletPath = req.getServletPath();
			logger.debug(servletPath);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/views" + servletPath);
			requestDispatcher.forward(request, response);
			logger.debug("will fwd req");
		}

	}

}
