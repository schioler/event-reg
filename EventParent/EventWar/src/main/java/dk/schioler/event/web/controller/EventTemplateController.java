package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.controller.exception.EventWebControllerException;
import dk.schioler.event.web.controller.exception.EventWebInsufficientParameterValuesException;
import dk.schioler.event.web.entity.WebEntityEventTemplate;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import dk.schioler.event.web.entity.impl.WebEntityEventTemplateImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventTemplateController extends AbstractController implements  WebEntitySymbolsShared {

// EVENT-TEMPLATE

// public static final String SES_EVENT_TEMPLATE_ID = "sesEventTemplateId";
// public static final String SES_EVENT_TEMPLATE = "sesEventTemplate";
// public static final String SES_EVENT_TEMPLATES = "sesEventTemplates";

 public static final String TEMPLATE_SELECT_UNIT_UPDATE = "event-template-select-unit-update.do";
 
 public final static String EVENT_TEMPLATE_LIST_SHOW = "event-template-list-show.do";
 public final static String EVENT_TEMPLATE_TYPE_SELECT = "event-template-type-select.do";
 public final static String EVENT_TEMPLATE_LIST_JSP = "21-event-template-list.jsp";

 public final static String EVENT_TEMPLATE_CREATE_SHOW = "event-template-create-show.do";
 public final static String EVENT_TEMPLATE_CREATE = "event-template-create.do";
 public final static String EVENT_TEMPLATE_CREATE_JSP = "02-event-template-create.jsp";

 public final static String EVENT_TEMPLATE_UPDATE_SHOW = "event-template-update-show.do";
 public final static String EVENT_TMPL_UPDATE = "event-template-update.do";
 public final static String EVENT_TEMPLATE_UPDATE_JSP = "02-event-template-update.jsp";

 public final static String EVENT_TEMPLATE_DELETE_SHOW = "event-template-delete-show.do";
 public final static String EVENT_TEMPLATE_DELETE = "event-template-delete.do";
 public final static String EVENT_TEMPLATE_DELETE_JSP = "02-event-template-delete.jsp";
 
 public static final String SES_EVENT_TEMPLATE_ID = "sesEventTemplateId";
 public static final String SES_EVENT_TEMPLATE = "sesEventTemplate";
 public static final String SES_EVENT_TEMPLATES = "sesEventTemplates";
 
//   @Autowired
//   protected EventTemplateDAO eventTemplateDAO;
//
//   @Autowired
//   protected EventTypeDAO eventTypeDAO;

   public List<EventTemplate> getRelatedEventTemplates(Integer ownerId, Integer eventTypeId ){
      EventTemplateCriteria etc = new EventTemplateCriteria();
      etc.addEventTypeId(eventTypeId);
      etc.addLoginId(ownerId);
      List<EventTemplate> tmplList = eventTemplateDAO.retrieve(etc, 0);
      return tmplList;
   }
    
   List<EventType> retrieveEventTypesAndTemplates(Integer ownerId) {
      EventTypeCriteria eTypeCrit = new EventTypeCriteria();
      eTypeCrit.addLoginId(ownerId);

      List<EventType> typeList = eventTypeDao.retrieve(eTypeCrit, 0);
      for (EventType eventType : typeList) {
         EventTemplateCriteria etc = new EventTemplateCriteria();
         etc.addEventTypeId(eventType.getId());
         etc.addLoginId(ownerId);
         List<EventTemplate> tmplList = eventTemplateDAO.retrieve(etc, 0);
         for (EventTemplate tmpl : tmplList) {
            eventType.addChild(tmpl);
         }
      }
      return typeList;
   }

   List<EventType> retrieveEventTypes(Integer ownerId) {
      EventTypeCriteria eTypeCrit = new EventTypeCriteria();
      eTypeCrit.addLoginId(ownerId);

      List<EventType> typeList = eventTypeDao.retrieve(eTypeCrit, 0);

      return typeList;
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TEMPLATE_LIST_SHOW, method = RequestMethod.GET)
   public String eventTmplListShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TEMPLATE_LIST_SHOW + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         
         List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
//         prepareForSelectEventType(session, loginId, params);
         session.setAttribute(SES_EVENT_TYPES, eventTypes);
//         session.removeAttribute(SES_EVENT_TYPE);
//         session.removeAttribute(SES_EVENT_TEMPLATES);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_LIST_JSP;

      } else {
         return SLASH;
      }
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TEMPLATE_CREATE_SHOW, method = RequestMethod.POST)
   public String eventTmplCreateShowGet(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TEMPLATE_CREATE_SHOW + ": params=" + params);

      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         Map<String, String> parameters = new TreeMap<String, String>();
         parameters.putAll(params);
