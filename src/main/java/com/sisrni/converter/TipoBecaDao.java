/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.converter;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoBeca;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoBecaDao")
public class TipoBecaDao extends GenericDao<TipoBeca, Integer>{
    public List<TipoBeca> getAllByNameAsc(){
       String query="Select tb from TipoBeca tb order by tb.nombreTipoBeca asc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
    public List<TipoBeca> getAllByIdDesc(){
       String query="Select t from TipoBeca t order by t.idTipoBeca desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }
}
