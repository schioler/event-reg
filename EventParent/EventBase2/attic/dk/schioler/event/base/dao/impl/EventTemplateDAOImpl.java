package dk.schioler.event.base.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.EventTemplateDAO;
import dk.schioler.event.base.dao.criteria.MedicineEventTemplateCriteria;
import dk.schioler.event.base.dao.table.impl.EventTemplateTableImpl;
import dk.schioler.event.base.entity.MedicineEventTemplate;
import dk.schioler.event.base.exception.EventTemplateDAOException;

@Service
public class EventTemplateDAOImpl extends AbstractNameDAOImpl<MedicineEventTemplate> implements EventTemplateDAO {

   public EventTemplateDAOImpl() {
      super(new EventTemplateTableImpl());
   }

   @Override
   protected boolean isValidInsertObject(MedicineEventTemplate type) {
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
   public List<MedicineEventTemplate> getFromEventTypeId(Integer eventTypeId, Integer loginId) {
      MedicineEventTemplateCriteria et = new MedicineEventTemplateCriteria();
      et.addMedicineId(eventTypeId);
      et.addOwnerId(loginId);
      List<MedicineEventTemplate> list = retrieve(et, 0);

      return list;
   }

   @Override
   public List<MedicineEventTemplate> getFavourites(Integer loginId) {
      MedicineEventTemplateCriteria et = new MedicineEventTemplateCriteria();

      et.addOwnerId(loginId);
      et.setFavourite(true);
      List<MedicineEventTemplate> list = retrieve(et, 0);

      return list;

   }

}
