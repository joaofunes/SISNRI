package com.dimesa.dao;

import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.SsMenus;
import com.dimesa.model.SsRoles;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SsMenusDao extends GenericDao<SsMenus, Integer> {

    public List<SsMenus> getMenusByrol(SsRoles rol) {
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsMenus o "
                + " LEFT OUTER JOIN FETCH o.ssRolesSet  ssRoles "
                + " WHERE ssRoles.idRol=:rol ");
        q.setParameter("rol", rol.getIdRol());
        return q.list();
    }
}
