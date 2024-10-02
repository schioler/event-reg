package dk.schioler.event.web;

import dk.schioler.event.web.controller.WebTokens;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class EventSessionWrapper implements  HttpSessionListener, WebTokens {

	public final static String EVENT_SESSION_WRAPPER_SESSION_KEY = "EVENT_SESSION_WRAPPER_SESSION_KEY";

//	private final HttpSession session;
//
//	public EventSessionWrapper(HttpSession session) {
//		if (session != null) {
//			this.session = session;
//		} else {
//			throw new EventBaseException("session = null");		
//		}
//	}
//
//	public static String HOME_PREPARE = "";
//	
//	public static String HOME_TYPE_SELECT = "";
//	public static String HOME_TEMPLATE_DIRECT_SAVE = "";
//	public static String HOME_TEMPLATE_QUEUE_ADD = "";
//	public static String HOME_TEMPLATE_QUEUE_SAVE = "";
//	
//	/**
//	 * Remove all, but eventTypes
//	 */
//	public static String SEARCH_NEW = "";
//	/**
//	 * 
//	 */
//	public static String SEARCH_TEMPLATE_ADD = "";
//	public static String SEARCH_TEMPLATE_REMOVE = "";
//	public static String SEARCH_EXECUTE = "";
//	
//	
//	public void prepare(String action) {
//		
//	}


	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
	}


	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		HttpSessionListener.super.sessionDestroyed(se);
	}
	
}
