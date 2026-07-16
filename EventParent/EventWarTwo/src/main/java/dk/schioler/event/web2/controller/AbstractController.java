package dk.schioler.event.web2.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.AbstractEntityId;
import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web2.common.InsufficientInputDataException;
import dk.schioler.event.web2.controller.exception.EventWebControllerException;
import dk.schioler.event.web2.controller.exception.EventWebInsufficientParameterValuesException;

@Controller
public class AbstractController {

   @Autowired
   protected EventTypeDAO eventTypeDao;

   @Autowired
   protected EventTemplateDAO eventTemplateDAO;

   @Autowired
   protected EventDAO eventDAO;

   protected Logger logger = LoggerFactory.getLogger(getClass());

   public List<UNIT> getSelectableUnits() {
      UNIT[] values = UNIT.values();
      List<UNIT> asList = Arrays.asList(values);

      return asList;
   }
   
   public List<String> getSelectableUnitsAsString() {
      List<String> asList = new ArrayList<String>();
      List<UNIT> selectableUnits = getSelectableUnits();
      for (UNIT unit : selectableUnits) {
         asList.add(unit.name());
      }
      
      return asList;
   }
   

   protected Integer extractRequiredIntegerFromParams(String key, Map<String, String> params) {
      String webId = params.get(key);
      logger.debug("extractRequiredIntegerFromParams: key="+key + "value=" + webId );

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
         throw new EventWebInsufficientParameterValuesException("found no value for key="+ key);
      }
      return i;
   }

   protected LocalDateTime establishLocalDateTime(String dateTimeStr, LocalDateTime fallback) {

      LocalDateTime customDateTime = null;

      if (StringUtils.isNotBlank(dateTimeStr)) {
         try {

            customDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ISO_DATE_TIME);
         } catch (Exception e) {
            throw new EventWebControllerException(e.getMessage(), e);
         }
      } else {
         customDateTime = fallback;
      }
      return customDateTime;

   }

   protected LocalDateTime establishLocalDateTime(String dateStr, String timeStr, LocalDateTime fallback) {

      LocalDateTime customDateTime = null;

      if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(timeStr)) {

         // Date yyyy-mm-dd
         // Time hh:mi
         String tdStr = dateStr + "T" + timeStr + ":00";
         logger.debug("da - ti str" + tdStr);
         customDateTime = establishLocalDateTime(tdStr, fallback);

      } else {
         customDateTime = fallback;
      }
      return customDateTime;

   }

// EntityId
   protected AbstractEntityId addEntityIdValues(AbstractEntityId entity, Map<String, String> params) throws InsufficientInputDataException {
//      String id = params.get(REQ_ID);
//      String created = params.get(REQ_CREATED);
//      String login = params.get(REQ_LOGIN_ID);
//
//      if (StringUtils.isNotBlank(id)) {
//         Integer idInt = null;
//         try {
//            idInt = Integer.parseInt(id);
//            entity.setId(idInt);
//         } catch (NumberFormatException e) {
//            logger.info("id = " + id + ", did not parse well" + e.getMessage());
//         }
//      }
//      if (StringUtils.isNotBlank(login)) {
//         Integer loginInt = null;
//         try {
//            loginInt = Integer.parseInt(login);
//            entity.setLoginId(loginInt);
//         } catch (NumberFormatException e) {
//            logger.info("login = " + login + ", did not parse well" + e.getMessage());
//         }
//      }
//
//      if (StringUtils.isNotBlank(created)) {
//         LocalDateTime ldt = LocalDateTime.parse(created, WebEntityUtil.getDtFormatter());
//         entity.setCreated(ldt);
//      }
//
      return entity;

   }

   // EntityName
   protected AbstractEntityName addEntityNameValues(AbstractEntityName e, Map<String, String> params) throws InsufficientInputDataException {
      AbstractEntityName entity = (AbstractEntityName) addEntityIdValues(e, params);

//      String name = params.get(REQ_NAME);
//      String shortName = params.get(REQ_SHORT_NAME);
//      String description = params.get(REQ_DESCRIPTION);
//      description = description != null ? description : "not set";
//
//      entity.setName(name);
//      entity.setShortName(shortName);
//      entity.setDescription(description);
      return entity;

   }

   // EVENT_TYPE
   protected EventType establishEventTypeCreateInstance(Map<String, String> params, Integer loginId) throws InsufficientInputDataException {
      EventType eventType = new EventType();
      eventType.setLoginId(loginId);
      eventType.setCreated(LocalDateTime.now());
      eventType = (EventType) addEntityNameValues(eventType, params);
      return eventType;
   }

