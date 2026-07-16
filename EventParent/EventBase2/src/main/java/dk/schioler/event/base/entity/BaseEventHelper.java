package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface BaseEventHelper {

   public DateTimeFormatter getDateFormatter();
   public DateTimeFormatter getTimeFormatter();
   public DateTimeFormatter getFullFormatter();

   
   public String formatTime(LocalDateTime dateTime);
   public String formatDate(LocalDateTime dateTime);
   public String formatFull(LocalDateTime dateTime);
   
   public LocalDateTime parseDate(String dateTime);
   public LocalDateTime parseTime(String dateTime);
   public LocalDateTime parseFull(String dateTime);
   
   
   
   
}
