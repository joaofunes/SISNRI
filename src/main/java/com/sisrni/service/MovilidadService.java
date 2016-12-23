package com.sisrni.service;

import com.sisrni.dao.MovilidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service(value = "movilidadService")
public class MovilidadService  extends GenericService<Movilidad, Integer>{
    @Autowired
    private MovilidadDao movilidadDao;
    
    @Override
    public GenericDao<Movilidad, Integer> getDao() {
      return movilidadDao;
    }
    
}
