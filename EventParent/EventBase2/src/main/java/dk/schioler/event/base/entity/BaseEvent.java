package dk.schioler.event.base.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BaseEvent extends EntityBase {
  
   public void accept(BaseEventVisitor visitor);
   
   public LocalDateTime getEventCreatedTS();

   public void setEventCreatedTS(LocalDateTime eventCreatedTs);

   public Integer getEventId();

   public void setEventId(Integer id);

   public Integer getEventCategoryId();

   public void setEventCategoryId(Integer categoryId);

   public void setName(String name);

   public String getName();

   public EVENT_TYPE getEventType();

//   public void setEventType(EVENT_TYPE eventType);
   
   
//   Tree related
   public TREE_TYPE getTreeType();
   
//   public void setTreeType(TREE_TYPE treeType);
   
// tree position value(s)
// parentname/name
//  name/date  
//  parent/
   
   public List<BaseEvent> getChildren();

   public boolean addChild(BaseEvent potChild);
   
   public boolean addChildToParentIdTree(BaseEvent parent, BaseEvent be);
   
   public boolean addChildToParentNameTree(BaseEvent parent, BaseEvent be);

   public void setParentName(String parentName);

   public String getParentName();

   public void setParentId(Integer parentId);

   public Integer getParentId();

   public void setParent(BaseEvent parent);

   public BaseEvent getParent();

   public void setLevel(int l);
//
   public int getLevel();

   public BigDecimal getObjectValue();

   public boolean isRoot();

   public Map<Integer, BigDecimal> getValuesOnLevels();

   public void setBaseEventFactory(BaseEventFactory baseEventFactory);
}