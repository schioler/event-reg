package dk.schioler.event.web.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventSearchDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.criteria.EventCriteria;
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.WebLogin;
import dk.schioler.event.web.entity.EventSearchCriteria;
import dk.schioler.shared.timeline.TimelineException;
import dk.schioler.shared.timeline.api.entity.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.api.entity.Timeline;
import dk.schioler.shared.timeline.api.entity.TimelineData;
import dk.schioler.shared.timeline.api.entity.TimelineSlot;
import dk.schioler.shared.timeline.chart.ChartBuilder;
import dk.schioler.shared.timeline.imp.entity.TimelineDataImpl;
import dk.schioler.shared.timeline.imp.entity.TimelineImpl;
import dk.schioler.shared.timeline.imp.entity.TimelineUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController extends AbstractController {

   public final static String SES_SEARCH_EVENT_TEMPLATES = "sesSearchEventTemplates";
   public final static String SES_SEARCH_CRITERIA_TMPL_LIST = "sesSearchCriteriaTmplList";
   public final static String SES_SEARCH_RESULT = "sesSearchResult";
   public final static String SES_SEARCH_RESULT_START_DATE = "sesSearchResultStartDate";
   public final static String SES_SEARCH_RESULT_END_DATE = "sesSearchResultEndDate";

//   Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   protected EventSearchDAO searchDAO;

//   @Autowired
//   protected EventTypeDAO eventTypeDAO;
//
//   @Autowired
//   protected EventDAO eventDAO;
//
//   @Autowired
//   protected EventTemplateDAO eventTemplateDAO;

   @Autowired
   protected EventTypeController eventTypeController;

   /*
    * State: present: sesSearchCriteris. IN show: a simple NO-DATA Searchriteria is
    * established and stored in session.
    */
   public static final String SES_SEARCH_CRITERIA = "sesSearchCriteria";

   // Prepare/cleanup session and show "search.jsp", where user can add criteria
   // (TimelineStart)
   public static final String SEARCH_NEW_SHOW = "/search-new-show.do";
   public static final String SEARCH_CURRENT_SHOW = "/search-current-show.do";

   // If needed, show page, where it's is possible to addTemplates to the current
   // search
   public static final String SEARCH_ADD_TEMPLATES_SHOW = "/search-add-templates-show.do";
   public static final String SEARCH_ADD_TEMPLATE = "/search-add-template.do";
   public static final String SEARCH_REMOVE_TEMPLATE = "/search-remove-template.do";
   public static final String SEARCH_EVENT_TYPE_SELECT = "/search-event-type-select.do";

   public static final String SEARCH = "/search.do";

   public static final String SEARCH_JSP = "redirect:/search.jsp";

   public static final String SEARCH_TEMPLATES_JSP = "redirect:/search-templates.jsp";

   public static final String SEARCH_RESULT_JSP = "redirect:/search-result.jsp";

   @RequestMapping(value = SEARCH_NEW_SHOW, method = RequestMethod.GET)
   public String searchNewShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_NEW_SHOW + "GET, Requested ");
      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {
         EventSearchCriteria searchCriteria = new EventSearchCriteria();
         searchCriteria.setLoginId(wl.getOwner().getId());

         session.setAttribute(SES_SEARCH_CRITERIA, searchCriteria);
         return SEARCH_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

   @RequestMapping(value = SEARCH_CURRENT_SHOW, method = RequestMethod.GET)
   public String searchCurrentShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_CURRENT_SHOW + "GET, Requested ");
      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {
         EventSearchCriteria searchCriteria = (EventSearchCriteria) session.getAttribute(SES_SEARCH_CRITERIA);
         if (searchCriteria == null) {
            searchCriteria = new EventSearchCriteria();
            searchCriteria.setLoginId(wl.getOwner().getId());
         } else {
            // do no changes
         }

         session.setAttribute(SES_SEARCH_CRITERIA, searchCriteria);
         return SEARCH_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

   @RequestMapping(value = SEARCH_ADD_TEMPLATES_SHOW, method = RequestMethod.POST)
   public String searchAddTemplatesShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_ADD_TEMPLATES_SHOW + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {

         EventTypeCriteria crit = new EventTypeCriteria();
         crit.addLoginId(wl.getOwner().getId());
         List<EventType> list = eventTypeDAO.retrieve(crit, 0);

         session.setAttribute(SES_EVENT_TYPES, list);
         return SEARCH_TEMPLATES_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

   @RequestMapping(value = SEARCH_EVENT_TYPE_SELECT, method = RequestMethod.POST)
   public String searchEventTypeSelect(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_EVENT_TYPE_SELECT + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {
         String eventTypeIdStr = params.get("reqEventTypeId");
         Integer eventTypeId = Integer.valueOf(eventTypeIdStr);
         Integer loginId = wl.getOwner().getId();
         logger.debug(SEARCH_EVENT_TYPE_SELECT + ". eventTypeId=" + eventTypeId + ", loginId=" + loginId);
         List<EventTemplate> sesEventTemplates = eventTemplateDAO.getFromEventTypeId(eventTypeId, loginId);
         logger.debug("eventTemplates:" + sesEventTemplates);
         session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eventTypeId);
         session.setAttribute(SES_EVENT_TEMPLATES, sesEventTemplates);
         return SEARCH_TEMPLATES_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }

   }

   @RequestMapping(value = SEARCH_ADD_TEMPLATE, method = RequestMethod.POST)
   public String searchAddTemplate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_ADD_TEMPLATE + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {
         EventSearchCriteria search = (EventSearchCriteria) session.getAttribute(SES_SEARCH_CRITERIA);
         if (search == null) {
            search = new EventSearchCriteria();
            search.setLoginId(wl.getOwner().getId());
         }

         String string = params.get("id");
         Integer id = Integer.valueOf(string);
         Integer loginId = wl.getLogin().getId();

         EventTemplate eventTemplate = eventTemplateDAO.get(id, loginId);

         search.add(eventTemplate);

         session.setAttribute(SES_SEARCH_EVENT_TEMPLATES, search.getEventTemplates());
         session.setAttribute(SES_SEARCH_CRITERIA, search);

         return SEARCH_TEMPLATES_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }

   }

   @RequestMapping(value = SEARCH_REMOVE_TEMPLATE, method = RequestMethod.POST)
   public String searchRemoveTemplate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_REMOVE_TEMPLATE + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {
         String string = params.get("id");
         Integer id = Integer.valueOf(string);

//			List<EventTemplate> eventTemplates = (List<EventTemplate>) session.getAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);
         EventSearchCriteria search = (EventSearchCriteria) session.getAttribute(SES_SEARCH_CRITERIA);

         List<EventTemplate> eventTemplates = search.getEventTemplates();

         for (Iterator<EventTemplate> iterator = eventTemplates.iterator(); iterator.hasNext();) {
            EventTemplate eventTemplate = (EventTemplate) iterator.next();
            if (eventTemplate.getId().equals(id)) {
               iterator.remove();
               break;
            }
         }

         session.setAttribute(SES_SEARCH_CRITERIA, search);
         return SEARCH_TEMPLATES_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }

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
   @RequestMapping(value = SEARCH, method = RequestMethod.POST)
   public String doSearch(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {

      logger.debug(SEARCH + ": RequestParams params = " + params);
      HttpSession session = request.getSession();

      WebLogin validLogin = this.getAuthenticatedLogin(session);
      if (validLogin != null) {

         String startDate = params.get("from-date");
         String interval = params.get("interval");
         String cStr = params.get("count");

         LocalDateTime startDateTime = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_DATE_TIME);
         TIMESLOT_LENGTH timeslotLength = TimelineUtil.mapToTimeslotLength(interval);
         int countSlots = Integer.parseInt(cStr);

         logger.debug("startd=" + startDateTime + ", interval=" + interval + ", count=" + countSlots);

         Timeline tl = new TimelineImpl("Search", startDateTime, timeslotLength, countSlots);
         tl.initializeTimelineSlots();

         logger.debug("startDate=" + tl.getStartDateTime() + ", endDate=" + tl.getEndDateTime() + ", tl.SLots=" + tl.getSlots().size());

         // grabbing templateIds from session
         List<Integer> templateIds = new ArrayList<Integer>();
         Set<Entry<String, String>> entrySet = params.entrySet();
         for (Entry<String, String> entry : entrySet) {
            if (entry.getKey().startsWith("id")) {
               templateIds.add(Integer.valueOf(entry.getValue()));
            }
         }

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

         logger.debug("eventTemplateIds=" + templateIds);
         EventCriteria eCrit = new EventCriteria();

//			List<Event> events = searchDAO.searchEvents(tl.getStartDate(), tl.getEndDate(), templateIds,
//					validLogin.getLogin().getId());

         eCrit.setCreatedStartTime(tl.getStartDateTime());
         eCrit.setCreatedEndTime(tl.getEndDateTime());
         eCrit.addLoginId(validLogin.getOwner().getId());
         eCrit.getEventTemplateIds().addAll(templateIds);
         List<Event> events = eventDAO.retrieve(eCrit, 0);

         if (events != null) {

            for (Event event : events) {
               TimelineData data = new TimelineDataImpl(event.getParentString(), event.getEventTS(), new BigDecimal(1));
               tl.addTimelineData(data);
            }

            List<List<String>> rows = new ArrayList<List<String>>();
            List<String> row = null;

            /* Headerrow */
            row = new ArrayList<String>();
            DateTimeFormatter formatter = null;
            if (TIMESLOT_LENGTH.MINUTE.equals(tl.getInterval())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
               row.add(tl.getStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("HH:mm");
            } else if (TIMESLOT_LENGTH.HOUR.equals(tl.getInterval())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
               row.add(tl.getStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("HH:mm");
            } else if (TIMESLOT_LENGTH.DAY.equals(tl.getInterval())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("LLL YYYY");
               row.add(tl.getStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("dd-MM");
            } else if (TIMESLOT_LENGTH.MONTH.equals(tl.getInterval())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("YYYY");
               row.add(tl.getStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("LLL YY");
            }

            List<TimelineSlot> slots = tl.getSlots();
            for (TimelineSlot slot : slots) {
               row.add(formatter.format(slot.getStartDateTime()));
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
                  int count2 = slot.getCount(category);
                  row.add(String.valueOf(count2));
               }
               rows.add(row);
            }

            session.setAttribute(SES_SEARCH_RESULT, rows);
            session.setAttribute(SES_SEARCH_RESULT_START_DATE, tl.getStartDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
            session.setAttribute(SES_SEARCH_RESULT_END_DATE, tl.getEndDateTime().format(DateTimeFormatter.ISO_DATE_TIME));

            session.removeAttribute(SES_SEARCH_CRITERIA_TMPL_LIST);

            /*
             * build ahart
             */

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
         }

         return SEARCH_RESULT_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
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

}
