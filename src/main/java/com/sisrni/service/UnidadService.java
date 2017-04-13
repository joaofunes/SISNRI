/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.UnidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Movilidad;
import com.sisrni.model.Unidad;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "unidadService")
public class UnidadService extends GenericService<Unidad, Integer>{
    
    @Autowired
    private UnidadDao unidadDao;
    
    @Override
    public GenericDao<Unidad, Integer> getDao() {
     return unidadDao;
   }
    
    //lo comente por si lo utilizamos despues aunque las unidades ya no estan por facultad
//    public List<EscuelaDepartamento> getUnidadesByFacultadId(Integer id) {
//        return unidadDao.getUnidadesByFacultadId(id);
//    }
    
    
    public List<Unidad> getUnidadesByUniversidad(Integer idUnidad){
        return unidadDao.getUnidadesByUniversidad(idUnidad);
    }
    
    public List<Integer> getUnidadesMovilidad(Integer idMov){
        return unidadDao.getUnidadesMovilidad(idMov);
    }
    
    public Unidad getUnidadById(Integer idUni){
        return unidadDao.getUnidadById(idUni);
    }
    
    public void eliminarIntermediaMovilidadUnidad(Movilidad mov){
        unidadDao.eliminarIntermediaMovilidadUnidad(mov);
    }
    
    public List<Unidad> getAllByIdDesc(){
        return unidadDao.getAllByIdDesc();
    }
}
