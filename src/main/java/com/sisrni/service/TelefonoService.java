/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.TelefonoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */

@Service(value = "telefonoService")
public class TelefonoService extends GenericService<Telefono, Integer>{

    @Autowired
    private TelefonoDao telefonoDao;
              
              
    @Override
    public GenericDao<Telefono, Integer> getDao() {
        return telefonoDao;
    }
    
    
    public List<Telefono> getTelefonosByPersona(Persona persona){
         return telefonoDao.getTelefonosByPersona(persona);
    }
     
    public List<Telefono> getTelefonosByOrganismo(Organismo organismo){
         return telefonoDao.getTelefonosByOrganismo(organismo);
    }
    
    public  String getMask(String idPais){
        return telefonoDao.getMask(idPais);
    }
    
}
