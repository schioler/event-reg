package dk.schioler.event.base.entity.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;

import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.BaseEventException;
import dk.schioler.event.base.entity.BaseEventFactory;
import dk.schioler.event.base.entity.BaseEventVisitor;
import dk.schioler.event.base.entity.EVENT_TYPE;
import dk.schioler.event.base.entity.EventEntityException;
import dk.schioler.event.base.entity.TREE_TYPE;

public abstract class BaseEventImpl extends EntityBaseImpl implements BaseEvent {

   public static final int LEVEL_NOT_SET = -1;
//   protected Logger logger = LoggerFactory.getLogger(getClass());
   private Object eventLock = new Object();

//   private Object treeTypeLock = new Object();
   private final TREE_TYPE treeType;
   private Integer parentId = null;
   private String parentName = null;
   private BaseEvent parent = null;

   private int level;
//   private boolean isRoot;
   private List<BaseEvent> children = new ArrayList<BaseEvent>();

   // *****************************************
   private LocalDateTime eventCreatedTs;
   private Integer eventId;
   private Integer eventCategoryId;
   private final EVENT_TYPE eventType;
   private String name;
   private BaseEventFactory baseEventFactory;

   // *****************************************'''

   protected BaseEventImpl(Integer id, Integer loginId, LocalDateTime created, EVENT_TYPE eventType, String name, TREE_TYPE treeType) {
      super(id, loginId, created);
      this.eventType = eventType;
      this.treeType = treeType;
      this.name = name;
   }

//   protected BaseEventImpl(Integer id, Integer loginId, LocalDateTime created, EVENT_TYPE eventType, String name, Integer parentId) {
//      super(id, loginId, created);
//      this.eventType = eventType;
//      this.name = name;
//      setParentId(parentId);
//   }

//   protected BaseEventImpl(Integer id, Integer loginId, LocalDateTime created, EVENT_TYPE eventType, String name, String parentName) {
//      super(id, loginId, created);
//      this.eventType = eventType;
//      this.name = name;
//      setParentId(parentId);
//   }

   @Override
   public boolean isRoot() {
      boolean isRoot = false;
      if (this.parent == null) {
         if (this.level <= 0) {
            isRoot = true;
         } else if (this.level > 0) {

         }
      } else {

      }
      return isRoot;
   }

   @Override
   @Bean(name = "baseEventFactory")
   public void setBaseEventFactory(BaseEventFactory baseEventFactory) {
      this.baseEventFactory = baseEventFactory;
   }

   @Override
   public void accept(BaseEventVisitor visitor) {
      visitor.visit(this);

      for (BaseEvent baseEvent : children) {
         baseEvent.accept(visitor);
      }
   }

//   ***************************************

   public boolean addChild(BaseEvent parent, BaseEvent child) {
      boolean success = false;
//      synchronized (eventLock) {
      if (parent != null) {
         if (child != null) {
            logger.debug("addChild, parent.name=" + parent.getName() + ", parent.level=" + parent.getLevel() + ", parent.id=" + parent.getId()
                  + ", parent.parentId=" + parent.getParentId() + ", parent.parentName=" + parent.getParentName());
            logger.debug("           child.name=" + child.getName() + ", child.level=" + child.getLevel() + ", child.id=" + child.getId() + ", child.parentId="
                  + child.getParentId() + ", child.parentName=" + child.getParentName());

            if (TREE_TYPE.PARENT_ID.equals(child.getTreeType())) {
               Integer id = parent.getId();
               Integer pId = child.getParentId();
               if (id != null && pId != null) {
                  if (id.equals(pId)) {
                     // weve found a place for the child...
                     child.setLevel(parent.getLevel() + 1);
                     child.setParent(parent);
                     parent.getChildren().add(child);
                     success = true;
                  }
               }

            } else if (TREE_TYPE.PARENT_NAME.equals(child.getTreeType())) {
               String parentName = parent.getName();
               String childparentName = child.getParentName();
               if (StringUtils.isNoneEmpty(parentName) && StringUtils.isNotBlank(childparentName)) {
                  if (parentName.equals(childparentName)) {
                     logger.debug("found a match and a home for the child: name=" + child.getName() + ", parentName=" + child.getParentName());
                     child.setLevel(parent.getLevel() + 1);
                     parent.getChildren().add(child);
                     child.setParent(parent);
                     success = true;
                  }
               }

            } else {
               throw new BaseEventException("TreeType not supported:" + child.getTreeType());
            }

         } else {
            logger.info("addChild (parent, child): received child == null");
         }

      } else {
         logger.info("addChild (parent, child): received parent == null");
      }

      return success;
   }

