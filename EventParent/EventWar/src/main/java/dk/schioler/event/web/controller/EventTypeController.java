package dk.schioler.event.web.controller;

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
import dk.schioler.event.web.WebLogin;
import dk.schioler.event.web.controller.exception.EventWebControllerException;
import dk.schioler.event.web.controller.exception.EventWebInsufficientParameterValuesException;
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
public class EventTypeController extends AbstractController {


	@RequestMapping(value = EVENT_TYPE_LIST_SHOW, method = RequestMethod.GET)
	public String showEventTypeListGet(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TYPE_LIST_SHOW + ": params=" + params);
		
		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			session.removeAttribute(SES_STATUS_MSG_LIST);
			EventTypeCriteria crit = new EventTypeCriteria();
			crit.addLoginId(wl.getOwner().getId());

			List<EventType> eventTypes = eventTypeDAO.retrieve(crit, 0);
			session.setAttribute(SES_EVENT_TYPES, eventTypes);
			session.removeAttribute(SES_EVENT_TYPE);

			return EVENT_TYPE_LIST_JSP;
		} else {
			resetStatus(session);
			return PUBLIC_LOGIN_JSP;
		}

	}

	@RequestMapping(value = EVENT_TYPE_CREATE_SHOW, method = RequestMethod.POST)
	public String eventTypeCreateShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TYPE_CREATE_SHOW + ": params=" + params);
		HttpSession session = request.getSession();

		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null && wl.isAuthenticated()) {
			// we want to create new
			session.removeAttribute(SES_EVENT_TYPE);

			return EVENT_TYPE_CREATE_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}

	}

	@RequestMapping(value = EVENT_TYPE_CREATE, method = RequestMethod.POST)
	public String createEventTypePost(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TYPE_CREATE + ": params=" + params);
		HttpSession session = request.getSession();

		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			EventType eventTypeInstance = this.createEventTypeInstance(params, loginId);

			eventTypeInstance = eventTypeDAO.insert(eventTypeInstance);

			EventTypeCriteria c = new EventTypeCriteria();
			c.addLoginId(wl.getOwner().getId());

			List<EventType> typeList = eventTypeDAO.retrieve(c, 0);
			session.setAttribute(SES_EVENT_TYPES, typeList);
			session.removeAttribute(SES_EVENT_TYPE);

			return EVENT_TYPE_LIST_JSP;

		} else {
			return PUBLIC_LOGIN_JSP;
		}

	}

//	"redirect:event-type-update.jsp"
	@RequestMapping(value = EVENT_TYPE_UPDATE_SHOW, method = RequestMethod.POST)
	public String eventTypeUpdateShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TYPE_UPDATE_SHOW + ": params=" + params);

		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);

		if (wl != null && wl.isAuthenticated()) {
			String selectedEventTypeId = params.get("id");
			logger.debug("received an ID, will prepare for update");

			// we want to update
			Integer id = Integer.parseInt(selectedEventTypeId);

			EventTypeCriteria etC = new EventTypeCriteria();
			etC.setLoginId(Collections.singletonList(wl.getOwner().getId()));
			etC.addId(id);
			List<EventType> eventTypes = eventTypeDAO.retrieve(etC, 0);

			if (eventTypes != null && eventTypes.size() == 1) {
				EventType eventType = eventTypes.get(0);
				session.setAttribute(SES_EVENT_TYPE, eventType);
			} else {
				throw new EventWebControllerException(
						"Did not recieve exactly one eventType, when attempting to lookup");
			}

			return EVENT_TYPE_UPDATE_JSP;

		} else {
			return PUBLIC_LOGIN_JSP;
		}

	}

	@RequestMapping(value = EVENT_TYPE_UPDATE, method = RequestMethod.POST)
	public String eventTypeUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(EVENT_TYPE_UPDATE + " params=" + params);
		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			EventType eventTypeInstance = this.createEventTypeInstance(params, loginId);
			int update = eventTypeDAO.update(eventTypeInstance);
			logger.debug("updated " + update + " row(s)");

			EventTypeCriteria crit = new EventTypeCriteria();
			crit.setLoginId(Collections.singletonList(loginId));
			List<EventType> list = eventTypeDAO.retrieve(crit, 0);

			session.setAttribute(SES_EVENT_TYPES, list);
			session.removeAttribute(SES_EVENT_TYPE);

			return EVENT_TYPE_LIST_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}

	}

	public static final String EVENT_TYPE_DELETE_JSP = "redirect:event-type-delete.jsp";

	@RequestMapping(value = EVENT_TYPE_DELETE_SHOW, method = RequestMethod.POST)
	public String eventTypeDeleteShow(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest request) {
		logger.debug(EVENT_TYPE_DELETE_SHOW + ": params=" + params);
		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();
			String id = params.get("id");
			if (StringUtils.isBlank(id)) {
				throw new EventWebInsufficientParameterValuesException("received insufficient value for id=" + id);
			}
//			
			EventType eventType = eventTypeDAO.get(Integer.valueOf(id), loginId);

			session.setAttribute(SES_EVENT_TYPE, eventType);

			return EVENT_TYPE_DELETE_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}

	}

	@RequestMapping(value = EVENT_TYPE_DELETE, method = RequestMethod.POST)
	public String eventTypeDelete(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(EVENT_TYPE_DELETE + ": params=" + params);

		HttpSession session = request.getSession();
		WebLogin wl = this.getAuthenticatedLogin(session);
		if (wl != null) {
			Integer loginId = wl.getOwner().getId();

			String id = params.get("id");

			eventTypeDAO.delete(Integer.valueOf(id), loginId);
			
			EventTypeCriteria c = new EventTypeCriteria();
			c.setLoginId(Collections.singletonList(loginId));
			
			List<EventType> typeList = eventTypeDAO.retrieve(c, 0);

			session.setAttribute(SES_EVENT_TYPES, typeList);
			session.removeAttribute(SES_EVENT_TYPE);

			return EVENT_TYPE_LIST_JSP;
		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

}
