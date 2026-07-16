package dk.schioler.event.web.entity;

import java.util.Map;

import dk.schioler.event.base.entity.AbstractEntityId;

public abstract interface WebEntityId {

   public static final String REQ_ID = "req-id";
   public static final String REQ_CREATED = "req-created";
   public static final String REQ_CREATED_DATE = "req-created-date";
   public static final String REQ_CREATED_TIME = "req-created-time";
   public static final String REQ_LOGIN_ID = "req-login-id";
   public static final String REQ_TOKEN = "req-token";

   public void grabRequestValues(Map<String, String> params);

   public AbstractEntityId getAsEntity(AbstractEntityId e);

//   public String getIdKey();
//
//   public String getCreatedKey();
//
//   public String getLoginIdKey();
//
//   public String getTokenKey();

   public String getId();
   public void setId(String Id);

   public String getCreated();
   public void setCreated(String created);

   public String getLoginId();  
   public void setLoginId(String loginId);

   public String getToken();
   public void setToken(String token);



}
