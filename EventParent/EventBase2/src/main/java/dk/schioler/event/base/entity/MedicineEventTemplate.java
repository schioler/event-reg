package dk.schioler.event.base.entity;

public interface MedicineEventTemplate extends EntityBase {

   public Integer getMedicineId();

   public void setMedicineId(Integer id);

   public String getMedicineName();

   public void setMedicineName(String dose);

   public boolean isFavorite();

   public void setFavorite(boolean isFavorite);

   public UNIT getUnit();

   public void setUnit(UNIT unit);

   public String getDose();

   public void setDose(String dose);

}
