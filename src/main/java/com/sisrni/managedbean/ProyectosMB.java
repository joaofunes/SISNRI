///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sisrni.managedbean;
//
//import com.sisrni.model.AreaConocimiento;
//import com.sisrni.model.Facultad;
//import com.sisrni.model.FacultadProyecto;
//import com.sisrni.model.FacultadProyectoPK;
//import com.sisrni.model.Organismo;
//import com.sisrni.model.Pais;
//import com.sisrni.model.Persona;
//import com.sisrni.model.PersonaProyecto;
//import com.sisrni.model.PersonaProyectoPK;
//import com.sisrni.model.PropuestaConvenio;
//import com.sisrni.model.Proyecto;
//import com.sisrni.model.Telefono;
//import com.sisrni.model.TipoFacultad;
//import com.sisrni.model.TipoPersona;
//import com.sisrni.model.TipoProyecto;
//import com.sisrni.model.TipoTelefono;
//import com.sisrni.model.EscuelaDepartamento;
//import com.sisrni.model.Unidad;
//import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
//import com.sisrni.service.AreaConocimientoService;
//import com.sisrni.service.EscuelaDepartamentoService;
//import com.sisrni.service.FacultadProyectoService;
//import com.sisrni.service.FacultadService;
//import com.sisrni.service.OrganismoService;
//import com.sisrni.service.PaisService;
//import com.sisrni.service.PersonaProyectoService;
//import com.sisrni.service.PersonaService;
//import com.sisrni.service.PropuestaConvenioService;
//import com.sisrni.service.ProyectoService;
//import com.sisrni.service.RegionService;
//import com.sisrni.service.TelefonoService;
//import com.sisrni.service.TipoFacultadService;
//import com.sisrni.service.TipoPersonaService;
//import com.sisrni.service.TipoProyectoService;
//import com.sisrni.service.TipoTelefonoService;
//import com.sisrni.service.UnidadService;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.ExternalContext;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.inject.Named;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.context.WebApplicationContext;
//
///**
// *
// * @author Lillian
// */
//@Named(value = "proyectosMB")
//@Scope(WebApplicationContext.SCOPE_APPLICATION)
//
//public class ProyectosMB {
//
//    @Inject
//    ProyectoMB proyectoMB;
//    @Autowired
//    private AreaConocimientoService areaConocimientoService;
//    @Autowired
//    private ProyectoService proyectoService;
//    @Autowired
//    private PersonaService personaService;
//    @Autowired
//    private TipoProyectoService tipoProyectoService;
//    @Autowired
//    private FacultadService facultadService;
//    @Autowired
//    private UnidadService unidadService;
//    @Autowired
//    private TelefonoService telefonoService;
//    @Autowired
//    private PropuestaConvenioService propuestaConvenioService;
//    @Autowired
//    private TipoTelefonoService tipoTelefonoService;
//    @Autowired
//    private OrganismoService organismoService;
//    @Autowired
//    private TipoPersonaService tipoPersonaService;
//    @Autowired
//    private PersonaProyectoService personaProyectoService;
//    @Autowired
//    private PaisService paisService;
//    @Autowired
//    private RegionService regionService;
//    @Autowired
//    private TipoFacultadService tipoFacultadService;
//    @Autowired
//    private FacultadProyectoService facultadProyectoService;
//    @Autowired
//    private EscuelaDepartamentoService escuelaDepartamentoService;
//
////Definicion de objetos    
//    private Proyecto proyecto;
//    private PropuestaConvenio propuestaConvenio;
//    private Persona persona;
//    private Persona personaAsistente;
//    private Persona personaExterna;
//    private Persona personaBecario;
//    private Facultad facultad;
//    private Facultad facultadB;
//    private EscuelaDepartamento unidadProyecto;
//    private Organismo organismo;
//    private Facultad facultadAsistente;
//    private Facultad facultadCoordinador;
//    private PersonaProyecto personaProyecto;
//    private PersonaProyectoPK personaProyectoExtPK;
//    private String numDocumentoSol;
//    private String numDocumentoAsis;
//    private String numDocumentoRefExt;
//    private String numDocumentoBecario;
//    private int yearActual;
//    private String anio;
//    private FacultadProyectoPK facultadProyectoPK;
//    private FacultadProyectoPK facultadProyectoBeneficiadaPK;
//    private FacultadProyecto facultadProyecto;
//
////Definicion de listas
//    private List<Facultad> facultadList;
//    private List<AreaConocimiento> areaConocimientoList;
//    private List<Organismo> organismoProyList;
//    private List<TipoProyecto> tipoproyectolist;
//    private List<Unidad> unidadList;
//    private List<EscuelaDepartamento> unidadSolList;
//    private List<EscuelaDepartamento> unidadAsisList;
//    private List<PropuestaConvenio> propuestaConvenioList;
//    private List<Organismo> organismoList;
//    private List<Telefono> listadoTelefonoSol;
//    private List<Telefono> listadoTelefonoAsis;
//    private List<Telefono> listadoTelefonoRefExt;
//    private List<Pais> paisList;
//    private List<Pais> paisCooperanteList;
//    private List<Facultad> facultadBeneficiada;
//    private List<Facultad> facultadBeneficiadaList;
//    private List<Proyecto> proyectoList;
////Definicion de selected
//    private TipoProyecto proyectoSelected;
//    private String[] areaConocimientoSelected;
//    private String[] organismoProySelected;
//    private List<AreaConocimiento> areasConocimiento;
//    private List<Organismo> organismosProyecto;
//    private Facultad facultadSelected;
//    private String[] facultadBeneficiadaSelected;
//    private EscuelaDepartamento escuelaDeptoSelected;
//    private EscuelaDepartamento escuelaDeptoSelectedSol;
//    private EscuelaDepartamento escuelaDeptoSelectedAsis;
//    private PersonaProyecto personaProyectoAsis;
//    private PersonaProyecto personaProyectoExt;
//    private PersonaProyectoPK personaProyectoPK;
//    private PersonaProyectoPK personaProyectoAsisPK;
//    private PropuestaConvenio propuestaConvenioSelected;
//    private String facultadSelectedSol;
//    private String facultadSelectedAsis;
//    private String facultadSelectedExt;
//    private EscuelaDepartamento unidadSelectedSol;
//    private EscuelaDepartamento unidadSelectedAsis;
//    private EscuelaDepartamento unidadSelectedBecario;
//    private Organismo organismoSelected;
//    private Pais paisSelected;
//    private Pais paisCooperanteSelected;
////Telefono
////private Telefono telefono;
//    private Telefono telefonoSolFijo;
//    private Telefono telefonoSolFax;
//    private Telefono telefonoSolCel;
//    private Telefono telefonoAsisFijo;
//    private Telefono telefonoAsisFax;
//    private Telefono telefonoAsisCel;
//    private Telefono telefonoRefextFijo;
//    private Telefono telefonoRefextFax;
//    private Telefono telefonoRefextCel;
//    private TipoTelefono tipoTelefonoFax;
//    private TipoTelefono tipoTelefonoFijo;
//    private TipoTelefono tipoTelefonoCel;
//
//    private TipoPersona tipoPersonaSol;
//    private TipoPersona tipoPersonaAsis;
//    private TipoPersona tipoPersonaRefext;
//
//    private TipoFacultad tipoFacultad;
//    private TipoFacultad tipoFacultadB;
//
//    private static final String FIJO = "FIJO";
//    private static final String FAX = "FAX";
//    private static final String CELULAR = "CELULAR";
//    private int existeSol;
//    private int existeAsis;
//    private int existeRefExt;
//    Pais regiones;
//    public boolean mostrarmonto;
//    private List<PojoFacultadesUnidades> facultadesUnidadesList;
//    private String facultadSelectedPojoP;
//    private String facultadSelectedPojoSol;
//    private String facultadSelectedPojoAsis;
//    private List<EscuelaDepartamento> escuelaDeptoList;
//    private List<EscuelaDepartamento> escuelaDeptoListSol;
//    private List<EscuelaDepartamento> escuelaDeptoListAsis;
//    public boolean actualizar;
//
//    /**
//     * Creates a new instance of ProyectosMB
//     */
//    @PostConstruct
//    public void init() {
//        inicializador();
//    }
//
//    public void inicializador() {
//        //selected
//        proyectoSelected = new TipoProyecto();
//        facultadSelected = new Facultad();
//        escuelaDeptoSelected = new EscuelaDepartamento();
//        escuelaDeptoSelectedSol = new EscuelaDepartamento();
//        escuelaDeptoSelectedAsis = new EscuelaDepartamento();
//        propuestaConvenioSelected = new PropuestaConvenio();
//        facultadSelectedSol = "";
//        facultadSelectedAsis = "";
//        facultadSelectedExt = "";
//        unidadSelectedSol = new EscuelaDepartamento();
//        unidadSelectedAsis = new EscuelaDepartamento();
//        unidadSelectedBecario = new EscuelaDepartamento();
//        organismoSelected = new Organismo();
//        areasConocimiento = new ArrayList<AreaConocimiento>();
//        organismosProyecto = new ArrayList<Organismo>();
//        paisSelected = new Pais();
//        paisCooperanteSelected = new Pais();
//        //Listas
//        tipoproyectolist = tipoProyectoService.findAll();
//        areaConocimientoList = areaConocimientoService.findAll();
//        organismoProyList = organismoService.findAll();
//        //facultadList = new ArrayList<Facultad>();
//        facultadList = facultadService.getFacultadesByUniversidad(1);
//        propuestaConvenioList = propuestaConvenioService.findAll();
//        organismoList = organismoService.findAll();
//        listadoTelefonoSol = telefonoService.findAll();
//        listadoTelefonoAsis = telefonoService.findAll();
//        listadoTelefonoRefExt = telefonoService.findAll();
//        paisList = paisService.findAll();
//        paisCooperanteList = paisService.findAll();
//        facultadBeneficiadaList = facultadService.findAll();
//        facultadBeneficiada = new ArrayList<Facultad>();
//        unidadList = unidadService.findAll();
//        escuelaDeptoList = new ArrayList<EscuelaDepartamento>();
//        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//        escuelaDeptoListAsis = new ArrayList<EscuelaDepartamento>();
//        proyectoList = proyectoService.findAll();
//        //Objetos
//        proyecto = new Proyecto();
//        propuestaConvenio = new PropuestaConvenio();
//        facultad = new Facultad();
//        facultadB = new Facultad();
//        facultadAsistente = new Facultad();
//        facultadCoordinador = new Facultad();
//        //telefono = new Telefono();
//        telefonoSolFijo = new Telefono();
//        telefonoSolFax = new Telefono();
//        telefonoSolCel = new Telefono();
//        telefonoAsisFijo = new Telefono();
//        telefonoAsisFax = new Telefono();
//        telefonoAsisCel = new Telefono();
//        telefonoRefextFijo = new Telefono();
//        telefonoRefextFax = new Telefono();
//        telefonoRefextCel = new Telefono();
//        persona = new Persona();
//        personaAsistente = new Persona();
//        personaExterna = new Persona();
//        personaBecario = new Persona();
//        organismo = new Organismo();
//        personaProyecto = new PersonaProyecto();
//        personaProyectoAsis = new PersonaProyecto();
//        personaProyectoExt = new PersonaProyecto();
//        personaProyectoPK = new PersonaProyectoPK();
//        personaProyectoAsisPK = new PersonaProyectoPK();
//        personaProyectoExtPK = new PersonaProyectoPK();
//        regiones = new Pais();
//        numDocumentoAsis = "";
//        numDocumentoSol = "";
//        numDocumentoRefExt = "";
//        numDocumentoBecario = "";
//        facultadProyecto = new FacultadProyecto();
//        facultadProyectoPK = new FacultadProyectoPK();
//        facultadProyectoBeneficiadaPK = new FacultadProyectoPK();
//        facultadesUnidadesList = getListFacultadesUnidades(facultadList, unidadList);
//
//        // tipos telefonos
//        tipoTelefonoFax = tipoTelefonoService.getTipoByDesc("FAX");
//        tipoTelefonoFijo = tipoTelefonoService.getTipoByDesc("FIJO");
//        tipoTelefonoCel = tipoTelefonoService.getTipoByDesc("CELULAR");
//        // tipo persona
//        tipoPersonaSol = tipoPersonaService.getTipoPersonaByNombre("REFERENTE INTERNO");
//        tipoPersonaAsis = tipoPersonaService.getTipoPersonaByNombre("ASISTENTE DE COORDINADOR");
//        tipoPersonaRefext = tipoPersonaService.getTipoPersonaByNombre("REFERENTE EXTERNO");
//        //tipo facultad
//        tipoFacultad = tipoFacultadService.getTipoFacultadByNombre("INICIATIVA");
//        tipoFacultadB = tipoFacultadService.getTipoFacultadByNombre("BENEFICIADA");
//        //bandera
//        existeSol = 0;
//        existeAsis = 0;
//        existeRefExt = 0;
//        yearActual = getYearOfDate(new Date());
//        anio = "";
//        facultadSelectedPojoP = "";
//        facultadSelectedPojoSol = "";
//        facultadSelectedPojoAsis = "";
//        actualizar = false;
//    }
//
//    public void guardarProyecto() {
//        try {
//            proyecto.setIdTipoProyecto(tipoProyectoService.findById(proyectoSelected.getIdTipoProyecto()));
//            PropuestaConvenio propuesta = propuestaConvenioService.findById(propuestaConvenioSelected.getIdPropuesta());
//            if (propuesta == null) {
//            } else {
//                proyecto.setIdPropuestaConvenio(propuesta);
//            }
//
//            Pais paiscooperante = paisService.findById(paisCooperanteSelected.getIdPais());
//            proyecto.setIdPaisCooperante(paiscooperante);
//            proyecto.setAnioGestion(Integer.parseInt(anio.trim()));
//            //guardar proyecto
//            proyecto.setIdProyecto(0);
//            //Intermedia de proyecto y area de conocimiento
//            for (int i = 0; i < areaConocimientoSelected.length; i++) {
//                AreaConocimiento area = areaConocimientoService.findById(Integer.parseInt(areaConocimientoSelected[i]));
//                if (area != null) {
//                    areasConocimiento.add(area);
//                }
//            }
//            proyecto.setAreaConocimientoList(areasConocimiento);
//            // guardar organismo
//            for (int i = 0; i < organismoProySelected.length; i++) {
//                Organismo organismo = organismoService.findById(Integer.parseInt(organismoProySelected[i]));
//                if (organismo != null) {
//                    organismosProyecto.add(organismo);
//                }
//            }
//            proyecto.setOrganismoList(organismosProyecto);
//            //si selecciona facultad o unidad
//            String facultadArreglo[] = facultadSelectedPojoP.split(",");
//            if (facultadArreglo[1].equals("1")) {
//                Facultad facSelected = facultadService.findById(Integer.parseInt(facultadArreglo[0]));
//                proyecto.setIdFacultad(facSelected);
//            } else {
//                proyecto.setIdUnidad(Integer.parseInt(facultadArreglo[0]));
//            }
//            if (actualizar == true) {
//                proyectoService.merge(proyecto);
//            } else {
//                proyectoService.save(proyecto);
//            }
//            //guardar en tabla intermedia Facultad_Proyecto facultad beneficiada
//            for (int i = 0; i < facultadBeneficiadaSelected.length; i++) {
//                FacultadProyectoPK facultadBenPK = new FacultadProyectoPK();
//                FacultadProyecto facultadBen = new FacultadProyecto();
//                Facultad fac = facultadService.findById(Integer.parseInt(facultadBeneficiadaSelected[i]));
//                if (fac != null) {
//                    facultadBenPK.setIdFacultad(fac.getIdFacultad());
//                    facultadBenPK.setIdProyecto(proyecto.getIdProyecto());
//                    facultadBenPK.setIdTipoFacultad(tipoFacultadB.getIdTipoFacultad());
//                    facultadBen.setFacultadProyectoPK(facultadBenPK);
//                    facultadBen.setTipoFacultad(tipoFacultadB);
//                    facultadBen.setFacultad(fac);
//                    facultadBen.setProyecto(proyecto);
//                    proyecto.getFacultadProyectoList().add(facultadBen);
//                    if (actualizar == true) {
//                        facultadProyectoService.merge(facultadBen);
//                    } else {
//                        facultadProyectoService.save(facultadBen);
//                    }
//                }
//            }
//            //guardar persona solicitante
//            String facultadArregloSol[] = facultadSelectedPojoSol.split(",");
//            if (facultadArregloSol[1].equals("1")) {
//                EscuelaDepartamento escuelaselec = escuelaDepartamentoService.findById(escuelaDeptoSelectedSol.getIdEscuelaDepto());
//                persona.setIdEscuelaDepto(escuelaselec);
//            } else {
//                Unidad unidadSelectedSoli = unidadService.findById(Integer.parseInt(facultadArregloSol[0]));
//                persona.setIdUnidad(unidadSelectedSoli);
//            }
//            persona.setDuiPersona(numDocumentoSol);
//            persona.setPasaporte("-");
//            persona.setExtranjero(Boolean.FALSE);
//            persona.setActivo(Boolean.TRUE);
//            //guardar telefono fijo solicitante
//            telefonoSolFijo.setIdPersona(persona);
//            telefonoSolFijo.setIdTipoTelefono(tipoTelefonoFijo);
//            persona.getTelefonoList().add(telefonoSolFijo);
//            //guardar telefono celular solicitante
//            telefonoSolCel.setIdPersona(persona);
//            telefonoSolCel.setIdTipoTelefono(tipoTelefonoCel);
//            persona.getTelefonoList().add(telefonoSolCel);
//            //guardar telefono fax solicitante
//            telefonoSolFax.setIdPersona(persona);
//            telefonoSolFax.setIdTipoTelefono(tipoTelefonoFax);
//            persona.getTelefonoList().add(telefonoSolFax);
//            if (existeSol == 1 || actualizar == true) {
//                personaService.merge(persona);
//            } else {
//                personaService.save(persona);
//            }
//            //guardar en tabla intermedia persona_proyecto de un solicitante 
//            personaProyectoPK.setIdPersona(persona.getIdPersona());
//            personaProyectoPK.setIdProyecto(proyecto.getIdProyecto());
////                    personaProyectoPK.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());
//
//            /////////////        
//            personaProyecto.setProyecto(proyecto);
//            personaProyecto.setPersona(persona);
//            personaProyecto.setIdTipoPersona(tipoPersonaSol);
//            personaProyecto.setPersonaProyectoPK(personaProyectoPK);
//            proyecto.getPersonaProyectoList().add(personaProyecto);
//            proyectoService.merge(proyecto);
//
//            //guardar persona Asistente
//            String facultadArregloAsis[] = facultadSelectedPojoAsis.split(",");
//            if (facultadArregloAsis[1].equals("1")) {
//                EscuelaDepartamento escuelaselecAsis = escuelaDepartamentoService.findById(escuelaDeptoSelectedAsis.getIdEscuelaDepto());
//                personaAsistente.setIdEscuelaDepto(escuelaselecAsis);
//            } else {
//                Unidad unidadSelectedAsist = unidadService.findById(Integer.parseInt(facultadArregloAsis[0]));
//                personaAsistente.setIdUnidad(unidadSelectedAsist);
//            }
//            personaAsistente.setDuiPersona(numDocumentoAsis);
//            personaAsistente.setPasaporte("-");
//            personaAsistente.setExtranjero(Boolean.FALSE);
//            personaAsistente.setActivo(Boolean.TRUE);
//            //guardar telefono fijo asistente
//            telefonoAsisFijo.setIdPersona(personaAsistente);
//            telefonoAsisFijo.setIdTipoTelefono(tipoTelefonoFijo);
//            personaAsistente.getTelefonoList().add(telefonoAsisFijo);
//            //guardar telefono celular asistente
//            telefonoAsisCel.setIdPersona(personaAsistente);
//            telefonoAsisCel.setIdTipoTelefono(tipoTelefonoCel);
//            personaAsistente.getTelefonoList().add(telefonoAsisCel);
//            // guardar fax Asistente
//            telefonoAsisFax.setIdPersona(personaAsistente);
//            telefonoAsisFax.setIdTipoTelefono(tipoTelefonoFax);
//            personaAsistente.getTelefonoList().add(telefonoAsisFax);
//            if (existeAsis == 1 || actualizar == true) {
//                personaService.merge(personaAsistente);
//            } else {
//                personaService.save(personaAsistente);
//            }
//            //guardar en tabla intermedia persona_proyecto Asistente
//            //PersonaProyectoPK personaProyectoPK = new PersonaProyectoPK();            
//            personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
//            personaProyectoAsisPK.setIdProyecto(proyecto.getIdProyecto());
////                    personaProyectoAsisPK.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());
//            /////////////        
//            personaProyectoAsis.setProyecto(proyecto);
//            personaProyectoAsis.setPersona(personaAsistente);
//            personaProyectoAsis.setIdTipoPersona(tipoPersonaAsis);
//            personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
//            proyecto.getPersonaProyectoList().add(personaProyectoAsis);
//            proyectoService.merge(proyecto);
//            //personaProyectoService.save(personaProyectoAsis);
//
//            //Guardar informacion de referente externo
////                    personaExterna.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
//            Organismo organismoExt = organismoService.findById(organismoSelected.getIdOrganismo());
//            personaExterna.setIdOrganismo(organismoExt);
//            personaExterna.setPasaporte(numDocumentoRefExt);
//            personaExterna.setDuiPersona("-");
//            personaExterna.setExtranjero(Boolean.TRUE);
//            personaExterna.setActivo(Boolean.TRUE);
//            //guardar telefono fijo persona externa
//            telefonoRefextFijo.setIdPersona(personaExterna);
//            telefonoRefextFijo.setIdTipoTelefono(tipoTelefonoFijo);
//            personaExterna.getTelefonoList().add(telefonoRefextFijo);
//            //guardar telefono fijo persona externa
//            telefonoRefextCel.setIdPersona(personaExterna);
//            telefonoRefextCel.setIdTipoTelefono(tipoTelefonoCel);
//            personaExterna.getTelefonoList().add(telefonoRefextCel);
//            //guardar fax persona externa
//            telefonoRefextFax.setIdPersona(personaExterna);
//            telefonoRefextFax.setIdTipoTelefono(tipoTelefonoFax);
//            personaExterna.getTelefonoList().add(telefonoRefextFax);
//            if (existeRefExt == 1 || actualizar == true) {
//                personaService.merge(personaExterna);
//            } else {
//                personaService.save(personaExterna);
//            }
//            //guardar en tabla intermedia persona_proyecto Referente externo
//            personaProyectoExtPK.setIdPersona(personaExterna.getIdPersona());
//            personaProyectoExtPK.setIdProyecto(proyecto.getIdProyecto());
////                    personaProyectoExtPK.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
//            //////
//            personaProyectoExt.setProyecto(proyecto);
//            personaProyectoExt.setPersona(personaExterna);
//            personaProyectoExt.setIdTipoPersona(tipoPersonaRefext);
//            personaProyectoExt.setPersonaProyectoPK(personaProyectoExtPK);
//            proyecto.getPersonaProyectoList().add(personaProyectoExt);
//            proyectoService.merge(proyecto);
//            //personaProyectoService.save(personaProyectoExt);
//
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));
//        }
//        inicializador();
//    }
//
//    private List<PojoFacultadesUnidades> getListFacultadesUnidades(List<Facultad> facs, List<Unidad> unidades) {
//
//        List<PojoFacultadesUnidades> lista = new ArrayList<PojoFacultadesUnidades>();
//        for (Facultad fac : facs) {
//            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();
//            pojo.setValue(fac.getIdFacultad() + ",1");
//            pojo.setLabel(fac.getNombreFacultad());
//            lista.add(pojo);
//        }
//        for (Unidad uni : unidades) {
//            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();
//            pojo.setValue(uni.getIdUnidad() + ",2");
//            pojo.setLabel(uni.getNombreUnidad());
//            lista.add(pojo);
//        }
//        return lista;
//    }
//
////    public void onFacultadChange() {
////        facultadSelectedPojoP.trim();
////        String facultadArreglo[] = facultadSelectedPojoP.split(",");
////        if (facultadArreglo[1].equals("1")) {
////            escuelaDeptoList = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadArreglo[0]));
////
////        } else {
////            int band = 1;
////        }
////    }
////
//    public void onFacultadSolicitanteChange() {
//        facultadSelectedPojoSol.trim();
//        String facultadArreglo[] = facultadSelectedPojoSol.split(",");
//        if (facultadArreglo[1].equals("1")) {
//            escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadArreglo[0]));
//
//        } else {
//            int bandSol = 1;
//        }
//    }
//
//    public void onFacultadAsistenteChange() {
//        facultadSelectedPojoAsis.trim();
//        String facultadArreglo[] = facultadSelectedPojoAsis.split(",");
//        if (facultadArreglo[1].equals("1")) {
//            escuelaDeptoListAsis = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadArreglo[0]));
//
//        } else {
//            int bandAsis = 1;
//        }
//    }
//
////Buscando a persona  Solicitante existente
//    public void searchByDocInterno(String numDocumentoSol) {
//        try {
//            if (numDocumentoSol != null) {
//                Persona auxSol=new Persona();
//                //auxSol=persona;
//                auxSol = personaService.getPersonaByDui(numDocumentoSol);
//                if (auxSol != null) {
//                    existeSol = 1;
//                    persona=auxSol;
//                    if (persona.getIdUnidad() == null || persona.getIdEscuelaDepto() == null) {
//                        facultadSelected = new Facultad();
//                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                    }
//                    if (persona.getIdEscuelaDepto() != null) {
//                        facultadSelectedPojoSol = persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
//                        escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
//                        escuelaDeptoSelectedSol = persona.getIdEscuelaDepto();
//                    }
//                    if (persona.getIdUnidad() != null) {
//                        facultadSelectedPojoSol = persona.getIdUnidad().getIdUnidad() + ",2";
//                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                    }
//                    onChangeInterno();
//                } else {
//                    persona = new Persona();
//                    escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onChangeInterno() {
//        try {
//            listadoTelefonoSol = persona.getTelefonoList();
//
//            for (Telefono us : listadoTelefonoSol) {
//
//                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telefonoSolFijo = us;
//                }
//                if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
//                    telefonoSolCel = us;
//                }
//                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    telefonoSolFax = us;
//                }
//            }
//        } catch (Exception e) {
//        }
//    }//Fin Buscando a persona  Solicitante existente 
//
////Buscando si existe persona asistente
//    public void searchByDocAsistente(String numDocumentoAsis) {
//        try {
//
//            if (numDocumentoAsis != null) {
//                Persona auxAsis=new Persona();
//                //auxAsis=personaAsistente;
//                auxAsis = personaService.getPersonaByDui(numDocumentoAsis);
//                if (auxAsis != null) {
//                    existeAsis = 1;
//                    personaAsistente=auxAsis;
//                    if (personaAsistente.getIdUnidad() == null || personaAsistente.getIdEscuelaDepto() == null) {
//                        facultadSelected = new Facultad();
//                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                    }
//                    if (personaAsistente.getIdEscuelaDepto() != null) {
//                        facultadSelectedPojoSol = personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
//                        escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
//                        escuelaDeptoSelectedSol = personaAsistente.getIdEscuelaDepto();
//                    }
//                    if (personaAsistente.getIdUnidad() != null) {
//                        facultadSelectedPojoSol = personaAsistente.getIdUnidad().getIdUnidad() + ",2";
//                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                    }
//                    onChangeAsistente();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onChangeAsistente() {
//        try {
//            listadoTelefonoAsis = telefonoService.getTelefonosByPersona(personaAsistente);
//
//            for (Telefono us : listadoTelefonoAsis) {
//
//                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telefonoAsisFijo = us;
//                }
//                if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
//                    telefonoAsisCel = us;
//                }
//                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    telefonoAsisFax = us;
//                }
//            }
//        } catch (Exception e) {
//        }
//    }
//    //Fin busca de persona asistente
//
////Buscando si existe persona Referente externo
//    public void searchByDocReferenteExterno(String numDocumentoRefExt) {
//        try {
//
//            if (numDocumentoRefExt != null) {
//                Persona auxExt=new Persona();
//                //auxExt=personaExterna;
//                auxExt = personaService.getPersonaByDui(numDocumentoRefExt);
//                if (auxExt != null) {
//                    existeRefExt = 1;
//                    personaExterna=auxExt;
//                    if (personaExterna.getIdUnidad() == null || personaExterna.getIdEscuelaDepto() == null) {
//                        facultadSelected = new Facultad();
//                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                    }
//                    if (personaExterna.getIdEscuelaDepto() != null) {
//                        facultadSelectedPojoSol = personaExterna.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
//                        escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaExterna.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
//                        escuelaDeptoSelectedSol = personaExterna.getIdEscuelaDepto();
//                    }
//                    if (personaExterna.getIdUnidad() != null) {
//                        facultadSelectedPojoSol = personaExterna.getIdUnidad().getIdUnidad() + ",2";
//                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
//                    }
//                    onChangeReferenteExterno();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onChangeReferenteExterno() {
//        try {
//            listadoTelefonoRefExt = telefonoService.getTelefonosByPersona(personaExterna);
//
//            for (Telefono us : listadoTelefonoRefExt) {
//
//                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telefonoRefextFijo = us;
//                }
//                if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
//                    telefonoRefextCel = us;
//                }
//                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    telefonoRefextFax = us;
//                }
//            }
//        } catch (Exception e) {
//        }
//    }
//    //Fin busca de persona Referente externo
//
//    private Integer getYearOfDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        Integer year = cal.get(Calendar.YEAR);
//        return year;
//    }
//
//    public String preUpdateProyecto() {
//        try {
//
//            FacesContext.getCurrentInstance().getExternalContext().redirect("registrarProyecto.xhtml");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public void regresar() {
//        try {
//            inicializador();
//
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//
//            String outcome = "proyectoAdm.xhtml";
//            FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
//        } catch (Exception e) {
//        }
//    }
//
//    public void preUpdateProyecto(Integer id) {
//        try {
//            Proyecto proyectoactual = proyectoService.findById(id);
//            if (proyectoactual != null) {
//                proyecto=proyectoactual;
//                persona = getPersonaProyecto(proyecto.getPersonaProyectoList(), "REFERENTE INTERNO");
//                personaAsistente = getPersonaProyecto(proyecto.getPersonaProyectoList(), "ASISTENTE DE COORDINADOR");
//                personaExterna = getPersonaProyecto(proyecto.getPersonaProyectoList(), "REFERENTE EXTERNO");
//                if (proyecto.getIdFacultad() == null) {
//                    facultadSelectedPojoP = proyecto.getIdUnidad() + "2";
//                } else {
//                    facultadSelectedPojoP = proyecto.getIdFacultad() + "1";
//                }
//                searchByDocInterno(persona.getDuiPersona());
//                searchByDocAsistente(personaAsistente.getDuiPersona());
//                searchByDocReferenteExterno(personaExterna.getPasaporte());
//                proyectoSelected = proyecto.getIdTipoProyecto();
//                propuestaConvenioSelected = proyecto.getIdPropuestaConvenio();
//                paisCooperanteSelected = proyecto.getIdPaisCooperante();
//                cargarOrganismos(proyecto.getOrganismoList());
//                cargarAreas(proyecto.getAreaConocimientoList());
//                cargarFacultadesBeneficiadas(proyecto.getFacultadProyectoList());
//                actualizar = Boolean.TRUE;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    //metodo para cargar facultades beneficiadas
//
//    public void cargarFacultadesBeneficiadas(List<FacultadProyecto> facultadProyectoList) {
//        FacultadProyecto fac = new FacultadProyecto();
//        for (int i = 0; i < facultadProyectoList.size(); i++) {
//            fac = facultadProyectoList.get(i);
//            facultadBeneficiadaSelected[i] = fac.getFacultad().getIdFacultad().toString();
//        }
//    }
//
//    //metodo para cargar areas de conocimiento
//    public void cargarAreas(List<AreaConocimiento> areaConocimientos) {
//        AreaConocimiento areaCono = new AreaConocimiento();
//        for (int i = 0; i < areaConocimientos.size(); i++) {
//            areaCono = areaConocimientos.get(i);
//            areaConocimientoSelected[i] = areaCono.getIdAreaConocimiento().toString();
//        }
//    }
//
//    //metodo para cargar organismos
//    public void cargarOrganismos(List<Organismo> organismosProyecto) {
//        Organismo org = new Organismo();
//        List<String> listaOrganismos;
//        listaOrganismos=new ArrayList<String>();
//        organismoProySelected = new String[organismosProyecto.size()];
//                
//        for (int i = 0; i < organismosProyecto.size(); i++) {
//            org = organismosProyecto.get(i);
//            organismoProySelected[i] = org.getIdOrganismo().toString();
//        }
//    }
//    //metodo para obtener a las personas del proyecto ingresado
//
//    public Persona getPersonaProyecto(List<PersonaProyecto> lista, String tipo) {
//        Persona p = new Persona();
//        for (PersonaProyecto per : lista) {
//            if (per.getIdTipoPersona().getNombre().equalsIgnoreCase(tipo)) {
//                return personaService.getByID(per.getPersona().getIdPersona());
//
//            }
//        }
//        return p;
//    }
//
//    public TipoProyecto getProyectoSelected() {
//        return proyectoSelected;
//    }
//
//    public void setProyectoSelected(TipoProyecto proyectoSelected) {
//        this.proyectoSelected = proyectoSelected;
//    }
//
//    public List<TipoProyecto> getTipoproyectolist() {
//        return tipoproyectolist;
//    }
//
//    public void setTipoproyectolist(List<TipoProyecto> tipoproyectolist) {
//        this.tipoproyectolist = tipoproyectolist;
//    }
//
//    public Proyecto getProyecto() {
//        return proyecto;
//    }
//
//    public void setProyecto(Proyecto proyecto) {
//        this.proyecto = proyecto;
//    }
//
//    public Persona getPersona() {
//        return persona;
//    }
//
//    public void setPersona(Persona persona) {
//        this.persona = persona;
//    }
//
//    public Telefono getTelefonoSolFijo() {
//        return telefonoSolFijo;
//    }
//
//    public void setTelefonoSolFijo(Telefono telefonoSolFijo) {
//        this.telefonoSolFijo = telefonoSolFijo;
//    }
//
//    public Telefono getTelefonoSolFax() {
//        return telefonoSolFax;
//    }
//
//    public void setTelefonoSolFax(Telefono telefonoSolFax) {
//        this.telefonoSolFax = telefonoSolFax;
//    }
//
//    public Telefono getTelefonoAsisFijo() {
//        return telefonoAsisFijo;
//    }
//
//    public void setTelefonoAsisFijo(Telefono telefonoAsisFijo) {
//        this.telefonoAsisFijo = telefonoAsisFijo;
//    }
//
//    public Telefono getTelefonoAsisFax() {
//        return telefonoAsisFax;
//    }
//
//    public void setTelefonoAsisFax(Telefono telefonoAsisFax) {
//        this.telefonoAsisFax = telefonoAsisFax;
//    }
//
//    public Telefono getTelefonoRefextFijo() {
//        return telefonoRefextFijo;
//    }
//
//    public void setTelefonoRefextFijo(Telefono telefonoRefextFijo) {
//        this.telefonoRefextFijo = telefonoRefextFijo;
//    }
//
//    public Telefono getTelefonoRefextFax() {
//        return telefonoRefextFax;
//    }
//
//    public void setTelefonoRefextFax(Telefono telefonoRefextFax) {
//        this.telefonoRefextFax = telefonoRefextFax;
//    }
//
//    public Persona getPersonaAsistente() {
//        return personaAsistente;
//    }
//
//    public void setPersonaAsistente(Persona personaAsistente) {
//        this.personaAsistente = personaAsistente;
//    }
//
//    public Persona getPersonaExterna() {
//        return personaExterna;
//    }
//
//    public void setPersonaExterna(Persona personaExterna) {
//        this.personaExterna = personaExterna;
//    }
//
//    public Facultad getFacultad() {
//        return facultad;
//    }
//
//    public void setFacultad(Facultad facultad) {
//        this.facultad = facultad;
//    }
//
//    public Facultad getFacultadAsistente() {
//        return facultadAsistente;
//    }
//
//    public void setFacultadAsistente(Facultad facultadAsistente) {
//        this.facultadAsistente = facultadAsistente;
//    }
//
//    public String getFacultadSelectedAsis() {
//        return facultadSelectedAsis;
//    }
//
//    public void setFacultadSelectedAsis(String facultadSelectedAsis) {
//        this.facultadSelectedAsis = facultadSelectedAsis;
//    }
//
//    public String getFacultadSelectedExt() {
//        return facultadSelectedExt;
//    }
//
//    public void setFacultadSelectedExt(String facultadSelectedExt) {
//        this.facultadSelectedExt = facultadSelectedExt;
//    }
//
//    public EscuelaDepartamento getUnidadSelectedSol() {
//        return unidadSelectedSol;
//    }
//
//    public void setUnidadSelectedSol(EscuelaDepartamento unidadSelectedSol) {
//        this.unidadSelectedSol = unidadSelectedSol;
//    }
//
//    public EscuelaDepartamento getUnidadSelectedAsis() {
//        return unidadSelectedAsis;
//    }
//
//    public void setUnidadSelectedAsis(EscuelaDepartamento unidadSelectedAsis) {
//        this.unidadSelectedAsis = unidadSelectedAsis;
//    }
//
//    public Organismo getOrganismo() {
//        return organismo;
//    }
//
//    public void setOrganismo(Organismo organismo) {
//        this.organismo = organismo;
//    }
//
//    public TipoTelefono getTipoTelefonoFax() {
//        return tipoTelefonoFax;
//    }
//
//    public void setTipoTelefonoFax(TipoTelefono tipoTelefonoFax) {
//        this.tipoTelefonoFax = tipoTelefonoFax;
//    }
//
//    public TipoTelefono getTipoTelefonoFijo() {
//        return tipoTelefonoFijo;
//    }
//
//    public void setTipoTelefonoFijo(TipoTelefono tipoTelefonoFijo) {
//        this.tipoTelefonoFijo = tipoTelefonoFijo;
//    }
//
//    public PersonaProyecto getPersonaProyecto() {
//        return personaProyecto;
//    }
//
//    public void setPersonaProyecto(PersonaProyecto personaProyecto) {
//        this.personaProyecto = personaProyecto;
//    }
//
//    public TipoPersona getTipoPersonaSol() {
//        return tipoPersonaSol;
//    }
//
//    public void setTipoPersonaSol(TipoPersona tipoPersonaSol) {
//        this.tipoPersonaSol = tipoPersonaSol;
//    }
//
//    public TipoPersona getTipoPersonaAsis() {
//        return tipoPersonaAsis;
//    }
//
//    public void setTipoPersonaAsis(TipoPersona tipoPersonaAsis) {
//        this.tipoPersonaAsis = tipoPersonaAsis;
//    }
//
//    public TipoPersona getTipoPersonaRefext() {
//        return tipoPersonaRefext;
//    }
//
//    public void setTipoPersonaRefext(TipoPersona tipoPersonaRefext) {
//        this.tipoPersonaRefext = tipoPersonaRefext;
//    }
//
//    public List<Organismo> getOrganismoList() {
//        return organismoList;
//    }
//
//    public void setOrganismoList(List<Organismo> organismoList) {
//        this.organismoList = organismoList;
//    }
//
//    public Organismo getOrganismoSelected() {
//        return organismoSelected;
//    }
//
//    public void setOrganismoSelected(Organismo organismoSelected) {
//        this.organismoSelected = organismoSelected;
//    }
//
//    public String[] getAreaConocimientoSelected() {
//        return areaConocimientoSelected;
//    }
//
//    public void setAreaConocimientoSelected(String[] areaConocimientoSelected) {
//        this.areaConocimientoSelected = areaConocimientoSelected;
//    }
//
//    public List<AreaConocimiento> getAreaConocimientoList() {
//        return areaConocimientoList;
//    }
//
//    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
//        this.areaConocimientoList = areaConocimientoList;
//    }
//
//    public Facultad getFacultadSelected() {
//        return facultadSelected;
//    }
//
//    public void setFacultadSelected(Facultad facultadSelected) {
//        this.facultadSelected = facultadSelected;
//    }
//
//    public List<Facultad> getFacultadList() {
//        return facultadList;
//    }
//
//    public void setFacultadList(List<Facultad> facultadList) {
//        this.facultadList = facultadList;
//    }
//
//    public String getFacultadSelectedSol() {
//        return facultadSelectedSol;
//    }
//
//    public void setFacultadSelectedSol(String facultadSelectedSol) {
//        this.facultadSelectedSol = facultadSelectedSol;
//    }
//
//    public EscuelaDepartamento getUnidadProyecto() {
//        return unidadProyecto;
//    }
//
//    public void setUnidadProyecto(EscuelaDepartamento unidadProyecto) {
//        this.unidadProyecto = unidadProyecto;
//    }
//
//    public EscuelaDepartamento getEscuelaDeptoSelected() {
//        return escuelaDeptoSelected;
//    }
//
//    public void setEscuelaDeptoSelected(EscuelaDepartamento escuelaDeptoSelected) {
//        this.escuelaDeptoSelected = escuelaDeptoSelected;
//    }
//
//    public List<Unidad> getUnidadList() {
//        return unidadList;
//    }
//
//    public void setUnidadList(List<Unidad> unidadList) {
//        this.unidadList = unidadList;
//    }
//
//    public List<EscuelaDepartamento> getUnidadSolList() {
//        return unidadSolList;
//    }
//
//    public void setUnidadSolList(List<EscuelaDepartamento> unidadSolList) {
//        this.unidadSolList = unidadSolList;
//    }
//
//    public List<EscuelaDepartamento> getUnidadAsisList() {
//        return unidadAsisList;
//    }
//
//    public void setUnidadAsisList(List<EscuelaDepartamento> unidadAsisList) {
//        this.unidadAsisList = unidadAsisList;
//    }
//
//    public PropuestaConvenio getPropuestaConvenio() {
//        return propuestaConvenio;
//    }
//
//    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
//        this.propuestaConvenio = propuestaConvenio;
//    }
//
//    public List<PropuestaConvenio> getPropuestaConvenioList() {
//        return propuestaConvenioList;
//    }
//
//    public void setPropuestaConvenioList(List<PropuestaConvenio> propuestaConvenioList) {
//        this.propuestaConvenioList = propuestaConvenioList;
//    }
//
//    public PropuestaConvenio getPropuestaConvenioSelected() {
//        return propuestaConvenioSelected;
//    }
//
//    public void setPropuestaConvenioSelected(PropuestaConvenio propuestaConvenioSelected) {
//        this.propuestaConvenioSelected = propuestaConvenioSelected;
//    }
//
//    public List<AreaConocimiento> getAreasConocimiento() {
//        return areasConocimiento;
//    }
//
//    public void setAreasConocimiento(List<AreaConocimiento> areasConocimiento) {
//        this.areasConocimiento = areasConocimiento;
//    }
//
//    public String getNumDocumentoSol() {
//        return numDocumentoSol;
//    }
//
//    public void setNumDocumentoSol(String numDocumentoSol) {
//        this.numDocumentoSol = numDocumentoSol;
//    }
//
//    public String getNumDocumentoAsis() {
//        return numDocumentoAsis;
//    }
//
//    public void setNumDocumentoAsis(String numDocumentoAsis) {
//        this.numDocumentoAsis = numDocumentoAsis;
//    }
//
//    public String getNumDocumentoRefExt() {
//        return numDocumentoRefExt;
//    }
//
//    public void setNumDocumentoRefExt(String numDocumentoRefExt) {
//        this.numDocumentoRefExt = numDocumentoRefExt;
//    }
//
//    public List<Telefono> getListadoTelefonoSol() {
//        return listadoTelefonoSol;
//    }
//
//    public void setListadoTelefonoSol(List<Telefono> listadoTelefonoSol) {
//        this.listadoTelefonoSol = listadoTelefonoSol;
//    }
//
//    public List<Pais> getPaisList() {
//        return paisList;
//    }
//
//    public void setPaisList(List<Pais> paisList) {
//        this.paisList = paisList;
//    }
//
//    public Pais getPaisSelected() {
//        return paisSelected;
//    }
//
//    public void setPaisSelected(Pais paisSelected) {
//        this.paisSelected = paisSelected;
//    }
//
//    public Persona getPersonaBecario() {
//        return personaBecario;
//    }
//
//    public void setPersonaBecario(Persona personaBecario) {
//        this.personaBecario = personaBecario;
//    }
//
//    public String getNumDocumentoBecario() {
//        return numDocumentoBecario;
//    }
//
//    public void setNumDocumentoBecario(String numDocumentoBecario) {
//        this.numDocumentoBecario = numDocumentoBecario;
//    }
//
//    public EscuelaDepartamento getUnidadSelectedBecario() {
//        return unidadSelectedBecario;
//    }
//
//    public void setUnidadSelectedBecario(EscuelaDepartamento unidadSelectedBecario) {
//        this.unidadSelectedBecario = unidadSelectedBecario;
//    }
//
//    public int getYearActual() {
//        return yearActual;
//    }
//
//    public void setYearActual(int yearActual) {
//        this.yearActual = yearActual;
//    }
//
//    public String getAnio() {
//        return anio;
//    }
//
//    public void setAnio(String anio) {
//        this.anio = anio;
//    }
//
//    public Pais getRegiones() {
//        return regiones;
//    }
//
//    public void setRegiones(Pais regiones) {
//        this.regiones = regiones;
//    }
//
//    public List<Organismo> getOrganismoProyList() {
//        return organismoProyList;
//    }
//
//    public void setOrganismoProyList(List<Organismo> organismoProyList) {
//        this.organismoProyList = organismoProyList;
//    }
//
//    public String[] getOrganismoProySelected() {
//        return organismoProySelected;
//    }
//
//    public void setOrganismoProySelected(String[] organismoProySelected) {
//        this.organismoProySelected = organismoProySelected;
//    }
//
//    public List<Organismo> getOrganismosProyecto() {
//        return organismosProyecto;
//    }
//
//    public void setOrganismosProyecto(List<Organismo> organismosProyecto) {
//        this.organismosProyecto = organismosProyecto;
//    }
//
//    public List<Pais> getPaisCooperanteList() {
//        return paisCooperanteList;
//    }
//
//    public void setPaisCooperanteList(List<Pais> paisCooperanteList) {
//        this.paisCooperanteList = paisCooperanteList;
//    }
//
//    public Pais getPaisCooperanteSelected() {
//        return paisCooperanteSelected;
//    }
//
//    public void setPaisCooperanteSelected(Pais paisCooperanteSelected) {
//        this.paisCooperanteSelected = paisCooperanteSelected;
//    }
//
//    public boolean isMostrarmonto() {
//        return mostrarmonto;
//    }
//
//    public void setMostrarmonto(boolean mostrarmonto) {
//        this.mostrarmonto = mostrarmonto;
//    }
//
//    public ProyectoMB getProyectoMB() {
//        return proyectoMB;
//    }
//
//    public void setProyectoMB(ProyectoMB proyectoMB) {
//        this.proyectoMB = proyectoMB;
//    }
//
//    public List<Facultad> getFacultadBeneficiada() {
//        return facultadBeneficiada;
//    }
//
//    public void setFacultadBeneficiada(List<Facultad> facultadBeneficiada) {
//        this.facultadBeneficiada = facultadBeneficiada;
//    }
//
//    public List<Facultad> getFacultadBeneficiadaList() {
//        return facultadBeneficiadaList;
//    }
//
//    public void setFacultadBeneficiadaList(List<Facultad> facultadBeneficiadaList) {
//        this.facultadBeneficiadaList = facultadBeneficiadaList;
//    }
//
//    public String[] getFacultadBeneficiadaSelected() {
//        return facultadBeneficiadaSelected;
//    }
//
//    public void setFacultadBeneficiadaSelected(String[] facultadBeneficiadaSelected) {
//        this.facultadBeneficiadaSelected = facultadBeneficiadaSelected;
//    }
//
//    public Telefono getTelefonoSolCel() {
//        return telefonoSolCel;
//    }
//
//    public void setTelefonoSolCel(Telefono telefonoSolCel) {
//        this.telefonoSolCel = telefonoSolCel;
//    }
//
//    public Telefono getTelefonoAsisCel() {
//        return telefonoAsisCel;
//    }
//
//    public void setTelefonoAsisCel(Telefono telefonoAsisCel) {
//        this.telefonoAsisCel = telefonoAsisCel;
//    }
//
//    public Telefono getTelefonoRefextCel() {
//        return telefonoRefextCel;
//    }
//
//    public void setTelefonoRefextCel(Telefono telefonoRefextCel) {
//        this.telefonoRefextCel = telefonoRefextCel;
//    }
//
//    public Facultad getFacultadB() {
//        return facultadB;
//    }
//
//    public void setFacultadB(Facultad facultadB) {
//        this.facultadB = facultadB;
//    }
//
//    public Facultad getFacultadCoordinador() {
//        return facultadCoordinador;
//    }
//
//    public void setFacultadCoordinador(Facultad facultadCoordinador) {
//        this.facultadCoordinador = facultadCoordinador;
//    }
//
//    public List<PojoFacultadesUnidades> getFacultadesUnidadesList() {
//        return facultadesUnidadesList;
//    }
//
//    public void setFacultadesUnidadesList(List<PojoFacultadesUnidades> facultadesUnidadesList) {
//        this.facultadesUnidadesList = facultadesUnidadesList;
//    }
//
//    public String getFacultadSelectedPojoP() {
//        return facultadSelectedPojoP;
//    }
//
//    public void setFacultadSelectedPojoP(String facultadSelectedPojoP) {
//        this.facultadSelectedPojoP = facultadSelectedPojoP;
//    }
//
//    public List<EscuelaDepartamento> getEscuelaDeptoList() {
//        return escuelaDeptoList;
//    }
//
//    public void setEscuelaDeptoList(List<EscuelaDepartamento> escuelaDeptoList) {
//        this.escuelaDeptoList = escuelaDeptoList;
//    }
//
//    public List<EscuelaDepartamento> getEscuelaDeptoListSol() {
//        return escuelaDeptoListSol;
//    }
//
//    public void setEscuelaDeptoListSol(List<EscuelaDepartamento> escuelaDeptoListSol) {
//        this.escuelaDeptoListSol = escuelaDeptoListSol;
//    }
//
//    public List<EscuelaDepartamento> getEscuelaDeptoListAsis() {
//        return escuelaDeptoListAsis;
//    }
//
//    public void setEscuelaDeptoListAsis(List<EscuelaDepartamento> escuelaDeptoListAsis) {
//        this.escuelaDeptoListAsis = escuelaDeptoListAsis;
//    }
//
//    public String getFacultadSelectedPojoSol() {
//        return facultadSelectedPojoSol;
//    }
//
//    public void setFacultadSelectedPojoSol(String facultadSelectedPojoSol) {
//        this.facultadSelectedPojoSol = facultadSelectedPojoSol;
//    }
//
//    public String getFacultadSelectedPojoAsis() {
//        return facultadSelectedPojoAsis;
//    }
//
//    public void setFacultadSelectedPojoAsis(String facultadSelectedPojoAsis) {
//        this.facultadSelectedPojoAsis = facultadSelectedPojoAsis;
//    }
//
//    public EscuelaDepartamento getEscuelaDeptoSelectedSol() {
//        return escuelaDeptoSelectedSol;
//    }
//
//    public void setEscuelaDeptoSelectedSol(EscuelaDepartamento escuelaDeptoSelectedSol) {
//        this.escuelaDeptoSelectedSol = escuelaDeptoSelectedSol;
//    }
//
//    public EscuelaDepartamento getEscuelaDeptoSelectedAsis() {
//        return escuelaDeptoSelectedAsis;
//    }
//
//    public void setEscuelaDeptoSelectedAsis(EscuelaDepartamento escuelaDeptoSelectedAsis) {
//        this.escuelaDeptoSelectedAsis = escuelaDeptoSelectedAsis;
//    }
//
//    public List<Proyecto> getProyectoList() {
//        return proyectoList;
//    }
//
//    public void setProyectoList(List<Proyecto> proyectoList) {
//        this.proyectoList = proyectoList;
//    }
//    
//}
