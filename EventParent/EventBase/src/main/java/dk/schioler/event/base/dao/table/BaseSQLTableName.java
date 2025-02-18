package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.AbstractEntityName;

public interface BaseSQLTableName<T extends AbstractEntityName> extends BaseSQLTableId<T> {

   public static final String FLD_NAME = "NAME";
   public static final String FLD_SHORT_NAME = "SHORT_NAME";
   public static final String FLD_DESCRIPTION = "DESCRIPTION";

}
