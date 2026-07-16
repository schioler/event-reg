package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebEntityUtil;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.controller.exception.EventWebControllerException;
import dk.schioler.event.web.entity.WebEntityEventType;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import dk.schioler.event.web.entity.impl.WebEntityEventTypeImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * 
 * EventType has these main views - EventTypeList - EventType
 * 
 * You can 1: show create -> from List, data removed from session, entity.jsp,
 * user enters data, save.do, show updated list 2: show update -> from list,
 * req: entityId, data retrieved and entered in session, entity.jsp, user
 * updates data, save.do, show updated list 3: delete -> from list, 
 * 
 * 
 */
@Controller
public class EventTypeController extends AbstractController implements WebEntitySymbolsShared {

   public static final String EVENT_TYPE_DELETE_JSP = "event-type-delete.jsp";

   public static final String EVENT_TYPE_LIST_SHOW = "eventTypeListShow.do";

   public static final String SES_EVENT_TYPE = "sesEventType";

   public static final String EVENT_TYPE_LIST_JSP = "event-type-list.jsp";

   public static final String EVENT_TYPE_CREATE_JSP = "event-type-create.jsp";

   public static final String EVENT_TYPE_CREATE_SHOW = "eventTypeCreateShow.do";

   public static final String EVENT_TYPE_CREATE = "eventTypeCreate.do";

   public static final String EVENT_TYPE_UPDATE_JSP = "event-type-jsp";

   public static final String EVENT_TYPE_UPDATE_SHOW = "eventTypeUpdateShow.do";

   public static final String EVENT_TYPE_UPDATE = "eventTypeUpdate.do";

   public static final String EVENT_TYPE_DELETE_SHOW = "eventTypeDeleteShow.do";

   public static final String EVENT_TYPE_DELETE = "eventTypeDelete.do";

   public List<EventType> getRelevantEventTypes(List<Integer> loginIds) {
      EventTypeCriteria criteria = new EventTypeCriteria();
      criteria.setLoginId(loginIds);
      List<EventType> list = eventTypeDao.retrieve(criteria, 0);
      if (list != null) {
         logger.debug("Found these eventTypes:");
         for (EventType eventType : list) {
           logger.debug("eventType="+ eventType); 
         };               
      }
      return list;
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_LIST_SHOW, method = RequestMethod.GET)
   public String showEventTypeListGet(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_LIST_SHOW + ": params=" + params);

      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         WebCommonAPI.statusReset(session);

         EventTypeCriteria etc = new EventTypeCriteria();
         etc.addLoginId(wl.getOwner().getId());

         List<EventType> list = eventTypeDao.retrieve(etc, 0);

         List<WebEntityEventType> weet = new ArrayList<WebEntityEventType>();

         for (EventType eventType : list) {
            WebEntityEventType w = new WebEntityEventTypeImpl();
            weet.add(w);
         }

         WebCommonAPI.addObjectToSession(session, SES_EVENT_TYPES, weet);
//         WebCommonAPI.addObjectToSession(session, SES_LOGIN_ID, wl.getOwner().getId());

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_LIST_JSP;
      } else {
         WebCommonAPI.statusReset(session);
         return SLASH;
      }

   }

//   Event-type-create
   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_CREATE_SHOW, method = RequestMethod.POST)
   public String eventTypeCreateShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_CREATE_SHOW + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null && wl.isAuthenticated()) {
         // we want to create new
         Integer loginId = wl.getOwner().getId();
         WebEntityEventType wet = new WebEntityEventTypeImpl();
         wet.setLoginId(loginId.toString());

         session.setAttribute(SES_EVENT_TYPE, wet);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_CREATE_JSP;
      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_CREATE, method = RequestMethod.POST)
   public String createEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_CREATE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         WebEntityEventType webET = new WebEntityEventTypeImpl();
         EventType eventTypeInstance = establishEventTypeFrom(webET);

         eventTypeInstance = eventTypeDao.insert(eventTypeInstance);

         // Looking data up again
         EventTypeCriteria c = new EventTypeCriteria();
         c.addLoginId(wl.getOwner().getId());
         List<EventType> typeList = eventTypeDao.retrieve(c, 0);

         session.setAttribute(SES_EVENT_TYPES, typeList);
         session.removeAttribute(SES_EVENT_TYPE);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_LIST_JSP;

      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_UPDATE_SHOW, method = RequestMethod.POST)
   public String eventTypeUpdateShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_UPDATE_SHOW + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);

      if (wl != null && wl.isAuthenticated()) {
         Integer loginId = null;
         Integer id = null;

         WebEntityEventType webEventType = new WebEntityEventTypeImpl();

         String id2 = webEventType.getId();
//         String loginId2 = webEventType.getLoginId();

         EventTypeCriteria etC = new EventTypeCriteria();
//         etC.setLoginId(Collections.singletonList(Integer.parseInt(loginId2) ));
         etC.addId(Integer.parseInt(id2));

         List<EventType> eventTypes = eventTypeDao.retrieve(etC, 0);
         logger.debug("retrieved eventType(s):" + eventTypes);

         if (eventTypes != null && eventTypes.size() == 1) {
            EventType eventType = eventTypes.get(0);
            WebEntityEventType wet = new WebEntityEventTypeImpl();
            session.setAttribute(SES_EVENT_TYPE, wet);
         } else {
            throw new EventWebControllerException("Did not recieve exactly one eventType, when attempting to lookup");
         }

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_UPDATE_JSP;

      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_UPDATE, method = RequestMethod.POST)
   public String eventTypeUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_UPDATE + " params=" + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         WebEntityEventType webET = new WebEntityEventTypeImpl();
         EventType eventTypeInstance = establishEventTypeFrom(webET);

         int update = eventTypeDao.update(eventTypeInstance);
         logger.debug("updated " + update + " row(s)");

         EventTypeCriteria crit = new EventTypeCriteria();
         crit.setLoginId(Collections.singletonList(loginId));
         List<EventType> list = eventTypeDao.retrieve(crit, 0);

         session.setAttribute(SES_EVENT_TYPES, list);

         session.removeAttribute(SES_EVENT_TYPE);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_LIST_JSP;
      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_DELETE_SHOW, method = RequestMethod.POST)
   public String eventTypeDeleteShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_DELETE_SHOW + ": params=" + params);
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
//         String id = params.get("id");
//         if (StringUtils.isBlank(id)) {
//            throw new EventWebInsufficientParameterValuesException("received insufficient value for id=" + id);
//         }new 
//
         WebEntityEventType et = new WebEntityEventTypeImpl();
         EventType eventType = establishEventTypeFrom(et);
         eventType = eventTypeDao.get(eventType.getId(), loginId);
         WebEntityEventType wet = new WebEntityEventTypeImpl();
         session.setAttribute(SES_EVENT_TYPE, wet);
