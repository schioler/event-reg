package dk.schioler.event.web.controller;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebEntityUtil;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import dk.schioler.event.web.entity.search.WebEntityEventSearch;
import dk.schioler.event.web.entity.search.impl.WebEntityEventSearchImpl;
import dk.schioler.shared.security.entity.Login;
import dk.schioler.shared.timeline.Data;
import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.Timeline;
import dk.schioler.shared.timeline.impl.DataImpl;
import dk.schioler.shared.timeline.impl.SimpleTimeline;
import dk.schioler.shared.timeline.impl.TimelineImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventSearchController extends AbstractController implements  WebEntitySymbolsShared {

   public static final String EVENT_SEARCH_SHOW_DO = "eventSearchShow.do";
   public static final String EVENT_SEARCH_DO = "eventSearch.do";
   public static final String SES_EVENT_SEARCH_RESULT ="sesEventSearchResult";
   public static final String EVENT_SEARCH_JSP ="event-search.jsp";
   public static final String SES_EVENT_SEARCH_INPUT ="sesEventSearchInput";
   
   
   @Autowired
   protected EventTemplateDAO eventTemplateDAO;

   @Autowired
   protected EventTypeDAO eventTypeDAO;

   @Autowired
   protected EventSearchDAO searchDAO;

   @Autowired
   protected EventTypeController eventTypeController;

//   @RequestMapping(value = SECURE + SLASH + EVENT_SEARCH_SHOW_DO, method = RequestMethod.GET)
//   public String eventSearchShow(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + EVENT_SEARCH_SHOW_DO + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//
//         WebEntityEventSearch wees = new WebEntityEventSearch(params);
//
////         WebEntityEventSearchInput input = (WebEntityEventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_EVENT_SEARCH_INPUT);
//
////         TimelineInputOld timelineInput = input.getTimelineInput();
////         if (StringUtils.isNotBlank(startDate)) {
////            timelineInput.setTimelineStartStr(startDate);
////         }
//
////         if (StringUtils.isNotBlank(interval)) {
////   
////         TIMESLOT_LENGTH length = TIMESLOT_LENGTH.getTimeSlotFromString(interval);
////            if (length != null) {
////               timelineInput.setSelectedInterval(length);
////            }
////         }
//
////         if (StringUtils.isNotBlank(count)) {
////            timelineInput.setSelectedCount(Integer.parseInt(count));
////         }
//
////         session.setAttribute(SES_SEARCH_INPUT, wees);
//
//         String caller = params.get(CALLER);
//
////         if (StringUtils.isNotBlank(caller)) {
////            return REDIRECT + SLASH + SECURE + SLASH + caller;
////         } else {
////            return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
////         }
//         return REDIRECT + SLASH + SECURE + SLASH + EVENT_SEARCH_JSP;
//      } else {
//         return REDIRECT + SLASH;
//
//      }
//   }
//
//   @RequestMapping(value = SECURE + SLASH + EVENT_SEARCH_DO, method = RequestMethod.POST)
//   public String setInputParamsDateTimeIntervalCount(@RequestParam Map<String, String> params, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + EVENT_SEARCH_DO + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         WebEntityEventSearch search = new WebEntityEventSearch(params);
//
//         logger.debug("" + search);
////
////         TimelineInputOld timelineInput = input.getTimelineInput();
////         if (StringUtils.isNotBlank(startDate)) {
////            timelineInput.setTimelineStartStr(startDate);
////         }
////
////         if (StringUtils.isNotBlank(interval)) {
////            TIMESLOT_LENGTH length = TIMESLOT_LENGTH.getTimeSlotFromString(interval);
////            if (length != null) {
////               timelineInput.setSelectedInterval(length);
////            }
////         }
////
////         if (StringUtils.isNotBlank(count)) {
////            timelineInput.setSelectedCount(Integer.parseInt(count));
////         }
//         String caller = params.get(CALLER);
//         if (StringUtils.isNotBlank(caller)) {
//            return REDIRECT + SLASH + SECURE + SLASH + caller;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + HOME_JSP;
//         }
//      } else {
//         return REDIRECT + SLASH;
//
//      }
//   }

// secure/event.search.show.do
   @RequestMapping(value = SECURE + SLASH + EVENT_SEARCH_SHOW_DO, method = RequestMethod.GET)
   public String eventSearchShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_SEARCH_SHOW_DO + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Login owner = wl.getOwner();
         WebCommonAPI.statusReset(session);
         WebCommonAPI.removeObjectFromSession(session, SES_FAVORITES);
         WebCommonAPI.removeObjectFromSession(session, SES_EVENT_SEARCH_RESULT);

         WebEntityEventSearch searchInput = new WebEntityEventSearchImpl();

         searchInput.addLoginId(owner.getId());

//       prepareForSelectEventType(session, searchInput, params);
//       prepareForSelectEventTemplate(session, searchInput, params);

         WebCommonAPI.addObjectToSession(session, SES_EVENT_SEARCH_INPUT, searchInput);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_SEARCH_JSP;

      } else {
         return SLASH;

      }
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_SEARCH_DO, method = RequestMethod.POST)
   public String eventSearchDo(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_SEARCH_DO + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {

         WebEntityEventSearchImpl eventSearchInput = new WebEntityEventSearchImpl(params);

//       String createdStart = evEentSearchInput.getCreatedStart();
//       String createdEnd = eventSearchInput.getCreatedEnd();

         List<Integer> eventTemplateIds = eventSearchInput.getEventTemplateIds();
         List<Integer> ids = eventSearchInput.getIds();
         List<Integer> loginIds = eventSearchInput.getLoginIds();

         EventCriteria ec = new EventCriteria();
         
         ec.setLoginIds(loginIds);
         ec.setIds(ids);
         ec.setEventTemplateIds(eventTemplateIds);

         String timelineStartDate = eventSearchInput.getTimelineStartDate();
         LocalDateTime localDateTimeFrom = WebEntityUtil.getLocalDateTimeFrom(timelineStartDate);

         int timelineCountIntervals = eventSearchInput.getTimelineCountIntervals();
        

         String timelineInterval = eventSearchInput.getTimelineInterval();
         TIMESLOT_LENGTH interval = TIMESLOT_LENGTH.getTimeSlotFromString(timelineInterval);

         Timeline tl = new TimelineImpl("eventSearch", localDateTimeFrom, interval, timelineCountIntervals);

         LocalDateTime tlStartDateTime = tl.getTLStartDateTime();
         LocalDateTime tlEndDateTime = tl.getTLEndDateTime();
         ec.setEventTSInterval(tlStartDateTime, tlEndDateTime);

         if (StringUtils.isNotBlank(eventSearchInput.getName())) {
            ec.setName(eventSearchInput.getName());
         }

         if (StringUtils.isNotBlank(eventSearchInput.getShortName())) {
            ec.setShortName(eventSearchInput.getShortName());
         }

         logger.debug("getEvents, criteria=" + ec);

         List<Event> events = eventDAO.retrieve(ec, 0);

         List<String> chosenCategories = new ArrayList<String>();
         for (Event event : events) {
            Data data = new DataImpl(event.getName(), event.getEventTS(), new BigDecimal(1));
            tl.addTimelineData(data);
            if (!chosenCategories.contains(event.getName())) {
               chosenCategories.add(event.getName());
            }
         }

         SimpleTimeline simpleTimeline = tl.getSimpleTimeline(chosenCategories);
         WebCommonAPI.addObjectToSession(session, "sesTimeline", simpleTimeline);

         WebCommonAPI.statusReset(session);

         String msg = "Event Search executed";

         WebCommonAPI.statusAdd(session, msg);

         return REDIRECT + SLASH + SECURE + SLASH + "01-event-search-result-as-timeline.jsp";

      } else {
         return SLASH;
      }

   }

}
