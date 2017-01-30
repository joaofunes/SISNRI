package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsMenus;
import com.sisrni.model.SsOpciones;
import com.sisrni.model.SsRoles;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "ssOpcionesDao")
public class SsOpcionesDao extends GenericDao<SsOpciones, Integer> {

    public List<SsOpciones> getOpcionesByMenuRol(SsMenus menu, SsRoles rol) {
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o  FROM SsOpciones o "
                + "LEFT OUTER JOIN o.ssMenusList m  "
                + "LEFT OUTER JOIN o.ssRolesList r  "
                + "WHERE o.visible='S' AND r.idRol = :rol  AND m.idMenu =:menu  ");
        q.setParameter("rol", rol.getIdRol());
        q.setParameter("menu", menu.getIdMenu());
        return q.list();
    }
    
    public List<SsOpciones> getOpcionesNotMenu() {
        try {
            List<SsOpciones> lst = new ArrayList<SsOpciones>();
            //  Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT * FROM ss_opciones op WHERE op.ID_OPCION NOT IN (SELECT DISTINCT menopc.ID_OPCION FROM ss_menus_opciones menopc)");
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT op FROM SsOpciones op WHERE op.idOpcion NOT IN (SELECT DISTINCT o.idOpcion  FROM SsOpciones o INNER JOIN o.ssMenusList m  )");
            lst = q.list();
            if (lst != null) {
                return lst;
            } else {
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
        
        
   
    public List<SsOpciones> getOpcionestMenu(Integer idMenu) {
        try {
            List<SsOpciones> lst = new ArrayList<SsOpciones>();
            //Query q = getSessionFactory().getCurrentSession().createSQLQuery("SELECT * FROM ss_opciones op WHERE op.ID_OPCION IN (SELECT menopc.ID_OPCION FROM ss_menus_opciones menopc WHERE menopc.ID_MENU =:idMenu)");
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT op FROM SsOpciones op WHERE op.idOpcion IN (SELECT  o.idOpcion  FROM SsOpciones o INNER JOIN o.ssMenusList m WHERE m.idMenu=:idMenu)");
            q.setParameter("idMenu", idMenu);
            lst = q.list();
            if (lst != null) {
                return lst;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
