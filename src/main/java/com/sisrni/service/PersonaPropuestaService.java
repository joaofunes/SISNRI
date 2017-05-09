/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PersonaPropuestaDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PersonaPropuesta;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "personaPropuestaService")
public class PersonaPropuestaService extends GenericService<PersonaPropuesta, Integer>{
    
    @Autowired
    private PersonaPropuestaDao personaPropuestaDao;
  
    @Override
    public GenericDao<PersonaPropuesta, Integer> getDao() {
      return personaPropuestaDao;
    }
    
     public PersonaPropuesta getPersonaPropuestaByPropuestaTipoPersona(int propuesta,String tipoPersona){
       return personaPropuestaDao.getPersonaPropuestaByPropuestaTipoPersona(propuesta, tipoPersona);
     }
     
     public int updatePersonaPropuesta(int persona,int propuesta,int tipoPersona){
       return personaPropuestaDao.updatePersonaPropuesta(persona, propuesta, tipoPersona);
     }
     
     public int deletePersonaPropuesta(int persona,int propuesta,int tipoPersona){
       return personaPropuestaDao.deletePersonaPropuesta(persona, propuesta, tipoPersona);
     }
     
     public int deleteByPersonasPropuestas(int propuesta){
       return personaPropuestaDao.deleteByPersonasPropuestas(propuesta);
     }
     
       public Integer getCount(Integer persona) {
        return personaPropuestaDao.getCount(persona);
    }
     
}
