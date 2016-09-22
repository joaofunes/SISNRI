/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Facultad;
import com.sisrni.model.Persona;
import com.sisrni.model.Telefono;
import com.sisrni.model.Unidad;
import com.sisrni.service.FacultadService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.TelefonoService;
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
     
    private static final String FIJO ="FIJO";  
    private static final String FAX ="FAX";  
     
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;

    @Autowired
    @Qualifier(value = "telefonoService")
    private TelefonoService telefonoService;
    
    
    private List<Persona> listadoPersonasSolicitante;
    private List<Persona> listadoPersonasInterno;
    private List<Facultad> listadoFacultad;
    private List<Telefono> listadoTelefonoReferenteInterno;
    
    private Persona solicitante;
    private Persona referenteInterno;
    
    private Facultad facultad;
    
 
    
    private Telefono telFijoInterno;
    private Telefono faxInterno;
    
    private Telefono telFijoExterno;
    private Telefono faxExterno;
            
        
    @PostConstruct
    public void init() {
        try {
           solicitante= new Persona();
           referenteInterno = new Persona();
           telFijoInterno = new Telefono();
           faxInterno = new Telefono();
           telFijoExterno = new Telefono(); 
           faxExterno = new Telefono();       
           inicializador();          
        } catch (Exception e) {
        }
    } 
    
     private void inicializador() {
         try {             
             listadoPersonasSolicitante= personaService.findAll();
             listadoPersonasInterno= personaService.findAll();
             listadoFacultad= facultadService.findAll();             
             
         } catch (Exception e) {
           e.printStackTrace();
         }
    }
     
     
    public void  onChangeSolicitante(){
        try {
             System.out.println("com.sisrni.managedbean.PropuestaConvenioMB.onChange()"+solicitante );
             listadoTelefonoReferenteInterno = telefonoService.getTelefonosByPersona(solicitante);
             
            for( Telefono us: listadoTelefonoReferenteInterno){
               
                if(us.getIdTipoTelefono().getNombre().equals(FIJO)){
                    telFijoInterno= us;
                }
                if(us.getIdTipoTelefono().getNombre().equals(FAX)){
                    faxInterno= us;
                }
                
            }
             
             
        } catch (Exception e) {
        }
    }
    
    
    public void  onChangeInterno(){
        try {
           listadoTelefonoReferenteInterno = telefonoService.getTelefonosByPersona(referenteInterno);
             
            for( Telefono us: listadoTelefonoReferenteInterno){
               
                if(us.getIdTipoTelefono().getNombre().equals(FIJO)){
                    telFijoInterno= us;
                }
                if(us.getIdTipoTelefono().getNombre().equals(FAX)){
                    faxInterno= us;
                }
                
            }
             
        } catch (Exception e) {
        }
    }
    
    public void  onChangeExterno(){
        try {
            listadoTelefonoReferenteInterno = telefonoService.getTelefonosByPersona(solicitante);
             
            for( Telefono us: listadoTelefonoReferenteInterno){
               
                if(us.getIdTipoTelefono().getNombre().equals(FIJO)){
                    telFijoInterno= us;
                }
                if(us.getIdTipoTelefono().getNombre().equals(FAX)){
                    faxInterno= us;
                }
                
            }
             
        } catch (Exception e) {
        }
    }
    

    
    
    

    public Persona getReferenteInterno() {
        return referenteInterno;
    }

    public void setReferenteInterno(Persona referenteInterno) {
        this.referenteInterno = referenteInterno;
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

    public List<Persona> getListadoPersonasSolicitante() {
        return listadoPersonasSolicitante;
    }

    public void setListadoPersonasSolicitante(List<Persona> listadoPersonasSolicitante) {
        this.listadoPersonasSolicitante = listadoPersonasSolicitante;
    }

    public List<Persona> getListadoPersonasInterno() {
        return listadoPersonasInterno;
    }

    public void setListadoPersonasInterno(List<Persona> listadoPersonasInterno) {
        this.listadoPersonasInterno = listadoPersonasInterno;
    }

  
    public List<Telefono> getListadoTelefonoReferenteInterno() {
        return listadoTelefonoReferenteInterno;
    }

    public void setListadoTelefonoReferenteInterno(List<Telefono> listadoTelefonoReferenteInterno) {
        this.listadoTelefonoReferenteInterno = listadoTelefonoReferenteInterno;
    }

    public Telefono getTelFijoInterno() {
        return telFijoInterno;
    }

    public void setTelFijoInterno(Telefono telFijoInterno) {
        this.telFijoInterno = telFijoInterno;
    }

    public Telefono getFaxInterno() {
        return faxInterno;
    }

    public void setFaxInterno(Telefono faxInterno) {
        this.faxInterno = faxInterno;
    }

    public Telefono getTelFijoExterno() {
        return telFijoExterno;
    }

    public void setTelFijoExterno(Telefono telFijoExterno) {
        this.telFijoExterno = telFijoExterno;
    }

    public Telefono getFaxExterno() {
        return faxExterno;
    }

    public void setFaxExterno(Telefono faxExterno) {
        this.faxExterno = faxExterno;
    }

   
}
