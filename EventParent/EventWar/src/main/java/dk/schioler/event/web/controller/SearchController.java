package dk.schioler.event.web.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.EventBaseException;
import dk.schioler.event.base.chart.ChartBuilder;
import dk.schioler.event.base.dao.EventSearchDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.WebLogin;
import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.Timeline;
import dk.schioler.shared.timeline.TimelineData;
import dk.schioler.shared.timeline.TimelineDataContainerFactory;
import dk.schioler.shared.timeline.TimelineException;
import dk.schioler.shared.timeline.TimelineSlot;
import dk.schioler.shared.timeline.impl.TimelineDataContainerFactoryEventImpl;
import dk.schioler.shared.timeline.impl.TimelineDataEventImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController extends AbstractController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected EventSearchDAO searchDAO;

	@Autowired
	protected EventTypeDAO eventTypeDAO;

	@Autowired
	protected EventTemplateDAO eventTemplateDAO;

	@Autowired
	protected EventTypeController eventTypeController;

	/*
	 * State: present: sesEventTypes, no selected.
	 */
	@RequestMapping(value = "/search-new.do", method = RequestMethod.GET)
	public String searchSetup(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
		logger.debug("GET /search-new.do, Requested ");
		HttpSession session = request.getSession();
		WebLogin attribute = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
		
		EventTypeCriteria criteria = new EventTypeCriteria();
		criteria.addLoginId(attribute.getLogin().getId());
		
		List<EventType> eventTypes = eventTypeDAO.retrieve(criteria, 0);
		logger.debug("eventTypes:" + eventTypes);

		Map<Integer, List<EventTemplate>> tmplMap = new TreeMap<Integer, List<EventTemplate>>();

		for (EventType eventType : eventTypes) {
			Integer id = eventType.getId();
			List<EventTemplate> list = eventTemplateDAO.getFromEventTypeId(id, attribute.getLogin().getId());
			logger.debug("found Tmpls on id =" + id + ", tmpls=" + list);
			tmplMap.put(id, list);
		}
		
//		eventTypeController.verifySessionAttributes(session);
		session.removeAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
		session.removeAttribute(SES_EVENT_TEMPLATEMAP_ON_TID);
		session.removeAttribute(SES_EVENT_TEMPLATES);
		session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);
		session.setAttribute(SES_EVENT_TYPES, eventTypes);
//		session.setAttribute("caller", "search");
//		String caller = reqParams.get("caller");
		return "redirect:search.jsp";
	}

	@RequestMapping(value = "/search-select-type.do", method = RequestMethod.POST)
	public String searchTypesPost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug("search-types.do: params=" + params);

		HttpSession session = request.getSession();
		WebLogin attribute = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
		
		String typeIdStr = (String) params.get(EVENT_TMPL_LIST_SHOW);
		logger.debug("Home Page Requested, attribute = " + typeIdStr);

		EventTypeCriteria typeCrit = new EventTypeCriteria();
		typeCrit.addLoginId(attribute.getLogin().getId());
		
//		List<EventType> eventTypes = eventTypeDAO.retrieve(typeCrit, 0);
		Integer eT = Integer.valueOf(typeIdStr);
		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT,attribute.getLogin().getId());

