/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PersonaProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.PersonaProyectoPK;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "personaproyectoService")
public class PersonaProyectoService extends GenericService<PersonaProyecto, Integer> {

    @Autowired
    private PersonaProyectoDao personaproyectoDao;

    @Override
    public GenericDao<PersonaProyecto, Integer> getDao() {
        return personaproyectoDao;
    }

    public int updatePersonaProyecto(int persona, int proyecto, int tipoPersona) {
        return personaproyectoDao.updatePersonaProyecto(persona, proyecto, tipoPersona);
    }

    public Integer getCount(Integer persona) {
        return personaproyectoDao.getCount(persona);
    }

}
