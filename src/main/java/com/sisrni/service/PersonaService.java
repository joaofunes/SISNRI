/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PersonaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Persona;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "personaService")
public class PersonaService extends GenericService<Persona, Integer> {

    @Autowired
    private PersonaDao personaDao;
    
    @Override
    public GenericDao<Persona, Integer> getDao() {
        return personaDao;
    }
    
    public Persona getByID(int id){
        return personaDao.findById(id);
    }
    
    public List<Persona> getReferenteInternoByName(String query){
        return personaDao.getReferenteInternoByName(query);
    }
    
    public Persona getReferenteInternoByDocEmail(String doc, Persona persona){
        return personaDao.getReferenteInternoByDocEmail(doc,persona);
    }
    
    public List<Persona> getReferenteExternoByName(String query){
        return personaDao.getReferenteExternoByName(query);
    }
    
     public Persona getReferenteExternoByDocEmail(String doc, Persona persona){
        return personaDao.getReferenteExternoByDocEmail(doc,persona);
    }
     
     
      public Persona getReferenteInternoByDocumento(String doc){
        return personaDao.getReferenteInternoByDocumento(doc);
    }
      
      public Persona getReferenteExternoByDoccumento(String doc){
        return personaDao.getReferenteExternoByDoccumento(doc);
    }
      
      
      public List<Persona> getSolicitanteByName(String doc){
        return personaDao.getSolicitanteByName(doc);
    }
      
      
      public Persona getPersonaByProyectoTipoPersona(Integer idProy,Integer idTipoPer){
          return personaDao.getPersonaByProyectoTipoPersona(idProy, idTipoPer);
      }
      
      
       public Persona getPersonaByID(Integer id){
          return personaDao.getPersonaByID(id);
       }
      
}
