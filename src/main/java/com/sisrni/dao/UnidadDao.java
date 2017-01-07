/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Unidad;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */

@Repository(value = "unidadDao")
public class UnidadDao extends GenericDao<Unidad, Integer>  {
    
    
    //retorna la lista de unidades perteneciente a una facultad --comentado por si lo utilizamos despues
//    public List<EscuelaDepartamento> getUnidadesByFacultadId(Integer id){
//        Query q =getSessionFactory().getCurrentSession().createQuery("FROM EscuelaDepartamento u WHERE u.idFacultadUnidad.idFacultadUnidad = :id");
//        q.setParameter("id",id);
//        List<EscuelaDepartamento> lista= q.list();
//        return q.list();        
//   }
    
   /**
    * Retorna una lista de las unidades que pertenecen a un determinado organismo
    * recibe como parametro el id de un organismo especificado
    */ 
    public List<Unidad> getUnidadesByUniversidad(Integer idUnidad){
       try{
           Query q = getSessionFactory().getCurrentSession().createQuery("SELECT u FROM Unidad u WHERE u.idOrganismo.idOrganismo=:idUnidad");
           q.setParameter("idUnidad", idUnidad);
           return q.list();
       } catch(Exception e){
           e.printStackTrace();
           return null;
}
       
    }
     
    
    
}
