package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsMenus;
import com.sisrni.model.SsRoles;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SsMenusDao extends GenericDao<SsMenus, Integer> {

    public List<SsMenus> getAllMenus() {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o  FROM SsMenus o "
                    + " order by o.ordenMenu asc");
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public List<SsMenus> getMenusByrol(SsRoles rol) {
        try {
              Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsMenus o "
                + " LEFT OUTER JOIN FETCH o.ssRolesList  ssRoles "
                + " WHERE ssRoles.idRol=:rol "
                + " order by o.ordenMenu asc");
        q.setParameter("rol", rol.getIdRol());
        return q.list();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }
    
    
    
    public int deleteMenuOpciones(Integer menu, Integer opcion) {
        try {
            String sql = "DELETE FROM ss_menus_opciones WHERE ID_MENU=:menu AND ID_OPCION=:opcion";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            q.setParameter("menu", menu);
            q.setParameter("opcion", opcion);
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    public void guardarMenuOpciones(Integer menu, Integer opcion){
        try {
            String sql="INSERT INTO ss_menus_opciones (ID_MENU,ID_OPCION) VALUES ("+menu+","+opcion+")";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);            
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public int deleteMenuRoles(Integer menu, Integer rol) {
        try {
            String sql = "DELETE FROM ss_roles_menu WHERE ID_MENU=:menu AND ID_ROL=:rol";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            q.setParameter("menu", menu);
            q.setParameter("rol", rol);
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    
}
