/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.UbicacionDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Ubicacion;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "ubicacionService")
public class UbicacionService extends GenericService<Ubicacion, Integer>{
    @Autowired
    private UbicacionDao ubicacionDao;
    
    @Override
    public GenericDao<Ubicacion, Integer> getDao() {
       return ubicacionDao;
    }
    
}
