package com.sisrni.service;

import com.sisrni.dao.ProgramaMovilidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.ProgramaMovilidad;
import com.sisrni.service.generic.GenericService;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "programaMovilidadService")
public class ProgramaMovilidadService extends GenericService<ProgramaMovilidad, Integer> {

    @Autowired
    private ProgramaMovilidadDao programaMovilidadDao;
    
    @Override
    public GenericDao<ProgramaMovilidad, Integer> getDao() {
       return programaMovilidadDao;
    }
    
}
