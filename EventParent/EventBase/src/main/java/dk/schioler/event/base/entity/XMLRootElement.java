package dk.schioler.event.base.entity;

import dk.schioler.event.base.EventBaseException;

public class XMLRootElement extends AbstractEntity {

	public XMLRootElement() {
		super(null, null, null);
	}

	@Override
	public AbstractEntity instantiateParent() {
		throw new EventBaseException("xmlRoot do not have parent");
	}

}
