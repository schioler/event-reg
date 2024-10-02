package dk.schioler.shared.timeline.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.TimelineData;
import dk.schioler.shared.timeline.TimelineDataContainer;
import dk.schioler.shared.timeline.TimelineSlot;

public abstract class TimelineSlotAbstract implements TimelineSlot {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private final LocalDateTime startOfSlot;

	private LocalDateTime endOfSlot;

	private final TIMESLOT_LENGTH interval;

	private TimelineDataContainer timeslotDataContainer;

	// *****************************************************
	public TimelineSlotAbstract(LocalDateTime startOfSlot, TIMESLOT_LENGTH interval,
			TimelineDataContainer timeslotDataContainer) {
		this.interval = interval;
		this.startOfSlot = startOfSlot;
		this.endOfSlot = calculateEndOfSlot(startOfSlot);
		this.timeslotDataContainer = timeslotDataContainer;
	}
	// *****************************************************
	
	public TimelineDataContainer getTimeSlotDataContainer() {
		return timeslotDataContainer;
	}


	protected abstract LocalDateTime calculateEndOfSlot(LocalDateTime startOfSlot);

//	@Override
//	public void addTimelineData(TimelineData newData) {
//		logger.debug("addTimelineData: - will add timelineData="+ newData);
//		this.timeslotDataContainer.addTimelineData(newData);
//	}
	
	@Override
	public boolean dataMatchesSlotTime(TimelineData timelineData) {
		LocalDateTime dateTime = timelineData.getDateTime();
		if ((startOfSlot.isBefore(dateTime) && endOfSlot.isAfter(dateTime)) || startOfSlot.equals(dateTime)
				|| endOfSlot.equals(dateTime)) {
			return true;
		} else {
			return false;			
		}
	}


	@Override
	public LocalDateTime getStartOfSlot() {
		return startOfSlot;
	}

	@Override
	public LocalDateTime getEndOfSlot() {
		return endOfSlot;
	}

	@Override
	public TIMESLOT_LENGTH getInterval() {
		return interval;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TimeLineSlot[");
		builder.append("startOfSlot=").append(startOfSlot).append(", ");
		builder.append("endOfSlot=").append(endOfSlot).append(", ");
		builder.append("interval=").append(interval).append(", ");
		builder.append("TimelineData=").append(this.timeslotDataContainer);
		builder.append("]");
		return builder.toString();
	}

	public String toStringExt(String indent) {
		if (indent == null) {
			indent = "";
		}
		StringBuilder builder = new StringBuilder();
		builder.append(indent).append("TimeLineSlot ").append("interval=").append(interval).append(",");
		builder.append("startOfSlot=").append(startOfSlot).append(", ");
		builder.append("endOfSlot=").append(endOfSlot).append(", \n");

		builder.append(indent).append("DATA\n");
		indent = indent + "   ";
		Map<String, List<TimelineData>> timelineData2 = this.timeslotDataContainer.getTimelineData();

		for (Entry<String, List<TimelineData>> e : timelineData2.entrySet()) {
			builder.append(indent).append("cat=").append(e.getKey()).append("\n");
			List<TimelineData> value = e.getValue();
			for (TimelineData timelineData : value) {
				builder.append(indent + "  ").append("name=").append(timelineData.getCategory());
				builder.append(indent + "  ").append("datetime=").append(timelineData.getDateTime());
				builder.append(indent + "  ").append("value=").append(timelineData.getValue()).append("\n");
			}
		}

//		builder.append("TimelineData=").append(timelineData);
//		builder.append("]");
		return builder.toString();
	}
}
