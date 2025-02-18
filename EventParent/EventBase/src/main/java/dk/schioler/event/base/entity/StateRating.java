package dk.schioler.event.base.entity;

import java.time.LocalDateTime;

public class StateRating extends AbstractEntityId {

   private Integer stateAspectId;
   private Integer stateRegistrationId;
   private Integer rating;
   private LocalDateTime ratingTS;

   public StateRating() {
      super();
   }

   public Integer getStateRegistrationId() {
      return stateRegistrationId;
   }

   public void setStateRegistrationId(Integer stateRegistrationId) {
      this.stateRegistrationId = stateRegistrationId;
   }

   public Integer getStateAspectId() {
      return stateAspectId;
   }

   public void setStateAspectId(Integer stateAspectId) {
      this.stateAspectId = stateAspectId;
   }
   
   public LocalDateTime getRatingTS() {
      return ratingTS;
   }

   public void setRatingTS(LocalDateTime statusTS) {
      this.ratingTS = statusTS;
   }

   public Integer getRating() {
      return rating;
   }

   public void setRating(Integer rating) {
      this.rating = rating;
   }

   
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("StatusRegistration ["+ super.toString());
      builder.append(", statusElementId=").append(stateAspectId);
      builder.append(", rating=").append(rating);
      builder.append(", registrationTime=").append(ratingTS);
      builder.append("]");
      return builder.toString();
   }


   

}
