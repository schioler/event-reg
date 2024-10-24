package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.encrypt.Encrypter;
import dk.schioler.event.web.WebLogin;
import dk.schioler.secure.dao.LoginDAO;
import dk.schioler.secure.dao.PasswordDAO;
import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.entity.impl.PasswordImpl;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.MappingMatch;

@Controller
public class TestController extends WebTokensAbstract {

	Logger logger = LoggerFactory.getLogger(getClass());

	public TestController() {
		super();

	}

	public static final String REQ_1 = "public/test.do";
	public static final String REQ_2 = "./public/test.do";
	public static final String REQ_3 = "/public/test.do";

	public static final String REQ_4 = "public/test-re.do";
	public static final String REQ_5 = "./public/test-re.do";
	public static final String REQ_6 = "/public/test.do";

	public static final String RES_1 = "redirect:public/test.jsp";
	public static final String RES_2 = "redirect:test.jsp";

	public static final String RES_3 = "public/test.jsp";
	public static final String RES_4 = "test.jsp";
	public static final String RES_5 = "test.do";
	public static final String RES_6 = "test-re.do";

	public void handleReq(String req, Map<String, String> params, HttpServletRequest request) {
		logger.debug(req + ": params=" + params);

		StringBuffer requestURL = request.getRequestURL();
		logger.debug("reqURL=" + requestURL);

		String requestURI = request.getRequestURI();
		logger.debug("reqURI=" + requestURI);

		ServletContext servletContext = request.getServletContext();
		logger.debug("servletCtx=" + servletContext);

		String servletPath = request.getServletPath();
		logger.debug("<reqURL=" + requestURL);

		String pathInfo = request.getPathInfo();
		String contextPath = request.getContextPath();
		HttpServletMapping httpServletMapping = request.getHttpServletMapping();
		String matchValue = httpServletMapping.getMatchValue();

//		DispatcherType dispatcherType = request.getDispatcherType();
//		Enumeration<String> headerNames = request.getHeaderNames();
//		MappingMatch mappingMatch = httpServletMapping.getMappingMatch();

		logger.debug("");
	}

	@RequestMapping(value = REQ_1, method = RequestMethod.GET)
	public String userAuthenticate1(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {

		String retVal = handleReq(REQ_1, params, request);

		return retVal;
	}

	@RequestMapping(value = REQ_2, method = RequestMethod.GET)
	public String userAuthenticate2(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(REQ_2 + ": params=" + params);

		String retVal = RES_1;

		return retVal;
	}
}