package dk.schioler.event.web.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController extends AbstractController {
//	Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	protected EventTypeDAO eventTypeDAO;
//
//	@Autowired
//	protected EventTemplateDAO eventTemplateDAO;

	public HomeController() {
		super();

	}

//	public static final String FAVOURITES_SHOW = "favourites-show.do";
	
//	public static final String HOME_JSP = "redirect:favourites.jsp";
//	public static final String HOME_FAVORITE_JSP = "redirect:favorites.jsp";
//	public static final String HOME_FAVORITE_JSP = "redirect:favorites.jsp";

//	@RequestMapping(value = FAVOURITES_SHOW, method = RequestMethod.GET)
//	public String homeShow(Locale locale, Model model, HttpServletRequest request) {
//		logger.debug(FAVOURITES_SHOW + ", GET,  Requested, locale = " + locale);
//		HttpSession session = request.getSession();
//		WebLogin wl = this.isValidLogin(session);
//		if (wl != null) {
//			Integer loginId = wl.getLogin().getId();
//
//			EventTemplateCriteria etCrit = new EventTemplateCriteria();
//			etCrit.setFavourite(true);
//			etCrit.addLoginId(loginId);
//			List<EventTemplate> eventTemplates = eventTemplateDAO.retrieve(etCrit, 0);
//
//			logger.debug("eventTemplates=" + eventTemplates);
//			List<EventType> eventTypes = new ArrayList<EventType>();
//			for (EventTemplate eventTemplate : eventTemplates) {
//				EventType eventType = eventTypeDAO.get(eventTemplate.getParentId(), wl.getLogin().getId());
//				eventType.addChild(eventTemplate);
//				eventTypes.add(eventType);
//			}
//
//			session.setAttribute(SES_FAVORITES, eventTypes);
//
//			return FAVOURITES_SHOW;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//	}

//	public static final String HOME_STORE_FAVORITE_SHOW = "home-store-favorite-show.do";
//	public static final String HOME_STORE_FAVORITE = "home-store-favorite.do";


//	@RequestMapping(value = HOME_STORE_FAVORITE_SHOW, method = RequestMethod.GET)
//	public String homeStoreFavoriteShow(@RequestParam Map<String, String> params, Locale locale, Model model,
//			HttpServletRequest request) {
//		logger.debug(HOME_STORE_FAVORITE_SHOW + ", GET,  Requested, locale = " + locale);
//		HttpSession session = request.getSession();
//
//		WebLogin wl = this.isValidLogin(session);
//		if (wl != null) {
//			Integer loginId = wl.getLogin().getId();
//			EventTemplateCriteria crit = new EventTemplateCriteria();
//			crit.addLoginId(loginId);
//			crit.setFavourite(true);
//			List<EventTemplate> list = eventTemplateDAO.retrieve(crit, 0);
//			session.setAttribute("", list);
//			return HOME_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//
//	}

	
	
//	@RequestMapping(value = HOME_STORE_FAVORITE, method = RequestMethod.POST)
//	public String homeStoreFavorite(@RequestParam Map<String, String> params, Locale locale, Model model,
//			HttpServletRequest request) {
//		logger.debug(HOME_STORE_FAVORITE + ", GET,  Requested, locale = " + locale);
//		HttpSession session = request.getSession();
//
//		WebLogin wl = this.isValidLogin(session);
//		if (wl != null) {
//			Integer loginId = wl.getLogin().getId();
//			EventTemplateCriteria crit = new EventTemplateCriteria();
//			crit.addLoginId(loginId);
//			crit.setFavourite(true);
//			List<EventTemplate> list = eventTemplateDAO.retrieve(crit, 0);
//			session.setAttribute("", list);
//			return HOME_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//
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