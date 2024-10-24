package dk.schioler.secure.table;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.secure.entity.SecureEntity;
import dk.schioler.secure.table.impl.SQLConstructs;

public interface SecureTable<T extends SecureEntity> extends SQLConstructs {
	public static final String FLD_ID = "ID";

	public static final String FLD_START_TS = "START_TS";
	public static final String FLD_END_TS = "END_TS";

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
