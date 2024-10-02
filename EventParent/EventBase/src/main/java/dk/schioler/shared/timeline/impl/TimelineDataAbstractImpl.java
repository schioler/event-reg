package dk.schioler.shared.timeline.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.shared.timeline.TimelineData;

public abstract class TimelineDataAbstractImpl implements TimelineData {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private String name;
	private LocalDateTime dateTime;
	private Number value;
	
	public TimelineDataAbstractImpl(String name, LocalDateTime dateTime, Number value) {
		this.dateTime = dateTime;
		this.name = name.trim();
		this.value = value;
	}

	@Override
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String getCategory() {
		return name;
	}

	@Override
	public Number getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateTime, name, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimelineDataAbstractImpl other = (TimelineDataAbstractImpl) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(name, other.name)
				&& Objects.equals(value, other.value);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TimelineDataAbstractImpl [name=");
		builder.append(name);
		builder.append(", dateTime=");
		builder.append(dateTime);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Integer getIntValue() {
		return Integer.valueOf(value.intValue());
	}

	@Override
	public Double getDoubleValue() {
		return Double.valueOf(value.doubleValue());
	}

	@Override
	public BigDecimal getBigDecimalValue() {
		if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		}  else {
			BigDecimal bd = new BigDecimal(getDoubleValue());
			return bd;
			
		}
	
	}

	
	
}
