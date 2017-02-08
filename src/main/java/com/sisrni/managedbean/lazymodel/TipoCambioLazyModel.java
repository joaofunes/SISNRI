/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.TipoCambio;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Luis
 */
public class TipoCambioLazyModel extends GenericLazyModel<TipoCambio, Integer> {
    
    public TipoCambioLazyModel(GenericService<TipoCambio, Integer> service) {
        super(service);
    }

    @Override
    public TipoCambio getRowData(String rowKey) {
        for (TipoCambio tipoCambio : getDatasource()) {
            if (tipoCambio.getIdTipoCambio().equals(Integer.valueOf(rowKey)));
            return tipoCambio;
       }
        return null;
    }

    @Override
    public Integer getRowKey(TipoCambio element) {
        return element.getIdTipoCambio();
    }
    
}
