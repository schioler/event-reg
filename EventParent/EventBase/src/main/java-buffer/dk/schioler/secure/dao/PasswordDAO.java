package dk.schioler.secure.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.secure.entity.Password;

@Service
public interface PasswordDAO extends SecureBaseDAO<Password> {
	public List<Password> getPassword(Integer loginId, boolean includeHistoric);
}
