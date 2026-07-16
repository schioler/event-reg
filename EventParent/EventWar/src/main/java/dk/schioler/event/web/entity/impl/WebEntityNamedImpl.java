package dk.schioler.event.web.entity.impl;

import java.util.Map;

import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.web.entity.WebEntityNamed;

public class WebEntityNamedImpl extends WebEntityIdImpl implements WebEntityNamed {

   protected String name;
   protected String shortName;
   protected String description;

//   ***************************************

   public WebEntityNamedImpl() {
      super();
   }

   public WebEntityNamedImpl(AbstractEntityName entity) {
      super(entity);
      this.name = entity.getName();
      this.shortName = entity.getShortName();
      this.description = entity.getDescription();
   }

//   public WebEntityNamedImpl(Map<String, String> params) {
//      super(params);
//      name = params.get(REQ_NAME);
//      shortName = params.get(REQ_NAME_SHORT);
//      description = params.get(REQ_DESCRIPTION);
//   }

   @Override
   public void grabRequestValues(Map<String, String> params) {
      super.grabRequestValues(params);
      logger.debug("grabRequestValues=" + params);
      
      this.name = params.get(REQ_NAME);
      this.shortName = params.get(REQ_NAME_SHORT);
      this.description = params.get(REQ_DESCRIPTION);
   }

   @Override
   public AbstractEntityName getAsEntity(AbstractEntityName e) {
      AbstractEntityName asNamed = (AbstractEntityName) super.getAsEntity(e);
      asNamed.setName(name);
      asNamed.setShortName(shortName);
      asNamed.setDescription(description);
      return asNamed;
   }

   public String getName() {

      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public void setNameShort(String name) {

   }

   public String getNameShort() {
      return shortName;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append("\b,name=");
      builder.append(name);
      builder.append("\b, shortName=");
      builder.append(shortName);
      builder.append("\b, description=");
      builder.append(description);
//      builder.append("]");
      return builder.toString();
   }

}
