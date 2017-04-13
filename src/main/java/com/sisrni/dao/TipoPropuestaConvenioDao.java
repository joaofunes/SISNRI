/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoPropuestaConvenio;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */

@Repository(value = "tipoPropuestaConvenioDao")
public class TipoPropuestaConvenioDao extends GenericDao<TipoPropuestaConvenio, Integer>{
    
    public List<TipoPropuestaConvenio> getAllByIdDesc(){
       String query="Select t from TipoPropuestaConvenio t order by t.idTipoPropuesta desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }
}
