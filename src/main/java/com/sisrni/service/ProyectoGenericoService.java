/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ProyectoGenericoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.ProyectoGenerico;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lillian
 */
@Service(value="proyectoGenericoService")
public class ProyectoGenericoService extends GenericService<ProyectoGenerico, Integer>{
    @Autowired
    private ProyectoGenericoDao proyectoGenericoDao;
    @Override
    public GenericDao<ProyectoGenerico, Integer> getDao() {
        return proyectoGenericoDao;
    }
}