package dk.schioler.event.web.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.EventTypeDAO;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.WebLogin;
import jakarta.servlet.http.HttpSession;

public class AbstractController implements WebTokens {

	@Autowired
	protected EventDAO eventDAO;

	@Autowired
	protected EventTypeDAO eventTypeDAO;

	@Autowired
	protected EventTemplateDAO eventTemplateDAO;

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected WebLogin isValidLogin(HttpSession session) {

		if (session != null) {
			WebLogin webLogin = (WebLogin) session.getAttribute(SES_AUTHENTICATED_USER);

			if (webLogin != null) {
				if (webLogin.isAuthenticated()) {
					return webLogin;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}


	}

	protected EventTemplate buildEventTemplateFrom(Map<String, String> params, Integer loginId) {

		String idStr = params.get("id");
		String eventTypeIdStr = params.get("eventTypeId");
		String name = params.get("name");
		String shortName = params.get("shortName");
		String description = params.get("description");
		String unit = params.get("unit");
		String dose = params.get("dose");
		Integer eventTemplateId =null;
		if (idStr != null) {
			eventTemplateId = Integer.valueOf(idStr);
		}
		Integer eTypeId = null;
		if (eventTypeIdStr != null) {
			eTypeId = Integer.valueOf(eventTypeIdStr);
		} else {
			throw new EventWebInsufficientParameterValuesException("required 'event_type-id' was not provided, when building an EventTemplate instance");
		}

		
		EventTemplate et = new EventTemplate();
		et.setId(eventTemplateId);
		et.setParentId(eTypeId);
		et.setLoginId(loginId);
		et.setName(name);
		et.setShortName(shortName);
		et.setDose(dose);
		et.setUnit(unit);
		et.setDescription(description);

		return et;
	}

	protected EventType createEventTypeInstance(Map<String, String> params , Integer loginId) throws EventWebControllerException {
		Integer id = null;
		String idStr = params.get("id");
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
		String name = params.get("name");
		if (StringUtils.isEmpty(name)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: name parameter missing");
		}
		String shortName = params.get("shortName");
		if (StringUtils.isEmpty(shortName)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: shortName parameter missing");
		}
		
		// optional
		String description = params.get("description");
		

		EventType eventType = new EventType();// null, tmplId, note, eventCustomDateTime);
		eventType.setId(id);
		eventType.setLoginId(loginId);		
		eventType.setName(name);
		eventType.setShortName(shortName);
		eventType.setDescription(description);
		
		return eventType;
	}
	protected Event createEventInstance(Map<String, String> params, Integer loginId) throws EventWebControllerException {
		Integer id = null;
		String idStr = params.get("id");
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
			
		
		String templIdStr = params.get("templateId");

		if (StringUtils.isEmpty(templIdStr)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: templateId parameter missing");
		}

		Integer tmplId = null;
		try {
			tmplId = Integer.parseInt(templIdStr);
		} catch (NumberFormatException e) {
			throw new EventWebInValidParameterValuesException(e.getMessage(), e);
		} catch (Exception e) {
			throw new EventWebControllerException(e.getMessage(), e);
		}

		
		String name = params.get("name");
		if (StringUtils.isEmpty(name)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: name parameter missing");
		}
		String shortName = params.get("shortName");
		if (StringUtils.isEmpty(shortName)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: shortName parameter missing");
		}
		
		// not mandatory, so no verification...
		String note = params.get("note");

		String dose = params.get("dose");
		if (StringUtils.isEmpty(dose)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: dose parameter missing");
		}
		String unit = params.get("unit");
		if (StringUtils.isEmpty(unit)) {
			throw new EventWebInsufficientParameterValuesException("createEventInstance: unit parameter missing");
		}

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
		event.setShortName(shortName);
		event.setNote(note);
		event.setDose(dose);
		event.setUnit(unit);
		return event;
	}

}
