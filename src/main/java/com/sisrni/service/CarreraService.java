/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.CarreraDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Carrera;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cortez
 */
@Service(value="carreraService")
public class CarreraService extends GenericService<Carrera, Integer>{
    
    @Autowired
    CarreraDao carreraDao;

    @Override
    public GenericDao<Carrera, Integer> getDao() {
       return carreraDao;
    }
    
    public List<Carrera> getCarrerasByFacultad(int idFacultad){
    return carreraDao.getCarrerasByFacultad(idFacultad);
    }
    
    public List<Carrera> getAllByIdDesc(){
        return carreraDao.getAllByIdDesc();
    }
}
