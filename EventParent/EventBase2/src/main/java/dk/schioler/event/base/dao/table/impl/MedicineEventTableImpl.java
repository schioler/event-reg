package dk.schioler.event.base.dao.table.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.EventBaseException;
import dk.schioler.event.base.dao.criteria.EntityBaseCriteria;
import dk.schioler.event.base.dao.criteria.MedicineEventCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.MedicineEventRowMapper;
import dk.schioler.event.base.dao.table.MedicineEventTable;
import dk.schioler.event.base.entity.EntityBase;
import dk.schioler.event.base.entity.MedicineEvent;
import dk.schioler.event.base.entity.UNIT;

@Service
public class MedicineEventTableImpl<T extends MedicineEvent> extends BaseEventTableImpl<T> implements MedicineEventTable<T> {

   public MedicineEventTableImpl() {
      super();

      insertColumns.add(FLD_NOTE);
      insertColumns.add(FLD_DOSE);
      insertColumns.add(FLD_UNIT);
      insertColumns.add(FLD_MEDICINE_ID);
      insertColumns.add(FLD_MEDICINE_EVENT_TEMPLATE_ID);

      selectColumns.add(FLD_NOTE);
      selectColumns.add(FLD_DOSE);
      selectColumns.add(FLD_UNIT);

      orderByColumns.add(0, FLD_EVENT_CREATED_TS);
   }

   @Override
   public String getTableName() {
      return MedicineEventTable.TABLE;
   }
   
   @Override
   public RowMapper<MedicineEvent> getRowMapper() {

      return new MedicineEventRowMapper<MedicineEvent>();
   }
 

