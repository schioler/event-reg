package dk.schioler.event.web.controller.api;

import dk.schioler.shared.bits.symbols.CommonApplicationSymbols;

public interface BaseControllerAPI extends CommonApplicationSymbols {

      
   
   
   // common param names:
   public static final String PAR_LOGIN = "username";
   public static final String PAR_PASSWORD = "password";
   public static final String LOGIN_JSP = REDIRECT + "login.jsp";
   public static final String KEY_LOGIN = "KEY_LOGIN";
   
   public static final String PAR_EVENT_TYPE_ID = "event-type-id";
   public static final String PAR_EVENT_TEMPLATE_ID = "event-template-id";
   public static final String PAR_EVENT_ID = "event-id";
   public static final String PAR_CREATED = "created";
   public static final String PAR_LOGIN_ID = "login-id";

   public static final String PAR_NAME = "name";
   public static final String PAR_SHORT_NAME = "short-name";
   public static final String PAR_DESCRIPTION = "description";

   public static final String PAR_UNIT = "unit";
   public static final String PAR_DOSE = "dose";
   public static final String PAR_IS_FAVORITE = "is-favorite";
   public static final String PAR_SORT_ORDER = "sort-order";

   public static final String PAR_NOTE = "note";
   public static final String PAR_DATE = "date";
   public static final String PAR_TIME = "time";

//   public static final String PAR_STATE_ASPECT_ID = "state-aspect-id";
//   public static final String PAR_STATE_RATING_ID = "state-rating-id";
//   public static final String PAR_STATE_REGISTRATION_ID = "state-registration-id";

//   public static final String REQ_EVENT_TYPE_ID = "req-event-type-id";


   // commmon shared session variable names
   // Entity related
   public static final String SES_EVENT_TYPE_ID = "sesEventTypeId";
   public static final String SES_EVENT_TYPE = "sesEventType";
   public static final String SES_EVENT_TYPES = "sesEventTypes";
   
   public static final String SES_EVENT_TEMPLATE_ID = "sesEventTemplateId";
   public static final String SES_EVENT_TEMPLATE = "sesEventTemplate";
   public static final String SES_EVENT_TEMPLATES = "sesEventTemplates";
   
   public static final String SES_EVENT_ID = "sesEventId";
   public static final String SES_EVENT = "sesEvent";
   public static final String SES_EVENTS = "sesEvents";

   public static final String SES_LOGIN = "sesLogin";
   public static final String SES_LOGINS = "sesLogins";
   public static final String SES_LOGIN_TREE = "sesLoginTree";

   
   
   public static final String SES_SELECTABLE_UNITS = "sesSelectableUnits";
   public static final String SES_SELECTED_UNIT = "sesSelectedUnit";
   
   
   public static final String CTX_CHARTS_TO_BE_DELETED = "ctxChartsToBeDeleted";

   public static final String SES_SELECTED_EVENT_TYPE_ID = "sesSelectedEventTypeId";

   public static final String SES_EVENT_TEMPLATEMAP_ON_TID = "sesEventTemplateMapOnTId";

   
   // REQUESTS
   public static final String USER_AUTHENTICATE = "user-authenticate.do";
//   public static final String PUBLIC_LOGIN_JSP = "redirect:public/login.jsp";

//   ******************************************************************************''
   

   // commmon shared session variable names
   
   public static final String SES_ACCOUNT = "sesAccount";
   public static final String SES_ACCOUNTS = "sesAccounts";
//   public static final String SES_LOGINS = "sesLogins";
//   public static final String SES_LOGIN_TREE = "sesLoginTree";

//   public static final String SES_SELECTABLE_UNITS = "sesSelectableUnits";
//   public static final String SES_SELECTED_UNIT = "sesSelectedUnit";

   
//   public static final String CTX_CHARTS_TO_BE_DELETED = "ctxChartsToBeDeleted";

   
   // REQUESTS
   
   
   
   public static final String PUBLIC_LOGIN_JSP = "/login.jsp";

   
   public static final String LOGIN_LIST_JSP = "redirect:login-list.jsp";

   public final static String LOGIN_CREATE_SHOW = "/event-type-create-show.do";
   public final static String LOGIN_CREATE = "/event-type-create.do";
   public static final String LOGIN_CREATE_JSP = "redirect:event-type-create.jsp "; // create % update

