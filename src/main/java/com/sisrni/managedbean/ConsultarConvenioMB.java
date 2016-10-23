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

@Named("consultarConvenioMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ConsultarConvenioMB implements Serializable{
    
    private static final long serialVersionUID = 1L;  
    
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    private List<PropuestaConvenio> listadoPropuestaConvenio;
    private PropuestaConvenio propuestaConvenio;
    
    
    @PostConstruct
    public void init() {
        try {
          
           
           inicializador();   
           
        } catch (Exception e) {
        }
    } 

    private void inicializador() {
        try {
            propuestaConvenio = new PropuestaConvenio();
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

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }
}
