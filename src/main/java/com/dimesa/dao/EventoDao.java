/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.dao;

import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.Evento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HDEZ
 */
@Repository
public class EventoDao extends GenericDao<Evento, Integer> {

    SimpleDateFormat formStr = new SimpleDateFormat("yyyy-MM-dd");

    public List<Evento> getAllEventos() {
        Query q = getSessionFactory().getCurrentSession().createQuery(
                "SELECT * FROM EVENTO"
        );
        return q.list();
    }

    public List<String> getUnidades() {
        Query q = getSessionFactory().getCurrentSession().createQuery(
                "SELECT Unidad FROM Evento"
        );
        return q.list();
    }

    public List<Evento> getListadoEventoRFD(Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio = 'REPARACION' OR e.servicio = 'FALLA' OR e.servicio = 'DEPRECIACION' BETWEEN e.fechainicio=: fechainicio and e.fechafin=:fechafin"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getListadoExitoso(Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio = 'REPARACION' and  e.fechainicio BETWEEN :fechainicio AND :fechafin"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getListadoPreventivo(Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio = 'PREVENTIVO' and  e.fechainicio BETWEEN :fechainicio AND :fechafin"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getListadoDepreciacion(Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio = 'DEPRECIACION' and e.fechainicio=: fechainicio BETWEEN e.fechafin=:fechafin"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getListadoFallos(Date fechainicio, Date fechafin) {

        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio ='FALLA' AND e.fechainicio BETWEEN :fechainicio and :fechafin ORDER BY e.servicio"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();

        } catch (Exception e) {
            System.out.println("error " + e);
            return null;
        }
    }

    public List<Evento> getListadoFallosReparacion(Date fechainicio, Date fechafin) {

        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio ='FALLA' OR e.servicio ='REPARACION' AND e.fechainicio BETWEEN :fechainicio and :fechafin ORDER BY e.servicio"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();

        } catch (Exception e) {
            System.out.println("error " + e);
            return null;
        }
    }

    public List<Evento> getDepreciaciones(Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.servicio = 'DEPRECIACION' and e.fechainicio=: fechainicio BETWEEN e.fechafin=:fechafin"
            );
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getTiempoVidaUtil(int idA, int idB) {
        Query q = getSessionFactory().getCurrentSession().createQuery(
                "SELECT e FROM Evento e WHERE  e.pladimesa.pladimesa = :idA OR e.pladimesa.pladimesa = :idB"
        );
        q.setParameter("idA", idA);
        q.setParameter("idB", idB);
        return q.list();
    }

    public List<Evento> getComparativoReparaciones(String unidad, int idMaquina, Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.unidad=:unidad  AND e.pladimesa=: id AND e.fechainicio=: fechainicio BETWEEN e.fechafin=:fechafin"
            );
            q.setParameter("unidad", unidad);
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            q.setParameter("id", idMaquina);
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getComparativoReparacionesDos(String unidad, Date fechainicio, Date fechafin) {
        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.unidad=:unidad  AND e.fechainicio BETWEEN :fechainicio AND :fechafin"
            );
            q.setParameter("unidad", unidad);
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Evento> getComparativoReparacionesAllUnidad(Date fechainicio, Date fechafin) {

        try {
            Query q = getSessionFactory().getCurrentSession().createQuery(
                    "SELECT e FROM Evento e WHERE  e.fechainicio BETWEEN :fechainicio AND :fechafin");
            String fecha1 = formStr.format(fechainicio);
            String fecha2 = formStr.format(fechafin);
            q.setParameter("fechainicio", formStr.parse(fecha1));
            q.setParameter("fechafin", formStr.parse(fecha2));
            return q.list();
        } catch (ParseException ex) {
            Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
