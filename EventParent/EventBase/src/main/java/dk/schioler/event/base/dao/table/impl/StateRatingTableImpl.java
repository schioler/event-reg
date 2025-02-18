package dk.schioler.event.base.dao.table.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import dk.schioler.event.base.dao.criteria.AbstractIdCriteria;
import dk.schioler.event.base.dao.criteria.StateRatingCriteria;
import dk.schioler.event.base.dao.rowmapper.impl.StateRatingRowMapper;
import dk.schioler.event.base.dao.table.StateRatingTable;
import dk.schioler.event.base.entity.StateRating;

public class StateRatingTableImpl extends AbstractSQLTableId<StateRating> implements StateRatingTable {

   public StateRatingTableImpl() {
      super();
      insertColumns.add(FLD_STATE_ASPECT_ID);
      insertColumns.add(FLD_STATE_REGISTRATION_ID);
      insertColumns.add(FLD_RATING);


      selectColumns.add(FLD_STATE_ASPECT_ID);
      selectColumns.add(FLD_STATE_REGISTRATION_ID);
      selectColumns.add(FLD_RATING);
//      selectColumns.add(FLD_STATE_RATING_TS);

//		orderByColumns.add(FLD_ID);
   }

   @Override
   public String getTableName() {
      return TABLE;
   }

   @Override
   public Map<String, Object> getInsertMappings(StateRating type) {
      Map<String, Object> map = super.getInsertMappings(type);
      map.put(FLD_STATE_ASPECT_ID, type.getStateAspectId());
      map.put(FLD_STATE_REGISTRATION_ID, type.getStateRegistrationId());
      map.put(FLD_RATING, type.getRating());
      return map;
   }

   @Override
   public Map<String, Object> getUpdateMappings(StateRating type) {
      Map<String, Object> map = super.getUpdateMappings(type);
      if (type.getStateAspectId() != null) {
         map.put(FLD_STATE_ASPECT_ID, type.getStateAspectId());
      }

      if (type.getStateRegistrationId() != null) {
         map.put(FLD_STATE_REGISTRATION_ID, type.getStateRegistrationId());
      }

      if (type.getRating() != null) {
         map.put(FLD_RATING, type.getRating());
      }

      return map;
   }

   @Override
   public RowMapper<StateRating> getRowMapper() {
      return new StateRatingRowMapper();
   }

   @Override
   public List<StringBuffer> addLevelSpecificCriteriaFrom(AbstractIdCriteria idCrit) {
      List<StringBuffer> sql = super.addLevelSpecificCriteriaFrom(idCrit);

      if (idCrit != null) {
         StateRatingCriteria ratingCrit = (StateRatingCriteria) idCrit;
         
         List<Integer> stateAspectIdList = ratingCrit.getStateAspectIdList();
         if (stateAspectIdList != null && stateAspectIdList.size() > 0) {
            StringBuffer aspectCriteria = createIntegerCriteria(stateAspectIdList, FLD_STATE_ASPECT_ID);
            if (aspectCriteria != null) {
               sql.add(aspectCriteria);
            }
         }

         List<Integer> stateRegistrationIdList = ratingCrit.getStateRegistrationIdList();
         if (stateRegistrationIdList != null && stateRegistrationIdList.size() > 0) {
            StringBuffer regCriteria = createIntegerCriteria(stateRegistrationIdList, FLD_STATE_REGISTRATION_ID);
            if (regCriteria != null) {
               sql.add(regCriteria);
            }
         }
         
         Integer rating = ratingCrit.getRating();
         if (rating != null) {
            StringBuffer sb = new StringBuffer();
            sb.append(FLD_RATING).append(SPACE).append(EQ).append(SPACE).append(BIND).append(FLD_RATING);
            sql.add(sb);
         }

      }
      return sql;
   }

   @Override
   public Map<String, Object> addLevelSpecificRetrieveMappings(AbstractIdCriteria criteria) {
      Map<String, Object> map = super.addLevelSpecificRetrieveMappings(criteria);
      if (criteria != null) {
         StateRatingCriteria ratingCrit = (StateRatingCriteria) criteria;
         
         Integer rating = ratingCrit.getRating();
         if (rating != null) {
            map.put(FLD_RATING, rating);
         }
         
         List<Integer> stateAspectIdList = ratingCrit.getStateAspectIdList();
         if (stateAspectIdList != null && stateAspectIdList.size() > 0) {
            if (stateAspectIdList.size() == 1) {
               map.put(FLD_STATE_ASPECT_ID, stateAspectIdList.get(0));
            } else if (stateAspectIdList.size() > 1) {
               int size = stateAspectIdList.size();
               for (int i = 1; i <= size; i++) {
                  map.put(FLD_STATE_ASPECT_ID + i, stateAspectIdList.get(i - 1));
               }
            }

         }

         List<Integer> stateRegIdList = ratingCrit.getStateRegistrationIdList();
         if (stateRegIdList != null && stateRegIdList.size() > 0) {
            if (stateRegIdList.size() == 1) {
               map.put(FLD_STATE_REGISTRATION_ID, stateRegIdList.get(0));
            } else if (stateRegIdList.size() > 1) {
               int size = stateRegIdList.size();
               for (int i = 1; i <= size; i++) {
                  map.put(FLD_STATE_REGISTRATION_ID + i, stateRegIdList.get(i - 1));
               }
            }
         }         
      }
      return map;
   }
}
