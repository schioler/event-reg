package dk.schioler.event.base.dao.table;

import dk.schioler.event.base.entity.StateRating;

public interface StateRatingTable extends BaseSQLTableId<StateRating> {
   public static final String TABLE = "STATE_RATING";

   public static final String FLD_STATE_ASPECT_ID= "STATE_ASPECT_ID";
   public static final String FLD_STATE_REGISTRATION_ID = "STATE_REGISTRATION_ID";
   public static final String FLD_RATING = "RATING";
}