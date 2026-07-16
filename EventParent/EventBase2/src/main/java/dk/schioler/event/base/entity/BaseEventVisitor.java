package dk.schioler.event.base.entity;

import java.util.List;

public interface BaseEventVisitor {
   public void visit(BaseEvent baseEvent);

   public List<StringBuffer> getOutput();

}
