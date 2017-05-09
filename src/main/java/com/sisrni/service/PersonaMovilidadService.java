package com.sisrni.service;

import com.sisrni.dao.PersonaMovilidadDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Movilidad;
import com.sisrni.model.PersonaMovilidad;
import com.sisrni.service.generic.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service(value = "personaMovilidadService")
public class PersonaMovilidadService extends GenericService<PersonaMovilidad, Integer>{
    @Autowired
    private PersonaMovilidadDao personamovilidadDao;

    @Override
    public GenericDao<PersonaMovilidad, Integer> getDao() {
       return personamovilidadDao;
    }
    
    public void eliminarIntemediaPersonaMovilidad(Movilidad mov){
        personamovilidadDao.eliminarIntemediaPersonaMovilidad(mov);
    }
    public Integer getCount(Integer persona) {
        return personamovilidadDao.getCount(persona);
    }
}
