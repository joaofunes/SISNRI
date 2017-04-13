/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.TipoCambio;
import com.sisrni.model.TipoPersona;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "tipoCambioDao")
public class TipoCambioDao extends GenericDao<TipoCambio, Integer>{

    public TipoCambio getTipoCambioByCodigo(String name) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT tipo FROM TipoCambio tipo WHERE tipo.codigoDivisa=:name ");
             q.setParameter("name",name);
             return (TipoCambio) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;         
    }
    
    //Metodo para listar los registros en forma ordenada
    public List<TipoCambio> getAllByNameAsc(){
       String query="Select tc from TipoCambio tc order by tc.nombreDivisa asc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
   }
    
     public List<TipoCambio> getAllByIdDesc(){
       String query="Select t from TipoCambio t order by t.idTipoCambio desc";
       Query q= getSessionFactory().getCurrentSession().createQuery(query);
       return q.list();
     }
    
}
