package dk.schioler.buffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Buffer {

//	public final static String SES_SEARCH_CRITERIA_TMPL_LIST = "sesSearchCriteriaTmplList";
//	public final static String SES_SEARCH_RESULT = "sesSearchResult";
//	public final static String SES_SEARCH_RESULT_START_DATE = "sesSearchResultStartDate";
//	public final static String SES_SEARCH_RESULT_END_DATE = "sesSearchResultEndDate";

	Logger logger = LoggerFactory.getLogger(getClass());


	/*
	 * State: present: sesSearchCriteris. IN show: a simple NO-DATA Searchriteria is
	 * established and stored in session.
	 */
//	public static final String SES_SEARCH_CRITERIA = "sesSearchCriteria";

	// Prepare/cleanup session and show "search.jsp", where user can add criteria
	// (TimelineStart)
//	public static final String SEARCH_NEW_SHOW = "/search-new-show.do";

	// shows thd current search without "resettings" search state.
	// This is based upon the existance of SES_SEARCH_CRITERIA
//	public static final String SEARCH_CURRENT_SHOW = "/search-active-show.do";

	// If needed, show page, where it's is possible to addTemplates to the current
	// search
	public static final String SEARCH_ADD_TEMPLATES_SHOW = "/search-add-templates-show.do";
	public static final String SEARCH_ADD_TEMPLATE = "/search-add-template.do";

	// perform the actual search
	// redirects to search_result_jsp
	public static final String SEARCH = "/search.do";

	public static final String SEARCH_JSP = "redirect:/search.jsp";
	public static final String SEARCH_TEMPLATES_JSP = "redirect:/search-add_templates.jsp";
	public static final String SEARCH_RESULT_JSP = "redirect:/search-result.jsp";

//	@RequestMapping(value = SEARCH_NEW_SHOW, method = RequestMethod.GET)
//	public String searchShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
//		logger.debug(SEARCH_NEW_SHOW + "GET, Requested ");
//		HttpSession session = request.getSession();
//		WebLogin wl = isValidLogin(session);
//		if (wl != null) {
//			SearchCriteria searchCriteria = new SearchCriteria();
//			searchCriteria.setLoginId(wl.getLogin().getId());
//
//			session.removeAttribute(SES_SEARCH_CRITERIA);
//			return SEARCH_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//	}
//
//	@RequestMapping(value = SEARCH_CURRENT_SHOW, method = RequestMethod.GET)
//	public String searchActiveShow(@RequestParam Map<String, String> reqParams, Model model,
//			HttpServletRequest request) {
//		logger.debug(SEARCH_CURRENT_SHOW + "GET, Requested ");
//		HttpSession session = request.getSession();
//		WebLogin wl = isValidLogin(session);
//		if (wl != null) {
//			SearchCriteria searchCriteria = new SearchCriteria();
//			searchCriteria.setLoginId(wl.getLogin().getId());
//
//			return SEARCH_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//	}
//
//	@RequestMapping(value = SEARCH_ADD_TEMPLATES_SHOW, method = RequestMethod.POST)
//	public String searchChooseTemplate(@RequestParam Map<String, String> params, Model model,
//			HttpServletRequest request) {
//		logger.debug(SEARCH_ADD_TEMPLATES_SHOW + ": params=" + params);
//
//		HttpSession session = request.getSession();
//		WebLogin wl = isValidLogin(session);
//		if (wl != null) {
//
//			return SEARCH_ADD_TEMPLATES_SHOW;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//
////			WebLogin attribute = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//		//
////			String typeIdStr = (String) params.get(EVENT_TMPL_LIST_SHOW);
////			logger.debug("Home Page Requested, attribute = " + typeIdStr);
//		//
////			EventTypeCriteria typeCrit = new EventTypeCriteria();
////			typeCrit.addLoginId(attribute.getLogin().getId());
//		//
//////			List<EventType> eventTypes = eventTypeDAO.retrieve(typeCrit, 0);
////			Integer eT = Integer.valueOf(typeIdStr);
////			List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT, attribute.getLogin().getId());
//		//
//////			session.setAttribute(SES_EVENT_TYPES, eventTypes);
////			session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
////			session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//		//
////			return "redirect:search.jsp";
//	}
//
//	@RequestMapping(value = SEARCH_ADD_TEMPLATE, method = RequestMethod.POST)
//	public String searchAddTemplate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug(SEARCH_ADD_TEMPLATE + ": params=" + params);
//
//		HttpSession session = request.getSession();
//		WebLogin wl = isValidLogin(session);
//		if (wl != null) {
//
//			return SEARCH_TEMPLATES_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//
////			WebLogin attribute = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//		//
////			String typeIdStr = (String) params.get(EVENT_TMPL_LIST_SHOW);
////			logger.debug("Home Page Requested, attribute = " + typeIdStr);
//		//
////			EventTypeCriteria typeCrit = new EventTypeCriteria();
////			typeCrit.addLoginId(attribute.getLogin().getId());
//		//
//////			List<EventType> eventTypes = eventTypeDAO.retrieve(typeCrit, 0);
////			Integer eT = Integer.valueOf(typeIdStr);
////			List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT, attribute.getLogin().getId());
//		//
//////			session.setAttribute(SES_EVENT_TYPES, eventTypes);
////			session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
////			session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//		//
////			return "redirect:search.jsp";
//	}
//
//	@RequestMapping(value = SEARCH_CURRENT_SHOW, method = RequestMethod.POST)
//	public String searchActiveShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug(SEARCH_CURRENT_SHOW + ": params=" + params);
//
//		HttpSession session = request.getSession();
//		WebLogin wl = isValidLogin(session);
//		if (wl != null) {
//
//			return SEARCH_TEMPLATES_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//
////			WebLogin attribute = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//		//
////			String typeIdStr = (String) params.get(EVENT_TMPL_LIST_SHOW);
////			logger.debug("Home Page Requested, attribute = " + typeIdStr);
//		//
////			EventTypeCriteria typeCrit = new EventTypeCriteria();
////			typeCrit.addLoginId(attribute.getLogin().getId());
//		//
//////			List<EventType> eventTypes = eventTypeDAO.retrieve(typeCrit, 0);
////			Integer eT = Integer.valueOf(typeIdStr);
////			List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT, attribute.getLogin().getId());
//		//
//////			session.setAttribute(SES_EVENT_TYPES, eventTypes);
////			session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
////			session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//		//
////			return "redirect:search.jsp";
//	}
//
//	/**
//	 * Perform the actual search using from-date, interval and count, to build a
//	 * Timeline, where each timeslot has a length of "interval" {MINUTE, HOUR, DAY,
//	 * MONTH}
//	 * 
//	 * @param params
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value = SEARCH, method = RequestMethod.POST)
//	public String doSearch(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//
//		logger.debug(SEARCH + ": RequestParams params = " + params);
//		HttpSession session = request.getSession();
//
//		WebLogin validLogin = this.isValidLogin(session);
//		if (validLogin != null) {
//
//			String startDate = params.get("from-date");
//			String interval = params.get("interval");
//			String cStr = params.get("count");
//
//			LocalDateTime startDateTime = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);
//			TIMESLOT_LENGTH timeslotLength = Timeline.mapToTimeslotLength(interval);
//			int countSlots = Integer.parseInt(cStr);
//			TimelineDataContainerFactory tsDataContainerFactory = new TimelineDataContainerFactoryEventImpl();
//
//			logger.debug("startd=" + startDateTime + ", interval=" + timeslotLength + ", count=" + countSlots);
//
//			Timeline tl = new Timeline("Search", timeslotLength, startDateTime, countSlots, tsDataContainerFactory);
//
//			logger.debug("startDate=" + tl.getStartDate() + ", endDate=" + tl.getEndDate() + ", tl.SLots="
//					+ tl.getSlots().size());
//
//			// grabbing templateIds from session
//			List<Integer> templateIds = new ArrayList<Integer>();
//			List<EventTemplate> eventTemplates = (List<EventTemplate>) session
//					.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
//			if (eventTemplates != null) {
//				for (EventTemplate eventTemplate : eventTemplates) {
//					Integer id = eventTemplate.getId();
//					if (id != null && !templateIds.contains(id)) {
//						templateIds.add(id);
//					}
//				}
//			}
//
//			logger.debug("eventTemplateIds=" + templateIds);
//
//			List<Event> events = searchDAO.searchEvents(tl.getStartDate(), tl.getEndDate(), templateIds,
//					validLogin.getLogin().getId());
//
//			for (Event event : events) {
//				TimelineData data = new TimelineDataEventImpl(event);
//				tl.addData(data);
//			}
//
//			List<List<String>> rows = new ArrayList<List<String>>();
//			List<String> row = null;
//
//			/* Headerrow */
//			row = new ArrayList<String>();
//			DateTimeFormatter formatter = null;
//			if (TIMESLOT_LENGTH.MINUTE.equals(tl.getInterval())) {
//				DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
//				row.add(tl.getStartDate().format(headerF));
//				formatter = DateTimeFormatter.ofPattern("HH:mm");
//			} else if (TIMESLOT_LENGTH.HOUR.equals(tl.getInterval())) {
//				DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
//				row.add(tl.getStartDate().format(headerF));
//				formatter = DateTimeFormatter.ofPattern("HH:mm");
//			} else if (TIMESLOT_LENGTH.DAY.equals(tl.getInterval())) {
//				DateTimeFormatter headerF = DateTimeFormatter.ofPattern("LLL YYYY");
//				row.add(tl.getStartDate().format(headerF));
//				formatter = DateTimeFormatter.ofPattern("dd-MM");
//			} else if (TIMESLOT_LENGTH.MONTH.equals(tl.getInterval())) {
//				DateTimeFormatter headerF = DateTimeFormatter.ofPattern("YYYY");
//				row.add(tl.getStartDate().format(headerF));
//				formatter = DateTimeFormatter.ofPattern("LLL YY");
//			}
//
//			List<TimelineSlot> slots = tl.getSlots();
//			for (TimelineSlot slot : slots) {
//				row.add(formatter.format(slot.getStartOfSlot()));
//			}
//
//			rows.add(row);
//			/*
//			 * EO Headerrow
//			 * 
//			 */
//
//			/*
//			 * Category row: | category | slot.getCount().... as many as there are slots
//			 */
//			List<String> categories = tl.getCategories();
//			for (String category : categories) {
//				row = new ArrayList<String>();
//				row.add(category);
//
//				for (TimelineSlot slot : slots) {
//					int count2 = slot.getTimeSlotDataContainer().getCount(category);
//					row.add(String.valueOf(count2));
//				}
//				rows.add(row);
//			}
//
//			session.setAttribute(SES_SEARCH_RESULT, rows);
//			session.setAttribute(SES_SEARCH_RESULT_START_DATE,
//					tl.getStartDate().format(DateTimeFormatter.ISO_DATE_TIME));
//			session.setAttribute(SES_SEARCH_RESULT_END_DATE, tl.getEndDate().format(DateTimeFormatter.ISO_DATE_TIME));
//
//			session.removeAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
//
//			/*
//			 * build ahart
//			 */
//
//			ChartBuilder cb = new ChartBuilder();
//			JFreeChart chart = cb.buildTimeSeriesChartFromTimeline(tl);
//			String chartFileName[] = saveTmpChartfile(chart);
//			session.setAttribute("chart", "diagram/" + chartFileName[0]);
//
//			// adding to tmpfiles
//			@SuppressWarnings("unchecked")
//			List<String> chartsToBeDeleted = (List<String>) session.getServletContext()
//					.getAttribute(CTX_CHARTS_TO_BE_DELETED);
//			if (chartsToBeDeleted == null) {
//				chartsToBeDeleted = new ArrayList<String>();
//			}
//			chartsToBeDeleted.add(chartFileName[1]);
//
//			session.getServletContext().setAttribute(CTX_CHARTS_TO_BE_DELETED, chartsToBeDeleted);
//
//			return SEARCH_RESULT_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//	}
//
//	private String[] saveTmpChartfile(JFreeChart chart) {
//		String[] chartfilenameAndAbsPath = new String[2];
//		String tmpDirKey = "java.io.tmpdir";
//		String tmpDir = System.getProperty(tmpDirKey);
//
//		String random = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvxyz1234567890");
//		String chartFileName = "/chart-" + random + ".jpg";
//
//		String fileName = tmpDir + chartFileName;
//		String replace = StringUtils.replace(fileName, "\\", "/");
//
//		logger.debug("file=" + replace);
//
//		File f = new File(replace);
//
//		try {
//			ChartUtils.saveChartAsJPEG(f, chart, 800, 400);
//		} catch (IOException e) {
//			throw new TimelineException(e.getMessage(), e);
//		}
//
////			ServletContext servletContext = session.getServletContext();
//		chartfilenameAndAbsPath[0] = chartFileName;
//		chartfilenameAndAbsPath[1] = f.getAbsolutePath();
//		return chartfilenameAndAbsPath;
//	}
//
//	/**
//	 * Adds selected eventTemplate to searchCriteria
//	 * 
//	 * @param params
//	 * @param model
//	 * @param request
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = SEARCH_ADD_TEMPLATE, method = RequestMethod.POST)
//	public String searchAddTmpl(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug("search-add-tmpl.do: RequestParams params = " + params);
//
//		HttpSession session = request.getSession();
//		WebLogin login = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//
//		int int1 = 0;
//		String tmplIdStr = params.get("templateId");
//		if (tmplIdStr != null) {
//			try {
//				int1 = Integer.parseInt(tmplIdStr);
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
//
//		EventTemplate eTemplate = null;
//		eTemplate = eventTemplateDAO.get(int1, login.getLogin().getId());
//
//		List<EventTemplate> searchCrit = (List<EventTemplate>) session.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
//		if (searchCrit == null) {
//			searchCrit = new ArrayList<EventTemplate>();
//		}
//		if (!searchCrit.contains(eTemplate)) {
//			searchCrit.add(eTemplate);
//		}
//
//		logger.debug("sesSearchCritTmplList:" + searchCrit);
//
//		session.setAttribute(SES_SEARCH_CRITERIA_TMPL_LIST, searchCrit);
//
//		return "redirect:search.jsp";
//	}
//
//	@SuppressWarnings("unchecked")
//	@RequestMapping(value = "/search-remove-template.do", method = RequestMethod.POST)
//	public String removeTmplFromSearch(@RequestParam Map<String, String> params, Model model,
//			HttpServletRequest request) {
//		logger.debug("/search-remove-template.do: RequestParams params = " + params);
//
//		Integer id = null;
//		String tmplIdStr = params.get("eventTemplateId");
//		if (tmplIdStr != null) {
//			try {
//				id = Integer.valueOf(tmplIdStr);
//			} catch (Exception e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
//
//		HttpSession session = request.getSession();
//		List<EventTemplate> list = (List<EventTemplate>) session.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
//
//		if (list != null) {
//			for (Iterator<EventTemplate> iterator = list.iterator(); iterator.hasNext();) {
//				EventTemplate next = iterator.next();
//				logger.debug("looking at:" + next);
//				if (next.getId().equals(id)) {
//					logger.debug("will remove");
//					iterator.remove();
//					break;
//				}
//			}
//
//		} else {
//			throw new EventBaseException("found null list of eventTemplates ");
//		}
//
//		logger.debug("sesSearchCrit:" + list);
//
//		session.setAttribute(SES_SEARCH_CRITERIA_TMPL_LIST, list);
//
//		return "redirect:search.jsp";
//	}
//
}
