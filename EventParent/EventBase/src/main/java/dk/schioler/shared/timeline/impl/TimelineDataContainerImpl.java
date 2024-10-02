package dk.schioler.shared.timeline.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.shared.timeline.TimelineData;
import dk.schioler.shared.timeline.TimelineDataContainer;

public class TimelineDataContainerImpl implements TimelineDataContainer {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Map<String, List<TimelineData>> timelineDataMap = new TreeMap<String, List<TimelineData>>();
	
	public TimelineDataContainerImpl() {

	}

	@Override
	public void addTimelineData(TimelineData timelineData) {
		if (timelineData != null) {
			
			String category = timelineData.getCategory().trim();
			if(this.timelineDataMap.containsKey(category)) {
				List<TimelineData> list = this.timelineDataMap.get(category);
				if (list == null) {
					list = new ArrayList<TimelineData>();
				}	
				list.add(timelineData);
				this.timelineDataMap.replace(category, list);
				
			} else {
				List<TimelineData> list = new ArrayList<TimelineData>();
				list.add(timelineData);
				this.timelineDataMap.put(category, list);
			}
		}
	}
	
	@Override
	public void addCategory(String category) {
		if (!this.timelineDataMap.containsKey(category)) {
			this.timelineDataMap.put(category, new ArrayList<TimelineData>());
		}		
	}

	@Override
	public List<TimelineData> getTimelineData(String category) {
		return this.timelineDataMap.get(category);
	}

	@Override
	public List<String> getDataCategories() {
		List<String> retVal = new ArrayList<String>(this.timelineDataMap.keySet());
		Collections.sort(retVal);
		return retVal;
	}


	@Override
	public int getCount(String category) {
		List<TimelineData> list = this.timelineDataMap.get(category);
		if (list != null) {
			return list.size();
		} else {
			return 0;
		}
	}

	@Override
	public int getCount() {
		Set<String> keySet = this.timelineDataMap.keySet();
		int count = 0;
		for (String string : keySet) {
			List<TimelineData> list = this.timelineDataMap.get(string);
			count = count + list.size();
		}

		return count;
	}

	@Override
	public BigDecimal getSum(String category) {
		int count = getCount(category);

		return BigDecimal.valueOf(count);
	}

	@Override
	public BigDecimal getSum() {
		int count = getCount();
		return BigDecimal.valueOf(count);
	}

	@Override
	public int getAvg(String category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getAvg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toStringExt(String indent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<TimelineData>> getTimelineData() {
		return this.timelineDataMap;
	}

}
