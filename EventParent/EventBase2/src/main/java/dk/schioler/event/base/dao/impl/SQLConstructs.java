package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

@Service
public interface SQLConstructs {
	public static final String TBL_EVENT = "EVENT";
	public static final String TBL_EVENT_TYPE = "EVENT_TYPE";
	public static final String TBL_EVENT_TMPL = "EVENT_TEMPLATE";
	public static final String TBL_USERPROFILE = "USER_PROFILE";
	public static final String TBL_LOGIN = "LOGIN";
//	public static final String FLD_PASSWORD = "PASSWORD";
	
	public static final String FLD_ID = "ID";

	public static final String SELECT = "SELECT";
	public static final String FROM = "FROM";
	public static final String AS = "AS";
	public static final String WHERE = "WHERE";
	public static final String AND = "AND";
	public static final String INSERT_INTO = " INSERT INTO ";
	public static final String DELETE = " DELETE ";
	public static final String VALUES = " VALUES ";
	public static final String UPDATE = " UPDATE ";
	public static final String SET = " SET ";
	public static final String BETWEEN = " BETWEEN ";
	public static final String IN = " IN ";
	

	public static final String SEP = ",";
	public static final String SPACE = " ";
	public static final String EQ = "=";
	public static final String LARGER_THAN = ">";
	public static final String SMALLER_THAN = "<";
	public static final String ISNULL = "IS NULL";
	public static final String LIKE = "LIKE";
	public static final String HYPHEN = "'";
	public static final String CONCAT = "CONCAT";
	public static final String SQL_WILDCARD = "%";
	
	public static final String OR = "OR";
	public static final String DOT = ".";
	
	
	public static final String BIND = ":";
	public static final String LEFT_PARENTHIS = "(";
	public static final String RIGHT_PARENTHIS = ")";
	
	public static final String CREATED_START_DATE = "CREATED_STARTDATE";
   public static final String CREATED_END_DATE = "CREATED_ENDDATE";
   
	public static final String START_DATE = "STARTDATE";
	public static final String END_DATE = "ENDDATE";
	
	public static final String ORDER_BY = "ORDER BY";
	

}
