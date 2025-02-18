package dk.schioler.event.base.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dk.schioler.event.base.EventBaseException;


public abstract class AbstractEntityParentChild extends AbstractEntityName {

   private AbstractEntityParentChild parent;

   private List<AbstractEntityParentChild> children = new ArrayList<AbstractEntityParentChild>();


   public AbstractEntityParentChild() {
      super();
   }

   /*
    * All things concerning child && parent
    */
   /* GETTERS */
   public AbstractEntityParentChild getParent() {
      return this.parent;
   }

   public Integer getParentId() {
      Integer retVal = null;
      if (this.parent != null) {
         retVal = this.parent.getId();
      }
      return retVal;
   }

   public List<AbstractEntityParentChild> getChildren() {
      return this.children;
   }

   /* SETTERS */

   public void setParentId(Integer id) {
      setParent(id, null);
   }

   public void setParent(AbstractEntityParentChild parent) {
      setParent(null, parent);
   }

   public void removeChild(AbstractEntityParentChild child) {
      if (child != null) {
         if (this.children.contains(child)) {
            children.remove(child);
         }
         child.setParent(child);
      }
   }

   public void addChild(AbstractEntityParentChild child) {
      if (child != null) {
         if (!children.contains(child)) {
            logger.debug("will add child:");
            child.setParent(this);
         }
      }
   }

   public abstract AbstractEntityParentChild instantiateParent();

   private void setParent(Integer parentId, AbstractEntityParentChild parent) {
      if (parentId == null && parent == null) {
         if (this.parent != null) {
            this.parent.getChildren().remove(this);
            this.parent = null;
         }
      } else if (parentId != null && parent == null) {
         if (this.parent != null) {
            this.parent.getChildren().remove(this);
         }

         this.parent = instantiateParent();
         this.parent.setId(parentId);
         this.parent.getChildren().add(this);

      } else if (parentId == null && parent != null) {
         if (this.parent != null) {
            if (!this.parent.equals(parent)) {
               this.parent.getChildren().remove(this);
            }
            this.parent = parent;
            this.parent.getChildren().add(this);

         } else {
            this.parent = parent;
            this.parent.getChildren().add(this);
         }
      } else if (parentId != null && parent != null) {
         if (!parentId.equals(parent.getId())) {
            throw new EventBaseException("specified id=" + parentId + ", parent.getId()=" + parent.getId());
         }
         if (this.parent != null) {
            if (!this.parent.equals(parent)) {
               this.parent.getChildren().remove(this);
            }
            this.parent = parent;
            this.parent.getChildren().add(this);

         } else {
            this.parent = parent;
            this.parent.getChildren().add(this);
         }

      }
   }

   public String getParentString() {
      StringBuilder builder = new StringBuilder();
      AbstractEntityParentChild p = this.getParent();
      if (p != null) {
         builder.append("[ id=").append(p.getId());
         builder.append(", loginId=").append(p.getLoginId());
         builder.append(", name=").append(p.getName());
         builder.append("]");
      } else {
         builder.append("null");
      }
      return builder.toString();
   }

   public String getChildString() {
      StringBuilder sb = new StringBuilder();
      List<AbstractEntityParentChild> children = getChildren();
      for (AbstractEntityParentChild child : children) {
         sb.append("[ id=").append(child.getId());
         sb.append(", loginId=").append(child.getLoginId());
         sb.append(", name=").append(child.getName());
         sb.append(" ]");
      }

      return sb.toString();
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder("\n");
      builder.append(super.toString());

      builder.append("\n    ->->->->parent=");
      if (this.parent != null) {
         builder.append("[ parentId=" + parent.getId() + ", parent.loginId=" + parent.getLoginId() + ", name=" + parent.getName()).append("]");
      } else {
         builder.append("parent==null");
      }

      builder.append("\n    ->->->->children:\n");
      for (AbstractEntityName abstractEntity : children) {
         builder.append("      [child.id=").append(abstractEntity.getId());
         builder.append(", child.loginId=").append(abstractEntity.getLoginId());
         builder.append(", child.name=").append(abstractEntity.getName());
         builder.append("]");
      }

      builder.append("\n******************");
      return builder.toString();
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(children, parent);
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (!super.equals(obj))
         return false;
      if (getClass() != obj.getClass())
         return false;
      AbstractEntityParentChild other = (AbstractEntityParentChild) obj;
      return Objects.equals(children, other.children) && Objects.equals(parent, other.parent);
   }

}
