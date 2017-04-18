package com.sisrni.service;

import com.sisrni.dao.CategoriaMovilidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.CategoriaMovilidad;
import com.sisrni.service.generic.GenericService;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service(value = "categoriaMovilidadService")
public class CategoriaMovilidadService extends GenericService<CategoriaMovilidad, Integer>{

    @Autowired
    private CategoriaMovilidadDao categoriaMovilidadDao;
    
    @Override
    public GenericDao<CategoriaMovilidad, Integer> getDao() {
        return categoriaMovilidadDao;
    }
    
      public CategoriaMovilidad getByID(int id) {
        return categoriaMovilidadDao.findById(id);
    }
      
      public List<CategoriaMovilidad> getAllCategoriasByNameAsc(){
          return categoriaMovilidadDao.getAllCategoriasByNameAsc();
      }
      
         public List<CategoriaMovilidad> getAllByIdDesc(){
        return categoriaMovilidadDao.getAllByIdDesc();
    }
    
}
