package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

//import org.springframework.format.datetime.standard.DateTimeFormatterFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.web.controller.api.FavoriteControllerAPI;
import dk.schioler.event.web.entity.WebEvent;
import dk.schioler.event.web.entity.WebLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FavoriteController extends AbstractController implements FavoriteControllerAPI {

   public FavoriteController() {
      super();
   }

   @RequestMapping(value = FAVORITES_SHOW, method = RequestMethod.GET)
   public String favoritesShowGet(Locale locale, Model model, HttpServletRequest request) {
      logger.debug(FAVORITES_SHOW + ", GET,  Requested, locale = " + locale);
      return favoritesShow(locale, model, request);
   }

   @RequestMapping(value = FAVORITES_SHOW, method = RequestMethod.POST)
   public String favoritesShowPost(Locale locale, Model model, HttpServletRequest request) {
      logger.debug(FAVORITES_SHOW + ", POST,  Requested, locale = " + locale);
      return favoritesShow(locale, model, request);
   }

   public String favoritesShow(Locale locale, Model model, HttpServletRequest request) {

      HttpSession session = request.getSession();
      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer ownerId = wl.getOwner().getId();

         EventTemplateCriteria etCrit = new EventTemplateCriteria();
         etCrit.setFavourite(true);
         etCrit.addLoginId(ownerId);
         List<EventTemplate> eventTemplates = eventTemplateDAO.retrieve(etCrit, 0);

         logger.debug("******************************");
         logger.debug("eventTemplate.isFavorite=true:");
         List<WebEvent> preparedEvents = new ArrayList<WebEvent>();
         for (EventTemplate eventTemplate : eventTemplates) {
            logger.debug("et=" + eventTemplate);
            Event e = fillEventWithEventTemplateData(eventTemplate);
            WebEvent we = new WebEvent(e);
               preparedEvents.add(we);
         }

         session.setAttribute(SES_FAVORITES, preparedEvents);

         return FAVORITES_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

//	public static final String HOME_STORE_FAVORITE_SHOW = "home-store-favorite-show.do";

//	@RequestMapping(value = HOME_STORE_FAVORITE_SHOW, method = RequestMethod.GET)
//	public String homeStoreFavoriteShow(@RequestParam Map<String, String> params, Locale locale, Model model,
//			HttpServletRequest request) {
//		logger.debug(HOME_STORE_FAVORITE_SHOW + ", GET,  Requested, locale = " + locale);
//		HttpSession session = request.getSession();
//
//		WebLogin wl = this.isValidLogin(session);
//		if (wl != null) {
//			Integer loginId = wl.getLogin().getId();
//			EventTemplateCriteria crit = new EventTemplateCriteria();
//			crit.addLoginId(loginId);
//			crit.setFavourite(true);
//			List<EventTemplate> list = eventTemplateDAO.retrieve(crit, 0);
//			session.setAttribute("", list);
//			return HOME_JSP;
//		} else {
//			return PUBLIC_LOGIN_JSP;
//		}
//
//	}

   @RequestMapping(value = FAVORITE_SAVE, method = RequestMethod.POST)
   public String favoriteSave(@RequestParam Map<String, String> params, Locale locale, Model model, HttpServletRequest request) {
      logger.debug(FAVORITE_SAVE + ", GET,  Requested, locale = " + locale + ", params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer ownerId = wl.getOwner().getId();

         String templateId = params.get("event-template-id");
         String loginId = params.get("login-id");

         @SuppressWarnings("unchecked")
         List<WebEvent> webEvents = (List<WebEvent>) session.getAttribute(SES_FAVORITES);

         Event e = null;
         for (WebEvent webEvent : webEvents) {
            if (webEvent.getParentId().equals(Integer.valueOf(templateId))) {
               if (webEvent.getLoginId().equals(Integer.valueOf(loginId))) {
                  e = webEvent.getEvent();
                  e.setEventTS(LocalDateTime.now());
                  break;
               }
            }
         }
  
         if (e != null) {
            Event insert = eventDAO.insert(e);
            String msg = "created event=" + insert.getName() + "dose=" + insert.getDose() + ", unit=" + insert.getUnit();
            addToStatus(session, msg);
         } else {
            addToStatus(session, "Could not create event, since no valid Event object were found ");
         }

         return FAVORITES_SHOW;
      } else {
         return PUBLIC_LOGIN_JSP;
      }

   }

//	@RequestMapping(value = "/home-type-select.do", method = RequestMethod.POST)
//	public String selectEventTypePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug("home-type-select.do: params=" + params);
//		
//		String typeIdStr = (String) params.get(REQ_EVENT_TYPE_ID);
//		logger.debug("eventTypeId = " + typeIdStr);
//		
//
//		Integer eT = Integer.valueOf(typeIdStr);
//
//		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT);
//		logger.debug("In typeId=" + eT + ", Found these templates " + eventTemplates);
//
//		HttpSession session = request.getSession();
//		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
//		return "redirect:home.jsp";
//	}

//	@RequestMapping(value = "/home.do", method = RequestMethod.POST)
//	public String homePost(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//		logger.debug("home.do, POST: reqParams=" + params);
//
//		String typeIdStr = (String) params.get(REQ_EVENT_TYPE_ID);
//		logger.debug("Home Page Requested, attribute = " + typeIdStr);
//
//		List<EventType> eventTypes = eventTypeDAO.lookup();
//		Integer eT = Integer.valueOf(typeIdStr);
//		List<EventTemplate> eventTemplates = eventTemplateDAO.getFromEventTypeId(eT);
//
//		HttpSession session = request.getSession();
//		session.setAttribute(SES_EVENT_TYPES, eventTypes);
//		session.setAttribute(SES_EVENT_TEMPLATES, eventTemplates);
//		session.setAttribute(SES_SELECTED_EVENT_TYPE_ID, eT);
//		return "redirect:home.jsp";
//	}

}