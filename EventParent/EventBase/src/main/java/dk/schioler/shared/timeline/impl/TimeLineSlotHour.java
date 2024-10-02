package dk.schioler.shared.timeline.impl;

import java.time.LocalDateTime;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.TimelineDataContainer;

public class TimeLineSlotHour extends TimelineSlotAbstract {

	public TimeLineSlotHour(LocalDateTime startOfSlot, TimelineDataContainer dataContainer) {
		super(startOfSlot, TIMESLOT_LENGTH.HOUR, dataContainer);
	}

	

	protected LocalDateTime calculateEndOfSlot(LocalDateTime start) {
		LocalDateTime endOfSlot = LocalDateTime.of(start.getYear(), start.getMonthValue(), start.getDayOfMonth(), start.getHour(), 59);
								
		return endOfSlot;
	}

}
