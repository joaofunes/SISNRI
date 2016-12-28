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
     * Metodo para realizar busquedas de solicitantes por nombre
     *
     * @param doc
     * @return
     */
    public List<Persona> getSolicitanteByName(String name) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a   JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name AND a.activo is true ");
            q.setParameter("name", '%' + name.toLowerCase() + '%');
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param query
     * @return
     */
    public List<Persona> getReferenteInternoByName(String query) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name AND a.activo is true");
            q.setParameter("name", '%' + query.toLowerCase() + '%');

            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relizar busquedas de persona externas en base a LIKE
     *
     * @param query
     * @return
     */
    public List<Persona> getReferenteExternoByName(String query) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name AND a.activo is true");
            q.setParameter("name", '%' + query.toLowerCase() + '%');
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas de referente internos por medio de su
     * documento y email
     *
     * @param doc
     * @param persona
     * @return
     */
    public Persona getReferenteInternoByDocEmail(String doc, Persona persona) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a   JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE  a.emailPersona =:email AND a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num AND a.activo is true");
            q.setParameter("num", doc);
            q.setParameter("email", persona.getEmailPersona());
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas de referente externos por medio de su
     * documento y email
     *
     * @param doc
     * @param persona
     * @return
     */
    public Persona getReferenteExternoByDocEmail(String doc, Persona persona) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE  a.emailPersona =:email AND a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num AND a.activo is true");
            q.setParameter("num", doc);
            q.setParameter("email", persona.getEmailPersona());
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas de referente externos por medio de su
     * documento
     *
     * @param doc
     * @param persona
     * @return
     */
    public Persona getReferenteInternoByDocumento(String doc) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE  a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num AND a.activo is true");
            q.setParameter("num", doc);
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas de referente externos por medio de su
     * documento
     *
     * @param doc
     * @param persona
     * @return
     */
    public Persona getReferenteExternoByDoccumento(String doc) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE a.duiPersona =:num OR a.nitPersona =:num OR a.pasaporte =:num AND a.activo is true");
            q.setParameter("num", doc);
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Mentodo para obtener una persona en base a el proyecto al que pertenece y
     * a su tipo de persona
     *
     * @param idProy
     * @param idTipoPer
     * @return
     */
    public Persona getPersonaByProyectoTipoPersona(Integer idProy, Integer idTipoPer) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM Persona p JOIN p.personaProyectoList pp WHERE pp.proyectoGenerico.idProyecto =:proy AND pp.tipoPersona.idTipoPersona =:tipo AND p.activo is true");
            q.setParameter("proy", idProy);
            q.setParameter("tipo", idTipoPer);
            return (Persona) q.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas personasa
     *
     * @param doc
     * @param persona
     * @return
     */
    public Persona getPersonaByID(Integer id) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad JOIN FETCH a.idOrganismo orga WHERE a.idPersona =:id AND a.activo is true");
            q.setParameter("id", id);
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para obtener listados de personass
     *
     * @param extranjero
     * @return
     */
    public List<Persona> getPersonaList(boolean extranjero) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a JOIN FETCH a.idUnidad unidad JOIN FETCH unidad.idFacultad facultad WHERE  a.extranjero =:extranjero AND a.activo is true")
                    .setBoolean("extranjero", extranjero);
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Persona getBecarioByDoc(String doc) {
        String query = "Select b FROM Persona b where b.duiPersona=:doc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        q.setParameter("doc", doc);
        return (Persona) q.uniqueResult();
    }

}
