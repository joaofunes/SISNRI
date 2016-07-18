/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.EstadoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Estado;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "estadoService")
public class EstadoService extends GenericService<Estado, Integer> {

    @Autowired
    private EstadoDao estadoDao;
    
    @Override
    public GenericDao<Estado, Integer> getDao() {
        return estadoDao;
    }
    
}
