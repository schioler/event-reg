package dk.schioler.event.base.entity.impl;

import java.time.LocalDateTime;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.schioler.event.base.entity.BaseEvent;
import dk.schioler.event.base.entity.BaseEventFactory;
import dk.schioler.event.base.entity.BaseEventVisitor;
import dk.schioler.event.base.entity.TREE_TYPE;
import dk.schioler.event.base.entity.visitor.PrintTreeVisitor;

public class BaseEventTreeTest {

   private final Logger logger = LoggerFactory.getLogger(getClass());

   public static final String ROOT = "ROOT";
   
   public static final String B1 = "B1";
   public static final String B2 = "B2";
   public static final String B3 = "B3";

   public static final String C1 = "C1";
   public static final String C2 = "C2";
   public static final String C3 = "C3";
   public static final String C4 = "C4";
   public static final String C5 = "C5";
   public static final String C6 = "C6";

   public static final String D1 = "D1";
   public static final String D2 = "D2";
   public static final String D3 = "D3";
   public static final String D4 = "D4";
   public static final String D5 = "D5";
   public static final String D6 = "D6";

   public static final String E1 = "E1";
   public static final String E2 = "E2";
   public static final String E3 = "E3";

   @Test
   public void testGetChildren() {
      BaseEventVisitor pV = new PrintTreeVisitor();
      BaseEventFactory eventFactory = new BaseEventFactoryImpl();

      BaseEvent root = new MedicineEventImpl(1, 5, LocalDateTime.now(), ROOT, TREE_TYPE.PARENT_NAME);
      root.setBaseEventFactory(eventFactory);
//      logger.debug("root element=" + root);

      // lvl 1

      BaseEvent b1 = new MedicineEventImpl(10, 5, LocalDateTime.now(), B1, TREE_TYPE.PARENT_NAME);
      b1.setBaseEventFactory(eventFactory);
      b1.setParentName("ROOT");
      b1.setParentId(1);
      root.accept(pV);
      root.addChild(b1);
      logger.debug("adding child:" + b1.getName() + " level=" + b1.getLevel());

      BaseEvent b2 = new MedicineEventImpl(11, 5, LocalDateTime.now(), B2, TREE_TYPE.PARENT_NAME);
      b2.setBaseEventFactory(eventFactory);
      b2.setParentName(B1);
      b2.setParentId(10);

      root.addChild(b2);

      BaseEvent b3 = new MedicineEventImpl(12, 5, LocalDateTime.now(), B3, TREE_TYPE.PARENT_NAME);
      b3.setBaseEventFactory(eventFactory);
      b3.setParentName(B2);
      b3.setParentId(11);
      root.addChild(b3);
      
      BaseEvent c1 = new MedicineEventImpl(20, 5, LocalDateTime.now(), C1, TREE_TYPE.PARENT_NAME);
      c1.setBaseEventFactory(eventFactory);
      c1.setParentId(10);
      c1.setParentName(ROOT);
      root.addChild(c1);
      root.accept(pV);
      logger.debug("" + pV.getOutput());

      //      BaseEvent c2 = new MedicineEventImpl(C2);
      
      
//      BaseEvent c3 = new MedicineEventImpl(C3);
//      BaseEvent c4 = new MedicineEventImpl(C4);
//      BaseEvent c5 = new MedicineEventImpl(C5);
//      BaseEvent c6 = new MedicineEventImpl(C6);

//      BaseEvent d1 = new MedicineEventImpl(30, 5, LocalDateTime.now(), D1, TREE_TYPE.PARENT_ID);
//      d1.setBaseEventFactory(eventFactory);
//      d1.setParentId(20);
//      d1.setParentName(C1);
//      BaseEvent d2 = new MedicineEventImpl(D2);
//      BaseEvent d3 = new MedicineEventImpl(D3);
//      BaseEvent d4 = new MedicineEventImpl(D4);
//      BaseEvent d5 = new MedicineEventImpl(D5);
//      BaseEvent d6 = new MedicineEventImpl(D6);
//
//      BaseEvent e1 = new MedicineEventImpl(40, 5, LocalDateTime.now(), E1, TREE_TYPE.PARENT_ID);
//      e1.setBaseEventFactory(eventFactory);
//      e1.setParentId(30);
//      e1.setParentName(D1);
//      BaseEvent e2 = new MedicineEventImpl(E2);
//      BaseEvent e3 = new MedicineEventImpl(E3);

//      root.addChild(b1);
//    root.addChild(b2);
//      logger.debug("added child:" + b1.getName() + " level=" + b1.getLevel());

//      logger.debug("added child:" + b2.getName() + " level=" + b2.getLevel());
//      root.accept(pV);
//      logger.debug("adding child:" + b2.getName() + " level="+ b2.getLevel());
//      root.addChild(b2);
//      logger.debug("adding child:" + b3.getName() + " level="+ b3.getLevel());
//      root.addChild(b3);

//      logger.debug("root=" + root);

//      b1.addChild(c1);
//      b1.addChild(c2);
//      b1.addChild(c3);
//      b1.addChild(c4);
//      b1.addChild(c5);
//      b1.addChild(c6);
//
//      c1.addChild(d1);
//      c1.addChild(d2);
//      c1.addChild(d3);
//      c1.addChild(d4);
//      c1.addChild(d5);
//      c1.addChild(d6);
//
//      d1.addChild(e1);
//      d1.addChild(e2);
//      d1.addChild(e3);

//      Map<Integer, BigDecimal> valueOnLevel = root.getValuesOnLevels();
//      logger.debug("Value on level=" + valueOnLevel.toString());

   }

}
