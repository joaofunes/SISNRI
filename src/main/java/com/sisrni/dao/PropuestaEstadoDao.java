/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaEstado;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "propuestaEstadoDao")
public class PropuestaEstadoDao extends GenericDao<PropuestaEstado, Integer> {
    
    public PropuestaEstado getPrpuestaEstadoByID(int id){
        try {
            Query q= getSessionFactory().getCurrentSession().createQuery("SELECT p FROM PropuestaEstado p WHERE p.propuestaConvenio.idPropuesta=:id");
            q.setParameter("id",id);
            return (PropuestaEstado) q.uniqueResult();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    
    public int updatePropuestaEstado(int idPropuesta,int idEstado){
        try {
             String sql="UPDATE PROPUESTA_ESTADO SET ID_PROPUESTA ="+idPropuesta+" WHERE ID_ESTADO="+idEstado;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
    }
    
}
