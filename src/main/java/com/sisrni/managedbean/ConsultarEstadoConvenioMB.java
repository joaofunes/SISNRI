/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.PropuestaConvenio;
import com.sisrni.service.PropuestaConvenioService;
import java.io.Serializable;
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
    
    private List<PropuestaConvenio> listadoPropuestaConvenio;
    
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    @PostConstruct
    public void init() {
        try {
           iniciliazar();
        } catch (Exception e) {
        }
    } 

    private void iniciliazar() {
        try {
            
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    public List<PropuestaConvenio> getListadoPropuestaConvenio() {
        return listadoPropuestaConvenio;
    }

    public void setListadoPropuestaConvenio(List<PropuestaConvenio> listadoPropuestaConvenio) {
        this.listadoPropuestaConvenio = listadoPropuestaConvenio;
    }
    
}
