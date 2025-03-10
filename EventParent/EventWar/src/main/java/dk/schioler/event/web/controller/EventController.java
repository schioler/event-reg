
package dk.schioler.event.web.controller;

import java.util.ArrayList;
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

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.web.WebLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventController extends AbstractController {
	public static final String EVENT_SHOW = "/event-show.do";

	public static final String EVENT_JSP = "redirect:event.jsp";

//	public static final String ETYP_REQ_EVENT_TYPE_SHOW = "/event-show.do";

//	public static final String ETYP_RESP_EVENT_TYPE_SHOW = "redirect:event.jsp";

	public static final String EVENT_SAVE = "/event-save.do";

//	@RequestMapping(value = EVENT_SHOW, method = RequestMethod.GET)
//	public String eventGet(Locale locale, Model model, HttpServletRequest request) {
//		logger.debug(EVENT_SHOW + " GET,  Requested, locale = " + locale);
//		HttpSession session = request.getSession();
//		WebLogin webLogin = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//
//		if (webLogin != null) {
//			if (webLogin.isAuthenticated()) {
//				Login login = webLogin.getLogin();
//				EventTypeCriteria criteria = new EventTypeCriteria();
//				
//				criteria.setLoginId(Collections.singletonList(login.getId()));
//				List<EventType> eventTypes = eventTypeDAO.retrieve(criteria, 0);
//
//				logger.debug("eventTypes=" + eventTypes);
//
//				session.setAttribute(SES_EVENT_TYPES, eventTypes);
//				session.removeAttribute(SES_EVENT_TEMPLATES);
//				session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);
//
//				return EVENT_JSP;
//			} else {
//				return PUBLIC_LOGIN_JSP;
//			}
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//	}

//	public static final String ETYP_REQ_EVENT_TYPE_SELECT = "/event-type-select.do";

//	@RequestMapping(value = ETYP_REQ_EVENT_TYPE_SELECT, method = RequestMethod.POST)
//	public String selectEventTypePost(@RequestParam Map<String, String> params, Model model,
//			HttpServletRequest request) {
//		HttpSession session = request.getSession();
//		WebLogin wl = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);
//		if (wl != null) {
//			logger.debug(ETYP_REQ_EVENT_TYPE_SELECT + ": params=" + params);
//
//			String typeIdStr = (String) params.get(REQ_PARAM_EVENT_TYPE_ID);
//			logger.debug("eventTypeId = " + typeIdStr);
//
//			Integer eT = Integer.valueOf(typeIdStr);
//
//			List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT, wl.getLogin().getId());
//			logger.debug("In typeId=" + eT + ", Found these templates " + eventTemplates);
//
//			session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//			session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
//			return EVENT_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//
//		}
//	}

	@RequestMapping(value = EVENT_SAVE, method = RequestMethod.POST)
	public String eventSave(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug(EVENT_SAVE + ": RequestParams params = " + params);
		HttpSession session = request.getSession();
		WebLogin wl = getAuthenticatedLogin(session);
		if (wl != null) {

			Event event = createEventInstance(params,wl.getOwner().getId());
			
			Event inserted = eventDAO.insert(event);
			List<Event> eventsInserted = new ArrayList<Event>();
			eventsInserted.add(inserted);
			
			List<String> msgs = new ArrayList<String>();
			msgs.add("saved Event: " + inserted.getName()+", @"+inserted.getEventTS());
			this.setStatuMsg(session, msgs);
			
			
			return FAVORITES_JSP;
			
		} else {
			return PUBLIC_LOGIN_JSP;

		}


	}

//	public static Event createEventInstance(Map<String, String> params, List<String> errors) {
//		String templIdStr = params.get("templateId");
//		Integer tmplId = null;
//		try {
//			tmplId = Integer.parseInt(templIdStr);
//		} catch (Exception e) {
//			errors.add("templateId: " + e.getMessage());
//		}
//		String name = params.get("name");
//		String shortName = params.get("shortName");
//		String note = params.get("note");
//		String dose = params.get("dose");
//		String unit = params.get("unit");
//
//		String dateStr = params.get("date");
//		String timeStr = params.get("time");
//
//		LocalDateTime eventCustomDateTime = null;
//
//		if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(timeStr)) {
//			try {
//				// Date yyyy-mm-dd
//				// Time hh:mi
//				String tdStr = dateStr + "T" + timeStr + ":00";
////				logger.debug("da - ti str" + tdStr);
//				eventCustomDateTime = LocalDateTime.parse(tdStr, DateTimeFormatter.ISO_DATE_TIME);
////				logger.debug("parsedTime=" + eventCustomDateTime.toString());
//			} catch (Exception e) {
//				errors.add("customTime: " + e.getMessage());
//				throw new EventBaseException(e.getMessage(), e);
//			}
//		}
//
//		Event event = new Event();// null, tmplId, note, eventCustomDateTime);
//		event.setEventTemplateId(tmplId);
//		event.setEventTS(eventCustomDateTime);
//		event.setName(name);
//		event.setShortName(shortName);
//		event.setNote(note);
//		event.setDose(dose);
//		event.setUnit(unit);
//		return event;
//	}

}
