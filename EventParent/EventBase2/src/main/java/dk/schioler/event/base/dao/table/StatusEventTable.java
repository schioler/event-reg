package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.StatusEvent;

public interface StatusEventTable<T extends StatusEvent> extends BaseEventTable<T> {
   public static final String TABLE = "STATUS_EVENT";

//   public static final String FLD_TYPE_VALUE = StatusEvent.TYPE;
   public static final EVENT_TYPE FLD_TYPE_VALUE = EVENT_TYPE.STATUS;

   public static final String FLD_RATING = "RATING";

   public static final String FLD_DESCRIPTION = "DESCRIPTION";
}
