/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TipoCambioDao;
import com.sisrni.dao.TipoPersonaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoCambio;
import com.sisrni.model.TipoPersona;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "tipoCambioService")
public class TipoCambioService extends GenericService<TipoCambio, Integer>  {

    @Autowired
    private TipoCambioDao tipoCambioDao;
   
    @Override
    public GenericDao<TipoCambio, Integer> getDao() {
      return tipoCambioDao;
    }
    
    public TipoCambio getTipoCambioByCodigo(String name){
     return tipoCambioDao.getTipoCambioByCodigo(name);
    }
    public List<TipoCambio> getAllByNameAsc(){
        return tipoCambioDao.getAllByNameAsc();
    }
    
     public List<TipoCambio> getAllByIdDesc(){
        return tipoCambioDao.getAllByIdDesc();
    }
}
