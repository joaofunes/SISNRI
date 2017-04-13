package com.sisrni.service;

import com.sisrni.dao.SsRolesDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsRoles;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "ssRolesService")
public class SsRolesService extends GenericService<SsRoles, Integer> {

    @Autowired
    private SsRolesDao ssRolesDao;

    @Override
    public GenericDao<SsRoles, Integer> getDao() {
        return ssRolesDao;
    }

    public List<SsRoles> findByUrl(String url) {
        return ssRolesDao.findByUrl(url);
    }
    public SsRoles findByUser(String user) {
        return ssRolesDao.findByUser(user);
    }
    
    public SsRoles getRolByName(String name) {
        return ssRolesDao.getRolByName(name);
    }
    
     public List<SsRoles> getAllByIdDesc(){
        return ssRolesDao.getAllByIdDesc();
    }
//    
//       public List<SsRoles> getRolesByUrl(SsRoles rol) {
//        return this.ssRolesDao.getRolesByUrl(rol);
//    }

}
