package dk.schioler.shared.timeline;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TimelineData {

	public LocalDateTime getDateTime();

	public String getCategory();
	
	public Number getValue();
	
	public Integer getIntValue();
	
	public Double getDoubleValue();
	
	public BigDecimal getBigDecimalValue();
	
	
}