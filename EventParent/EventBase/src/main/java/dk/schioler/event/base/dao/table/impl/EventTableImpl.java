package dk.schioler.event.base.dao.table.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.criteria.EventCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.EventRowMapper;
import dk.schioler.event.base.dao.table.EventTable;
import dk.schioler.event.base.entity.Event;
import dk.schioler.event.base.entity.UNIT;

public class EventTableImpl extends AbstractSQLTableParentChild<Event> implements EventTable {

   public EventTableImpl() {
      super();

      insertColumns.add(FLD_EVENT_TEMPLATE_ID);
      insertColumns.add(FLD_NOTE);
      insertColumns.add(FLD_DOSE);
      insertColumns.add(FLD_UNIT);
      insertColumns.add(FLD_EVENT_TS);

      selectColumns.add(FLD_EVENT_TEMPLATE_ID);
      selectColumns.add(FLD_NOTE);
      selectColumns.add(FLD_DOSE);
      selectColumns.add(FLD_UNIT);
      selectColumns.add(FLD_EVENT_TS);

   }

   @Override
   public String getTableName() {
      return TABLE;
   }

   @Override
   public Map<String, Object> getInsertMappings(Event event) {
      Map<String, Object> map = super.getInsertMappings(event);
      map.put(FLD_EVENT_TEMPLATE_ID, event.getParentId());

      if (event.getEventTS() != null) {
         map.put(FLD_EVENT_TS, event.getEventTS());
      }

      map.put(FLD_NOTE, event.getNote());
      map.put(FLD_DOSE, event.getDose());
      map.put(FLD_UNIT, event.getUnit().toString());

      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(Event event) {
      Map<String, Object> map = super.getUpdateMappings(event);
      map.put(FLD_EVENT_TEMPLATE_ID, event.getParentId());

      if (event.getEventTS() != null) {
         map.put(FLD_EVENT_TS, event.getEventTS());
      }

      map.put(FLD_NOTE, event.getNote());
      map.put(FLD_DOSE, event.getDose());
      map.put(FLD_UNIT, event.getUnit().toString());
      return map;
   }

   @Override
   public RowMapper<Event> getRowMapper() {
      return new EventRowMapper();
   }

   @Override
   public List<String> getOrderBy() {
      List<String> orderBy = new ArrayList<String>();
      orderBy.add(FLD_NAME);

      return orderBy;
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      List<StringBuffer> eventCrit = super.addLevelSpecificCriteriaFrom(idCrit);
      if (idCrit != null) {
         EventCriteria ec = (EventCriteria) idCrit;

         BigDecimal doseMax = ec.getDoseMax();
         BigDecimal doseMin = ec.getDoseMin();
         if (doseMax != null && doseMin != null) {
            StringBuffer sb = createDoseCriteria(FLD_DOSE_MIN, FLD_DOSE_MAX, FLD_DOSE);
            if (sb != null) {
               eventCrit.add(sb);
            }
         }

         UNIT unit = ec.getUnit();
         if (unit != null) {
            String unitAsString = UNIT.unitAsString(unit);
            if (StringUtils.isNotBlank(unitAsString)) {
               StringBuffer sb = new StringBuffer();
               sb.append(FLD_UNIT).append(SPACE).append(EQ).append(SPACE).append(BIND).append(FLD_UNIT);
               eventCrit.add(sb);
            }
         }
         
         
         
         List<Integer> eventTemplateIds = ec.getEventTemplateIds();
         if (eventTemplateIds.size() > 0) {
            StringBuffer eventTemplateIdCriteria = createIntegerCriteria(eventTemplateIds, FLD_EVENT_TEMPLATE_ID);
            if (eventTemplateIdCriteria != null) {
               eventCrit.add(eventTemplateIdCriteria);
            }
         }



         LocalDateTime eventTSStartDate = ec.getEventTSStartDate();
         LocalDateTime eventTSEndDate = ec.getEventTSEndDate();
         if (eventTSStartDate != null && eventTSStartDate != null) {
            if (eventTSStartDate.isBefore(eventTSEndDate)) {
               StringBuffer criteria = createLocalDateTimeCriteria(FLD_EVENT_TS_START, FLD_EVENT_TS_END, FLD_EVENT_TS);
               if(criteria != null) {
                  eventCrit.add(criteria);
               }
               
            }
         }

      }

      return eventCrit;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);

      if (criteria != null) {
         EventCriteria ec = (EventCriteria) criteria;

         BigDecimal doseMin = ec.getDoseMin();
         BigDecimal doseMax = ec.getDoseMax();
         if (doseMin != null && doseMax != null) {
            map.put(FLD_DOSE_MIN, doseMin);
            map.put(FLD_DOSE_MAX, doseMax);
         }

         List<Integer> eventTemplateIds = ec.getEventTemplateIds();
         if (eventTemplateIds != null && eventTemplateIds.size() > 0) {
            Map<String, Object> integerMappings = createIntegerMappings(FLD_EVENT_TEMPLATE_ID, eventTemplateIds);
            map.putAll(integerMappings);

         }

         LocalDateTime eventTSStartDate = ec.getEventTSStartDate();
         LocalDateTime eventTSEndDate = ec.getEventTSEndDate();
         if (eventTSStartDate != null && eventTSEndDate != null) {
            map.put(FLD_EVENT_TS_START, eventTSStartDate);
            map.put(FLD_EVENT_TS_END, eventTSEndDate);
          
         }

         UNIT unit = ec.getUnit();
         if (unit != null) {
            map.put(FLD_UNIT, UNIT.unitAsString(unit));
         }
         
      }

      return map;

   }

}
