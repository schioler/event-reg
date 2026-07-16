package dk.schioler.event.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.entity.WebEntityEvent;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import dk.schioler.event.web.entity.impl.WebEntityEventImpl;
import dk.schioler.event.web.usecase.data.EventData;
import dk.schioler.event.web.usecase.data.impl.EventDataImpl;
import dk.schioler.shared.security.entity.Login;
//import dk.schioler.event.web.entity.WebLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventController extends AbstractController implements WebEntitySymbolsShared {

   public final static String EVENT_EVENT_TYPE_SELECT_UPDATE = "event-event-type-select-update.do";
   public static final String EVENT_EVENT_TEMPLATE_SELECT_UPDATE = "event-event-template-select-update.do´";
   public final static String EVENT_UNITS_SELECT_UPDATE = "event-units-select-update.do";
   public static final String EVENT_CREATE_SHOW = "event-create-show.do";
   public static final String EVENT_UPDATE_SHOW = "event-update-show.do";
   public static final String EVENT_DELETE_SHOW = "event-delete-show.do";

   public static final String EVENT_SHOW = "event-show.do";
   public static final String EVENT_JSP = "10-event.jsp";
   public static final String EVENT_CREATE = "event-create.do";
   public static final String EVENT_CREATE_JSP = "10-event-create.jsp";

   // *******************************************

   public static final String EVENT_UPDATE = "event-update.do";
   public static final String EVENT_UPDATE_JSP = "10-event-update.jsp";

   // *******************************************

   public static final String EVENT_DELETE = "event-delete.do";
   public static final String EVENT_DELETE_JSP = "10-event-delete.jsp";

//   public static final String SES_EVENT_CREATE_DATA = "sesEventCreateData";

   @Autowired
   private EventTypeController eventTypeController;

   @Autowired
   private EventTemplateController eventTemplateController;

   /**
    * Prepare the session with data to start createEvent process
    * i.e. 
    * - Starting up the data object used in this UseCase
    *    General data:   
    * - UNITs known in the system
    * - EventTypes in system that will be the parent of all events,
    * - Specific, initialized, WebEntityEvent object.
    *      
    * @param params
    * @param model
    * @param request
    * @return
    *
    */

   @RequestMapping(value = SECURE + SLASH + EVENT_CREATE_SHOW, method = RequestMethod.GET)
   public String eventCreateShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_CREATE_SHOW + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Login owner = wl.getOwner();
         Integer oId = owner.getId();

         // container, holding data and user selections
         EventData eventData = new EventDataImpl();

         // An object, that eventually will become a row in the database
         WebEntityEvent webEvent = new WebEntityEventImpl();
         // initialize
         webEvent.setToken(owner.getToken());
         webEvent.setLoginId(owner.getId().toString());

         webEvent.grabRequestValues(params);

         eventData.setWebEntity(webEvent);

         // Collecting for show in select/dropDown
         List<EventType> eventTypes = eventTypeController.getRelevantEventTypes(Collections.singletonList(oId));
         eventData.setEventTypes(eventTypes);

         eventData.setUnits(getSelectableUnitsAsString());

         WebCommonAPI.addObjectToSession(session, SES_EVENT_DATA, eventData);

         WebCommonAPI.removeObjectFromSession(session, SES_FAVORITES);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_CREATE_JSP;

      } else {
         return SLASH;

      }
   }

   /*
    * 
    * /* <a href="event-search-show.do"><fmt:message key="menu.event.search"/> </a>
    * <a href="event-new-show.do"><fmt:message key="menu.event.new"/> </a>
    */

   @RequestMapping(value = SECURE + SLASH + EVENT_CREATE, method = RequestMethod.POST)
   public String eventCreate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_CREATE + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Login owner = wl.getOwner();
         logger.debug("owner=" + owner + ", id=" + owner.getId());

         EventData createEventData = (EventData) WebCommonAPI.getObjectFromSession(session, SES_EVENT_DATA);
         WebEntityEvent webEntityEvent = (WebEntityEvent) createEventData.getWebEntity();

         Event event = new Event();
         event = (Event) webEntityEvent.getThisAsBaseEntity(event);
         logger.debug("Event built:" + event);

         Event inserted = eventDAO.insert(event);

         String msg = "Event, saved: " + inserted.getName() + ", @" + inserted.getCreated();

         WebCommonAPI.statusAdd(session, msg);

//         
         return REDIRECT + SLASH + SECURE + SLASH + HOME_JSP;
//         }

      } else {
         return SLASH;

      }
   }

//   

   @RequestMapping(value = SECURE + SLASH + EVENT_UNITS_SELECT_UPDATE, method = RequestMethod.POST)
   public String eventCreateUpdateUnits(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_UNITS_SELECT_UPDATE + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Login owner = wl.getOwner();
         logger.debug("owner=" + owner + ", id=" + owner.getId());

         EventData eventData = (EventData) WebCommonAPI.getObjectFromSession(session, SES_EVENT_DATA);
         WebEntityEvent webEntityEvent = (WebEntityEvent) eventData.getWebEntity();

         logger.debug("event =" + webEntityEvent);
         webEntityEvent.grabRequestValues(params);

         WebCommonAPI.addObjectToSession(session, SES_EVENT_DATA, eventData);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_CREATE_JSP;

      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_EVENT_TYPE_SELECT_UPDATE, method = RequestMethod.POST)
   public String eventTypeSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_EVENT_TYPE_SELECT_UPDATE + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Login owner = wl.getOwner();
         logger.debug("owner=" + owner + ", id=" + owner.getId());

         EventData ceData = (EventData) WebCommonAPI.getObjectFromSession(session, SES_EVENT_DATA);
         
         WebEntityEvent event = (WebEntityEvent) ceData.getWebEntity();
//         logger.debug("event =" + ceData);
         event.grabRequestValues(params);

         String selected = params.get(REQ_EVENT_TYPE_ID_SELECT);
         
         ceData.setSelectedEventTypeId(Integer.parseInt(selected));
         
         logger.debug("uCeventInstance=" + ceData);

         List<EventTemplate> eventTemplates = eventTemplateController.getRelatedEventTemplates(owner.getId(),
               ceData.getSelectedEventTypeId());

         ceData.setSelectableEventTemplates(eventTemplates);

         WebCommonAPI.addObjectToSession(session, SES_EVENT_DATA, ceData);
         return REDIRECT + SLASH + SECURE + SLASH + EVENT_CREATE_JSP;

      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_EVENT_TEMPLATE_SELECT_UPDATE, method = RequestMethod.POST)
   public String eventCreateUpdateTemplate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_EVENT_TEMPLATE_SELECT_UPDATE + ": RequestParams params = " + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Login owner = wl.getOwner();
         logger.debug("owner=" + owner + ", id=" + owner.getId());

         WebEntityEvent webEvent = new WebEntityEventImpl();
         webEvent.grabRequestValues(params);

         logger.debug("created params=" + webEvent);
         WebCommonAPI.addObjectToSession(session, SES_EVENT_DATA, webEvent);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_CREATE_JSP;

      } else {
         return SLASH;
      }

   }

}
