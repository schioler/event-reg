package dk.schioler.economy.web.configuration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class EconomyContextLoaderListener extends ContextLoaderListener {

	public EconomyContextLoaderListener() {
		this(new AnnotationConfigWebApplicationContext());
	}

	public EconomyContextLoaderListener(WebApplicationContext context) {
		super(context);		
	}

}
