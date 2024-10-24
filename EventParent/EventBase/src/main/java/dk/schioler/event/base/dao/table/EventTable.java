package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.Event;

public interface EventTable extends ISQLTable<Event> {
	public static final String TABLE = "EVENT";

	public static final String FLD_EVENT_TEMPLATE_ID = "EVENT_TEMPLATE_ID";
	
	public static final String FLD_NAME = "NAME";
	public static final String FLD_SHORT_NAME = "SHORT_NAME";
	public static final String FLD_NOTE = "NOTE";
	public static final String FLD_DOSE = "DOSE";
	public static final String FLD_UNIT = "UNIT";
	
	public static final String FLD_EVENT_TS= "EVENT_DATE";

	public static final String FLD_CREATED = "CREATED";
}
