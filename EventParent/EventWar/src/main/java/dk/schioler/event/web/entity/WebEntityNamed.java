package dk.schioler.event.web.entity;

import java.util.Map;

import dk.schioler.event.base.entity.AbstractEntityName;

public interface WebEntityNamed extends WebEntityId {

   public static final String REQ_NAME = "req-name";
   public static final String REQ_NAME_SHORT = "req-name-short";
   public static final String REQ_DESCRIPTION = "req-description";

   public String getName();
   public void setName(String name);

   public String getNameShort();
   public void setNameShort(String name);
   
   public String getDescription();
   public void setDescription(String name);
   

   
   
   public void grabRequestValues(Map<String, String> params);
   
   public AbstractEntityName getAsEntity(AbstractEntityName e);


}
