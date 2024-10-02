package dk.schioler.shared.timeline.impl;

import java.time.LocalDateTime;

import dk.schioler.event.base.entity.Event;

public class TimelineDataEventImpl extends TimelineDataAbstractImpl {

	public TimelineDataEventImpl(String name, LocalDateTime dateTime, Integer value) {
		super(name, dateTime, value );
	}
	
	public TimelineDataEventImpl(String name, LocalDateTime dateTime) {
		super(name, dateTime, Integer.valueOf(1) );
	}
	
	public TimelineDataEventImpl(Event e) {
		super(e.getName(), e.getEventTS(), Integer.valueOf(1) );
	}

	public Integer getIntegerValue() {
		return (Integer) getValue();
	}

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
		builder.append("TimelineDataEventImpl [getIntegerValue()=");
		builder.append(getIntegerValue());
		builder.append(", getDateTime()=");
		builder.append(getDateTime());
		builder.append(", getName()=");
		builder.append(getCategory());
		builder.append(", getValue()=");
		builder.append(getValue());
		builder.append("]");
		return builder.toString();
	}
	
	
}
