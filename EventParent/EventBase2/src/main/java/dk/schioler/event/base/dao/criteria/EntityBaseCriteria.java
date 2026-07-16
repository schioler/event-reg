package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public abstract class EntityBaseCriteria {

//   public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.of(1970, 1, 1, 1, 1);

//   public static final String DEFAULT_STRING = "DEFAULT";

   private List<Integer> ids = new ArrayList<Integer>();

   private List<Integer> ownerIds = new ArrayList<Integer>();

   private LocalDateTime createdStartTime = null;

   private LocalDateTime createdEndTime = null;

   public EntityBaseCriteria() {
   }

   public List<Integer> getIds() {
      return ids;
   }

   public void addId(Integer id) {
      this.ids.add(id);
   }

   public List<Integer> getOwnerIds() {
      return ownerIds;
   }

//	public void setLoginId(List<Integer> loginId) {
//		this.ownerIds = loginId;
//	}

   public void addOwnerId(Integer ownerId) {
      ownerIds.add(ownerId);
   }

   public LocalDateTime getCreatedStart() {
      return createdStartTime;
   }

   public void setCreatedStartTime(LocalDateTime startTime) {
      this.createdStartTime = startTime;
   }

   public LocalDateTime getCreatedEndTime() {
      return createdEndTime;
   }

   public void setCreatedEndTime(LocalDateTime endTime) {
      this.createdEndTime = endTime;
   }

   public void setIds(List<Integer> ids) {
      this.ids = ids;
   }

//   public void setLoginIds(List<Integer> loginIds) {
//      this.ownerIds = loginIds;
//   }

}
