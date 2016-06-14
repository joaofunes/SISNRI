/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean.lazymodel;

import com.dimesa.managedbean.lazymodel.generic.GenericLazyModel;
import com.dimesa.model.Equipo;
import com.dimesa.service.generic.GenericService;

/**
 *
 * @author HDEZ
 */
public class ProyeccionEfectividadReparacionTiempoEquipoLazyModel extends GenericLazyModel<Equipo, Integer> {

    public ProyeccionEfectividadReparacionTiempoEquipoLazyModel(GenericService<Equipo, Integer> service) {
        super(service);
    }

    @Override
    public Equipo getRowData(String rowKey) {
        for (Equipo equipo : getDatasource()) {
            if (equipo.getPladimesa().equals(Integer.valueOf(rowKey)));
            return equipo;
        }
        return null;
    }

    @Override
    public Integer getRowKey(Equipo element) {
        return element.getPladimesa();
    }

}
