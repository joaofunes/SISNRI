/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Parametros;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "parametrosDao")
public class ParametrosDao extends GenericDao<Parametros, Integer> {

    public Parametros getParametrosMail() {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM Parametros p WHERE p.activo is true ");
            return (Parametros) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateParametrosMail() {
        try {
            Query q = getSessionFactory().getCurrentSession().createSQLQuery("UPDATE parametros SET activo=FALSE");
            q.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
