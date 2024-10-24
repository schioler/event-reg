package dk.schioler.event.base.dao.secure;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.AbstractEntity;
import dk.schioler.secure.entity.Login;

@Service
public interface SecureBaseEventDAO<T extends AbstractEntity> {
	public T insert(T type, Login login);

	public int update(T type, Login login);

	public int delete(Integer id, Login login);

	public T get(Integer id, Login login);

	public List<T> retrieve(Map<String, Object> criteria, Login login);

	

//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);

}
