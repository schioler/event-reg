package dk.schioler.event.base.chart;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.Month;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.Timeline;
import dk.schioler.shared.timeline.TimelineSlot;
import dk.schioler.shared.timeline.TimelineDataContainer;

public class ChartBuilder {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public ChartBuilder() {

	}

	private RegularTimePeriod mapIntervalToTimeperiodInstance(TimelineSlot slot) {
		LocalDateTime startOfSlot = slot.getStartOfSlot();

		TIMESLOT_LENGTH length = slot.getInterval();
		if (TIMESLOT_LENGTH.MINUTE.equals(length)) {
			return new Minute(startOfSlot.getMinute(), startOfSlot.getHour(), startOfSlot.getDayOfMonth(),
					startOfSlot.getMonthValue(), startOfSlot.getYear());
		} else if (TIMESLOT_LENGTH.HOUR.equals(length)) {
			return new Hour(startOfSlot.getHour(), startOfSlot.getDayOfMonth(), startOfSlot.getMonthValue(),
					startOfSlot.getYear());
		} else if (TIMESLOT_LENGTH.DAY.equals(length)) {

			return new Day(startOfSlot.getDayOfMonth(), startOfSlot.getMonthValue(), startOfSlot.getYear());
		} else if (TIMESLOT_LENGTH.MONTH.equals(length)) {
			return new Month(startOfSlot.getMonthValue(), startOfSlot.getYear());
		} else {
			return null;
		}
	}

//	private Class mapIntervalToTimeperiodClass(TIMESLOT_LENGTH length) {
//		if (TIMESLOT_LENGTH.MINUTE.equals(length)) {
//			return Minute.class;
//		} else if (TIMESLOT_LENGTH.HOUR.equals(length)) {
//			return Hour.class;
//		} else if (TIMESLOT_LENGTH.DAY.equals(length)) {
//			return Day.class;
//		} else if (TIMESLOT_LENGTH.MONTH.equals(length)) {
//			return Month.class;
//		} else {
//			return null;
//		}
//	}

	public JFreeChart buildTimeSeriesChartFromTimeline(Timeline timeline) {
		TimeSeriesCollection dataSet = new TimeSeriesCollection();

		List<TimelineSlot> slots = timeline.getSlots();
		logger.debug("slots=" + slots);
		List<String> categories = timeline.getCategories();
		logger.debug("cats=" + categories);
	
		for (String category : categories) {
			TimeSeries ts = new TimeSeries(category);
			for (TimelineSlot slot : slots) {
				RegularTimePeriod timePeriod = mapIntervalToTimeperiodInstance(slot);
				
				TimelineDataContainer timeslotDataContainer = slot.getTimeSlotDataContainer();
				
				BigDecimal sum = timeslotDataContainer.getSum(category);
				
				ts.add(timePeriod, sum);
								
			}
			dataSet.addSeries(ts);

		}

		JFreeChart chart =	ChartFactory.createTimeSeriesChart("events", timeline.getInterval().toString(), "Value", dataSet);
		
		

		
		return chart;
	}

}
