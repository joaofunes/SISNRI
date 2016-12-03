/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.TipoModalidadBecaDao;
import com.sisrni.dao.TipoProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoModalidaBeca;
import com.sisrni.model.TipoProyecto;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "tipoModalidadBecaService")
public class TipoModalidadBecaService extends GenericService<TipoModalidaBeca, Integer> {

    @Autowired
    private TipoModalidadBecaDao tipoModalidadBecaDao;
    @Override
    public GenericDao<TipoModalidaBeca, Integer> getDao() {
     return tipoModalidadBecaDao;
    }
    
}
