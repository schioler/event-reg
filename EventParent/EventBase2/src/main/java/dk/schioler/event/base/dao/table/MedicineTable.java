package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.Medicine;

public interface MedicineTable<T extends Medicine> extends EntityBaseTable<T> {
   public static final String TABLE = "MEDICINE";

   public static final String FLD_MEDICINE_TYPE_ID = "MEDICINE_TYPE_ID";
   
   public static final String FLD_NAME = "NAME";
   public static final String FLD_DOSE = "DOSE";
   public static final String FLD_UNIT = "UNIT";
//   public static final String FLD_NOTE = "NOTE";

}
