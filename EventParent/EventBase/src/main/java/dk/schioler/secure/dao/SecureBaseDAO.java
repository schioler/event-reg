package dk.schioler.secure.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.secure.entity.SecureEntity;

@Service 
public interface SecureBaseDAO<T extends SecureEntity> {
	public T insert(T type);

	public int update(T type);

	public int delete(Integer id);

	public T get(Integer id);

	public List<T> retrieve(T criteria, int maxRows);

	

//	public void setRowsMax(int rowCount);
//	public void setRowsStartFrom(int startRow);

}
