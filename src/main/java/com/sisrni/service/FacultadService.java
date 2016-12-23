/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.FacultadDao;
import com.sisrni.dao.generic.GenericDao;

import com.sisrni.model.FacultadUnidad;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "facultadService")
public class FacultadService extends GenericService<FacultadUnidad, Integer> {
    
    @Autowired
    private FacultadDao facultadDao;

    @Override
    public GenericDao<FacultadUnidad, Integer> getDao() {
       return facultadDao;
    }
    
    
}
