/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.DocumentoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Documento;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "documentoService")
public class DocumentoService extends GenericService<Documento, Integer>{
    
    @Autowired
    private DocumentoDao documentoDao;

    @Override
    public GenericDao<Documento, Integer> getDao() {
        return documentoDao;
    }
    
    public List<Documento> getDocumentFindCovenio(int propuestaConvenio){
        return documentoDao.getDocumentFindCovenio(propuestaConvenio);
    }
public List<Documento> getDocumentFindBeca(Integer idBeca){
        return documentoDao.getDocumentFindBeca(idBeca);
    }
}
