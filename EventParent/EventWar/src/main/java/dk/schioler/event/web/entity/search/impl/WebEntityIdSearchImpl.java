package dk.schioler.event.web.entity.search.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.web.entity.impl.EventWebEntityMissingDataException;
import dk.schioler.event.web.entity.search.WebEntityIdSearch;

public class WebEntityIdSearchImpl implements WebEntityIdSearch {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   private String createdStart;
   private String createdEnd;

   private List<Integer> loginIds = new ArrayList<Integer>();
   private List<Integer> ids = new ArrayList<Integer>();
  
   private String timelineStartdate;
   private String timelineInterval;
   private int timelineIntervalCount;   
   
   
   public WebEntityIdSearchImpl() {
      super();
   }

   public List<Integer> getLoginIds() {
      return loginIds;
   }

   public void addLoginId(Integer loginId) {
      this.loginIds.add(loginId);
   }

   
   public void addId(Integer id) {
      this.ids.add(id);
   }

   public List<Integer> getIds() {
      return ids;
   }

   public void setCreatedSearchInterval(String start, String end) {
      if (start != null && end != null) {
         this.createdStart = start;
         this.createdEnd = end;
      } else {
         throw new EventWebEntityMissingDataException("You need to provide both start and end dates, to enable a search");
      }
   }

   public String getCreatedStart() {
      return createdStart;
   }

   public String getCreatedEnd() {
      return createdEnd;
   }

   @Override
   public String getTimelineStartDate() {

      return timelineStartdate;
   }

   @Override
   public void setTimelineStartDate(String timelineStart) {
       this.timelineStartdate = timelineStart;
      
   }

   @Override
   public int getTimelineCountIntervals() {

      return timelineIntervalCount;
   }

   @Override
   public void setTimelineCountIntervals(int countIntervals) {
         this.timelineIntervalCount = countIntervals;
      
   }

   @Override
   public String getTimelineInterval() {

      return this.timelineInterval;
   }

   @Override
   public void setTimelineInterval(String timeslotLength) {

      this.timelineInterval = timeslotLength;
   }


   
}
