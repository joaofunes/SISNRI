/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;


import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.Unidad;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.UnidadService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Joao
 */

@Named("personaMB")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PersonaMB implements Serializable{
    
    
    private List<Organismo> listadoOrganismo;
    private List<Unidad> listadoUnidad;
    private List<TipoPersona> listadoTipoPersona;
    
    private Persona persona;
    private TipoPersona tipoPersona;
    
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;
    
    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;
    
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;
    
    @Autowired
    @Qualifier(value = "tipoPersonaService")
    private TipoPersonaService tipoPersonaService;
    
    //declaracion de listas
    @PostConstruct
    public void init() {
        inicializador();
        inicializarListas();
    }

    private void inicializador() {
        try {
            persona = new Persona();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     private void inicializarListas() {
         try {
             listadoOrganismo = organismoService.findAll();
             listadoUnidad = unidadService.findAll();
             listadoTipoPersona = tipoPersonaService.findAll();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }

    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void guardar(){
        try {
            String msg = "Persona Almacenado Exitosamente!";    
           
            personaService.save(persona);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void editar(){
        try {
            String msg = "Persona Editada Exitosamente!";    
            personaService.merge(persona);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void preEditar(Persona persona){
        try {
            String msg = "Persona Editada Exitosamente!";    
          
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
     
    public List<Organismo> getListadoOrganismo() {
        return listadoOrganismo;
    }

    public void setListadoOrganismo(List<Organismo> listadoOrganismo) {
        this.listadoOrganismo = listadoOrganismo;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Unidad> getListadoUnidad() {
        return listadoUnidad;
    }

    public void setListadoUnidad(List<Unidad> listadoUnidad) {
        this.listadoUnidad = listadoUnidad;
    }

    public PersonaService getPersonaService() {
        return personaService;
    }

    public void setPersonaService(PersonaService personaService) {
        this.personaService = personaService;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public List<TipoPersona> getListadoTipoPersona() {
        return listadoTipoPersona;
    }

    public void setListadoTipoPersona(List<TipoPersona> listadoTipoPersona) {
        this.listadoTipoPersona = listadoTipoPersona;
    }

    
    
}
