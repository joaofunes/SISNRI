/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.UnidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "unidadService")
public class UnidadService extends GenericService<EscuelaDepartamento, Integer>{
    
    @Autowired
    private UnidadDao unidadDao;
    
    @Override
    public GenericDao<EscuelaDepartamento, Integer> getDao() {
     return unidadDao;
   }
    public List<EscuelaDepartamento> getUnidadesByFacultadId(Integer id){
    return unidadDao.getUnidadesByFacultadId(id);
    }
    
}
