package dk.schioler.event.web.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.common.EventInsufficientInputDataException;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.controller.api.BaseControllerAPI;
import dk.schioler.event.web.controller.exception.EventWebControllerException;
import dk.schioler.event.web.controller.exception.EventWebInsufficientParameterValuesException;
import dk.schioler.event.web.entity.WebLogin;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.dao.UserProfileDAO;
import dk.schioler.shared.security.encrypt.Encrypter;
import jakarta.servlet.http.HttpSession;

public class AbstractController implements BaseControllerAPI {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   public List<UNIT> getSelectableUnits() {
      UNIT[] values = UNIT.values();
      List<UNIT> asList = Arrays.asList(values);

      return asList;
   }


   // ************************************************************
   @Autowired
   protected WebCommonAPI webCommonAPI;

   @Autowired
   protected EventDAO eventDAO;

   @Autowired
   protected EventTypeDAO eventTypeDAO;

   @Autowired
   protected EventTemplateDAO eventTemplateDAO;

   @Autowired
   protected LoginDAO loginDAO;

   @Autowired
   protected PasswordDAO passwordDAO;

   @Autowired
   protected Encrypter encrypter;

   @Autowired
   protected UserProfileDAO userProfileDAO;

   // Login stuff
   protected WebLogin getAuthenticatedLogin(HttpSession session) {
      return this.webCommonAPI.getAuthenticatedLogin(session);
   }

   public boolean isLoginAuthenticated(HttpSession session) {
      return webCommonAPI.isLoginAuthenticated(session);
   }

   public void setAuthenticatedLogin(HttpSession session, WebLogin weblogin) {
      webCommonAPI.setAuthenticatedLogin(session, weblogin);
   }

   public boolean isPublicURL(String requestURI) {
      return webCommonAPI.isPublicURL(requestURI);
   }

   public void addObjectToSession(HttpSession session, String key, Object object) {
      webCommonAPI.addObjectToSession(session, key, object);
   }

   public List<Object> getListFromSession(HttpSession session, String key) {
      return webCommonAPI.getListFromSession(session, key);
   }

   public void addToStatus(HttpSession session, String msg) {
      webCommonAPI.addToStatus(session, msg);
   }

   public void resetStatus(HttpSession session) {
      webCommonAPI.resetStatus(session);
   }

   // *******************************************************


   protected AbstractEntityName addEntityNameValues(AbstractEntityName entity, Map<String, String> params) throws EventInsufficientInputDataException {
      String name = params.get(PAR_NAME);
      String shortName = params.get(PAR_SHORT_NAME);
      String description = params.get(PAR_DESCRIPTION);

      entity.setName(name);
      entity.setShortName(shortName);
      entity.setDescription(description);
      return entity;

   }


