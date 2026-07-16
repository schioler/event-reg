package dk.schioler.event.web.entity.impl;

import dk.schioler.event.web.EventWebException;

public class EventWebEntityException extends EventWebException {

   private static final long serialVersionUID = -8954349845393276047L;

   public EventWebEntityException() {

   }

   public EventWebEntityException(String message) {
      super(message);

   }

   public EventWebEntityException(Throwable cause) {
      super(cause);

   }

   public EventWebEntityException(String message, Throwable cause) {
      super(message, cause);

   }

   public EventWebEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);

   }

}
