package dk.schioler.event.base.entity.visitor;

import java.util.ArrayList;
import java.util.List;

import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.BaseEventVisitor;

public class PrintTreeVisitor implements BaseEventVisitor {
//   private Logger logger = LoggerFactory.getLogger(getClass());

   private List<StringBuffer> output = new ArrayList<StringBuffer>();

   @Override
   public List<StringBuffer> getOutput() {
      return output;
   }
   
   @Override
   public void visit(BaseEvent baseEvent) {
      StringBuffer event = new StringBuffer("visit: name=" + baseEvent.getName() + ",  getLevel=" + baseEvent.getLevel());
      output.add(event.append("\n"));
      

   }

}
