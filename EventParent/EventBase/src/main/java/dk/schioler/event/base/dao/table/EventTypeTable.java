package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.EventType;

public interface EventTypeTable extends ISQLTable<EventType> {
	public static final String TABLE = "EVENT_TYPE";

	public static final String FLD_NAME = "NAME";
	public static final String FLD_SHORT_NAME = "SHORT_NAME";
	public static final String FLD_DESCRIPTION = "DESCRIPTION";

	public static final String FLD_FQ_ID = TABLE + "_" + FLD_ID;
	public static final String FLD_FQ_NAME = TABLE + "_" + FLD_NAME;
	public static final String FLD_FQ_SHORT_NAME = TABLE + "_" + FLD_SHORT_NAME;
	public static final String FLD_FQ_DESCRIPTION = TABLE + "_" + FLD_DESCRIPTION;
	
}