   @Override
   public boolean addChild(BaseEvent child) {
      boolean found = false;
      if (child != null) {
         logger.debug("addChild: received BaseEvent, (be): name=" + child.getName() + ", parentId=" + child.getParentId() + ", parentName="
               + child.getParentName() + ", level=" + child.getLevel());

//      synchronized (eventLock) {
         if (TREE_TYPE.PARENT_ID.equals(child.getTreeType())) {
//            newParent = addChildToParentIdTree(this, potChild);
         } else if (TREE_TYPE.PARENT_NAME.equals(child.getTreeType())) {
            found = addChildToParentNameTree(this, child);
            if (found == true) {
               logger.debug("found child");
            }
         } else {
            throw new BaseEventException("TreeType not supported:" + child.getTreeType());
         }

      } else {
         logger.info("addChild (" + this.treeType + ") recieved a baseEvent == null ");
      }
//      }
      return found;
   }

   @Override
   public boolean addChildToParentIdTree(BaseEvent potentialParent, BaseEvent be) {
      logger.trace("addChildToParentIdTree(" + potentialParent + ", child=" + be);
      boolean retVal = false;
      if (be != null && potentialParent != null) {
         Integer potParentId = potentialParent.getId();
         Integer potChildId = be.getParentId();
         logger.trace("found potParentId=" + potParentId + ", and potChildId=" + potChildId);
         if (potParentId != null) {
            if (potChildId != null) {
               if (potParentId.equals(potChildId)) {
                  // we have a match
//                  potentialParent.addChild(be)
//                  this.children.add(be);
//                  be.setLevel(this.level + 1);
//                  be.setParent(this);
//                  retVal = true
               } else {
                  logger.info("no direct match, will attempt with the children");
                  for (BaseEvent child : children) {
                     retVal = addChildToParentIdTree(child, be);
//                     if (retVal != null) {
//                        break;
//                     }
                  }
               }
            } else {
               logger.info("the event you attempt to add to a tree, has no parentId set. Will return with failed course");
            }
         } else {
            logger.info("potParentId was null, will search in lower places... ");

         }
      }
      return retVal;

   }

   @Override
   public boolean addChildToParentNameTree(BaseEvent potentialParent, BaseEvent potChild) {
      boolean found = false;
      logger.trace("addChildToParentNameTree:");

      if (potChild != null && potentialParent != null) {
         logger.debug("addChildToParentNameTree( potentialParent:" + potentialParent.getName() + ", child=" + potChild.getParentName());

         String potParentName = potentialParent.getName();
         String potChildName = potChild.getParentName();
         if (potParentName != null) {
            if (potChildName != null) {
               if (potParentName.equals(potChildName)) {
                  // we found a parent for the child:
                  addChild(potentialParent, potChild);
//                  potChild.setLevel(potentialParent.getLevel() + 1);
//                  potChild.setParent(potentialParent);
//                  potentialParent.addChild(potChild);

                  found = true;
//                  break;
//                  this.children.add(be);

               } else {
                  logger.info("no match on this level: will attempt with the children");
                  for (BaseEvent child : potentialParent.getChildren()) {
                     found = addChildToParentNameTree(child, potChild);
                     if (found == true) {
                        break;
                     }
                  }
               }
            } else {
               logger.info("the event you attempt to add to a tree, has no parentId set. Will return with failed course");
            }
         } else {
            logger.info("potParentId was null, will search in lower places... ");

         }
      }
      return found;

   }

   @Override
   public TREE_TYPE getTreeType() {
      return treeType;
   }

   @Override
   public void setParentName(String parentName) {
//      synchronized (eventLock) {
      this.parentName = parentName;
//      }
   }

   @Override
   public String getParentName() {
//      synchronized (eventLock) {
      return this.parentName;
//      }
   }

   @Override
   public void setParentId(Integer parentId) {
      synchronized (eventLock) {

//         if (this.parent != null) {
//            this.baseEventFactory.createBaseEvent(parentId, getOwnerId(), getCreatedTS(), this.eventType, name, treeType);
//         } else {
//
//         }
//         this.parent = parent;
//         if (this.parent == null) {
//            this.level = 0;
//
//         } else {
//            this.level = this.parent.getLevel() + 1;
//
//         }
      }

   }

