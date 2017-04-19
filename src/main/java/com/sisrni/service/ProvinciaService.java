/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.ProvinciaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Provincia;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service(value = "provinciaService")
public class ProvinciaService extends GenericService<Provincia, Integer>{

    @Autowired
    private ProvinciaDao provinciaDao;
  
    @Override
    public GenericDao<Provincia, Integer> getDao() {
      return provinciaDao;
    }
    
    public List<Provincia> getProvinciasByPaisId(Integer id){
    return provinciaDao.getProvinciasByPaisId(id);
    }
    
      public List<Provincia> getAllByIdDesc(){
        return provinciaDao.getAllByIdDesc();
    }
}
