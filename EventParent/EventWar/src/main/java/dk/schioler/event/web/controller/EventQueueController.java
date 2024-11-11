
package dk.schioler.event.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.entity.Event;
import dk.schioler.event.web.WebLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EventQueueController extends AbstractController {
//	Logger logger = LoggerFactory.getLogger(getClass());
//
//	@Autowired
//	protected EventTypeDAO eventTypeDAO;
//
//	@Autowired
//	protected EventTemplateDAO eventTemplateDAO;
//
//	@Autowired
//	protected EventDAO eventDAO;

	/**
	 * QUEUE
	 * 
	 * @param params
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/event-queue-add.do", method = RequestMethod.POST)
	public String eventAddToQueue(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
		logger.debug("event-add-to-queue-do: RequestParams params = " + params);
		HttpSession session = request.getSession();
		WebLogin wl = this.isValidLogin(session);
		if (wl != null) {
			Integer loginId = wl.getLogin().getId();

			List<String> errors = new ArrayList<String>();

			Event event = createEventInstance(params, loginId);

			List<Event> eventsToInsert = new ArrayList<Event>();
			eventsToInsert.add(event);

			@SuppressWarnings("unchecked")
			List<Event> eventQ = (List<Event>) session.getAttribute(SES_EVENT_QUEUE);
			if (eventQ == null) {
				logger.debug("found " + SES_EVENT_QUEUE + " = null");
				eventQ = new ArrayList<Event>();
			} else {
				logger.debug("found " + SES_EVENT_QUEUE + " =" + eventQ);
			}

			eventQ.add(event);

			logger.debug("eventQueue=" + eventQ);
			session.setAttribute(SES_EVENT_QUEUE, eventQ);

			return "redirect:event.jsp";
		} else {
			return PUBLIC_LOGIN_JSP;
		}
	}

	@RequestMapping(value = "/event-queue-store.do", method = RequestMethod.POST)
	public String storeQueue(Locale locale, Model model, HttpServletRequest request) {
		logger.debug("/event-queue-store.do called");

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		List<Event> eventQueue = (List<Event>) session.getAttribute(SES_EVENT_QUEUE);

		List<Event> eventsInserted = new ArrayList<Event>();
		if (eventQueue != null) {
			if (eventQueue.size() > 0) {
				for (Event event : eventQueue) {
					Event insert = eventDAO.insert(event);
					eventsInserted.add(insert);
				}
			} else {
				logger.debug("found no eventQueue in session");
			}
		}
		session.removeAttribute(SES_EVENT_QUEUE);

		session.setAttribute(SES_EVENTS_INSERTED, eventsInserted);
		return "redirect:event.jsp";
	}

	@RequestMapping(value = "/event-queue-remove.do", method = RequestMethod.POST)
	public String removeFromEventQueue(@RequestParam Map<String, String> params, Locale locale, Model model,
			HttpServletRequest request) {
		logger.debug("/eventRemoveFromEventQueue.do called");
		logger.debug("params=" + params.toString());

		String name = params.get("name");
		if (name != null) {
			name = name.trim();
		}

		String templateId = params.get("templateId");
		Integer integer = null;
		if (StringUtils.isNotBlank(templateId)) {
			integer = Integer.parseInt(templateId);
		}
		logger.debug("name=" + name + ", tmplId=" + integer);

		try {
			HttpSession session = request.getSession();

			@SuppressWarnings("unchecked")
			List<Event> eventQueue = (List<Event>) session.getAttribute(SES_EVENT_QUEUE);
			for (int i = 0; i < eventQueue.size(); i++) {
				Event event = eventQueue.get(i);
				logger.debug("inQueue=" + event);
				if (name.equals(event.getName().trim())) {
					if (integer != null && integer.equals(event.getParentId())) {
						logger.debug("removing event from Queue");

						eventQueue.remove(i);
					}

				}
			}
			session.removeAttribute(SES_EVENT_QUEUE);

			session.setAttribute(SES_EVENT_QUEUE, eventQueue);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage(), e);
		}

		return "redirect:event.jsp";

	}

}
