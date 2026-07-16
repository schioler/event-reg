package dk.schioler.event.base.organizeable;

import java.util.List;

import dk.schioler.event.base.entity.BaseEvent;

public interface OrganizeableVisitor {
   public void visit(BaseEvent baseEvent);

   public List<StringBuffer> getOutput();

}
