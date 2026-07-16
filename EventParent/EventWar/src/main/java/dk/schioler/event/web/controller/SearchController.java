package dk.schioler.event.web.controller;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.EventSearchDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.criteria.EventCriteria;
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.EventWebException;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import dk.schioler.event.web.entity.search.impl.WebEntityEventSearchImpl;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.timeline.Data;
import dk.schioler.shared.timeline.Slot;
import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.Timeline;
import dk.schioler.shared.timeline.impl.DataImpl;
import dk.schioler.shared.timeline.impl.TimelineImpl;
import dk.schioler.shared.timeline.impl.TimelineUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SearchController extends AbstractController implements WebEntitySymbolsShared {

   @Autowired
   protected EventTemplateDAO eventTemplateDAO;

   @Autowired
   protected EventTypeDAO eventTypeDAO;

//   public final static String SES_SEARCH_EVENT_TEMPLATES = "sesSearchEventTemplates";
//   public final static String SES_SEARCH_CRITERIA_TMPL_LIST = "sesSearchCriteriaTmplList";
   public final static String SES_SEARCH_INPUT = "sesSearchInput";
   public final static String SES_SEARCH_RESULT = "sesSearchResult";
   public final static String SES_SEARCH_RESULT_START_DATE = "sesSearchResultStartDate";
   public final static String SES_SEARCH_RESULT_END_DATE = "sesSearchResultEndDate";
   private List<TIMESLOT_LENGTH> timeslots = Arrays.asList(TIMESLOT_LENGTH.values());
//   Logger logger = LoggerFactory.getLogger(getClass());

   @Autowired
   protected EventSearchDAO searchDAO;

//   @Autowired
//   protected EventTypeController eventTypeController;

   /*
    * State: present: sesSearchCriteris. IN show: a simple NO-DATA Searchriteria is
    * established and stored in session.
    */
   public static final String SES_SEARCH_CRITERIA = "sesSearchCriteria";

   // Prepare/cleanup session and show "search.jsp", where user can add criteria
   // (TimelineStart)

   public static final String SEARCH_NEW_SHOW = "search.new.show.do";
   public static final String SEARCH_CURRENT_SHOW = "search-current-show.do";

   public static final String SEARCH_EVENT_TYPE_SELECT = "search-event-type-select.do";

   // If needed, show page, where it's is possible to addTemplates to the current
   // search
   public static final String SEARCH_ADD_TEMPLATES_SHOW = "search-add-templates-show.do";
   public static final String SEARCH_QUEUE_ADD_TEMPLATE = "search-queue-add-template.do";
   public static final String SEARCH_REMOVE_TEMPLATE = "search-remove-template.do";

   public static final String SEARCH = "search.do";

   public static final String SEARCH_JSP = "search.jsp";

   public static final String SEARCH_TEMPLATES_JSP = "search-templates.jsp";

   public static final String SEARCH_RESULT_JSP = "search-result.jsp";

//   public static final String sesSelectedEventTypeId  sesSelectedEventTypeId
//   public static final String SES_SEARCH_RESULT = "sesSearchTestesult";
//   public static final String SEARCH_SHOW = "/search-test-show.do";
//   public static final String SEARCH_TEST_JSP = "redirect:/search-test.jsp";

   @RequestMapping(value = SECURE + SLASH + SEARCH_NEW_SHOW, method = RequestMethod.GET)
   public String searchTestShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + SEARCH_NEW_SHOW + "GET, Requested ");
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null && wl.isAuthenticated()) {
         Login owner = wl.getOwner();

         // EventTypes
         EventTypeCriteria etc = new EventTypeCriteria();
         etc.addLoginId(owner.getId());

         List<EventType> relevantEventTypes = eventTypeDAO.retrieve(etc, 0);
//         WebCommonAPI.addObjectToSession(session, SES_EVENT_TYPES, relevantEventTypes);
         WebEntityEventSearchImpl input = new WebEntityEventSearchImpl();
//         input.setEventTypes(relevantEventTypes);
//         input.setEventTypeIdSelected(null);

         WebCommonAPI.addObjectToSession(session, SES_SEARCH_INPUT, input);

//         WebCommonAPI.removeObjectFromSession(session, SES_SEARCH_EVENT_TEMPLATES);
//         WebCommonAPI.removeObjectFromSession(session, SES_EVENT_TYPE_ID_SELECTED);
         WebCommonAPI.removeObjectFromSession(session, SES_SEARCH_RESULT);

         return REDIRECT + SLASH + SECURE + SLASH + SEARCH_JSP;
      } else {
         return REDIRECT + SLASH;
      }
   }

