/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.dao;

import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.Persona;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joao
 */
@Repository(value = "personaDao")
public class PersonaDao extends GenericDao<Persona, Integer> {
    
    /**
     * 
     * @param query
     * @return 
     */
    public List<Persona> getReferenteInternoByName(String query) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo JOIN FETCH a.idUnidad unidad WHERE tipo.nombre='B' AND lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name");
             q.setParameter("name", '%' + query.toLowerCase() + '%');
       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Metodo para relizar busquedas de persona externas en base a LIKE
     * @param query
     * @return 
     */
    public List<Persona> getReferenteExternoByName(String query) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo  JOIN FETCH a.idUnidad unidad WHERE tipo.nombre='C' AND lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name");
             q.setParameter("name", '%' + query.toLowerCase() + '%');       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
