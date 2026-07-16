package dk.schioler.event.base.organizeable;

import java.util.List;

import dk.schioler.event.base.entity.BaseEvent;

public class TestOrganizeableNameParentName implements Organizeable {

   private BaseEvent dataObject;

   
   private List<Organizeable> children;


   

   @Override
   public ORGANIZEABLE_TYPE getOrganizeableType() {
      return ORGANIZEABLE_TYPE.PARENT_NAME;
   }

   @Override
   public boolean add(Organizeable o) {
      boolean retVal = false;
      if (this.isOrganizeableMatch(o)) {
         retVal = true;
         children.add(o);
      } else {
         for (Organizeable child : children) {
            if (child.isOrganizeableMatch(o)) {
               retVal = true;
               child.add(o);
               break;
            }
         }
      }

      return retVal;
   }

   @Override
   public List<Organizeable> getChildren() {
      return children;
   }

   
   
   @Override
   public Object getDataObject() {
      return dataObject;
   }

   @Override
   public boolean isOrganizeableMatch(Organizeable child) {
      boolean retVal = false;
      BaseEvent childData = (BaseEvent) child.getDataObject();
      
      
      if (dataObject.getName().equals(childData.getParentName())) {
         retVal = true;
      }
      
      
      return retVal;
   }

}
