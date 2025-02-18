package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.Event;

public interface EventTable extends BaseSQLTableParentChild<Event> {
   public static final String TABLE = "EVENT";

   // parent id
   public static final String FLD_EVENT_TEMPLATE_ID = "EVENT_TEMPLATE_ID";
 
 
   public static final String FLD_NOTE = "NOTE";
   public static final String FLD_DOSE = "DOSE";
   public static final String FLD_UNIT = "UNIT";

   public static final String FLD_EVENT_TS = "EVENT_TS";
   
   public static final String FLD_EVENT_TS_START = "EVENT_TS_START";
   public static final String FLD_EVENT_TS_END = "EVENT_TS_END";

//   public static final String FLD_DOSE_MIN = "DOSE_MIN";
//   public static final String FLD_DOSE_MAX = "DOSE_MAX";
   
}
