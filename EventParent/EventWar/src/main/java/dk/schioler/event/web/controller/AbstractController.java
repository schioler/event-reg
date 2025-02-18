package dk.schioler.event.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.BaseWebCommon;
import dk.schioler.event.web.controller.exception.EventWebControllerException;
import dk.schioler.event.web.controller.exception.EventWebInValidParameterValuesException;
import dk.schioler.event.web.controller.exception.EventWebInsufficientParameterValuesException;
import dk.schioler.shared.security.dao.LoginDAO;
import dk.schioler.shared.security.dao.PasswordDAO;
import dk.schioler.shared.security.dao.UserProfileDAO;
import dk.schioler.shared.security.encrypt.Encrypter;
import jakarta.servlet.http.HttpSession;

public class AbstractController extends BaseWebCommon {

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
   protected UserProfileDAO userProfileDAO ;

   public static final String LOGIN_JSP = "redirect:public/login.jsp";

   public static final String SES_STATUS_MSG_LIST = "sesStatusMessageList";

   protected void setStatuMsg(HttpSession session, List<String> msgList) {
      session.setAttribute(SES_STATUS_MSG_LIST, msgList);
   }

   protected void addToStatus(HttpSession session, String msg) {
      List<String> list = (List<String>) session.getAttribute(SES_STATUS_MSG_LIST);
      if (list == null) {
         list = new ArrayList<String>();
      }
      list.add(msg);
      session.setAttribute(SES_STATUS_MSG_LIST, list);
   }

   protected void resetStatus(HttpSession session) {
      List<String> list = (List<String>) session.getAttribute(SES_STATUS_MSG_LIST);
      if (list == null) {
         list = new ArrayList<String>();
      } else {
         list.clear();
      }
      session.setAttribute(SES_STATUS_MSG_LIST, list);
   }

   public static final String PAR_EVENT_ID = "event-id";
   public static final String PAR_EVENT_TEMPLATE_ID = "event-template-id";
   public static final String PAR_EVENT_TYPE_ID = "event-type-id";
   public static final String PAR_LOGIN_ID = "login-id";
   public static final String PAR_NAME = "name";
   public static final String PAR_SHORT_NAME = "short-name";
   public static final String PAR_DOSE = "dose";
   public static final String PAR_UNIT = "unit";
   public static final String PAR_IS_FAVORITE = "is-favorite";
   public static final String PAR_DESCRIPTION = "description";
   public static final String PAR_DATE = "date";
   public static final String PAR_TIME = "time";

   protected EventTemplate createEventTemplateInstance(Map<String, String> params, Integer loginId) {

      String idStr = params.get(PAR_EVENT_TEMPLATE_ID);
      String eventTypeIdStr = params.get(PAR_EVENT_TYPE_ID);
      String name = params.get(PAR_NAME);
      String shortName = params.get(PAR_SHORT_NAME);
      String description = params.get(PAR_DESCRIPTION);
      String unit = params.get(PAR_UNIT);
      String dose = params.get(PAR_DOSE);
      String isFavorite = params.get(PAR_IS_FAVORITE);

      Integer eventTemplateId = null;
      if (idStr != null) {
         eventTemplateId = Integer.valueOf(idStr);
      }
      Integer eTypeId = null;
      if (eventTypeIdStr != null) {
         eTypeId = Integer.valueOf(eventTypeIdStr);
      } else {
         throw new EventWebInsufficientParameterValuesException("required " + PAR_EVENT_TYPE_ID + " was not provided, when building an EventTemplate instance");
      }

      EventTemplate et = new EventTemplate();
      et.setId(eventTemplateId);
      et.setParentId(eTypeId);
      et.setLoginId(loginId);
      et.setName(name);
      et.setDescription(shortName);
      et.setDose(new BigDecimal(dose));
      et.setUnit(UNIT.getUnit(unit));
      et.setDescription(description);
      et.setFavorite(BooleanUtils.toBoolean(isFavorite));
      return et;
   }

