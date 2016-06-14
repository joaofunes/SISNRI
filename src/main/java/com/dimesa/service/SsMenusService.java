package com.dimesa.service;

import com.dimesa.dao.SsMenusDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.SsMenus;
import com.dimesa.model.SsRoles;
import com.dimesa.service.generic.GenericService;
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

    public List<SsMenus> getMenusByrol(SsRoles rol) {
        return this.ssMenusDao.getMenusByrol(rol);
    }
}
