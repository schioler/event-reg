package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.AbstractTSEntity;

@Service
public abstract class AbstractTSDAOImpl<T extends AbstractTSEntity> extends AbstractDAOImplOld<T> {
	

	protected static final String FLD_START_TS = "START_TS";
	protected static final String FLD_END_TS = "END_TS";
	
	
	public AbstractTSDAOImpl(String tableName, List<String> selectColumns,List<String> updateColumns) {
		super(tableName, selectColumns, updateColumns);
	}
	
//	@Override
//	public T insert(T type) {
//		try {
//			logger.debug("insert "+ type);
//			T typeTS = super.insert(type);
//			logger.debug("type has been inserted, will retrieve startTS");
//			Timestamp startTS = getStartTS(typeTS.getId());
//			typeTS.setStartTS(startTS.toLocalDateTime());
//			
//			return typeTS;
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//			throw e;
//		}
//
//	}

	private Timestamp getStartTS(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT START_TS FROM ");
		sql.append(getSQLTableName()).append(SPACE); 
		sql.append(WHERE).append(SPACE);
		sql.append(FLD_ID).append(EQ).append(BIND).append(FLD_ID);

		logger.debug("sql="+ sql.toString());
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(FLD_ID, id);

		List<Timestamp> query = getJDBCTemplate().query(sql.toString(), paramSource,  new RowMapper<Timestamp>() {

			@Override
			public Timestamp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Timestamp timestamp = rs.getTimestamp(1);
				return timestamp;
			}
			
		});

		Timestamp t = query.get(0);
		
		logger.debug("getStartTS: returning:"+ t);
		return t;
	}

}
