package dk.schioler.secure.dao;

import org.springframework.stereotype.Service;

import dk.schioler.secure.entity.Login;

@Service
public interface LoginDAO extends SecureBaseDAO<Login> {
	public Login getLogin(String login);

}
