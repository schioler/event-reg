package dk.schioler.event.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.WebLogin;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FavoriteController extends AbstractController {

   public FavoriteController() {
      super();

   }

   public static final String FAVORITE_SAVE = "favorite-save.do";

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
         Integer loginId = wl.getOwner().getId();

         EventTemplateCriteria etCrit = new EventTemplateCriteria();
         etCrit.setFavourite(true);
         etCrit.addLoginId(loginId);
         List<EventTemplate> eventTemplates = eventTemplateDAO.retrieve(etCrit, 0);

         logger.debug("eventTemplates=" + eventTemplates);
         List<EventTemplate> eventTypes = new ArrayList<EventTemplate>();
         for (EventTemplate eventTemplate : eventTemplates) {
            EventType eventType = eventTypeDAO.get(eventTemplate.getParentId(), loginId);
            eventTemplate.setParent(eventType);
            eventTypes.add(eventTemplate);
         }

         session.setAttribute(SES_FAVORITES, eventTypes);

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
   public String favoriteStore(@RequestParam Map<String, String> params, Locale locale, Model model, HttpServletRequest request) {
      logger.debug(FAVORITE_SAVE + ", GET,  Requested, locale = " + locale + ", params="+ params   );
      HttpSession session = request.getSession();

      WebLogin wl = this.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer ownerId = wl.getOwner().getId();

         String templateId = params.get("event-template-id");
         String shortName = params.get("short-name");
         String unit = params.get("unit");
         String dose = params.get("dose");
         String name = params.get("name");

         Event event = new Event();
         event.setName(name);
         event.setDescription(shortName);
         event.setParentId(Integer.valueOf(templateId));
         event.setLoginId(ownerId);
         event.setEventTS(LocalDateTime.now());
         event.setDose(new BigDecimal(dose));
         event.setUnit(UNIT.getUnit(unit));
         Event insert = eventDAO.insert(event);
         
         List<String> list = new ArrayList<String>();
         list.add("added event=" + insert.getName()+"dose="+insert.getDose()+", unit="+insert.getUnit());
         setStatuMsg(session, list);
         
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