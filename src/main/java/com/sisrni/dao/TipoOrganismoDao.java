/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;

import com.sisrni.model.TipoOrganismo;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoOrganismoDao")
public class TipoOrganismoDao extends GenericDao<TipoOrganismo, Integer>{
    public List<TipoOrganismo> getAllByNameAsc(){
       String query="Select to from TipoOrganismo to order by to.nombreTipo asc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
     public List<TipoOrganismo> getAllByIdDesc(){
       String query="Select t from TipoOrganismo t order by t.idTipoOrganismo desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }
}
