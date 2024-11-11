package dk.schioler.event.base.dao.criteria;

import java.util.Objects;

public class EventCriteria extends AbstractCriteria {

	private Integer eventTemplateId;

	public Integer getEventTemplateId() {
		return eventTemplateId;
	}

	public void setEventTemplateId(Integer eventTemplateId) {
		this.eventTemplateId = eventTemplateId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(eventTemplateId);
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
		EventCriteria other = (EventCriteria) obj;
		return Objects.equals(eventTemplateId, other.eventTemplateId);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventCriteria [eventTemplateId=");
		builder.append(eventTemplateId);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	
	
}
