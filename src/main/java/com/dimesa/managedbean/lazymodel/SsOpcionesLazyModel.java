/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimesa.managedbean.lazymodel;

import com.dimesa.managedbean.lazymodel.generic.GenericLazyModel;
import com.dimesa.model.SsOpciones;
import com.dimesa.service.generic.GenericService;

/**
 *
 * @author JosueRogelio
 */
public class SsOpcionesLazyModel extends  GenericLazyModel<SsOpciones, Integer>{

    public SsOpcionesLazyModel(GenericService<SsOpciones, Integer> service) {
        super(service);
    }

    @Override
    public SsOpciones getRowData(String rowKey) {
        for (SsOpciones opciones : getDatasource()) {
            if (opciones.getIdOpcion().equals(Integer.valueOf(rowKey))) {
                return opciones;
            }
        }
        return null;
    }

    @Override
    public Integer getRowKey(SsOpciones element) {
        return element.getIdOpcion();
//        return null;
    }
    
}
