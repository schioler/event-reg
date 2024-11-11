package dk.schioler.event.base.dao.criteria;

import java.util.Objects;

public class EventTemplateCriteria extends AbstractCriteria {

	private Boolean favourite;
	
	private Integer eventTypeId;

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public Boolean isFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(eventTypeId, favourite);
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
		EventTemplateCriteria other = (EventTemplateCriteria) obj;
		return Objects.equals(eventTypeId, other.eventTypeId) && favourite == other.favourite;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventTemplateCriteria [favourite=");
		builder.append(favourite);
		builder.append(", eventTypeId=");
		builder.append(eventTypeId);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append(", getLoginIds()=");
		builder.append(getLoginIds());
		builder.append(", getName()=");
		builder.append(getName());
		builder.append(", getStartTime()=");
		builder.append(getStartTime());
		builder.append(", getEndTime()=");
		builder.append(getEndTime());
		builder.append("]");
		return builder.toString();
	}
	


}
