package dk.schioler.event.web.usecase.data.impl;

import java.util.ArrayList;
import java.util.List;

import dk.schioler.event.web.entity.WebEntityId;
import dk.schioler.event.web.usecase.data.UseCaseData;

public abstract class UseCaseDataImpl implements UseCaseData {

   private List<String> units = new ArrayList<String>();

   private WebEntityId entity;

   @Override
   public List<String> getUnits() {
      return units;
   }

   @Override
   public void setUnits(List<String> units) {
      this.units.clear();
      this.units.addAll(units);

   }

   @Override
   public WebEntityId getWebEntity() {

      return this.entity;
   }

   @Override
   public void setWebEntity(WebEntityId entity) {
      this.entity = entity;
   }

   @Override
   public String toString() {
      final int maxLen = 2;
      StringBuilder builder = new StringBuilder();
      builder.append("\b, units=");
      builder.append(units != null ? units.subList(0, Math.min(units.size(), maxLen)) : null);
      builder.append("\b, entity=");
      builder.append(entity);
//      builder.append("]");
      return builder.toString();
   }

   
   
   
}
