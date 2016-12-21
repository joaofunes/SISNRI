/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.FacultadProyectoDao;
import com.sisrni.dao.PersonaProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.FacultadProyecto;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "facultadproyectoService")
public class FacultadProyectoService extends GenericService<FacultadProyecto, Integer> {

    @Autowired
    private FacultadProyectoDao facultadproyectoDao;
    @Override
    public GenericDao<FacultadProyecto, Integer> getDao() {
       return facultadproyectoDao;
    }
    
    public int updateFacultadProyecto(int facultad, int proyecto,int tipoFacultad){
        return facultadproyectoDao.updateFacultadProyecto(facultad, proyecto, tipoFacultad);
    }
    
}