   protected EventType createEventTypeInstance(Map<String, String> params, Integer loginId) throws EventWebControllerException {
      Integer id = null;
      String idStr = params.get(PAR_EVENT_TYPE_ID);
      if (!StringUtils.isEmpty(idStr)) {
         try {
            id = Integer.parseInt(idStr);
         } catch (NumberFormatException e) {
            throw new EventWebInValidParameterValuesException(e.getMessage(), e);
         } catch (Exception e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      } else {
         logger.info("found no id in req parameters - skipping id");
      }
      String name = params.get(PAR_NAME);
      if (StringUtils.isEmpty(name)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: name parameter missing");
      }
      String shortName = params.get(PAR_SHORT_NAME);
      if (StringUtils.isEmpty(shortName)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: shortName parameter missing");
      }

      // optional
      String description = params.get(PAR_DESCRIPTION);

      EventType eventType = new EventType();// null, tmplId, note, eventCustomDateTime);
      eventType.setId(id);
      eventType.setLoginId(loginId);
      eventType.setName(name);
      eventType.setDescription(shortName);
      eventType.setDescription(description);

      return eventType;
   }

   protected Event createEventInstance(Map<String, String> params, Integer loginId) throws EventWebControllerException {

      Integer id = null;
      String idStr = params.get(PAR_EVENT_ID);
      if (!StringUtils.isEmpty(idStr)) {
         try {
            id = Integer.parseInt(idStr);
         } catch (NumberFormatException e) {
            throw new EventWebInValidParameterValuesException(e.getMessage(), e);
         } catch (Exception e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      } else {
         logger.info("found no id in req parameters - skipping id");
      }

      String templIdStr = params.get(PAR_EVENT_TEMPLATE_ID);

      if (StringUtils.isEmpty(templIdStr)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: template-id parameter missing");
      }

      Integer tmplId = null;
      try {
         tmplId = Integer.parseInt(templIdStr);
      } catch (NumberFormatException e) {
         throw new EventWebInValidParameterValuesException(e.getMessage(), e);
      } catch (Exception e) {
         throw new EventWebControllerException(e.getMessage(), e);
      }

      String name = params.get(PAR_NAME);
      if (StringUtils.isEmpty(name)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: name parameter missing");
      }
      String shortName = params.get(PAR_SHORT_NAME);
      if (StringUtils.isEmpty(shortName)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: short-name parameter missing");
      }

      // not mandatory, so no verification...
      String note = params.get(PAR_DESCRIPTION);

      String dose = params.get(PAR_DOSE);
      if (StringUtils.isEmpty(dose)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: dose parameter missing");
      }
      String unit = params.get(PAR_UNIT);
      if (StringUtils.isEmpty(unit)) {
         throw new EventWebInsufficientParameterValuesException("createEventInstance: unit parameter missing");
      }

      String dateStr = params.get(PAR_DATE);
      String timeStr = params.get(PAR_TIME);

      LocalDateTime eventCustomDateTime = null;

      if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(timeStr)) {
         try {
            // Date yyyy-mm-dd
            // Time hh:mi
            String tdStr = dateStr + "T" + timeStr + ":00";
//				logger.debug("da - ti str" + tdStr);
            eventCustomDateTime = LocalDateTime.parse(tdStr, DateTimeFormatter.ISO_DATE_TIME);
         } catch (Exception e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      } else {
         eventCustomDateTime = LocalDateTime.now();
      }

      Event event = new Event();// null, tmplId, note, eventCustomDateTime);
      event.setId(id);
      event.setParentId(tmplId);
      event.setLoginId(loginId);
      event.setEventTS(eventCustomDateTime);
      event.setName(name);
      event.setDescription(shortName);
      event.setNote(note);
      event.setDose(new BigDecimal(dose));
      event.setUnit(UNIT.getUnit(unit));
      return event;
   }

}
