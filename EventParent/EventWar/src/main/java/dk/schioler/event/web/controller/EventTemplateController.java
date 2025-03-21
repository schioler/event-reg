package dk.schioler.event.web.controller;

import java.util.List;
import java.util.Map;

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
import dk.schioler.event.web.controller.api.EventTemplateControllerAPI;
import dk.schioler.event.web.controller.exception.EventWebInsufficientParameterValuesException;
import dk.schioler.event.web.entity.WebLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventTemplateController extends AbstractController implements EventTemplateControllerAPI {


   List<EventType> retrieveEventTypesAndTemplates(Integer ownerId){
      EventTypeCriteria eTypeCrit = new EventTypeCriteria();
      eTypeCrit.addLoginId(ownerId);

      List<EventType> typeList = eventTypeDAO.retrieve(eTypeCrit, 0);
      for (EventType eventType : typeList) {
         EventTemplateCriteria etc = new EventTemplateCriteria();
         etc.addEventTypeId(eventType.getId());
         List<EventTemplate> tmplList = eventTemplateDAO.retrieve(etc, 0);
         for (EventTemplate tmpl : tmplList) {
            eventType.addChild(tmpl);
         }
      }
      return typeList;
   }
   
   
   @RequestMapping(value = EVENT_TEMPLATE_LIST_SHOW, method = RequestMethod.GET)
   public String eventTmplListShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TEMPLATE_LIST_SHOW + ": params=" + params);

      HttpSession session = request.getSession();
      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         
         List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
         
         session.setAttribute(SES_EVENT_TYPES, eventTypes);
         session.removeAttribute(SES_EVENT_TYPE);
         session.removeAttribute(SES_EVENT_TEMPLATES);
         session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);

         return EVENT_TEMPLATE_LIST_JSP;

      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

//
//   @RequestMapping(value = EVENT_TEMPLATE_TYPE_SELECT, method = RequestMethod.POST)
//   public String selectEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug(EVENT_TEMPLATE_TYPE_SELECT + ": params=" + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = this.getAuthenticatedLogin(session);
//      if (wl != null) {
//         Integer loginId = wl.getOwner().getId();
//
//         String typeIdStr = (String) params.get(REQ_EVENT_TYPE_ID);
//         logger.debug("eventTypeId = " + typeIdStr);
//
//         Integer eT = Integer.valueOf(typeIdStr);
//
//         List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT, loginId);
//
//         logger.debug("In typeId=" + eT + ", Found these templates ");
//
//         for (EventTemplate eventTemplate : eventTemplates) {
//            logger.debug("eTmpl=" + eventTemplate);
//         }
//
//         session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
//         session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//
//         return EVENT_TEMPLATE_LIST_JSP;
//
//      } else {
//         return PUBLIC_LOGIN_JSP;
//      }
//   }

   @RequestMapping(value = EVENT_TEMPLATE_CREATE_SHOW, method = RequestMethod.POST)
   public String eventTmplCreateShowGet(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TEMPLATE_CREATE_SHOW + ": params=" + params);
      
      HttpSession session = request.getSession();
      
      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         Integer eventTypeId = null;
          
         
         String eventTypeIdStr = params.get(PAR_EVENT_TYPE_ID);
         if (StringUtils.isNotBlank(eventTypeIdStr)) {
            try {
               eventTypeId = Integer.valueOf(eventTypeIdStr);
            } catch (NumberFormatException nfe) {
               logger.error(nfe.getMessage(), nfe);
               throw new EventWebInsufficientParameterValuesException(nfe.getMessage(), nfe);
            }
         } else {
            logger.error("required parameter " + PAR_EVENT_TYPE_ID + " was not provided");
            throw new EventWebInsufficientParameterValuesException("required parameter " + PAR_EVENT_TYPE_ID + " was not provided");
         }

         session.setAttribute(SES_EVENT_TYPE_ID, eventTypeId);
         session.removeAttribute(SES_EVENT_TEMPLATE);
         
         List<UNIT> selectableUnits = this.getSelectableUnits();
         session.setAttribute(SES_SELECTABLE_UNITS, selectableUnits);
         session.removeAttribute(SES_SELECTED_UNIT);
         // we want to create new

         return EVENT_TEMPLATE_CREATE_JSP;

      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

   @RequestMapping(value = EVENT_TEMPLATE_CREATE, method = RequestMethod.POST)
   public String createEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TEMPLATE_CREATE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
