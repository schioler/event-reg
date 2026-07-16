package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.EventCategory;

public interface EventCategoryTable<T extends EventCategory> extends EntityBaseTable<T> {
   public static final String TABLE = "EVENT_CATEGORY";
   
   public static final String FLD_NAME = "NAME";
   public static final String FLD_DESCRIPTION= "DESCRIPTION";
}
