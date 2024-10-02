package dk.schioler.event.base.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class EventType extends AbstractEntity {

	private String name;

	private String shortName;

	private String description;

	private List<EventTemplate> eventTemplates = new ArrayList<EventTemplate>();

	public EventType(Integer id, String name, String shortName, String description) {
		super(id);
		this.name = name;
		this.shortName = shortName;
		this.description = description;
	}

	public EventType() {
		super(null);
		// TODO Auto-generated constructor stub
	}

	public void addEventTemplate(EventTemplate tmpl) {
		eventTemplates.add(tmpl);
	}

	public List<EventTemplate> getEventTemplates() {
		return eventTemplates;
	}

	public void setEventTemplates(List<EventTemplate> eventTemplates) {
		this.eventTemplates = eventTemplates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(description, eventTemplates, name, shortName);
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
		EventType other = (EventType) obj;
		return Objects.equals(description, other.description) && Objects.equals(eventTemplates, other.eventTemplates)
				&& Objects.equals(name, other.name) && Objects.equals(shortName, other.shortName);
	}

	@Override
	public String toString() {
		return "EventType [id="+getId()+", name=" + name + ", shortName=" + shortName + ", description=" + description
				+ ", eventTemplates=" + eventTemplates.size() + "]";
	}

	public String toStringExt() {
		String out= "EventType [id="+ getId() + ", name=" + name + ", shortName=" + shortName + ", description=" + description;
//		for (EventTemplate eventTemplate : eventTemplates) {
//			out = out +  "\ntmpl=" + eventTemplate.toString();
//		}
		out = out + ", templates.size"+eventTemplates.size();
		return out + "]";
	}
}
