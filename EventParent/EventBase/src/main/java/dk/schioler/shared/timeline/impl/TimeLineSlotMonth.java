package dk.schioler.shared.timeline.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.TimelineDataContainer;

public class TimeLineSlotMonth extends TimelineSlotAbstract {

	public TimeLineSlotMonth(LocalDateTime startOfSlot, TimelineDataContainer dataContainer) {
		super(startOfSlot, TIMESLOT_LENGTH.MONTH, dataContainer);
	}

	@Override
	protected LocalDateTime calculateEndOfSlot(LocalDateTime start) {

		LocalDate startDate = LocalDate.of(start.getYear(), start.getMonth(), start.getDayOfMonth());

		LocalDateTime endOfSlot = LocalDateTime.of(start.getYear(), start.getMonth(),
				start.getMonth().length(startDate.isLeapYear()), 23, 59);
		return endOfSlot;
	}

}