//   @RequestMapping(value = SEARCH_NEW_SHOW, method = RequestMethod.GET)
//   public String searchNewShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
//      logger.debug(SEARCH_NEW_SHOW + "GET, Requested ");
//      HttpSession session = request.getSession();
//      WebLogin wl = getAuthenticatedLogin(session);
//      if (wl != null) {
//         Login owner = wl.getOwner();
//         
//         
//         
////         searchCriteria.setLoginId(owner.getId());
//         
//         session.setAttribute(SES_SEARCH_CRITERIA, searchCriteria);
//         return SEARCH_JSP;
//      } else {
//         return PUBLIC_LOGIN_JSP;
//      }
//   }

//   @RequestMapping(value = SEARCH_CURRENT_SHOW, method = RequestMethod.GET)
//   public String searchCurrentShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
//      logger.debug(SEARCH_CURRENT_SHOW + "GET, Requested ");
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         EventSearchCriteria searchCriteria = (EventSearchCriteria) session.getAttribute(SES_SEARCH_CRITERIA);
//         if (searchCriteria == null) {
//            searchCriteria = new EventSearchCriteria();
//            searchCriteria.setLoginId(wl.getOwner().getId());
//         } else {
//            // do no changes
//         }
//
//         session.setAttribute(SES_SEARCH_CRITERIA, searchCriteria);
//         return SEARCH_JSP;
//      } else {
//         return REDIRECT + SLASH;
//      }
//   }
//
   @RequestMapping(value = SEARCH_ADD_TEMPLATES_SHOW, method = RequestMethod.POST)
   public String searchAddTemplatesShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SEARCH_ADD_TEMPLATES_SHOW + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {

         EventTypeCriteria crit = new EventTypeCriteria();
         crit.addLoginId(wl.getOwner().getId());
         List<EventType> list = eventTypeDAO.retrieve(crit, 0);

//         session.setAttribute(SES_EVENT_TYPES, list);
         return SEARCH_TEMPLATES_JSP;
      } else {
         return REDIRECT + SLASH;
      }
   }

//   @RequestMapping(value = SECURE + SLASH + SEARCH_EVENT_TYPE_SELECT, method = RequestMethod.POST)
//   public String searchEventTypeSelect(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug(SECURE + SLASH + SEARCH_EVENT_TYPE_SELECT + ": params=" + params);
//
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         EventSearchInput eis = (EventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_SEARCH_INPUT);
//         if (eis == null) {
//            eis = new EventSearchInput();
//
//         }
//         this.setInputParams(params, eis);
//
//         Integer loginId = wl.getOwner().getId();
//         String eventTypeIdStr = params.get("req-event-type-id");
//         logger.debug(SECURE + SLASH + SEARCH_EVENT_TYPE_SELECT + ". eventTypeId=" + eventTypeIdStr + ", loginId=" + loginId);
//
//         Integer eventTypeId = Integer.valueOf(eventTypeIdStr);
//         List<EventTemplate> sesEventTemplates = eventTemplateDAO.getFromEventTypeId(eventTypeId, loginId);
//         eis.setEventTypeSelectId(eventTypeId);
//         eis.setEventTemplates(sesEventTemplates);
//
//         session.setAttribute(SES_SEARCH_INPUT, eis);
//         logger.debug("searchInput=" + eis);
////         session.setAttribute(SES_EVENT_TYPE_ID_SELECTED, eventTypeId   );
////         session.setAttribute(SES_EVENT_TEMPLATES, sesEventTemplates);
////         return REDIRECT + SLASH + SECURE + SLASH + SEARCH_TEMPLATES_JSP;
//         return REDIRECT + SLASH + SECURE + SLASH + SEARCH_JSP;
//      } else {
//         return REDIRECT + SLASH;
//      }
//
//   }

//   @RequestMapping(value = SECURE + SLASH + SEARCH_QUEUE_ADD_TEMPLATE, method = RequestMethod.POST)
//   public String searchAddTemplate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug(SECURE + SLASH + SEARCH_QUEUE_ADD_TEMPLATE + ": params=" + params);
//
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         EventSearchInput searchInput = (EventSearchInput) session.getAttribute(SES_SEARCH_INPUT);
//
////         EventSearchCriteria search = (EventSearchCriteria) session.getAttribute(SES_SEARCH_CRITERIA);
//         if (searchInput == null) {
//            searchInput = new EventSearchInput();
//            searchInput.setLoginId(wl.getOwner().getId());
//         }
//
//         this.setInputParams(params, searchInput);
//
//         String etNo = params.get("et-no");
////         logger.debug("etNo="+etNo);
//         String tmplId = "template-id" + etNo;
//         String templateId = params.get(tmplId);
//         logger.debug("templateId=" + templateId);
//         Integer id = Integer.valueOf(templateId);
//         Integer loginId = wl.getLogin().getId();
//
//         EventTemplate eventTemplate = eventTemplateDAO.get(id, loginId);
//
//         searchInput.getSelectedTemplates().add(eventTemplate);
//
//         session.setAttribute(SES_SEARCH_INPUT, searchInput);
//
//         return REDIRECT + SLASH + SECURE + SLASH + SEARCH_JSP;
//      } else {
//         return REDIRECT + SLASH;
//      }
//
//   }