   @Override
   public Map<String, Object> getInsertMappings(EntityBase base) {
      Map<String, Object> map = super.getInsertMappings(base);

      if (base instanceof MedicineEvent) {
         MedicineEvent event = (MedicineEvent) base;

         map.put(FLD_EVENT_ID, event.getEventId());
         map.put(FLD_EVENT_ID, event.getEventId());
         map.put(FLD_EVENT_CREATED_TS, event.getEventCreatedTS());

         map.put(FLD_NOTE, event.getNote());
         map.put(FLD_DOSE, event.getDose());
         map.put(FLD_UNIT, event.getUnit().toString());
      } else {
      }
      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(EntityBase base) {
      Map<String, Object> map = super.getUpdateMappings(base);

      if (base instanceof MedicineEvent) {
         MedicineEvent event = (MedicineEvent) base;

//         Integer eventId = event.getEventId();
//         if (eventId != null) {
         map.put(FLD_EVENT_ID, event.getEventId());
//         }

         LocalDateTime eventCreatedTS = event.getEventCreatedTS();

//         if (eventCreatedTS != null) {
         map.put(FLD_EVENT_CREATED_TS, eventCreatedTS);
//         }

         map.put(FLD_NOTE, event.getNote());
         map.put(FLD_DOSE, event.getDose());
         map.put(FLD_UNIT, event.getUnit().toString());
      } else {
         throw new EventBaseException("retrieving update mappings, but found no valid src object");
      }
      return map;
   }

   
   
   
//   @Override
//   public RowMapper<T> getRowMapper() {
//      return null;
//   }
   
   

//   @Override
//   public List<String> getOrderBy() {
////      List<String> orderBy = new ArrayList<String>();
////      orderBy.add(FLD_NAME);
//
//      return orderBy;
//   }


   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(EntityBaseCriteria idCrit) {
      List<StringBuffer> crits = super.addLevelSpecificCriteriaFrom(idCrit);
      if (idCrit != null) {
         MedicineEventCriteria ec = (MedicineEventCriteria) idCrit;
         logger.debug("received Criteria=" + ec);

         List<Integer> eventIds = ec.getEventIds();
         List<String> eventTypes = ec.getEventTypes();
         List<Integer> medicineIds = ec.getMedicineIds();
         List<UNIT> units = ec.getUnits();
         List<Integer> eventTmplIds = ec.getEventTemplateIds();
         LocalDateTime eventTSStartDate = ec.getEventTSStartDate();
         LocalDateTime eventTSEndDate = ec.getEventTSEndDate();

         // BigDecimal doseMax = ec.getDoseMax();
//         BigDecimal doseMin = ec.getDoseMin();
//         if (doseMax != null && doseMin != null) {
//            StringBuffer sb = createDoseCriteria(FLD_DOSE_MIN, FLD_DOSE_MAX, FLD_DOSE);
//            if (sb != null) {
//               eventCrit.add(sb);
//            }
//         }

//         UNIT unit = ec.g
//         if (unit != null) {
//            String unitAsString = UNIT.unitAsString(unit);
//            if (StringUtils.isNotBlank(unitAsString)) {
//               StringBuffer sb = new StringBuffer();
//               sb.append(FLD_UNIT).append(SPACE).append(EQ).append(SPACE).append(BIND).append(FLD_UNIT);
//               eventCrit.add(sb);
//            }
//         }

//         List<Integer> eventTypeIds = ec.getEventTypeIds();
//         if (eventTypeIds.size() > 0) {
//            logger.debug("will add eventTypeIds to search:" + eventTypeIds);
//            StringBuffer eventTypeIdCriteria = createIntegerCriteria(eventTypeIds, FLD_EVENT_TYPE_ID);
//            if (eventTypeIdCriteria != null) {
//               eventCrit.add(eventTypeIdCriteria);
//            }
//         }

         if (eventTmplIds.size() > 0) {
            logger.debug("will add eventTmplIds to search:" + eventTmplIds);
            StringBuffer eventTemplateIdCriteria = createIntegerCriteria(FLD_EVENT_ID, eventTmplIds);
            if (eventTemplateIdCriteria != null) {
               crits.add(eventTemplateIdCriteria);
            }
         }

         if (eventTSStartDate != null && eventTSStartDate != null) {
            if (eventTSStartDate.isBefore(eventTSEndDate)) {
               StringBuffer criteria = createLocalDateTimeCriteria(FLD_EVENT_TS_START, FLD_EVENT_TS_END, FLD_EVENT_CREATED_TS);
               if (criteria != null) {
                  crits.add(criteria);
               }

            }
         }

      }

      return crits;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(EntityBaseCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria != null) {
         MedicineEventCriteria ec = (MedicineEventCriteria) criteria;
         
//         BigDecimal doseMin = ec.getDoseMin();
//         BigDecimal doseMax = ec.getDoseMax();
//         if (doseMin != null && doseMax != null) {
//            map.put(FLD_DOSE_MIN, doseMin);
//            map.put(FLD_DOSE_MAX, doseMax);
//         }

         List<Integer> eventTmplIds = ec.getEventTemplateIds();
         LocalDateTime eventTSStartDate = ec.getEventTSStartDate();
         LocalDateTime eventTSEndDate = ec.getEventTSEndDate();
         
         
         if (eventTmplIds != null && eventTmplIds.size() > 0) {
            Map<String, Object> integerMappings = createIntegerMappings(FLD_EVENT_ID, eventTmplIds);
            map.putAll(integerMappings);

         }

//         List<Integer> eventTypeIds = ec.getEventTypeIds();
//         if (eventTypeIds != null && eventTypeIds.size() > 0) {
//            Map<String, Object> integerMappings = createIntegerMappings(FLD_EVENT_TYPE_ID, eventTypeIds);
//            map.putAll(integerMappings);
//
//         }

         if (eventTSStartDate != null && eventTSEndDate != null) {
            map.put(FLD_EVENT_TS_START, eventTSStartDate);
            map.put(FLD_EVENT_TS_END, eventTSEndDate);

         }

         List<UNIT> units = ec.getUnits();
         if (units != null) {
            for (UNIT unit : units) {
               map.put(FLD_UNIT, UNIT.unitAsString(unit));               
            }
         }

      }

      return map;

   }

}
