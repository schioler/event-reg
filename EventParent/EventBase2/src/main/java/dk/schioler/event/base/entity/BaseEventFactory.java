package dk.schioler.event.base.entity;

import java.time.LocalDateTime;

public interface BaseEventFactory {
   public BaseEvent createBaseEvent(Integer id, Integer ownerId, LocalDateTime createdTs, EVENT_TYPE eventType, String name, TREE_TYPE treeType);
}
