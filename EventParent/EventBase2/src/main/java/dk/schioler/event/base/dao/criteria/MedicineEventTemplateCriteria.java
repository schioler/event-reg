package dk.schioler.event.base.dao.criteria;

import java.util.ArrayList;
import java.util.List;

public class MedicineEventTemplateCriteria extends BaseEventCriteria {

   private Boolean favourite;

   private List<Integer> medicineIds = new ArrayList<Integer>();


   public List<Integer> getMedicineIds() {
      return medicineIds;
   }

   public void addMedicineId(Integer medicineId) {
      this.medicineIds.add(medicineId);
   }  

   public Boolean isFavourite() {
      return favourite;
   }

   public Boolean getFavourite() {
      return favourite;
   }

   public void setFavourite(Boolean favourite) {
      this.favourite = favourite;
   }


   public void setEventTypeIds(List<Integer> eventTypeIds) {
      this.medicineIds = eventTypeIds;
   }



}
