package com.sisrni.service;

import com.sisrni.dao.SsMenusDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsMenus;
import com.sisrni.model.SsRoles;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ssMenuService")
public class SsMenusService extends GenericService<SsMenus, Integer> {

    @Autowired
    private SsMenusDao ssMenusDao;

    @Override
    public GenericDao<SsMenus, Integer> getDao() {
        return ssMenusDao;
    }

    public List<SsMenus> getAllMenus(){
      return this.ssMenusDao.getAllMenus();
    }
    
    public List<SsMenus> getMenusByrol(SsRoles rol) {
        return this.ssMenusDao.getMenusByrol(rol);
    }
    
     public int deleteMenuOpciones(Integer menu, Integer opcion){
         return this.ssMenusDao.deleteMenuOpciones(menu, opcion);
     }
     
     public void guardarMenuOpciones(Integer menu, Integer opcion){
          this.ssMenusDao.guardarMenuOpciones(menu, opcion);
     }
     
     public int deleteMenuRoles(Integer menu, Integer rol) {
         return  this.ssMenusDao.deleteMenuRoles(menu, rol);
     }
}
