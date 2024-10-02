package dk.schioler.event.base.dao;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.UserProfile;

@Service
public interface UserProfileDAO extends BaseEventDAO<UserProfile> {

}
