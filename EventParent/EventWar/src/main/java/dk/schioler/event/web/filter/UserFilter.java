package dk.schioler.event.web.filter;

import java.io.IOException;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dk.schioler.event.web.WebLogin;
import dk.schioler.event.web.controller.WebTokens;
import dk.schioler.secure.entity.impl.LoginImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserFilter implements Filter, WebTokens {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public UserFilter() {
		logger.trace("filter:constructor");
	}

	private boolean isPublicURL(String requestURI) {
		boolean retval = false;
		logger.debug("isPublic:" + requestURI);
		if (requestURI.indexOf("public") >= 0) {
			retval = true;
		} else if (requestURI.indexOf("colss.css") >= 0) {
			retval = true;
		} else if (requestURI.indexOf("styles.css") >= 0) {
			retval = true;
		} else if (requestURI.indexOf("forgot-password.do") >= 0) {
			retval = true;
		} else if (requestURI.indexOf("show-forgot-password.do") >= 0) {
			retval = true;
		} else if (requestURI.indexOf("user-authenticate.do") >= 0) {
			retval = true;
		}

		logger.debug("isPublic: returning " + retval);
		return retval;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		HttpSession session = httpServletRequest.getSession();

		String requestURI = httpServletRequest.getRequestURI();
		String ctxPath = httpServletRequest.getContextPath();
		logger.debug("doUserFilter: ctxPath=" + ctxPath + ", requestURI=" + requestURI);

		@SuppressWarnings("unused")
		WebLogin login = (WebLogin) session.getAttribute(KEY_LOGIN);

		if (login != null) {
			logger.debug("login found=" + login + " will allow access");
			chain.doFilter(request, response);
		} else {
			if (isPublicURL(requestURI)) {
				logger.debug("will fwd to original url, as it is a public path");
				chain.doFilter(request, response);
			} else {
				String contextPath = httpServletRequest.getContextPath();
				String redirectUrl = contextPath + "/public/login.jsp";
				logger.debug("HTMLFilter: will send redirect to " + redirectUrl);

				HttpServletResponse resp = (HttpServletResponse) response;
				resp.sendRedirect(redirectUrl);
				logger.debug("doFilter:exit");
			}

			logger.debug("found no login in session - will prompt user to login");
		}

	}
}
