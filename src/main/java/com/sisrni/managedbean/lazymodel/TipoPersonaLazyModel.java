/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.TipoPersona;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Luis
 */
public class TipoPersonaLazyModel extends GenericLazyModel<TipoPersona, Integer> {
    
    public TipoPersonaLazyModel(GenericService<TipoPersona, Integer> service) {
        super(service);
    }

    @Override
    public TipoPersona getRowData(String rowKey) {
        for (TipoPersona tipoPersona : getDatasource()) {
            if (tipoPersona.getIdTipoPersona().equals(Integer.valueOf(rowKey)));
            return tipoPersona;
       }
        return null;
    }

    @Override
    public Integer getRowKey(TipoPersona element) {
        return element.getIdTipoPersona();
    }
    
}
