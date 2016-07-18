/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.PaisDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Pais;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "paisService")
public class PaisService extends GenericService<Pais, Integer>{

    @Autowired
    private PaisDao paisDao;
  
    @Override
    public GenericDao<Pais, Integer> getDao() {
      return paisDao;
    }
    
}
