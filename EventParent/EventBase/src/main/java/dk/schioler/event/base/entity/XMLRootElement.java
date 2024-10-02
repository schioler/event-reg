package dk.schioler.event.base.entity;

import java.util.ArrayList;
import java.util.List;

public class XMLRootElement extends AbstractEntity {

	List<EventType> eventTypes = new ArrayList<EventType>();

	public XMLRootElement() {
		super(null);
	}

	public List<EventType> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<EventType> ets) {
		this.eventTypes = ets;
	}

	public void addEventType(EventType et) {
		this.eventTypes.add(et);
	}

}
