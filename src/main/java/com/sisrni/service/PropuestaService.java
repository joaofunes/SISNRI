/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PropuestaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Propuesta;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "propuestaService")
public class PropuestaService extends GenericService<Propuesta, Integer>{

    @Autowired
    private PropuestaDao propuestaDao;
    
    @Override
    public GenericDao<Propuesta, Integer> getDao() {
        return propuestaDao;
    }
    
}
