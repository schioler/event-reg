package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.EventTemplate;

public interface EventTemplateTable extends BaseSQLTableParentChild<EventTemplate> {
   public static final String TABLE = "EVENT_TEMPLATE";

   // ParentId
   public static final String FLD_EVENT_TYPE_ID = "EVENT_TYPE_ID";

   public static final String FLD_DOSE = "DOSE";
   public static final String FLD_UNIT = "UNIT";

   public static final String FLD_IS_FAVOURITE = "IS_FAVORITE";
   public static final String FLD_SORT_ORDER = "SORT_ORDER";
   
   

   
}
