package test.dk.schioler.ineritance;

import java.time.LocalDateTime;

public interface IBaseId<T extends EntityId > {
   public Integer getId();
   public LocalDateTime getCreated();
   public String verify(T t);
  }