//         parameters.put(REQ_LOGIN_ID, loginId.toString());

         WebEntityEventTemplate wET = new WebEntityEventTemplateImpl();
         session.setAttribute(SES_EVENT_TEMPLATE, wET);

//         List<UNIT> selectableUnits = this.getSelectableUnits();
//         session.setAttribute(SES_SELECT_UNITS, selectableUnits);

         // we want to create new
         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_CREATE_JSP;

      } else {
         return SLASH;
      }
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TEMPLATE_CREATE, method = RequestMethod.POST)
   public String eventTemplateCreatePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TEMPLATE_CREATE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         WebEntityEventTemplate webEventTemplateEntity = new WebEntityEventTemplateImpl();

         EventTemplate et = convertToEventTemplate(webEventTemplateEntity);
         et = eventTemplateDAO.insert(et);

         WebCommonAPI.statusAdd(session, "Created EventTemplate: name=" + et.getName() + ", and Id=" + et.getId());

         // Looking up eventTypes + EventTemplates
         EventTemplateCriteria etC = new EventTemplateCriteria();
         etC.addLoginId(loginId);

         List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);

         session.setAttribute(SES_EVENT_TYPES, eventTypes);

         session.removeAttribute(SES_EVENT_TEMPLATE);
//         session.removeAttribute(SES_SELECT_UNITS);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_LIST_JSP;
      } else {
         return SLASH;
      }
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TEMPLATE_UPDATE_SHOW, method = RequestMethod.POST)
   public String eventTmplUpdateShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TEMPLATE_UPDATE_SHOW + ": params=" + params);
      HttpSession session = request.getSession();
      WebCommonAPI.statusReset(session);

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         WebEntityEventTemplate wET = new WebEntityEventTemplateImpl();
         Integer etId = Integer.valueOf(wET.getId());

         EventTemplate eventTemplateDb = eventTemplateDAO.get(etId, loginId);
         logger.debug("retrieved eventTemplate from db=" + eventTemplateDb);

         if (eventTemplateDb != null) {
            wET = new WebEntityEventTemplateImpl();
            session.setAttribute(SES_EVENT_TEMPLATE, wET);

            List<UNIT> selectableUnits = this.getSelectableUnits();
            logger.debug("Selectable UNITS=" + selectableUnits);
//            session.setAttribute(SES_SELECT_UNITS, selectableUnits);

            return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_UPDATE_JSP;
         } else {
            String errorTemplateNotFound = "Can not update eventTemplate, since the EventTemplate to update, can not be found";
            WebCommonAPI.statusAdd(session, errorTemplateNotFound);
            return EVENT_TEMPLATE_LIST_JSP;
         }

      } else {
         return SLASH;
      }

   }

   /**
    * Will handle the value selected by user
    * 
    * @param params
    * @param model
    * @param request
    * @return
    */
   @RequestMapping(value = SECURE + SLASH + TEMPLATE_SELECT_UNIT_UPDATE, method = RequestMethod.POST)
   public String eventTmplUnitSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + TEMPLATE_SELECT_UNIT_UPDATE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      logger.debug("weblogin=" + wl);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         WebEntityEventTemplate wETSession = (WebEntityEventTemplate) session.getAttribute(SES_EVENT_TEMPLATE);
         logger.debug("eventTmpl from session=" + wETSession.toString());

         WebEntityEventTemplate wET = new WebEntityEventTemplateImpl();
         logger.debug("eventTemplate from params: " + wET);

         session.setAttribute(SES_EVENT_TEMPLATE, wET);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_LIST_JSP;
      } else {
         return SLASH;
      }
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TMPL_UPDATE, method = RequestMethod.POST)
   public String eventTmplUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TMPL_UPDATE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      logger.debug("weblogin=" + wl);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         WebEntityEventTemplate wet = new WebEntityEventTemplateImpl();
         Integer i = Integer.valueOf(wet.getId());
         EventTemplate eventTemplate = eventTemplateDAO.get(i, loginId);

         logger.debug("looked up template, will update values: " + eventTemplate);

         EventTemplate et = convertToEventTemplate(wet);

         eventTemplateDAO.update(et);

         List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
         session.setAttribute(SES_EVENT_TYPES, eventTypes);

         return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_LIST_JSP;
      } else {
         return SLASH;
      }
   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TEMPLATE_DELETE_SHOW, method = RequestMethod.POST)
   public String eventTmplDeleteShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TEMPLATE_DELETE_SHOW + ": params=" + params);
      HttpSession session = request.getSession();
      WebCommonAPI.statusReset(session);
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         String tmplId = params.get("");

         if (StringUtils.isNotBlank(tmplId)) {
            Integer id = Integer.valueOf(tmplId);
            EventTemplate eventTemplate = eventTemplateDAO.get(id, loginId);

            WebEntityEventTemplate wet = new WebEntityEventTemplateImpl();
            session.setAttribute(SES_EVENT_TEMPLATE, wet);
            return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_DELETE_JSP;
         } else {
            throw new EventWebInsufficientParameterValuesException(
                  EVENT_TEMPLATE_DELETE_SHOW + ", trying to parse " +  " from req - didn't work");
         }

      } else {
         return SLASH;
      }

   }

   @RequestMapping(value = SECURE + SLASH + EVENT_TEMPLATE_DELETE, method = RequestMethod.POST)
   public String deleteEventTemplatePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + EVENT_TEMPLATE_DELETE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         WebEntityEventTemplate wET = new WebEntityEventTemplateImpl();
         String id = wET.getId();
         if (StringUtils.isNotBlank(id)) {
            int delete = eventTemplateDAO.delete(Integer.valueOf(id), loginId);

            WebCommonAPI.statusAdd(session, "deleted template");
            logger.debug("Deleted " + delete + " eventTemplate");

            List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
            session.setAttribute(SES_EVENT_TYPES, eventTypes);

            return REDIRECT + SLASH + SECURE + SLASH + EVENT_TEMPLATE_LIST_JSP;
         } else {
            throw new EventWebControllerException("Did not find required id parameter ");
         }
      } else {
         return SLASH;
      }
   }

   /**
    * Will create an EventTemplate object, with values from WebEventTemplate.
    * 
    * @param webObj
    * @param evt
    * @return
    */
   public EventTemplate convertToEventTemplate(WebEntityEventTemplate webObj) {
      EventTemplate et = null;

      et = new EventTemplate();

      String id = webObj.getId();
      String created = webObj.getCreated();

      String loginId = webObj.getLoginId();
      String eventTypeId = webObj.getEventTypeId();

      String name = webObj.getName();
      String shortName = webObj.getNameShort();
      String description = webObj.getDescription();

      String unit = webObj.getUnit();
      String dose = webObj.getDose();
      String isFavorite = webObj.isFavorite();
      String sortOrder = webObj.getSortOrder();

      if (StringUtils.isNotBlank(id)) {
         try {
            Integer i = Integer.valueOf(id);
            et.setId(i);
         } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            throw new EventWebControllerException(e.getMessage(), e);
         }
      }

      if (StringUtils.isNotBlank(created)) {
         try {
            LocalDateTime ldt = LocalDateTime.parse(created);
            et.setCreated(ldt);
         } catch (DateTimeParseException e) {
            logger.error(e.getMessage(), e);
            throw new EventWebControllerException(e.getMessage(), e);
         }
      }

      if (StringUtils.isNotBlank(loginId)) {
         try {
            Integer i = Integer.valueOf(loginId);
            et.setLoginId(i);
         } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            throw new EventWebControllerException(e.getMessage(), e);
         }
      }

      if (StringUtils.isNotBlank(eventTypeId)) {
         try {
            Integer i = Integer.valueOf(eventTypeId);
            et.setParentId(i);

         } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            throw new EventWebControllerException(e.getMessage(), e);
         }
      }

      et.setName(name);
      et.setShortName(shortName);
      et.setDescription(description);

      et.setDose(dose);

      if (StringUtils.isNotBlank(unit)) {
         UNIT u = UNIT.getUnitFromString(unit);
         if (u != null) {
            et.setUnit(u);
         }
      }

      et.setFavorite(Boolean.parseBoolean(isFavorite));

      if (StringUtils.isNotBlank(sortOrder)) {
         try {
            Integer i = Integer.valueOf(sortOrder);
            et.setSortOrder(i);
         } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
            et.setSortOrder(0);
         }
      }

      return et;
   }

