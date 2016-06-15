/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ServicioDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Servicio;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HDEZ
 */
@Service(value="servicioService")
public class ServicioService extends GenericService<Servicio, Integer> {

    @Autowired
    private ServicioDao servicioDao;
    
    @Override
    public GenericDao<Servicio, Integer> getDao() {
      return servicioDao;
    }
    
}
