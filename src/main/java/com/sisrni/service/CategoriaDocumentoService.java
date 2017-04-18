/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.*;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.CategoriaDocumento;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Luis
 */
@Service(value ="categoriaDocumentoService")
public class CategoriaDocumentoService extends GenericService<CategoriaDocumento, Integer>{

    @Autowired
    private CategoriaDocumentoDao categoriaDocumentoDao;
    @Override
    public GenericDao<CategoriaDocumento, Integer> getDao() {
        return categoriaDocumentoDao;
    }
    
   
     public List<Integer> getCategoriasDocumentoProyecto(Integer id){
        return categoriaDocumentoDao.getCategoriasDocumentoProyecto(id);
    }
     
      public List<CategoriaDocumento> getAllByIdDesc(){
        return categoriaDocumentoDao.getAllByIdDesc();
    }
     
    
}
