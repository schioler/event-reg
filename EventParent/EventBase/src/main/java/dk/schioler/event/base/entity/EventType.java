package dk.schioler.event.base.entity;

public class EventType extends AbstractEntityParentChild {

	public EventType() {

	}

	@Override
   public AbstractEntityParentChild instantiateParent() {
      return new XMLRootElement();
   }
	
	@Override
	public void setParent(AbstractEntityParentChild parent) {
		if (parent instanceof XMLRootElement) {
			super.setParent(parent);
		} else {
			throw new EventEntityException("EventTypes has only one type of parent: " + XMLRootElement.class.getName());
		}
	}

	@Override
	public void removeChild(AbstractEntityParentChild child) {
		if (child instanceof EventTemplate) {
			super.removeChild(child);
		} else {
			throw new EventEntityException("EventTypes has only one type of children: " + EventTemplate.class.getName());
		}
	}

	@Override
	public void addChild(AbstractEntityParentChild child) {
		if (child instanceof EventTemplate) {
			super.addChild(child);
		} else {
			throw new EventEntityException("EventTypes has only one type of children: " + EventTemplate.class.getName());
		}

	}




}
