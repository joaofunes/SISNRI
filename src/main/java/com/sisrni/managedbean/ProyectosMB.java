/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Lillian
 */
@Named(value = "proyectosMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)

public class ProyectosMB {
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
//    private ProyectoGenericoService proyectoGenericoService;
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
//    private BecaService becaService;
//    @Autowired
//    private PaisService paisService;
//    @Autowired
//    private RegionService regionService;
//    @Autowired
//    private TipoModalidadBecaService tipoModalidadBecaService;
//
////Definicion de objetos    
//    private Proyecto proyecto;
//    private ProyectoGenerico proyectoGenerico;
//    private PropuestaConvenio propuestaConvenio;
//    private Persona persona;
//    private Persona personaAsistente;
//    private Persona personaExterna;
//    private Persona personaBecario;
//    private Facultad facultad;
//    private Unidad unidadProyecto;
//    private Organismo organismo;
//    private Facultad facultadAsistente;
//    private Facultad facultadExterna;
//    private PersonaProyecto personaProyecto;
//    private PersonaProyectoPK personaProyectoExtPK;
//    private String numDocumentoSol;
//    private String numDocumentoAsis;
//    private String numDocumentoRefExt;
//    private String numDocumentoBecario;
//    private Beca beca;
//    private Pais pais;
//    private int yearActual;
//    private String anio;
//    private TipoModalidaBeca tipoModalidad;
////Definicion de listas
//    private List<Facultad> facultadList;
//    private List<AreaConocimiento> areaConocimientoList;
//    private List<Organismo> organismoProyList;
//    private List<TipoProyecto> tipoproyectolist;
//    private List<Unidad> unidadList;
//    private List<Unidad> unidadSolList;
//    private List<Unidad> unidadAsisList;
//    private List<Unidad> unidadBecarioList;
//    private List<PropuestaConvenio> propuestaConvenioList;
//    private List<Organismo> organismoList;
//    private List<Telefono> listadoTelefonoSol;
//    private List<Telefono> listadoTelefonoAsis;
//    private List<Telefono> listadoTelefonoRefExt;
//    private List<Telefono> listadoTelefonoBecario;
//    private List<Pais> paisList;
//    private List<Pais> paisCooperanteList;
//    private List<TipoModalidaBeca> tipoModalidadList;
////Definicion de selected
//    private TipoProyecto proyectoSelected;
//    private String[] areaConocimientoSelected;
//    private String[] organismoProySelected;
//    private List<AreaConocimiento> areasConocimiento;
//    private List<Organismo> organismosProyecto;
//    private Facultad facultadSelected;
//    private Unidad unidadSelected;
//    private PersonaProyecto personaProyectoAsis;
//    private PersonaProyecto personaProyectoExt;
//    private PersonaProyecto personaProyectoBecario;
//    private PersonaProyectoPK personaProyectoPK;
//    private PersonaProyectoPK personaProyectoAsisPK;
//    private PersonaProyectoPK personaProyectoBecarioPK;
//    private PropuestaConvenio propuestaConvenioSelected;
//    private Facultad facultadSelectedSol;
//    private Facultad facultadSelectedAsis;
//    private Facultad facultadSelectedExt;
//    private Facultad facultadSelectedBecario;
//    private Unidad unidadSelectedSol;
//    private Unidad unidadSelectedAsis;
//    private Unidad unidadSelectedBecario;
//    private Organismo organismoSelected;
//    private Pais paisSelected;
//    private Pais paisCooperanteSelected;
//    private TipoModalidaBeca tipoModalidadSelected;
////Telefono
////private Telefono telefono;
//    private Telefono telefonoSolFijo;
//    private Telefono telefonoSolFax;
//    private Telefono telefonoAsisFijo;
//    private Telefono telefonoAsisFax;
//    private Telefono telefonoRefextFijo;
//    private Telefono telefonoRefextFax;
//    private Telefono telefonoBecarioFijo;
//    private Telefono telefonoBecarioFax;
//    private TipoTelefono tipoTelefonoFax;
//    private TipoTelefono tipoTelefonoFijo;
//
//    private TipoPersona tipoPersonaSol;
//    private TipoPersona tipoPersonaAsis;
//    private TipoPersona tipoPersonaRefext;
//    private TipoPersona tipoPersonaBecario;
//
//    private static final String FIJO = "FIJO";
//    private static final String FAX = "FAX";
//    private int existeSol;
//    private int existeAsis;
//    private int existeRefExt;
//    private int existeBecario;
//    Pais regiones;
//    private int reg;
//    public boolean mostrarmonto;
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
//        unidadSelected = new Unidad();
//        propuestaConvenioSelected = new PropuestaConvenio();
//        facultadSelectedSol = new Facultad();
//        facultadSelectedAsis = new Facultad();
//        facultadSelectedExt = new Facultad();
//        facultadSelectedBecario = new Facultad();
//        unidadSelectedSol = new Unidad();
//        unidadSelectedAsis = new Unidad();
//        unidadSelectedBecario = new Unidad();
//        organismoSelected = new Organismo();
//        areasConocimiento = new ArrayList<AreaConocimiento>();
//        organismosProyecto = new ArrayList<Organismo>();
//        paisSelected = new Pais();
//        paisCooperanteSelected = new Pais();
//        tipoModalidadSelected = new TipoModalidaBeca();
//        //Listas
//        tipoproyectolist = tipoProyectoService.findAll();
//        areaConocimientoList = areaConocimientoService.findAll();
//        organismoProyList = organismoService.findAll();
//        //facultadList = new ArrayList<Facultad>();
//        facultadList = facultadService.findAll();
//        propuestaConvenioList = propuestaConvenioService.findAll();
//        organismoList = organismoService.findAll();
//        listadoTelefonoSol = telefonoService.findAll();
//        listadoTelefonoAsis = telefonoService.findAll();
//        listadoTelefonoRefExt = telefonoService.findAll();
//        listadoTelefonoBecario = telefonoService.findAll();
//        paisList = paisService.findAll();
//        paisCooperanteList = paisService.findAll();
//        tipoModalidadList = tipoModalidadBecaService.findAll();
//        //unidadList=unidadService.findAll();
//        proyecto = new Proyecto();
//        proyectoGenerico = new ProyectoGenerico();
//        propuestaConvenio = new PropuestaConvenio();
//        facultad = new Facultad();
//        facultadAsistente = new Facultad();
//        facultadExterna = new Facultad();
//        //telefono = new Telefono();
//        telefonoSolFijo = new Telefono();
//        telefonoSolFax = new Telefono();
//        telefonoAsisFijo = new Telefono();
//        telefonoAsisFax = new Telefono();
//        telefonoRefextFijo = new Telefono();
//        telefonoRefextFax = new Telefono();
//        telefonoBecarioFijo = new Telefono();
//        persona = new Persona();
//        personaAsistente = new Persona();
//        personaExterna = new Persona();
//        personaBecario = new Persona();
//        organismo = new Organismo();
//        personaProyecto = new PersonaProyecto();
//        personaProyectoAsis = new PersonaProyecto();
//        personaProyectoExt = new PersonaProyecto();
//        personaProyectoBecario = new PersonaProyecto();
//        personaProyectoPK = new PersonaProyectoPK();
//        personaProyectoAsisPK = new PersonaProyectoPK();
//        personaProyectoBecarioPK = new PersonaProyectoPK();
//        personaProyectoExtPK = new PersonaProyectoPK();
//        beca = new Beca();
//        pais = new Pais();
//        regiones = new Pais();
//        tipoModalidad = new TipoModalidaBeca();
//        numDocumentoAsis = "";
//        numDocumentoSol = "";
//        numDocumentoRefExt = "";
//        numDocumentoBecario = "";
//
//        // tipos telefonos
//        tipoTelefonoFax = tipoTelefonoService.getTipoByDesc("FAX");
//        tipoTelefonoFijo = tipoTelefonoService.getTipoByDesc("FIJO");
//        // tipo persona
//        tipoPersonaSol = tipoPersonaService.getTipoPersonaByNombre("REFERENTE INTERNO");
//        tipoPersonaAsis = tipoPersonaService.getTipoPersonaByNombre("ASISTENTE DE COORDINADOR");
//        tipoPersonaRefext = tipoPersonaService.getTipoPersonaByNombre("REFERENTE EXTERNO");
//        tipoPersonaBecario = tipoPersonaService.getTipoPersonaByNombre("BECARIO");
//        //bandera
//        existeSol = 0;
//        existeAsis = 0;
//        existeRefExt = 0;
//        yearActual = getYearOfDate(new Date());
//        anio = "";
//        reg = 0;
//    }
//
//    public void mostrarCampo() {
//        if (tipoModalidadSelected.getIdTipoModalidad() == 1) {
//            mostrarmonto = true;
//        } else {
//            mostrarmonto = false;
//        }
//    }
//
//    public void guardarProyecto() {
//        try {
//            if (proyectoSelected.getIdTipoProyecto() != 1) {
//                proyecto.setIdTipoProyecto(tipoProyectoService.findById(proyectoSelected.getIdTipoProyecto()));
//                proyecto.setIdFacultad(facultadSelected.getIdFacultad());
//                proyecto.setIdUnidad(unidadSelected.getIdUnidad());
//                PropuestaConvenio propuesta = propuestaConvenioService.findById(propuestaConvenioSelected.getIdPropuesta());
//                proyecto.setIdPropuestaConvenio(propuesta);
//                proyecto.setAnioGestion(Integer.parseInt(anio.trim()));
//                //guardar proyecto
//                proyecto.setIdProyecto(0);
//                Pais paiscooperante = paisService.findById(paisCooperanteSelected.getIdPais());
//                proyecto.setIdPaisCooperante(paiscooperante);
//                proyectoService.save(proyecto);
//
//                //guardar proyecto genérico
//                //proyectoGenerico.setAreaConocimientoList(areaConocimientoList);
//                proyectoGenerico.setIdProyecto(proyecto.getIdProyecto());
//                //Intermedia de proyecto y area de conocimiento
//                for (int i = 0; i < areaConocimientoSelected.length; i++) {
//                    AreaConocimiento area = areaConocimientoService.findById(Integer.parseInt(areaConocimientoSelected[i]));
//                    if (area != null) {
//                        areasConocimiento.add(area);
//                    }
//                }
//                proyectoGenerico.setAreaConocimientoList(areasConocimiento);
//
//                // guardar organismo
//                for (int i = 0; i < organismoProySelected.length; i++) {
//                    Organismo organismo = organismoService.findById(Integer.parseInt(organismoProySelected[i]));
//                    if (organismo != null) {
//                        organismosProyecto.add(organismo);
//                    }
//                }
//                proyectoGenerico.setOrganismoList(organismosProyecto);
//                proyectoGenericoService.save(proyectoGenerico);
//                //guardar persona solicitante
//                if (existeSol != 1) {
//                    Unidad unidadSolicitante = unidadService.findById(unidadSelectedSol.getIdUnidad());
//                    persona.setIdUnidad(unidadSolicitante);
//                    persona.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());
//                    persona.setDuiPersona(numDocumentoSol);
//                    personaService.save(persona);
//                    //guardar telefono fijo solicitante
//                    telefonoSolFijo.setIdPersona(persona);
//                    telefonoSolFijo.setIdTipoTelefono(tipoTelefonoFijo);
//                    telefonoService.save(telefonoSolFijo);
//                    // guardar fax solicitante
//                    telefonoSolFax.setIdPersona(persona);
//                    telefonoSolFax.setIdTipoTelefono(tipoTelefonoFax);
//                    telefonoService.save(telefonoSolFax);
//                    //guardar en tabla intermedia persona_proyecto de un solicitante 
//
//                    personaProyectoPK.setIdPersona(persona.getIdPersona());
//                    personaProyectoPK.setIdProyectoGenerico(proyectoGenerico.getIdProyecto());
//                    personaProyectoPK.setIdTipoPersona(tipoPersonaSol.getIdTipoPersona());
//
//                    /////////////        
//                    personaProyecto.setProyectoGenerico(proyectoGenerico);
//                    personaProyecto.setPersona(persona);
//                    personaProyecto.setTipoPersona(tipoPersonaSol);
//                    personaProyecto.setPersonaProyectoPK(personaProyectoPK);
//                    personaProyectoService.save(personaProyecto);
//                }
//                //guardar persona Asistente
//                if (existeAsis != 1) {
//                    Unidad unidadAsistente = unidadService.findById(unidadSelectedAsis.getIdUnidad());
//                    personaAsistente.setIdUnidad(unidadAsistente);
//                    personaAsistente.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());
//                    personaAsistente.setDuiPersona(numDocumentoAsis);
//                    personaService.save(personaAsistente);
//                    //guardar telefono fijo asistente
//                    telefonoAsisFijo.setIdPersona(personaAsistente);
//                    telefonoAsisFijo.setIdTipoTelefono(tipoTelefonoFijo);
//                    telefonoService.save(telefonoAsisFijo);
//                    // guardar fax Asistente
//                    telefonoAsisFax.setIdPersona(personaAsistente);
//                    telefonoAsisFax.setIdTipoTelefono(tipoTelefonoFax);
//                    telefonoService.save(telefonoAsisFax);
//                    //guardar en tabla intermedia persona_proyecto Asistente
//                    //PersonaProyectoPK personaProyectoPK = new PersonaProyectoPK();            
//                    personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
//                    personaProyectoAsisPK.setIdProyectoGenerico(proyectoGenerico.getIdProyecto());
//                    personaProyectoAsisPK.setIdTipoPersona(tipoPersonaAsis.getIdTipoPersona());
//                    /////////////        
//                    personaProyectoAsis.setProyectoGenerico(proyectoGenerico);
//                    personaProyectoAsis.setPersona(personaAsistente);
//                    personaProyectoAsis.setTipoPersona(tipoPersonaAsis);
//                    personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
//                    personaProyectoService.save(personaProyectoAsis);
//                }
//
//                //Guardar informacion de referente externo
//                if (existeRefExt != 1) {
//                    personaExterna.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
//                    Organismo organismoExt = organismoService.findById(organismoSelected.getIdOrganismo());
//                    personaExterna.setIdOrganismo(organismoExt);
//                    personaExterna.setPasaporte(numDocumentoRefExt);
//                    personaService.save(personaExterna);
//                    //guardar telefono fijo persona externa
//                    telefonoRefextFijo.setIdPersona(personaExterna);
//                    telefonoRefextFijo.setIdTipoTelefono(tipoTelefonoFijo);
//                    telefonoService.save(telefonoRefextFijo);
//                    //guardar fax persona externa
//                    telefonoRefextFax.setIdPersona(personaExterna);
//                    telefonoRefextFax.setIdTipoTelefono(tipoTelefonoFax);
//                    telefonoService.save(telefonoRefextFax);
//                    //guardar en tabla intermedia persona_proyecto Referente externo
//                    personaProyectoExtPK.setIdPersona(personaExterna.getIdPersona());
//                    personaProyectoExtPK.setIdProyectoGenerico(proyectoGenerico.getIdProyecto());
//                    personaProyectoExtPK.setIdTipoPersona(tipoPersonaRefext.getIdTipoPersona());
//                    //////
//                    personaProyectoExt.setProyectoGenerico(proyectoGenerico);
//                    personaProyectoExt.setPersona(personaExterna);
//                    personaProyectoExt.setTipoPersona(tipoPersonaRefext);
//                    personaProyectoExt.setPersonaProyectoPK(personaProyectoExtPK);
//                    personaProyectoService.save(personaProyectoExt);
//                }
//            } else // guardar becario
//            if (existeBecario != 1) {
//                proyecto.setIdTipoProyecto(tipoProyectoService.findById(proyectoSelected.getIdTipoProyecto()));
//                proyecto.setIdFacultad(facultadSelected.getIdFacultad());
//                proyecto.setIdUnidad(unidadSelected.getIdUnidad());
//                PropuestaConvenio propuesta = propuestaConvenioService.findById(propuestaConvenioSelected.getIdPropuesta());
//                proyecto.setIdPropuestaConvenio(propuesta);
//                proyecto.setAnioGestion(Integer.parseInt(anio.trim()));
//                //guardar proyecto
//                proyecto.setIdProyecto(0);
//                proyectoService.save(proyecto);
//                //guardar persona becario
//                personaBecario.setIdTipoPersona(tipoPersonaBecario.getIdTipoPersona());
//                personaBecario.setDuiPersona(numDocumentoBecario);
//                Unidad unidadBecario = unidadService.findById(unidadSelectedBecario.getIdUnidad());
//                personaBecario.setIdUnidad(unidadBecario);
//                personaBecario.setIdTipoPersona(tipoPersonaBecario.getIdTipoPersona());
//                personaService.save(personaBecario);
//                //guardar tabla becas
//                beca.setIdBecas(proyecto.getIdProyecto());
//                beca.setIdPaisDestino(paisSelected.getIdPais());
//                pais = paisService.findById(paisSelected.getIdPais());
//                beca.setIdRegionDestino(pais.getIdRegion().getIdRegion());
//                tipoModalidad = tipoModalidadBecaService.findById(tipoModalidadSelected.getIdTipoModalidad());
//                beca.setIdModalidad(tipoModalidad);
//                Persona becario = personaService.findById(personaBecario.getIdPersona());
//              //  beca.setIdPersona(becario); cometariado despues de remapear 
//                becaService.save(beca);
//                //Telefono del becario
//                telefonoBecarioFijo.setIdTipoTelefono(tipoTelefonoFijo);
//                telefonoBecarioFijo.setIdPersona(personaBecario);
//                telefonoService.save(telefonoBecarioFijo);
//                
//
//            }
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La Informacion no ha sido registrada."));
//        }
//        inicializador();
//    }
//
//    public void onFacultadChange() {
//        if (facultadSelected.getIdFacultad() != null && !facultadSelected.getIdFacultad().toString().equals("")) {
//            unidadList = unidadService.getUnidadesByFacultadId(facultadSelected.getIdFacultad());
//        } else {
//            unidadList = new ArrayList<Unidad>();
//        }
//    }
//
//    public void onFacultadSolicitanteChange() {
//        if (facultadSelectedSol.getIdFacultad() != null && !facultadSelectedSol.getIdFacultad().toString().equals("")) {
//            unidadSolList = unidadService.getUnidadesByFacultadId(facultadSelectedSol.getIdFacultad());
//        } else {
//            unidadSolList = new ArrayList<Unidad>();
//        }
//    }
//
//    public void onFacultadAsistenteChange() {
//        if (facultadSelectedAsis.getIdFacultad() != null && !facultadSelectedAsis.getIdFacultad().toString().equals("")) {
//            unidadAsisList = unidadService.getUnidadesByFacultadId(facultadSelectedAsis.getIdFacultad());
//        } else {
//            unidadAsisList = new ArrayList<Unidad>();
//        }
//    }
//
//    public void onFacultadBecarioChange() {
//        if (facultadSelectedBecario.getIdFacultad() != null && !facultadSelectedBecario.getIdFacultad().toString().equals("")) {
//            unidadBecarioList = unidadService.getUnidadesByFacultadId(facultadSelectedBecario.getIdFacultad());
//        } else {
//            unidadBecarioList = new ArrayList<Unidad>();
//        }
//    }
////Buscando a persona  Solicitante existente
//
//    public void searchByDocEmailInterno() {
//        try {
//
//            if (numDocumentoSol != null && persona != null && !numDocumentoSol.equals("") && !persona.getEmailPersona().equals("")) {
//                persona = personaService.getReferenteInternoByDocEmail(numDocumentoSol, persona);
//                if (persona != null) {
//                    existeSol = 1;
//                }
//                facultadSelectedSol = persona.getIdUnidad().getIdFacultad();
//                unidadSelectedSol = persona.getIdUnidad();
//                onChangeInterno();
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onChangeInterno() {
//        try {
//            listadoTelefonoSol = telefonoService.getTelefonosByPersona(persona);
//
//            for (Telefono us : listadoTelefonoSol) {
//
//                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telefonoSolFijo = us;
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
//    public void searchByDocEmailAsistente() {
//        try {
//
//            if (numDocumentoAsis != null && personaAsistente != null && !numDocumentoAsis.equals("") && !personaAsistente.getEmailPersona().equals("")) {
//                personaAsistente = personaService.getReferenteInternoByDocEmail(numDocumentoAsis, personaAsistente);
//                if (personaAsistente != null) {
//                    existeAsis = 1;
//                }
//                facultadSelectedAsis = personaAsistente.getIdUnidad().getIdFacultad();
//                unidadSelectedAsis = personaAsistente.getIdUnidad();
//                onChangeAsistente();
//
//            }
//
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
//    public void searchByDocEmailReferenteExterno() {
//        try {
//
//            if (numDocumentoRefExt != null && personaExterna != null && !numDocumentoRefExt.equals("") && !personaExterna.getEmailPersona().equals("")) {
//                personaExterna = personaService.getReferenteInternoByDocEmail(numDocumentoRefExt, personaExterna);
//                if (personaExterna != null) {
//                    existeRefExt = 1;
//                }
//                facultadSelectedExt = personaExterna.getIdUnidad().getIdFacultad();
//                organismoSelected = personaExterna.getIdOrganismo();
//                onChangeReferenteExterno();
//
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
//                if (us.getIdTipoTelefono().getNombre().equals(FAX)) {
//                    telefonoRefextFax = us;
//                }
//            }
//        } catch (Exception e) {
//        }
//    }
//    //Fin busca de persona Referente externo
//
////Buscando si existe persona Becario
//    public void searchByDocEmailBecario() {
//        try {
//
//            if (numDocumentoBecario != null && personaBecario != null && !numDocumentoBecario.equals("") && !personaBecario.getEmailPersona().equals("")) {
//                personaBecario = personaService.getReferenteInternoByDocEmail(numDocumentoBecario, personaBecario);
//                if (personaBecario != null) {
//                    existeBecario = 1;
//                    unidadSelectedBecario = personaBecario.getIdUnidad();
//                    onChangeBecario();
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void onChangeBecario() {
//        try {
//            listadoTelefonoBecario = telefonoService.getTelefonosByPersona(personaBecario);
//
//            for (Telefono us : listadoTelefonoBecario) {
//
//                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
//                    telefonoBecarioFijo = us;
//                }
//            }
//        } catch (Exception e) {
//        }
//    }
//    //Fin busca de persona Becario
//
//    private Integer getYearOfDate(Date date) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        Integer year = cal.get(Calendar.YEAR);
//        return year;
//    }
//    public void regresar(){
//        try {
//            proyectoMB.cargarProyecto();
//            
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();  
//           
//            String outcome = "proyectoAdm.xhtml";
//            FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
//        } catch (Exception e) {
//        }
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
//    public Facultad getFacultadExterna() {
//        return facultadExterna;
//    }
//
//    public void setFacultadExterna(Facultad facultadExterna) {
//        this.facultadExterna = facultadExterna;
//    }
//
//    public Facultad getFacultadSelectedAsis() {
//        return facultadSelectedAsis;
//    }
//
//    public void setFacultadSelectedAsis(Facultad facultadSelectedAsis) {
//        this.facultadSelectedAsis = facultadSelectedAsis;
//    }
//
//    public Facultad getFacultadSelectedExt() {
//        return facultadSelectedExt;
//    }
//
//    public void setFacultadSelectedExt(Facultad facultadSelectedExt) {
//        this.facultadSelectedExt = facultadSelectedExt;
//    }
//
//    public Unidad getUnidadSelectedSol() {
//        return unidadSelectedSol;
//    }
//
//    public void setUnidadSelectedSol(Unidad unidadSelectedSol) {
//        this.unidadSelectedSol = unidadSelectedSol;
//    }
//
//    public Unidad getUnidadSelectedAsis() {
//        return unidadSelectedAsis;
//    }
//
//    public void setUnidadSelectedAsis(Unidad unidadSelectedAsis) {
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
//    public Facultad getFacultadSelectedSol() {
//        return facultadSelectedSol;
//    }
//
//    public void setFacultadSelectedSol(Facultad facultadSelectedSol) {
//        this.facultadSelectedSol = facultadSelectedSol;
//    }
//
//    public Unidad getUnidadProyecto() {
//        return unidadProyecto;
//    }
//
//    public void setUnidadProyecto(Unidad unidadProyecto) {
//        this.unidadProyecto = unidadProyecto;
//    }
//
//    public Unidad getUnidadSelected() {
//        return unidadSelected;
//    }
//
//    public void setUnidadSelected(Unidad unidadSelected) {
//        this.unidadSelected = unidadSelected;
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
//    public List<Unidad> getUnidadSolList() {
//        return unidadSolList;
//    }
//
//    public void setUnidadSolList(List<Unidad> unidadSolList) {
//        this.unidadSolList = unidadSolList;
//    }
//
//    public List<Unidad> getUnidadAsisList() {
//        return unidadAsisList;
//    }
//
//    public void setUnidadAsisList(List<Unidad> unidadAsisList) {
//        this.unidadAsisList = unidadAsisList;
//    }
//
//    public List<Unidad> getUnidadBecarioList() {
//        return unidadBecarioList;
//    }
//
//    public void setUnidadBecarioList(List<Unidad> unidadBecarioList) {
//        this.unidadBecarioList = unidadBecarioList;
//    }
//
//    public ProyectoGenerico getProyectoGenerico() {
//        return proyectoGenerico;
//    }
//
//    public void setProyectoGenerico(ProyectoGenerico proyectoGenerico) {
//        this.proyectoGenerico = proyectoGenerico;
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
//    public Beca getBeca() {
//        return beca;
//    }
//
//    public void setBeca(Beca beca) {
//        this.beca = beca;
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
//    public Facultad getFacultadSelectedBecario() {
//        return facultadSelectedBecario;
//    }
//
//    public void setFacultadSelectedBecario(Facultad facultadSelectedBecario) {
//        this.facultadSelectedBecario = facultadSelectedBecario;
//    }
//
//    public Telefono getTelefonoBecarioFijo() {
//        return telefonoBecarioFijo;
//    }
//
//    public void setTelefonoBecarioFijo(Telefono telefonoBecarioFijo) {
//        this.telefonoBecarioFijo = telefonoBecarioFijo;
//    }
//
//    public TipoPersona getTipoPersonaBecario() {
//        return tipoPersonaBecario;
//    }
//
//    public void setTipoPersonaBecario(TipoPersona tipoPersonaBecario) {
//        this.tipoPersonaBecario = tipoPersonaBecario;
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
//    public Unidad getUnidadSelectedBecario() {
//        return unidadSelectedBecario;
//    }
//
//    public void setUnidadSelectedBecario(Unidad unidadSelectedBecario) {
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
//    public TipoModalidaBeca getTipoModalidad() {
//        return tipoModalidad;
//    }
//
//    public void setTipoModalidad(TipoModalidaBeca tipoModalidad) {
//        this.tipoModalidad = tipoModalidad;
//    }
//
//    public List<TipoModalidaBeca> getTipoModalidadList() {
//        return tipoModalidadList;
//    }
//
//    public void setTipoModalidadList(List<TipoModalidaBeca> tipoModalidadList) {
//        this.tipoModalidadList = tipoModalidadList;
//    }
//
//    public TipoModalidaBeca getTipoModalidadSelected() {
//        return tipoModalidadSelected;
//    }
//
//    public void setTipoModalidadSelected(TipoModalidaBeca tipoModalidadSelected) {
//        this.tipoModalidadSelected = tipoModalidadSelected;
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
    
}
