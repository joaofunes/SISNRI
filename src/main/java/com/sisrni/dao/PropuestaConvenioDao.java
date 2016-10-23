/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.TipoPropuestaConvenio;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */

@Repository(value = "propuestaConvenioDao")
public class PropuestaConvenioDao extends GenericDao<PropuestaConvenio, Integer>{
    
    
     public List<PropuestaConvenio> getPropuestaConvenioByTipoPropuesta(TipoPropuestaConvenio tipoPropuestaConvenio) {
          try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM PropuestaConvenio a JOIN FETCH a.idTipoPropuestaConvenio tipo WHERE tipo.nombrePropuestaConvenio =:name");
             q.setParameter("name",tipoPropuestaConvenio.getNombrePropuestaConvenio());       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
