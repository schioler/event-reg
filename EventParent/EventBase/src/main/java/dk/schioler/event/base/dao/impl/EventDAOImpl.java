package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventDAO;
import dk.schioler.event.base.dao.table.impl.EventTableImpl;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.exception.EventDAOException;

@Service
public class EventDAOImpl extends AbstractNameDAOImpl<Event> implements EventDAO {

   public EventDAOImpl() {
      super(new EventTableImpl());
   }

   @Override
   protected boolean isValidInsertObject(Event type) {
      boolean retVal = super.isValidInsertObject(type);
      if (retVal) {
         if (type.getDose() != null) {
            if (type.getUnit() != null) {
               if (type.getEventTS() != null) {
                  retVal = true;
               } else {
                  throw new EventDAOException("eventTS can not be null");
               }
            } else {
               throw new EventDAOException("Unit can not be null");
            }
         } else {
            throw new EventDAOException("Dose can not be null");
         }

      } else {
         throw new EventDAOException("super did not validate");
      }
      return retVal;
   }

}
