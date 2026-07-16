package dk.schioler.event.web.entity.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.entity.AbstractEntityId;
import dk.schioler.event.web.common.WebEntityUtil;
import dk.schioler.event.web.entity.WebEntityId;

public abstract class WebEntityIdImpl implements WebEntityId {

   protected Logger logger = LoggerFactory.getLogger(getClass());

   protected String id;
   protected String loginId;
   protected String created;

   protected String token;

   public WebEntityIdImpl(AbstractEntityId entity) {
      if (entity != null) {
         Integer eid = entity.getId();
         if (eid != null) {
            this.id = eid.toString();
         }
         Integer lId = entity.getLoginId();
         if (lId != null) {
            this.loginId = lId.toString();
         }

         LocalDateTime ldt = entity.getCreated();
         if (ldt != null) {
            this.created = WebEntityUtil.getDateTimeFrom(ldt);
         }
      }

   }

//   public WebEntityIdImpl(Map<String, String> params) {
//      this.grabRequestValues(params);
//   }

   public WebEntityIdImpl() {
      super();
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getLoginId() {
      return loginId;
   }

   public void setLoginId(String loginId) {
      this.loginId = loginId;
   }

   public String getCreated() {
      return created;
   }

   public void setCreated(String created) {
      this.created = created;
   }

   public void setToken(String loginToken) {
      this.token = loginToken;
   }

   @Override
   public String getToken() {

      return token;
   }

   public void grabRequestValues(Map<String, String> params) {
      logger.debug("grabRequestValues:" + params);

      String reqId = params.get(REQ_ID);

      if (StringUtils.isNotBlank(id)) {
         if (StringUtils.isNotBlank(reqId)) {
            if (!id.equals(reqId)) {
               throw new EventWebEntityException("WebEntityIdImpl: tampering with the instance id - not good");
            }
         } else {
            // leaving as is.
         }
      } else {
         // leaving id as is
      }

      if (StringUtils.isNotBlank(loginId)) {
         // has a value
         String reqLoginId = params.get(REQ_LOGIN_ID);

         if (!loginId.equals(reqLoginId)) {
            throw new EventWebEntityException("tampering with the instance id - not good");
         }

      } else {
         // leaving as is
      }

      
//      String reqToken = params.get(REQ_TOKEN);
//      String reqCreated = params.get(REQ_CREATED);
//      loginId = params.get(REQ_LOGIN_ID);
//      token = params.get(REQ_TOKEN);
//      created = params.get(REQ_CREATED);

      
   }

   public AbstractEntityId getAsEntity(AbstractEntityId entity) {
      if (entity != null) {
         if (StringUtils.isNotEmpty(id)) {
            try {
               Integer idInt = Integer.valueOf(id);
               entity.setId(idInt);
            } catch (NumberFormatException e) {
               logger.error(e.getMessage(), e);
               entity.setId(null);
            }
         } else {
            entity.setId(null);
         }

         if (StringUtils.isNotEmpty(loginId)) {

            try {
               Integer loginIdInt = Integer.valueOf(loginId);
               entity.setLoginId(loginIdInt);
            } catch (NumberFormatException e) {
               logger.error(e.getMessage(), e);
               entity.setLoginId(null);
            }
         } else {
            entity.setLoginId(null);
         }

         if (StringUtils.isNotEmpty(created)) {
            LocalDateTime localDateTimeFrom = WebEntityUtil.getLocalDateTimeFrom(created);
            entity.setCreated(localDateTimeFrom);
         } else {
            entity.setCreated(null);
         }
      } else {
         logger.info("received entity==null");
      }
      return entity;
   }

   @Override
   public int hashCode() {
      return Objects.hash(created, id, loginId, token);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      WebEntityIdImpl other = (WebEntityIdImpl) obj;
      return Objects.equals(created, other.created) && Objects.equals(id, other.id) && Objects.equals(loginId, other.loginId)
            && Objects.equals(token, other.token);
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("WebEntityIdImpl [id=");
      builder.append(id);
      builder.append(", loginId=");
      builder.append(loginId);
      builder.append(", created=");
      builder.append(created);
      builder.append(", loginToken=");
      builder.append(token);
      builder.append("]");
      return builder.toString();
   }

}
