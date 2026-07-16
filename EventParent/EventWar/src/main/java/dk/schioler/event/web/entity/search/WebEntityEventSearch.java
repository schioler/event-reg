package dk.schioler.event.web.entity.search;


import java.util.List;

import dk.schioler.event.base.entity.UNIT;

public interface WebEntityEventSearch extends WebEntityNamedSearch {

   public UNIT getUnit() ;
   
   public void setUnit(UNIT unit) ;

   public String getDose() ;

   public void setDose(String dose) ;

   public List<Integer> getEventTemplateIds();
   
   public void setEventTemplateIds(List<Integer> eventTemplateIds) ;

   public void addEventTemplateId(Integer eventTemplateId);
   
}