//		session.setAttribute(SES_EVENT_TYPES, eventTypes);
		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
		session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);

		return "redirect:search.jsp";
	}

	/**
	 * Perform the actual search using from-date, interval and count, to build a
	 * Timeline, where each timeslot has a length of "interval" {MINUTE, HOUR, DAY,
	 * MONTH}
	 * 
	 * @param params
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String doSearch(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {

		logger.debug("doSearch: RequestParams params = " + params);
		HttpSession session = request.getSession();

		WebLogin attribute = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
		
		String startDate = params.get("from-date");
		String interval = params.get("interval");
		String cStr = params.get("count");

		LocalDateTime startDateTime = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);
		TIMESLOT_LENGTH timeslotLength = Timeline.mapToTimeslotLength(interval);
		int countSlots = Integer.parseInt(cStr);
		TimelineDataContainerFactory tsDataContainerFactory = new TimelineDataContainerFactoryEventImpl();

		logger.debug("startd=" + startDateTime + ", interval=" + timeslotLength + ", count=" + countSlots);

		Timeline tl = new Timeline("Search", timeslotLength, startDateTime, countSlots, tsDataContainerFactory);

		logger.debug("startDate=" + tl.getStartDate() + ", endDate=" + tl.getEndDate() + ", tl.SLots="
				+ tl.getSlots().size());

		
		
		// grabbing templateIds from session
		List<Integer> templateIds = new ArrayList<Integer>();
		List<EventTemplate> eventTemplates = (List<EventTemplate>) session.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
		if (eventTemplates != null ) {
			for (EventTemplate eventTemplate : eventTemplates) {
				Integer id = eventTemplate.getId();
				if (id != null && !templateIds.contains(id)) {
					templateIds.add(id);
				}
			}
		}

		logger.debug("eventTemplateIds=" + templateIds);

		List<Event> events = searchDAO.searchEvents(tl.getStartDate(), tl.getEndDate(), templateIds, attribute.getLogin().getId());

		for (Event event : events) {
			TimelineData data = new TimelineDataEventImpl(event);
			tl.addData(data);
		}

		
		List<List<String>> rows = new ArrayList<List<String>>();
		List<String> row = null;

		/* Headerrow */
		row = new ArrayList<String>();
		DateTimeFormatter formatter = null;  
		if(TIMESLOT_LENGTH.MINUTE.equals(tl.getInterval())){
			DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
			row.add(tl.getStartDate().format(headerF));
			formatter = DateTimeFormatter.ofPattern("HH:mm");
		} else if(TIMESLOT_LENGTH.HOUR.equals(tl.getInterval())){
			DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
			row.add(tl.getStartDate().format(headerF));
			formatter = DateTimeFormatter.ofPattern("HH:mm");
		} else if(TIMESLOT_LENGTH.DAY.equals(tl.getInterval())) {
			DateTimeFormatter headerF = DateTimeFormatter.ofPattern("LLL YYYY");
			row.add(tl.getStartDate().format(headerF));
			formatter = DateTimeFormatter.ofPattern("dd-MM");
		} else if(TIMESLOT_LENGTH.MONTH.equals(tl.getInterval())){
			DateTimeFormatter headerF = DateTimeFormatter.ofPattern("YYYY");
			row.add(tl.getStartDate().format(headerF));
			formatter = DateTimeFormatter.ofPattern("LLL YY");
		}
		
		List<TimelineSlot> slots = tl.getSlots();
		for (TimelineSlot slot : slots) {
			row.add(formatter.format(slot.getStartOfSlot()));
		}

		rows.add(row);
		/*
		 * EO Headerrow
		 * 
		 */

		/*
		 * Category row: | category | slot.getCount().... as many as there are slots
		 */
		List<String> categories = tl.getCategories();
		for (String category : categories) {
			row = new ArrayList<String>();
			row.add(category);

			for (TimelineSlot slot : slots) {
				int count2 = slot.getTimeSlotDataContainer().getCount(category);
				row.add(String.valueOf(count2));
			}
			rows.add(row);
		}
		
		
		session.setAttribute(SES_SEARCH_RESULT, rows);
		session.setAttribute(SES_SEARCH_RESULT_START_DATE, tl.getStartDate().format(DateTimeFormatter.ISO_DATE_TIME));
		session.setAttribute(SES_SEARCH_RESULT_END_DATE, tl.getEndDate().format(DateTimeFormatter.ISO_DATE_TIME));
		
		session.removeAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);

		/*
		 * build ahart 
		 */
		
		ChartBuilder cb = new ChartBuilder();
		JFreeChart chart = cb.buildTimeSeriesChartFromTimeline(tl);
		String chartFileName[] = saveTmpChartfile(chart);
		session.setAttribute("chart", "diagram/" + chartFileName[0]);

		// adding to tmpfiles
		@SuppressWarnings("unchecked")
		List<String> chartsToBeDeleted = (List<String>) session.getServletContext()
				.getAttribute(CTX_CHARTS_TO_BE_DELETED);
		if (chartsToBeDeleted == null) {
			chartsToBeDeleted = new ArrayList<String>();
		}
		chartsToBeDeleted.add(chartFileName[1]);

		session.getServletContext().setAttribute(CTX_CHARTS_TO_BE_DELETED, chartsToBeDeleted);

		return "redirect:search-result.jsp";
	}

	private String[] saveTmpChartfile(JFreeChart chart) {
		String[] chartfilenameAndAbsPath = new String[2];
		String tmpDirKey = "java.io.tmpdir";
		String tmpDir = System.getProperty(tmpDirKey);

		String random = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvxyz1234567890");
		String chartFileName = "/chart-" + random + ".jpg";

		String fileName = tmpDir + chartFileName;
		String replace = StringUtils.replace(fileName, "\\", "/");

		logger.debug("file=" + replace);

		File f = new File(replace);

		try {
			ChartUtils.saveChartAsJPEG(f, chart, 800, 400);
		} catch (IOException e) {
			throw new TimelineException(e.getMessage(), e);
		}

//		ServletContext servletContext = session.getServletContext();
		chartfilenameAndAbsPath[0] = chartFileName;
		chartfilenameAndAbsPath[1] = f.getAbsolutePath();
		return chartfilenameAndAbsPath;
	}

	/**
	 * Adds selected eventTemplate to searchCriteria
	 * 
	 * @param params
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search-add-tmpl.do", method = RequestMethod.POST)
	public String searchAddTmpl(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("search-add-tmpl.do: RequestParams params = " + params);
		HttpSession session = request.getSession();
		WebLogin login = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
		
		int int1 = 0;
		String tmplIdStr = params.get("templateId");
		if (tmplIdStr != null) {
			try {
				int1 = Integer.parseInt(tmplIdStr);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		EventTemplate eTemplate = null;
		eTemplate = eventTemplateDAO.get(int1, login.getLogin().getId());

		
		List<EventTemplate> searchCrit = (List<EventTemplate>) session.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
		if (searchCrit == null) {
			searchCrit = new ArrayList<EventTemplate>();
		}
		if (!searchCrit.contains(eTemplate)) {
			searchCrit.add(eTemplate);
		}

		logger.debug("sesSearchCritTmplList:" + searchCrit);

		session.setAttribute(SES_SEARCH_CRITERIA_TMPL_LIST, searchCrit);

		return "redirect:search.jsp";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search-remove-template.do", method = RequestMethod.POST)
	public String removeTmplFromSearch(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("/search-remove-template.do: RequestParams params = " + params);

		Integer id = null;
		String tmplIdStr = params.get("eventTemplateId");
		if (tmplIdStr != null) {
			try {
				id = Integer.valueOf(tmplIdStr);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

		HttpSession session = request.getSession();
		List<EventTemplate> list = (List<EventTemplate>) session.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);

		if (list != null) {
			for (Iterator<EventTemplate> iterator = list.iterator(); iterator.hasNext();) {
				EventTemplate next = iterator.next();
				logger.debug("looking at:" + next);
				if (next.getId().equals(id)) {
					logger.debug("will remove");
					iterator.remove();
					break;
				}
			}

		} else {
			throw new EventBaseException("found null list of eventTemplates ");
		}

		logger.debug("sesSearchCrit:" + list);

		session.setAttribute(SES_SEARCH_CRITERIA_TMPL_LIST, list);

		return "redirect:search.jsp";
	}
}
