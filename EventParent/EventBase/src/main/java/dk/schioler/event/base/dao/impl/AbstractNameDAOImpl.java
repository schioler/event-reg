package dk.schioler.event.base.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.BaseNameDAO;
import dk.schioler.event.base.dao.criteria.AbstractNameCriteria;
import dk.schioler.event.base.dao.table.BaseSQLTableId;
import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.exception.AbstractNameDAOException;

@Service
public abstract class AbstractNameDAOImpl<T extends AbstractEntityName> extends AbstractIdDAOImpl<T> implements BaseNameDAO<T> {

   protected AbstractNameDAOImpl(BaseSQLTableId<T> table) {
      super(table);
   }

   protected boolean isValidInsertObject(T type) throws AbstractNameDAOException {
      boolean retVal = super.isValidInsertObject(type);
   
      // super needs to have evaluated to "true"
      if (retVal) {
         if (StringUtils.isNotEmpty(type.getName())) {
            if (StringUtils.isNotEmpty(type.getShortName())) {
               retVal = true;
            } else {
               throw new AbstractNameDAOException("ShortName can not be null");
            }
         } else {
            throw new AbstractNameDAOException("name can not be empty");
         }

      }

      return retVal;
   }

   @Override
   public List<T> retrieve(AbstractNameCriteria criteria, int maxRows) {
      
      return super.retrieve(criteria, maxRows);
   };
   
 
}
