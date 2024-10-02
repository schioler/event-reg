package dk.schioler.shared.timeline.impl;

import dk.schioler.shared.timeline.TimelineDataContainer;
import dk.schioler.shared.timeline.TimelineDataContainerFactory;

public class TimelineDataContainerFactoryEventImpl implements TimelineDataContainerFactory {

	public TimelineDataContainerFactoryEventImpl() {

	}

	@Override
	public TimelineDataContainer newInstance() {

		return new TimelineDataContainerImpl();
	}

//	@Override
//	public TimelineData createCategoryNullDataInstance(String name, LocalDateTime dt, Integer value){
//
//		return new TimelineDataEventImpl(name, dt, value);
//	}

}
