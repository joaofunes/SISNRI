package com.sisrni.service;

import com.sisrni.dao.SsUsuariosDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsUsuarios;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ssUsuariosService")
public class SsUsuariosService extends GenericService<SsUsuarios, Integer> {

    @Autowired
    private SsUsuariosDao ssUsuariosDao;

    @Override
    public GenericDao<SsUsuarios, Integer> getDao() {
        return ssUsuariosDao;
    }

    public SsUsuarios findByUser(String user){
       return this.ssUsuariosDao.findByUser(user);
    }
    
     public SsUsuarios findByIdPersona(Integer persona){
       return this.ssUsuariosDao.findByIdPersona(persona);
    }
    
    public SsUsuarios checkLogin(String user, String password) {
        return this.ssUsuariosDao.checkLogin(user, password);
    }
    
    public List<SsUsuarios> getAllUser(){
       return this.ssUsuariosDao.getAllUser();
    }
    
    public int deleteUserRoles(Integer usuario, Integer rol) {
        return this.ssUsuariosDao.deleteUserRoles(usuario, rol);
    }
    
    public void guardarUserRol(Integer usuario, Integer rol){
        ssUsuariosDao.guardarUserRol(usuario, rol);
    }
}
