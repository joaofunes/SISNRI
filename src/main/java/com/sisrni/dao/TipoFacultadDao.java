/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoFacultad;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoFacultadDao")
public class TipoFacultadDao extends GenericDao<TipoFacultad, Integer>{

    public TipoFacultad getTipoFacultadByNombre(String name) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT tipo FROM TipoFacultad tipo WHERE tipo.nombreTipoFacultad=:name ");
             q.setParameter("name",name);
             return (TipoFacultad) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;         
    }

    
}
