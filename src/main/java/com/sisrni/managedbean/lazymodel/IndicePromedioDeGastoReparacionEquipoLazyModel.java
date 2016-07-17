/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.Equipo;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author HDEZ
 */
public class IndicePromedioDeGastoReparacionEquipoLazyModel extends GenericLazyModel<Equipo, Integer> {

    public IndicePromedioDeGastoReparacionEquipoLazyModel(GenericService<Equipo, Integer> service) {
        super(service);
    }

    @Override
    public Equipo getRowData(String rowKey) {
        for (Equipo equipo : getDatasource()) {
            if (equipo.getPlasisrni().equals(Integer.valueOf(rowKey)));
            return equipo;
        }
        return null;
    }

    @Override
    public Integer getRowKey(Equipo element) {
        return element.getPlasisrni();
    }

}
