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
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PersonaPropuesta p JOIN FETCH p.propuestaConvenio prop JOIN FETCH p.tipoPersona tipo WHERE tipo.nombre =:tipo AND prop.idPropuesta=:propuesta");
            q.setParameter("propuesta", propuesta);
            q.setParameter("tipo", tipoPersona);
            return (PersonaPropuesta) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();        
        }
     return null;
    }
    
}
