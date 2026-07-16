package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.MedicineEventTemplate;

public interface MedicineEventTemplateTable<T extends MedicineEventTemplate> extends EntityBaseTable<T> {
   public static final String TABLE = "MEDICINE_EVENT_TEMPLATE";


   public static final String FLD_MEDICINE_ID = "MEDICINE_ID";
   public static final String FLD_MEDICINE_NAME = "MEDICINE_NAME";

   public static final String FLD_UNIT = "UNIT";
   public static final String FLD_DOSE = "DOSE";

   public static final String FLD_IS_FAVOURITE = "IS_FAVORITE";

}
