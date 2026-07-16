package dk.schioler.event.base.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import dk.schioler.event.base.dao.table.EntityBaseTable;
import dk.schioler.event.base.entity.AbstractEntityName;
import dk.schioler.event.base.exception.AbstractNameDAOException;
import dk.schioler.event.base.temp.BaseNameDAO;

@Service
public abstract class AbstractNameDAOImpl<T extends AbstractEntityName> extends EntityBaseDAOImpl<T> implements BaseNameDAO<T> {

   protected AbstractNameDAOImpl(EntityBaseTable<T> table) {
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

//   @Override
//   public List<T> retrieve(AbstractNameCriteria criteria, int maxRows) {
//      
//      return super.retrieve(criteria, maxRows);
//   };
   
 
}
