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
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE (lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name) AND a.activo is true ");
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
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE (lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name) AND a.activo=1 AND a.extranjero=0 AND a.idCarrera is null");
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
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE (lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name) AND a.activo=1 AND a.extranjero=1");
            q.setParameter("name", '%' + query.toLowerCase() + '%');
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas de referente internos por medio de su
     * email
     *
     * @param persona
     * @return
     */
    public List<Persona> getReferenteInternoByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  lower(a.emailPersona) LIKE :email AND a.activo is true AND a.extranjero is false AND a.idCarrera is null");
            //q.setParameter("email",email);
            q.setParameter("email", '%' + email.toLowerCase() + '%');
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getBecarioByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  lower(a.emailPersona) LIKE :email AND a.activo is true AND a.extranjero is false");
            //q.setParameter("email",email);
            q.setParameter("email", '%' + email.toLowerCase() + '%');
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar busquedas de referente externos por medio de su
     * email
     *
     * @param persona
     * @return
     */
    public List<Persona> getReferenteExternoByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE lower(a.emailPersona) LIKE :email AND a.activo is true AND a.extranjero is true");
            q.setParameter("email", '%' + email.toLowerCase() + '%');
            return q.list();

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
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  a.extranjero =:extranjero AND a.activo is true")
                    .setBoolean("extranjero", extranjero);
            return q.list();

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
    public List<Persona> getPersonaList2(boolean extranjero) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  a.extranjero =:extranjero order by a.idPersona desc")
                    .setBoolean("extranjero", extranjero);
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar por DUI de personas
     *
     * @param dui
     * @param persona
     * @return
     */
    public Persona getPersonaByDui(String dui) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE a.duiPersona =:num  AND a.activo is true");
            q.setParameter("num", dui);
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar por Pasaporte de personas
     *
     * @param pasaporte
     * @param persona
     * @return
     */
    public Persona getPersonaByPasaporte(String pasaporte) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE a.pasaporte =:num  AND a.activo is true");
            q.setParameter("num", pasaporte);
            return (Persona) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para relaizar por DUI de personas
     *
     * @param email
     * @param persona
     * @return
     */
    public Persona getPersonaByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE a.emailPersona =:mail");
            q.setParameter("mail", email);
            return (Persona) q.uniqueResult();

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

    public Persona getRfteFacultadBeneficiadaByDoc(String doc) {
        Query q = getSessionFactory().getCurrentSession().createQuery("SELECT p FROM Persona p WHERE p.duiPersona=:dui AND p.activo is true");
        q.setParameter("dui", doc);
        return (Persona) q.uniqueResult();
    }

    /**
     * Metodo para obtener personas que pertenecen a un organismo especificado
     *
     * @param idOrg
     * @return
     */
    public List<Persona> getPersonasByIdOrganismo(Integer idOrg) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE a.idOrganismo.idOrganismo =:org");
            q.setParameter("org", idOrg);
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getPersonaMovilidadSalienteByName(String query) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE (lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name) AND a.activo is true AND a.extranjero is false");
            q.setParameter("name", '%' + query.toLowerCase() + '%');
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getPersonaMovilidadSalienteByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  lower(a.emailPersona) LIKE :email AND a.activo is true AND a.extranjero is false");
            //q.setParameter("email",email);
            q.setParameter("email", '%' + email.toLowerCase() + '%');
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getPersonaMovilidadReferenteByName(String query) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE (lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name) AND a.activo is true AND a.extranjero is false");
            q.setParameter("name", '%' + query.toLowerCase() + '%');
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getPersonaMovilidadReferenteByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  lower(a.emailPersona) LIKE :email AND a.activo is true AND a.extranjero is false");
            //q.setParameter("email",email);
            q.setParameter("email", '%' + email.toLowerCase() + '%');
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getPersonaMovilidadEntranteByName(String query) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE (lower(a.nombrePersona) LIKE :name OR lower(a.apellidoPersona) LIKE :name) AND a.activo is true AND a.extranjero is true");
            q.setParameter("name", '%' + query.toLowerCase() + '%');
            return q.list();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getPersonaMovilidadEntranteByEmail(String email) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a  WHERE  lower(a.emailPersona) LIKE :email AND a.activo is true AND a.extranjero is true");
            //q.setParameter("email",email);
            q.setParameter("email", '%' + email.toLowerCase() + '%');
            return q.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para realizar busquedas personas que contenga
     * nombre,apellido,email
     *
     * @param name
     * @param lastName
     * @param email
     * @return
     */
    public Persona existePersona(String name, String lastName, String email) {
        try {
            Persona persona;
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE (lower(a.nombrePersona) LIKE :name AND lower(a.apellidoPersona) LIKE :lastName) AND lower(a.emailPersona)=:email");
            q.setParameter("name", '%' + name.toLowerCase() + '%');
            q.setParameter("lastName", '%' + lastName.toLowerCase() + '%');
            q.setParameter("email", email.toLowerCase());

            persona = (Persona) q.uniqueResult();

            if (persona != null) {
                return persona;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para realizar busquedas por email
     *
     * @param email
     * @return
     */
    public Persona existePersonaByMail(String email) {
        try {
            Persona persona;
            Query q = getSessionFactory().getCurrentSession().createQuery("SELECT a FROM Persona a WHERE lower(a.emailPersona)=:email");
            q.setParameter("email", email.toLowerCase());
            persona = (Persona) q.uniqueResult();

            if (persona != null) {
                return persona;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> getAllByIdDesc() {
        String query = "Select p from Persona p order by p.idPersona desc";
        Query q = getSessionFactory().getCurrentSession().createQuery(query);
        return q.list();
    }

}
