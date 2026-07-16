package dk.schioler.event.base.dao.impl;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventCategoryDAO;
import dk.schioler.event.base.dao.table.impl.MedicineEventTableImpl;
import dk.schioler.event.base.entity.MedicineFirm;
import dk.schioler.event.base.exception.EventDAOException;

@Service
public class EventDAOImpl extends AbstractNameDAOImpl<MedicineFirm> implements EventCategoryDAO {

   public EventDAOImpl() {
      super(new MedicineEventTableImpl());
   }

   @Override
   protected boolean isValidInsertObject(MedicineFirm type) {
      boolean retVal = super.isValidInsertObject(type);
      if (retVal) {
         if (type.getDescription() != null) {
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

// @Override
// public List<T> retrieve(AbstractNameCriteria criteria, int maxRows) {
//    
//    return super.retrieve(criteria, maxRows);
// };
}
