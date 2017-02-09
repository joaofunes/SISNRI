/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.EtapaMovilidad;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Luis
 */
public class EtapaMovilidadLazyModel extends GenericLazyModel<EtapaMovilidad, Integer> {
    
    public EtapaMovilidadLazyModel(GenericService<EtapaMovilidad, Integer> service) {
        super(service);
    }

    @Override
    public EtapaMovilidad getRowData(String rowKey) {
        for (EtapaMovilidad etapaMovilidad : getDatasource()) {
            if (etapaMovilidad.getIdEtapa().equals(Integer.valueOf(rowKey)));
            return etapaMovilidad;
       }
        return null;
    }

    @Override
    public Integer getRowKey(EtapaMovilidad element) {
        return element.getIdEtapa();
    }
    
}
