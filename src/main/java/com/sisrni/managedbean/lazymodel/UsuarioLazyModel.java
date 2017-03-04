/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sisrni.managedbean.lazymodel;

import com.sisrni.managedbean.lazymodel.generic.GenericLazyModel;
import com.sisrni.model.SsUsuarios;
import com.sisrni.service.generic.GenericService;
import java.util.List;

/**
 *
 * @author Josue
 */
public class UsuarioLazyModel extends GenericLazyModel<SsUsuarios, Integer>{

//    public UsuarioLazyModel(GenericService<SsUsuarios, Integer> service) {
//        super(service);
//    }
    public UsuarioLazyModel(List<SsUsuarios> datasource, GenericService<SsUsuarios, Integer> service) {
        super(datasource,service);
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
