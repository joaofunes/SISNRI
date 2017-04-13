/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.CategoriaNoticiaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.CategoriaNoticia;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "categoriaNoticiaService")
public class CategoriaNoticiaService extends GenericService<CategoriaNoticia, Integer> {

    @Autowired
    private CategoriaNoticiaDao categoriaNoticiaDao;

    @Override
    public GenericDao<CategoriaNoticia, Integer> getDao() {
        return categoriaNoticiaDao;
    }

    public List<String> getCategoriaNoticiaName() {
        return categoriaNoticiaDao.getCategoriaNoticiaName();
    }
    
    
         public List<CategoriaNoticia> getAllByIdDesc(){
        return categoriaNoticiaDao.getAllByIdDesc();
    }
}
