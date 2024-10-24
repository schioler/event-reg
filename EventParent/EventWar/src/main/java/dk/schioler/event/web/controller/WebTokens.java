package dk.schioler.event.web.controller;

public interface WebTokens {
	public static String SES_SELECTED_EVENT_TYPE_ID = "sesSelectedEventTypeId";
	public static String SES_EVENT_TYPES = "sesEventTypes";
	public static String SES_EVENT_TYPE = "sesEventType";

	public static String SES_EVENT_TEMPLATES = "sesEventTemplates";
	public static String SES_EVENT_TEMPLATE = "sesEventTemplate";
	public static String SES_EVENT_TEMPLATEMAP_ON_TID = "sesEventTemplateMapOnTId";
	public static String SES_EVENTS_INSERTED = "sesEventsInserted";
	public static String SES_EVENT_QUEUE = "sesEventQueue";

	public static String SES_SEARCH_CRITERIA_TMPL_LIST = "sesSearchCriteriaTmplList";
	public static String SES_SEARCH_RESULT = "sesSearchResult";
	public static String SES_SEARCH_RESULT_START_DATE = "sesSearchResultStartDate";
	public static String SES_SEARCH_RESULT_END_DATE = "sesSearchResultEndDate";

	public static String SES_CALLER = "sesCaller";

	public static String REQ_PARAM_EVENT_TYPE_ID = "reqEventTypeId";
	public static String REQ_PARAM_TEMPLATE_SELECT = "reqEventTemplateSelect";

	public static String REQ_PARAM_START_DATE = "reqStartDate";
	public static String REQ_PARAM_END_DATE = "reqEndDate";

	public static String REQ_PARAM_START_TIME = "reqStartTime";
	public static String REQ_PARAM_END_TIME = "reqEndTime";

	public static String CTX_CHARTS_TO_BE_DELETED = "chartsToBeDeleted";

	/* 
	 * *************************************************************************
	 */
	
	public static String REQ_EVENT_TYPE_LIST_SHOW = "/event-type-list-show.do";
	public static String REQ_EVENT_TYPE_CREATE_SHOW = "/event-type-create-show.do";

	public static String REQ_EVENT_TYPE_CREATE = "/event-type-create.do";

	public static String REQ_EVENT_TYPE_UPDATE_SHOW = "/event-type-update-show.do";
	public static String REQ_EVENT_TYPE_UPDATE = "/event-type-update.do";

	public static String REQ_EVENT_TYPE_DELETE_SHOW = "/event-type-delete-show.do";
	public static String REQ_EVENT_TYPE_DELETE = "/event-type-delete.do";
	
	/* 
	 * *************************************************************************
	 */

	public static String REQ_EVENT_TMPL_LIST_SHOW = "/event-tmpl-list-show.do";
	public static String REQ_EVENT_TMPL_TYPE_SELECT = "/event-tmpl-type-select.do";
	public static String RES_EVENT_TMPL_LIST_JSP = "event-tmpl-list.jsp";

	public static String REQ_EVENT_TMPL_CREATE_SHOW = "/event-tmpl-create-show.do";
	public static String REQ_EVENT_TMPL_CREATE = "/event-tmpl-create.do";
	public static String RES_EVENT_TMPL_CREATE_JSP = "event-tmpl-create.jsp";

	public static String REQ_EVENT_TMPL_UPDATE_SHOW = "/event-tmpl-update-show.do";
	public static String REQ_EVENT_TMPL_UPDATE = "/event-tmpl-update.do";
	public static String RES_EVENT_TMPL_UPDATE_JSP = "event-tmpl-update.jsp";

	public static String REQ_EVENT_TMPL_DELETE_SHOW = "/event-tmpl-delete-show.do";
	public static String REQ_EVENT_TMPL_DELETE = "/event-tmpl-delete.do";
	public static String RES_EVENT_TMPL_DELETE_JSP = "event-tmpl-delete.jsp";

//	public static String RES_EVENT_TMPL_JSP = "event-tmpl.jsp";
	public static final String KEY_LOGIN = "KEY_LOGIN";

	public static String LOGIN = "username";
	public static String PASSWORD = "password";
	
}
