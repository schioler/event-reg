package dk.schioler.event.web.filter;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebCommonAPIImpl;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserFilter  implements Filter {

	private Logger logger = LoggerFactory.getLogger(getClass());

   public UserFilter() {
      logger.trace("useDfilter:constructor");
   }
   
   private WebCommonAPI webCommonAPI = new WebCommonAPIImpl();

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;

      HttpSession session = httpServletRequest.getSession();

      String requestURI = httpServletRequest.getRequestURI();
      String ctxPath = httpServletRequest.getContextPath();
      logger.debug("doFilter: ctxPath=" + ctxPath + ", requestURI=" + requestURI);

      boolean publicURL = webCommonAPI.isPublicURL(requestURI);

      // Does request require a authenticated user?u
      if (!publicURL) {         
         boolean isAuthEnticated = webCommonAPI.isLoginAuthenticated(session);
         if (isAuthEnticated) {
            logger.debug("authenticated login found -  will allow access");
            chain.doFilter(request, response);
         } else {
            logger.debug("found no login in session - will prompt user to login");
            String contextPath = httpServletRequest.getContextPath();
            logger.debug("ctxPath=" + contextPath);

            String redirectUrl = contextPath + "/public/login.jsp";
            if (contextPath.endsWith("/public")) {
               redirectUrl = contextPath + "/login.jsp";
            } else {
               redirectUrl = contextPath + "/public/login.jsp";
            }
            logger.debug("UserFilter: will send redirect to " + redirectUrl);

            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(redirectUrl);
            logger.debug("doFilter:exit");

         }
      } else {
         logger.debug("will fwd to original url, as it is a public path");
         chain.doFilter(request, response);
      }

   }
}
