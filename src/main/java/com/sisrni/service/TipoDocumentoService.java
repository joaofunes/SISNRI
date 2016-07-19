/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TipoDocumentoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Convenio;
import com.sisrni.model.TipoDocumento;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "tipoDocumentoService")
public class TipoDocumentoService extends GenericService<TipoDocumento, Integer>{

    @Autowired
    private TipoDocumentoDao documentoDao;
    @Override
    public GenericDao<TipoDocumento, Integer> getDao() {
        return documentoDao;
    }
    
}
