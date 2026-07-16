package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.EntityBase;

public interface BaseEventTable<T extends BaseEvent> extends EntityBaseTable<EntityBase>  {
   public static final String TABLE = "BASE_EVENT";

   public static final String FLD_EVENT_CATEGORY_ID = "EVENT_CATEGORY_ID";

   public static final String FLD_EVENT_CREATED_TS = "EVENT_CREATED_TS";
   public static final String FLD_EVENT_ID = "EVENT_ID";
   public static final String FLD_EVENT_TYPE = "EVENT_TYPE";
   public static final String FLD_NAME = "NAME";

//   Criteria_TS range
   public static final String FLD_EVENT_TS_START = "EVENT_TS_START";
   public static final String FLD_EVENT_TS_END  = "EVENT_TS_END";
//   public static final String FLD_EVENT_TS  = "";
  
   public String getTableName(); 
   
}
