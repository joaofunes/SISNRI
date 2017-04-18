/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TipoPropuestaConvenioDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "tipoPropuestaConvenioService")
public class TipoPropuestaConvenioService extends GenericService<TipoPropuestaConvenio, Integer>  {
    
    @Autowired
    private TipoPropuestaConvenioDao tipoPropuestaConvenioDao;
   
    @Override
    public GenericDao<TipoPropuestaConvenio, Integer> getDao() {
      return tipoPropuestaConvenioDao;
    }
    
        public List<TipoPropuestaConvenio> getAllByIdDesc(){
        return tipoPropuestaConvenioDao.getAllByIdDesc();
    }
    
}
