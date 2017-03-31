/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.PropuestaConvenio;
import com.sisrni.pojo.rpt.PojoConvenioEstado;
import com.sisrni.service.PropuestaConvenioService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */


@Named("consultarEstadoConvenioMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class ConsultarEstadoConvenioMB implements Serializable{
    
    private static final long serialVersionUID = 1113799434508676095L;
    
    private Date fechaActual;
    private List<PojoConvenioEstado> listadoPropuestaConvenio;
    private List<PojoConvenioEstado> listadoConvenio;
    
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    //@PostConstruct
    public void init() {
        try {
           iniciliazar();
        } catch (Exception e) {
        }
    } 

    private void iniciliazar() {
        try {
           listadoPropuestaConvenio=propuestaConvenioService.getPropuestasConvenioWithEstado();
           fechaActual= new Date();
           listadoConvenio=propuestaConvenioService.getConveioWithEstado();
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public List<PojoConvenioEstado> getListadoPropuestaConvenio() {
        return listadoPropuestaConvenio;
    }

    public void setListadoPropuestaConvenio(List<PojoConvenioEstado> listadoPropuestaConvenio) {
        this.listadoPropuestaConvenio = listadoPropuestaConvenio;
    }

    public List<PojoConvenioEstado> getListadoConvenio() {
        return listadoConvenio;
    }

    public void setListadoConvenio(List<PojoConvenioEstado> listadoConvenio) {
        this.listadoConvenio = listadoConvenio;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

   
    
}
