package dk.schioler.event.base.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;

public class EventPropertyResources extends PropertyResourceBundle {

	public EventPropertyResources(InputStream stream) throws IOException {
		super(stream);
	}

	public EventPropertyResources(Reader reader) throws IOException {
		super(reader);
	}

	
	
}
