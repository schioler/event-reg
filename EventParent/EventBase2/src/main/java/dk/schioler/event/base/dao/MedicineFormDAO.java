package dk.schioler.event.base.dao;

import org.springframework.stereotype.Service;

import dk.schioler.event.base.entity.MedicineType;

@Service
public interface MedicineFormDAO<T extends MedicineType> extends EntityBaseDAO<T> {
   

}
