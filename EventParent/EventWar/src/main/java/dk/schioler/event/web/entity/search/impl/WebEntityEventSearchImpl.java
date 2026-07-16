package dk.schioler.event.web.entity.search.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dk.schioler.event.base.entity.UNIT;
import dk.schioler.event.web.entity.search.WebEntityEventSearch;

public class WebEntityEventSearchImpl extends WebEntityNamedSearchImpl implements WebEntityEventSearch {

   private UNIT unit;
   private String dose;

   private List<Integer> eventTemplateIds = new ArrayList<Integer>();

   public WebEntityEventSearchImpl() {

   }

   public WebEntityEventSearchImpl(Map<String, String> params) {
//      String loginId = params.get(REQ_LOGIN_ID);
//      String startDate = params.get(REQ_TIMELINE_STARTDATE);
//      String interval = params.get(REQ_TIMELINE_INTERVAL);
//      String count = params.get(REQ_TIMELINE_COUNT_INTERVALS);
//
//      logger.debug("loginId=" + loginId + ", fromDate=" + startDate + ", interval=" + interval + ", count=" + count);

   }

   
   @Override
   public List<Integer> getEventTemplateIds() {
      
      return eventTemplateIds;
   }

   @Override
   public void setEventTemplateIds(List<Integer> eventTemplateIds) {
      
   }

   @Override
   public void addEventTemplateId(Integer eventTemplateId) {
      this.eventTemplateIds.add(eventTemplateId);
      
   }

   public UNIT getUnit() {
      return unit;
   }

   public void setUnit(UNIT unit) {
      this.unit = unit;
   }

   public String getDose() {
      return dose;
   }

   public void setDose(String dose) {
      this.dose = dose;
   }

  
}
