package dk.schioler.event.base.dao.criteria;

import java.math.BigDecimal;
import java.util.Objects;

import dk.schioler.event.base.entity.UNIT;

public abstract class AbstractMeasureCriteria extends AbstractNameCriteria {

   private UNIT unit;

   private BigDecimal doseMin;
   
   private BigDecimal doseMax;
   
   public BigDecimal getDoseMin() {
      return doseMin;
   }

   public void setDoseMin(BigDecimal doseMin) {
      this.doseMin = doseMin;
   }

   public BigDecimal getDoseMax() {
      return doseMax;
   }

   public void setDoseMax(BigDecimal doseMax) {
      this.doseMax = doseMax;
   }

   public UNIT getUnit() {
      return unit;
   }

   public void setUnit(UNIT unit) {
      this.unit = unit;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(doseMax, doseMin, unit);
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
      AbstractMeasureCriteria other = (AbstractMeasureCriteria) obj;
      return Objects.equals(doseMax, other.doseMax) && Objects.equals(doseMin, other.doseMin) && unit == other.unit;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append("AbstractMeasureCriteria [unit=");
      builder.append(unit);
      builder.append(", doseMin=");
      builder.append(doseMin);
      builder.append(", doseMax=");
      builder.append(doseMax);
      builder.append("]");
      return builder.toString();
   }

   
}
