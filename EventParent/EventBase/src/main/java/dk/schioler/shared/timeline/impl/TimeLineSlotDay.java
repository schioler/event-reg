package dk.schioler.shared.timeline.impl;

import java.time.LocalDateTime;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.TimelineDataContainer;

public class TimeLineSlotDay extends TimelineSlotAbstract {


	public TimeLineSlotDay( LocalDateTime startOfSlot, TimelineDataContainer dataContainer ) {
		super( startOfSlot, TIMESLOT_LENGTH.DAY, dataContainer);
	}

	

	
	protected LocalDateTime calculateEndOfSlot(LocalDateTime start) {
			return LocalDateTime.of(
				start.getYear(), 
				start.getMonth(), 
				start.getDayOfMonth(),
				23,
				59
				);
	}

	
	
}
