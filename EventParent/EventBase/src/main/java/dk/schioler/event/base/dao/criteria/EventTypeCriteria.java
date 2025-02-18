package dk.schioler.event.base.dao.criteria;

import org.springframework.stereotype.Component;

@Component
public class EventTypeCriteria extends AbstractNameCriteria {

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventTypeCriteria [toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}


	
}
