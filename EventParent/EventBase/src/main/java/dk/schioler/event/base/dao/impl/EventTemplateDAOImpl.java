package dk.schioler.event.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.criteria.EventTemplateCriteria;
import dk.schioler.event.base.dao.table.impl.EventTemplateTableImpl;
import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.event.base.exception.EventTemplateDAOException;

@Service
public class EventTemplateDAOImpl extends AbstractNameDAOImpl<EventTemplate> implements EventTemplateDAO {

   public EventTemplateDAOImpl() {
      super(new EventTemplateTableImpl());
   }

   @Override
   protected boolean isValidInsertObject(EventTemplate type) {
      boolean isValid = super.isValidInsertObject(type);
      if (isValid) {
         if (type.getParentId() != null) {
            if (type.getUnit() != null) {
               if (type.getDose() != null) {
                  isValid = true;
               } else {
                  throw new EventTemplateDAOException("Dose can not be null");
               }
            } else {
               throw new EventTemplateDAOException("Unit can not be null");
            }
         } else {
            throw new EventTemplateDAOException("parentId can not be null");
         }

      } else {
         logger.info("super returned false on isValidInsertObject");
      }
      return isValid;

   }

   @Override
   public List<EventTemplate> getFromEventTypeId(Integer eventTypeId, Integer loginId) {
      EventTemplateCriteria et = new EventTemplateCriteria();
      et.addEventTypeId(eventTypeId);
      et.addLoginId(loginId);
      List<EventTemplate> list = retrieve(et, 0);

      return list;
   }

   @Override
   public List<EventTemplate> getFavourites(Integer loginId) {
      EventTemplateCriteria et = new EventTemplateCriteria();

      et.addLoginId(loginId);
      et.setFavourite(true);
      List<EventTemplate> list = retrieve(et, 0);

      return list;

   }

}
