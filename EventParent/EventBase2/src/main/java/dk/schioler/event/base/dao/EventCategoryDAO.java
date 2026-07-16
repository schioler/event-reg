package dk.schioler.event.base.dao;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.EventCategory;

@Service
public interface EventCategoryDAO<T extends EventCategory> extends EntityBaseDAO<T> {

}
