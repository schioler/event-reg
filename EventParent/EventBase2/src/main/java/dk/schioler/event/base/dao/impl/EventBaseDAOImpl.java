package dk.schioler.event.base.dao.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.dao.EventBaseDAO;
import dk.schioler.event.base.entity.impl.BaseEventImpl;
import dk.schioler.event.base.entity.impl.EntityBaseImpl;

public abstract class EventBaseDAOImpl<T extends BaseEventImpl> extends EntityBaseImpl implements EventBaseDAO<T> {

   public EventBaseDAOImpl(Integer id, Integer loginId, LocalDateTime created) {
      super(id, loginId, created);
   
   }

   
}
