package dk.schioler.shared.timeline;

import java.time.Duration;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.shared.timeline.impl.TimeLineSlotDay;
import dk.schioler.shared.timeline.impl.TimeLineSlotHour;
import dk.schioler.shared.timeline.impl.TimeLineSlotMinute;
import dk.schioler.shared.timeline.impl.TimeLineSlotMonth;

public class Timeline {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public static TIMESLOT_LENGTH mapToTimeslotLength(String input) {
		TIMESLOT_LENGTH retVal = null;
		System.out.println("input:" + input);
		if (StringUtils.isNotBlank(input)) {
			if (TIMESLOT_LENGTH.DAY.toString().equals(input.toUpperCase())) {
				return TIMESLOT_LENGTH.DAY;
			} else if (TIMESLOT_LENGTH.HOUR.toString().equals(input.toUpperCase())) {
				return TIMESLOT_LENGTH.HOUR;
			} else if (TIMESLOT_LENGTH.MINUTE.toString().equals(input.toUpperCase())) {
				return TIMESLOT_LENGTH.MINUTE;
			} else if (TIMESLOT_LENGTH.MONTH.toString().equals(input.toUpperCase())) {
				return TIMESLOT_LENGTH.MONTH;
			} else {
				return retVal;
			}
		} else {
			return retVal;
		}

	}

	private final TIMESLOT_LENGTH interval;

	private final String name;

	private List<TimelineSlot> timeLineSlots = new ArrayList<TimelineSlot>();

	private TimelineDataContainerFactory dataContainerFactory;

	// these to are derived from the slots added this timeline
	private LocalDateTime startOfFirstSlot;
	private LocalDateTime endOfLastSlot;

	// ***************************************''

	public Timeline(String name, TIMESLOT_LENGTH interval, LocalDateTime startDateTime, int countSlots,
			TimelineDataContainerFactory dataSlotContainerFactory) {
		this.interval = interval;
		this.name = name;
		this.dataContainerFactory = dataSlotContainerFactory;

		logger.debug("name=" + name + ", interval=" + interval + ", cntFact=" + dataSlotContainerFactory
				+ ", startDate=" + startDateTime + ", count=" + countSlots);
		if (startDateTime != null) {
			if (TIMESLOT_LENGTH.MINUTE.equals(interval)) {
				List<TimelineSlot> minuteTimeslots = createMinuteTimeslots(startDateTime, countSlots);
				logger.debug("slots=" + minuteTimeslots);
				timeLineSlots.addAll(minuteTimeslots);

			} else if (TIMESLOT_LENGTH.HOUR.equals(interval)) {
				List<TimelineSlot> hourTimeslots = createHourTimeslots(startDateTime, countSlots);
				logger.debug("timeslots, HOUR=" + hourTimeslots);
				timeLineSlots.addAll(hourTimeslots);
			} else if (TIMESLOT_LENGTH.DAY.equals(interval)) {
				List<TimelineSlot> timeslots = createDayTimeslots(startDateTime, countSlots);
				logger.debug("TimeLine(DAY)" + timeslots);
				timeLineSlots.addAll(timeslots);
			} else if (TIMESLOT_LENGTH.MONTH.equals(interval)) {
				List<TimelineSlot> timeslots = createMonthTimeslots(startDateTime, countSlots);
				logger.debug("TimeLine(MONTH)" + timeslots);
				timeLineSlots.addAll(timeslots);
			}
			startOfFirstSlot = timeLineSlots.get(0).getStartOfSlot();
			endOfLastSlot = timeLineSlots.get(timeLineSlots.size() - 1).getEndOfSlot();

		}
	}

	private List<TimelineSlot> createMinuteTimeslots(LocalDateTime startDateTime, int count) {
		List<TimelineSlot> retVal = new ArrayList<TimelineSlot>();
		LocalDateTime start = LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(),
				startDateTime.getDayOfMonth(), startDateTime.getHour(), startDateTime.getMinute(), 0);

