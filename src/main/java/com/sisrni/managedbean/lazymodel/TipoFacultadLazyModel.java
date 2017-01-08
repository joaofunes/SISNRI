/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.TipoFacultad;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Joao
 */
public class TipoFacultadLazyModel extends GenericLazyModel<TipoFacultad, Integer> {
    
    public TipoFacultadLazyModel(GenericService<TipoFacultad, Integer> service) {
        super(service);
    }

    @Override
    public TipoFacultad getRowData(String rowKey) {
        for (TipoFacultad tipoFacultad : getDatasource()) {
            if (tipoFacultad.getIdTipoFacultad().equals(Integer.valueOf(rowKey)));
            return tipoFacultad;
       }
        return null;
    }

    @Override
    public Integer getRowKey(TipoFacultad element) {
        return element.getIdTipoFacultad();
    }
    
}
