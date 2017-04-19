/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Estado;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "estadoDao")
public class EstadoDao extends GenericDao<Estado, Integer>{
    
    /**
     * Metodo que devuelve el estado a settear a convenio o proyecto
     * @param estado
     * @return 
     */
    public Estado getEstadoByName(String estado){
        try {
            Query q= getSessionFactory().getCurrentSession().createQuery("SELECT e FROM Estado e WHERE e.nombreEstado=:estado");
            q.setParameter("estado",estado);            
            return (Estado) q.uniqueResult();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
        
    /**
     * Metodo que devuelve los estados para propuestas de Convenio
     * 
     * @return 
     */
    public List<Estado> getEstadoPropuestasConvenio(){
        try {
            Query q= getSessionFactory().getCurrentSession().createQuery("SELECT e FROM Estado e WHERE e.tipoEstado=1");                    
            return q.list();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Estado> getAllByIdDesc(){
       String query="Select e from Estado e order by e.idEstado desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
}
