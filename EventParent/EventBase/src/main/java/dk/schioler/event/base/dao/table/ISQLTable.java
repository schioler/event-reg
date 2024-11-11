package dk.schioler.event.base.dao.table;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractCriteria;
import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.base.entity.AbstractEntity;

public interface ISQLTable<T extends AbstractEntity> extends SQLConstructs {
	public static final String FLD_ID = "ID";
	public static final String FLD_LOGIN_ID = "LOGIN_ID";
	
	public static final String FLD_NAME = "NAME";
	public static final String FLD_SHORT_NAME = "SHORT_NAME";

	
	public String getTableName();

	public StringBuffer getInsertSQL();

	public Map<String, Object> getInsertMappings(T type);

	public StringBuffer getUpdateSQL();

	public Map<String, Object> getUpdatetMappings(T type);

	public StringBuffer getDeleteSQL();

	public Map<String, Object> getIdMapping(Integer id, Integer loginId);

	public String getFromIdSQL();

	public StringBuffer getRetrieveSQL(AbstractCriteria criteria, int maxRows);
	
	public Map<String, Object> getRetrieveMappings(AbstractCriteria type);

	public RowMapper<T> getRowMapper();
}
