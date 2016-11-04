/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.PropuestaConvenio;
import com.sisrni.pojo.rpt.PojoPropuestaConvenio;
import com.sisrni.service.PersonaService;
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
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
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

    
    public void preEditar(PojoPropuestaConvenio pj){
        try {
              
              
            propuestaConvenioMB.setSolicitante(personaService.getByID(Integer.parseInt(pj.getID_SOLICITANTE())));            
            propuestaConvenioMB.setReferenteInterno(personaService.getByID(Integer.parseInt(pj.getID_REF_INTERNO())));
            propuestaConvenioMB.setReferenteExterno(personaService.getByID(Integer.parseInt(pj.getID_REF_EXTERNO())));
            
            propuestaConvenioMB.test();
            
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();  
           
            String outcome = "propuestaCovenio.xhtml";
            FacesContext context = FacesContext.getCurrentInstance();
            
            context.getApplication().getNavigationHandler().handleNavigation(context, null, outcome);

            
            //context.redirect(context.getRequestContextPath() + "/views/convenio/propuestaCovenio.xhtml");
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
