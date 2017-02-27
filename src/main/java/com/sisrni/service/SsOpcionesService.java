package com.sisrni.service;

import com.sisrni.dao.SsOpcionesDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsMenus;
import com.sisrni.model.SsOpciones;
import com.sisrni.model.SsRoles;
import com.sisrni.service.generic.GenericService;
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
    
    public int deleteOpcionesRoles(Integer opcion, Integer rol) {
         return this.ssOpcionesDao.deleteOpcionesRoles(opcion, rol);
    }
    
     public int gurdarRolesOpciones(Integer idRol,Integer idOpcion){
         return this.ssOpcionesDao.gurdarRolesOpciones(idRol, idOpcion);
     }

      public List<SsOpciones> getListadoOpciones(){
          return this.ssOpcionesDao.getListadoOpciones();
      }
}
