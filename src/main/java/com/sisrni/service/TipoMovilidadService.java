package com.sisrni.service;

import com.sisrni.dao.TipoMovilidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoMovilidad;
import com.sisrni.service.generic.GenericService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service(value = "tipoMovilidadService")
public class TipoMovilidadService extends GenericService<TipoMovilidad, Integer>{
    @Autowired
    private TipoMovilidadDao tipoMovilidadDao;

    @Override
    public GenericDao<TipoMovilidad, Integer> getDao() {
        return tipoMovilidadDao;
    }
    
     public TipoMovilidad getByID(int id) {
        return tipoMovilidadDao.findById(id);
    }
    
}
