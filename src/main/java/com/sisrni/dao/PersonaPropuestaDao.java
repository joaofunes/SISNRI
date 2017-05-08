/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PersonaPropuesta;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */

@Repository(value = "personaPropuestaDao")
public class PersonaPropuestaDao extends GenericDao<PersonaPropuesta, Integer> {
    
    
    public PersonaPropuesta getPersonaPropuestaByPropuestaTipoPersona(int propuesta,String tipoPersona){
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PersonaPropuesta p JOIN FETCH p.propuestaConvenio prop JOIN FETCH p.tipoPersona tipo WHERE tipo.nombreTipoPersona =:tipo AND prop.idPropuesta=:propuesta");
            q.setParameter("propuesta", propuesta);
            q.setParameter("tipo", tipoPersona);
            return (PersonaPropuesta) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();        
        }
     return null;
    }
    
    
    public int updatePersonaPropuesta(int persona,int propuesta,int tipoPersona){
        try {
            
            String sql="UPDATE PERSONA_PROPUESTA SET ID_PERSONA ="+persona+"  WHERE ID_PROPUESTA="+propuesta+" AND ID_TIPO_PERSONA="+tipoPersona;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
    }
    
    public int deletePersonaPropuesta(int persona,int propuesta,int tipoPersona){
        try {
            
            String sql="DELETE FROM PERSONA_PROPUESTA WHERE ID_PERSONA ="+persona+" AND ID_PROPUESTA="+propuesta+" AND ID_TIPO_PERSONA="+tipoPersona;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
    }
    
    
    public int deleteByPersonasPropuestas(int propuesta){
        try {
            
            String sql="DELETE FROM PERSONA_PROPUESTA WHERE ID_PROPUESTA="+propuesta;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            getSessionFactory().getCurrentSession().flush();
            getSessionFactory().getCurrentSession().clear();
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
    }
    
    
        public PersonaPropuesta getPersonaPropuestaById2(int persona){
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PersonaPropuesta p WHERE p.idPersona=:persona");
            q.setParameter("persona", persona);
            return (PersonaPropuesta) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();        
        }
     return null;
    }
        
          public PersonaPropuesta getPersonaPropuestaById(int persona){
        try {
            
            String sql="SELECT * FROM PERSONA_PROPUESTA WHERE ID_PERSONA ="+persona;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
             return (PersonaPropuesta) q.uniqueResult();
        } catch (Exception e) {
          e.printStackTrace();
        }
        return null;
    }
}
