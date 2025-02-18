package dk.schioler.event.web.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import dk.schioler.event.base.entity.EventTemplate;
import dk.schioler.shared.timeline.api.entity.TIMESLOT_LENGTH;

public class EventSearchCriteria implements Serializable {
	private static final long serialVersionUID = 2940008566935314289L;

	private LocalDateTime timelineStart;
	private TIMESLOT_LENGTH timelineSlotLength;
	private int countTimelineSlots;
	private boolean isFavorite;
	private List<EventTemplate> eventTemplates = new ArrayList<EventTemplate>();
	private Integer loginId;

	public EventSearchCriteria() {
		super();
	}

	public LocalDateTime getTimelineStart() {
		return timelineStart;
	}

	public void setTimelineStart(LocalDateTime timelineStart) {
		this.timelineStart = timelineStart;
	}

	public TIMESLOT_LENGTH getTimelineSlotLength() {
		return timelineSlotLength;
	}

	public void setTimelineSlotLength(TIMESLOT_LENGTH timelineSlotLength) {
		this.timelineSlotLength = timelineSlotLength;
	}

	public int getCountTimelineSlots() {
		return countTimelineSlots;
	}

	public void setCountTimelineSlots(int countTimelineSlots) {
		this.countTimelineSlots = countTimelineSlots;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public List<EventTemplate> getEventTemplates() {
		return eventTemplates;
	}

	public Iterator<EventTemplate> iterator() {
		return eventTemplates.iterator();
	}

	public boolean add(EventTemplate e) {
		return eventTemplates.add(e);
	}

	public boolean addAll(Collection<EventTemplate> c) {
		return eventTemplates.addAll(c);
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countTimelineSlots, eventTemplates, isFavorite, loginId, timelineSlotLength, timelineStart);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EventSearchCriteria other = (EventSearchCriteria) obj;
		return countTimelineSlots == other.countTimelineSlots && Objects.equals(eventTemplates, other.eventTemplates)
				&& isFavorite == other.isFavorite && Objects.equals(loginId, other.loginId)
				&& timelineSlotLength == other.timelineSlotLength && Objects.equals(timelineStart, other.timelineStart);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SearchCriteria [timelineStart=");
		builder.append(timelineStart);
		builder.append(", timelineSlotLength=");
		builder.append(timelineSlotLength);
		builder.append(", countTimelineSlots=");
		builder.append(countTimelineSlots);
		builder.append(", isFavorite=");
		builder.append(isFavorite);
		builder.append(", eventTemplates=");
		builder.append(eventTemplates);
		builder.append(", loginId=");
		builder.append(loginId);
		builder.append("]");
		return builder.toString();
	}

	
}
