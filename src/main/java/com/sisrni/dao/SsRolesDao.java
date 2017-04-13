package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.SsRoles;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "ssRolesDao")
public class SsRolesDao extends GenericDao<SsRoles, Integer> {

    public List<SsRoles> findByUrl(String url) {
        List<SsRoles> rolesYOpciones=null;
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o FROM SsRoles o JOIN FETCH o.ssOpcionesList op WHERE op.url LIKE :url ");
        q.setParameter("url", "%" + url + "%");
        rolesYOpciones=q.list();
        return rolesYOpciones;
    }
    
    
    
    public SsRoles findByUser(String user) {
        SsRoles rolesYOpciones=null;
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o FROM SsRoles o JOIN FETCH o.ssMenusList op WHERE op.idRol LIKE :user ");
        q.setParameter("user", "%" + user + "%");        
        rolesYOpciones=(SsRoles) q.uniqueResult();
        return rolesYOpciones;
    }

    public SsRoles getRolByName(String name) {
        try {
            SsRoles rolesYOpciones = null;
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT o FROM SsRoles o  WHERE o.codigoRol =:name ");
            q.setParameter("name",name);
            rolesYOpciones = (SsRoles) q.uniqueResult();
            return rolesYOpciones;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
         public List<SsRoles> getAllByIdDesc(){
       String query="Select r from SsRoles r order by r.idRol desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
}
