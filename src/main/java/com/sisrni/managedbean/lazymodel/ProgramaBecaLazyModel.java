/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.ProgramaBeca;
import com.sisrni.service.generic.GenericService;

/**
 *
 * @author Luis
 */
public class ProgramaBecaLazyModel extends GenericLazyModel<ProgramaBeca, Integer> {
    
    public ProgramaBecaLazyModel(GenericService<ProgramaBeca, Integer> service) {
        super(service);
    }

    @Override
    public ProgramaBeca getRowData(String rowKey) {
        for (ProgramaBeca programaBeca : getDatasource()) {
            if (programaBeca.getIdPrograma().equals(Integer.valueOf(rowKey)));
            return programaBeca;
       }
        return null;
    }

    @Override
    public Integer getRowKey(ProgramaBeca element) {
        return element.getIdPrograma();
    }
    
}
