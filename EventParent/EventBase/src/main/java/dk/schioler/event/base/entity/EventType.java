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
		} else if (parent == null) {
		   super.setParent(null);
		} else {
			throw new EventEntityException("EventTypes has only one type of parent: " + XMLRootElement.class.getName());
		}
	}

	@Override
	public void removeChild(AbstractEntityParentChild child) {
		if (child instanceof EventTemplate) {
			super.removeChild(child);
		} else if (child == null) {
		   super.removeChild(null);
		} else {
			throw new EventEntityException("EventTypes has only one type of children: " + EventTemplate.class.getName());
		}
	}

	@Override
	public void addChild(AbstractEntityParentChild child) {
	   
		if (child instanceof EventTemplate) {
//		   logger.trace("addingChild: child = "+ child.toString());
			super.addChild(child);
      } else if (child == null) {
//         logger.trace("addingChild: child = null");s
         super.addChild(null);
		} else {
			throw new EventEntityException("EventTypes has only one type of children: " + EventTemplate.class.getName());
		}

	}

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append(" ");
      return builder.toString();
   }




}
