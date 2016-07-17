/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.EmpleadoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Empleado;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HDEZ
 */
@Service(value = "empleadoService")
public class EmpleadoService extends GenericService<Empleado, Integer> {

    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    public GenericDao<Empleado, Integer> getDao() {
        return empleadoDao;
    }

    public List<Empleado> getTecnicosExterno() {
        return empleadoDao.getTecnicosExterno();
    }

    public List<Empleado> getTecnicos() {
        return empleadoDao.getTecnicosExterno();
    }

}