//   @RequestMapping(value = SECURE + SLASH + SEARCH_REMOVE_TEMPLATE, method = RequestMethod.POST)
//   public String searchRemoveTemplate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug(SECURE + SLASH + SEARCH_REMOVE_TEMPLATE + ": params=" + params);
//
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         String no = params.get("tmpl-no");
//         String string = params.get("event-template-id" + no);
//         Integer id = Integer.valueOf(string);
//
//         EventSearchInput search = (EventSearchInput) session.getAttribute(SES_SEARCH_INPUT);
//         setInputParams(params, search);
//
//         List<EventTemplate> eventTemplates = search.getSelectedTemplates();
//
//         for (Iterator<EventTemplate> iterator = eventTemplates.iterator(); iterator.hasNext();) {
//            EventTemplate eventTemplate = (EventTemplate) iterator.next();
//            if (eventTemplate.getId().equals(id)) {
//               iterator.remove();
//               break;
//            }
//         }
//
//         session.setAttribute(SES_SEARCH_INPUT, search);
//         return REDIRECT + SLASH + SECURE + SLASH + SEARCH_JSP;
//      } else {
//         return REDIRECT + SLASH;
//      }
//
//   }

   public static final String REQ_KEY_SEARCH_FROM_DATE = "from-date";
   public static final String REQ_KEY_SEARCH_FROM_TIME = "from-time";
   public static final String REQ_KEY_SEARCH_INTERVAL = "interval";
   public static final String REQ_KEY_SEARCH_COUNT = "count";

//   private void setInputParams(Map<String, String> params, WebEventSearchInput input) {
//      String fromDate = params.get(REQ_KEY_SEARCH_FROM_DATE);
//      String fromTime = params.get(REQ_KEY_SEARCH_FROM_TIME);
//      String interval = params.get(REQ_KEY_SEARCH_INTERVAL);
//      String count = params.get(REQ_KEY_SEARCH_COUNT);
//
//      logger.debug("fromDate=" + fromDate + ", fromTime=" + fromTime + ", interval=" + interval + ", count=" + count);

