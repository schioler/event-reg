package dk.schioler.event.web.entity.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dk.schioler.event.base.entity.EventType;
import dk.schioler.event.web.entity.WebEntityEventType;

public class WebEntityEventTypeImpl extends WebEntityNamedImpl implements WebEntityEventType {

   private List<WebEntityEventTemplateImpl> webEventTemplates = new ArrayList<WebEntityEventTemplateImpl>();
   
   public WebEntityEventTypeImpl() {
      super();
   }

   public WebEntityEventTypeImpl(EventType eventType) {
      super(eventType);        
   }
   
   
   public void addWebEventTemplate(WebEntityEventTemplateImpl webEventTemplate) {
      this.webEventTemplates.add(webEventTemplate);
   }

   
   public List<WebEntityEventTemplateImpl> getWebEventTemplates() {
      return webEventTemplates;
   }

   public void setWebEventTemplates(List<WebEntityEventTemplateImpl> webEventTemplates) {
      this.webEventTemplates = webEventTemplates;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(webEventTemplates);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      WebEntityEventTypeImpl other = (WebEntityEventTypeImpl) obj;
      return Objects.equals(webEventTemplates, other.webEventTemplates);
   }

   @Override
   public String toString() {
      final int maxLen = 2;
      StringBuilder builder = new StringBuilder();
      builder.append("WebEntityEventTypeImpl [");
      builder.append(super.toString());
      builder.append(", webEventTemplates=");
      builder.append(webEventTemplates != null ? webEventTemplates.subList(0, Math.min(webEventTemplates.size(), maxLen)) : null);
      builder.append("]");
      return builder.toString();
   }

   

}
