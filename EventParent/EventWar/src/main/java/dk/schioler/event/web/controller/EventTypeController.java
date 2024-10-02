package dk.schioler.event.web.controller;

import java.util.List;
import java.util.Map;

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
import dk.schioler.event.base.entity.EventType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * EventType has these main views - EventTypeList - EventType
 * 
 * You can 1: show create -> from List, data removed from session, entity.jsp,
 * user enters data, save.do, show updated list 2: show update -> from list,
 * req: entityId, data retrieved and entered in session, entity.jsp, user
 * updates data, save.do, show updated list 3: delete -> from list, d
 * 
 * 
 */
@Controller
public class EventTypeController extends WebTokensAbstract {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected EventTypeDAO eventTypeDAO;

	@Autowired
	protected EventTemplateDAO eventTemplateDAO;

	public void verifySessionAttributes(HttpSession session) {
		@SuppressWarnings("unchecked")
		List<EventType> typeList = (List<EventType>) session.getAttribute(SES_EVENT_TYPES);
		if (typeList == null) {
			typeList = eventTypeDAO.lookup();
		}
		session.setAttribute(SES_EVENT_TYPES, typeList);
	}

	@RequestMapping(value = REQ_EVENT_TYPE_LIST_SHOW, method = RequestMethod.GET)
	public String showEventTypeListGet(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("event-type-list-show.do: params=" + params);

		List<EventType> lookup = eventTypeDAO.lookup();
		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TYPES, lookup);
		session.removeAttribute(SES_EVENT_TYPE);
		return "redirect:event-type-list.jsp";

	}

	@RequestMapping(value = REQ_EVENT_TYPE_CREATE_SHOW, method = RequestMethod.POST)
	public String eventTypeCreateShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("event-type-show.do: params=" + params);

		// we want to create new
//			session.removeAttribute(SES_EVENT_TYPE);
		logger.debug("received no ID, will prepare for create");

		return "redirect:event-type-create.jsp";

	}

	@RequestMapping(value = REQ_EVENT_TYPE_CREATE, method = RequestMethod.POST)
	public View createEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug("event-type-create.do: params=" + params);

		String name = params.get("name");
		String shortName = params.get("shortName");
		String description = params.get("description");

		EventType eventType = new EventType();
		eventType.setName(name);
		eventType.setShortName(shortName);
		eventType.setDescription(description);
		eventTypeDAO.insert(eventType);
		eventTypeDAO.refreshCache();
		List<EventType> typeList = eventTypeDAO.lookup();
		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TYPES, typeList);
		session.removeAttribute(SES_EVENT_TYPE);

		RedirectView view = new RedirectView("event-type-list.jsp");

		return view;
	}

	@RequestMapping(value = REQ_EVENT_TYPE_UPDATE_SHOW, method = RequestMethod.POST)
	public String eventTypeUpdateShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug("event-type-update-show.do: params=" + params);

		HttpSession session = request.getSession();

		String selectedEventTypeId = params.get("id");

		// we want to update
		Integer id = Integer.parseInt(selectedEventTypeId);
		EventType eventType = eventTypeDAO.get(id);

		session.setAttribute(SES_EVENT_TYPE, eventType);
		logger.debug("received an ID, will prepare for update");

		return "redirect:event-type-update.jsp";

	}

	@RequestMapping(value = REQ_EVENT_TYPE_UPDATE, method = RequestMethod.POST)
	public String eventTypeUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug("event-type-update.do: params=" + params);

		String id = params.get("id");
		String name = params.get("name");
		String shortName = params.get("shortName");
		String description = params.get("description");

		EventType eventType = eventTypeDAO.get(Integer.valueOf(id));
		eventType.setName(name);
		eventType.setShortName(shortName);
		eventType.setDescription(description);
		eventTypeDAO.update(eventType);
		eventTypeDAO.refreshCache();
		List<EventType> typeList = eventTypeDAO.lookup();
		HttpSession session = request.getSession();

		session.setAttribute(SES_EVENT_TYPES, typeList);
		session.removeAttribute(SES_EVENT_TYPE);

		return "redirect:event-type-list.jsp";
	}

	@RequestMapping(value = REQ_EVENT_TYPE_DELETE_SHOW, method = RequestMethod.POST)
	public String eventTypeDeleteShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(REQ_EVENT_TYPE_DELETE_SHOW + ": params=" + params);

		String id = params.get("id");

//		eventTypeDAO.delete(Integer.valueOf(id));
//		eventTypeDAO.refreshCache();
//		List<EventType> typeList = eventTypeDAO.lookup();
		EventType eventType = eventTypeDAO.get(Integer.valueOf(id));
		HttpSession session = request.getSession();

		session.setAttribute(SES_EVENT_TYPE, eventType);

		return "redirect:event-type-delete.jsp";
	}

	@RequestMapping(value = REQ_EVENT_TYPE_DELETE, method = RequestMethod.POST)
	public String eventTypeDelete(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(REQ_EVENT_TYPE_DELETE + ": params=" + params);

		String id = params.get("id");

		eventTypeDAO.delete(Integer.valueOf(id));
		eventTypeDAO.refreshCache();
		List<EventType> typeList = eventTypeDAO.lookup();

		HttpSession session = request.getSession();

		session.setAttribute(SES_EVENT_TYPES, typeList);
		session.removeAttribute(SES_EVENT_TYPE);

		return "redirect:event-type-list.jsp";
	}

}
