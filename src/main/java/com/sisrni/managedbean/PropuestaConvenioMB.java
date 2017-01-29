/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.converter.FacultadUnidadConverter;
import com.sisrni.mail.JCMail;
import com.sisrni.model.Carrera;
import com.sisrni.model.Estado;
import com.sisrni.model.Organismo;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaPropuesta;
import com.sisrni.model.PersonaPropuestaPK;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Facultad;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.PropuestaEstado;
import com.sisrni.model.PropuestaEstadoPK;
import com.sisrni.model.TipoPropuestaConvenio;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.security.AppUserDetails;
import com.sisrni.service.EscuelaDepartamentoService;
import com.sisrni.service.EstadoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PersonaPropuestaService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.PropuestaEstadoService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoPropuestaConvenioService;
import com.sisrni.service.UnidadService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
     
    private static final String FIJO="FIJO";
    private static final String CELULAR="CELULAR";  
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
    
    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;
    
    @Autowired
    @Qualifier(value = "escuelaDepartamentoService")
    private EscuelaDepartamentoService escuelaDepartamentoService;
    
    
    private String numDocumentoInterno;
    private String numDocumentoExterno;
    
    private List<Persona> listadoPersonasSolicitante;
    private List<Persona> listadoPersonasInterno;
    private List<Persona> listadoPersonasExterno;
    
    private List<Organismo> listadoOrganismo;
    private Organismo organismo;  
   
    private List<TipoPersona> listadoTipoPersona;
    
    private List<Telefono> listadoTelefonoReferenteInterno;
    private List<Telefono> listadoTelefonoReferenteExterno;
    
    private List<TipoPropuestaConvenio> listadoTipoPrpouestaConvenio;
    private List<PropuestaConvenio> listadoPropuestaConvenio;
    
    private PropuestaConvenio propuestaConvenio;
    private PropuestaConvenio propuestaConvenioTemp;
    private List<PojoFacultadesUnidades> listaFacultadUnidad;
    private PojoFacultadesUnidades facultadesUnidades;
    private PojoFacultadesUnidades facultadesUnidadesInterno;
    private List<Facultad> listaFacultad;
    private List<Unidad> listaUnidad;
    private List<EscuelaDepartamento> listadoEscuelaDepartamento;
    private EscuelaDepartamento escuelaDepartamento;
    private EscuelaDepartamento escuelaDepartamentoInterno;
    
    private Persona personaEdit;
    private Persona solicitante;
    private Persona referenteInterno;
    private Persona referenteExterno;
    
    private Telefono telFijoInterno;
    private Telefono telCelularInterno;
    
    private Telefono telFijoExterno;
    private Telefono telCelularExterno;
      
    private CurrentUserSessionBean user;
    private AppUserDetails usuario;

    private boolean flagConvenioMarco = false;
    private boolean flagEdicion = false;
    private boolean flagEscuelaDept = false;
    private boolean flagEscuelaDeptInterno = false;
    private boolean mismoSolicitante;
    
    private Boolean tabAsis;
    private Boolean tabAsisMostrar;
    private Boolean tabAsisExterno;
    private Boolean tabAsisMostrarExterno;
    
    private JCMail mail;
    
     
    
    @PostConstruct
    public void init() {
        try {          
          // RequestContext.getCurrentInstance().reset(":formAdmin"); 
           inicializador();
           inicializadorListados();           
           getListFacultadesUnidades();
           cargarUsuario();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    
    public void postInit(){
        try {            
            inicializadorListados();
            cargarUsuario();
            ///searchByNameInterno();
            //searchByNameExterno();           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
   
    /***
     * metodo incializador de instancias
     */
    
    public void inicializador() {
         try {  
            solicitante= new Persona();
            solicitante.setIdEscuelaDepto(new EscuelaDepartamento());
            solicitante.setIdCarrera(new Carrera());
            solicitante.getIdCarrera().setIdFacultad(new Facultad());            
            solicitante.setIdUnidad(new Unidad());
            facultadesUnidades = new PojoFacultadesUnidades();
            facultadesUnidadesInterno = new PojoFacultadesUnidades();
            propuestaConvenioTemp = new PropuestaConvenio();
            referenteInterno = new Persona();
            referenteExterno = new Persona();
            telFijoInterno = new Telefono();
            telCelularInterno = new Telefono();
            telFijoExterno = new Telefono(); 
            telCelularExterno = new Telefono(); 
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            personaEdit = new Persona();
            propuestaConvenio = new PropuestaConvenio();   
            escuelaDepartamento = new EscuelaDepartamento();
            tabAsisMostrar = Boolean.FALSE;
            tabAsis = Boolean.FALSE;
            tabAsisExterno= Boolean.FALSE;
            tabAsisMostrarExterno= Boolean.FALSE;
         } catch (Exception e) {
           e.printStackTrace();
         }
    }
    
    /**
     * Metodo para recargas listados
     */
    private void inicializadorListados() {
         try {  
             
             listadoPersonasSolicitante = new ArrayList<Persona>();
             listadoPersonasInterno = new ArrayList<Persona>();
             listadoPersonasExterno = new ArrayList<Persona>();
             listadoTipoPrpouestaConvenio = new ArrayList<TipoPropuestaConvenio>();
             
             listadoPersonasSolicitante= personaService.findAll();
             listadoPersonasInterno= personaService.findAll();
             listadoPersonasExterno= personaService.findAll();
             listadoTipoPrpouestaConvenio = tipoPropuestaConvenioService.findAll();
             
             //***Editar Persona***
             listadoOrganismo = organismoService.findAll();
             listadoTipoPersona = tipoPersonaService.findAll();
           
             listaFacultad = facultadService.findAll();
             listaUnidad = unidadService.findAll();
             
             listadoEscuelaDepartamento = escuelaDepartamentoService.findAll();
             
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
         
          if(solicitante.getIdUnidad()!= null){
            
             for(PojoFacultadesUnidades us: listaFacultadUnidad){
                 if(us.getId()==solicitante.getIdUnidad().getIdUnidad() && us.getUnidadFacultad()=='U'){
                     facultadesUnidades=us;
                 }
             }
          }
          if(solicitante.getIdCarrera()!= null){              
             for(PojoFacultadesUnidades us: listaFacultadUnidad){
                 if(us.getId()== solicitante.getIdCarrera().getIdFacultad().getIdFacultad() && us.getUnidadFacultad()=='F'){
                     facultadesUnidades=us;
                 }
             }
          }
                      
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

     
     /***
      * Metodo para realizar busquedas por medio del DUI agrega guion al final del 7 digito
      * para compara con el alamcenado
      * @param query 
      */
     public void completeBusquedaDui(String query) {
        
         try {
           
             query=query.substring(0,7)+"-"+query.substring(7);
             
             referenteInterno=personaService.getPersonaByDui(query);
             
             if(referenteInterno == null){
               referenteInterno=new Persona();
             }

             referenteInterno.setDuiPersona(query);           
             
             if (referenteInterno.getIdPersona() != null) {

                 List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(referenteInterno);

                 for (Telefono tel : telefonosByPersona) {
                     if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                         telFijoInterno = tel;
                     }
                     if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                         telCelularInterno = tel;
                     }
                 }

                 if (referenteInterno.getIdUnidad() != null) {
                     for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                         if (us.getId() == solicitante.getIdUnidad().getIdUnidad() && us.getUnidadFacultad() == 'U') {
                             facultadesUnidadesInterno = us;
                         }
                     }
                 }
                 if (referenteInterno.getIdCarrera() != null) {
                     for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                         if (us.getId() == referenteInterno.getIdCarrera().getIdFacultad().getIdFacultad() && us.getUnidadFacultad() == 'F') {
                             facultadesUnidadesInterno = us;
                         }
                     }
                 }
             RequestContext context=RequestContext.getCurrentInstance();
             context.update("formAdmin:idSolicinateNombreInterno");
             context.update("formAdmin:idSolicinateApellidoInterno");
             context.update("formAdmin:email");
             context.update("formAdmin:idTelFijoInterno");
             context.update("formAdmin:idCelInterno");
             context.update("formAdmin:idFacultadInterno");
             context.update("formAdmin:idUnidadInterno");
             context.update("formAdmin:idFacultadUnidadInterno");
             context.update("formAdmin:idCargoInterno");
             }
            
             
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
     
     
     /***
      * Metodo para realizar busquedas por medio de pasaporte
      * utilizado por personas extranjeras.
      * @param query 
      */
     
     public void completeBusquedaPassaporte(String query) {
        
         try {
             
             referenteExterno=personaService.getPersonaByPasaporte(query);
             
             if(referenteExterno==null){
               referenteExterno= new Persona();
             }
             
             referenteExterno.setPasaporte(query);
                          
             if (referenteExterno.getIdPersona() != null) {
                 List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(referenteExterno);

                 for (Telefono tel : telefonosByPersona) {
                     if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                         telFijoExterno = tel;
                     }
                     if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                         telCelularExterno = tel;
                     }
                 }
             RequestContext context=RequestContext.getCurrentInstance();
             context.update("formAdmin:idSolicinateNombreExterno");
             context.update("formAdmin:idSolicinateApellidoExterno");
             context.update("formAdmin:emailExterno");
             context.update("formAdmin:idTelefonoExterno");
             context.update("formAdmin:idCelExterno");
             context.update("formAdmin:idCargoExterno");
             context.update("formAdmin:idEntidadInstitucion");
             }
             
             

             
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
     
     /**
      * Metodo utilizado para, cuando el solicitante sea el mismo solicitante interno.
      */
    public void mismoSolicitante() {
        try {
           
            if (mismoSolicitante) {
                    referenteInterno = solicitante;
                    numDocumentoInterno = referenteInterno.getDuiPersona().replaceAll("-","");
                if (referenteInterno.getIdUnidad() != null) {
                    for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                        if (us.getId() == solicitante.getIdUnidad().getIdUnidad() && us.getUnidadFacultad() == 'U') {
                            facultadesUnidadesInterno = us;
                        }
                    }
                }
                if (referenteInterno.getIdCarrera() != null) {
                    for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                        if (us.getId() == referenteInterno.getIdCarrera().getIdFacultad().getIdFacultad() && us.getUnidadFacultad() == 'F') {
                            facultadesUnidadesInterno = us;
                        }
                    }
                }

                List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(referenteInterno);

                for (Telefono tel : telefonosByPersona) {
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                        telFijoInterno = tel;
                    }
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                        telCelularInterno = tel;
                    }
                }
            }else{
               referenteInterno = new Persona();
               telFijoInterno = new Telefono();
               telCelularInterno= new Telefono();
               facultadesUnidadesInterno = new PojoFacultadesUnidades();
               numDocumentoInterno = null;
            }
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formAdmin:idSolicinateNombreInterno");
            context.update("formAdmin:idSolicinateApellidoInterno");
            context.update("formAdmin:email");
            context.update("formAdmin:idTelFijoInterno");
            context.update("formAdmin:idCelInterno");
            context.update("formAdmin:idFacultadInterno");
            context.update("formAdmin:idUnidadInterno");
            context.update("formAdmin:idFacultadUnidadInterno");
            context.update("formAdmin:idCargoInterno");
            context.update("formAdmin:idDocumentoInterno");
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
             personaEdit = personaService.getPersonaByID(referenteInterno.getIdPersona());
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
            RequestContext context = RequestContext.getCurrentInstance();              
                context.execute("PF('EditDialog').hide();");
            
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
            propuestaConvenio.setIdConvenio(propuestaConvenioTemp.getIdPropuesta());
            propuestaConvenio.setFechaIngreso(new Date());
            propuestaConvenioService.save(propuestaConvenio);
            
            
            //Estado de propuesta de convenio
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
            
            solicitante.setIdUnidad(null);
            solicitante.setIdCarrera(null);
            solicitante.setIdEscuelaDepto(null);
           
            if (facultadesUnidades.getUnidadFacultad() == 'U') {
                solicitante.setIdUnidad(unidadService.findById(facultadesUnidades.getId()));
            } else if (facultadesUnidades.getUnidadFacultad() == 'F') {
                solicitante.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamento.getIdEscuelaDepto()));
            }
            
            personaService.merge(solicitante);
            
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
            if (referenteInterno.getDuiPersona() != null && referenteInterno.getNombrePersona() != null && referenteInterno.getApellidoPersona() != null && referenteInterno.getEmailPersona() != null) {
                if (!mismoSolicitante) {//verfico que la persona exista, si true entonces 
                    //crear persona y luego almacenar
                    referenteInterno.setIdUnidad(null);
                    referenteInterno.setIdCarrera(null);
                    referenteInterno.setIdEscuelaDepto(null);
                    
                    if (facultadesUnidadesInterno.getUnidadFacultad() == 'U') {
                        referenteInterno.setIdUnidad(unidadService.findById(facultadesUnidadesInterno.getId()));
                    } else if (facultadesUnidadesInterno.getUnidadFacultad() == 'F') {
                        referenteInterno.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamentoInterno.getIdEscuelaDepto()));
                    }
                    
                    referenteInterno.setExtranjero(Boolean.FALSE);//no es extrajero
                    referenteInterno.setActivo(Boolean.TRUE);//esta activo
                    referenteInterno.setPasaporte("0");
                    personaService.saveOrUpdate(referenteInterno);
                }
                
                telefonoService.saveOrUpdate(telFijoInterno);
                telefonoService.saveOrUpdate(telCelularInterno);
                
                prsRefInterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_INTERNO));
                prsRefInterno.setPersona(referenteInterno);
                personaPropuestaPK = new PersonaPropuestaPK();
                personaPropuestaPK.setIdPersona(referenteInterno.getIdPersona());
                personaPropuestaPK.setIdPropuesta(propuestaConvenio.getIdPropuesta());
                personaPropuestaPK.setIdTipoPersona(prsRefInterno.getTipoPersona().getIdTipoPersona());
                prsRefInterno.setPersonaPropuestaPK(personaPropuestaPK);
                personaPropuestaService.save(prsRefInterno);
            }
           
            
               // persona REFERENTE_EXTERNO
            if (referenteExterno.getPasaporte() != null && referenteExterno.getNombrePersona() != null && referenteExterno.getApellidoPersona() != null && referenteExterno.getEmailPersona() != null) {

                //crear persona y luego almacenar
                referenteExterno.setExtranjero(Boolean.TRUE);//no es extrajero
                referenteExterno.setActivo(Boolean.TRUE);//esta activo
                referenteExterno.setDuiPersona("0");
                personaService.saveOrUpdate(referenteExterno);
                
                telefonoService.saveOrUpdate(telCelularExterno);
                telefonoService.saveOrUpdate(telFijoExterno);
                
                prsRefExterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_EXTERNO));
                prsRefExterno.setPersona(referenteExterno);
                personaPropuestaPK = new PersonaPropuestaPK();
                personaPropuestaPK.setIdPersona(referenteExterno.getIdPersona());
                personaPropuestaPK.setIdPropuesta(propuestaConvenio.getIdPropuesta());
                personaPropuestaPK.setIdTipoPersona(prsRefExterno.getTipoPersona().getIdTipoPersona());
                prsRefExterno.setPersonaPropuestaPK(personaPropuestaPK);
                personaPropuestaService.save(prsRefExterno);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardado", "Propuesta Convenio almacenada"));
           
            
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "sisrni/views/convenio/consultarPropuestaConvenio.xhtml");
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
            propuestaConvenio.setIdConvenio(propuestaConvenioTemp.getIdPropuesta());
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
    
   
    public void onTipoConvenioChange(){
        try {            
            
            if(propuestaConvenio.getIdTipoPropuestaConvenio().getNombrePropuestaConvenio().equalsIgnoreCase(CONVENIO_MARCO)){
                flagConvenioMarco=true;
            }else{
                listadoPropuestaConvenio = propuestaConvenioService.getConvenios();
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
    

    
    /**
 * Metodo para obtener arreglo de facultades y unidades
 * @param facs
 * @param unidades
 * @return 
 */
    private List<PojoFacultadesUnidades> getListFacultadesUnidades() {

         
         
        listaFacultadUnidad = new ArrayList<PojoFacultadesUnidades>();
        int i=1;
        
        for (Facultad fac : listaFacultad) {
            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();            
            pojo.setLabel(fac.getNombreFacultad());
            pojo.setUnidadFacultad('F');
            pojo.setId(i++);
            pojo.setPrimary(fac.getIdFacultad());
            listaFacultadUnidad.add(pojo);
        }
        for (Unidad uni : listaUnidad) {
            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();            
            pojo.setLabel(uni.getNombreUnidad());
            pojo.setUnidadFacultad('U');
            pojo.setId(i++);
            pojo.setPrimary(uni.getIdUnidad());
            listaFacultadUnidad.add(pojo);
        }        
        return listaFacultadUnidad;
    }

  /**
   * Metodo para renderizar escuela o departamento despues de seleccionar facultad para un solicitante
   */
    public void actualizarEscuelaDept(){
        try {
             
             if(facultadesUnidades.getUnidadFacultad()=='U'){
                  flagEscuelaDept=true;
             }else{
                 flagEscuelaDept=false;
             }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * * Metodo para renderizar escuela o departamento despues de seleccionar facultad para un solicitanteInterno
     */
    public void actualizarEscuelaDeptInterno(){
        try {
             
             if(facultadesUnidadesInterno.getUnidadFacultad()=='U'){
                  flagEscuelaDeptInterno=true;
             }else{
                 flagEscuelaDeptInterno=false;
             }
                
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * metodo habilita tab de solicitante interno 
     */
     public void mostrarTab() {
        tabAsis = tabAsisMostrar ? Boolean.TRUE : Boolean.FALSE;
    }
    /**
     * metodo habilita tab de solicitante interno 
     */
     public void mostrarTabExterno() {
        tabAsisExterno = tabAsisMostrarExterno ? Boolean.TRUE : Boolean.FALSE;
    }
     
     
    /// test de email
   
public void FileRead(){
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;
    String to = "";
    String subject = "Test Send Email";
    String messages = "Henrry Culeu";
    ArrayList<String> listado = new ArrayList<String>();
    
//    SAXBuilder sax;
//    Document dconfig1=null;
    String MailAccount="tgraduacion01@gmail.com";
    String PassMailAccount="";
    String rootFileContent = "";
   
    
      try {
                mail = new JCMail();
          
                mail.setFrom( MailAccount );
                mail.setPassword("tragra01" );        
                mail.setTo(solicitante.getEmailPersona());
                mail.setSubject( "Test GAMIL" );
                mail.setMessage( messages );
                mail.SEND();      
  
                 
      }

      
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta 
         // una excepcion.
         try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
     }
      }    




/// test de email
    
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

    public Telefono getTelCelularInterno() {
        return telCelularInterno;
    }

    public void setTelCelularInterno(Telefono telCelularInterno) {
        this.telCelularInterno = telCelularInterno;
    }

    public Telefono getTelFijoExterno() {
        return telFijoExterno;
    }

    public void setTelFijoExterno(Telefono telFijoExterno) {
        this.telFijoExterno = telFijoExterno;
    }

    public Telefono getTelCelularExterno() {
        return telCelularExterno;
    }

    public void setTelCelularExterno(Telefono telCelularExterno) {
        this.telCelularExterno = telCelularExterno;
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

    public PropuestaConvenio getPropuestaConvenioTemp() {
        return propuestaConvenioTemp;
    }

    public void setPropuestaConvenioTemp(PropuestaConvenio propuestaConvenioTemp) {
        this.propuestaConvenioTemp = propuestaConvenioTemp;
    }

    public JCMail getMail() {
        return mail;
    }

    public void setMail(JCMail mail) {
        this.mail = mail;
    }

    public List<PojoFacultadesUnidades> getListaFacultadUnidad() {
        return listaFacultadUnidad;
    }

    public void setListaFacultadUnidad(List<PojoFacultadesUnidades> listaFacultadUnidad) {
        this.listaFacultadUnidad = listaFacultadUnidad;
    }

    public List<Facultad> getListaFacultad() {
        return listaFacultad;
    }

    public void setListaFacultad(List<Facultad> listaFacultad) {
        this.listaFacultad = listaFacultad;
    }

    public List<Unidad> getListaUnidad() {
        return listaUnidad;
    }

    public void setListaUnidad(List<Unidad> listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

    public PojoFacultadesUnidades getFacultadesUnidades() {
        return facultadesUnidades;
    }

    public void setFacultadesUnidades(PojoFacultadesUnidades facultadesUnidades) {
        this.facultadesUnidades = facultadesUnidades;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public PojoFacultadesUnidades getFacultadesUnidadesInterno() {
        return facultadesUnidadesInterno;
    }

    public void setFacultadesUnidadesInterno(PojoFacultadesUnidades facultadesUnidadesInterno) {
        this.facultadesUnidadesInterno = facultadesUnidadesInterno;
    }

    public boolean isMismoSolicitante() {
        return mismoSolicitante;
    }

    public void setMismoSolicitante(boolean mismoSolicitante) {
        this.mismoSolicitante = mismoSolicitante;
    }

    public FacultadService getFacultadService() {
        return facultadService;
    }

    public void setFacultadService(FacultadService facultadService) {
        this.facultadService = facultadService;
    }

    public EscuelaDepartamento getEscuelaDepartamento() {
        return escuelaDepartamento;
    }

    public void setEscuelaDepartamento(EscuelaDepartamento escuelaDepartamento) {
        this.escuelaDepartamento = escuelaDepartamento;
    }

    public List<EscuelaDepartamento> getListadoEscuelaDepartamento() {
        return listadoEscuelaDepartamento;
    }

    public void setListadoEscuelaDepartamento(List<EscuelaDepartamento> listadoEscuelaDepartamento) {
        this.listadoEscuelaDepartamento = listadoEscuelaDepartamento;
    }

    public EscuelaDepartamento getEscuelaDepartamentoInterno() {
        return escuelaDepartamentoInterno;
    }

    public void setEscuelaDepartamentoInterno(EscuelaDepartamento escuelaDepartamentoInterno) {
        this.escuelaDepartamentoInterno = escuelaDepartamentoInterno;
    }

    public boolean isFlagEscuelaDept() {
        return flagEscuelaDept;
    }

    public void setFlagEscuelaDept(boolean flagEscuelaDept) {
        this.flagEscuelaDept = flagEscuelaDept;
    }

    public boolean isFlagEscuelaDeptInterno() {
        return flagEscuelaDeptInterno;
    }

    public void setFlagEscuelaDeptInterno(boolean flagEscuelaDeptInterno) {
        this.flagEscuelaDeptInterno = flagEscuelaDeptInterno;
    }

    public Boolean getTabAsisMostrar() {
        return tabAsisMostrar;
    }

    public void setTabAsisMostrar(Boolean tabAsisMostrar) {
        this.tabAsisMostrar = tabAsisMostrar;
    }

    public Boolean getTabAsis() {
        return tabAsis;
    }

    public void setTabAsis(Boolean tabAsis) {
        this.tabAsis = tabAsis;
    }

    public Boolean getTabAsisExterno() {
        return tabAsisExterno;
    }

    public void setTabAsisExterno(Boolean tabAsisExterno) {
        this.tabAsisExterno = tabAsisExterno;
    }

    public Boolean getTabAsisMostrarExterno() {
        return tabAsisMostrarExterno;
    }

    public void setTabAsisMostrarExterno(Boolean tabAsisMostrarExterno) {
        this.tabAsisMostrarExterno = tabAsisMostrarExterno;
    }

   

}
