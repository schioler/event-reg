package dk.schioler.event.base.dao.table;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.impl.SQLConstructs;
import dk.schioler.event.base.entity.AbstractEntity;

public interface ISQLTable<T extends AbstractEntity> extends SQLConstructs {
	public static final String FLD_ID = "ID";

	public String getTableName();

	public StringBuffer getInsertSQL();

	public Map<String, Object> getInsertMappings(T type);

	public StringBuffer getUpdateSQL();

	public Map<String, Object> getUpdatetMappings(T type);

	public StringBuffer getDeleteSQL();

	public Map<String, Object> getIdMapping(Integer id);

	public String getFromIdSQL(Integer id);

	public StringBuffer getRetrieveSQL(Map<String, Object> criteria, int maxRows);

	public RowMapper<T> getRowMapper();
}
