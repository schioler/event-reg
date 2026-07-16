package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.MedicineType;

public interface MedicineTypeTable<T extends MedicineType> extends EntityBaseTable<T> {
   public static final String TABLE = "MEDICINE_TYPE";


   public static final String FLD_MEDICINE_FIRM_ID = "MEDICINE_FIRM_ID";
   public static final String FLD_MEDICINE_TYPE = "MEDICINE_TYPE";

   public static final String FLD_NAME = "NAME";
   public static final String FLD_DESCRIPTION = "DESCRIPTION";

}