//   /**
//    * Will establish a WebEventTemplate instance from the provided params (request) values
//    * 
//    * @param params: values to 
//    * @return
//    */
//   public WebEntityEventTemplate buildWebEventTemplateFrom(Map<String, String> params) {
//      WebEntityEventTemplate wET = new WebEntityEventTemplate();
//
//      String id = params.get(REQ_EVENT_TEMPLATE_ID);
//      String loginId = params.get(REQ_LOGIN_ID);
//      String created = params.get(REQ_CREATED);
//      wET.setId(id);
//      wET.setLoginId(loginId);
//      wET.setCreated(created);
//
//      String eventTypeId = params.get(REQ_EVENT_TYPE_ID);
//      wET.setEventTypeId(eventTypeId);
//
//      String reqName = params.get(REQ_NAME);
//      String newShortName = params.get(REQ_SHORT_NAME);
//      String newDescription = params.get(REQ_DESCRIPTION);
//      wET.setName(reqName);
//      wET.setShortName(newShortName);
//      wET.setDescription(newDescription);
//
//      String unit = params.get(REQ_SELECT_UNIT);
//      String doseStr = params.get(REQ_DOSE);
//      String isFavorite = params.get(REQ_IS_FAVORITE);
//      String sortOrderStr = params.get(REQ_SORT_ORDER);
//
//      wET.setUnit(unit);
//      wET.setDose(doseStr);
//      wET.setIsFavorite(isFavorite);
//      wET.setSortOrder(sortOrderStr);
//
//      return wET;
//   }