//         Integer eventTypeId =  (Integer) session.getAttribute(SES_SELECTED_EVENT_TYPE_ID);
         EventTemplate et = establishEventTemplateCreateInstance(params, loginId);

         et = eventTemplateDAO.insert(et);
         
         addToStatus(session, "Created EventTemplate: name="+et.getName()+", and Id="+et.getId());
         
         EventTemplateCriteria etC = new EventTemplateCriteria();
         etC.addLoginId(loginId);
         
         List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
         
         session.setAttribute(SES_EVENT_TYPES, eventTypes);

//			session.removeAttribute(SES_EVENT_TYPE);

         return EVENT_TEMPLATE_LIST_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

   @RequestMapping(value = EVENT_TEMPLATE_UPDATE_SHOW, method = RequestMethod.POST)
   public String eventTmplUpdateShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TEMPLATE_UPDATE_SHOW + ": params=" + params);
      HttpSession session = request.getSession();
      resetStatus(session);
      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
         String templateIdStr = params.get(PAR_EVENT_TEMPLATE_ID);
         
         Integer etId = Integer.valueOf(templateIdStr);
         EventTemplate eventTemplateDb = eventTemplateDAO.get(etId, loginId);
         logger.debug("retrieved et from db="+eventTemplateDb);
         if (eventTemplateDb != null) {
            session.setAttribute(SES_EVENT_TEMPLATE, eventTemplateDb);
            List<UNIT> selectableUnits = this.getSelectableUnits();
            session.setAttribute(SES_SELECTABLE_UNITS, selectableUnits);   
         } else {
            String errorTemplateNotFound = "Can not update eventTemplate, since there was nothing to update";
            addToStatus(session, errorTemplateNotFound);
            return EVENT_TEMPLATE_LIST_JSP;
         }

         return EVENT_TEMPLATE_UPDATE_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }

   }

   @RequestMapping(value = EVENT_TMPL_UPDATE, method = RequestMethod.POST)
   public String eventTmplUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TMPL_UPDATE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = this.getAuthenticatedLogin(session);
      logger.debug("weblogin=" + wl);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();
//         Integer eventTypeId = (Integer) session.getAttribute(SES_SELECTED_EVENT_TYPE_ID);
         
         EventTemplate et = establishEventTemplateUpdateInstance(params, loginId);
         logger.debug("from params: " + et );
         
         eventTemplateDAO.update(et);

         // refresh
//         List<EventTemplate> tmplList = eventTemplateDAO.getFromEventTypeId(et.getParentId(), loginId);
//
//         session.setAttribute(SES_EVENT_TEMPLATES, tmplList);
//         session.removeAttribute(SES_EVENT_TEMPLATE);

         List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
         session.setAttribute(SES_EVENT_TYPES, eventTypes);

         
         return EVENT_TEMPLATE_LIST_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

   @RequestMapping(value = EVENT_TEMPLATE_DELETE_SHOW, method = RequestMethod.POST)
   public String eventTmplDeleteShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TEMPLATE_DELETE_SHOW + ": params=" + params);
      HttpSession session = request.getSession();
      resetStatus(session);
      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) { 
         Integer loginId = wl.getOwner().getId();
         String tmplId = params.get(PAR_EVENT_TEMPLATE_ID);
         if (StringUtils.isNotBlank(tmplId)) {
            Integer id = Integer.valueOf(tmplId);
            EventTemplate eventTemplate = eventTemplateDAO.get(id, loginId);
            session.setAttribute(SES_EVENT_TEMPLATE, eventTemplate);
            return EVENT_TEMPLATE_DELETE_JSP;
         } else {
            throw new EventWebInsufficientParameterValuesException(EVENT_TEMPLATE_DELETE_SHOW + ", trying to parse "+ PAR_EVENT_TEMPLATE_ID +" from req - didn't work");
         }

      } else {
         return PUBLIC_LOGIN_JSP;
      }

   }

   @RequestMapping(value = EVENT_TEMPLATE_DELETE, method = RequestMethod.POST)
   public String deleteEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
      logger.debug(EVENT_TEMPLATE_DELETE + ": params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer loginId = wl.getOwner().getId();

         EventTemplate et = establishEventTemplateDeleteInstance(params, loginId);
         int delete = eventTemplateDAO.delete(et.getId(), loginId);
         
         addToStatus(session, "deleted template");
         logger.debug("Deleted " + delete + " eventTemplates");
            
            List<EventType> eventTypes = retrieveEventTypesAndTemplates(loginId);
            
            session.setAttribute(SES_EVENT_TYPES, eventTypes);

         return EVENT_TEMPLATE_LIST_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

}


