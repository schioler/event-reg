package dk.schioler.shared.timeline.impl;

import java.time.LocalDateTime;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.TimelineDataContainer;

public class TimeLineSlotMinute extends TimelineSlotAbstract {


	public TimeLineSlotMinute(LocalDateTime startOfSlot, TimelineDataContainer dataContainer) {
		super( startOfSlot, TIMESLOT_LENGTH.MINUTE, dataContainer);
	}

		
	@Override
	protected LocalDateTime calculateEndOfSlot(LocalDateTime startOfSlot) {
		LocalDateTime endOfSlot =  LocalDateTime.of(
					startOfSlot.getYear(), 
					startOfSlot.getMonth(), 
					startOfSlot.getDayOfMonth(),
					startOfSlot.getHour(),
					startOfSlot.getMinute(),
					59);
		
		return endOfSlot;
	}








	
}
