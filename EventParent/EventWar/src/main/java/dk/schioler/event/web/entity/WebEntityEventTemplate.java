package dk.schioler.event.web.entity;

import java.util.List;
import java.util.Map;

public interface WebEntityEventTemplate extends WebEntityNamed {

   public static final String CTX_CHARTS_TO_BE_DELETED = "ctxChartsToBeDeleted";

//   public static final String REQ_EVENT_TEMPLATE_ID = "req-event-template-id";

   public static final String REQ_UNIT = "req-unit";
   public static final String REQ_DOSE = "req-dose";
   
   public static final String REQ_IS_FAVORITE = "req-is-favorite";
   public static final String REQ_SORT_ORDER = "req-sort-order";

   public static final String REQ_PARENT_ID = "req-parent-id";
   public static final String REQ_EVENT_TYPE_ID = "req-event-type-id";

   // **************************************************************************
//   EVENT-TEMPLATE
//   public static final String TEMPLATE_SELECT_UNIT_UPDATE = "event-template-select-unit-update.do";

//   public static final String SES_EVENT_TEMPLATE_ID = "sesEventTemplateId";
//   public static final String SES_EVENT_TEMPLATE = "sesEventTemplate";
//   public static final String SES_EVENT_TEMPLATES = "sesEventTemplates";

   public void grabRequestValues(Map<String, String> params);
   
   public String getEventTypeId();

   public void setEventTypeId(String value);

   public String getUnit();

   public void setUnit(String value);

   public String getDose();

   public void setDose(String value);

   public String isFavorite();

   public void setFavorite(String value);

   public String getSortOrder();

   public void setSortOrder(String value);

   public void addEventId(String eventId);
   
   public List<String> getEventIds();
}
