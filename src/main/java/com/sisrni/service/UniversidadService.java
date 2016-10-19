/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.UniversidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Universidad;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "universidadService")
public class UniversidadService extends GenericService<Universidad, Integer>{
    
    @Autowired
    private UniversidadDao universidadDao;
    @Override
    public GenericDao<Universidad, Integer> getDao() {
       return universidadDao;
    }
    
}