//         WebCommonAPI.statusAdd(session, "EventType deleted");
         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_DELETE_JSP;
      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_DELETE, method = RequestMethod.POST)
   public String eventTypeDelete(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TYPE_DELETE + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         WebEntityEventType et = new WebEntityEventTypeImpl();

         EventType eventType = establishEventTypeFrom(et);

//         String id = params.get(REQ_EVENT_TYPE_ID);
//         if (StringUtils.isNotEmpty(id)) {
         int count = eventTypeDao.delete(eventType.getId(), eventType.getLoginId());
         WebCommonAPI.statusAdd(session, "Deleted " + count + " EventTypes");
         EventTypeCriteria c = new EventTypeCriteria();
         c.setLoginId(Collections.singletonList(loginId));
         List<EventType> typeList = eventTypeDao.retrieve(c, 0);

         session.setAttribute(SES_EVENT_TYPES, typeList);
         session.removeAttribute(SES_EVENT_TYPE);

         return REDIRECT + SLASH;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + EVENT_TYPE_LIST_SHOW;
//         }
      } else {
         return SLASH;
      }
   }

   public EventType establishEventTypeFrom(WebEntityEventType webET) {
      EventType et = new EventType();
      String id = webET.getId();
      String created = webET.getCreated();
      String loginId = webET.getLoginId();

      String name = webET.getName();
      String shortName = webET.getNameShort();
      String description = webET.getDescription();

      if (id != null) {
         try {
            Integer typeId = Integer.valueOf(id);
            et.setId(typeId);
         } catch (NumberFormatException e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      }

      if (StringUtils.isNotBlank(created)) {
         LocalDateTime createdLDT = WebEntityUtil.getLocalDateTimeFrom(created);
         et.setCreated(createdLDT);
      }

      if (loginId != null) {
         try {
            et.setLoginId(Integer.valueOf(loginId));
         } catch (NumberFormatException e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      } else {

      }

      et.setName(name);
      et.setShortName(shortName);
      et.setDescription(description);

      return et;
   }

//   public WebEntityEventType buildWebEventTypeFrom(Map<String, String> params) {
//      WebEntityEventType webET = new WebEntityEventType();
//
//      String id = params.get(REQ_EVENT_TYPE_ID);
//      String loginId = params.get(REQ_LOGIN_ID);
//      String created = params.get(REQ_CREATED);
//      webET.setId(id);
//      webET.setLoginId(loginId);
//      webET.setCreated(created);
//
//      String reqName = params.get(REQ_NAME);
//      String newShortName = params.get(REQ_NAME_SHORT);
//      String newDescription = params.get(REQ_DESCRIPTION);
//      webET.setName(reqName);
//      webET.setShortName(newShortName);
//      webET.setDescription(newDescription);
//      logger.debug("created this webEntityEventType=" + webET);
//      return webET;
//   }

//   // event-type.select.do
//   @RequestMapping(value = SECURE + SLASH + EVENT_TYPE_SELECT, method = RequestMethod.POST)
//   public String eventTypeSelect(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug(SECURE + SLASH + EVENT_TYPE_SELECT + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         Login owner = wl.getOwner();
//         
//         String eventTypeIdStr = params.get("req-event-type-id-select");
//         if(StringUtils.isNotEmpty(eventTypeIdStr)) {
//            prepareForEventTypeSelect(session, owner.getId(), Integer.parseInt(eventTypeIdStr));
//         } else {
//            prepareForEventTypeSelect(session, owner.getId(), null);            
//         }
//         
//        return REDIRECT + SLASH + SECURE + SLASH + EVENT_CREATE_JSP;
//
//      } else {
//         return SLASH;
//
//      }
//   }

}
