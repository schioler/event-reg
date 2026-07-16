package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.Treatment;

public interface TreatmentTable<T extends Treatment> extends EntityBaseTable<T> {
   public static final String TABLE = "TREATMENT";


   public static final String FLD_SUPPLIER_ID = "SUPPLIER_ID";
   public static final String FLD_NAME = "NAME";
   public static final String FLD_DESCRIPTION = "DESCRIPTION";

}
