package com.dimesa.dao;

import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.SsMenus;
import com.dimesa.model.SsOpciones;
import com.dimesa.model.SsRoles;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "ssOpcionesDao")
public class SsOpcionesDao extends GenericDao<SsOpciones, Integer> {

    public List<SsOpciones> getOpcionesByMenuRol(SsMenus menu, SsRoles rol) {
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o  FROM SsOpciones o "
                + "LEFT OUTER JOIN o.ssMenusSet m  "
                + "LEFT OUTER JOIN o.ssRolesSet r  "
                + "WHERE o.visible='S' AND r.idRol = :rol  AND m.idMenu =:menu  ");
        q.setParameter("rol", rol.getIdRol());
        q.setParameter("menu", menu.getIdMenu());
        return q.list();
    }
    
    public List<SsOpciones> getOpcionesNotMenu() {
        Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT * FROM ss_opciones op WHERE op.ID_OPCION NOT IN \n" +
        "(SELECT DISTINCT menopc.ID_OPCION FROM ss_menus_opciones menopc");
        return q.list();
    }
    public List<SsOpciones> getOpcionestMenu(Integer idMenu) {
        Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT * FROM ss_opciones op WHERE op.ID_OPCION IN (SELECT menopc.ID_OPCION FROM ss_menus_opciones menopc WHERE menopc.ID_MENU =:idMenu)");
        q.setParameter("idMenu",idMenu);
        return q.list();
    }
}
