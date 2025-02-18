package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractIdCriteria {

	private List<Integer> ids = new ArrayList<Integer>();

	private List<Integer> loginIds = new ArrayList<Integer>();
	
	private LocalDateTime createdStartTime;
	
	private LocalDateTime createdEndTime;

	public AbstractIdCriteria() {
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void addId(Integer id) {
		this.ids.add(id);
	}

	public List<Integer> getLoginIds() {
		return loginIds;
	}
	

	public void setLoginId(List<Integer> loginId) {
		this.loginIds = loginId;
	}
	
	public void addLoginId(Integer loginId) {
		loginIds.add(loginId);
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

   public void setLoginIds(List<Integer> loginIds) {
      this.loginIds = loginIds;
   }

   @Override
   public int hashCode() {
      return Objects.hash(createdEndTime, ids, loginIds, createdStartTime);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      AbstractIdCriteria other = (AbstractIdCriteria) obj;
      return Objects.equals(createdEndTime, other.createdEndTime) && Objects.equals(ids, other.ids) && Objects.equals(loginIds, other.loginIds)
            && Objects.equals(createdStartTime, other.createdStartTime);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("AbstractIdCriteria [ids=");
      builder.append(ids);
      builder.append(", loginIds=");
      builder.append(loginIds);
      builder.append(", startTime=");
      builder.append(createdStartTime);
      builder.append(", endTime=");
      builder.append(createdEndTime);
      builder.append("]");
      return builder.toString();
   }



	
		
	
}
