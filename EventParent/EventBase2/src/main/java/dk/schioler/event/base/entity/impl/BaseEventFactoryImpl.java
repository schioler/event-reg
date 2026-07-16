package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.BaseEventFactory;
import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.TREE_TYPE;

public class BaseEventFactoryImpl implements BaseEventFactory {

   @Override
   public BaseEvent createBaseEvent(Integer id, Integer ownerId, LocalDateTime createdTs, EVENT_TYPE eventType, String name, TREE_TYPE treeType) {
      BaseEvent be = null;
      if (EVENT_TYPE.MEDICINE.equals(eventType)) {
         be = new MedicineEventImpl(id, ownerId, createdTs, name, treeType);
      } else if (EVENT_TYPE.STATUS.equals(eventType)) {
         be = new StatusEventImpl(id, ownerId, createdTs, name, treeType);
      } else if (EVENT_TYPE.TREATMENT.equals(eventType)) {
         be = new TreatmentEventImpl(id, ownerId, createdTs, name, treeType);
      }
      return be;
   }

}
