package dk.schioler.event.base.organizeable;

import java.util.List;

import dk.schioler.event.base.entity.BaseEvent;

/**
 * Organisation is a wrapper interface, that, when implemented, <br>enables organising BaseEvents in a tree-like structure
 * It is based upon two attributes, that will act as parent and child.<br> 
 * <br>One example: parentId and id. Pretty straight forward. <br>
 * Adding a child, successfully, requires the parent to exist in the tree already. <br>
 * If no parent matches, the child is rejected.
 * <br><br>
 * Another example, could be a sorting mechanism, on i.e. createdTS.<br>
 * There is no requirement, that the parent is present. 
 * <code>
 * BaseEvent be1, be2<br>
 * if (be1 > be2) -> be1 is parent and be2 will be placed as child of be1
 * </code>
 * <
 * 
 *   
 */
public interface Organizeable {
   
   public ORGANIZEABLE_TYPE getOrganizeableType();

   public boolean add(Organizeable o);

   public List<Organizeable> getChildren();

   public Object getDataObject();

   public boolean isOrganizeableMatch(Organizeable child);

   
}
