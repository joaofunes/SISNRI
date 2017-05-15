/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TipoDocumentoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoDocumento;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "tipoDocumentoService")
public class TipoDocumentoService extends GenericService<TipoDocumento, Integer> {

    @Autowired
    private TipoDocumentoDao documentoDao;

    @Override
    public GenericDao<TipoDocumento, Integer> getDao() {
        return documentoDao;
    }

    public List<TipoDocumento> getTipoDocumentosByCategory(Integer idCategoria) {
        return documentoDao.getTipoDocumentosByCategory(idCategoria);
    }

    public List<TipoDocumento> getAllByIdDesc() {
        return documentoDao.getAllByIdDesc();
    }

    public TipoDocumento getTipoDocumento(String tipoDocumento) {
        return documentoDao.getTipoDocumento(tipoDocumento);
    }
}
