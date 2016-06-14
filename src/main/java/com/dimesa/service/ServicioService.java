/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.service;

import com.dimesa.dao.ServicioDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.Servicio;
import com.dimesa.service.generic.GenericService;
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
