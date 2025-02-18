package dk.schioler.event.base.entity;

import dk.schioler.event.base.EventBaseException;

public class XMLRootElement extends AbstractEntityParentChild {

	public XMLRootElement() {
		super();
	}

	@Override
	public AbstractEntityParentChild instantiateParent() {
		throw new EventBaseException("xmlRoot do not have parent");
	}

	@Override
	public void setParentId(Integer id) {
		throw new EventBaseException("xmlRoot do not have parent");
	}

	@Override
	public void setParent(AbstractEntityParentChild parent) {
		throw new EventBaseException("xmlRoot do not have parent");
	}

	@Override
	public String getParentString() {
		return "n/a";
	}

	
}
