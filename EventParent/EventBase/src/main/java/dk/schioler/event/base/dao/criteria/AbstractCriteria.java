package dk.schioler.event.base.dao.criteria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public abstract class AbstractCriteria {

	private Integer id;
	
	private String name;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;

	private List<Integer> loginIds = new ArrayList<Integer>();
	
	public AbstractCriteria() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endTime, id, loginIds, name, startTime);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCriteria other = (AbstractCriteria) obj;
		return Objects.equals(endTime, other.endTime) && Objects.equals(id, other.id)
				&& Objects.equals(loginIds, other.loginIds) && Objects.equals(name, other.name)
				&& Objects.equals(startTime, other.startTime);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AbstractEntity [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", startTime=");
		builder.append(startTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", loginIds=");
		builder.append(loginIds);
		builder.append("]");
		return builder.toString();
	}

	
		
	
}
