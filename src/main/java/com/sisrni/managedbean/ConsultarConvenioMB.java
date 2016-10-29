/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.PropuestaConvenio;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.service.PropuestaConvenioService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
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
    
    @Inject
    PropuestaConvenioMB propuestaConvenioMB;
    
    
    @Autowired
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    private List<PojoPropuestaConvenio> listadoPropuestaConvenio;
    private PropuestaConvenio propuestaConvenio;
    private PojoPropuestaConvenio pojoPropuestaConvenio;
    
    
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
            
            listadoPropuestaConvenio= propuestaConvenioService.getAllPropuestaConvenioSQL();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void preEliminar(PojoPropuestaConvenio pojo){
        try {
            pojoPropuestaConvenio = propuestaConvenioService.getAllPropuestaConvenioSQLByID(pojo.getID_PROPUESTA());
            RequestContext context = RequestContext.getCurrentInstance();              
            context.execute("PF('dataChangeDlg').show();");
            //RequestContext.getCurrentInstance().update(":formPrincipal");
        } catch (Exception e) {
         e.printStackTrace();
        }
    }

    
    public void preEditar(){
        try {
//            propuestaConvenioMB.setSolicitante(referenteInterno);            
//            propuestaConvenioMB.setReferenteInterno(referenteInterno);
//            propuestaConvenioMB.setReferenteExterno(referenteExterno);
            
            propuestaConvenioMB.onChangeInterno();
            
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "propuestaCovenio.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    public void eliminarConvenio(){
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public List<PojoPropuestaConvenio> getListadoPropuestaConvenio() {
        return listadoPropuestaConvenio;
    }

    public void setListadoPropuestaConvenio(List<PojoPropuestaConvenio> listadoPropuestaConvenio) {
        this.listadoPropuestaConvenio = listadoPropuestaConvenio;
    }

    public PojoPropuestaConvenio getPojoPropuestaConvenio() {
        return pojoPropuestaConvenio;
    }

    public void setPojoPropuestaConvenio(PojoPropuestaConvenio pojoPropuestaConvenio) {
        this.pojoPropuestaConvenio = pojoPropuestaConvenio;
    }
}
