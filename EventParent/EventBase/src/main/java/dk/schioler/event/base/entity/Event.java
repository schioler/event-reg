package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import dk.schioler.event.base.EventBaseException;

public class Event extends AbstractEntity {

	private String note;

	private LocalDateTime eventTS;

	private String dose;
	
	private String unit;
	
	private String shortName;

	public Event() {
		super(null, null, null);

	}

	public Event(Integer id, Integer loginId, String name) {
		super(id, loginId, name);
	}


	@Override
	public AbstractEntity instantiateParent() {
		return new EventTemplate();
	}
	
	
	
//	public EventTemplate getEventTemplateId() {
//		return (EventTemplate) this.getParent();
//	}
//	
//	public void setEventTemplateId(Integer eventTemplateId) {
//		this.setParentId(eventTemplateId);
//	}
	
//	public Integer getEventTemplateId() {
//		Integer retVal = null;
//		if (this.eventTemplate != null) {
//			retVal = eventTemplate.getId();
//		}
//		return retVal;
//	}
//
//	public void setEventTemplateId(Integer eventTemplateId) {
//		setEventTemplate(eventTemplateId, null);
//	}
//
//	public void setEventTemplate(EventTemplate eventTemp) {
//		setEventTemplate(null, eventTemp);
//	}
//
//	private void setEventTemplate(Integer eTmplId, EventTemplate eventTemplate) {
//		
//		if(this.eventTemplate != null) {
//			if(this.eventTemplate.equals(eventTemplate)) {
//				return;
//			}
//		}
//		if (eTmplId != null) {
//			if (eventTemplate != null) {
//				// provided eventType is not null
//				if (this.eventTemplate != null) {
//					this.eventTemplate.removeEvent(this);
//				}
//				this.eventTemplate = eventTemplate;
//				this.eventTemplate.setId(eTmplId);
//				this.eventTemplate.addEvent(this);
//			} else {
//				// provided eventType is null
//				if (this.eventTemplate != null) {
//					this.eventTemplate.removeEvent(this);
//				}
//				this.eventTemplate = new EventTemplate();
//				this.eventTemplate.setId(eTmplId);
//				this.eventTemplate.addEvent(this);
//			}
//		} else {
//			if (eventTemplate != null) {
//				// provided eventType is not null
//				if (this.eventTemplate != null) {
//					this.eventTemplate .removeEvent(this);
//				}
//				this.eventTemplate = eventTemplate;
////				this.eventType.setId(eTypeId);
//				this.eventTemplate.addEvent(this);
//			} else {
//				if (this.eventTemplate != null) {
//					this.eventTemplate.removeEvent(this);
//				}
//				this.eventTemplate = new EventTemplate();
////				this.eventType.setId(eTypeId);
//				this.eventTemplate.addEvent(this);
//			}
//		}
//	}

	@Override
	public List<AbstractEntity> getChildren() {
		throw new EventBaseException("Events has no children");
	}



	@Override
	public void removeChild(AbstractEntity child) {
		throw new EventBaseException("Events has no children");
	}

	@Override
	public void addChild(AbstractEntity child) {
		throw new EventBaseException("Events has no children");
		
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
		result = prime * result + Objects.hash(dose, eventTS, note, shortName, unit);
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
				&& Objects.equals(note, other.note) && Objects.equals(shortName, other.shortName)
				&& Objects.equals(unit, other.unit);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Event [ ");
		builder.append(" note=").append(note);
		builder.append(", eventTS=").append(eventTS);
		builder.append(", dose=").append(dose);
		builder.append(", unit=").append(unit);
		builder.append(", shortName=").append(shortName);
		builder.append("]");
		return builder.toString();
	}





		
}
