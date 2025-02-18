package dk.schioler.event.base.dao.criteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StateRatingCriteria extends AbstractIdCriteria {

	private List<Integer> stateAspectIdList = new ArrayList<Integer>();
	
	private List<Integer> stateRegistrationIdList = new ArrayList<Integer>();
	
	private Integer rating = null;

   public List<Integer> getStateAspectIdList() {
      return stateAspectIdList;
   }

   public void addToStateAspectIdList(Integer stateAspectId) {
      this.stateAspectIdList.add(stateAspectId);
   }

   public List<Integer> getStateRegistrationIdList() {
      return stateRegistrationIdList;
   }

   public void addToStateRegistrationIdList(Integer stateRegistrationId) {
      this.stateRegistrationIdList.add(stateRegistrationId);
   }

   public Integer getRating() {
      return rating;
   }

   public void setRating(Integer rating) {
      this.rating = rating;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(rating, stateAspectIdList, stateRegistrationIdList);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      StateRatingCriteria other = (StateRatingCriteria) obj;
      return Objects.equals(rating, other.rating) && Objects.equals(stateAspectIdList, other.stateAspectIdList)
            && Objects.equals(stateRegistrationIdList, other.stateRegistrationIdList);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder(super.toString());
      builder.append("StateRatingCriteria [stateAspectIdList=");
      builder.append(stateAspectIdList);
      builder.append(", stateRegistrationIdList=");
      builder.append(stateRegistrationIdList);
      builder.append(", rating=");
      builder.append(rating);
      builder.append("]");
      return builder.toString();
   }
	
	
}
