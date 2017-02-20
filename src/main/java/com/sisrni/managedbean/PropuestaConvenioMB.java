/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

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
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
public class PropuestaConvenioMB implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final String FIJO = "FIJO";
    private static final String CELULAR = "CELULAR";
    private static final String SOLICITANTE = "SOLICITANTE";
    private static final String REFERENTE_INTERNO = "REFERENTE INTERNO";
    private static final String REFERENTE_EXTERNO = "REFERENTE EXTERNO";
    private static final String CONVENIO_MARCO = "CONVENIO MARCO";
    private static final String ESTADO = "REVISION";

    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;

    @Autowired
    @Qualifier(value = "telefonoService")
    private TelefonoService telefonoService;

    @Autowired
    @Qualifier(value = "tipoTelefonoService")
    private TipoTelefonoService tipoTelefonoService;

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

    private String tipoBusquedaInterna;
    private String tipoBusquedaExterna;

    private List<Persona> listadoPersonasSolicitante;
    private List<Persona> listadoPersonasInterno;
    private List<Persona> listadoPersonasExterno;

    private List<Organismo> listadoOrganismo;
    private Organismo organismo;

    private List<TipoPersona> listadoTipoPersona;

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
    private List<EscuelaDepartamento> listadoEscuelaDepartamentoInter;
    private EscuelaDepartamento escuelaDepartamento;
    private EscuelaDepartamento escuelaDepartamentoInterno;

    private Persona personaEdit;
    private Persona solicitante;
    private Persona referenteInterno;
    private Persona referenteExterno;

    private List<Telefono> listadoTelefonoReferenteSolicitante;
    private List<Telefono> listadoTelefonoReferenteInterno;
    private List<Telefono> listadoTelefonoReferenteExterno;

    private Telefono telFijoSolicitante;
    private Telefono telCelularSolicitante;

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
    private boolean mismoSol = false;

    private Boolean tabAsis;
    private Boolean tabAsisMostrar;
    private Boolean tabAsisExterno;
    private Boolean tabAsisMostrarExterno;
    private Boolean disableAutoInterno;
    private Boolean flagSearchDuiInterno;
    private Boolean flagSearchNombreInterno;
    private Boolean flagSearchEmailInterno;
    private Boolean disableAutoExterno;
    private Boolean flagSearchDuiExterno;
    private Boolean flagSearchNombreExterno;
    private Boolean flagSearchEmailExterno;

    private List<Persona> listAll;

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

    public void postInit() {
        try {
            inicializador();
            inicializadorListados();
            getListFacultadesUnidades(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * *
     * metodo incializador de instancias
     */
    public void inicializador() {
        try {
            solicitante = new Persona();
            solicitante.setIdEscuelaDepto(new EscuelaDepartamento());
            solicitante.setIdCarrera(new Carrera());
            solicitante.getIdCarrera().setIdFacultad(new Facultad());
            solicitante.setIdUnidad(new Unidad());
            facultadesUnidades = new PojoFacultadesUnidades();
            facultadesUnidadesInterno = new PojoFacultadesUnidades();
            escuelaDepartamentoInterno = new EscuelaDepartamento();
            propuestaConvenioTemp = new PropuestaConvenio();
            referenteInterno = new Persona();
            referenteExterno = new Persona();
            telFijoInterno = new Telefono();
            telCelularInterno = new Telefono();
            telFijoExterno = new Telefono();
            telCelularExterno = new Telefono();
            telFijoSolicitante = new Telefono();
            telCelularSolicitante = new Telefono();
            user = new CurrentUserSessionBean();
            usuario = user.getSessionUser();
            personaEdit = new Persona();
            propuestaConvenio = new PropuestaConvenio();
            escuelaDepartamento = new EscuelaDepartamento();

            tabAsisMostrar = Boolean.FALSE;
            tabAsis = Boolean.FALSE;
            tabAsisExterno = Boolean.FALSE;
            tabAsisMostrarExterno = Boolean.FALSE;
            disableAutoInterno = Boolean.TRUE;
            flagSearchDuiInterno = Boolean.FALSE;
            flagSearchNombreInterno = Boolean.FALSE;
            flagSearchEmailInterno = Boolean.FALSE;

            disableAutoExterno = Boolean.TRUE;
            flagSearchDuiExterno = Boolean.FALSE;
            flagSearchNombreExterno = Boolean.FALSE;
            flagSearchEmailExterno = Boolean.FALSE;
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

            listadoPersonasSolicitante = personaService.findAll();
            listadoPersonasInterno = personaService.findAll();
            listadoPersonasExterno = personaService.findAll();
            listadoTipoPrpouestaConvenio = tipoPropuestaConvenioService.findAll();
            //***Editar Persona***
            listadoOrganismo = organismoService.findAll();
            listadoTipoPersona = tipoPersonaService.findAll();

            listaFacultad = facultadService.findAll();
            listaUnidad = unidadService.findAll();

            listadoEscuelaDepartamento = escuelaDepartamentoService.findAll();
            listadoEscuelaDepartamentoInter = escuelaDepartamentoService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * cargar datos de usuario logeado
     */
    private void cargarUsuario() {
        try {
            solicitante = personaService.findById(usuario.getUsuario().getIdPersona());
            if (solicitante != null) {
                cargarUnidadesFacultadesSolicitante();
                cargarTelefonosSolicitante();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para realizar busquedas por nombre, email, documento independiente
     *
     * @param query
     * @return
     */
    public List<Persona> methodSearch(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaInterna.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteInternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                return list;
            } else if (tipoBusquedaInterna.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteInternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                return list;
            }
//            else if (tipoBusquedaInterna.equalsIgnoreCase("doc")) {
//                query = query.substring(0, 7) + "-" + query.substring(7);
//                referenteInterno = personaService.getPersonaByDui(query);
//                boolean add = list.add(referenteInterno);
//               
//                return list;
//            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para realizar busquedas por nombre, email, documento independiente
     *
     * @param query
     * @return
     */
    public List<Persona> methodSearchExterno(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaInterna.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteExternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                return list;
            } else if (tipoBusquedaInterna.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteExternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Metodo para realizar busquedas por nombre, email, documento independiente
     *
     * @param query
     * @return
     */
    public List<Persona> methodCompleteName(String query) {
        try {
            listAll = new ArrayList<Persona>();
            listAll = personaService.getReferenteInternoByName(query);
            List<Persona> list = new ArrayList<Persona>();
            for (Persona us : listAll) {
                list.add(us);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * *
     * Metodo para realizar busquedas por medio del DUI agrega guion al final
     * del 7 digito para compara con el alamcenado
     *
     * @param query
     */
    public void completeBusquedaDui(String query) {

        try {
            query = query.substring(0, 7) + "-" + query.substring(7);
            referenteInterno = personaService.getPersonaByDui(query);

            if (referenteInterno == null) {
                referenteInterno = new Persona();
            }

            referenteInterno.setDuiPersona(query);

            if (referenteInterno.getIdPersona() != null) {

                cargarTelefonosInternos();
                cargarUnidadesFacultadesSolicitanteInterno();

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
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * *
     * Metodo para realizar busquedas por medio de pasaporte utilizado por
     * personas extranjeras.
     *
     * @param query
     */
    public void completeBusquedaPassaporte(String query) {

        try {
            referenteExterno = personaService.getPersonaByPasaporte(query);
            if (referenteExterno == null) {
                referenteExterno = new Persona();
            }
            referenteExterno.setPasaporte(query);

            if (referenteExterno.getIdPersona() != null) {
                cargarTelefonosExterno();

                RequestContext context = RequestContext.getCurrentInstance();
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
     * Metodo utilizado para, cuando el solicitante sea el mismo solicitante
     * interno.
     */
    public void mismoSolicitantes() {
        try {
            if (mismoSolicitante) {
                mismoSol = false;
                this.referenteInterno = solicitante;
                this.facultadesUnidadesInterno = getFacultadesUnidades();
                this.escuelaDepartamentoInterno = getEscuelaDepartamento();
                cargarTelefonosInternos();
            } else {
                referenteInterno = new Persona();
                telFijoInterno = new Telefono();
                telCelularInterno = new Telefono();
                facultadesUnidadesInterno = new PojoFacultadesUnidades();
                numDocumentoInterno = null;
                escuelaDepartamentoInterno = new EscuelaDepartamento();
            }
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formAdmin:acordion:idFacultadUnidadInterno");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para alamacenar nueva propuesta de convenio
     * @throws java.lang.Exception
     */
    public void guardarPropuestaConvenio()  throws Exception   {
        try {

            // guardar propuesta convenio
            savePropuestaConvenio();

            //Estado de propuesta de convenio
            guardarEstado();

            // persona solicitante            
            guardarSolicitante();

          ////////////////////////////////////
          //// PERSONA REFERENTE_INTERNO/////
          ////////////////////////////////////  
            if (mismoSolicitante) { //verfico que la persona exista, si true entonces
                guardarReferenteInternoComplemento();
            } else if (referenteInterno.getEmailPersona() != null) {
                Persona existePersona = personaService.existePersonaByMail(referenteInterno.getEmailPersona());
                if (existePersona != null && existePersona.getIdPersona() != null) {
                    referenteInterno.setIdPersona(existePersona.getIdPersona());
                }
                if (referenteInterno.getIdPersona() != null) {
                    actualizarReferenteInterno();
                } else {
                    guardarReferenteInterno();
                }
                guardarReferenteInternoComplemento();
            }
            
            
//            if (referenteInterno.getDuiPersona() != null && referenteInterno.getNombrePersona() != null && referenteInterno.getApellidoPersona() != null && referenteInterno.getEmailPersona() != null) {
//                //verfifico si la persona ya existe en entidad persona
//                if (referenteInterno.getIdPersona() == null) {
//                    Persona existePersona = personaService.existePersona(referenteInterno.getNombrePersona(), referenteInterno.getApellidoPersona(), );
//                    if (existePersona != null && existePersona.getIdPersona() != null) {
//                        referenteInterno.setIdPersona(existePersona.getIdPersona());
//                    }
//                }
//                if (!mismoSolicitante) { //verfico que la persona exista, si true entonces
//                    if (referenteInterno.getIdPersona() != null) {
//                        actualizarReferenteInterno();
//                    } else {
//                        guardarReferenteInterno();                        
//                    }
//                }
//                guardarReferenteInternoComplemento();
//            }
            //////////////////////////////////
            //// PERSONA REFERENTE_EXTERNO////
            //////////////////////////////////
            if (referenteExterno.getEmailPersona() != null) {
                Persona existePersona = personaService.existePersonaByMail(referenteExterno.getEmailPersona());
                if (existePersona != null && existePersona.getIdPersona() != null) {
                    referenteExterno.setIdPersona(existePersona.getIdPersona());
                }

                if (referenteExterno.getIdPersona() != null) {
                    actualizarReferenteExterno();
                } else {
                    guardarReferenteExterno();
                }
            }
            
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", "Propuesta Convenio almacenada"));
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            context.redirect(context.getRequestContextPath() + "/views/convenio/consultarPropuestaConvenio.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para actualizar propuesta de convenio.
     */
    public void actualizarPropuestaConvenio() {
        try {
            
            // actualizar propuesta convenio
            propuestaConvenio.setIdConvenio(propuestaConvenioTemp.getIdPropuesta());
            propuestaConvenioService.merge(propuestaConvenio);

            // persona solicitante
            PersonaPropuesta persPropuesta = new PersonaPropuesta();
            persPropuesta = personaPropuestaService.getPersonaPropuestaByPropuestaTipoPersona(propuestaConvenio.getIdPropuesta(), SOLICITANTE);
            if (persPropuesta != null) {
                persPropuesta.setPersona(solicitante);
                persPropuesta.getPersonaPropuestaPK().setIdPersona(solicitante.getIdPersona());
                personaPropuestaService.updatePersonaPropuesta(solicitante.getIdPersona(), persPropuesta.getPropuestaConvenio().getIdPropuesta(), persPropuesta.getTipoPersona().getIdTipoPersona());
            } else {
                guardarSolicitante();
                persPropuesta.setPersona(solicitante);
                persPropuesta.setPropuestaConvenio(propuestaConvenio);
                persPropuesta.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(SOLICITANTE));
                persPropuesta.setPersonaPropuestaPK(new PersonaPropuestaPK(solicitante.getIdPersona(), persPropuesta.getTipoPersona().getIdTipoPersona(), propuestaConvenio.getIdPropuesta()));
                personaPropuestaService.save(persPropuesta);
            }

            // persona REFERENTE_INTERNO
            
            if (referenteInterno.getDuiPersona() != null && referenteInterno.getNombrePersona() != null && referenteInterno.getApellidoPersona() != null && referenteInterno.getEmailPersona() != null) {
                if (referenteInterno.getIdPersona() == null) {
                    Persona existePersona = personaService.existePersona(referenteInterno.getNombrePersona(), referenteInterno.getApellidoPersona(), referenteInterno.getEmailPersona());
                    if (existePersona != null && existePersona.getIdPersona() != null) {
                        referenteInterno.setIdPersona(existePersona.getIdPersona());
                    }
                }
                if (!mismoSolicitante) { //verfico que la persona exista, si true entonces
                    if (referenteInterno.getIdPersona() != null) {
                        actualizarReferenteInterno();
                        actualizacionReferenteInternoComplemento();
                    } else {
                        guardarReferenteInterno();
                        guardarReferenteInternoComplemento();
                    }
                }
            }
            
            
            // persona REFERENTE_EXTERNO
            if (referenteExterno.getPasaporte() != null && referenteExterno.getNombrePersona() != null && referenteExterno.getApellidoPersona() != null && referenteExterno.getEmailPersona() != null) {               
                
                if (referenteExterno.getIdPersona() == null) {
                    Persona existePersona = personaService.existePersona(referenteExterno.getNombrePersona(), referenteExterno.getApellidoPersona(), referenteExterno.getEmailPersona());
                    if (existePersona != null && existePersona.getIdPersona() != null) {
                        referenteExterno.setIdPersona(existePersona.getIdPersona());
                    }
                }                
                if (referenteExterno.getIdPersona() != null) {
                        actualizarReferenteExterno();
                    } else {
                        guardarReferenteExterno();
                    } 
            }
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getFlash().setKeepMessages(true);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado", "Propuesta Convenio!!"));

            //sleep 3 seconds
            Thread.sleep(3000);
            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarConvenio.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para alamcenar propuesta de convenio sin solicitantes
     */
    private void savePropuestaConvenio() {
        try {
            propuestaConvenio.setIdConvenio(propuestaConvenioTemp.getIdPropuesta());
            propuestaConvenio.setFechaIngreso(new Date());
            propuestaConvenioService.save(propuestaConvenio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para almacenar estado de propuesta de convenio inicialmente estara
     * en REVISION
     */
    private void guardarEstado() {
        try {
            PropuestaEstado estado = new PropuestaEstado();
            Estado stado = estadoService.getEstadoByName(ESTADO);
            estado.setFecha(new Date());
            estado.setPropuestaConvenio(propuestaConvenio);
            estado.setEstado(stado);
            estado.setPropuestaEstadoPK(new PropuestaEstadoPK(propuestaConvenio.getIdPropuesta(), stado.getIdEstado()));
            propuestaEstadoService.save(estado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para actualizar solicitante y ademas agregar en personas propuesta
     */
    private void guardarSolicitante() {
        try {

            PersonaPropuesta prsSolicitante = new PersonaPropuesta();
            solicitante.setIdUnidad(null);
            solicitante.setIdCarrera(null);
            solicitante.setIdEscuelaDepto(null);

            if (facultadesUnidades.getUnidadFacultad() == 'U') {
                solicitante.setIdUnidad(unidadService.findById(facultadesUnidades.getId()));
            } else if (facultadesUnidades.getUnidadFacultad() == 'F') {
                solicitante.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamento.getIdEscuelaDepto()));
            }

            listadoTelefonoReferenteSolicitante = new ArrayList<Telefono>();
            telFijoSolicitante.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
            telFijoSolicitante.setIdPersona(solicitante);
            listadoTelefonoReferenteSolicitante.add(telFijoSolicitante);

            telCelularSolicitante.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
            telCelularSolicitante.setIdPersona(solicitante);
            listadoTelefonoReferenteSolicitante.add(telCelularSolicitante);
            solicitante.setTelefonoList(listadoTelefonoReferenteSolicitante);
            personaService.merge(solicitante);

            //Guardar solicitante en persona de propuesta
            prsSolicitante.setPropuestaConvenio(propuestaConvenio);
            prsSolicitante.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(SOLICITANTE));
            prsSolicitante.setPersona(solicitante);
            prsSolicitante.setPersonaPropuestaPK(new PersonaPropuestaPK(solicitante.getIdPersona(), prsSolicitante.getTipoPersona().getIdTipoPersona(), propuestaConvenio.getIdPropuesta()));
            personaPropuestaService.save(prsSolicitante);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que guarda nuevas personas referentes internos
     */
    private void guardarReferenteInterno() {
        try {
            //crear persona y luego almacenar
            referenteInterno.setIdUnidad(null);
            referenteInterno.setIdCarrera(null);
            referenteInterno.setIdEscuelaDepto(null);

            if (facultadesUnidadesInterno.getUnidadFacultad() == 'U') {
                referenteInterno.setIdUnidad(unidadService.findById(facultadesUnidadesInterno.getPrimary()));
            } else if (facultadesUnidadesInterno.getUnidadFacultad() == 'F') {
                referenteInterno.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamentoInterno.getIdEscuelaDepto()));
            }

            referenteInterno.setExtranjero(Boolean.FALSE);//no es extrajero
            referenteInterno.setActivo(Boolean.TRUE);//esta activo
            referenteInterno.setPasaporte("0");

            //telefonos
            listadoTelefonoReferenteInterno = new ArrayList<Telefono>();
            telFijoInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
            telFijoInterno.setIdPersona(referenteInterno);
            listadoTelefonoReferenteInterno.add(telFijoInterno);

            telCelularInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
            telCelularInterno.setIdPersona(referenteInterno);
            listadoTelefonoReferenteInterno.add(telCelularInterno);
            referenteInterno.setTelefonoList(listadoTelefonoReferenteInterno);
            personaService.saveOrUpdate(referenteInterno);
            //personaService.save(referenteInterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que Actualziar personas referentes internos
     */
    private void actualizarReferenteInterno() {
        try {
            //crear persona y luego almacenar
            referenteInterno.setIdUnidad(null);
            referenteInterno.setIdCarrera(null);
            referenteInterno.setIdEscuelaDepto(null);
            if (facultadesUnidadesInterno.getUnidadFacultad() == 'U') {
                referenteInterno.setIdUnidad(unidadService.findById(facultadesUnidadesInterno.getPrimary()));
            } else if (facultadesUnidadesInterno.getUnidadFacultad() == 'F') {
                referenteInterno.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamentoInterno.getIdEscuelaDepto()));
            }
            referenteInterno.setExtranjero(Boolean.FALSE);//no es extrajero
            referenteInterno.setActivo(Boolean.TRUE);//esta activo
            referenteInterno.setPasaporte("0");
            personaService.merge(referenteInterno);
            //telefonos
   
            telFijoInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
            telFijoInterno.setIdPersona(referenteInterno);
            telefonoService.merge(telFijoInterno);

            telCelularInterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
            telCelularInterno.setIdPersona(referenteInterno);
            telefonoService.merge(telCelularInterno);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que complementa el almacenamiento de referente interno, el tipo de
     * persona para referente interno
     */
    private void guardarReferenteInternoComplemento() {
        try {
            PersonaPropuesta prsRefInterno = new PersonaPropuesta();
            prsRefInterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_INTERNO));
            prsRefInterno.setPersona(referenteInterno);
            prsRefInterno.setPersonaPropuestaPK(new PersonaPropuestaPK(referenteInterno.getIdPersona(), prsRefInterno.getTipoPersona().getIdTipoPersona(), propuestaConvenio.getIdPropuesta()));
            personaPropuestaService.save(prsRefInterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo que complementa la actualizacion de referente interno, el tipo de
     * persona para referente interno
     */
    private void actualizacionReferenteInternoComplemento() {
        try {
             PersonaPropuesta prsRefInterno = new PersonaPropuesta();       
             prsRefInterno = personaPropuestaService.getPersonaPropuestaByPropuestaTipoPersona(propuestaConvenio.getIdPropuesta(), REFERENTE_INTERNO);
             
             if (prsRefInterno != null) {
                    prsRefInterno.setPersona(referenteInterno);
                    prsRefInterno.setPersonaPropuestaPK(new PersonaPropuestaPK(referenteInterno.getIdPersona(), prsRefInterno.getTipoPersona().getIdTipoPersona(), propuestaConvenio.getIdPropuesta()));                   
                    personaPropuestaService.updatePersonaPropuesta(referenteInterno.getIdPersona(), prsRefInterno.getPropuestaConvenio().getIdPropuesta(), prsRefInterno.getTipoPersona().getIdTipoPersona());               
              }       

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para alamcenar Referente Externo con telefonos, tipo de propuesta
     */
    private void guardarReferenteExterno() {
        try {
            //crear persona y luego almacenar
            PersonaPropuesta prsRefExterno = new PersonaPropuesta();
            referenteExterno.setExtranjero(Boolean.TRUE);//no es extrajero
            referenteExterno.setActivo(Boolean.TRUE);//esta activo
            referenteExterno.setDuiPersona("0");
            listadoTelefonoReferenteExterno = new ArrayList<Telefono>();
            telFijoExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
            telFijoExterno.setIdPersona(referenteExterno);
            telCelularExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
            telCelularExterno.setIdPersona(referenteExterno);
            listadoTelefonoReferenteExterno.add(telFijoInterno);
            listadoTelefonoReferenteExterno.add(telFijoExterno);
            referenteExterno.setTelefonoList(listadoTelefonoReferenteInterno);       
            personaService.saveOrUpdate(referenteExterno);

            prsRefExterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_EXTERNO));
            prsRefExterno.setPersona(referenteExterno);
            prsRefExterno.setPersonaPropuestaPK(new PersonaPropuestaPK(referenteExterno.getIdPersona(), prsRefExterno.getTipoPersona().getIdTipoPersona(), propuestaConvenio.getIdPropuesta()));
            personaPropuestaService.save(prsRefExterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo para actualizar Referente Externo con telefonos, tipo de propuesta
     */
    private void actualizarReferenteExterno() {
        try {
            //crear persona y luego almacenar
           
            referenteExterno.setExtranjero(Boolean.TRUE);//no es extrajero
            referenteExterno.setActivo(Boolean.TRUE);//esta activo
            referenteExterno.setDuiPersona("0");
            personaService.merge(referenteExterno);
                        
            telFijoExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(FIJO));
            telFijoExterno.setIdPersona(referenteExterno);
            telefonoService.merge(telFijoExterno);
            telCelularExterno.setIdTipoTelefono(tipoTelefonoService.getTipoByDesc(CELULAR));
            telCelularExterno.setIdPersona(referenteExterno);
            telefonoService.merge(telCelularExterno);          
                        
            PersonaPropuesta prsRefExterno = personaPropuestaService.getPersonaPropuestaByPropuestaTipoPersona(propuestaConvenio.getIdPropuesta(), REFERENTE_EXTERNO);             
            prsRefExterno.setTipoPersona(tipoPersonaService.getTipoPersonaByNombre(REFERENTE_EXTERNO));
            prsRefExterno.setPersona(referenteExterno);
            prsRefExterno.setPersonaPropuestaPK(new PersonaPropuestaPK(referenteExterno.getIdPersona(), prsRefExterno.getTipoPersona().getIdTipoPersona(), propuestaConvenio.getIdPropuesta()));
            personaPropuestaService.updatePersonaPropuesta(referenteExterno.getIdPersona(), prsRefExterno.getPropuestaConvenio().getIdPropuesta(), prsRefExterno.getTipoPersona().getIdTipoPersona());
            //personaPropuestaService.save(prsRefExterno);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
    
        /**
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona solicitante interna
     */
    public void cargarNombreSoliInterno() {
        try {
            if (referenteInterno.getIdPersona() != null) {
                cargarTelefonosInternos();
                cargarUnidadesFacultadesSolicitanteInterno();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona solicitante interna
     */
    public void cargarNombreSoliExterno() {
        try {

            if (referenteExterno == null) {
                referenteExterno = new Persona();
            }
            if (referenteExterno.getIdPersona() != null) {
                cargarTelefonosExterno();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Metodo para precargar facultad / unidad o Escuela/Departamento
     * Solicitante
     */
    public void cargarUnidadesFacultadesSolicitante() {
        try {
            if (solicitante.getIdUnidad() != null) {
                for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                    if (us.getUnidadFacultad() == 'U') {
                        if (us.getId() == solicitante.getIdUnidad().getIdUnidad()) {
                            facultadesUnidades = us;
                        }
                    }
                }
            }
            if (solicitante.getIdEscuelaDepto() != null) {
                for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                    if (us.getUnidadFacultad() == 'F') {
                        if (us.getPrimary() == solicitante.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) {
                            facultadesUnidades = us;
                        }
                    }
                }
                Integer idEscuelaDepto = solicitante.getIdEscuelaDepto().getIdEscuelaDepto();
                this.escuelaDepartamento = escuelaDepartamentoService.findById(idEscuelaDepto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para precargar facultad / unidad o Escuela/Departamento
     * Solicitante Interno
     */
    public void cargarUnidadesFacultadesSolicitanteInterno() {
        try {
            if (referenteInterno.getIdUnidad() != null) {
                for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                    if (us.getUnidadFacultad() == 'U') {
                        if (us.getPrimary() == referenteInterno.getIdUnidad().getIdUnidad()) {
                            facultadesUnidadesInterno = us;
                        }
                    }
                }
            }

            if (referenteInterno.getIdEscuelaDepto() != null) {
                for (PojoFacultadesUnidades us : listaFacultadUnidad) {
                    if (us.getUnidadFacultad() == 'F') {
                        if (us.getPrimary() == referenteInterno.getIdEscuelaDepto().getIdFacultad().getIdFacultad() && us.getUnidadFacultad() == 'F') {
                            facultadesUnidadesInterno = us;
                        }
                    }
                }

                Integer idEscuelaDepto = referenteInterno.getIdEscuelaDepto().getIdEscuelaDepto();
                this.escuelaDepartamentoInterno = escuelaDepartamentoService.findById(idEscuelaDepto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo Utilizado para cargar Telefonos de personal interno al momento de
     * ediitar una propuesta
     */
    public void cargarTelefonosInternos() {
        try {
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(referenteInterno);

            for (Telefono tel : telefonosByPersona) {
                if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {                                       
                    telFijoInterno = tel;
                }
                if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                    telCelularInterno = tel;
                }
            }
            
            
        } catch (Exception e) {
        }
    }

    /**
     * Metodo Utilizado para cargar Telefonos de Solicitante al momento de
     * ediitar una propuesta
     */
    public void cargarTelefonosSolicitante() {
        try {
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(solicitante);
            for (Telefono tel : telefonosByPersona) {
                if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                    telFijoSolicitante = tel;
                }
                if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                    telCelularSolicitante = tel;
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Metodo Utilizado para cargar Telefonos de personal Externo al momento de
     * ediitar una propuesta
     */
    public void cargarTelefonosExterno() {
        try {
            List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(referenteExterno);

            for (Telefono tel : telefonosByPersona) {
                if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                    telFijoExterno = tel;
                }
                if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                    telCelularExterno = tel;
                }
            }
        } catch (Exception e) {
        }
    }

    public void onTipoConvenioChange() {
        try {

            if (propuestaConvenio.getIdTipoPropuestaConvenio().getNombrePropuestaConvenio().equalsIgnoreCase(CONVENIO_MARCO)) {
                flagConvenioMarco = true;
            } else {
                listadoPropuestaConvenio = propuestaConvenioService.getConvenios();
                flagConvenioMarco = false;
            }

            RequestContext.getCurrentInstance().update("formAdmin:idNamePropuesta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarPropuestaConvenio(int idPropuestaConvenio) {
        try {
            flagEdicion = true;
            propuestaConvenio = propuestaConvenioService.getPropuestaCovenioByID(idPropuestaConvenio);
            if (propuestaConvenio.getIdTipoPropuestaConvenio().getNombrePropuestaConvenio().equalsIgnoreCase(CONVENIO_MARCO)) {
                flagConvenioMarco = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para obtener arreglo de facultades y unidades
     *
     * @param facs
     * @param unidades
     * @return
     */
    private List<PojoFacultadesUnidades> getListFacultadesUnidades() {

        listaFacultadUnidad = new ArrayList<PojoFacultadesUnidades>();
        int i = 1;

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
     * Metodo para renderizar escuela o departamento despues de seleccionar
     * facultad para un solicitante
     */
    public void actualizarEscuelaDept() {
        try {

            if (facultadesUnidades.getUnidadFacultad() == 'U') {
                flagEscuelaDept = true;
            } else {
                flagEscuelaDept = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * * Metodo para renderizar escuela o departamento despues de seleccionar
     * facultad para un solicitanteInterno
     */
    public void actualizarEscuelaDeptInterno() {
        try {

            if (facultadesUnidadesInterno.getUnidadFacultad() == 'U') {
                flagEscuelaDeptInterno = true;
            } else {
                flagEscuelaDeptInterno = false;
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

    /**
     * metodo habilita el autoComplete para ingreso de busquedas de personas
     * internas
     */
    public void habilitarAutoInterno(AjaxBehaviorEvent event) {

        flagSearchDuiInterno = Boolean.FALSE;
        flagSearchNombreInterno = Boolean.FALSE;
        flagSearchEmailInterno = Boolean.FALSE;

        if (disableAutoInterno) {
            disableAutoInterno = Boolean.FALSE;
        }
        if (tipoBusquedaInterna.equalsIgnoreCase("doc")) {
            flagSearchDuiInterno = Boolean.TRUE;
        }
        if (tipoBusquedaInterna.equalsIgnoreCase("nombre")) {
            flagSearchNombreInterno = Boolean.TRUE;
        }
        if (tipoBusquedaInterna.equalsIgnoreCase("email")) {
            flagSearchEmailInterno = Boolean.TRUE;
        }

    }

    /**
     * metodo habilita el autoComplete para ingreso de busquedas de personas
     * Externas
     */
    public void habilitarAutoExterno(AjaxBehaviorEvent event) {

        flagSearchDuiExterno = Boolean.FALSE;
        flagSearchNombreExterno = Boolean.FALSE;
        flagSearchEmailExterno = Boolean.FALSE;

        if (disableAutoExterno) {
            disableAutoExterno = Boolean.FALSE;
        }
        if (tipoBusquedaExterna.equalsIgnoreCase("doc")) {
            flagSearchDuiExterno = Boolean.TRUE;
        }
        if (tipoBusquedaExterna.equalsIgnoreCase("nombre")) {
            flagSearchNombreExterno = Boolean.TRUE;
        }
        if (tipoBusquedaExterna.equalsIgnoreCase("email")) {
            flagSearchEmailExterno = Boolean.TRUE;
        }

    }

    /// test de email
    public void FileRead() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String to = "";
        String subject = "Test Send Email";
        String messages = "Henrry Culeu";
        ArrayList<String> listado = new ArrayList<String>();

//    SAXBuilder sax;
//    Document dconfig1=null;
        String MailAccount = "tgraduacion01@gmail.com";
        String PassMailAccount = "";
        String rootFileContent = "";

        try {
            mail = new JCMail();

            mail.setFrom(MailAccount);
            mail.setPassword("tragra01");
            mail.setTo(solicitante.getEmailPersona());
            mail.setSubject("Test GAMIL");
            mail.setMessage(messages);
            mail.SEND();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
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

    public String getTipoBusquedaInterna() {
        return tipoBusquedaInterna;
    }

    public void setTipoBusquedaInterna(String tipoBusquedaInterna) {
        this.tipoBusquedaInterna = tipoBusquedaInterna;
    }

    public String getTipoBusquedaExterna() {
        return tipoBusquedaExterna;
    }

    public void setTipoBusquedaExterna(String tipoBusquedaExterna) {
        this.tipoBusquedaExterna = tipoBusquedaExterna;
    }

    public Boolean getDisableAutoInterno() {
        return disableAutoInterno;
    }

    public void setDisableAutoInterno(Boolean disableAutoInterno) {
        this.disableAutoInterno = disableAutoInterno;
    }

    public List<Persona> getListAll() {
        return listAll;
    }

    public void setListAll(List<Persona> listAll) {
        this.listAll = listAll;
    }

    public Boolean getFlagSearchDuiInterno() {
        return flagSearchDuiInterno;
    }

    public void setFlagSearchDuiInterno(Boolean flagSearchDuiInterno) {
        this.flagSearchDuiInterno = flagSearchDuiInterno;
    }

    public Boolean getFlagSearchNombreInterno() {
        return flagSearchNombreInterno;
    }

    public void setFlagSearchNombreInterno(Boolean flagSearchNombreInterno) {
        this.flagSearchNombreInterno = flagSearchNombreInterno;
    }

    public Boolean getFlagSearchEmailInterno() {
        return flagSearchEmailInterno;
    }

    public void setFlagSearchEmailInterno(Boolean flagSearchEmailInterno) {
        this.flagSearchEmailInterno = flagSearchEmailInterno;
    }

    public Boolean getDisableAutoExterno() {
        return disableAutoExterno;
    }

    public void setDisableAutoExterno(Boolean disableAutoExterno) {
        this.disableAutoExterno = disableAutoExterno;
    }

    public Boolean getFlagSearchDuiExterno() {
        return flagSearchDuiExterno;
    }

    public void setFlagSearchDuiExterno(Boolean flagSearchDuiExterno) {
        this.flagSearchDuiExterno = flagSearchDuiExterno;
    }

    public Boolean getFlagSearchNombreExterno() {
        return flagSearchNombreExterno;
    }

    public void setFlagSearchNombreExterno(Boolean flagSearchNombreExterno) {
        this.flagSearchNombreExterno = flagSearchNombreExterno;
    }

    public Boolean getFlagSearchEmailExterno() {
        return flagSearchEmailExterno;
    }

    public void setFlagSearchEmailExterno(Boolean flagSearchEmailExterno) {
        this.flagSearchEmailExterno = flagSearchEmailExterno;
    }

    public List<EscuelaDepartamento> getListadoEscuelaDepartamentoInter() {
        return listadoEscuelaDepartamentoInter;
    }

    public void setListadoEscuelaDepartamentoInter(List<EscuelaDepartamento> listadoEscuelaDepartamentoInter) {
        this.listadoEscuelaDepartamentoInter = listadoEscuelaDepartamentoInter;
    }

    public boolean isMismoSol() {
        return mismoSol;
    }

    public void setMismoSol(boolean mismoSol) {
        this.mismoSol = mismoSol;
    }

    public List<Telefono> getListadoTelefonoReferenteSolicitante() {
        return listadoTelefonoReferenteSolicitante;
    }

    public void setListadoTelefonoReferenteSolicitante(List<Telefono> listadoTelefonoReferenteSolicitante) {
        this.listadoTelefonoReferenteSolicitante = listadoTelefonoReferenteSolicitante;
    }

    public Telefono getTelFijoSolicitante() {
        return telFijoSolicitante;
    }

    public void setTelFijoSolicitante(Telefono telFijoSolicitante) {
        this.telFijoSolicitante = telFijoSolicitante;
    }

    public Telefono getTelCelularSolicitante() {
        return telCelularSolicitante;
    }

    public void setTelCelularSolicitante(Telefono telCelularSolicitante) {
        this.telCelularSolicitante = telCelularSolicitante;
    }

}
