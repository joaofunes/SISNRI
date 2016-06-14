package com.dimesa.dao;

import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.SsUsuarios;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SsUsuariosDao extends GenericDao<SsUsuarios, Integer> {

    public SsUsuarios findByUser(String user) {
        SsUsuarios ret = null;
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsUsuarios o "
                + " LEFT OUTER JOIN FETCH o.ssRolesSet "
                + " WHERE o.codigoUsuario=:codigoUsuario ");
        q.setParameter("codigoUsuario", user);
        List<SsUsuarios> list = q.list();
        if (list != null && !list.isEmpty()) {
            ret = list.get(0);
        }
        return ret;
    }

    public SsUsuarios checkLogin(String user, String password) {
        SsUsuarios ret = null;
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsUsuarios o "
                + " LEFT OUTER JOIN FETCH o.ssRolesSet "
                + " WHERE o.codigoUsuario=:codigoUsuario "
                + " AND o.clave=:clave ");
        q.setParameter("codigoUsuario", user);
        q.setParameter("clave", password);
        List<SsUsuarios> list = q.list();
        if (list != null && !list.isEmpty()) {
            ret = list.get(0);
        }
        return ret;
    }
}
