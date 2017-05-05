/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.service;

import com.sisrni.dao.PropuestaConvenioDao;
import com.sisrni.dao.generic.GenericDao;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.pojo.rpt.PojoConvenioEstado;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.pojo.rpt.RptBitacoraEstadosPojo;
import com.sisrni.pojo.rpt.RptConteoConveniosPorTipoPojo;
import com.sisrni.pojo.rpt.RptConveniosPorAnioPojo;
import com.sisrni.service.generic.GenericService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joao
 */
@Service(value = "propuestaConvenioService")
public class PropuestaConvenioService extends GenericService<PropuestaConvenio, Integer> {

    @Autowired
    private PropuestaConvenioDao propuestaConvenioDao;

    @Override
    public GenericDao<PropuestaConvenio, Integer> getDao() {
        return propuestaConvenioDao;
    }

    public PropuestaConvenio getByID(int id) {
        return propuestaConvenioDao.findById(id);
    }

    public PropuestaConvenio getByIDPropuestaWithPersona(int idPropuesta) {
        return propuestaConvenioDao.getByIDPropuestaWithPersona(idPropuesta);
    }

    public List<PropuestaConvenio> getPropuestaConvenioByTipoPropuesta(TipoPropuestaConvenio tipoPropuestaConvenio) {
        return propuestaConvenioDao.getPropuestaConvenioByTipoPropuesta(tipoPropuestaConvenio);
    }

    public List<PropuestaConvenio> getConvenios() {
        return propuestaConvenioDao.getConvenios();
    }

    public List<PropuestaConvenio> getConveniosMarcos() {
        return propuestaConvenioDao.getConveniosMarcos();
    }

    public List<PojoPropuestaConvenio> getAllPropuestaConvenioSQL() {
        return propuestaConvenioDao.getAllPropuestaConvenioSQL();
    }

    public List<PojoPropuestaConvenio> getAllPropuestaConvenioSQL(int idSolicitante) {
        return propuestaConvenioDao.getAllPropuestaConvenioSQL(idSolicitante);
    }

    public PojoPropuestaConvenio getAllPropuestaConvenioSQLByID(Integer id) {
        return propuestaConvenioDao.getAllPropuestaConvenioSQLByID(id);
    }

    public PropuestaConvenio getPropuestaCovenioByID(int idPropuesta) {
        return propuestaConvenioDao.getPropuestaCovenioByID(idPropuesta);
    }

    public List<PojoConvenioEstado> getConveioWithEstado() {
        return propuestaConvenioDao.getConveioWithEstado();
    }

    public List<PojoConvenioEstado> getPropuestasConvenioWithEstado() {
        return propuestaConvenioDao.getPropuestasConvenioWithEstado();
    }

    public List<PojoPropuestaConvenio> getAllConvenioSQL() {
        return propuestaConvenioDao.getAllConvenioSQL();
    }

    public List<RptConveniosPorAnioPojo> getconveniosPorAnio(Integer desde, Integer hasta) {
        return propuestaConvenioDao.getconveniosPorAnio(desde, hasta);
    }

    public List<PojoConvenioEstado> getPropuestasConvenioWithEstado(Integer desde, Integer hasta) {
        return propuestaConvenioDao.getPropuestasConvenioWithEstado();
    }

    public Integer conteoPropuestasEnRevision() {
        return propuestaConvenioDao.conteoPropuestasEnRevision();
    }

    public List<RptConteoConveniosPorTipoPojo> conteoPropuestaConvenioByTipoPropuesta(Integer desde, Integer hasta) {
        return propuestaConvenioDao.conteoPropuestaConvenioByTipoPropuesta(desde, hasta);
    }

    public List<RptBitacoraEstadosPojo> estadosPropuestaConvenioBitacora(Integer desde, Integer hasta) {
        return propuestaConvenioDao.estadosPropuestaConvenioBitacora(desde, hasta);
    }

    public List<PropuestaConvenio> getConvenioVigentes() {
        return propuestaConvenioDao.getConvenioVigentes();
    }
    
     public int deletePropuestas(int propuesta){
         return propuestaConvenioDao.deletePropuestas(propuesta);
     }
}
