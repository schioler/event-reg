package dk.schioler.secure.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import dk.schioler.secure.dao.PasswordDAO;
import dk.schioler.secure.entity.Password;
import dk.schioler.secure.table.PasswordTable;
import dk.schioler.secure.table.impl.PasswordTableImpl;

@Service
public class PasswordDAOImpl extends AbstractSecureBaseDAOImpl<Password> implements PasswordDAO {

	public PasswordDAOImpl() {
		super(new PasswordTableImpl());
	}

	@Override
	public List<Password> getPassword(Integer loginId, boolean includeHistoric) {

		PasswordTable pTable = (PasswordTable) getTable();

		Map<String, Object> values = new HashMap<String, Object>();
		StringBuffer sql = pTable.getPasswordFromLoginSQL(loginId, values, includeHistoric);

		logger.debug("getPasswordBMOLoginid.sql=" + sql.toString());
		logger.debug("values=" + values);

		NamedParameterJdbcTemplate jdbcTmpl = getJDBCTemplate();

		MapSqlParameterSource paramSource = new MapSqlParameterSource(values);

		List<Password> query = jdbcTmpl .query(sql.toString(), paramSource, pTable.getRowMapper());

		return query;

	}

	@Override
	protected boolean validateInsertObject(Password type) {
		boolean retVal = false;
		if (type != null) {
			if (StringUtils.isNotBlank(type.getPwd())) {
				if (type.getLoginId() != null) {
					retVal = true;
				}

			}
		}

		return retVal;
	}

//	@Override
//	public void refreshCache() {
//		// no cache on password
//
//	}

//	@Override
//	public String getOrderBy() {
//
//		return FLD_LOGIN_ID;
//	}

}
