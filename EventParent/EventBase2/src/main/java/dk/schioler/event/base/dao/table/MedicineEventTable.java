package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.MedicineEvent;

public interface MedicineEventTable<T extends MedicineEvent> extends BaseEventTable<T> {
   public static final String TABLE = "MEDICINE_EVENT";
   
//   public String getTableName(); 

//   public static final String FLD_TYPE_VALUE = MedicineEvent.TYPE;
   public static final EVENT_TYPE FLD_TYPE_VALUE = EVENT_TYPE.MEDICINE;

//   public static final String FLD_TYPE = "TYPE";

   public static final String FLD_MEDICINE_ID = "MEDICINE_ID";
   public static final String FLD_MEDICINE_EVENT_TEMPLATE_ID = "MEDICINE_EVENT_TEMPLATE_ID";

   public static final String FLD_NOTE = "NOTE";
   public static final String FLD_DOSE = "DOSE";
   public static final String FLD_UNIT = "UNIT";

   

   
}
