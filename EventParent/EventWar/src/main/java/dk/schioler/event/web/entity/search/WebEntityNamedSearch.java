package dk.schioler.event.web.entity.search;

public interface WebEntityNamedSearch extends WebEntityIdSearch {

   public String getName();

   public void setName(String name);

   public String getShortName();

   public void setShortName(String shortName);

   public String getDescription();

   public void setDescription(String description);

}
