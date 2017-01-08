/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.ProgramaMovilidad;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Luis
 */
public class ProgramaMovilidadLazyModel extends GenericLazyModel<ProgramaMovilidad, Integer> {
    
    public ProgramaMovilidadLazyModel(GenericService<ProgramaMovilidad, Integer> service) {
        super(service);
    }

    @Override
    public ProgramaMovilidad getRowData(String rowKey) {
        for (ProgramaMovilidad programaMovilidad : getDatasource()) {
            if (programaMovilidad.getIdProgramaMovilidad().equals(Integer.valueOf(rowKey)));
            return programaMovilidad;
       }
        return null;
    }

    @Override
    public Integer getRowKey(ProgramaMovilidad element) {
        return element.getIdProgramaMovilidad();
    }
    
}
