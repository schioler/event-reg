package dk.schioler.event.web.controller;

import org.springframework.stereotype.Controller;

@Controller
public class SelectController extends AbstractController {

//   @Autowired
//   protected EventTemplateDAO eventTemplateDAO;
//
//   @Autowired
//   protected EventTypeDAO eventTypeDAO;
//
//   @Autowired
//   protected EventDAO eventDAO;

//   @RequestMapping(value = SECURE + SLASH + SELECT_TIMESLOT_LENGTH_UPDATE_DO, method = RequestMethod.POST)
//   public String timeslotLengthSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + SELECT_TIMESLOT_LENGTH_UPDATE_DO+ ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
////         Login owner = wl.getOwner();        
//         
//         WebEventSearchInput eventSearchInput = (WebEventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_EVENT_SEARCH_INPUT);
//         
//         
//         updateSelectTimeslotLength(session, eventSearchInput, params);
//         updateTimelineStart(session, eventSearchInput, params);
//         
//         String caller = params.get(CALLER);
//         if (StringUtils.isNotBlank(caller)) {
//            return REDIRECT + SLASH + SECURE + SLASH + caller;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
//         }
//      } else {
//         return REDIRECT + SLASH;
//      }
//   }

//   @RequestMapping(value = SECURE + SLASH + SELECT_UNIT_UPDATE_DO, method = RequestMethod.POST)
//   public String unitSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + SELECT_UNIT_UPDATE_DO + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         
////         WebEventSearchInput eventSearchInput = (WebEventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_EVENT_SEARCH_INPUT);
////         String string = params.get(REQ_SELECT_UNIT);
//         
//         
//         UNIT updateSelectUnit = updateSelectUnit(session, params);
//         session.setAttribute(SES_SELECTED_UNIT, updateSelectUnit);
//         
//         String caller = params.get(CALLER);
//         if (StringUtils.isNotBlank(caller)) {
//            return REDIRECT + SLASH + SECURE + SLASH + caller;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
//         }
//      } else {
//         return REDIRECT + SLASH;
//      }
//   }

//   @RequestMapping(value = SECURE + SLASH + SELECT_EVENT_TEMPLATE_UPDATE_DO, method = RequestMethod.POST)
//   public String eventTemplateSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + SELECT_UNIT_UPDATE_DO + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         WebEventSearchInput eventSearchInput = (WebEventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_EVENT_SEARCH_INPUT);
//         
////         Login owner = wl.getOwner();        
//         updateSelectEventTemplate(session, eventSearchInput, params);
////         updateTimelineStart(session, eventSearchInput, params);
//         
//         String caller = params.get(CALLER);
//         if (StringUtils.isNotBlank(caller)) {
//            return REDIRECT + SLASH + SECURE + SLASH + caller;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
//         }
//      } else {
//         return REDIRECT + SLASH;
//      }
//   }

   /*
    * Updates selectEventTypes based on selectParameter AND establish
    * selectEventTemplates.
    */
//   @RequestMapping(value = SECURE + SLASH + SELECT_EVENT_TYPE_UPDATE_DO, method = RequestMethod.POST)
//   public String eventTypeSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + SELECT_EVENT_TYPE_UPDATE_DO + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
//         Login owner = wl.getOwner();
//         WebEntityEventSearchInput eventSearchInput = (WebEntityEventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_EVENT_SEARCH_INPUT);
////         updateTimelineStart(session, eventSearchInput, params);
//         
//         updateSelectEventType(session, eventSearchInput, params);         
//         prepareForSelectEventTemplate(session, eventSearchInput, params);
//         String caller = params.get(CALLER);
//         if (StringUtils.isNotBlank(caller)) {
//            return REDIRECT + SLASH + SECURE + SLASH + caller;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
//         }
//      } else {
//         return REDIRECT + SLASH;
//      }
//   }

//   @RequestMapping(value = SECURE + SLASH + SELECT_COUNT_UPDATE_DO, method = RequestMethod.POST)
//   public String countSelectUpdate(@RequestParam Map<String, String> params, Model model, HttpServletRequest request, HttpServletResponse response) {
//      logger.debug(SECURE + SLASH + SELECT_COUNT_UPDATE_DO + ": RequestParams params = " + params);
//      HttpSession session = request.getSession();
//      WebLogin wl = WebCommonAPI.getAuthenticatedLogin(session);
//      if (wl != null) {
////         Login owner = wl.getOwner();        
//         
//         WebEventSearchInput eventSearchInput = (WebEventSearchInput) WebCommonAPI.getObjectFromSession(session, SES_EVENT_SEARCH_INPUT);
//         
//         updateSelectCount(session, eventSearchInput, params);
//         updateTimelineStart(session, eventSearchInput, params);
//         
//         
//         String caller = params.get(CALLER);
//         if (StringUtils.isNotBlank(caller)) {
//            return REDIRECT + SLASH + SECURE + SLASH + caller;
//         } else {
//            return REDIRECT + SLASH + SECURE + SLASH + FAVORITES_JSP;
//         }
//      } else {
//         return REDIRECT + SLASH;
//      }
//   }

}
