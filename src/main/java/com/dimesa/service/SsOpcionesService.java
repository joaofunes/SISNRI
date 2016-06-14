package com.dimesa.service;

import com.dimesa.dao.SsOpcionesDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.SsMenus;
import com.dimesa.model.SsOpciones;
import com.dimesa.model.SsRoles;
import com.dimesa.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ssOpcionesService")
public class SsOpcionesService extends GenericService<SsOpciones, Integer> {

    @Autowired
    private SsOpcionesDao ssOpcionesDao;

    @Override
    public GenericDao<SsOpciones, Integer> getDao() {
        return ssOpcionesDao;
    }

    public List<SsOpciones> getOpcionesByMenuRol(SsRoles rol, SsMenus menu) {
        return this.ssOpcionesDao.getOpcionesByMenuRol(menu, rol);
    }

    
    public List<SsOpciones> getOpcionesNotMenu(){
        return this.ssOpcionesDao.getOpcionesNotMenu();
    }
    
    public List<SsOpciones> getOpcionestMenu(Integer idMenu) {
         return this.ssOpcionesDao.getOpcionestMenu(idMenu);
    }

}
