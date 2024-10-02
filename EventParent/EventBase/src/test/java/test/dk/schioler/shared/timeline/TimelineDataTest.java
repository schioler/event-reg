package test.dk.schioler.shared.timeline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.chart.ChartBuilder;
import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.Timeline;
import dk.schioler.shared.timeline.TimelineData;
import dk.schioler.shared.timeline.TimelineException;
import dk.schioler.shared.timeline.TimelineSlot;
import dk.schioler.shared.timeline.TimelineDataContainerFactory;
import dk.schioler.shared.timeline.impl.TimelineDataEventImpl;
import dk.schioler.shared.timeline.impl.TimelineDataContainerFactoryEventImpl;

public class TimelineDataTest extends ChartUtils {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		// Timeline creation
		LocalDateTime startDateTime = LocalDateTime.of(2022, 7, 6, 9, 25);
		
		TimelineDataContainerFactory factory = new TimelineDataContainerFactoryEventImpl();

		Timeline timeline = new Timeline("timeline-6Months, 20220706T09:25", TIMESLOT_LENGTH.MONTH, startDateTime, 6, factory);
		logger.debug(timeline.toExtString("  "));
		List<TimelineSlot> slots = timeline.getSlots();
		assertEquals(6, slots.size());
		// December slot
		TimelineSlot timelineSlot = slots.get(5);
		assertEquals(TIMESLOT_LENGTH.MONTH, timelineSlot.getInterval());
		LocalDateTime startOfSlot = timelineSlot.getStartOfSlot();
		LocalDateTime endOfSlot = timelineSlot.getEndOfSlot();

		LocalDateTime expStart = LocalDateTime.of(2022, 12, 1, 0, 0);
		LocalDateTime expEnd = LocalDateTime.of(2022, 12, 31, 23, 59);
		assertEquals(startOfSlot, expStart);
		assertEquals(endOfSlot, expEnd);


		// timeline data1: 20221023T21:30
		LocalDateTime dte1 = LocalDateTime.of(2022, 10, 23, 21, 30);
		TimelineData tlData = new TimelineDataEventImpl("event1", dte1);

		// adding to timeline, hopefully received in correct slot
		timeline.addData(tlData);

		List<TimelineSlot> slots2 = timeline.getSlots();
		TimelineSlot timelineSlot2 = slots2.get(3);
		List<TimelineData> timelineData = timelineSlot2.getTimeSlotDataContainer().getTimelineData("event1");
		assertNotNull("timeline data", timelineData);
		assertEquals(1, timelineData.size());
		
		// **** adding some data
		LocalDateTime dte2 = LocalDateTime.of(2022, 11, 23, 21, 30);
		TimelineData tlData2 = new TimelineDataEventImpl("event1", dte2);
		timeline.addData(tlData2);
		
		dte2 = LocalDateTime.of(2022, 9, 3, 11, 30);
		tlData2 = new TimelineDataEventImpl("event1", dte2);
		timeline.addData(tlData2);
		
		dte2 = LocalDateTime.of(2022, 9, 7, 11, 30);
		tlData2 = new TimelineDataEventImpl("event1", dte2);
		timeline.addData(tlData2);
		
		dte2 = LocalDateTime.of(2022, 9, 17, 11, 30);
		tlData2 = new TimelineDataEventImpl("event1", dte2);
		timeline.addData(tlData2);
		
		dte2 = LocalDateTime.of(2022, 8, 13, 15, 30);
		tlData2 = new TimelineDataEventImpl("event1", dte2);
		timeline.addData(tlData2);
		
		logger.debug(timeline.toExtString(" "));

		dte2 = LocalDateTime.of(2022, 9, 3, 14, 30);
		tlData2 = new TimelineDataEventImpl("event2", dte2);
		timeline.addData(tlData2);
		
		dte2 = LocalDateTime.of(2022, 12, 3, 17, 30);
		tlData2 = new TimelineDataEventImpl("event2", dte2);
		timeline.addData(tlData2);
		
		dte2 = LocalDateTime.of(2022, 8, 3, 12, 30);
		tlData2 = new TimelineDataEventImpl("event3", dte2);
		timeline.addData(tlData2);
		
		logger.debug(timeline.toExtString(" "));
		
		ChartBuilder cb = new ChartBuilder();
		
		JFreeChart chart = cb.buildTimeSeriesChartFromTimeline(timeline);
		
		try {
			ChartUtils.saveChartAsPNG(new File("target/chart.png"), chart, 400, 400);
		} catch (IOException e) {
			throw new TimelineException(e.getMessage(), e);			
		}
//		.
//		ChartPanel panel = new ChartPanel(chart); 
//		panel.setPreferredSize(new Dimension(1024, 360)); 
//		panel.setLayout(new BorderLayout()); 
//		getContentPane().add(panel, BorderLayout.CENTER);

	}
	

//		timeline = new TimeLine("timeline31Days",TIMESLOT_LENGTH.DAY ,startDateTime ,31);
//		logger.debug(timeline.toExtString());
//		
//		timeline = new TimeLine("timeline24Hrs",TIMESLOT_LENGTH.HOUR ,startDateTime ,24);
//		logger.debug(timeline.toExtString());
//		
//		timeline = new TimeLine("timeline1",TIMESLOT_LENGTH.MINUTE ,startDateTime ,60);
//		logger.debug(timeline.toExtString());
}
