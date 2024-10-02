package dk.schioler.shared.timeline;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface TimelineDataContainer {

	public void addTimelineData(TimelineData timelineData);

	public List<TimelineData> getTimelineData(String category);

	public Map<String, List<TimelineData>> getTimelineData();
	
	public List<String> getDataCategories();
	
	public void addCategory(String category);

	public int getCount(String category);

	public int getCount();

	public BigDecimal getSum(String category);

	public BigDecimal getSum();

	public int getAvg(String category);

	public int getAvg();

	public String toStringExt(String indent);

}