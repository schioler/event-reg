package dk.schioler.event.base.entity;

import java.util.Objects;

public class EventTemplate extends AbstractEntity {

	private Integer eventTypeId;
	
	private String name;

	private String shortName;

	private String description;

	private String unit;

	private String dose;
	
	
	public EventTemplate() {
		super(null);
	
	}

	
	
	
	public EventTemplate(Integer id, Integer eventTypeId, String name, String shortName, String description,
			String unit, String dose) {
		super(id);
		this.eventTypeId = eventTypeId;
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		this.unit = unit;
		this.dose = dose;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Integer getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, dose, eventTypeId, name, shortName, unit);
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
				&& Objects.equals(eventTypeId, other.eventTypeId) && Objects.equals(name, other.name)
				&& Objects.equals(shortName, other.shortName) && Objects.equals(unit, other.unit);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EventTemplate [eventTypeId=");
		builder.append(eventTypeId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", dose=");
		builder.append(dose);
		builder.append(", getId()=");
		builder.append(getId());
		builder.append("]");
		return builder.toString();
	}

		
}
