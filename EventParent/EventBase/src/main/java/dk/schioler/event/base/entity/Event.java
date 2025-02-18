package dk.schioler.event.base.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Event extends AbstractEntityParentChild {

	private String note;

	private LocalDateTime eventTS;

	private BigDecimal dose;

	private UNIT unit;



	public Event() {
      super();
   }
	
   public Event(String note, LocalDateTime eventTS, BigDecimal dose, UNIT unit) {
      super();
      this.note = note;
      this.eventTS = eventTS;
      this.dose = dose;
      this.unit = unit;
   }




   @Override
	public List<AbstractEntityParentChild> getChildren() {
		throw new EventEntityException("Events has no children");
	}

	@Override
	public void removeChild(AbstractEntityParentChild child) {
		throw new EventEntityException("Events has no children");
	}

	@Override
	public void addChild(AbstractEntityParentChild child) {
		throw new EventEntityException("Events has no children");

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

	public BigDecimal getDose() {
		return dose;
	}

	public void setDose(BigDecimal dose) {
		this.dose = dose;
	}




	public UNIT getUnit() {
      return unit;
   }

   public void setUnit(UNIT unit) {
      this.unit = unit;
   }

   /**
	 * Event has no children...
	 */
	@Override
	public String getChildString() {
	   throw new EventEntityException("Event can have no children...");
//		return "Event has no children...";
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
		builder.append("]");
		return builder.toString();
	}

	 
	
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(dose, eventTS, note, unit);
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
      return Objects.equals(dose, other.dose) && Objects.equals(eventTS, other.eventTS) && Objects.equals(note, other.note) && unit == other.unit;
   }

   @Override
   public AbstractEntityParentChild instantiateParent() {
      return new EventTemplate();
   }


}
