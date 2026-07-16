package dk.schioler.event.web.entity.impl;

public class EventWebEntityMissingDataException extends EventWebEntityException {

   private static final long serialVersionUID = -8954349845393276047L;

   public EventWebEntityMissingDataException() {

   }

   public EventWebEntityMissingDataException(String message) {
      super(message);

   }

   public EventWebEntityMissingDataException(Throwable cause) {
      super(cause);

   }

   public EventWebEntityMissingDataException(String message, Throwable cause) {
      super(message, cause);

   }

   public EventWebEntityMissingDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);

   }

}
