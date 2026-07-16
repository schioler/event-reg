package dk.schioler.event.configuration.resources;

public class EventResourceException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = -718429805932814594L;

   public EventResourceException() {

   }

   public EventResourceException(String message) {
      super(message);
   }

   public EventResourceException(Throwable cause) {
      super(cause);
   }

   public EventResourceException(String message, Throwable cause) {
      super(message, cause);
   }

   public EventResourceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
   }

}
