/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Facultad;
import com.sisrni.model.Persona;
import com.sisrni.model.Unidad;
import com.sisrni.service.FacultadService;
import com.sisrni.service.PersonaService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */

@Named("propuestaConvenioMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PropuestaConvenioMB implements Serializable{
    
     private static final long serialVersionUID = 1L;  
     
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    
    private List<Persona> listadoPersonas;
    private List<Facultad> listadoFacultad;
    
    private Persona solicitante;
    private Persona referenteInterno;
    private Facultad facultad;
    
        
    @PostConstruct
    public void init() {
        try {
           solicitante= new Persona();
           solicitante.setCargoPersona("");
           //solicitante.setIdUnidad(new Unidad());
           inicializador();          
        } catch (Exception e) {
        }
    } 
    
     private void inicializador() {
         try {             
             listadoPersonas= personaService.findAll();
             listadoFacultad= facultadService.findAll();
         } catch (Exception e) {
           e.printStackTrace();
         }
    }
     
     
    public void  onChange(){
        System.out.println("com.sisrni.managedbean.PropuestaConvenioMB.onChange()"+solicitante );
        System.out.println("com.sisrni.managedbean.PropuestaConvenioMB.onChange()"+solicitante.getApellidoPersona() );
        System.out.println("com.sisrni.managedbean.PropuestaConvenioMB.onChange()"+solicitante.getTipoPersona() );
    }
    

    
    
    

    public Persona getReferenteInterno() {
        return referenteInterno;
    }

    public void setReferenteInterno(Persona referenteInterno) {
        this.referenteInterno = referenteInterno;
    }

    public List<Persona> getListadoPersonas() {
        return listadoPersonas;
    }

    public void setListadoPersonas(List<Persona> listadoPersonas) {
        this.listadoPersonas = listadoPersonas;
    }

    public List<Facultad> getListadoFacultad() {
        return listadoFacultad;
    }

    public void setListadoFacultad(List<Facultad> listadoFacultad) {
        this.listadoFacultad = listadoFacultad;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Persona solicitante) {
        this.solicitante = solicitante;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

   
}
