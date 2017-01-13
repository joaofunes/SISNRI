/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.Persona;
import com.sisrni.service.generic.GenericService;
import java.io.Serializable;

/**
 *
 * @author Joao
 */
public class PersonaLazyModal extends GenericLazyModel<Persona, Integer>{

    public PersonaLazyModal(GenericService<Persona, Integer> service) {
        super(service);
    }

    
    
    @Override
    public Persona getRowData(String rowKey) {
       for (Persona opciones : getDatasource()) {
            if (opciones.getIdPersona().equals(Integer.valueOf(rowKey))) {
                return opciones;
            }
        }
        return null;
    }

    @Override
    public Integer getRowKey(Persona element) {
         return element.getIdPersona();
    }
    
}
