package com.dimesa.service;

import com.dimesa.dao.SsRolesDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.SsRoles;
import com.dimesa.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

}
