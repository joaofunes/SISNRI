/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.Propuesta;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.Unidad;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.FacultadService;  
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.UnidadService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
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

@Named("propuestaConvenioMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
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
    
    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;
    
    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;
    
    @Autowired
    @Qualifier(value = "tipoPersonaService")
    private TipoPersonaService tipoPersonaService;
    
    
    @Autowired
    @Qualifier(value = "propuestaService")
    private PropuestaService propuestaService;
    
        
    private String numDocumentoInterno;
    private String numDocumentoExterno;
    
    private List<Persona> listadoPersonasSolicitante;
    private List<Persona> listadoPersonasInterno;
    private List<Persona> listadoPersonasExterno;
    
    private List<Organismo> listadoOrganismo;
    private List<Unidad> listadoUnidad;
    private List<TipoPersona> listadoTipoPersona;
    
    private List<Telefono> listadoTelefonoReferenteInterno;
    private List<Telefono> listadoTelefonoReferenteExterno;
    
    
    private Propuesta propuesta;
    
    private Persona personaEdit;
    private Persona solicitante;
    private Persona referenteInterno;
    private Persona referenteExterno;
    
    private Telefono telFijoInterno;
    private Telefono faxInterno;
    
    private Telefono telFijoExterno;
    private Telefono faxExterno;
      
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

        
    @PostConstruct
    public void init() {
        try {
          
           RequestContext.getCurrentInstance().reset(":formAdmin"); 
           inicializador();   
           cargarUsuario();
        } catch (Exception e) {
        }
    } 
    
    /**
     * cargar datos de usuario logeado
     */
    private void cargarUsuario() {
        try {
          usuario.getUsername();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    private void inicializador() {
         try {  
            solicitante= new Persona();
            referenteInterno = new Persona();
            referenteExterno = new Persona();
            telFijoInterno = new Telefono();
            faxInterno = new Telefono();
            telFijoExterno = new Telefono(); 
            faxExterno = new Telefono(); 
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            personaEdit = new Persona();
            propuesta = new Propuesta();
             
             listadoPersonasSolicitante = new ArrayList<Persona>();
             listadoPersonasInterno = new ArrayList<Persona>();
             listadoPersonasExterno = new ArrayList<Persona>();
             
             listadoPersonasSolicitante= personaService.findAll();
             listadoPersonasInterno= personaService.findAll();
             listadoPersonasExterno= personaService.findAll();
             
             //***Editar Persona***
             listadoOrganismo = organismoService.findAll();
             listadoUnidad = unidadService.findAll();
             listadoTipoPersona = tipoPersonaService.findAll();
             
                 
             
         } catch (Exception e) {
           e.printStackTrace();
         }
    }
     
     
    public void  onChangeSolicitante(){
        try {
             
             listadoTelefonoReferenteInterno = telefonoService.getTelefonosByPersona(solicitante);
             
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
            listadoTelefonoReferenteExterno = telefonoService.getTelefonosByPersona(referenteExterno);
             
            for( Telefono us: listadoTelefonoReferenteExterno){
               
                if(us.getIdTipoTelefono().getNombre().equals(FIJO)){
                    telFijoExterno= us;
                }
                if(us.getIdTipoTelefono().getNombre().equals(FAX)){
                    faxExterno= us;
                }
                
            }
             
        } catch (Exception e) {
        }
    }
    
    public List<Persona> completeSolicitante(String query) {
        
        List<Persona> filteredThemes = new ArrayList<Persona>();
        
        if(query!=null && !query.equals("")){
            filteredThemes = personaService.getSolicitanteByName(query);
        }
        return filteredThemes;
    }
    
    public List<Persona> completeSolicitanteInterno(String query) {
        
        List<Persona> filteredThemes = new ArrayList<Persona>();
        
        if(query!=null && !query.equals("")){
            filteredThemes = personaService.getReferenteInternoByName(query);
        }
        return filteredThemes;
    }
    
        
     public List<Persona> completeSolicitanteExterno(String query) {
        
        List<Persona> filteredThemes = new ArrayList<Persona>();
        
        if(query!=null && !query.equals("")){
            filteredThemes = personaService.getReferenteExternoByName(query);
        }
         
        return filteredThemes;
    }
    
     
     
     public void searchByNameSolicitante(){
        try {            
             RequestContext.getCurrentInstance().update(":formAdmin");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     
     
      public void searchByNameInterno(){
        try {
            
            onChangeInterno();
            
            if(referenteInterno.getDuiPersona()!= null){
                 setNumDocumentoInterno(referenteInterno.getDuiPersona());
            }else if(referenteInterno.getNitPersona() != null){
                setNumDocumentoInterno(referenteInterno.getNitPersona());
            }else if(referenteInterno.getPasaporte() != null){
                 setNumDocumentoInterno(referenteInterno.getPasaporte());
            }else{
                setNumDocumentoInterno("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public void searchByDocEmailInterno(){
        try {
            
            if(numDocumentoInterno!=null && referenteInterno!= null && !numDocumentoInterno.equals("") &&  !referenteInterno.getEmailPersona().equals("")){
            referenteInterno = personaService.getReferenteInternoByDocEmail(numDocumentoInterno, referenteInterno);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     
  
     
      public void searchByDocEmailExterno(){
        try {
            
            if(numDocumentoExterno!=null && referenteExterno!= null && !numDocumentoExterno.equals("") &&  !referenteExterno.getEmailPersona().equals("")){
            referenteExterno = personaService.getReferenteExternoByDocEmail(numDocumentoExterno, referenteExterno);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
     
     public void searchByNameExterno(){
        try {
            
            onChangeExterno();
            
            if(referenteExterno.getDuiPersona()!= null){
                 setNumDocumentoExterno(referenteExterno.getDuiPersona());
            }else if(referenteExterno.getNitPersona() != null){
                setNumDocumentoExterno(referenteExterno.getNitPersona());
            }else if(referenteExterno.getPasaporte() != null){
                 setNumDocumentoExterno(referenteExterno.getPasaporte());
            }else{
                setNumDocumentoExterno("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
     
     
      /**
     * Metodo para almacenar una nueva persona
     */ 
    public void preEditarPropuestaInterno(){
        try {
            
            if(numDocumentoInterno!=null && !numDocumentoInterno.equals("")){
             personaEdit = new Persona();
             personaEdit = personaService.getReferenteInternoByDocumento(numDocumentoInterno);
              if(personaEdit != null){  
                RequestContext context = RequestContext.getCurrentInstance();              
                context.execute("PF('EditDialog').show();");
              } else {
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Editado", "Persona no encontrada"));
              } 
            }     
             
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    /**
     * Metodo para almacenar una nueva persona
     */ 
    public void preEditarPropuestaExterno(){
        try {
           
            if(numDocumentoExterno!=null && !numDocumentoExterno.equals("")){
             personaEdit = new Persona();   
             personaEdit  = personaService.getReferenteExternoByDoccumento(numDocumentoExterno);
              if(personaEdit != null){  
                RequestContext context = RequestContext.getCurrentInstance();              
                context.execute("PF('EditDialog').show();");
               } else {
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Editado", "Persona no encontrada"));
              }   
            }     
             
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
            personaService.merge(personaEdit);            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Editado", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    /**
     * Metodo para alamacenar nueva propuesta de convenio
     */
    public void guardarPropuestaConvenio(){
        try {
            propuesta.setIdPersonaSolicitante(solicitante);
            propuesta.setIdPersonaInterno(referenteInterno);
            propuesta.setIdPersonaExterno(referenteExterno);
           // propuesta.setIdConvenio(idConvenio);
           //propuesta.setIdOrganismo(idOrganismo);
           //propuesta.setIdFacultad(idFacultad);
           propuestaService.save(propuesta);
            
            
            
        } catch (Exception e) {
        }
    }
     
     
    public Persona getReferenteInterno() {
        return referenteInterno;
    }

    public void setReferenteInterno(Persona referenteInterno) {
        this.referenteInterno = referenteInterno;
    }

    public Persona getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Persona solicitante) {
        this.solicitante = solicitante;
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

    public List<Persona> getListadoPersonasExterno() {
        return listadoPersonasExterno;
    }

    public void setListadoPersonasExterno(List<Persona> listadoPersonasExterno) {
        this.listadoPersonasExterno = listadoPersonasExterno;
    }

    public List<Telefono> getListadoTelefonoReferenteExterno() {
        return listadoTelefonoReferenteExterno;
    }

    public void setListadoTelefonoReferenteExterno(List<Telefono> listadoTelefonoReferenteExterno) {
        this.listadoTelefonoReferenteExterno = listadoTelefonoReferenteExterno;
    }

    public Persona getReferenteExterno() {
        return referenteExterno;
    }

    public void setReferenteExterno(Persona referenteExterno) {
        this.referenteExterno = referenteExterno;
    }

    public String getNumDocumentoInterno() {
        return numDocumentoInterno;
    }

    public void setNumDocumentoInterno(String numDocumentoInterno) {
        this.numDocumentoInterno = numDocumentoInterno;
    }

    public String getNumDocumentoExterno() {
        return numDocumentoExterno;
    }

    public void setNumDocumentoExterno(String numDocumentoExterno) {
        this.numDocumentoExterno = numDocumentoExterno;
    }

    public AppUserDetails getUsuario() {
        return usuario;
    }

    public void setUsuario(AppUserDetails usuario) {
        this.usuario = usuario;
    }

    public CurrentUserSessionBean getUser() {
        return user;
    }

    public void setUser(CurrentUserSessionBean user) {
        this.user = user;
    }
    
    public Persona getPersonaEdit() {
        return personaEdit;
    }

    public void setPersonaEdit(Persona personaEdit) {
        this.personaEdit = personaEdit;
    }

    public List<Organismo> getListadoOrganismo() {
        return listadoOrganismo;
    }

    public void setListadoOrganismo(List<Organismo> listadoOrganismo) {
        this.listadoOrganismo = listadoOrganismo;
    }

    public List<Unidad> getListadoUnidad() {
        return listadoUnidad;
    }

    public void setListadoUnidad(List<Unidad> listadoUnidad) {
        this.listadoUnidad = listadoUnidad;
    }

    public List<TipoPersona> getListadoTipoPersona() {
        return listadoTipoPersona;
    }

    public void setListadoTipoPersona(List<TipoPersona> listadoTipoPersona) {
        this.listadoTipoPersona = listadoTipoPersona;
    }

    public Propuesta getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(Propuesta propuesta) {
        this.propuesta = propuesta;
    }

   
}
