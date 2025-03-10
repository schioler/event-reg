package dk.schioler.event.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.criteria.EventTypeCriteria;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.WebLogin;
import dk.schioler.event.web.controller.exception.EventWebInsufficientParameterValuesException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventTemplateController extends AbstractController {

	@RequestMapping(value = EVENT_TMPL_LIST_SHOW, method = RequestMethod.GET)
	public String eventTmplListShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(EVENT_TMPL_LIST_SHOW + ": params=" + params);

		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			EventTypeCriteria eTypeCrit = new EventTypeCriteria();
			eTypeCrit.addLoginId(loginId);

			List<EventType> lookup = eventTypeDAO.retrieve(eTypeCrit, 0);

			session.setAttribute(SES_EVENT_TYPES, lookup);
			session.removeAttribute(SES_EVENT_TYPE);
			session.removeAttribute(SES_EVENT_TEMPLATES);
			session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);

			return EVENT_TMPL_LIST_JSP;

		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

//
	@RequestMapping(value = EVENT_TMPL_TYPE_SELECT, method = RequestMethod.POST)
	public String selectEventTypePost(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TMPL_TYPE_SELECT + ": params=" + params);
		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			String typeIdStr = (String) params.get(REQ_EVENT_TYPE_ID);
			logger.debug("eventTypeId = " + typeIdStr);

			Integer eT = Integer.valueOf(typeIdStr);

			List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT, loginId);

			logger.debug("In typeId=" + eT + ", Found these templates ");

			for (EventTemplate eventTemplate : eventTemplates) {
				logger.debug("eTmpl=" + eventTemplate);
			}

			session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
			session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);

			return EVENT_TMPL_LIST_JSP;

		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

	@RequestMapping(value = EVENT_TMPL_CREATE_SHOW, method = RequestMethod.POST)
	public String showEventTypeGet(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(EVENT_TMPL_CREATE_SHOW + ": params=" + params);
		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			EventTemplate eventTemplate = createEventTemplateInstance(params, loginId);
			session.setAttribute(SES_EVENT_TEMPLATE, eventTemplate);

			// we want to create new

			return EVENT_TMPL_CREATE_JSP;

		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

	@RequestMapping(value = EVENT_TMPL_CREATE, method = RequestMethod.POST)
	public String createEventTypePost(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TMPL_CREATE + ": params=" + params);
		HttpSession session = request.getSession();

		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			EventTemplate et = createEventTemplateInstance(params, loginId);

			et = eventTemplateDAO.insert(et);

			EventTemplateCriteria etC = new EventTemplateCriteria();
			etC.addLoginId(loginId);
			etC.addEventTypeId(et.getParentId());

			List<EventTemplate> templateList = eventTemplateDAO.retrieve(etC, 0);

			session.setAttribute(SES_EVENT_TEMPLATES, templateList);

//			session.removeAttribute(SES_EVENT_TYPE);

			return EVENT_TMPL_LIST_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

	@RequestMapping(value = EVENT_TMPL_UPDATE_SHOW, method = RequestMethod.POST)
	public String eventTmplUpdateShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TMPL_UPDATE_SHOW + ": params=" + params);
		HttpSession session = request.getSession();
		resetStatus(session);
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();
			String templateIdStr = params.get("event-template-id");
			Integer etId = Integer.valueOf(templateIdStr);
			EventTemplate eventTemplateDb = eventTemplateDAO.get(etId, loginId);
			if(eventTemplateDb != null) {
				session.setAttribute(SES_EVENT_TEMPLATE, eventTemplateDb);
			} else {
				String errorTemplateNotFound = "Can not update eventTemplate, since there was nothing to update";
				addToStatus(session, errorTemplateNotFound);
				return EVENT_TMPL_LIST_JSP;
			}
			
			
			return EVENT_TMPL_UPDATE_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}
		
		// String eventTypeIdStr = params.get("eventTypeId");
//		String shortName = params.get("shortName");
//		String name = params.get("name");
//		String unit = params.get("unit");
//		String dose = params.get("dose");
//		String note = params.get("note");

//		EventTemplate eventTemplate = buildEventTemplateFrom(params);
//		eventTemplate.setId(Integer.valueOf(templateIdStr));
//		eventTemplate.setEventTypeId(Integer.valueOf(eventTypeIdStr));
//		eventTemplate.setName(name);
//		eventTemplate.setShortName(shortName);
//		eventTemplate.setUnit(unit);
//		eventTemplate.setDose(dose);
//		eventTemplate.setDescription(note);
//		logger.debug("eventTemplate:" + eventTemplate);

		// we want to update
		// verifying state...
//		if (eventTemplate.equals(eventTemplateDb)) {
//		session.setAttribute(SES_EVENT_TEMPLATE, eventTemplate);

//		}

		

	}

	@RequestMapping(value = EVENT_TMPL_UPDATE, method = RequestMethod.POST)
	public String eventTmplUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(EVENT_TMPL_UPDATE + ": params=" + params);
		HttpSession session = request.getSession();

		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			EventTemplate et = createEventTemplateInstance(params, loginId);

			eventTemplateDAO.update(et);

			List<EventTemplate> tmplList = eventTemplateDAO.getFromEventTypeId(et.getParentId(), loginId);

			session.setAttribute(SES_EVENT_TEMPLATES, tmplList);
			session.removeAttribute(SES_EVENT_TEMPLATE);

			return EVENT_TMPL_LIST_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

	@RequestMapping(value = EVENT_TMPL_DELETE_SHOW, method = RequestMethod.POST)
	public String eventTmplDeleteShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TMPL_DELETE_SHOW + ": params=" + params);
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
				return EVENT_TMPL_DELETE_JSP;
			} else {
				throw new EventWebInsufficientParameterValuesException(
						EVENT_TMPL_DELETE_SHOW + ", trying to parse ETmpl.id from req - didn't work");
			}

		} else {
			return PUBLIC_LOGIN_JSP;
		}

	}

	@RequestMapping(value = EVENT_TMPL_DELETE, method = RequestMethod.POST)
	public String deleteEventTypePost(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TMPL_DELETE + ": params=" + params);
		HttpSession session = request.getSession();

		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			String id = params.get(PAR_EVENT_TEMPLATE_ID);
			String typeId = params.get(PAR_EVENT_TYPE_ID);
			if (StringUtils.isNotBlank(id)) {
				Integer idInt = Integer.valueOf(id);

				int delete = eventTemplateDAO.delete(idInt, loginId);
				addToStatus(session, "deleted template");
				logger.debug("Deleted " + delete + " eventTemplates");

				List<EventTemplate> typeList = eventTemplateDAO.getFromEventTypeId(Integer.valueOf(typeId), loginId);
				
				session.setAttribute(SES_EVENT_TEMPLATES, typeList);
			} else {
				throw new EventWebInsufficientParameterValuesException("id could not be parsed");
			}

			return EVENT_TMPL_LIST_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

}
