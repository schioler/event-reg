package dk.schioler.event.web.entity.search;

import java.util.List;

public interface WebEntityIdSearch {

   public void addId(Integer id);

   public List<Integer> getIds();

   public void addLoginId(Integer loginId);

   public List<Integer> getLoginIds();

   public void setCreatedSearchInterval(String start, String end);

   public String getCreatedStart();

   public String getCreatedEnd();
   
   

   public String getTimelineStartDate() ;

   public void setTimelineStartDate(String timelineStart) ;

   public int getTimelineCountIntervals() ;

   public void setTimelineCountIntervals(int countIntervals) ;

   public String getTimelineInterval() ;

   public void setTimelineInterval(String timeslotLength) ;


}
