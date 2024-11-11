package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.EventTemplate;

public interface EventTemplateTable extends ISQLTable<EventTemplate> {
	public static final String TABLE = "EVENT_TEMPLATE";

	public static final String FLD_EVENT_TYPE_ID = "EVENT_TYPE_ID";

	public static final String FLD_DESCRIPTION = "DESCRIPTION";
	public static final String FLD_DOSE = "DOSE";
	public static final String FLD_UNIT = "UNIT";
	
	public static final String FLD_IS_FAVOURITE = "IS_FAVORITE";
	public static final String FLD_SORT_ORDER = "SORT_ORDER";
	

	public static final String FLD_FQ_ID = TABLE + "_" + FLD_ID;
	public static final String FLD_FQ_EVENT_TYPE_ID = TABLE + "_" + FLD_EVENT_TYPE_ID;
	
	public static final String FLD_FQ_NAME = TABLE + "_" + FLD_NAME;
	public static final String FLD_FQ_SHORT_NAME = TABLE + "_" + FLD_SHORT_NAME;
	public static final String FLD_FQ_DESCRIPTION = TABLE + "_" + FLD_DESCRIPTION;
	public static final String FLD_FQ_DOSE = TABLE + "_" + FLD_DOSE;
	public static final String FLD_FQ_UNIT = TABLE + "_" + FLD_UNIT;
	
}
