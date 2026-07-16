package dk.schioler.event.base.entity;

import java.time.LocalDateTime;

public interface EntityBase {

//   public static final String FLD_ID = "ID";
//   public static final String FLD_OWNER_ID = "OWNER_ID";
//   public static final String FLD_CREATED_TS = "CREATED_TS";
//   
   
   public LocalDateTime getCreatedTS();

   public void setCreatedTS(LocalDateTime created);

   public String getCreatedTime();

   public String getCreatedDate();
   
   public Integer getOwnerId();

   public void setOwnerId(Integer ownerId);

   public Integer getId();

   public void setId(Integer ownerId);
   
}
