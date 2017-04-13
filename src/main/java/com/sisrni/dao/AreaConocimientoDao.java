/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.AreaConocimiento;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lillian
 */
@Repository(value = "areaConocimientoDao")
public class AreaConocimientoDao extends GenericDao<AreaConocimiento, Integer >{
   
    public List<Integer> getAreasConocimientoProyecto(Integer idProy){
        try{
        String sql = "SELECT a.idAreaConocimiento FROM Proyecto p JOIN p.proyectoGenerico pg JOIN pg.areaConocimientoList a  WHERE p.idProyecto =:proy ";
        
        Query q = getSessionFactory().getCurrentSession().createQuery(sql);
        q.setParameter("proy", idProy);
        return q.list();
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    public List<AreaConocimiento> getAllByNameAsc(){
       String query="Select a from AreaConocimiento a order by a.nombreArea asc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
    public List<AreaConocimiento> getAllByIdDesc(){
       String query="Select a from AreaConocimiento a order by a.idAreaConocimiento desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
}
