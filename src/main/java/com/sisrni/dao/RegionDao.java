/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Region;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "regionDao")
public class RegionDao extends GenericDao<Region, Integer> {
    
     public List<Region> getAllByIdDesc(){
       String query="Select r from Region r order by r.idRegion desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
