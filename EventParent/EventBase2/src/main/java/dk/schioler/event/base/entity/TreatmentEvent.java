package dk.schioler.event.base.entity;

public interface TreatmentEvent extends BaseEvent {
   public static final EVENT_TYPE TYPE = EVENT_TYPE.TREATMENT;

   public Integer getTreatmentId();
   public void setTreatmentId(Integer Id);
   
   public String getTreatmentName();
   public void setTreatmentName(String treatmentName);
   
   public String getDuration();
   public void setDuration(String duration);
   
   public UNIT getUnit();
   public void setUnit(UNIT unit);
   
   public int getRating();
   public void setRating(int rating);
   
   public String getDescription();
   public void setDescription(String description);
   

}