		Duration duration = Duration.ofMinutes(1);
		for (int i = 0; i < count; i++) {
			TimelineSlot slot = new TimeLineSlotMinute(start, this.dataContainerFactory.newInstance());
			retVal.add(slot);
			start = start.plus(duration);
		}
		return retVal;
	}

	private List<TimelineSlot> createHourTimeslots(LocalDateTime startDateTime, int count) {
		List<TimelineSlot> retVal = new ArrayList<TimelineSlot>();
		LocalDateTime start = LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(),
				startDateTime.getDayOfMonth(), startDateTime.getHour(), 0, 0);

		Duration duration = Duration.ofHours(1);
		for (int i = 0; i < count; i++) {
			TimelineSlot slot = new TimeLineSlotHour(start, this.dataContainerFactory.newInstance());
			retVal.add(slot);
			start = start.plus(duration);
		}
		return retVal;
	}

	private List<TimelineSlot> createDayTimeslots(LocalDateTime startDateTime, int count) {
		List<TimelineSlot> retVal = new ArrayList<TimelineSlot>();
		LocalDateTime start = LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(),
				startDateTime.getDayOfMonth(), 0, 0, 0);

		Period period = Period.ofDays(1);
		for (int i = 0; i < count; i++) {
			TimelineSlot slot = new TimeLineSlotDay(start, this.dataContainerFactory.newInstance());
			retVal.add(slot);
			start = start.plus(period);
		}
		return retVal;
	}

	private List<TimelineSlot> createMonthTimeslots(LocalDateTime startDateTime, int count) {
		LocalDateTime start = LocalDateTime.of(startDateTime.getYear(), startDateTime.getMonth(), 1, 0, 0, 0);

		List<TimelineSlot> retVal = new ArrayList<TimelineSlot>();
		Period period = Period.ofMonths(1);

		for (int i = 0; i < count; i++) {
			TimelineSlot slot = new TimeLineSlotMonth(start, this.dataContainerFactory.newInstance());
			retVal.add(slot);
			start = start.plus(period);
		}
		return retVal;
	}

	// *****************************''

	public void addData(TimelineData data) {
		if (data != null) {
			if (isInTimelinePeriod(data)) {
				logger.debug("timeline.data is in timeline period");

				for (TimelineSlot timelineSlot : timeLineSlots) {
					if (timelineSlot.dataMatchesSlotTime(data)) {
						timelineSlot.getTimeSlotDataContainer().addTimelineData(data);
					} else {
						timelineSlot.getTimeSlotDataContainer().addCategory(data.getCategory());
					}

				}
			}
		}

	}

	private boolean isInTimelinePeriod(TimelineData data) {
		boolean retVal = false;
		LocalDateTime dateTime = data.getDateTime();

		if (this.startOfFirstSlot.isBefore(dateTime) && this.endOfLastSlot.isAfter(dateTime)) {
			retVal = true;
		}

		return retVal;
	}

	public List<String> getCategories() {		
		List<String> categories = new ArrayList<String>();

		for (TimelineSlot slot : this.timeLineSlots) {
			List<String> timelineDataCategories = slot.getTimeSlotDataContainer().getDataCategories();
			for (String category : timelineDataCategories) {
				if (!categories.contains(category)) {
					categories.add(category);
				}
			}
		}
		
		Collections.sort(categories);
		logger.debug("getDataCategories returning=" + categories);
		return categories;
	}

	/**
	 * Rules for startDate: always set start point at start of an interval.
	 * interval== YEAR: startdate will be 1 January, 00:00 of the provided
	 * startDate. If startDateTime equals 30112022, the timeline will start at
	 * January 1st, 00:00, 2022. interval HOUR: provided startDateTime: 2021, 2
	 * February, 12:45pm, the calculated startDate will be 2021, 2 Feb, 12:00 PM
	 * 
	 * The duration of a slot will be interval-length minus 1 Interval: HOUR,
	 * provided dateTime: 2022, 0607 (July 6), 08:13 TimeLine start = 2022, 0607,
	 * 08:00 1 Slot = 08:00 to 08:59 2 Slot = 09:00 to 09:59 etc. Interval DAY,
	 * date=2022, 0722 (July 22), Hour:07, Minute: 13. 1 Slot: 2022, 0722, 00:00 to
	 * 23:59 2 slot: 2022, 0723, 00:00 to 23:59
	 */

	public LocalDateTime getStartDate() {
		return startOfFirstSlot;
	}

	public TIMESLOT_LENGTH getInterval() {
		return interval;
	}

	public LocalDateTime getEndDate() {
		return endOfLastSlot;
	}

	public String getName() {
		return name;
	}

	public List<TimelineSlot> getSlots() {
		return timeLineSlots;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(timeLineSlots, endOfLastSlot, name, startOfFirstSlot);
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
		Timeline other = (Timeline) obj;
		return Objects.equals(timeLineSlots, other.timeLineSlots) && Objects.equals(endOfLastSlot, other.endOfLastSlot)
				&& Objects.equals(name, other.name) && Objects.equals(startOfFirstSlot, other.startOfFirstSlot);
	}

	@Override
	public String toString() {
		return "TimeLine [name=" + name + ", startDate=" + startOfFirstSlot + ", endDate=" + endOfLastSlot
				+ ", content=" + timeLineSlots + "]";
	}

	public String toExtString(String indent) {
		if (indent == null) {
			indent = "";
		}
		if (this.timeLineSlots.size() > 0) {
			StringBuffer retVal = new StringBuffer("\nTimeLine:\n");
			retVal.append(indent).append("  ").append("name=").append(name).append(", \n");
			retVal.append(indent).append("  ").append("startDate=").append(startOfFirstSlot).append(", ");
			retVal.append(indent).append("  ").append("endDate=").append(endOfLastSlot).append(", \n");
			retVal.append(indent).append("  ").append("interval=").append(interval).append("\n");

			StringBuffer slots = new StringBuffer();
			for (TimelineSlot slot : timeLineSlots) {
				slots.append(slot.toStringExt("      ")).append("\n");
			}
			retVal.append(slots);

			return retVal.toString();

		} else {
			return "TimeLine [name=" + name + ", no intervals added]";
		}
	}

}
