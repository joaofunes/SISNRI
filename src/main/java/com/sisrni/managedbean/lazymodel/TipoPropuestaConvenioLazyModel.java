/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Joao
 */
public class TipoPropuestaConvenioLazyModel extends GenericLazyModel<TipoPropuestaConvenio, Integer> {
    
    public TipoPropuestaConvenioLazyModel(GenericService<TipoPropuestaConvenio, Integer> service) {
        super(service);
    }

    @Override
    public TipoPropuestaConvenio getRowData(String rowKey) {
        for (TipoPropuestaConvenio tipoPropuestaConvenio : getDatasource()) {
            if (tipoPropuestaConvenio.getIdTipoPropuesta().equals(Integer.valueOf(rowKey)));
            return tipoPropuestaConvenio;
       }
        return null;
    }

    @Override
    public Integer getRowKey(TipoPropuestaConvenio element) {
        return element.getIdTipoPropuesta();
    }
    
}
