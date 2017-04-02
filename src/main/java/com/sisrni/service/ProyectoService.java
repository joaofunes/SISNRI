/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.ProyectoDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.Proyecto;
import com.sisrni.pojo.rpt.PojoMapaInteractivo;
import com.sisrni.pojo.rpt.RptProyectoPojo;
import com.sisrni.pojo.rpt.RptProyectosFinanciadosPojo;
import com.sisrni.pojo.rpt.RptProyectosPorPaisPojo;
import com.sisrni.service.generic.GenericService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "proyectoService")
public class ProyectoService extends GenericService<Proyecto, Integer> {

    @Autowired
    private ProyectoDao proyectoDao;

    @Override
    public GenericDao<Proyecto, Integer> getDao() {
        return proyectoDao;
    }

    public List<PojoMapaInteractivo> getProjectListToCharts(List<String> paisSelected, List<String> tipoProyectoSelected, String desde, String hasta) {
        return proyectoDao.getProjectListToCharts(paisSelected, tipoProyectoSelected, desde, hasta);
    }

    public Proyecto getProyectoByID(Integer idProyecto) {
        return proyectoDao.getProyectoByID(idProyecto);
    }

    public List<Proyecto> getAllProyecto() {
        return proyectoDao.getAllProyecto();
    }

    public List<RptProyectoPojo> getDataProyectosGestionadosReportes() {
        return proyectoDao.getDataProyectosGestionadosReportes();
    }

    public List<Proyecto> getProyectosDesdeHasta(Integer desde, Integer hasta) {
        return proyectoDao.getProyectosDesdeHasta(desde, hasta);
    }

    public List<RptProyectosFinanciadosPojo> getDataProyectosFinanciadosReportes(Integer desde, Integer hasta) {
        return proyectoDao.getDataProyectosFinanciadosReportes(desde, hasta);
    }

    public List<RptProyectosPorPaisPojo> getDataProyectosPorPais(Integer desde, Integer hasta) {
        return proyectoDao.getDataProyectosPorPais(desde, hasta);
    }

    public List<RptProyectosFinanciadosPojo> getDataProyectosTotales(Integer desde, Integer hasta) {
        return proyectoDao.getDataProyectosTotales(desde, hasta);
    }

    public void desvincularInterno(Integer proyectoId, Integer personaId) {
        proyectoDao.desvincularInterno(proyectoId, personaId);
    }

    //metodo que retorna si la persona esta vinculada al proyecto
    public PersonaProyecto isVinculadoPersona(Integer idProyecto, Integer idPersona) {
        return proyectoDao.isVinculadoPersona(idProyecto, idPersona);
    }

    public void eliminarIntermediaPersona(Proyecto proyecto) {
        proyectoDao.eliminarIntermediaPersona(proyecto);
    }

    public void eliminarIntermediaFacultad(Proyecto proyecto) {
        proyectoDao.eliminarIntermediaFacultad(proyecto);
    }

    public void eliminarIntermediaArea(Proyecto proyecto) {
        proyectoDao.eliminarIntermediaArea(proyecto);
    }

    public void eliminarIntermediaOrganismo(Proyecto proyecto) {
        proyectoDao.eliminarIntermediaOrganismo(proyecto);
    }
}
