package dk.schioler.event.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dk.schioler.event.base.dao.StateAspectDAO;
import dk.schioler.event.base.dao.StateRatingDAO;
import dk.schioler.event.base.dao.StateRegistrationDAO;
import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.WebLogin;
import dk.schioler.event.web.entity.EventSearchCriteria;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StatusController extends AbstractController {

   @Autowired
   StateAspectDAO stateAspectDAO;
   
   @Autowired
   StateRatingDAO stateRatingDAO;
   
   @Autowired 
   StateRegistrationDAO stateRegistrationDAO;
   
//   public final static String SES_SEARCH_RESULT_START_DATE = "sesSearchResultStartDate";
//   public final static String SES_SEARCH_CRITERIA = "sesSearchResultEndDate";
   public final static String STATE_ASPECT_LIST_SHOW = "state-aspect-list-show.do";
//
//   public static final String STATUS_LIST_SHOW = "/status-list-show.do";
//   public static final String STATUS_TYPE_EDIT_SHOW = "/search-current-show.do";
//   public static final String STATUS_TYPE_SAVE = "/search-current-show.do";
//   public static final String STATUS_TYPE_DELETE = "/search-current-show.do";
//
//   // If needed, show page, where it's is possible to addTemplates to the current
//   // search
//   public static final String SEARCH_ADD_TEMPLATES_SHOW = "/search-add-templates-show.do";
//   public static final String SEARCH_ADD_TEMPLATE = "/search-add-template.do";
//   public static final String SEARCH_REMOVE_TEMPLATE = "/search-remove-template.do";
//
//   public static final String SEARCH_EVENT_TYPE_SELECT = "/search-event-type-select.do";
//
//   public static final String SEARCH = "/search.do";
//
   public static final String SEARCH_JSP = "redirect:/search.jsp";
//
//   public static final String SEARCH_CURRENT_SHOW = "";
//   public static final String SEARCH_TEMPLATES_JSP = "redirect:/search-templates.jsp";
//
//   public static final String SEARCH_RESULT_JSP = "redirect:/search-result.jsp";

   @RequestMapping(value = STATE_ASPECT_LIST_SHOW, method = RequestMethod.GET)
   public String searchNewShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
      logger.debug(STATE_ASPECT_LIST_SHOW + "GET, Requested ");
      HttpSession session = request.getSession();
      WebLogin wl = getAuthenticatedLogin(session);
      if (wl != null) {
         
//         stateAspectDAO.retrieve(null, 0)
//         session.setAttribute(SES_SEARCH_CRITERIA, searchCriteria);
         return SEARCH_JSP;
      } else {
         return PUBLIC_LOGIN_JSP;
      }
   }

//   @RequestMapping(value = SEARCH_CURRENT_SHOW, method = RequestMethod.GET)
//   public String searchCurrentShow(@RequestParam Map<String, String> reqParams, Model model, HttpServletRequest request) {
//      logger.debug(SEARCH_CURRENT_SHOW + "GET, Requested ");
//      HttpSession session = request.getSession();
//      WebLogin wl = getAuthenticatedLogin(session);
//      if (wl != null) {
//         SearchCriteria searchCriteria = (SearchCriteria) session.getAttribute(SES_SEARCH_CRITERIA);
//         if (searchCriteria == null) {
//            searchCriteria = new SearchCriteria();
//            searchCriteria.setLoginId(wl.getOwner().getId());
//         } else {
//            // do no changes
//         }
//
//         session.setAttribute(SES_SEARCH_CRITERIA, searchCriteria);
//         return SEARCH_JSP;
//      } else {
//         return PUBLIC_LOGIN_JSP;
//      }
//   }

//   @RequestMapping(value = SEARCH_ADD_TEMPLATES_SHOW, method = RequestMethod.POST)
//   public String searchAddTemplatesShow(@RequestParam Map<String, String> params, Model model, HttpServletRequest request) {
//      logger.debug(SEARCH_ADD_TEMPLATES_SHOW + ": params=" + params);
//
//      HttpSession session = request.getSession();
//      WebLogin wl = getAuthenticatedLogin(session);
//      if (wl != null) {
//
//         EventTypeCriteria crit = new EventTypeCriteria();
//         crit.addLoginId(wl.getOwner().getId());
//         List<EventType> list = eventTypeDAO.retrieve(crit, 0);
//
//         session.setAttribute(SES_EVENT_TYPES, list);
//         return SEARCH_TEMPLATES_JSP;
//      } else {
//         return PUBLIC_LOGIN_JSP;
//      }
//
//   }

}
