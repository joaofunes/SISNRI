package com.sisrni.service.generic;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.utils.PagedResult;
import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class GenericService<T, ID extends Serializable> {

    public abstract GenericDao<T, ID> getDao();

    public List<T> findAll() {
        return getDao().findAll();
    }
   

  //  public List findAlll();

    public T findById(ID id) {
        return getDao().findById(id);
    }

    public Serializable save(T entity) {
        return getDao().save(entity);
    }

    public void saveOrUpdate(T entity) {
         getDao().saveOrUpdate(entity);
    }
    
    public T merge(T entity) {
        return getDao().merge(entity);
    }

    public void delete(T entity) {
        getDao().delete(entity);
    }

    public PagedResult getPage(int page, int pageSize, String sortProperty, String sortDirection, Object sample) {
        return getDao().getPage(page, pageSize, sortProperty, sortDirection, sample);
    }

    public PagedResult getPage(int page, int pageSize, String sortProperty, String sortDirection) {
        return getDao().getPage(page, pageSize, sortProperty, sortDirection);
    }

    public PagedResult getPage(int page, int pageSize, String sortProperty, String sortDirection, List criterions) {
        return getDao().getPage(page, pageSize, sortProperty, sortDirection, criterions);
    }
}
