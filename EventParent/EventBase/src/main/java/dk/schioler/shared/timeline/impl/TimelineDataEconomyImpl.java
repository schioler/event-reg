package dk.schioler.shared.timeline.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TimelineDataEconomyImpl extends TimelineDataAbstractImpl {

	public TimelineDataEconomyImpl(String name, LocalDateTime dateTime, BigDecimal bigDecimal) {
		super(name, dateTime, bigDecimal);
	}

	public BigDecimal getBigDecimalValue() {
		return (BigDecimal) getValue();
	}
}
