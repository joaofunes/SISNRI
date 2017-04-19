/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Provincia;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luis
 */
@Repository(value = "provinciaDao")
public class ProvinciaDao extends GenericDao<Provincia, Integer>  {
    
     //retorna la lista de provinciaes perteneciente a una regi�n
    public List<Provincia> getProvinciasByPaisId(Integer id){
        Query q =getSessionFactory().getCurrentSession().createQuery("FROM Provincia p WHERE p.idPais.idPais = :id");
        q.setParameter("id",id);
        List<Provincia> lista= q.list();
        return q.list();        
   }
    
      public List<Provincia> getAllByIdDesc(){
       String query="Select p from Provincia p order by p.idProvincia desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
}
