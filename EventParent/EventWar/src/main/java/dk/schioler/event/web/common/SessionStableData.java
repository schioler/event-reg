package dk.schioler.event.web.common;

import java.util.ArrayList;
import java.util.List;

import dk.schioler.event.base.entity.UNIT;

public class SessionStableData {

   private List<UNIT> units = new ArrayList<UNIT>();

   public List<UNIT> getUnits() {
      return units;
   }

   public void addUnit(UNIT unit) {
      this.units.add(unit);
   }
   
   

}
