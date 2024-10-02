package test.dk.schioler.shared.timeline;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.shared.timeline.TIMESLOT_LENGTH;
import dk.schioler.shared.timeline.Timeline;
import dk.schioler.shared.timeline.TimelineData;
import dk.schioler.shared.timeline.TimelineDataContainerFactory;
import dk.schioler.shared.timeline.impl.TimelineDataEventImpl;
import dk.schioler.shared.timeline.impl.TimelineDataContainerFactoryEventImpl;

public class TimelineTest {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() {
		LocalDateTime startDateTime = LocalDateTime.of(2022, 7, 6, 9, 25);

		TimelineDataContainerFactory factory = new TimelineDataContainerFactoryEventImpl();
		Timeline timeline = new Timeline("timeline6Month", TIMESLOT_LENGTH.MONTH, startDateTime, 6, factory);
		logger.debug(timeline.toExtString(""));
		assertEquals(6, timeline.getSlots().size());

		TimelineData data = null;
		data = new TimelineDataEventImpl("name1", LocalDateTime.of(2022, 7, 4, 22, 30));
		timeline.addData(data);

		logger.debug(timeline.toExtString(""));
//		timeline = new TimeLine("timeline31Days",TIMESLOT_LENGTH.DAY ,startDateTime ,31);
//		logger.debug(timeline.toExtString());
//		
//		timeline = new TimeLine("timeline24Hrs",TIMESLOT_LENGTH.HOUR ,startDateTime ,24);
//		logger.debug(timeline.toExtString());
//		
//		timeline = new TimeLine("timeline1",TIMESLOT_LENGTH.MINUTE ,startDateTime ,60);
//		logger.debug(timeline.toExtString());
	}

}
