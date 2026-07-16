package dk.schioler.event.base.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEntityId {

   // YEAR-MONTH-DAY, ex 2018-12-29
   protected final DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
   
   protected final DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");
   
   public static final Integer DEFAULT_ID = Integer.valueOf(-1);
   
   protected Logger logger = LoggerFactory.getLogger(getClass());

   private Integer id = DEFAULT_ID;

   private Integer loginId;
   
   private LocalDateTime created;
  
   public AbstractEntityId(Integer id, Integer loginId, LocalDateTime created) {
      super();
      this.id = id;
      this.loginId = loginId;
      this.created = created;
   }

   public AbstractEntityId() {
      super();
      created = LocalDateTime.now();
   }

   public LocalDateTime getCreated() {
      return created;
   } 

   public void setCreated(LocalDateTime created) {
      this.created = created;
   }

   public String getCreatedTime() {
      return this.getTimeFormatter().format(created);
   }
   
   public String getCreatedDate() {
      return this.getDateFormatter().format(created);
   }
   
   public DateTimeFormatter getTimeFormatter() {
      return this.tf;
   }
   
   public DateTimeFormatter getDateFormatter() {
      return this.df;
   }
   
   
   
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   
   public Integer getLoginId() {
      return loginId;
   }

   public void setLoginId(Integer loginId) {
      this.loginId = loginId;
   }

   
   @Override
   public int hashCode() {
      return Objects.hash(created, id, loginId);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      AbstractEntityId other = (AbstractEntityId) obj;
      return Objects.equals(id, other.id) && Objects.equals(loginId, other.loginId);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(this.getClass().getTypeName());
      builder.append(":id=" + id);
      builder.append(", loginId=" + loginId);
      builder.append(", created=").append(created);
      
      return builder.toString();
   }



}
