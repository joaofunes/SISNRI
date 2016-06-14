/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dimesa.managedbean.lazymodel;

import com.dimesa.managedbean.lazymodel.generic.GenericLazyModel;
import com.dimesa.model.SsUsuarios;
import com.dimesa.service.generic.GenericService;

/**
 *
 * @author Josue
 */
public class UsuarioLazyModel extends GenericLazyModel<SsUsuarios, Integer>{

    public UsuarioLazyModel(GenericService<SsUsuarios, Integer> service) {
        super(service);
    }

    @Override
    public SsUsuarios getRowData(String rowKey) {
         for (SsUsuarios ssusuarios : getDatasource()) {
            if (ssusuarios.getIdUsuario().equals(Integer.valueOf(rowKey))) {
                return ssusuarios;
            }
        }
        return null;
    }

    @Override
    public Integer getRowKey(SsUsuarios element) {
        return element.getIdUsuario();
    }
    
}