//      if (StringUtils.isNotBlank(fromDate)) {
//         input.setStartDate(fromDate);
//      }
//      if (StringUtils.isNotBlank(fromTime)) {
//         input.setStartTime(fromTime);
//      }
//      if (StringUtils.isNotBlank(interval)) {
//
//         TIMESLOT_LENGTH length = TIMESLOT_LENGTH.getTimeSlotFromString(interval);
//         if (length != null) {
//            input.setSelectedInterval(length);
//         }
//      }
//
//      if (StringUtils.isNotBlank(count)) {
//         input.setSelectedCount(Integer.parseInt(count));
//      }
//   }

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
   @RequestMapping(value = SECURE + SLASH + SEARCH, method = RequestMethod.POST)
   public String doSearch(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {

      logger.debug(SECURE + SLASH + SEARCH + ": RequestParams params = " + params);
      HttpSession session = request.getSession();

      WebLogin validLogin = WebCommonAPI.getAuthenticatedLogin(session);
      if (validLogin != null) {

         String startDate = params.get("from-date");
         String startTime = params.get("from-time");
         String interval = params.get("interval");
         String cStr = params.get("count");
         logger.debug("startd=" + startDate + ", startTime=" + startTime + ", interval=" + interval + ", count=" + cStr);

         List<Integer> selectedEventTemplateIds = new ArrayList<Integer>();
         Set<String> keySet = params.keySet();
         for (String key : keySet) {
            logger.debug("key=" + key);

            if (StringUtils.containsIgnoreCase(key, "selected-event-template-id")) {
               String val = params.get(key);
//               logger.debug("val=" + val);
               if (StringUtils.isNotBlank(val)) {
                  Integer etId = Integer.parseInt(val);
                  selectedEventTemplateIds.add(etId);
               }
            }
         }

         logger.debug("selected-event-template-id's=" + selectedEventTemplateIds);
         TIMESLOT_LENGTH timeslotLength = TimelineUtil.mapToTimeslotLength(interval);
         String dateTimeString = null;
         LocalDateTime startDateTime = null;
         if (StringUtils.isNotBlank(startTime)) {
            dateTimeString = startDate + "T" + startTime;
            logger.debug("dateTimeStr=" + dateTimeString);

         } else {
            if (TIMESLOT_LENGTH.MONTH.equals(timeslotLength) || TIMESLOT_LENGTH.DAY.equals(timeslotLength)) {
               startTime = "00:01";
               dateTimeString = startDate + "T" + startTime;

            } else {
               throw new EventWebException("You must specify a time component, when your interval is HOUR or MINUTE");
            }
         }

         startDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_DATE_TIME);

         int countSlots = Integer.parseInt(cStr);

         Timeline tl = new TimelineImpl("Event Search", startDateTime, timeslotLength, countSlots);
         logger.debug("startDate=" + tl.getTLStartDateTime() + ", endDate=" + tl.getTLEndDateTime() + ", tl.SLots=" + tl.getSlots().size());
         logger.trace("" + tl);

//         Begin creating searchCriteria
         EventCriteria eCrit = new EventCriteria();

         eCrit.setEventTSInterval(tl.getTLStartDateTime(), tl.getTLEndDateTime());
         eCrit.addLoginId(validLogin.getOwner().getId());

         for (Integer tmplId : selectedEventTemplateIds) {
            eCrit.addEventTemplateId(tmplId);
         }
         List<Event> events = eventDAO.retrieve(eCrit, 0);

         if (events != null) {

            for (Event event : events) {
               Data data = new DataImpl(event.getName(), event.getEventTS(), new BigDecimal(1));
               tl.addTimelineData(data);
            }

            List<List<String>> rows = new ArrayList<List<String>>();
            List<String> row = null;

            /* Headerrow */
            row = new ArrayList<String>();
            DateTimeFormatter formatter = null;
            if (TIMESLOT_LENGTH.MINUTE.equals(tl.getTimeslotLength())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
               row.add(tl.getTLStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("HH:mm");
            } else if (TIMESLOT_LENGTH.HOUR.equals(tl.getTimeslotLength())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("dd LLL YYYY");
               row.add(tl.getTLStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("HH:mm");
            } else if (TIMESLOT_LENGTH.DAY.equals(tl.getTimeslotLength())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("LLL YYYY");
               row.add(tl.getTLStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("dd-MM");
            } else if (TIMESLOT_LENGTH.MONTH.equals(tl.getTimeslotLength())) {
               DateTimeFormatter headerF = DateTimeFormatter.ofPattern("YYYY");
               row.add(tl.getTLStartDateTime().format(headerF));
               formatter = DateTimeFormatter.ofPattern("LLL YY");
            }

            List<Slot> slots = tl.getSlots();
            for (Slot slot : slots) {
               row.add(formatter.format(slot.getSlotStartDateTime()));
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

               for (Slot slot : slots) {
                  int count2 = slot.getCount(category);
                  row.add(String.valueOf(count2));
               }
               rows.add(row);
            }

            session.setAttribute(SES_SEARCH_RESULT, rows);
            session.setAttribute(SES_SEARCH_RESULT_START_DATE, tl.getTLStartDateTime().format(DateTimeFormatter.ISO_DATE_TIME));
            session.setAttribute(SES_SEARCH_RESULT_END_DATE, tl.getTLEndDateTime().format(DateTimeFormatter.ISO_DATE_TIME));

//            session.removeAttribute(SES_SEARCH_EVENT_TEM );

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

         return REDIRECT + SLASH + SECURE + SLASH + SEARCH_RESULT_JSP;
      } else {
         return REDIRECT + SLASH;
      }
   }

//   private String[] saveTmpChartfile(JFreeChart chart) {
//      String[] chartfilenameAndAbsPath = new String[2];
//      String tmpDirKey = "java.io.tmpdir";
//      String tmpDir = System.getProperty(tmpDirKey);
//
//      String random = RandomStringUtils.random(5, "abcdefghijklmnopqrstuvxyz1234567890");
//      String chartFileName = "/chart-" + random + ".jpg";
//
//      String fileName = tmpDir + chartFileName;
//      String replace = StringUtils.replace(fileName, "\\", "/");
//
//      logger.debug("file=" + replace);
//
//      File f = new File(replace);
//
//      try {
//         ChartUtils.saveChartAsJPEG(f, chart, 800, 400);
//      } catch (IOException e) {
//         throw new TimelineException(e.getMessage(), e);
//      }
//
////		ServletContext servletContext = session.getServletContext();
//      chartfilenameAndAbsPath[0] = chartFileName;
//      chartfilenameAndAbsPath[1] = f.getAbsolutePath();
//      return chartfilenameAndAbsPath;
//   }

}
