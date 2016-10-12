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
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE tipo.nombre='B' AND lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name");
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
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE tipo.nombre='C' AND lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name");
             q.setParameter("name", '%' + query.toLowerCase() + '%');       
             return q.list();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /**
     * Metodo para relaizar busquedas de referente internos por medio de su documento y email
     * @param doc
     * @param persona
     * @return 
     */
    public Persona getReferenteInternoByDocEmail(String doc, Persona persona) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE tipo.nombre='B' AND a.emailPersona =:email AND a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num");
             q.setParameter("num",doc);       
             q.setParameter("email",persona.getEmailPersona());       
             return (Persona) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
     /**
     * Metodo para relaizar busquedas de referente externos por medio de su documento y email
     * @param doc
     * @param persona
     * @return 
     */
    public Persona getReferenteExternoByDocEmail(String doc, Persona persona) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE tipo.nombre='C' AND a.emailPersona =:email AND a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num");
             q.setParameter("num",doc);       
             q.setParameter("email",persona.getEmailPersona());       
             return (Persona) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    /**
     * Metodo para relaizar busquedas de referente externos por medio de su documento
     * @param doc
     * @param persona
     * @return 
     */
    public Persona getReferenteInternoByDocumento(String doc) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE tipo.nombre='B'  AND a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num");
             q.setParameter("num",doc);             
             return (Persona) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Metodo para relaizar busquedas de referente externos por medio de su documento
     * @param doc
     * @param persona
     * @return 
     */
    public Persona getReferenteExternoByDoccumento(String doc) {
        try {
             Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idTipoPersona tipo  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE tipo.nombre='C'  AND a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num");
             q.setParameter("num",doc);            
             return (Persona) q.uniqueResult();
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
