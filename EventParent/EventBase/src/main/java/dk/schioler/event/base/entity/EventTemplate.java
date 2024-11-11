package dk.schioler.event.base.entity;

import java.util.Objects;

import dk.schioler.event.base.EventBaseException;

public class EventTemplate extends AbstractEntity {

	private String shortName;

	private String description;

	private String unit;

	private String dose;

	private Integer sortOrder;

	private boolean isFavorite;

	public EventTemplate() {
		super(null, null, null);

	}

	public EventTemplate(Integer id, Integer loginId, String name) {
		super(id, loginId, name);
	}

	@Override
	public AbstractEntity instantiateParent() {
		return new EventType();
	}

	@Override
	public void setParent(AbstractEntity parent) {
		if (parent instanceof EventType) {
			super.setParent(parent);
		} else {
			throw new EventBaseException("EventTemplate can only have EventType as parent");
		}
	}

	@Override
	public void addChild(AbstractEntity child) {
		if (child instanceof Event) {
			super.addChild(child);
		} else {
			throw new EventBaseException("EventTemplate can only have Event as child");
		}

	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("[ ");
		builder.append("shortName=").append(shortName);
		builder.append(", description=").append(description);
		builder.append(", unit=").append(unit);
		builder.append(", dose=").append(dose);
		builder.append(", sortOrder=").append(sortOrder);
		builder.append(", isFavorite=").append(isFavorite);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, dose, isFavorite, shortName, sortOrder, unit);
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
		EventTemplate other = (EventTemplate) obj;
		return Objects.equals(description, other.description) && Objects.equals(dose, other.dose)
				&& isFavorite == other.isFavorite && Objects.equals(shortName, other.shortName)
				&& Objects.equals(sortOrder, other.sortOrder) && Objects.equals(unit, other.unit);
	}

}
