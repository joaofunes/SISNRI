package com.sisrni.service;

import com.sisrni.dao.EtapaMovilidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EtapaMovilidad;
import com.sisrni.service.generic.GenericService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service(value = "etapamovilidadService")
public class EtapaMovilidadService extends GenericService<EtapaMovilidad, Integer>{
    
     @Autowired
    private EtapaMovilidadDao etapaMovilidadDao;

    @Override
    public GenericDao<EtapaMovilidad, Integer> getDao() {
        return etapaMovilidadDao;
    }
    
    
}