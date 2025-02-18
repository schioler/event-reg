package dk.schioler.event.base.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class EventTemplate extends AbstractEntityParentChild {

   private UNIT unit;

   private BigDecimal dose;

   private Integer sortOrder;

   private boolean isFavorite;

   public EventTemplate() {

   }

   public EventTemplate(UNIT unit, BigDecimal dose, Integer sortOrder, boolean isFavorite) {
      super();
      this.unit = unit;
      this.dose = dose;
      this.sortOrder = sortOrder;
      this.isFavorite = isFavorite;
   }

   @Override
   public AbstractEntityParentChild instantiateParent() {
      return new EventType();
   }

   @Override
   public void setParent(AbstractEntityParentChild parent) {
      if (parent instanceof EventType) {
         super.setParent(parent);
      } else {
         throw new EventEntityException("EventTemplate can only have EventType as parent");
      }
   }

   @Override
   public void addChild(AbstractEntityParentChild child) {
      if (child instanceof Event) {
         super.addChild(child);
      } else {
         throw new EventEntityException("EventTemplate can only have Event as child");
      }

   }

   public Integer getSortOrder() {
      return sortOrder;
   }

   public void setSortOrder(Integer sortOrder) {
      this.sortOrder = sortOrder;
   }

   public boolean isFavorite() {
      return isFavorite;
   }

   public void setFavorite(boolean isFavorite) {
      this.isFavorite = isFavorite;
   }

   
   public UNIT getUnit() {
      return unit;
   }

   public void setUnit(UNIT unit) {
      this.unit = unit;
   }

   public BigDecimal getDose() {
      return dose;
   }

   public void setDose(BigDecimal dose) {
      this.dose = dose;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append(super.toString());
      builder.append("[ ");
      builder.append(", unit=").append(unit);
      builder.append(", dose=").append(dose);
      builder.append(", sortOrder=").append(sortOrder);
      builder.append(", isFavorite=").append(isFavorite);
      builder.append("]");
      return builder.toString();
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + Objects.hash(dose, isFavorite, sortOrder, unit);
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
      EventTemplate other = (EventTemplate) obj;
      return Objects.equals(dose, other.dose) && isFavorite == other.isFavorite && Objects.equals(sortOrder, other.sortOrder)
            && Objects.equals(unit, other.unit);
   }

}
