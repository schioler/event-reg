package dk.schioler.event.web.entity;

import java.util.Map;

import dk.schioler.event.base.entity.Event;

public interface WebEntityEvent extends WebEntityNamed {

   public static final String REQ_EVENT_EVENT_TYPE_SELECT = "req-event-event-type-select"; // req-event-type-id-select

   public static final String REQ_EVENT_EVENT_TEMPLATE_SELECT = "req-event-template-type-select";

   public static final String REQ_UNIT = "req-unit";
   public static final String REQ_DOSE = "req-dose";
   public static final String REQ_EVENT_NOTE = "req-event-note";
   public static final String REQ_EVENT_TS = "req-event-ts";

   public static final String REQ_EVENT_ID = "req-event-id";
   public static final String REQ_EVENT_TEMPLATE_ID = "req-event-template-id";

//   public static final String SES_UNITS = "sesUnits";

   public String getEventTemplateId();

   public void setEventTemplateId(String eventTemplateId);

   public String getUnit();

   public void setUnit(String unit);

   public String getDose();

   public void setDose(String dose);

   public String getEventTs();

   public void setEventTs(String eventTs);

   public String getNote();

   public void setNote(String note);

   public void grabRequestValues(Map<String, String> params);

   public Event getThisAsBaseEntity(Event e);

   // // Event Search
//   public static final String REQ_TIMELINE_STARTDATE = "req-timeline-startdate";
//   public static final String REQ_TIMELINE_START_DATE = "req-timeline-startdate";
//   public static final String REQ_TIMELINE_INTERVAL = "req-timeline-interval";
//   public static final String REQ_TIMELINE_COUNT_INTERVALS = "req-timeline-count-intervals";

//   public static final String SES_EVENT_CREATE_DATA = "sesEventCreateDats";

//   public final static String EVENT_EVENT_TYPE_SELECT_UPDATE = "event-event-type-select-update.do";
//   public static final String EVENT_EVENT_TEMPLATE_SELECT_UPDATE = "event-event-template-select-update.do´";
//   public final static String EVENT_UNITS_SELECT_UPDATE = "event-units-select-update.do";
//   public static final String EVENT_CREATE_SHOW = "event-create-show.do";
//   public static final String EVENT_UPDATE_SHOW = "event-update-show.do";
//   public static final String EVENT_DELETE_SHOW = "event-delete-show.do";
//
//   public static final String EVENT_SHOW = "event-show.do";
//   public static final String EVENT_JSP = "10-event.jsp";
//   public static final String EVENT_CREATE = "event-create.do";
//   public static final String EVENT_CREATE_JSP = "10-event-create.jsp";
//
//   // *******************************************
//
//   public static final String EVENT_UPDATE = "event-update.do";
//   public static final String EVENT_UPDATE_JSP = "10-event-update.jsp";
//
//   // *******************************************
//
//   public static final String EVENT_DELETE = "event-delete.do";
//   public static final String EVENT_DELETE_JSP = "10-event-delete.jsp";
//

   // // *******************************************
//
// // Event Search
// public static final String REQ_TIMELINE_STARTDATE = "req-timeline-startdate";
// public static final String REQ_TIMELINE_START_DATE = "req-timeline-startdate";
// public static final String REQ_TIMELINE_INTERVAL = "req-timeline-interval";
// public static final String REQ_TIMELINE_COUNT_INTERVALS = "req-timeline-count-intervals";
// 
// public static final String REQ_EVENT_EVENT_TYPE_SELECT = "req-event-event-type-select"; //req-event-type-id-select
// public final static String EVENT_EVENT_TYPE_SELECT_UPDATE = "event-event-type-select-update.do";
//
// public static final String REQ_EVENT_EVENT_TEMPLATE_SELECT = "req-event-template-type-select"; 
// public static final String EVENT_EVENT_TEMPLATE_SELECT_UPDATE = "event-event-template-select-update.do´";
//
// public final static String EVENT_UNITS_SELECT_UPDATE = "event-units-select-update.do";
// 
// public static final String EVENT_SEARCH_JSP = "10-event-search.jsp";
//
// public final static String EVENT_SEARCH_RESULT_JSP = "01-event-search-result.jsp";
// public static final String REQ_SELECT_TIMESLOT_INTERVAL_LENGTH = "req-select-timeslot-interval-length";
// 
// public static final String SES_EVENT_CREATE_DATA = "sesEventCreateDats";
// 
// public static final String SES_SELECT_TIMESLOT_LENGTHS = "sesSelectTimeslotLengths";
// public static final String SES_SELECTED_TIMESLOT_LENGTH = "sesSelectedTimeslotLength";
// public static final String SES_EVENT_SEARCH_RESULT = "sesSelectedTimeslotLength";
// public static final String SES_EVENT_SEARCH_INPUT = "sesSelectedTimeslotLength";
// 
// 
// public static final String EVENT_SHOW = "event-show.do";
// public static final String EVENT_JSP = "10-event.jsp";

   // ******

//
//   // ******
//   public void grabRequestValues(Map<String, String> params);
//
//   public abstract AbstractEntityId getAsEntity(WebEntityEvent e);
//
   // public static final String REQ_EVENT_ID = "req-event-id";
   // public static final String REQ_EVENT_TEMPLATE_ID = "req-event-template-id";
//

   // URLs
//   public final static String EVENT_SEARCH_SHOW_DO = "event-search-show.do";
//   public final static String EVENT_SEARCH_DO = "event-search.do";
//   public final static String EVENT_LIST_SHOW = "event-list-show.do";
//   public final static String EVENT_LIST_JSP = "01-event-list.jsp";

   // *******************************************
}
