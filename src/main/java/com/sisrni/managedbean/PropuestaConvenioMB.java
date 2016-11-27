/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.Estado;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaPropuesta;
import com.sisrni.model.PersonaPropuestaPK;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.Unidad;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.PropuestaEstado;
import com.sisrni.model.PropuestaEstadoPK;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.EstadoService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaPropuestaService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoPropuestaConvenioService;
import com.sisrni.service.UnidadService;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
    private static final String SOLICITANTE ="SOLICITANTE";  
    private static final String REFERENTE_INTERNO ="REFERENTE INTERNO";  
    private static final String REFERENTE_EXTERNO ="REFERENTE EXTERNO";  
    private static final String CONVENIO_MARCO ="CONVENIO MARCO";  
    private static final String ESTADO ="REVISION";  
     
    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;

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
    @Qualifier(value = "propuestaConvenioService")
    private PropuestaConvenioService propuestaConvenioService;
    
    @Autowired
    @Qualifier(value = "personaPropuestaService")
    private PersonaPropuestaService personaPropuestaService;
    
    @Autowired
    @Qualifier(value = "tipoPropuestaConvenioService")
    private TipoPropuestaConvenioService tipoPropuestaConvenioService;
    
    @Autowired
    @Qualifier(value = "propuestaEstadoService")
    private PropuestaEstadoService propuestaEstadoService;
  
    @Autowired
    @Qualifier(value = "estadoService")
    private EstadoService estadoService;
    
    
    
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
    
    private List<TipoPropuestaConvenio> listadoTipoPrpouestaConvenio;
    private List<PropuestaConvenio> listadoPropuestaConvenio;
    
    private PropuestaConvenio propuestaConvenio;
    private PropuestaConvenio propuestaConvenioTemp;
    
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

    private boolean flagConvenioMarco = false;
    private boolean flagEdicion = false;
    
    
 
    
    
    
    @PostConstruct
    public void init() {
        try {          
          // RequestContext.getCurrentInstance().reset(":formAdmin"); 
           inicializador();
           inicializadorListados();
           cargarUsuario();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    public void postInit(){
        try {            
            inicializadorListados();
            cargarUsuario();
            searchByNameInterno();
            searchByNameExterno();           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * cargar datos de usuario logeado
     */
    private void cargarUsuario() {
        try {
         
          solicitante=personaService.findById( usuario.getUsuario().getIdPersona());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void inicializador() {
         try {  
            solicitante= new Persona();
            solicitante.setIdUnidad(new Unidad());
            solicitante.getIdUnidad().setIdFacultad(new Facultad());
            referenteInterno = new Persona();
            referenteExterno = new Persona();
            telFijoInterno = new Telefono();
            faxInterno = new Telefono();
            telFijoExterno = new Telefono(); 
            faxExterno = new Telefono(); 
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            personaEdit = new Persona();
            propuestaConvenio = new PropuestaConvenio();             
         } catch (Exception e) {
           e.printStackTrace();
         }
    }
    private void inicializadorListados() {
         try {  
             
             listadoPersonasSolicitante = new ArrayList<Persona>();
             listadoPersonasInterno = new ArrayList<Persona>();
             listadoPersonasExterno = new ArrayList<Persona>();
             listadoTipoPrpouestaConvenio = new ArrayList<TipoPropuestaConvenio>();
             listadoPropuestaConvenio = new ArrayList<PropuestaConvenio>();
             
             listadoPersonasSolicitante= personaService.findAll();
             listadoPersonasInterno= personaService.findAll();
             listadoPersonasExterno= personaService.findAll();
             listadoTipoPrpouestaConvenio = tipoPropuestaConvenioService.findAll();
             
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
            if(referenteInterno != null){
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
           }
        } catch (Exception e) {
            e.printStackTrace();
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
            if(referenteExterno != null){
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
         }
        } catch (Exception e) {
            e.printStackTrace();
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
            PersonaPropuesta prsSolicitante = new PersonaPropuesta();
            PersonaPropuesta prsRefInterno  = new PersonaPropuesta();
            PersonaPropuesta prsRefExterno  = new PersonaPropuesta();
            PersonaPropuestaPK personaPropuestaPK = new PersonaPropuestaPK();     
            
            // guardar propuesta convenio
  
            propuestaConvenioService.save(propuestaConvenio);
            
            PropuestaEstado estado = new PropuestaEstado();
            PropuestaEstadoPK estadoPK= new PropuestaEstadoPK();
            Estado stado = estadoService.getEstadoByName(ESTADO);
            
            
            estadoPK.setIdEstado(stado.getIdEstado());
            estadoPK.setIdPropuesta(propuestaConvenio.getIdPropuesta());
            
            estado.setFecha(new Date());
            estado.setPropuestaConvenio(propuestaConvenio);
            estado.setEstado(stado);
            estado.setPropuestaEstadoPK(estadoPK);
            
            propuestaEstadoService.save(estado);
            
            // persona solicitante
           
            prsSolicitante.setPropuestaConvenio(propuestaConvenio);
                              
            prsSolicitante.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(SOLICITANTE));            
            prsSolicitante.setPersona(solicitante);            
            personaPropuestaPK = new PersonaPropuestaPK();            
            personaPropuestaPK.setIdPersona(solicitante.getIdPersona());
            personaPropuestaPK.setIdPropuesta(propuestaConvenio.getIdPropuesta());
            personaPropuestaPK.setIdTipoPersona(prsSolicitante.getTipoPersona().getIdTipoPersona());            
            prsSolicitante.setPersonaPropuestaPK(personaPropuestaPK);             
            personaPropuestaService.save(prsSolicitante);
            
             // persona REFERENTE_INTERNO

            prsRefInterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_INTERNO));        
            prsRefInterno.setPersona(referenteInterno);
            personaPropuestaPK = new PersonaPropuestaPK();            
            personaPropuestaPK.setIdPersona(referenteInterno.getIdPersona());
            personaPropuestaPK.setIdPropuesta(propuestaConvenio.getIdPropuesta());
            personaPropuestaPK.setIdTipoPersona(prsRefInterno.getTipoPersona().getIdTipoPersona());            
            prsRefInterno.setPersonaPropuestaPK(personaPropuestaPK);       
            personaPropuestaService.save(prsRefInterno);
            
               // persona REFERENTE_EXTERNO

            prsRefExterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_EXTERNO));        
            prsRefExterno.setPersona(referenteExterno);
            personaPropuestaPK = new PersonaPropuestaPK();            
            personaPropuestaPK.setIdPersona(referenteExterno.getIdPersona());
            personaPropuestaPK.setIdPropuesta(propuestaConvenio.getIdPropuesta());
            personaPropuestaPK.setIdTipoPersona(prsRefExterno.getTipoPersona().getIdTipoPersona());            
            prsRefExterno.setPersonaPropuestaPK(personaPropuestaPK);    
            personaPropuestaService.save(prsRefExterno);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", "Propuesta Convenio almacenada"));
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    
    
      /**
     * Metodo para actualizar propuesta de convenio.
     */
    public void actualizarPropuestaConvenio(){
        try {
           
            // actualizar propuesta convenio
            propuestaConvenioService.merge(propuestaConvenio);
            
            // persona solicitante
            PersonaPropuesta persPropuesta = personaPropuestaService.getPersonaPropuestaByPropuestaTipoPersona(propuestaConvenio.getIdPropuesta(), SOLICITANTE);                           
            persPropuesta.setPersona(solicitante);   
            persPropuesta.getPersonaPropuestaPK().setIdPersona(solicitante.getIdPersona());                                
            personaPropuestaService.updatePersonaPropuesta(solicitante.getIdPersona(),persPropuesta.getPropuestaConvenio().getIdPropuesta(),persPropuesta.getTipoPersona().getIdTipoPersona());
            
            
             // persona REFERENTE_INTERNO             
            PersonaPropuesta persPropuestaRefInterno = personaPropuestaService.getPersonaPropuestaByPropuestaTipoPersona(propuestaConvenio.getIdPropuesta(), REFERENTE_INTERNO);                           
            persPropuestaRefInterno.setPersona(referenteInterno);   
            persPropuestaRefInterno.getPersonaPropuestaPK().setIdPersona(referenteInterno.getIdPersona());           
            personaPropuestaService.updatePersonaPropuesta(referenteInterno.getIdPersona(),persPropuestaRefInterno.getPropuestaConvenio().getIdPropuesta(),persPropuestaRefInterno.getTipoPersona().getIdTipoPersona());
            
            // persona REFERENTE_EXTERNO
            PersonaPropuesta persPropuestaRefExterno = personaPropuestaService.getPersonaPropuestaByPropuestaTipoPersona(propuestaConvenio.getIdPropuesta(), REFERENTE_EXTERNO);                           
            persPropuestaRefExterno.setPersona(referenteExterno);   
            persPropuestaRefExterno.getPersonaPropuestaPK().setIdPersona(referenteExterno.getIdPersona());           
           
            personaPropuestaService.updatePersonaPropuesta(referenteExterno.getIdPersona(),persPropuestaRefExterno.getPropuestaConvenio().getIdPropuesta(),persPropuestaRefExterno.getTipoPersona().getIdTipoPersona());
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Actualizado", "Propuesta Convenio!!"));
            
            //sleep 3 seconds
            Thread.sleep(3000);
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarConvenio.xhtml");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void volver(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarConvenio.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(PropuestaConvenioMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void onTipoConvenioChange(){
        try {            
            
            if(propuestaConvenio.getIdTipoPropuestaConvenio().getNombrePropuestaConvenio().equalsIgnoreCase(CONVENIO_MARCO)){
                flagConvenioMarco=true;
            }else{
                listadoPropuestaConvenio = propuestaConvenioService.getPropuestaConvenioByTipoPropuesta(propuestaConvenio.getIdTipoPropuestaConvenio());
                flagConvenioMarco=false;
            }
            
            // RequestContext.getCurrentInstance().update("formAdmin:idNamePropuesta");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
    
    public void cargarPropuestaConvenio(int idPropuestaConvenio) {
         try {
             flagEdicion= true;
             propuestaConvenio=propuestaConvenioService.getPropuestaCovenioByID(idPropuestaConvenio);             
             if(propuestaConvenio.getIdTipoPropuestaConvenio().getNombrePropuestaConvenio().equalsIgnoreCase(CONVENIO_MARCO)){
                 flagConvenioMarco=true;
             }
         } catch (Exception e) {
             e.printStackTrace();
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

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public List<TipoPropuestaConvenio> getListadoTipoPrpouestaConvenio() {
        return listadoTipoPrpouestaConvenio;
    }

    public void setListadoTipoPrpouestaConvenio(List<TipoPropuestaConvenio> listadoTipoPrpouestaConvenio) {
        this.listadoTipoPrpouestaConvenio = listadoTipoPrpouestaConvenio;
    }

    public List<PropuestaConvenio> getListadoPropuestaConvenio() {
        return listadoPropuestaConvenio;
    }

    public void setListadoPropuestaConvenio(List<PropuestaConvenio> listadoPropuestaConvenio) {
        this.listadoPropuestaConvenio = listadoPropuestaConvenio;
    }

    public PropuestaConvenio getPropuestaConvenioTemp() {
        return propuestaConvenioTemp;
    }

    public void setPropuestaConvenioTemp(PropuestaConvenio propuestaConvenioTemp) {
        this.propuestaConvenioTemp = propuestaConvenioTemp;
    }

    public boolean isFlagConvenioMarco() {
        return flagConvenioMarco;
    }

    public void setFlagConvenioMarco(boolean flagConvenioMarco) {
        this.flagConvenioMarco = flagConvenioMarco;
    }

    public boolean isFlagEdicion() {
        return flagEdicion;
    }

    public void setFlagEdicion(boolean flagEdicion) {
        this.flagEdicion = flagEdicion;
    }

   

}
