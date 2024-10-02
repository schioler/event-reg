package dk.schioler.event.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Controller
public class HomeController extends WebTokensAbstract {
	Logger logger = LoggerFactory.getLogger(getClass());

//	@Autowired
//	protected EventTypeDAO eventTypeDAO;
//
//	@Autowired
//	protected EventTemplateDAO eventTemplateDAO;

	public HomeController() {
		super();

	}

//	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
//	public String homeGet(Locale locale, Model model, HttpServletRequest request) {
//		logger.debug("/home.do, GET,  Requested, locale = " + locale);
//
//		
//		List<EventType> eventTypes = eventTypeDAO.lookup();
//
//		logger.debug("eventTypes="+eventTypes);
//		
//		HttpSession session = request.getSession();
//		session.setAttribute(SES_EVENT_TYPES, eventTypes);
//		session.removeAttribute(SES_EVENT_TEMPLATES);
//		session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);
//		
//		return "redirect:home.jsp";
//	}
	
//	@RequestMapping(value = "/home-type-select.do", method = RequestMethod.POST)
//	public String selectEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug("home-type-select.do: params=" + params);
//		
//		String typeIdStr = (String) params.get(REQ_EVENT_TYPE_ID);
//		logger.debug("eventTypeId = " + typeIdStr);
//		
//
//		Integer eT = Integer.valueOf(typeIdStr);
//
//		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT);
//		logger.debug("In typeId=" + eT + ", Found these templates " + eventTemplates);
//
//		HttpSession session = request.getSession();
//		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
//		return "redirect:home.jsp";
//	}

	
//	@RequestMapping(value = "/home.do", method = RequestMethod.POST)
//	public String homePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug("home.do, POST: reqParams=" + params);
//
//		String typeIdStr = (String) params.get(REQ_EVENT_TYPE_ID);
//		logger.debug("Home Page Requested, attribute = " + typeIdStr);
//
//		List<EventType> eventTypes = eventTypeDAO.lookup();
//		Integer eT = Integer.valueOf(typeIdStr);
//		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT);
//
//		HttpSession session = request.getSession();
//		session.setAttribute(SES_EVENT_TYPES, eventTypes);
//		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
//		session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//		return "redirect:home.jsp";
//	}

}