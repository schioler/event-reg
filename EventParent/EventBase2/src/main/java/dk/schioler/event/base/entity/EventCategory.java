package dk.schioler.event.base.entity;

public interface EventCategory extends EntityBase {
   public static final String TABLE = "EVENT_CATEGORY";
   public String getName();
   public void setName(String name);
   
   public String getDescription();
   public void setDescription(String description);
}
