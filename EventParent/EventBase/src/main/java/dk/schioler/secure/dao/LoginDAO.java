package dk.schioler.secure.dao;

import org.springframework.stereotype.Service;

import dk.schioler.secure.entity.Login;

@Service
public interface LoginDAO extends SecureBaseDAO<Login> {
	public Login getSecLogin(String login);
//	public Login getLogin(String login);
//	public List<Login> getLogins(Integer userProfileId);
//	public Login getActiveLogin(String login );
}
