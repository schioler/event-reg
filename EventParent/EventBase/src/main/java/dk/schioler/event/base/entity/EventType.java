package dk.schioler.event.base.entity;

import java.util.Objects;

import org.springframework.stereotype.Component;

import dk.schioler.event.base.EventBaseException;

@Component
public class EventType extends AbstractEntity {

	private String shortName;

	private String description;

	public EventType() {
		super(null, null, null);
	}

	public EventType(Integer id, Integer loginId, String name) {
		super(id, loginId, name);
	}

//	@Override
//	public AbstractEntity getParent() {
//		throw new EventBaseException("EventType do not have parent");
//	}
//
//	@Override
//	public Integer getParentId() {
//		throw new EventBaseException("EventType do not have parent");
//	}
//
//	@Override
//	public void setParentId(Integer id) {
//		throw new EventBaseException("EventType do not have parent");
//	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setParent(AbstractEntity parent) {
		if (parent instanceof XMLRootElement) {
			super.setParent(parent);
		} else {
			throw new EventBaseException("EventTypes has only one type of parent: " + XMLRootElement.class.getName());
		}
	}

	@Override
	public void removeChild(AbstractEntity child) {
		if (child instanceof EventTemplate) {
			super.removeChild(child);
		} else {
			throw new EventBaseException("EventTypes has only one type of children: " + EventTemplate.class.getName());
		}
	}

	@Override
	public void addChild(AbstractEntity child) {
		if (child instanceof EventTemplate) {
			super.addChild(child);
		} else {
			throw new EventBaseException("EventTypes has only one type of children: " + EventTemplate.class.getName());
		}

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, shortName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EventType other = (EventType) obj;
		return Objects.equals(description, other.description) && Objects.equals(shortName, other.shortName);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("     EventType [");
		builder.append("shortName=").append(shortName);
		builder.append(", description=").append(description);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public AbstractEntity instantiateParent() {
		return new XMLRootElement();
	}

}