   @Override
   public Integer getParentId() {
//      synchronized (eventLock) {
      return this.parentId;
//      }
   }

   @Override
   public List<BaseEvent> getChildren() {
//      synchronized (eventLock) {
      return this.children;
//      }
   }

   @Override
   public void setParent(BaseEvent parent) {
//      synchronized (eventLock) {
      this.parent = parent;
      if (this.parent == null) {
         this.level = 0;

      } else {
         this.level = this.parent.getLevel() + 1;
      }
//      }
   }

   @Override
   public BaseEvent getParent() {
//      synchronized (eventLock) {
      BaseEvent p = this.parent;
      if (p == null) {
         if (this.getLevel() == 0) {
            return parent;
         } else {
            throw new BaseEventException("level is not correct");
         }

      } else {
         if (!this.isRoot()) {
            if (this.getLevel() > 0) {
               return parent;
            } else {
               throw new BaseEventException("level is not correct");
            }
         } else {
            throw new BaseEventException("isRoot is not correct");
         }
      }
//      }
   }

   @Override
   public Map<Integer, BigDecimal> getValuesOnLevels() {
      logger.debug("level=" + this.level + ", parent=" + this.parent);
      Map<Integer, BigDecimal> levels = new HashMap<Integer, BigDecimal>();
      if (this.level != 0) {
         throw new EventEntityException("levels are not requested from root node");
      } else {
         Integer currentLevel = 0;
         BigDecimal currentValue = new BigDecimal(1);
         levels.put(currentLevel, currentValue);

         Map<Integer, BigDecimal> levelValues = getNextLevelValues(currentLevel, levels);
         return levelValues;
      }
   }

   protected Map<Integer, BigDecimal> getNextLevelValues(Integer level, Map<Integer, BigDecimal> collector) {

      Integer nextLevel = level + 1;

      if (!collector.containsKey(nextLevel)) {
         collector.put(nextLevel, new BigDecimal(0));
      }

      BigDecimal nextLevelSum = new BigDecimal(0);
      if (!children.isEmpty()) {
         for (BaseEvent child : children) {
            BigDecimal childValue = child.getObjectValue();
            nextLevelSum = nextLevelSum.add(childValue);
//         nextLevelSum = currentVal.add(childValue);
            // going for the next level
            getNextLevelValues(nextLevel, collector);
         }
         logger.debug("will be adding " + nextLevelSum + " to collector, level=" + nextLevelSum.toPlainString());
         collector.put(nextLevel, nextLevelSum);
      } else {
         logger.debug("Object has no children - skipping ");
      }
      return collector;
   }

   @Override
   public BigDecimal getObjectValue() {
      return new BigDecimal(1);
   }

   @Override
   public void setLevel(int l) {
      this.level = l;

   }

   @Override
   public int getLevel() {
      return this.level;
   }

   @Override
   public Integer getEventCategoryId() {

      return eventCategoryId;
   }

   @Override
   public void setEventCategoryId(Integer categoryId) {
      this.eventCategoryId = categoryId;

   }

   @Override
   public void setName(String name) {
      this.name = name;

   }

   @Override
   public String getName() {

      return this.name;
   }

   @Override
   public Integer getEventId() {

      return eventId;
   }

   @Override
   public void setEventId(Integer id) {
      this.eventId = id;

   }

   @Override
   public EVENT_TYPE getEventType() {

      return this.eventType;
   }

//   @Override
//   public void setEventType(EVENT_TYPE eventType) {
//      this.eventType = eventType;
//   }

   @Override
   public LocalDateTime getEventCreatedTS() {
      return eventCreatedTs;
   }

   @Override
   public void setEventCreatedTS(LocalDateTime eventCreatedTs) {
      this.eventCreatedTs = eventCreatedTs;

   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();

      builder.append(super.toString());

      builder.append("BaseEventImpl [eventCreatedTs=");
      builder.append(eventCreatedTs);
      builder.append(", eventId=");
      builder.append(eventId);
      builder.append(", eventCategoryId=");
      builder.append(eventCategoryId);
      builder.append(", eventType=");
      builder.append(eventType);
      builder.append(", name=");
      builder.append(name);
      builder.append(", level=");
      builder.append(level);
      builder.append(", rootLock=");
      builder.append(eventLock);
      builder.append(", parent=");
      builder.append(parent);
      builder.append(", children=");
      builder.append(children.size());
      builder.append("]");
      return builder.toString();
   }

}
