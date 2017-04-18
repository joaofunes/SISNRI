/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.OrganismoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Organismo;
import com.sisrni.pojo.rpt.PojoOrganismo;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "organismoService")
public class OrganismoService extends GenericService<Organismo, Integer> {

    @Autowired
    private OrganismoDao organismoDao;

    @Override
    public GenericDao<Organismo, Integer> getDao() {
        return organismoDao;
    }

    public List<Integer> getOrganismosProyecto(Integer id) {
        return organismoDao.getOrganismosProyecto(id);
    }
      public List<PojoOrganismo> getOrganismos() {
        return organismoDao.getOrganismos();
    }
    
    public List<Organismo> getOrganismosPorPaisYTipo(Integer idPais, Integer idTipo) {
        return organismoDao.getOrganismosPorPaisYTipo(idPais, idTipo);
    }
     public List<PojoOrganismo> getOrganismosPorTipoYPais(Integer idTipo, Integer idPais) {
        return organismoDao.getOrganismosPorTipoYPais(idTipo, idPais);
    }
     
      public Organismo getByID(int id) {
        return organismoDao.findById(id);
    }
      public List<Organismo> getAllByNameAsc() {
        return organismoDao.getAllByNameAsc();
    }
      
     public List<Organismo> getAllByIdDesc(){
        return organismoDao.getAllByIdDesc();
    }  
    
}
