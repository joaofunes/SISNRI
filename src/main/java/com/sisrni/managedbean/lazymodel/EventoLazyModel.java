/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.Evento;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author RAUL
 */
public class EventoLazyModel extends  GenericLazyModel<Evento, Integer> {

    public EventoLazyModel(GenericService<Evento, Integer> service) {
        super(service);
    }

    
    @Override
    public Evento getRowData(String rowKey) {
         for (Evento evento : getDatasource()) {
                return evento;
        }
        return null;
        
    }

    @Override
    public Integer getRowKey(Evento element) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