//   public List<WebEventTypeEntity> convertToWebObject(List<EventType> typeList) {
//      List<WebEventTypeEntity> list = new ArrayList<WebEventTypeEntity>();
//
//      for (EventType eventTypeEntity : typeList) {
//         WebEventTypeEntity type = new WebEventTypeEntity(eventTypeEntity);
//         list.add(type);
//      }
//      return list;
//   }

//   public EventTemplate establishEventTemplate(WebEventTemplateEntity wET) {
//    EventTemplate currentlyPersisted = new EventTemplate();
//    if (wET.getId() != null) {
//       String id = wET.getId();
//       currentlyPersisted.setId(Integer.parseInt(id));
//    }
//
//    
//    if (wET.getEventTypeId() != null) {
//       String typeId = wET.getEventTypeId();
//       currentlyPersisted.setParentId(Integer.parseInt(typeId));
//    }
//
//    if (wET.getLoginId() != null) {
//       String id = wET.getLoginId();
//       currentlyPersisted.setLoginId(Integer.parseInt(id));
//    }
//
//    if (wET.getCreated() != null) {
//       DateTimeFormatter dtFormatter = WebEntityUtil.getDtFormatter();
//       LocalDateTime localDateTime = LocalDateTime.parse(wET.getCreated(), dtFormatter);
//
//       currentlyPersisted.setCreated(localDateTime);
//
//    }
//
//    if (wET.getName() != null) {
//       currentlyPersisted.setName(wET.getName());
//    }
//
//    if (wET.getShortName() != null) {
//       currentlyPersisted.setShortName(wET.getShortName());
//    }
//
//    if (wET.getDescription() != null) {
//       currentlyPersisted.setDescription(wET.getDescription());
//    }
//
//    if (wET.getUnit() != null) {
//       currentlyPersisted.setUnit(UNIT.getUnitFromString(wET.getUnit()));
//
//    }
//
//    currentlyPersisted.setDose(wET.getDose());
//
//    if (wET.getIsFavorite() != null) {
//       try {
//          boolean isFav = BooleanUtils.toBoolean(wET.getIsFavorite());
//          currentlyPersisted.setFavorite(isFav);
//       } catch (Exception e) {
//          logger.error("webTemplateEntity.isFavorite did not parse properly: " + e.getMessage(), e);
//       }
//    }
//
//    if (StringUtils.isNotBlank(wET.getSortOrder())) {
//       int sOrder = Integer.parseInt(wET.getSortOrder());
//       currentlyPersisted.setSortOrder(sOrder);
//    } else {
//       logger.debug("sortOrder not found in request");
//    }
//
//    return currentlyPersisted;
//
// }

}
