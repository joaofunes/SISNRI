/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.service;

import com.dimesa.dao.EmpleadoDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.Empleado;
import com.dimesa.service.generic.GenericService;
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
