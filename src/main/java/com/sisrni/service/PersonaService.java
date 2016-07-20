/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PersonaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Persona;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "personaService")
public class PersonaService extends GenericService<Persona, Integer> {

    @Autowired
    private PersonaDao personaDao;
    
    @Override
    public GenericDao<Persona, Integer> getDao() {
        return personaDao;
    }
    
}