//   protected EventType establishEventTypeUpdateInstance(Map<String, String> params, Integer loginId) throws InsufficientInputDataException {
//      EventType eventType = new EventType();
////      eventType.setLoginId(loginId);
////      addEntityNameValues(eventType, params);
////
////      Integer i = extractRequiredIntegerFromParams(REQ_EVENT_TYPE_ID, params);
////      eventType.setId(i);
//
//      return eventType;
//   }

   protected EventType establishEventTypeDeleteInstance(Map<String, String> params, Integer loginId) {
      EventType et = new EventType();
//      et.setLoginId(loginId);
//      Integer i = extractRequiredIntegerFromParams(REQ_EVENT_TYPE_ID, params);
//      et.setId(i);
      return et;
   }

   // ******************************'
   // EventtTemplate
   // ******************************'
//   protected EventTemplate establishEventTemplateSpecificDataInstance(Map<String, String> params, EventTemplate eventTemplate)
//         throws InsufficientInputDataException {
//      // oarent ref
//      Integer eTypeId = extractRequiredIntegerFromParams(REQ_EVENT_TYPE_ID, params);
//
//      // Some date
//      String unit = params.get(REQ_SELECT_UNIT);
//      String doseStr = params.get(REQ_DOSE);
//      String isFavorite = params.get(REQ_IS_FAVORITE);
//      String sortOrderStr = params.get(REQ_SORT_ORDER);
//
//      boolean isFav = BooleanUtils.toBoolean(isFavorite);
//
//      Integer sortO = null;
//      if (StringUtils.isNotBlank(sortOrderStr)) {
//         sortO = Integer.valueOf(sortOrderStr);
//      } else {
//         sortO = 1;
//      }
//
//      BigDecimal dose;
//      try {
//         dose = new BigDecimal(doseStr);
//      } catch (NumberFormatException nfe) {
//         logger.error(doseStr + " did not parse properly.....");
//         throw new EventWebControllerException("dose=" + doseStr + " did not parse properly.....");
//      }
//
//      UNIT u = UNIT.getUnitFromString(unit);
//      if (u == null) {
//         throw new EventWebControllerException(unit + " is not a defined unit");
//      }
//
//      eventTemplate.setParentId(eTypeId);
//      eventTemplate.setDose(doseStr);
//      eventTemplate.setUnit(u);
//      eventTemplate.setFavorite(isFav);
//      eventTemplate.setSortOrder(sortO);
//      return eventTemplate;
//   }

//   protected EventTemplate establishEventTemplateCreateInstance(Map<String, String> params, Integer loginId) throws InsufficientInputDataException {
//      EventTemplate eventTemplate = new EventTemplate();
//      eventTemplate.setLoginId(loginId);
//      eventTemplate = (EventTemplate) addEntityNameValues(eventTemplate, params);
//      eventTemplate = establishEventTemplateSpecificDataInstance(params, eventTemplate);
//      return eventTemplate;
//   }

//   protected EventTemplate establishEventTemplateUpdateInstance(Map<String, String> params, Integer loginId) throws InsufficientInputDataException {
//      EventTemplate eventTemplate = establishEventTemplateCreateInstance(params, loginId);
//
//      Integer i = extractRequiredIntegerFromParams(REQ_EVENT_TEMPLATE_ID, params);
//      eventTemplate.setId(i);
//
//      return eventTemplate;
//   }
//
//   protected EventTemplate establishEventTemplateDeleteInstance(Map<String, String> params, Integer loginId) {
//      EventTemplate et = new EventTemplate();
//      et.setLoginId(loginId);
//
//      Integer i = extractRequiredIntegerFromParams(REQ_EVENT_TEMPLATE_ID, params);
//      et.setId(i);
//      return et;
//   }

   // ******************************'
   // EVENT
   // ******************************'
   protected Event fillEventWithEventTemplateData(EventTemplate template) {
      Event e = new Event();

//      e.setLoginId(template.getLoginId());
//      e.setCreated(LocalDateTime.now());
//      e.setName(template.getName());
//      e.setShortName(template.getShortName());
//      e.setDescription(template.getDescription());
//
//      e.setParentId(template.getId());
//      e.setDose(template.getDose());
//      e.setUnit(template.getUnit());
//
      return e;
   }

   protected Event addEventSpecificData(Map<String, String> params, Event event, Integer login) throws InsufficientInputDataException {

//      Integer parentId = extractRequiredIntegerFromParams(REQ_PARENT_ID, params);
//
//      String dose = params.get(REQ_DOSE);
//      String unit = params.get(REQ_SELECT_UNIT);
//
//
//      String timelineStartDateTime = params.get(REQ_TIMELINE_START_DATE);
//      LocalDateTime eventTS = null;
//
//      if (StringUtils.isNotBlank(timelineStartDateTime)) {
//         eventTS = LocalDateTime.parse(timelineStartDateTime, WebEntityUtil.getDtFormatter());
//      } else {
//         eventTS = LocalDateTime.now();
//      }
//
//      String note = params.get(REQ_NOTE);
//
//      event.setParentId(parentId);
//      event.setDose(dose);
//      event.setUnit(UNIT.getUnitFromString(unit));
//      event.setEventTS(eventTS);
//      event.setNote(note);

      return event;
   }

