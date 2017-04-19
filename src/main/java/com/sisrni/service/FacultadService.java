/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.FacultadDao;
import com.sisrni.dao.generic.GenericDao;

import com.sisrni.model.Facultad;
import com.sisrni.model.Movilidad;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "facultadService")
public class FacultadService extends GenericService<Facultad, Integer> {

    @Autowired
    private FacultadDao facultadDao;

    @Override
    public GenericDao<Facultad, Integer> getDao() {
        return facultadDao;
    }

    public List<Facultad> getFacultadesByUniversidad(Integer idOrganismo) {
        return facultadDao.getFacultadesByUniversidad(idOrganismo);
    }
    
    public List<Integer> getFacultadesMovilidad(Integer idmov){
        return facultadDao.getFacultadesMovilidad(idmov);
    }
    
     public Facultad getFacultadById(Integer idFac){
         return facultadDao.getFacultadById(idFac);
     }
     
     public void eliminarIntermediaMovilidadFacultad(Movilidad mov){
         facultadDao.eliminarIntermediaMovilidadFacultad(mov);
     }
     
      public List<Facultad> getAllByIdDesc(){
        return facultadDao.getAllByIdDesc();
    }

}
