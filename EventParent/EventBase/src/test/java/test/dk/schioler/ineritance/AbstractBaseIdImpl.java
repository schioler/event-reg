package test.dk.schioler.ineritance;

import java.time.LocalDateTime;

public class AbstractBaseIdImpl<T extends EntityId> implements IBaseId<T> {
   private Integer id;
  
   private LocalDateTime created; 
   
   private T t;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public LocalDateTime getCreated() {
      return created;
   }

   public void setCreated(LocalDateTime created) {
      this.created = created;
   }

   public T getT() {
      return t;
   }

   public void setT(T t) {
      this.t = t;
   }

   @Override
   public String verify(T t) {
      
      return null;
   }
   
   
   
}
