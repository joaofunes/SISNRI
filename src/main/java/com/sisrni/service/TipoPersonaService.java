/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TipoPersonaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoPersona;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "tipoPersonaService")
public class TipoPersonaService extends GenericService<TipoPersona, Integer>  {

    @Autowired
    private TipoPersonaDao tipoPersonaDao;
   
    @Override
    public GenericDao<TipoPersona, Integer> getDao() {
      return tipoPersonaDao;
    }
    
    public TipoPersona getTipoPersonaByNombre(String name){
     return tipoPersonaDao.getTipoPersonaByNombre(name);
    }
    
       public List<TipoPersona> getAllByIdDesc(){
        return tipoPersonaDao.getAllByIdDesc();
    }
}
