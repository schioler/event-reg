package dk.schioler.shared.timeline;

import java.time.LocalDateTime;

public interface TimelineSlot {

	public LocalDateTime getStartOfSlot();

	public LocalDateTime getEndOfSlot();

	public TIMESLOT_LENGTH getInterval();
	
	// ******************************
	
	public boolean dataMatchesSlotTime(TimelineData timelineData);
	
	public TimelineDataContainer getTimeSlotDataContainer();

//	public void addCategoryIfNotPresent(String category);
//	
//	public void addTimelineData(TimelineData timelineData);
	
//	public List<String> getCategories();
	
//	public List<TimelineData> getTimelineData(String category);
	
//	public List<String> getTimelineDataCategories();
	
	public String toStringExt(String indent);
	

}