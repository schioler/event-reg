package dk.schioler.event.web.usecase.data;

import java.util.List;

import dk.schioler.event.web.entity.WebEntityId;

public interface UseCaseData {

   public List<String> getUnits();

   public void setUnits(List<String> units);

 
   /**
    * The webentity in focus 
    * @return
    */
   
   public WebEntityId getWebEntity();

   public void setWebEntity(WebEntityId webEntity);

}
