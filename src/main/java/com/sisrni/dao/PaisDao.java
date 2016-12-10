/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Pais;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "paisDao")
public class PaisDao extends GenericDao<Pais, Integer>  {
    
     //retorna la lista de paises perteneciente a una región
    public List<Pais> getPaisesByRegionId(Integer id){
        Query q =getSessionFactory().getCurrentSession().createQuery("FROM Pais p WHERE p.idRegion.idRegion = :id");
        q.setParameter("id",id);
        List<Pais> lista= q.list();
        return q.list();        
   }
    
}
