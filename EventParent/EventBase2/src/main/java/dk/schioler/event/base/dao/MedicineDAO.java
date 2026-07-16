package dk.schioler.event.base.dao;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.Medicine;

@Service
public interface MedicineDAO<T extends Medicine> extends EntityBaseDAO<T> {
//   public String getMedicineFirmId();
//   public String getMedicineType();
//   public String getName();
//   public String getUnit();
//   public String getDose();

}
