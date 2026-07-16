package dk.schioler.event.web.entity.search.impl;

import java.util.ArrayList;
import java.util.List;

import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.entity.search.WebEntityEventTemplateSearch;

public class WebEntityEventTemplateSearchImpl extends WebEntityNamedSearchImpl implements WebEntityEventTemplateSearch {

   private List<Integer> eventTemplateIds = new ArrayList<Integer>();

   private List<UNIT> unit = new ArrayList<UNIT>();

   private String dose;

   private Boolean isFavorite;

   @Override
   public void setUnits(List<UNIT> units) {
      this.unit = units;
   }

   @Override
   public List<UNIT> getUnits() {
      return unit;
   }

   @Override
   public void addUnit(UNIT unit) {
      this.unit.add(unit);
   }

   @Override
   public String getDose() {

      return dose;
   }

   @Override
   public void setDose(String dose) {
      this.dose = dose;
   }

   @Override
   public List<Integer> getEventTemplateIds() {
      return this.eventTemplateIds;
   }

   @Override
   public void setEventTemplateIds(List<Integer> eventTemplateIds) {
      this.eventTemplateIds = eventTemplateIds;
   }

   @Override
   public void addEventTemplateId(Integer eventTemplateId) {
      this.eventTemplateIds.add(eventTemplateId);
   }

   @Override
   public void setIsFavorite(Boolean isFavorite) {
      this.isFavorite = isFavorite;
   }

   @Override
   public Boolean getIsFavorite() {
      return isFavorite;
   }

}
