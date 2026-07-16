   package dk.schioler.event.base.entity;

public interface TreatmentEventTemplate extends EntityBase{

   public Integer getTreatmentId();
   
   public void setTreatmentId(Integer id);

   public boolean isFavorite() ;

   public void setFavorite(boolean isFavorite) ;

   public UNIT getUnit() ;

   public void setUnit(UNIT unit);

   public String getDuration() ;
   
   public void setDuration(String duration) ;
   
   
}