//   protected Event establishEventCreateInstance(Map<String, String> params, Integer loginId) throws InsufficientInputDataException {
//      Event event = new Event();
//      event = (Event) addEntityNameValues(event, params);
//      event.setLoginId(loginId);
//      event.setCreated(LocalDateTime.now());
//
//      return event;
//   }

//   protected Event establishEventUpdateInstance(Map<String, String> params, Integer loginId) throws InsufficientInputDataException {
//      Event event = establishEventCreateInstance(params, loginId);
//
////      Integer i = extractRequiredIntegerFromParams(REQ_EVENT_ID, params);
////      event.setId(i);
//
//      return event;
//   }

   protected Event establishEventDeleteInstance(Map<String, String> params, Integer loginId) {
      Event et = new Event();
//      et.setLoginId(loginId);
//
//      Integer i = extractRequiredIntegerFromParams(REQ_EVENT_ID, params);
//      et.setId(i);
      return et;
   }

  
   
//   protected void prepareForSelectEventTemplate(HttpSession session, WebEventSearchInput search, Map<String, String> params) {

//      Integer eventTypeId = WebCommonAPI.getIntegerFromSession(session, SES_SELECTED_EVENT_TYPE_ID);
//
//      EventTemplateCriteria etc = new EventTemplateCriteria();
//      List<Integer> loginIds = search.getLoginIds();
//      for (Integer integer : loginIds) {
//         etc.addLoginId(integer);
//      }
//      etc.addEventTypeId(eventTypeId);
//      List<EventTemplate> list = eventTemplateDao.retrieve(etc, 0);
//      logger.debug("retrieved eventTemplates;" + list);
//
//      if (list != null && list.size() > 0) {
//         WebCommonAPI.addObjectToSession(session, SES_SELECT_EVENT_TEMPLATES, list);
////         session.setAttribute(SES_SELECT_EVENT_TEMPLATES, list);
//      } else {
////         logger.debug("Found no event types on login=" + loginId);
////         session.removeAttribute(SES_SELECT_EVENT_TEMPLATES);
//      }
//      session.removeAttribute(SES_SELECTED_EVENT_TEMPLATE_ID);
//   }

//   protected void updateSelectEventTemplate(HttpSession session, WebEventSearchInput eventSearchInput, Map<String, String> params) {
//
//      String selectedEventTypeInputId = params.get(REQ_SELECT_EVENT_TYPE);
//      String selectedEventTemplateId = params.get(REQ_SELECT_EVENT_TEMPLATE);
//
//      List<EventTemplate> eventTemplates = null;
//
//      if (StringUtils.isNotBlank(selectedEventTypeInputId)) {
//         if (StringUtils.isNotBlank(selectedEventTemplateId)) {
//            Integer selectedEventTypeId = Integer.valueOf(selectedEventTypeInputId);
//            eventSearchInput.setEventTypeIdSelected(selectedEventTypeId);
//
//            Integer selectEventTemplateId = Integer.valueOf(selectedEventTemplateId);
//            eventSearchInput.setEventTemplateIdSelected(selectEventTemplateId);
//
//            // Lookup thetemplates, that are related to the selected types
//            EventTemplateCriteria eTmplCrit = new EventTemplateCriteria();
//            List<Integer> loginIds = eventSearchInput.getLoginIds();
//            for (Integer loginId : loginIds) {
//               eTmplCrit.addLoginId(loginId);
//            }
//            eTmplCrit.addEventTypeId(selectedEventTypeId);
//            eTmplCrit.addId(selectEventTemplateId);
//            eventTemplates = eventTemplateDao.retrieve(eTmplCrit, 0);
//
//            eventSearchInput.setEventTemplates(eventTemplates);
//
//         }
//
//      }

//   }

//  ***************************************************

//   protected void prepareForSelectEventType(HttpSession session, WebEventSearchInput search, Map<String, String> params) {
//      List<Integer> loginIds = search.getLoginIds();
//
//      EventTypeCriteria etc = new EventTypeCriteria();
//      for (Integer integer : loginIds) {
//         etc.addLoginId(integer);
//      }
//
//      List<EventType> selectableEventTypes = eventTypeDao.retrieve(etc, 0);
//
//      search.setEventTypes(selectableEventTypes);
//      search.setEventTypeIdSelected(null);
//
//      search.setEventTemplates(null);
//      search.setEventTemplateIdSelected(null);
//
//   }

