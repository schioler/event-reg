package dk.schioler.event.base.dao;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.EntityBase;

@Service
public interface EventBaseDAO<T extends BaseEvent> extends EntityBaseDAO<EntityBase> {
//   public Integer getEventId();
//   public Integer getEventCategoryId();
//   public String getEventType();
//
//   
//   public String getName();

   
}
