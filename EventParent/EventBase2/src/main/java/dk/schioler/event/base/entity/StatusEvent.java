package dk.schioler.event.base.entity;

public interface StatusEvent extends BaseEvent {
   public static final EVENT_TYPE TYPE = EVENT_TYPE.STATUS;
   
   public EVENT_TYPE getEventType();
//   public void setType(String type);
      
   public int getRating();
   public void setRating(int rating);
   
   public String getDescription();
   public void setDescription(String description);
   
}