//   protected void updateSelectEventType(HttpSession session, WebEventSearchInput search, Map<String, String> params) {
//      Integer selectedEventTypeIdInt = 0;
//      String selectedEventTypeId = params.get(REQ_SELECT_EVENT_TYPE);
//
//      if (StringUtils.isNotBlank(selectedEventTypeId)) {
//         try {
//            selectedEventTypeIdInt = Integer.parseInt(selectedEventTypeId);
//         } catch (NumberFormatException e) {
//            logger.debug("Recieved an un-parseable token in request:" + selectedEventTypeId);
//         }
//      }
//      if (selectedEventTypeIdInt != null && selectedEventTypeIdInt.intValue() > 0) {
//         search.setEventTypeIdSelected(selectedEventTypeIdInt);
//      }

//   }
// ******************************************************************

//   protected void prepareForSelectUnit(HttpSession session, EventSearchInput searchInput, Map<String, String> params) {
//
//      UNIT[] values = UNIT.values();
//      List<UNIT> list = Arrays.asList(values);
//
//      logger.debug("found units;" + list);
//      if (list != null && list.size() > 0) {
//         searchInput.setUnits(list);
//
//      } else {
//         logger.debug("Found no units to use in search");
////        session.removeAttribute(SES_SELECT_EVENT_TYPES);
//      }
//      WebCommonAPI.removeObjectFromSession(session, SES_SELECTED_UNIT);
//   }

//   protected UNIT updateSelectUnit(HttpSession session, Map<String, String> params) {
//
//      UNIT selectedUnit = null;
//      String selectedUnitString = params.get(REQ_SELECT_UNIT);
//      
//      if (StringUtils.isNotBlank(selectedUnitString)) {
//         try {
//            selectedUnit = UNIT.getUnitFromString(selectedUnitString);
//         } catch (NumberFormatException e) {
//            logger.debug("Recieved an un-parseable token in request:" + selectedUnitString);
//         }
//      }

//   protected void updateSelectCount(HttpSession session, WebEventSearchInput searchInput, Map<String, String> params) {
//
//      Integer sesSelectedCount = null;
//      String selectedCountString = params.get(REQ_SELECT_COUNT);
//      if (StringUtils.isNotBlank(selectedCountString)) {
//         try {
//            sesSelectedCount = Integer.parseInt(selectedCountString);
//            searchInput.setSelectedCount(sesSelectedCount);
//         } catch (NumberFormatException e) {
//            throw new EventWebControllerException("Recieved an un-parseable token in request:" + selectedCountString);
//         }
//      }
//
//   }

//   protected void prepareForSelectCount(HttpSession session, Integer loginId, Map<String, String> params) {
//
//      List<Integer> values = new ArrayList<Integer>();
//      for (int i = 1; i < 13; i++) {
//         values.add(i);
//      }
//
//      logger.debug("found counts;" + values);
//      if (values != null && values.size() > 0) {
//         session.setAttribute(SES_SELECT_COUNTS, values);
//      } else {
//         logger.debug("Found no event types on login=" + loginId);
////        session.removeAttribute(SES_SELECT_EVENT_TYPES);
//      }
//      WebCommonAPI.removeObjectFromSession(session, SES_SELECTED_COUNT);
//   }

//   protected void updateSelectTimeslotLength(HttpSession session, EventW searchInput, Map<String, String> params) {
//
//      String selectedTimeslotString = params.get(REQ_SELECT_TIMESLOT_INTERVAL_LENGTH);
//      if (StringUtils.isNotBlank(selectedTimeslotString)) {
//         TIMESLOT_LENGTH timeSlot = TIMESLOT_LENGTH.getTimeSlotFromString(selectedTimeslotString);
//         searchInput.setSelectedInterval(timeSlot);
//      } else {
////         throw new InsufficientInputDataException();
//      }
//
//   }


//   protected void prepareForSelectTimeslot(HttpSession session, EventSearchInput searchInput, Map<String, String> params) {
//      TIMESLOT_LENGTH[] values2 = TIMESLOT_LENGTH.values();
//      List<TIMESLOT_LENGTH> values = Arrays.asList(values2);
//      searchInput.s
//      logger.debug("found counts;" + values);
//      if (values != null && values.size() > 0) 
//         session.setAttribute(SES_SELECT_TIMESLOT_LENGTHS, values);
//      } else {
//         logger.debug("Found no event types on login=" + loginId);
////        session.removeAttribute(SES_SELECT_EVENT_TYPES);
//      }
//      WebCommonAPI.removeObjectFromSession(session, SES_SELECTED_TIMESLOT_LENGTH);
//   }

}
