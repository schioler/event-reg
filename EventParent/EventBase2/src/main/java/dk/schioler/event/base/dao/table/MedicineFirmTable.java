package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.MedicineFirm;

/**
 * This interface represents the functionality of MedicinFirmTable and it's place in the inheritance tree.
 * Participators:
 * MedicineFirmTable handles MedicineFirm instances - or extensions thereof.
 * MedicineFirmTable extends EntityBaseTable<T>
 * <br>The EnitityBaseTable is the table, representing functionality of EntityBase. <br>
 * This in all simplicity, represents all objects in this domain, as the EntityBase contains ID, CREATED_TS,OWNER_ID  
 * @param <T>
 */
public interface MedicineFirmTable<T extends MedicineFirm> extends EntityBaseTable<T> {
   public static final String TABLE = "MEDICINE_FIRM";
   // parent id
   public static final String FLD_NAME = "NAME";
   public static final String FLD_BRAND = "BRAND";
  

}