   protected Integer extractRequiredIntegerFromParams(String key, Map<String, String> params) {
      String webId = params.get(key);

      Integer i = null;
      if (StringUtils.isNotBlank(webId)) {
         try {
            i = Integer.valueOf(webId);
         } catch (NumberFormatException nfe) {
            String msg = "Unable to parse id from String to Integer: id=" + webId;
            logger.error(msg);
            throw new EventWebInsufficientParameterValuesException(msg + "\n" + nfe.getMessage(), nfe);
         }
      } else {
         throw new EventWebInsufficientParameterValuesException("found no value for ");
      }
      return i;
   }

//   **************************************************************'''
//   EVENT_TYPE
   protected EventType establishEventTypeCreateInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
      EventType eventType = new EventType();
      eventType.setLoginId(loginId);
      eventType = (EventType) addEntityNameValues(eventType, params);
      return eventType;
   }

   protected EventType establishEventTypeUpdateInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
      EventType eventType = establishEventTypeCreateInstance(params, loginId);
      
      Integer i = extractRequiredIntegerFromParams(PAR_EVENT_TYPE_ID, params);
      eventType.setId(i);
      
      return eventType;
   }

   protected EventType establishEventTypeDeleteInstance(Map<String, String> params, Integer loginId) {
      EventType et = new EventType();
      et.setLoginId(loginId);
      Integer i = extractRequiredIntegerFromParams(PAR_EVENT_TYPE_ID, params);
      et.setId(i);
      return et;
   }
   // ******************************'
   //   EventtTemplate
   // ******************************'
   protected EventTemplate establishEventTemplateSpecificDataInstance(Map<String, String> params, EventTemplate eventTemplate) throws EventInsufficientInputDataException {
      // oarent ref
      Integer eTypeId = extractRequiredIntegerFromParams(PAR_EVENT_TYPE_ID, params);
      
      // Some date
      String unit = params.get(PAR_UNIT);
      String doseStr = params.get(PAR_DOSE);
      String isFavorite = params.get(PAR_IS_FAVORITE);
      String sortOrderStr = params.get(PAR_SORT_ORDER);

      boolean isFav = BooleanUtils.toBoolean(isFavorite);

      Integer sortO = null;
      if (StringUtils.isNotBlank(sortOrderStr)) {
         sortO = Integer.valueOf(sortOrderStr);
      } else {
         sortO = 1;
      }

      BigDecimal dose;
      try {
         dose = new BigDecimal(doseStr);
      } catch (NumberFormatException nfe) {
         logger.error(doseStr + " did not parse properly.....");
         throw new EventWebControllerException(doseStr + " did not parse properly.....");
      }

      UNIT u = UNIT.getUnitFromString(unit);
      if (u == null) {
         throw new EventWebControllerException(unit + " is not a defined unit");
      }

      
      eventTemplate.setParentId(eTypeId);
      eventTemplate.setDose(dose);
      eventTemplate.setUnit(u);
      eventTemplate.setFavorite(isFav);
      eventTemplate.setSortOrder(sortO);
      return eventTemplate;
   }

   protected EventTemplate establishEventTemplateCreateInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
      EventTemplate eventTemplate = new EventTemplate();
      eventTemplate.setLoginId(loginId);
      eventTemplate = (EventTemplate) addEntityNameValues(eventTemplate, params);
      eventTemplate = establishEventTemplateSpecificDataInstance(params, eventTemplate);
      return eventTemplate;
   }

   protected EventTemplate establishEventTemplateUpdateInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
      EventTemplate eventTemplate = establishEventTemplateCreateInstance(params, loginId);
      
      Integer i = extractRequiredIntegerFromParams(PAR_EVENT_TEMPLATE_ID, params);
      eventTemplate.setId(i);
      
      return eventTemplate;
   }

   protected EventTemplate establishEventTemplateDeleteInstance(Map<String, String> params, Integer loginId) {
      EventTemplate et = new EventTemplate();      
      et.setLoginId(loginId);
      
      Integer i = extractRequiredIntegerFromParams(PAR_EVENT_TEMPLATE_ID, params);
      et.setId(i);
      return et;
   }

   // ******************************'
   //    EVENT
   // ******************************'
   protected Event fillEventWithEventTemplateData(EventTemplate template) {
      Event e = new Event();
      
      e.setLoginId(template.getLoginId());
      e.setName(template.getName());
      e.setShortName(template.getShortName());
      e.setDescription(template.getDescription());
      
      e.setParentId(template.getId());
      e.setDose(template.getDose());
      e.setUnit(template.getUnit());

      
      return e;
   }
   
   protected Event addEventSpecificData(Map<String, String> params, Event event, Integer login) throws EventInsufficientInputDataException {
      
      Integer parentId = extractRequiredIntegerFromParams(PAR_EVENT_TEMPLATE_ID, params);
      
      String dose = params.get(PAR_DOSE);
      String unit = params.get(PAR_UNIT);
      
      String date = params.get(PAR_DATE);
      String time = params.get(PAR_TIME);
      // must be in ISO_LOCAL_TIME format: ex 10:29:55
      LocalTime lt = LocalTime.parse(time);
      // must be in ISO_LOCAL_DATE format, ex 2007-12-03
      LocalDate ld = LocalDate.parse(date);
      LocalDateTime eventTS = LocalDateTime.of(ld, lt);

      String note = params.get(PAR_NOTE);
      
      event.setParentId(parentId);
      event.setDose(new BigDecimal(dose));
      event.setUnit(UNIT.getUnitFromString(unit));
      event.setEventTS(eventTS);
      event.setNote(note);

      return event;
   }

   
   protected Event establishEventCreateInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
      Event event = new Event();
      event.setLoginId(loginId);
      event = (Event) addEntityNameValues(event, params);
      
      return event;
   }

   protected Event establishEventUpdateInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
      Event event = establishEventCreateInstance(params, loginId);
      
      Integer i = extractRequiredIntegerFromParams(PAR_EVENT_ID, params);
      event.setId(i);
      
      return event;
   }

   protected Event establishEventDeleteInstance(Map<String, String> params, Integer loginId) {
      Event et = new Event();      
      et.setLoginId(loginId);
      
      Integer i = extractRequiredIntegerFromParams(PAR_EVENT_ID, params);
      et.setId(i);
      return et;
   }
   
   
   
