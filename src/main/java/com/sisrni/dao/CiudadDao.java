/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Ciudad;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Luis
 */
@Repository(value = "ciudadDao")
public class CiudadDao extends GenericDao<Ciudad, Integer>  {
    
     //retorna la lista de ciudades perteneciente a una provincia
    public List<Ciudad> getCiudadesByProvinciaId(Integer id){
        Query q =getSessionFactory().getCurrentSession().createQuery("FROM Ciudad c WHERE c.idProvincia.idProvincia = :id");
        q.setParameter("id",id);
        List<Ciudad> lista= q.list();
        return q.list();        
   }
    
     public List<Ciudad> getAllByIdDesc(){
       String query="Select c from Ciudad c order by c.idCiudad desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
