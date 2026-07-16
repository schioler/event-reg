package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.TreatmentEvent;

public interface TreatmentEventTable<T extends TreatmentEvent> extends BaseEventTable<T> {
   public static final String TABLE = "TREATMENT_EVENT";

   // parent id
   public static final String FLD_TREATMENT_ID = "TREATMENT_ID";
   public static final String FLD_TREATMENT_NAME = "TREATMENT_NAME";

   public static final String FLD_UNIT = "UNIT";
   public static final String FLD_DURATION = "DURATION";
   public static final String FLD_IS_FAVORITE = "IS_FAVORITE";

}
