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
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventTemplateController implements WebTokens {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected EventTypeDAO eventTypeDAO;

	@Autowired
	protected EventTemplateDAO eventTemplateDAO;

	@RequestMapping(value = REQ_EVENT_TMPL_LIST_SHOW, method = RequestMethod.GET)
	public String eventTmplListShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_LIST_SHOW + ": params=" + params);

		List<EventType> lookup = eventTypeDAO.lookup();
		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TYPES, lookup);
		session.removeAttribute(SES_EVENT_TYPE);
		session.removeAttribute(SES_EVENT_TEMPLATES);
		session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);

		return "redirect:" + RES_EVENT_TMPL_LIST_JSP;

	}

//
	@RequestMapping(value = REQ_EVENT_TMPL_TYPE_SELECT, method = RequestMethod.POST)
	public String selectEventTypePost(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_TYPE_SELECT + ": params=" + params);

		String typeIdStr = (String) params.get(REQ_PARAM_EVENT_TYPE_ID);
		logger.debug("eventTypeId = " + typeIdStr);

		Integer eT = Integer.valueOf(typeIdStr);

		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT);
		logger.debug("In typeId=" + eT + ", Found these templates ");
		for (EventTemplate eventTemplate : eventTemplates) {
			logger.debug("eTmpl=" + eventTemplate);
		}

		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
		session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);

		return "redirect:" + RES_EVENT_TMPL_LIST_JSP;

	}

	@RequestMapping(value = REQ_EVENT_TMPL_CREATE_SHOW, method = RequestMethod.POST)
	public String showEventTypeGet(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_CREATE_SHOW + ": params=" + params);

		EventTemplate eventTemplate = buildEventTemplateFrom(params);
		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TEMPLATE, eventTemplate);

		// we want to create new

		return "redirect:" + RES_EVENT_TMPL_CREATE_JSP;

	}

	@RequestMapping(value = REQ_EVENT_TMPL_CREATE, method = RequestMethod.POST)
	public View createEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_CREATE + ": params=" + params);

		EventTemplate et = buildEventTemplateFrom(params);

		eventTemplateDAO.insert(et);
		eventTemplateDAO.refreshCache();

		List<EventTemplate> typeList = eventTemplateDAO.getFromEventTypeId(et.getEventTypeId());

		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TEMPLATES, typeList);

//		session.removeAttribute(SES_EVENT_TYPE);

		RedirectView view = new RedirectView(RES_EVENT_TMPL_LIST_JSP);

		return view;
	}

	@RequestMapping(value = REQ_EVENT_TMPL_UPDATE_SHOW, method = RequestMethod.POST)
	public String eventTmplUpdateShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_UPDATE_SHOW + ": params=" + params);

//		String eventTypeIdStr = params.get("eventTypeId");
//		String templateIdStr = params.get("templateId");
//		String shortName = params.get("shortName");
//		String name = params.get("name");
//		String unit = params.get("unit");
//		String dose = params.get("dose");
//		String note = params.get("note");

		EventTemplate eventTemplate = buildEventTemplateFrom(params);
//		eventTemplate.setId(Integer.valueOf(templateIdStr));
//		eventTemplate.setEventTypeId(Integer.valueOf(eventTypeIdStr));
//		eventTemplate.setName(name);
//		eventTemplate.setShortName(shortName);
//		eventTemplate.setUnit(unit);
//		eventTemplate.setDose(dose);
//		eventTemplate.setDescription(note);
		logger.debug("eventTemplate:" + eventTemplate);

		HttpSession session = request.getSession();

		// we want to update
		// verifying state...
//		EventTemplate eventTemplateDb = eventTemplateDAO.get(eventTemplate.getId());
//		if (eventTemplate.equals(eventTemplateDb)) {
		session.setAttribute(SES_EVENT_TEMPLATE, eventTemplate);

//		}

		return "redirect:" + RES_EVENT_TMPL_UPDATE_JSP;

	}

	protected EventTemplate buildEventTemplateFrom(Map<String, String> params) {

		String idStr = params.get("id");
		String eventTypeIdStr = params.get("eventTypeId");
		String name = params.get("name");
		String shortName = params.get("shortName");
		String description = params.get("description");
		String unit = params.get("unit");
		String dose = params.get("dose");

		EventTemplate et = new EventTemplate();
		if (idStr != null) {
			et.setId(Integer.valueOf(idStr));
		}
		if (eventTypeIdStr != null) {
			et.setEventTypeId(Integer.valueOf(eventTypeIdStr));
		}
		et.setName(name);
		et.setShortName(shortName);
		et.setDose(dose);
		et.setUnit(unit);
		et.setDescription(description);

		return et;
	}

	@RequestMapping(value = REQ_EVENT_TMPL_UPDATE, method = RequestMethod.POST)
	public String eventTmplUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_UPDATE + ": params=" + params);

		EventTemplate et = buildEventTemplateFrom(params);

		eventTemplateDAO.update(et);
		eventTemplateDAO.refreshCache();

		List<EventTemplate> typeList = eventTemplateDAO.getFromEventTypeId(et.getEventTypeId());
		HttpSession session = request.getSession();

		session.setAttribute(SES_EVENT_TEMPLATES, typeList);
		session.removeAttribute(SES_EVENT_TEMPLATE);

		return "redirect:" + RES_EVENT_TMPL_LIST_JSP;
	}

	@RequestMapping(value = REQ_EVENT_TMPL_DELETE_SHOW, method = RequestMethod.POST)
	public String EventTmplDeleteShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_DELETE_SHOW + ": params=" + params);

		EventTemplate et = buildEventTemplateFrom(params);
		HttpSession session = request.getSession();

		session.setAttribute(SES_EVENT_TEMPLATE, et);

		return "redirect:" + RES_EVENT_TMPL_DELETE_JSP;

	}

	@RequestMapping(value = REQ_EVENT_TMPL_DELETE, method = RequestMethod.POST)
	public String deleteEventTypePost(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(REQ_EVENT_TMPL_DELETE + ": params=" + params);

		EventTemplate et = buildEventTemplateFrom(params);

		eventTemplateDAO.delete(Integer.valueOf(et.getId()));
		eventTemplateDAO.refreshCache();

		List<EventTemplate> typeList = eventTemplateDAO.getFromEventTypeId(et.getEventTypeId());

		HttpSession session = request.getSession();
//
		session.setAttribute(SES_EVENT_TEMPLATES, typeList);
//		session.removeAttribute(SES_EVENT_TYPE);

		return "redirect:" + RES_EVENT_TMPL_LIST_JSP;
	}

}
