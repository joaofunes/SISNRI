/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.Estado;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Luis
 */
public class EstadoLazyModel extends GenericLazyModel<Estado, Integer> {
    
    public EstadoLazyModel(GenericService<Estado, Integer> service) {
        super(service);
    }

    @Override
    public Estado getRowData(String rowKey) {
        for (Estado estado : getDatasource()) {
            if (estado.getIdEstado().equals(Integer.valueOf(rowKey)));
            return estado;
       }
        return null;
    }

    @Override
    public Integer getRowKey(Estado element) {
        return element.getIdEstado();
    }
    
}