//   protected Event createEventInstance(Map<String, String> params, Integer loginId) throws EventInsufficientInputDataException {
//      Event event = new Event();
//      event.setLoginId(loginId);
//
////      event = (Event) getEntityIdFromParams(event, params);
//      event = (Event) addEntityNameValues(event, params);
//
//      String templIdStr = params.get(PAR_EVENT_TEMPLATE_ID);
//      if (StringUtils.isEmpty(templIdStr)) {
//         throw new EventWebInsufficientParameterValuesException("createEventInstance: " + PAR_EVENT_TEMPLATE_ID + " parameter missing or not valid");
//      }
//
//      Integer templateId = null;
//      try {
//         templateId = Integer.parseInt(templIdStr);
//      } catch (NumberFormatException e) {
//         throw new EventWebInValidParameterValuesException(e.getMessage(), e);
//      } catch (Exception e) {
//         throw new EventWebControllerException(e.getMessage(), e);
//      }
//
//      String dose = params.get(PAR_DOSE);
//      if (StringUtils.isEmpty(dose)) {
//         throw new EventWebInsufficientParameterValuesException("createEventInstance: " + PAR_DOSE + " parameter missing.");
//      }
//      BigDecimal doseBD = null;
//      try {
//         doseBD = new BigDecimal(dose);
//      } catch (NumberFormatException nfl) {
//         throw new EventWebControllerException("Unable to parse " + PAR_DOSE + " value. Got: " + nfl.getMessage(), nfl);
//      }
//
//      String unit = params.get(PAR_UNIT);
//      if (StringUtils.isEmpty(unit)) {
//         throw new EventWebInsufficientParameterValuesException("createEventInstance: " + PAR_UNIT + " parameter missing");
//      }
//      UNIT unitInType = UNIT.getUnitFromString(unit);
//      if (unitInType == null) {
//         throw new EventWebInsufficientParameterValuesException("createEventInstance: " + PAR_UNIT + " parameter with unparseable value: " + unit);
//      }
//
//      String note = params.get(PAR_NOTE);
//
//      String dateStr = params.get(PAR_DATE);
//      String timeStr = params.get(PAR_TIME);
//
//      LocalDateTime eventCustomDateTime = establishLocalDateTime(dateStr, timeStr, LocalDateTime.now());
//
//      event.setParentId(templateId);
//      event.setEventTS(eventCustomDateTime);
//      event.setNote(note);
//      event.setDose(doseBD);
//      event.setUnit(unitInType);
//      return event;
//   }

   protected LocalDateTime establishLocalDateTime(String dateStr, String timeStr, LocalDateTime fallback) {

      LocalDateTime customDateTime = null;

      if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(timeStr)) {
         try {
            // Date yyyy-mm-dd
            // Time hh:mi
            String tdStr = dateStr + "T" + timeStr + ":00";
//           logger.debug("da - ti str" + tdStr);
            customDateTime = LocalDateTime.parse(tdStr, DateTimeFormatter.ISO_DATE_TIME);
         } catch (Exception e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      } else {
         customDateTime = fallback;
      }
      return customDateTime;

   }

}
