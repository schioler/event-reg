package dk.schioler.secure.dao;

import org.springframework.stereotype.Service;

import dk.schioler.secure.entity.UserProfile;

@Service
public interface UserProfileDAO extends SecureBaseDAO<UserProfile> {

}
