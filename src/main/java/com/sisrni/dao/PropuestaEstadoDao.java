/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaEstado;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "propuestaEstadoDao")
public class PropuestaEstadoDao extends GenericDao<PropuestaEstado, Integer> {
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
    
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
            String sql="UPDATE PROPUESTA_ESTADO SET ID_ESTADO="+idEstado+" ,FECHA = CURDATE()  WHERE ID_PROPUESTA ="+idPropuesta;
            Query q = getSessionFactory().getCurrentSession().createSQLQuery(sql);
            int executeUpdate = q.executeUpdate();
            return executeUpdate;
        } catch (Exception e) {
          e.printStackTrace();
        }
        return 0;
    }
    
     public int deletePropuestaEstado(int propuesta){
        try {
            
            String sql="DELETE FROM PROPUESTA_ESTADO WHERE ID_PROPUESTA="+propuesta;
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
    
}
