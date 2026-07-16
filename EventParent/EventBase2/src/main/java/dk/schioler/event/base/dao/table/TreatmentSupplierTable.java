package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.TreatmentSupplier;

public interface TreatmentSupplierTable<T extends TreatmentSupplier> extends EntityBaseTable<T> {
   public static final String TABLE = "TREATMENT_SUPPLIER";

   public static final String FLD_NAME = "NAME";
   public static final String FLD_BRAND = "BRAND";
}
