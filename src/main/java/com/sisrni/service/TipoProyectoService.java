/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;


import com.sisrni.dao.TipoProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoProyecto;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "tipoProyectoService")
public class TipoProyectoService extends GenericService<TipoProyecto, Integer> {

    @Autowired
    private TipoProyectoDao tipoProyectoDao;
    @Override
    public GenericDao<TipoProyecto, Integer> getDao() {
     return tipoProyectoDao;
    }
    public List<TipoProyecto> getAllByNameAsc(){
        return tipoProyectoDao.getAllByNameAsc();
    }
    
   public List<TipoProyecto> getAllByIdDesc(){
        return tipoProyectoDao.getAllByIdDesc();
    }
    
}
