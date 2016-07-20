/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Proyecto;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "proyectoService")
public class ProyectoService extends GenericService<Proyecto, Integer> {

    @Autowired
    private ProyectoDao proyectoDao;
    @Override
    public GenericDao<Proyecto, Integer> getDao() {
       return proyectoDao;
    }
    
}