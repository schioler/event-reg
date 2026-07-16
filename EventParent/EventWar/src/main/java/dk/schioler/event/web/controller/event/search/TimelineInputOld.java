package dk.schioler.event.web.controller.event.search;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import dk.schioler.event.web.common.WebEntityUtil;
import dk.schioler.shared.timeline.TIMESLOT_LENGTH;

public class TimelineInputOld {

   private LocalDateTime timelineStart;
   private int selectedCount = -1;
   private TIMESLOT_LENGTH selectedTimeslot;

   public void setTimelineStartStr(String reqSearchStart) {
      if (StringUtils.isNotBlank(reqSearchStart)) {
         
         this.timelineStart =  WebEntityUtil.getLocalDateTimeFrom(reqSearchStart) ;
//               LocalDateTime.parse(reqSearchStart, WebEntityUtil.getDtFormatter());
      } else {
         this.timelineStart = null;
      }
   }

   public void setTimelineStart(LocalDateTime reqSearchStart) {
      this.timelineStart = reqSearchStart;
   }
   

   public String getFormattedTimelineStart() {
      if (timelineStart != null) {
         return WebEntityUtil.getDateTimeFrom(timelineStart);
//         return WebEntityUtil.getDtFormatter().format(timelineStart);
      } else {
         return "";
      }
   }

   public LocalDateTime getTimelineStart() {
      return timelineStart;
   }

//   public List<TIMESLOT_LENGTH> getTimeslots() {
//      return timeslots;
//   }

   public TIMESLOT_LENGTH getSelectedInterval() {
      return selectedTimeslot;
   }

   public TIMESLOT_LENGTH getSelectedTimeslot() {
      return selectedTimeslot;
   }

   public void setSelectedTimeslot(TIMESLOT_LENGTH selectedTimeslot) {
      this.selectedTimeslot = selectedTimeslot;
   }

   public void setSelectedTimeslotStr(String selectedTimeslot) {
      this.selectedTimeslot = TIMESLOT_LENGTH.getTimeSlotFromString(selectedTimeslot);
   }

   public void setSelectedInterval(TIMESLOT_LENGTH interval) {
      this.selectedTimeslot = interval;
   }

   public int getSelectedCount() {
      return selectedCount;
   }

   public void setSelectedCount(int count) {
      this.selectedCount = count;
   }
}
