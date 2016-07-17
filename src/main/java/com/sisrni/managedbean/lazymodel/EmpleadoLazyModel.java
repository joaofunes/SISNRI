/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.Empleado;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author HDEZ
 */
public class EmpleadoLazyModel  extends GenericLazyModel<Empleado, Integer> {

    public EmpleadoLazyModel(GenericService<Empleado, Integer> service) {
        super(service);
    }

    @Override
    public Empleado getRowData(String rowKey) {
        for (Empleado empleado : getDatasource()) {
            if (empleado.getIdEmpleado().equals(Integer.valueOf(rowKey)));
            return empleado;
        }
        return null;
    }

    @Override
    public Integer getRowKey(Empleado element) {
         return element.getIdEmpleado();
    }
    
}
