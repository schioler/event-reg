package dk.schioler.secure.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import dk.schioler.secure.entity.Login;
import dk.schioler.secure.entity.SecureEntity;

@Service 
public interface SecureBaseDAO<T extends SecureEntity> {
	public T insert(T type, Login login);

	public int update(T type, Login login);

	public int delete(Integer id, Login login);

	public T get(Integer id, Login login);

	public List<T> retrieve(Map<String, Object> criteria, Login login);

	

//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);

}
