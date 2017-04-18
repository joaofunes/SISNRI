/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TipoFacultadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoFacultad;
import com.sisrni.model.TipoPersona;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "tipoFacultadService")
public class TipoFacultadService extends GenericService<TipoFacultad, Integer>  {

    @Autowired
    private TipoFacultadDao tipoFacultadDao;
   
    @Override
    public GenericDao<TipoFacultad, Integer> getDao() {
      return tipoFacultadDao;
    }
    
    public TipoFacultad getTipoFacultadByNombre(String name){
     return tipoFacultadDao.getTipoFacultadByNombre(name);
    }
    
       public List<TipoFacultad> getAllByIdDesc(){
        return tipoFacultadDao.getAllByIdDesc();
    }
}
