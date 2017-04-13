/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoPersona;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoPersonaDao")
public class TipoPersonaDao extends GenericDao<TipoPersona, Integer>{

    public TipoPersona getTipoPersonaByNombre(String name) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT tipo FROM TipoPersona tipo WHERE tipo.nombreTipoPersona=:name ");
             q.setParameter("name",name);
             return (TipoPersona) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;         
    }

     public List<TipoPersona> getAllByIdDesc(){
       String query="Select t from TipoPersona t order by t.idTipoPersona desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }
}
