/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dimesa.service;

import com.dimesa.dao.EventoDao;
import com.dimesa.dao.generic.GenericDao;
import com.dimesa.model.Evento;
import com.dimesa.service.generic.GenericService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HDEZ
 */
@Service(value = "eventoService")
public class EventoService extends GenericService<Evento, Integer> {

    @Autowired
    private EventoDao eventoDao;

    @Override
    public GenericDao<Evento, Integer> getDao() {
        return eventoDao;
    }

    public List<Evento> getAllEventos() {
        return this.eventoDao.getAllEventos();

    }

    public List<String> getUnidades() {
        return this.eventoDao.getUnidades();
    }

    public List<Evento> getListadoVidaUtil(int idA, int idB) {
        return this.eventoDao.getTiempoVidaUtil(idA, idB);
    }

    public List<Evento> getListadoEventoRFD(Date fechainicio, Date fechafin) {
        return this.eventoDao.getListadoEventoRFD(fechainicio, fechafin);

    }

    public List<Evento> getListadoFallos(Date fechainicio, Date fechafin) {
        return this.eventoDao.getListadoFallos(fechainicio, fechafin);

    }

    public List<Evento> getListadoFallosReparacion(Date fechainicio, Date fechafin) {
        return this.eventoDao.getListadoFallosReparacion(fechainicio, fechafin);
    }

    public List<Evento> getDepreciaciones(Date fechainicio, Date fechafin) {
        return this.eventoDao.getDepreciaciones(fechainicio, fechafin);
    }

    public List<Evento> getComparativoReparaciones(String unidad, int idMaquina, Date fechainicio, Date fechafin) {
        return this.eventoDao.getComparativoReparaciones(unidad, idMaquina, fechainicio, fechafin);
    }

    public List<Evento> getComparativoReparacionesDos(String unidad, Date fechainicio, Date fechafin) {
        return this.eventoDao.getComparativoReparacionesDos(unidad, fechainicio, fechafin);
    }

    public List<Evento> getComparativoReparacionesAllUnidad(Date fechainicio, Date fechafin) {
        return this.eventoDao.getComparativoReparacionesAllUnidad(fechainicio, fechafin);
    }

    public List<Evento> getListadoExitoso(Date fechainicio, Date fechafin) {
        return this.eventoDao.getListadoExitoso(fechainicio, fechafin);
    }

    public List<Evento> getListadoPreventivo(Date fechainicio, Date fechafin) {
        return this.eventoDao.getListadoPreventivo(fechainicio, fechafin);
    }

}
