/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.TipoOrganismo;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Joao
 */
public class TipoOrganismoLazyModel extends GenericLazyModel<TipoOrganismo, Integer> {
    
    public TipoOrganismoLazyModel(GenericService<TipoOrganismo, Integer> service) {
        super(service);
    }

    @Override
    public TipoOrganismo getRowData(String rowKey) {
        for (TipoOrganismo tipoOrganismo : getDatasource()) {
            if (tipoOrganismo.getIdTipoOrganismo().equals(Integer.valueOf(rowKey)));
            return tipoOrganismo;
       }
        return null;
    }

    @Override
    public Integer getRowKey(TipoOrganismo element) {
        return element.getIdTipoOrganismo();
    }
    
}
