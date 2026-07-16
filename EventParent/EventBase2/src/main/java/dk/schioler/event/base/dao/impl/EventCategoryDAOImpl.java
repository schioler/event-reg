package dk.schioler.event.base.dao.impl;

import dk.schioler.event.base.dao.EventCategoryDAO;
import dk.schioler.event.base.dao.table.impl.EventCategoryTableImpl;
import dk.schioler.event.base.entity.EventCategory;

public class EventCategoryDAOImpl<T extends EventCategory> extends EntityBaseDAOImpl<T> implements EventCategoryDAO<T> {

   protected EventCategoryDAOImpl() {
      super(new EventCategoryTableImpl<T>());
   }

}
