package dk.schioler.event.web.entity.search;

import java.util.List;

import dk.schioler.event.base.entity.UNIT;

public interface WebEntityEventTemplateSearch extends WebEntityNamedSearch {

   public void setUnits(List<UNIT> units);
   
   public List<UNIT> getUnits();

   public void addUnit(UNIT unit);

   public String getDose();

   public void setDose(String dose);

   public List<Integer> getEventTemplateIds();

   public void setEventTemplateIds(List<Integer> eventTemplateIds);

   public void addEventTemplateId(Integer eventTemplateId);
   
   public void setIsFavorite(Boolean isFavorite);
   
   public Boolean getIsFavorite();
   

}
