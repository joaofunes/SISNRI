/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.EquipoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Equipo;
import com.sisrni.service.generic.GenericService;
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
