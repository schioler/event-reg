package dk.schioler.event.web.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.web.common.WebCommonAPI;
import dk.schioler.event.web.common.WebLogin;
import dk.schioler.event.web.entity.WebEntitySymbolsShared;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class FavoriteController extends AbstractController implements WebEntitySymbolsShared {

   public final static String STATE_ASPECT_LIST_SHOW = "state-aspect-list-show.do";
//
   public final static String FAVORITES_SHOW = "favorites-show.do";
   public final static String FAVORITES_JSP = "40-favorites.jsp";
   
   public final static String FAVORITE_SAVE = "favorites-save.do";
   
   @Autowired
   protected EventTemplateDAO eventTemplateDAO;

   public FavoriteController() {
      super();
   }

//   @RequestMapping(value = SECURE + SLASH + FAVORITES_SHOW, method = RequestMethod.GET)
//   public String favoritesShowGet(Locale locale, Model model, HttpServletRequest request) {
//      logger.debug(SECURE + SLASH + FAVORITES_SHOW + ", GET,  Requested, locale = " + locale);
//      return favoritesShow(locale, model, request);
//   }
//
//   @RequestMapping(value = SECURE + SLASH + FAVORITES_SHOW, method = RequestMethod.POST)
//   public String favoritesShowPost(Locale locale, Model model, HttpServletRequest request) {
//      logger.debug(SECURE + SLASH + FAVORITES_SHOW + ", POST,  Requested, locale = " + locale);
//      return favoritesShow(locale, model, request);
//   }

   @RequestMapping(value = SECURE + SLASH + FAVORITES_SHOW, method = RequestMethod.GET)
   public String favoritesShow(Locale locale, Model model, HttpServletRequest request) {
      logger.debug("favoritesShow");
      HttpSession session = request.getSession();
      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer ownerId = wl.getOwner().getId();

         EventTemplateCriteria etCrit = new EventTemplateCriteria();
         etCrit.setFavourite(true);
         etCrit.addLoginId(ownerId);

         List<EventTemplate> eventTemplates = eventTemplateDAO.retrieve(etCrit, 0);
         EventTemplate et = eventTemplates.get(0);
         et.getParentId();

         logger.debug("******************************");
         logger.debug("sesFavorites=" + eventTemplates);
         logger.debug("******************************");

         session.setAttribute(SES_FAVORITES, eventTemplates);

         return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;

      } else {
         return SLASH;
      }
   }

//	public static final String HOME_STORE_FAVORITE_SHOW = "home-store-favorite-show.do";

//	@RequestMapping(value = HOME_STORE_FAVORITE_SHOW, <method = RequestMethod.GET)
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

   @RequestMapping(value = SECURE + SLASH + FAVORITE_SAVE, method = RequestMethod.POST)
   public String favoriteSave(@RequestParam Map<String, String> params, Locale locale, Model model, HttpServletRequest request) {
      logger.debug(SECURE + SLASH + FAVORITE_SAVE + ", GET,  Requested, locale = " + locale + ", params=" + params);
      HttpSession session = request.getSession();

      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
      if (wl != null) {
         Integer ownerId = wl.getOwner().getId();

         String templateId = params.get("event-template.id");
         String loginId = params.get("login.id");

         @SuppressWarnings("unchecked")
         List<EventTemplate> events = (List<EventTemplate>) session.getAttribute(SES_FAVORITES);

         EventTemplate eventTemplate = null;
         for (EventTemplate tmpl : events) {
            if (tmpl.getId().equals(Integer.valueOf(templateId))) {
               if (tmpl.getLoginId().equals(Integer.valueOf(loginId))) {
                  eventTemplate = tmpl;

                  break;
               }
            }
         }

         if (eventTemplate != null) {
            Event event = this.fillEventWithEventTemplateData(eventTemplate);
            event.setEventTS(LocalDateTime.now());
            Event insert = eventDAO.insert(event);
            String msg = "created event=" + insert.getName() + "dose=" + insert.getDose() + ", unit=" + insert.getUnit();
            WebCommonAPI.statusAdd(session, msg);
         } else {
            WebCommonAPI.statusAdd(session, "Could not create event, since no valid Event object were found ");
         }

         return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
      } else {
         return SLASH;
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