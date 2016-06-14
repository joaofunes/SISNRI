/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.managedbean.lazymodel;

import com.dimesa.managedbean.lazymodel.generic.GenericLazyModel;
import com.dimesa.model.Evento;
import com.dimesa.service.generic.GenericService;

/**
 *
 * @author Joao
 */
public class IndicePromedioDeGastoReparacionEventoLazyModel extends GenericLazyModel<Evento, Integer> {

    public IndicePromedioDeGastoReparacionEventoLazyModel(GenericService<Evento, Integer> service) {
        super(service);
    }

    @Override
    public Evento getRowData(String rowKey) {
     for (Evento evento : getDatasource()) {
            if (evento.getNumDimesa().equals(Integer.valueOf(rowKey)));
            return evento;
        }
        return null;   }

    @Override
    public Integer getRowKey(Evento element) {
      return element.getNumDimesa();
    }
    
}
