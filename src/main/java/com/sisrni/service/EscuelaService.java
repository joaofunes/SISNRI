/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.EscuelaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Escuela;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "escuelaService")
public class EscuelaService extends GenericService<Escuela, Integer> {

    @Autowired
    private EscuelaDao escuelaDao;
    
    @Override
    public GenericDao<Escuela, Integer> getDao() {
       return escuelaDao;
    }
    
}
