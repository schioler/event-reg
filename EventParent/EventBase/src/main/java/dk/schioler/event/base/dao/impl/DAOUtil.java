package dk.schioler.event.base.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.entity.AbstractEntityName;

public interface DAOUtil<T extends AbstractEntityName> {
	public RowMapper<T> getRowMapper();
	public String getSQLTableName();
	public List<String> getSQLSelectColumns();
	public List<String> getSQLInsertColumns();
	public StringBuffer getSelectSqlFromColumns(List<String> columns);
	public StringBuffer getInsertSqlFromColumns(List<String> columns);
	public String getOrderBy(); 

}
