/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.ConvenioDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Convenio;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "convenioService")
public class ConvenioService extends GenericService<Convenio, Integer>{
   
    @Autowired
    private ConvenioDao convenioDao;

    @Override
    public GenericDao<Convenio, Integer> getDao() {
        return convenioDao;
    }
}
