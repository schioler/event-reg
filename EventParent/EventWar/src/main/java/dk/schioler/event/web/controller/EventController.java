
package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

import dk.schioler.event.base.EventBaseException;
import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventController extends WebTokensAbstract {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	protected EventTypeDAO eventTypeDAO;

	@Autowired
	protected EventTemplateDAO eventTemplateDAO;

	@Autowired
	protected EventDAO eventDAO;

	@RequestMapping(value = "/event-show.do", method = RequestMethod.GET)
	public String eventGet(Locale locale, Model model, HttpServletRequest request) {
		logger.debug("/event-show.do, GET,  Requested, locale = " + locale);

		
		List<EventType> eventTypes = eventTypeDAO.lookup();

		logger.debug("eventTypes="+eventTypes);
		
		HttpSession session = request.getSession();
		session.setAttribute(SES_EVENT_TYPES, eventTypes);
		session.removeAttribute(SES_EVENT_TEMPLATES);
		session.removeAttribute(SES_SELECTED_EVENT_TYPE_ID);
		
		return "redirect:event.jsp";
	}
	

	@RequestMapping(value = "/event-type-select.do", method = RequestMethod.POST)
	public String selectEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug("event-type-select.do: params=" + params);
		
		String typeIdStr = (String) params.get(REQ_PARAM_EVENT_TYPE_ID);
		logger.debug("eventTypeId = " + typeIdStr);
		
		Integer eT = Integer.valueOf(typeIdStr);

		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT);
		logger.debug("In typeId=" + eT + ", Found these templates " + eventTemplates);

		HttpSession session = request.getSession();
		session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
		return "redirect:event.jsp";
	}

	




	@RequestMapping(value = "/event-insert.do", method = RequestMethod.POST)
	public String eventStoreQ(@RequestParam Map<String, String> params, Model model,
			HttpServletRequest httpServletRequest) {
		logger.debug("eventStoreQ: RequestParams params = " + params);

		List<String> errors = new ArrayList<String>();
		Event event = createEvent(params, errors);

		Event inserted = eventDAO.insert(event);
		List<Event> eventsInserted = new ArrayList<Event>();
		eventsInserted.add(inserted);

		HttpSession session = httpServletRequest.getSession();

		session.setAttribute(SES_EVENTS_INSERTED, eventsInserted);
		
		return "redirect:event.jsp";
	}

	public static Event createEvent(Map<String, String> params, List<String> errors) {
		String templIdStr = params.get("templateId");
		Integer tmplId = null;
		try {
			tmplId = Integer.parseInt(templIdStr);
		} catch (Exception e) {
			errors.add("templateId: " + e.getMessage());
		}
		String name = params.get("name");
		String shortName = params.get("shortName");
		String note = params.get("note");
		String dose = params.get("dose");
		String unit = params.get("unit");

		String dateStr = params.get("date");
		String timeStr = params.get("time");

		LocalDateTime eventCustomDateTime = null;

		if (StringUtils.isNotBlank(dateStr) && StringUtils.isNotBlank(timeStr)) {
			try {
				// Date yyyy-mm-dd
				// Time hh:mi
				String tdStr = dateStr + "T" + timeStr + ":00";
//				logger.debug("da - ti str" + tdStr);
				eventCustomDateTime = LocalDateTime.parse(tdStr, DateTimeFormatter.ISO_DATE_TIME);
//				logger.debug("parsedTime=" + eventCustomDateTime.toString());
			} catch (Exception e) {
				errors.add("customTime: " + e.getMessage());
				throw new EventBaseException(e.getMessage(), e);
			}
		}

		Event event = new Event();// null, tmplId, note, eventCustomDateTime);
		event.setEventTemplateId(tmplId);
		event.setEventTS(eventCustomDateTime);
		event.setName(name);
		event.setShortName(shortName);
		event.setNote(note);
		event.setDose(dose);
		event.setUnit(unit);
		return event;
	}

}
