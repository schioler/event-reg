package dk.schioler.event.web.filter;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebLogin;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class UserFilter implements Filter {

   private Logger logger = LoggerFactory.getLogger(getClass());

   public UserFilter() {
      logger.trace("userfilter:constructor");
   }

   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      logger.trace("doFilter; enter");
      
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;

      HttpSession session = httpServletRequest.getSession();
      
      String requestURI = httpServletRequest.getRequestURI();
      String ctxPath = httpServletRequest.getContextPath();
      logger.debug("doFilter: ctxPath=" + ctxPath + ", requestURI=" + requestURI);
      
      boolean securedURL = WebCommonAPI.isSecuredURL(requestURI);
      if (securedURL) {
         WebLogin webLogin = WebCommonAPI.getAuthenticatedLogin(session);
         if (webLogin != null && webLogin.getAuthenticateTime() != null) {
            logger.debug("authenticated login found -  will pass on request");
            chain.doFilter(request, response);
         } else {
            logger.debug("found no login in session");
            logger.debug("secured-area-resource requested - will prompt user to login");
            String contextPath = httpServletRequest.getContextPath();            
            logger.debug("UserFilter: will send redirect to " + contextPath);
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect(contextPath);
         }         
      } else {
         logger.debug("un-secured area resource requested - will pass on the request");
         chain.doFilter(request, response);        
      }
      
      logger.trace("doFilter:exit");

   }
}
