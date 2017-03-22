package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsUsuarios;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SsUsuariosDao extends GenericDao<SsUsuarios, Integer> {

    public SsUsuarios findByUser(String user) {
        SsUsuarios ret = null;
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsUsuarios o "
                + " LEFT OUTER JOIN FETCH o.ssRolesList "
                + " WHERE o.codigoUsuario=:codigoUsuario ");
        q.setParameter("codigoUsuario", user);
        List<SsUsuarios> list = q.list();
        if (list != null && !list.isEmpty()) {
            ret = list.get(0);
        }
        return ret;
    }

    public SsUsuarios findByIdPersona(Integer persona) {
        SsUsuarios ret = null;
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsUsuarios o "
                + " LEFT OUTER JOIN FETCH o.ssRolesList "
                + " WHERE o.idPersona=:idPersona ");
        q.setParameter("idPersona", persona);
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
                + " LEFT OUTER JOIN FETCH o.ssRolesList "
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
    
    
    public List<SsUsuarios> getAllUser() {
        SsUsuarios ret = null;
        
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o "
                + " FROM SsUsuarios o ");
        
        List<SsUsuarios> list = q.list();
        getSessionFactory().getCurrentSession().flush();
        getSessionFactory().getCurrentSession().clear();
        if (list != null && !list.isEmpty()) {
            
           return list;
        }
        return null;
    }
    
     public int deleteUserRoles(Integer usuario, Integer rol) {
        try {
            String sql = "DELETE FROM ss_roles_usuarios WHERE ID_USUARIO=:usuario AND ID_ROL=:rol";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            q.setParameter("usuario", usuario);
            q.setParameter("rol", rol);
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
     
    
     public void guardarUserRol(Integer usuario, Integer rol){
        try {
            String sql="INSERT INTO ss_roles_usuarios (ID_USUARIO,ID_ROL) VALUES ("+usuario+","+rol+")";
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);            
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
