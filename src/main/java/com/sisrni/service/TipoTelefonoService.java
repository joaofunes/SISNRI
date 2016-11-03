/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.TipoTelefonoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoTelefono;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "tipoTelefonoService")
public class TipoTelefonoService extends GenericService<TipoTelefono, Integer> {

    @Autowired
    private TipoTelefonoDao tipoTelefonoDao;
    @Override
    public GenericDao<TipoTelefono, Integer> getDao() {
     return tipoTelefonoDao;
    }
    public TipoTelefono getTipoByDesc (String nombretelefono){
    return tipoTelefonoDao.getTipoByDesc(nombretelefono);
    
    }
}