   public final static String USER_PROFILE_CREATE_SHOW = "/user-profile-create-show.do";
   public final static String USER_PROFILE_CREATE = "/user-profile-create.do";
   public static final String USER_PROFILE_CREATE_JSP = "redirect:user-profile-create.jsp";

   public final static String PASSWORD_SHOW = "/user-profile-create-show.do";
   public final static String PASSWORD_CREATE = "/user-profile-create.do";
   public static final String PASSWORD_CREATE_JSP = "redirect:user-profile-create.jsp";


   // public static final String EVENT_SAVE = "/event-save.do";


 

   public static final String SECURE_STARTPAGE_SHOW = SECURE + "startpage-show.do";
   public static final String STARTPAGE_JSP = "startpage.jsp";

   
//   **************************************************************************
   public final static String EVENT_TYPE_LIST_SHOW = "/event-type-list-show.do";
   public static final String EVENT_TYPE_LIST_JSP = "redirect:event-type-list.jsp";

   public final static String EVENT_TYPE_CREATE_SHOW = "/event-type-create-show.do";
   public final static String EVENT_TYPE_CREATE = "/event-type-create.do";
   public static final String EVENT_TYPE_CREATE_JSP = "redirect:event-type-create.jsp "; // create % update

   public final static String EVENT_TYPE_UPDATE_SHOW = "/event-type-update-show.do";
   public final static String EVENT_TYPE_UPDATE = "/event-type-update.do";
   public static final String EVENT_TYPE_UPDATE_JSP = "redirect:event-type-update.jsp";

   public final static String EVENT_TYPE_DELETE_SHOW = "/event-type-delete-show.do";
   public final static String EVENT_TYPE_DELETE = "/event-type-delete.do";
   public static final String EVENT_TYPE_DELETE_JSP = "redirect:event-type-delete.jsp";


   
   
   public final static String EVENT_TEMPLATE_LIST_SHOW = "event-template-list-show.do";
   public final static String EVENT_TEMPLATE_TYPE_SELECT = "event-template-type-select.do";
   public final static String EVENT_TEMPLATE_LIST_JSP = "redirect:event-template-list.jsp";

   public final static String EVENT_TEMPLATE_CREATE_SHOW = "/event-template-create-show.do";
   public final static String EVENT_TEMPLATE_CREATE = "/event-template-create.do";
   public final static String EVENT_TEMPLATE_CREATE_JSP = "redirect:event-template-create.jsp";

   public final static String EVENT_TEMPLATE_UPDATE_SHOW = "/event-template-update-show.do";
   public final static String EVENT_TMPL_UPDATE = "/event-template-update.do";
   public final static String EVENT_TEMPLATE_UPDATE_JSP = "redirect:event-template-update.jsp";

   public final static String EVENT_TEMPLATE_DELETE_SHOW = "/event-template-delete-show.do";
   public final static String EVENT_TEMPLATE_DELETE = "/event-template-delete.do";
   public final static String EVENT_TEMPLATE_DELETE_JSP = "redirect:event-template-delete.jsp";

   public final static String EVENT_LIST_SHOW = "event-list-show.do";
//   public final static String EVENT_TEMPLATE_TYPE_SELECT = "event-template-type-select.do";
   public final static String EVENT_LIST_JSP = "redirect:event-list.jsp";
   
   public static final String EVENT_SHOW = "/event-show.do";
   public static final String EVENT_SAVE = "/event-save.do";
   public static final String EVENT_JSP = "redirect:event.jsp";
   
   public static final String WELCOME_JSP =  "welcome.jsp";
   
   public static final String FAVORITES_SHOW = "favorites-show.do";
   public static final String FAVORITES_JSP = "redirect:favorites.jsp";
   public static final String FAVORITE_SAVE = "favorite-save.do";
   
   public static final String LOGIN_TREE_JSP = "redirect:login-tree.jsp";
   public static final String LOGIN_TREE_SHOW = "login-tree-show.do";

   public static final String USER_PROFILE_SHOW = "user-profile-show.do";
   public static final String USER_PROFILE_SAVE = "user-profile-save.do";
   public static final String USER_PROFILE_DELETE = "user-profile-delete.do";
   
   





}
