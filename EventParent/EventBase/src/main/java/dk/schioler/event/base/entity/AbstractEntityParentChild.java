package dk.schioler.event.base.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

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
            logger.trace("child has been removed: child=" + child);
         }
         child.setParent(null);
      } else {
         logger.warn("removeChild: recieved child == null, will make no changes");
      }
   }

   public void addChild(AbstractEntityParentChild child) {
      logger.debug("will add child:" + child);
      if (child != null) {
         if (!children.contains(child)) {
            child.setParent(this);
         }
      } else {
         logger.warn("addChild: received child == null. Will make no changes");
      }
   }

   public abstract AbstractEntityParentChild instantiateParent();

   private void setParent(Integer parentId, AbstractEntityParentChild parent) {
      if (parentId == null && parent == null) {
         logger.trace("setParent(null,null)");
         if (this.parent != null) {
            logger.trace("setParent: this.parent not null - will remove this from parent.children and set this.parent= null");
            this.parent.getChildren().remove(this);
            this.parent = null;
         }
      } else if (parentId != null && parent == null) {
         logger.trace("setParent(" + parentId + ",null)");
         if (this.parent != null) {
            logger.trace("this.parent != null, will remove this from parent.children");
            this.parent.getChildren().remove(this);
         }

         logger.trace("setParent: this.parent has new instance, setting id and adding to children");
         this.parent = instantiateParent();
         this.parent.setId(parentId);
         this.parent.getChildren().add(this);

      } else if (parentId == null && parent != null) {
         logger.trace("setParent(null, " + parent.getId() + ")");
         if (this.parent != null) {
            logger.trace("this.parent != null");
//            if (!this.parent.equals(parent)) {
            logger.trace("will remove this(" + this.getId() + ") from parents.children");
            this.parent.getChildren().remove(this);
//            }
            this.parent = parent;
            this.parent.getChildren().add(this);

         } else {
            this.parent = parent;
            this.parent.getChildren().add(this);
         }
         logger.trace("have this.parent from argument, and added this to parent.children");
      } else if (parentId != null && parent != null) {
         logger.trace("setParent(" + parentId + ", " + parent.getId());
         if (!parentId.equals(parent.getId())) {
            throw new EventBaseException("specified id=" + parentId + ", parent.getId()=" + parent.getId());
         }

         if (this.parent != null) {
//            if (!this.parent.equals(parent)) {
            this.parent.getChildren().remove(this);
//            }
//            this.parent = parent;
//            this.parent.getChildren().add(this);

//         } else {
         }
         this.parent = parent;
         this.parent.getChildren().add(this);
         logger.trace("this.parent has been set, and this been added to parent.children");
      }
//      logger.trace("setParent: this=" + this + ", parent= " + this.parent);
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
         builder.append(parent.getClass());
         builder.append("[ parentId=" + parent.getId() + ", parent.loginId=" + parent.getLoginId() + ", name=" + parent.getName()).append("]");
      } else {
         builder.append("=null");
      }

      builder.append("\n    ->->->->children:");
      for (AbstractEntityName abstractEntity : children) {
         builder.append("\n      child.id=").append(abstractEntity.getId());
         builder.append(", child.loginId=").append(abstractEntity.getLoginId());
         builder.append(", child.name=").append(abstractEntity.getName());
         builder.append(", child.created=").append(abstractEntity.getCreated());
      }

//      builder.append("\n******************\n");
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
      if (obj == null)
         return false;
      
      if (getClass() != obj.getClass())
         return false;

      AbstractEntityParentChild other = (AbstractEntityParentChild) obj;

      boolean idEq = false;
      boolean loginIdEq = false;
      boolean nameEq = false;
      boolean shortNameEq = false;
      boolean childrenEq = false;

      Integer thisId = this.getId();
      Integer otherId = other.getId();
      if (thisId != null) {
         if (otherId != null) {
            idEq = thisId.equals(otherId);
         } else {
            idEq  = false;
         }
      } else {
         if (otherId != null) {
            idEq  = false;
         } else {
            idEq  = true
                  ;
         }
      }

      Integer thisLoginId = this.getLoginId();
      Integer otherLoginId = other.getLoginId();
      if (thisLoginId != null) {
         if (otherLoginId != null) {
            if (thisLoginId.equals(otherLoginId)) {
               loginIdEq = true;
            } else {
               loginIdEq = false;
            }
         } else {
            loginIdEq = false;
         }
      } else {
         if (otherLoginId != null) {
            loginIdEq = false;
         } else {
            loginIdEq = true;
            ;
         }
      }

      if (StringUtils.equals(this.getName(), other.getName())) {
         nameEq = true;
      } else {
         nameEq = false;
      }

      if (StringUtils.equals(this.getShortName(), other.getShortName())) {
         shortNameEq = true;
      } else {
         shortNameEq = false;
      }

      if (this.children.size() == other.children.size()) {
         childrenEq = true;
      } else {
         childrenEq = false;
      }

      boolean parentEq = false;
      if (this.parent != null) {
         if (other.parent != null) {
            parentEq = this.parent.equals(other.parent);
         } else {
            parentEq = false;
         }
      } else {
         if (other.parent != null) {
            parentEq = false;
         } else {
            parentEq = true;
         }
      }

      if (idEq && loginIdEq && nameEq && shortNameEq && childrenEq && parentEq) {
         return true;
      } else {
         return false;
      }


   }

}
