/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.service;


import com.dimesa.dao.EquipoDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.Equipo;
import com.dimesa.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author HDEZ
 */

@Service(value="equipoService")
public class EquipoService extends GenericService<Equipo, Integer>{

    @Autowired
    private EquipoDao equipoDao;

  

    @Override
    public GenericDao<Equipo, Integer> getDao() {
        return equipoDao;
       
    }

}
