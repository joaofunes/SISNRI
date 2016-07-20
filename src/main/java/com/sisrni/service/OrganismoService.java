/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.OrganismoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Organismo;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "organismoService")
public class OrganismoService extends GenericService<Organismo, Integer> {
    
    @Autowired
    private OrganismoDao organismoDao;
    
    @Override
    public GenericDao<Organismo, Integer> getDao() {
        return organismoDao;
    }
    
}