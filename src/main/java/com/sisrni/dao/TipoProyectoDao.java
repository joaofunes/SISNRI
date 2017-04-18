/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoProyecto;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoProyectoDao")
public class TipoProyectoDao extends GenericDao<TipoProyecto, Integer>{
   public List<TipoProyecto> getAllByNameAsc(){
       String query="Select tp from TipoProyecto tp order by tp.nombreTipoProyecto asc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
   
    public List<TipoProyecto> getAllByIdDesc(){
       String query="Select t from TipoProyecto t order by t.idTipoProyecto desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }
   
}
