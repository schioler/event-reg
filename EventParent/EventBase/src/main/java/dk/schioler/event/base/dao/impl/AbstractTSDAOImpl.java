package dk.schioler.event.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.AbstractTSEntity;

@Service
public abstract class AbstractTSDAOImpl<T extends AbstractTSEntity> extends AbstractDAOImpl<T> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractTSDAOImpl() {

	}

	
	@Override
	public T insert(T type) {
		try {
			logger.debug("insert "+ type);
			T typeTS = super.insert(type);
			logger.debug("type has been inserted, will retrieve startTS");
			Timestamp startTS = getStartTS(typeTS.getId());
			typeTS.setStartTS(startTS.toLocalDateTime());
			
			return typeTS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}

	}

//	@Override
//	public void update(T type) {
//		jdbcTemplate.update(getUpdateSQL(), getUpdateMappings(type));
//
//	}


	
	private Timestamp getStartTS(Integer id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT START_TS FROM ");
		sql.append(getTableName()).append(SPACE); 
		sql.append(WHERE).append(SPACE);
		sql.append(COL_ID).append(EQ).append(BIND).append(COL_ID);

		logger.debug("sql="+ sql.toString());
		
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(COL_ID, id);

		List<Timestamp> query = jdbcTemplate.query(sql.toString(), paramSource,  new RowMapper<Timestamp>() {

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
