/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PropuestaEstadoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaEstado;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "propuestaEstadoService")
public class PropuestaEstadoService extends GenericService<PropuestaEstado, Integer> {

    @Autowired
    private PropuestaEstadoDao propuestaEstadoDao;

    @Override
    public GenericDao<PropuestaEstado, Integer> getDao() {
        return propuestaEstadoDao;
    }

    public PropuestaEstado getPrpuestaEstadoByID(int id) {
        return propuestaEstadoDao.getPrpuestaEstadoByID(id);
    }

    public int updatePropuestaEstado(int idPropuesta, int idEstado) {
        return propuestaEstadoDao.updatePropuestaEstado(idPropuesta, idEstado);
    }

    public int deletePropuestaEstado(int idPropuesta) {
        return propuestaEstadoDao.deletePropuestaEstado(idPropuesta);
    }

}
