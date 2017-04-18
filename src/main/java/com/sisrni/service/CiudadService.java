/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.CiudadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Ciudad;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "ciudadService")
public class CiudadService extends GenericService<Ciudad, Integer>{

    @Autowired
    private CiudadDao ciudadDao;
  
    @Override
    public GenericDao<Ciudad, Integer> getDao() {
      return ciudadDao;
    }
    
    public List<Ciudad> getCiudadesByProvinciaId(Integer id){
    return ciudadDao.getCiudadesByProvinciaId(id);
    }
   
         public List<Ciudad> getAllByIdDesc(){
        return ciudadDao.getAllByIdDesc();
    }
}
