/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ParametrosDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Parametros;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */


@Service(value = "parametrosService")
public class ParametrosService extends GenericService<Parametros, Integer>{
     @Autowired
    private ParametrosDao parametrosDao;

    @Override
    public GenericDao<Parametros, Integer> getDao() {
       return parametrosDao;
    }
    
     public Parametros getParametrosMail(){
         return parametrosDao.getParametrosMail();
     }
     
      public void updateParametrosMail(){
           parametrosDao.updateParametrosMail();
      }
}
