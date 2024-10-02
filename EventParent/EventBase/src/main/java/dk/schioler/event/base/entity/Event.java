package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event extends AbstractEntity {

	private Integer eventTemplateId;

	private String note;

	private LocalDateTime eventTS;

	private String dose;
	
	private String unit;
	
	private String name;
	
	private String shortName;
	

	public Event() {
		super(null);

	}

	
	


	public Integer getEventTemplateId() {
		return eventTemplateId;
	}

	public void setEventTemplateId(Integer eventTemplateId) {
		this.eventTemplateId = eventTemplateId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public LocalDateTime getEventTS() {
		return eventTS;
	}

	public void setEventTS(LocalDateTime eventTS) {
		this.eventTS = eventTS;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dose, eventTS, eventTemplateId, name, note, shortName, unit);
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
		Event other = (Event) obj;
		return Objects.equals(dose, other.dose) && Objects.equals(eventTS, other.eventTS)
				&& Objects.equals(eventTemplateId, other.eventTemplateId) && Objects.equals(name, other.name)
				&& Objects.equals(note, other.note) && Objects.equals(shortName, other.shortName)
				&& Objects.equals(unit, other.unit);
	}





	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Event [eventTemplateId=");
		builder.append(eventTemplateId);
		builder.append(", note=");
		builder.append(note);
		builder.append(", eventTS=");
		builder.append(eventTS);
		builder.append(", dose=");
		builder.append(dose);
		builder.append(", unit=");
		builder.append(unit);
		builder.append(", name=");
		builder.append(name);
		builder.append(", shortName=");
		builder.append(shortName);
		builder.append("]");
		return builder.toString();
	}



		
}
