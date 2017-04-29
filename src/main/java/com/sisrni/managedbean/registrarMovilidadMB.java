package com.sisrni.managedbean;

import com.sisrni.model.CategoriaMovilidad;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.EtapaMovilidad;
import com.sisrni.model.Facultad;
import com.sisrni.model.Movilidad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaMovilidad;
import com.sisrni.model.PersonaMovilidadPK;
import com.sisrni.model.ProgramaMovilidad;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoCambio;
import com.sisrni.model.TipoMovilidad;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.TipoTelefono;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
import com.sisrni.service.CategoriaMovilidadService;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.EscuelaDepartamentoService;
import com.sisrni.service.EtapaMovilidadService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.MovilidadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.PersonaMovilidadService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.ProgramaMovilidadService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoCambioService;
import com.sisrni.service.TipoMovilidadService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.MailException;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Usuario
 */
@Named(value = "registrarMovilidadMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class registrarMovilidadMB {

    @Inject
    CategoriaMovilidadMB categoriaMovilidadMB;
    @Inject
    PaisMB paisMB;
    @Inject
    OrganismoCooperanteMB organismoCooperanteMB;
    @Inject
    TipoCambioMB tipoCambioMB;
    @Inject
    EtapaMovilidadMB etapaMovilidadMB;
    @Inject
    ProgramaMovilidadMB programaMovilidadMB;

    //Variables
    private Boolean existeReferente;
    private Boolean existePersonaMovilidad;
    private Boolean existeMovilidad;
    private Boolean isEditable;
    private Boolean mostrarEscuelaDocente;
    private Boolean mostrarEscuelaReferente;
    private Boolean escuelaDocenteRequerido;
    private Boolean escuelaReferenteRequerido;
    private Boolean mostrarBuscadorEntrante;
    private Boolean mostrarBuscadorSaliente;
    private Boolean isHabilidado;
    private Boolean isHabilitadoRfte;

    private Boolean usadoBuscadorPersonaMov;   
    private boolean usadoBuscadorPersonaRefte;

    private Boolean flagSearchDuiSaliente;
    private Boolean flagSearchEmailSaliente;
    private Boolean flagSearchNombreSaliente;
    private Boolean disableAutoSaliente;

    private Boolean flagSearchDuiReferente;
    private Boolean flagSearchEmailReferente;
    private Boolean flagSearchNombreReferente;
    private Boolean disableAutoReferente;

    private Boolean flagSearchDuiEntrante;
    private Boolean flagSearchEmailEntrante;
    private Boolean flagSearchNombreEntrante;
    private Boolean disableAutoEntrante;
    private Boolean actualizar;
    private Boolean actualizarPersonaMov;
    private Boolean actualizarRefte;
    private Boolean desvinculadoRfte;
    private Boolean mostrarBotonDesvincular;
    private Boolean activarDocente;
    private Boolean activarReferente;

    private float totalViaticosCurso;
    private float totalViaticosCursoBoletoAereo;

    private Integer tipoMovilidadSelected;
    private Integer categoriaMovilidadSelected;
    private Integer paisOrigenSelected;
    private Integer institucionOrigenSelected;
    private Integer paisDestinoSelected;
    private Integer institucionDestinoSelected;
    private Boolean entregaInformeSelected;
    
    private Integer etapaMovilidadSelected;
    private Integer escuelaDepartamentoReferenteFactBnfSelected;
    private Integer escuelaDepartamentoPersonaMovilidad;
    private Integer institucionPersonaMovilidadSelected;

    private static final String FIJO = "FIJO";
    private static final String FAX = "FAX";
    private static final String CELULAR = "CELULAR";

    
    private String facultadDeReferente;
    private String facultadPersonaMovilidad;

    private String docSearchReferente;
    private String docSearchPersonaMovilidad;
    private String mascaraTelefonoMovilidad;

    private String tipoMovilidadConsultar;
    private String categoriaMovilidadConsultar;
    private String institucionPersonaMovConsultar;
    private String facultadPersonaMovConsultar;
    private String escuelaDeptoPersonaMovConsultar;
    private String paisOrigenConsultar;
    private String institucionOrigenConsultar;
    private String paisDestinoconsultar;
    private String institucionDestinoConsultar;
    private String etapaMovilidadConsultar;
    private String entregaInformeConsultar;
    //private String obsequioConsultar;
    private String facultadPersonaRefConsultar;
    private String escuelaDepartamentoRefConsultar;
    private String fInicioConsultar;
    private String fFinConsultar;
    private String fEntregaConsultar;

    private String tipoBusquedaSaliente;
    private String tipoBusquedaEntrante;
    private String tipoBusquedaReferente;

    private String msgPersonaMovExiste;
    private String msgPersonaRefExiste;

    private String tituloRegistroEdicion;

    private Boolean mostrarSaliente, mostrarEntrante;

    private Date fechaInicioSelected;
    private Date fechaFinSelected;
    private Date fechaEntregaMinedSelected;

    private ProgramaMovilidad programaMovilidadSelected;
    private ProgramaMovilidad programaMovilidad;
    private TipoMovilidad tipoMovilidad;
    private Movilidad movilidad;
    private CategoriaMovilidad categoriaMovilidad;
    //private Persona personaMovilidad;
    private Persona personaMovilidadGenerico;

    private Persona personaFacultadGenerico;

    private Persona personaMovAux;
    private Persona personaReftAux;

    private TipoPersona tipoPersonaReferenteFact;

    private TipoPersona tipoPersonaMovilidad;

    private Telefono telFijoPersonaMovilidad;
    private Telefono telCelPersonaMovilidad;
    //private Telefono faxPersonaMovilidad;

    private Telefono telFijoPersonaFacultad;
    private Telefono telCelPersonaFacultad;
    //private Telefono faxPersonaFacultad;

    private TipoTelefono tipoTelefonoFax;
    private TipoTelefono tipoTelefonoFijo;
    private TipoTelefono tipoTelefonoCel;

    private Unidad unidadRftFactTmp;
    private Unidad unidadPersonMovTmp;

    private List<Movilidad> listMovilidad;
    private List<ProgramaMovilidad> listProgramaMovilidad;
    private List<TipoMovilidad> listTipoMovilidad;
    private List<CategoriaMovilidad> listCategoriaMovilidad;
    private List<Pais> listPaisOrigenMovilidad;
    private List<Pais> listPaisDestinoMovilidad;
    private List<Persona> listPersonaMovilidad;
    private List<Persona> listPersonaReferenteFacultad;  
    private List<Organismo> listOrganismosOrigen;
    private List<Organismo> listOrganismosDestino;
    private List<EtapaMovilidad> listEtapaMovilidad;
    private List<PojoFacultadesUnidades> listFacultadUnidad;
    private List<PojoFacultadesUnidades> listFacultadUnidadReferenteFactBnf;
    private List<PojoFacultadesUnidades> listFacultadesUnidadesPersonaMovilidad;
    private List<String> listFacultadesBeneficiadasSelected;
    private List<String> listUnidadesBeneficiadasSelected;

    //private List<Integer> listFacultadSelected;
    private List<Organismo> listOrganismoPersonaMovilidad;
    private List<Facultad> listFacultadAdd;
    private List<Unidad> listUnidad;
    private List<Unidad> listUnidadAdd;

    private List<Facultad> listFacultadBnfUes;
    private List<Unidad> listUnidadBnfUes;

    private List<Facultad> listFacultadByInst;
    private List<Unidad> listUnidadByInst;

    private List<EscuelaDepartamento> listEscuelaDepartamentoRefFact;
    private List<EscuelaDepartamento> listEscuelaDepartamentoPersonaMovilidad;

    private List<PojoMovilidadAdm> listPojoMovilidadAdm;
    private List<String> listFacultadesUnidadesBeneficiadasSelected;

    private List<TipoCambio> listTipoCambio;

    private List<Persona> listAll;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private TipoCambio tipoCambioSelected;

    //--------------------------------------
    private static Organismo institucionUES;
    private Boolean fueEditadoDocente;
    private Boolean fueEditadoReferente;

    private Boolean siEditarDocente;
    private Boolean siEditarReferente;
    private Boolean mostrarBotonEditarDocente;
    private Boolean mostrarBotonEditarReferente;
    private Boolean habilitarBuscadorReferente;
    private Boolean mostrarBotonNuevoDocente;
    private Boolean mostrarBotonNuevoReferente;

    private Boolean consultoria;
    private Boolean disableConsultoria;
    private Boolean facultadDocenteRequerido;

    private String txtBotonGuardar;
    private String txtBotonRegresar;
    private String codigoPais;
    private String iconRegresarCancelar;
    private String iconGuardarActualizar;

    //Services
    @Autowired
    private ProgramaMovilidadService programaMovilidadService;

    @Autowired
    private TipoMovilidadService tipoMovilidadService;

    @Autowired
    private CategoriaMovilidadService categoriaMovilidadService;

    @Autowired
    private PaisService paisService;

    @Autowired
    private MovilidadService movilidadService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaMovilidadService personaMovilidadService;

    @Autowired
    private OrganismoService organismoService;

    @Autowired
    private EtapaMovilidadService etapamovilidadService;

    @Autowired
    private FacultadService facultadService;

    @Autowired
    private UnidadService unidadService;

    @Autowired
    private EscuelaDepartamentoService escuelaDepartamentoService;

    @Autowired
    private TipoTelefonoService tipoTelefonoService;

    @Autowired
    private TelefonoService telefonoService;

    @Autowired
    private TipoPersonaService tipoPersonaService;

    @Autowired
    private TipoCambioService tipoCambioService;

    @Autowired
    private DocumentoService documentoService;

    /**
     * Constructor
     */
    public registrarMovilidadMB() {
    }

    /**
     * Post constructor
     */
    @PostConstruct
    public void init() {
        cargarMovilidadPersona();
    }

    /**
     * Metodo inicializador de variables y objetos
     */
    public void cargarMovilidadPersona() {
        existeReferente = false;   
        existePersonaMovilidad = false;
        existeMovilidad = false;
        isEditable = true;
        mostrarEscuelaDocente = false;
        mostrarEscuelaReferente = false;
        escuelaDocenteRequerido = false;
        escuelaReferenteRequerido = false;
        mostrarBuscadorSaliente = false;
        mostrarBuscadorEntrante = false;
        isHabilidado = Boolean.FALSE;
        isHabilitadoRfte = Boolean.FALSE;

        flagSearchDuiSaliente = Boolean.FALSE;
        flagSearchEmailSaliente = Boolean.FALSE;
        flagSearchNombreSaliente = Boolean.FALSE;
        disableAutoSaliente = Boolean.TRUE;

        flagSearchDuiReferente = Boolean.FALSE;
        flagSearchEmailReferente = Boolean.FALSE;
        flagSearchNombreReferente = Boolean.FALSE;
        disableAutoReferente = Boolean.TRUE;

        flagSearchDuiEntrante = Boolean.FALSE;
        flagSearchEmailEntrante = Boolean.FALSE;
        flagSearchNombreEntrante = Boolean.FALSE;
        disableAutoEntrante = Boolean.TRUE;

        actualizar = false;
        actualizarPersonaMov = false;
        actualizarRefte = false;
        usadoBuscadorPersonaMov = false;
        usadoBuscadorPersonaRefte = false;
        desvinculadoRfte = true;
        mostrarBotonDesvincular = false;
        activarDocente = true;
        activarReferente = true;

        programaMovilidadSelected = new ProgramaMovilidad();
        movilidad = new Movilidad();
        movilidad.setIdTipoMovilidad(new TipoMovilidad());
        movilidad.setIdProgramaMovilidad(new ProgramaMovilidad());
        movilidad.setIdEtapaMovilidad(new EtapaMovilidad());
        programaMovilidad = new ProgramaMovilidad();
        tipoMovilidad = new TipoMovilidad();
        tipoMovilidadSelected = null;
        categoriaMovilidadSelected = null;
        categoriaMovilidad = new CategoriaMovilidad();
        paisOrigenSelected = null;
        institucionOrigenSelected = null;
        paisDestinoSelected = null;
        institucionDestinoSelected = null;
        entregaInformeSelected = null;
        //obsequioSelected = null;
        //personaMovilidad = new Persona();
        personaMovilidadGenerico = new Persona();
        
        personaFacultadGenerico = new Persona();

        personaMovAux = new Persona();
        personaReftAux = new Persona();

        fechaInicioSelected = null;
        fechaFinSelected = null;
        fechaEntregaMinedSelected = null;
        etapaMovilidadSelected = null;
        escuelaDepartamentoReferenteFactBnfSelected = null;
        escuelaDepartamentoPersonaMovilidad = null;
        institucionPersonaMovilidadSelected = null;

        telFijoPersonaMovilidad = new Telefono();
        telCelPersonaMovilidad = new Telefono();
        
        telFijoPersonaFacultad = new Telefono();
        telCelPersonaFacultad = new Telefono();
       
        tipoCambioSelected = new TipoCambio();

        listMovilidad = movilidadService.findAll(); 
        listPojoMovilidadAdm = movilidadService.getMovilidadAdm(-1);

        listProgramaMovilidad = programaMovilidadService.findAll();
        listTipoMovilidad = tipoMovilidadService.findAll();
        listCategoriaMovilidad = categoriaMovilidadService.findAll();
        listPaisOrigenMovilidad = paisService.findAll();
        listPaisDestinoMovilidad = paisService.findAll();
        listPersonaMovilidad = personaService.findAll();
        listEtapaMovilidad = etapamovilidadService.findAll();

        listFacultadesBeneficiadasSelected = new ArrayList<String>();
        listUnidadesBeneficiadasSelected = new ArrayList<String>();

        listFacultadBnfUes = facultadService.getFacultadesByUniversidad(1); 
        listUnidadBnfUes = unidadService.getUnidadesByUniversidad(1);   

        listFacultadByInst = new ArrayList<Facultad>();
        listUnidadByInst = new ArrayList<Unidad>();

        listFacultadUnidad = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);
        listFacultadAdd = new ArrayList<Facultad>();

        listUnidad = unidadService.findAll();
        listUnidadAdd = new ArrayList<Unidad>();

        listTipoCambio = tipoCambioService.findAll();

        listFacultadesUnidadesBeneficiadasSelected = new ArrayList<String>();

        listOrganismoPersonaMovilidad = organismoService.findAll();

        listFacultadesUnidadesPersonaMovilidad = new ArrayList<PojoFacultadesUnidades>();

        listFacultadUnidadReferenteFactBnf = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);//revisar esto
        listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
        listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();

        // tipos telefonos
        tipoTelefonoFax = tipoTelefonoService.getTipoByDesc("FAX");  //REVISAR ESTO
        tipoTelefonoFijo = tipoTelefonoService.getTipoByDesc("FIJO");
        tipoTelefonoCel = tipoTelefonoService.getTipoByDesc("CELULAR");

        //Tipos personas
        tipoPersonaReferenteFact = tipoPersonaService.getTipoPersonaByNombre("REFERENTE FACULTAD BENEFICIADA");  //REVISAR ESTO
        tipoPersonaMovilidad = tipoPersonaService.getTipoPersonaByNombre("DOCENTE EN MOVILIDAD");

        listPersonaReferenteFacultad = personaService.getPersonasByIdOrganismo(1);  // Revisar esto

        docSearchReferente = "";
        docSearchPersonaMovilidad = "";
        tipoMovilidadConsultar = "";
        categoriaMovilidadConsultar = "";
        institucionPersonaMovConsultar = "";
        facultadPersonaMovConsultar = "";
        escuelaDeptoPersonaMovConsultar = "";
        paisOrigenConsultar = "";
        institucionOrigenConsultar = "";
        paisDestinoconsultar = "";
        institucionDestinoConsultar = "";
        etapaMovilidadConsultar = "";
        entregaInformeConsultar = "";
        //obsequioConsultar = "";
        facultadPersonaRefConsultar = "";
        escuelaDepartamentoRefConsultar = "";

        fInicioConsultar = "";
        fFinConsultar = "";
        fEntregaConsultar = "";

        mascaraTelefonoMovilidad = "";

        facultadPersonaMovilidad = "";
        facultadDeReferente = "";

        unidadRftFactTmp = null;
        unidadPersonMovTmp = null;

        tipoBusquedaSaliente = "";
        tipoBusquedaEntrante = "";
        tipoBusquedaReferente = "";

        msgPersonaMovExiste = "";
        msgPersonaRefExiste = "";

        tituloRegistroEdicion = "";

        //------------------------------------------
        institucionUES = organismoService.findById(1);
        mostrarBotonEditarDocente = false;
        mostrarBotonEditarReferente = false;
        fueEditadoDocente = false;
        fueEditadoReferente = false;
        siEditarDocente = false;
        siEditarReferente = false;
        habilitarBuscadorReferente = false;
        mostrarBotonNuevoDocente = false;
        mostrarBotonNuevoReferente = false;
        consultoria = false;
        disableConsultoria = true;
        facultadDocenteRequerido = false;

        txtBotonGuardar = "Guardar";
        txtBotonRegresar = "Regresar";
        codigoPais = "";
        iconRegresarCancelar = "ui-icon-arrowreturnthick-1-w";
        iconGuardarActualizar = "ui-icon-disk";
    }

    public void mostrarEscuelaDocenteMovilidad() {
        int result = -1;
        if ((result = facultadPersonaMovilidad.indexOf(",2")) > -1) {
            mostrarEscuelaDocente = true;
            escuelaDocenteRequerido = false;
            onchangeListFacultadPersonaMovilidad();
        } else {
            mostrarEscuelaDocente = false;
            escuelaDocenteRequerido = true;
            onchangeListFacultadPersonaMovilidad();

        }

    }

    public void mostrarEscuelaReferenteFact() {
        int result = -1;
        if ((result = facultadDeReferente.indexOf(",2")) > -1) {
            mostrarEscuelaReferente = true;
            escuelaReferenteRequerido = false;
            onchangeListFacultadReferente();
        } else {
            mostrarEscuelaReferente = false;
            escuelaReferenteRequerido = true;
            onchangeListFacultadReferente();
        }
    }

    public void onchangeProgramaMovilidadList() {
        try {
            if (movilidad.getIdProgramaMovilidad() != null) {
                //programaMovilidad = programaMovilidadService.findById(programaMovilidadSelected.getIdProgramaMovilidad());
                programaMovilidad = movilidad.getIdProgramaMovilidad();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeTipoMovilidadList() {
        try {
            if (movilidad.getIdTipoMovilidad().getIdTipoMovilidad() != null) {
                //tipoMovilidad = tipoMovilidadService.findById(tipoMovilidadSelected);
                tipoMovilidad = movilidad.getIdTipoMovilidad();

                if (movilidad.getIdTipoMovilidad().getIdTipoMovilidad() == 1) { //movilidad entrante

                    mostrarEntrante = true;
                    mostrarSaliente = false;
                    mascaraTelefonoMovilidad = "";
                    mostrarBuscadorEntrante = true;
                    mostrarBuscadorSaliente = false;
                    //Seteando  pais destino por defecto
                    movilidad.setIdPaisDestino(222);
                    changePaisDestino();
                    movilidad.setIdUniversidadDestino(1);

                    movilidad.setIdPaisOrigen(null);
                    movilidad.setIdUniversidadOrigen(null);
                    onchangeListPaisOrigen();
                    facultadDocenteRequerido = false;
                    
                    personaMovilidadGenerico.setIdOrganismo(null);
                } else {  //movilidad Saliente
                    mostrarEntrante = false;
                    mostrarSaliente = true;
                    listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);//revisar esto
                    //mascaraTelefonoMovilidad = "(503)-9999-9999";
                    mascaraTelefonoMovilidad = "+503 #### ####";
                    mostrarBuscadorSaliente = true;
                    mostrarBuscadorEntrante = false;
                    personaMovilidadGenerico.setIdOrganismo(organismoService.findById(1));
                    //Setetando pais origen por defecto
                    movilidad.setIdPaisOrigen(222);
                    changePaisOrigen();
                    movilidad.setIdUniversidadOrigen(1);

                    movilidad.setIdPaisDestino(null);
                    movilidad.setIdUniversidadDestino(null);
                    onchangeListPaisDestino();

                    facultadDocenteRequerido = true;

                }

            } else {
                tipoMovilidad = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeListInstitucionPersonaMovilidad() {
        listFacultadByInst = facultadService.getFacultadesByUniversidad(personaMovilidadGenerico.getIdOrganismo().getIdOrganismo());
        listUnidadByInst = unidadService.getUnidadesByUniversidad(personaMovilidadGenerico.getIdOrganismo().getIdOrganismo());
        listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(listFacultadByInst, listUnidadByInst);
        codigoPais = paisService.findById(personaMovilidadGenerico.getIdOrganismo().getIdPais()).getCodigoPais();
        mascaraTelefonoMovilidad = telefonoService.getMask(codigoPais);

    }

    /**
     * Metodo para obtener una lista de las escuelas/departamentos de una
     * facultad para una persona que se encuentra en movilidad
     */
    public void onchangeListFacultadPersonaMovilidad() {
        int result = -1;
        Integer id;
        if ((result = facultadPersonaMovilidad.indexOf(",1")) > -1) { //facultad
            id = Integer.parseInt(facultadPersonaMovilidad.substring(0, result));
            listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(id);
            personaMovilidadGenerico.setIdUnidad(null);

        } else if ((result = facultadPersonaMovilidad.indexOf(",2")) > -1) { //unidad
            id = Integer.parseInt(facultadPersonaMovilidad.substring(0, result));
            //agregando la unidad seleccionada a una variable temporral
            //unidadPersonMovTmp = unidadService.findById(id);
            personaMovilidadGenerico.setIdUnidad(unidadService.findById(id));
            //escuelaDepartamentoPersonaMovilidad = null;
            personaMovilidadGenerico.setIdEscuelaDepto(null);
            listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();

        }
    }

    public void onchangeListPaisOrigen() {
        try {
            if (movilidad.getIdPaisOrigen() != null) {
                listOrganismosOrigen = listaOrganismos(movilidad.getIdPaisOrigen());
            } else {
                listOrganismosOrigen = new ArrayList<Organismo>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeListPaisDestino() {
        try {
            if (movilidad.getIdPaisDestino() != null) {
                //nota: validar si no se recibe ningun organismo
                //listOrganismosDestino = organismoService.getOrganismosPorPaisYTipo(movilidad.getIdPaisDestino(), 1);
                listOrganismosDestino = listaOrganismos(movilidad.getIdPaisDestino());
            } else {
                listOrganismosDestino = new ArrayList<Organismo>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeListFacultadReferente() {
        int result = -1;
        Integer id;
        if ((result = facultadDeReferente.indexOf(",1")) > -1) {  //facultad
            id = Integer.parseInt(facultadDeReferente.substring(0, result));
            listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(id);
            personaFacultadGenerico.setIdUnidad(null);
        } else if ((result = facultadDeReferente.indexOf(",2")) > -1) {  //unidad
            id = Integer.parseInt(facultadDeReferente.substring(0, result));
            //Afrefando la unidad seleccionada a una variable temporal
            //unidadRftFactTmp = unidadService.findById(id);
            personaFacultadGenerico.setIdUnidad(unidadService.findById(id));
            personaFacultadGenerico.setIdEscuelaDepto(null);
            listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();

        }
    }

    public void onchangeConsultoria() {
        if (consultoria == true) {
            disableConsultoria = false;
        } else {
            disableConsultoria = true;
        }
    }
  
    public void buscarPersonaEnMovilidadEntrante() {
        //Organismo organismoPersonaEnMovilidad = new Organismo();
        Facultad facultadPersonaEnMovilidad = new Facultad();
        Unidad unidadPersonaEnMovilidad = new Unidad();
        EscuelaDepartamento escuelaDeptoPersonaEnMovilidad = new EscuelaDepartamento();
        Persona personaMovilidadSelected = new Persona();
        Organismo institucion = new Organismo();
        List<Facultad> facultades = new ArrayList<Facultad>();
        List<Unidad> unidades = new ArrayList<Unidad>();
        try {
            if (!docSearchPersonaMovilidad.equalsIgnoreCase("")) {
                personaMovilidadSelected = personaService.getPersonaByPasaporte(docSearchPersonaMovilidad);
                personaMovilidadGenerico = personaMovilidadSelected;
                existePersonaMovilidad = true;

                //obteniendo la institucion de la persona en movilidad
                if ((institucion = personaMovilidadSelected.getIdOrganismo()) != null) {
                    institucionPersonaMovilidadSelected = institucion.getIdOrganismo();
                    facultades = facultadService.getFacultadesByUniversidad(institucion.getIdOrganismo());
                    unidades = unidadService.getUnidadesByUniversidad(institucion.getIdOrganismo());
                    listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(facultades, unidades);
                }

                //Obteniendo escuela_departamento y facultad de la persona seleccionada
                if ((escuelaDeptoPersonaEnMovilidad = personaMovilidadSelected.getIdEscuelaDepto()) != null) {
                    listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(escuelaDeptoPersonaEnMovilidad.getIdFacultad().getIdFacultad());
                    escuelaDepartamentoPersonaMovilidad = escuelaDeptoPersonaEnMovilidad.getIdEscuelaDepto();

                    facultadPersonaMovilidad = escuelaDeptoPersonaEnMovilidad.getIdFacultad().getIdFacultad().toString() + ",1";
                }
                if ((unidadPersonaEnMovilidad = personaMovilidadSelected.getIdUnidad()) != null) {
                    facultadPersonaMovilidad = unidadPersonaEnMovilidad.getIdUnidad().toString() + ",2";
                    listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                }

                List<Telefono> listTelefonosPersonaMovilidad = telefonoService.getTelefonosByPersona(personaMovilidadSelected);
                for (Telefono tlfx : listTelefonosPersonaMovilidad) {
                    if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaMovilidad = tlfx;
                    }
                    if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaMovilidad = tlfx;
                    }
                    //   if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                    //       faxPersonaMovilidad = tlfx;
                    //   }
                }
            }
        } catch (Exception e) {

        }

    }
  
    public void buscarPersonaEnMovilidadSaliente() {
        Facultad facultadPersonaEnMovilidad = new Facultad();
        Unidad unidadPersonaEnMovilidad = new Unidad();
        EscuelaDepartamento escuelaDeptoPersonaEnMovilidad = new EscuelaDepartamento();
        Persona personaMovilidadSelected = new Persona();

        try {
            if (!docSearchPersonaMovilidad.equalsIgnoreCase("")) {
                personaMovilidadSelected = personaService.getPersonaByDui(docSearchPersonaMovilidad);
                personaMovilidadGenerico = personaMovilidadSelected;
                existePersonaMovilidad = true;

                //Obteniendo escuela_departamento y facultad de la persona seleccionada
                if ((escuelaDeptoPersonaEnMovilidad = personaMovilidadSelected.getIdEscuelaDepto()) != null) {
                    listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(escuelaDeptoPersonaEnMovilidad.getIdFacultad().getIdFacultad());
                    escuelaDepartamentoPersonaMovilidad = escuelaDeptoPersonaEnMovilidad.getIdEscuelaDepto();

                    facultadPersonaMovilidad = escuelaDeptoPersonaEnMovilidad.getIdFacultad().getIdFacultad().toString() + ",1";
                }
                if ((unidadPersonaEnMovilidad = personaMovilidadSelected.getIdUnidad()) != null) {
                    facultadPersonaMovilidad = unidadPersonaEnMovilidad.getIdUnidad().toString() + ",2";
                    listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                }

                List<Telefono> listTelefonosPersonaMovilidad = telefonoService.getTelefonosByPersona(personaMovilidadSelected);
                for (Telefono tlfx : listTelefonosPersonaMovilidad) {
                    if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaMovilidad = tlfx;
                    }
                    if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaMovilidad = tlfx;
                    }
                    //    if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                    //        faxPersonaMovilidad = tlfx;
                    //    }
                }

            }
        } catch (Exception e) {

        }
    }

    
    public void buscarReferente() {
        EscuelaDepartamento escuelaDepartamentoRft = new EscuelaDepartamento();
        //Facultad facultadRft = new Facultad();
        Unidad unidadRft = new Unidad();
        Persona personaFacultadSelected = new Persona();
        try {
            if (!docSearchReferente.equalsIgnoreCase("")) {
                personaFacultadSelected = personaService.getRfteFacultadBeneficiadaByDoc(docSearchReferente);
                if (personaFacultadSelected != null) {
                    personaFacultadGenerico = personaFacultadSelected;
                    existeReferente = true;
                    //personaFacultadSelected = new Persona();

                    //Obteniendo escuela_departamento y facultad de la persona seleccionada
                    if ((escuelaDepartamentoRft = personaFacultadSelected.getIdEscuelaDepto()) != null) {
                        listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(escuelaDepartamentoRft.getIdFacultad().getIdFacultad());
                        escuelaDepartamentoReferenteFactBnfSelected = escuelaDepartamentoRft.getIdEscuelaDepto();

                        facultadDeReferente = escuelaDepartamentoRft.getIdFacultad().getIdFacultad().toString() + ",1";
                    }
                    if ((unidadRft = personaFacultadSelected.getIdUnidad()) != null) {
                        facultadDeReferente = unidadRft.getIdUnidad().toString() + ",2";
                        listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                    }

                    // escuelaDepartamentoReferenteFactBnfSelected = personaFacultadSelected.getIdEscuelaDepto().getIdEscuelaDepto();
                    //obteniendo telefonos de la persona seleccionada
                    List<Telefono> listTelefonosPersonaFacultad = telefonoService.getTelefonosByPersona(personaFacultadSelected);
                    for (Telefono tlfx : listTelefonosPersonaFacultad) {
                        if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                            telFijoPersonaFacultad = tlfx;
                        }
                        if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                            telCelPersonaFacultad = tlfx;
                        }
                        //  if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                        //      faxPersonaFacultad = tlfx;
                        //  }
                    }

                }
            }
        } catch (Exception e) {

        }
    }

    //metodo para boton que habilitar la edicion de campos de un docente en movilidad
    public void habilitarActualizarDocente() {
        habilitarCamposDocente();
        existePersonaMovilidad = true;
        fueEditadoDocente = true;

    }

    public void habilitarActualizacionReferente() {
        habilitarCamposReferente();
        existeReferente = true;
        fueEditadoReferente = true;
    }

    //metodo  que habilita los campos del docente en movilidad
    public void habilitarCamposDocente() {
        activarDocente = false;
        mostrarEscuelaDocente = false;

    }

    public void deshabilitarCamposDocente() {
        activarDocente = true;
        mostrarEscuelaDocente = true;
    }

    public void desabilitarCamposReferente() {
        activarReferente = true;
        mostrarEscuelaReferente = true;
    }

/**
 * metodo para habilitar campos del referente de la movilidad
 */    

    public void habilitarCamposReferente() {
        activarReferente = false;
        mostrarEscuelaReferente = false;
    }

/**
 * Metodo que habilita para agregar nuevo referente manualmente
 */    

    public void agregarNuevoReferente() {
        personaFacultadGenerico = new Persona();
        telFijoPersonaFacultad = new Telefono();
        telCelPersonaFacultad = new Telefono();
        facultadDeReferente = "";
        existeReferente = false;
        habilitarCamposReferente();
       

    }

/**
 * Metodo que habilita para agregar un nuevo docente manualmente
 */    

    public void agregarNuevoDocente() {
        personaMovilidadGenerico = new Persona();
        telFijoPersonaMovilidad = new Telefono();
        telCelPersonaMovilidad = new Telefono();
        facultadPersonaMovilidad = "";
        escuelaDepartamentoPersonaMovilidad = null;
        institucionPersonaMovilidadSelected = null;
        existePersonaMovilidad = false;
        habilitarCamposDocente();
    }

/**
 * Metodo que re inicializa variables y objetos relacionados a la persona en 
 * movilidad saliente
 */    

    public void limpiarSaliente() {
        personaMovilidadGenerico = new Persona();
        // personaMovilidadSeleccionado = new Persona();
        telFijoPersonaMovilidad = new Telefono();
        telCelPersonaMovilidad = new Telefono();
        facultadPersonaMovilidad = "";
        escuelaDepartamentoPersonaMovilidad = null;
        institucionPersonaMovilidadSelected = null;
    }

/**
 * Metodo inicializador de variables prebio a la busqueda de una persona 
 * en movilidad saliente
 */

    public void habilitarAutoSaliente() {
        flagSearchDuiSaliente = Boolean.FALSE;
        flagSearchEmailSaliente = Boolean.FALSE;
        flagSearchNombreSaliente = Boolean.FALSE;
        personaMovAux = new Persona();
        mostrarBotonEditarDocente = false;
        mostrarBotonNuevoDocente = false;
        limpiarSaliente();

        if (disableAutoSaliente) {
            disableAutoSaliente = Boolean.FALSE;
        }
        if (tipoBusquedaSaliente.equalsIgnoreCase("doc")) {
            flagSearchDuiSaliente = Boolean.TRUE;
        }
        if (tipoBusquedaSaliente.equalsIgnoreCase("nombre")) {
            flagSearchNombreSaliente = Boolean.TRUE;
        }
        if (tipoBusquedaSaliente.equalsIgnoreCase("email")) {
            flagSearchEmailSaliente = Boolean.TRUE;
        }

    }

/**
 * Metodo Buscar Persona Saliente
 * @param query
 * @return 
 */    
    public List<Persona> methodSearchSaliente(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            limpiarSaliente();
            if (tipoBusquedaSaliente.equalsIgnoreCase("nombre")) {
                listAll = personaService.getPersonaMovilidadSalienteByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoDocente = true;
                    mostrarBotonEditarDocente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupDocente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:groupPersonaMovilidad");
                }
                return list;
            } else if (tipoBusquedaSaliente.equalsIgnoreCase("email")) {
                listAll = personaService.getPersonaMovilidadSalienteByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoDocente = true;
                    mostrarBotonEditarDocente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupDocente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:groupPersonaMovilidad");
                }
                return list;
            } else if (tipoBusquedaSaliente.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                personaMovAux = personaService.getPersonaByDui(query);

                if (personaMovAux == null) {
                    limpiarSaliente();
                } else {
                    boolean add = list.add(personaMovAux);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoDocente = true;
                    mostrarBotonEditarDocente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupDocente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:groupPersonaMovilidad");
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
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona en movilidad saliente
     */
    public void cargarNombrePersonaSaliente() {
        try {

            if (personaMovAux.getIdPersona() != null) {
                personaMovilidadGenerico = personaMovAux;
                existePersonaMovilidad = true;
                usadoBuscadorPersonaMov = true;
                mostrarBotonEditarDocente = true;
                mostrarBotonNuevoDocente = false;

                //List<Telefono> listTelefonosPersonaMovilidad = telefonoService.getTelefonosByPersona(personaMovilidadGenerico);
                List<Telefono> listTelefonosPersonaMovilidad = personaMovilidadGenerico.getTelefonoList();

                for (Telefono us : listTelefonosPersonaMovilidad) {

                    if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaMovilidad = us;
                    }
                    if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaMovilidad = us;
                    }
                }
                if (personaMovilidadGenerico.getIdUnidad() == null || personaMovilidadGenerico.getIdEscuelaDepto() == null) {
                    listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                }
                if (personaMovilidadGenerico.getIdEscuelaDepto() != null) {
                    facultadPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";

                    listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    escuelaDepartamentoPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();
                }
                if (personaMovilidadGenerico.getIdUnidad() != null) {
                    facultadPersonaMovilidad = personaMovilidadGenerico.getIdUnidad().getIdUnidad() + ",2";
                    listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarReferente() {
        personaFacultadGenerico = new Persona();
        //personaFacultadSeleccionado = new Persona();
        telFijoPersonaFacultad = new Telefono();
        telCelPersonaFacultad = new Telefono();
        facultadDeReferente = "";
        escuelaDepartamentoReferenteFactBnfSelected = null;
    }

/**
 * Metodo que inicializa variables prebio a la busqueda de una persona referente
 */
    public void habilitarAutoReferente() {
        flagSearchDuiReferente = Boolean.FALSE;
        flagSearchEmailReferente = Boolean.FALSE;
        flagSearchNombreReferente = Boolean.FALSE;
        personaReftAux = new Persona();
        mostrarBotonEditarReferente = false;
        mostrarBotonNuevoReferente = false;
        limpiarReferente();

        if (disableAutoReferente) {
            disableAutoReferente = Boolean.FALSE;
        }
        if (tipoBusquedaReferente.equalsIgnoreCase("doc")) {
            flagSearchDuiReferente = Boolean.TRUE;
        }
        if (tipoBusquedaReferente.equalsIgnoreCase("nombre")) {
            flagSearchNombreReferente = Boolean.TRUE;
        }
        if (tipoBusquedaReferente.equalsIgnoreCase("email")) {
            flagSearchEmailReferente = Boolean.TRUE;
        }

    }

/**
 * Metodo Buscar Persona Referente
 * @param query
 * @return 
 */    
    public List<Persona> methodSearchReferente(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            limpiarReferente();
            if (tipoBusquedaReferente.equalsIgnoreCase("nombre")) {
                listAll = personaService.getPersonaMovilidadReferenteByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoReferente = true;
                    mostrarBotonEditarReferente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupReferente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupReferente");
                }
                
                return list;
            } else if (tipoBusquedaReferente.equalsIgnoreCase("email")) {
                listAll = personaService.getPersonaMovilidadReferenteByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoReferente = true;
                    mostrarBotonEditarReferente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupReferente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupReferente");
                }
                return list;
            } else if (tipoBusquedaReferente.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                personaReftAux = personaService.getPersonaByDui(query);
                if(personaReftAux == null){
                    limpiarReferente();
                }else{
                   boolean add = list.add(personaReftAux); 
                }
                
                if (list.isEmpty()) {
                    mostrarBotonNuevoReferente = true;
                    mostrarBotonEditarReferente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupReferente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupReferente");
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
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona Referente
     */
    public void cargarNombrePersonaReferente() {
        try {

            if (personaReftAux.getIdPersona() != null) {
                //personaFacultadGenerico = personaFacultadSeleccionado;
                usadoBuscadorPersonaRefte = true;
                existeReferente = true;
                mostrarBotonEditarReferente = true;
                mostrarBotonNuevoReferente = false;
                personaFacultadGenerico = personaReftAux;

                List<Telefono> listTelefonosPersonaReferente = personaFacultadGenerico.getTelefonoList();

                for (Telefono us : listTelefonosPersonaReferente) {

                    if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaFacultad = us;
                    }
                    if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaFacultad = us;
                    }
                }
                if (personaFacultadGenerico.getIdUnidad() == null || personaFacultadGenerico.getIdEscuelaDepto() == null) {
                    listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                }
                if (personaFacultadGenerico.getIdEscuelaDepto() != null) {
                    facultadDeReferente = personaFacultadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";

                    listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaFacultadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    escuelaDepartamentoReferenteFactBnfSelected = personaFacultadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();
                }
                if (personaFacultadGenerico.getIdUnidad() != null) {
                    facultadDeReferente = personaFacultadGenerico.getIdUnidad().getIdUnidad() + ",2";
                    listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limpiarEntrante() {
        personaMovilidadGenerico = new Persona();
        telFijoPersonaMovilidad = new Telefono();
        telCelPersonaMovilidad = new Telefono();
        facultadPersonaMovilidad = "";
        escuelaDepartamentoPersonaMovilidad = null;
        institucionPersonaMovilidadSelected = null;
    }

/**
 *Metodo que inicializa variables prebio a la busqueda de una persona en movilidad entrante 
 */    

    public void habilitarAutoEntrante() {
        flagSearchDuiEntrante = Boolean.FALSE;
        flagSearchEmailEntrante = Boolean.FALSE;
        flagSearchNombreEntrante = Boolean.FALSE;
        personaMovAux = new Persona();
        mostrarBotonNuevoDocente = false;
        limpiarEntrante();

        mostrarBotonEditarDocente = false;

        if (disableAutoEntrante) {
            disableAutoEntrante = Boolean.FALSE;
        }
        if (tipoBusquedaEntrante.equalsIgnoreCase("doc")) {
            flagSearchDuiEntrante = Boolean.TRUE;
        }
        if (tipoBusquedaEntrante.equalsIgnoreCase("nombre")) {
            flagSearchNombreEntrante = Boolean.TRUE;
        }
        if (tipoBusquedaEntrante.equalsIgnoreCase("email")) {
            flagSearchEmailEntrante = Boolean.TRUE;
        }

    }

/**
 * Metodo Buscar Persona Entrante
 * @param query
 * @return 
 */    
    public List<Persona> methodSearchEntrante(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            limpiarEntrante();
            if (tipoBusquedaEntrante.equalsIgnoreCase("nombre")) {
                listAll = personaService.getPersonaMovilidadEntranteByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoDocente = true;
                    mostrarBotonEditarDocente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupDocente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:groupPersonaMovilidad");
                }
                
                return list;
            } else if (tipoBusquedaEntrante.equalsIgnoreCase("email")) {
                listAll = personaService.getPersonaMovilidadEntranteByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoDocente = true;
                    mostrarBotonEditarDocente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupDocente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:groupPersonaMovilidad");
                }
                
                return list;
            } else if (tipoBusquedaEntrante.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                personaMovAux = personaService.getPersonaByDui(query);
                if (personaMovAux == null) {
                    limpiarEntrante();
                } else {
                    boolean add = list.add(personaMovAux);
                }
                if (list.isEmpty()) {
                    mostrarBotonNuevoDocente = true;
                    mostrarBotonEditarDocente = false;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:grupDocente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:groupPersonaMovilidad");
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
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona en movilidad entrante
     */
    public void cargarNombrePersonaEntrante() {
        Organismo institucion = new Organismo();
        List<Facultad> facultades = new ArrayList<Facultad>();
        List<Unidad> unidades = new ArrayList<Unidad>();
        try {

            if (personaMovAux.getIdPersona() != null) {
                //personaMovilidadGenerico = personaMovilidadSeleccionado;
                usadoBuscadorPersonaMov = true;
                existePersonaMovilidad = true;
                mostrarBotonEditarDocente = true;
                mostrarBotonNuevoDocente = false;
                personaMovilidadGenerico = personaMovAux;

                List<Telefono> listTelefonosPersonaMovilidad = personaMovilidadGenerico.getTelefonoList();

                for (Telefono us : listTelefonosPersonaMovilidad) {

                    if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaMovilidad = us;
                    }
                    if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaMovilidad = us;
                    }
                }
                 //Para obtener la maskara para el telefono de la persona eztrangera
                 codigoPais = paisService.findById(personaMovilidadGenerico.getIdOrganismo().getIdPais()).getCodigoPais();
                 mascaraTelefonoMovilidad = telefonoService.getMask(codigoPais);

                if ((institucion = personaMovilidadGenerico.getIdOrganismo()) != null) {
                    institucionPersonaMovilidadSelected = personaMovilidadGenerico.getIdOrganismo().getIdOrganismo();
                    facultades = facultadService.getFacultadesByUniversidad(institucion.getIdOrganismo());
                    unidades = unidadService.getUnidadesByUniversidad(institucion.getIdOrganismo());
                    listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(facultades, unidades);
                }

                if (personaMovilidadGenerico.getIdUnidad() == null || personaMovilidadGenerico.getIdEscuelaDepto() == null) {
                    listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                }
                if (personaMovilidadGenerico.getIdEscuelaDepto() != null) {
                    facultadPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";

                    listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    escuelaDepartamentoPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();
                }
                if (personaMovilidadGenerico.getIdUnidad() != null) {
                    facultadPersonaMovilidad = personaMovilidadGenerico.getIdUnidad().getIdUnidad() + ",2";
                    listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para guardar en arreglos las facultades y unidades beneficiadas
     */
    public void getArreglosFacultadesUnidadesBeneficiadas() {
        int result;
        listFacultadesBeneficiadasSelected = new ArrayList<String>();
        listUnidadesBeneficiadasSelected = new ArrayList<String>();
        String[] facultadesUnidadesBeneficiadasSelected = new String[listFacultadesUnidadesBeneficiadasSelected.size()];
        facultadesUnidadesBeneficiadasSelected = listFacultadesUnidadesBeneficiadasSelected.toArray(facultadesUnidadesBeneficiadasSelected);
        try {
            for (int i = 0; i < facultadesUnidadesBeneficiadasSelected.length; i++) {
                result = -1;
                if ((result = facultadesUnidadesBeneficiadasSelected[i].indexOf(",1")) > -1) {
                    listFacultadesBeneficiadasSelected.add(facultadesUnidadesBeneficiadasSelected[i].substring(0, result));
                } else if ((result = facultadesUnidadesBeneficiadasSelected[i].indexOf(",2")) > -1) {
                    listUnidadesBeneficiadasSelected.add(facultadesUnidadesBeneficiadasSelected[i].substring(0, result));
                }
            }

            //Invocando a metodos para agregar las facultades y unidades beneficiadas a la instancia de Movilidad
            addFacultadesBeneficiadas(listFacultadesBeneficiadasSelected);
            addUnidadesBeneficiadas(listUnidadesBeneficiadasSelected);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo para agregar las facultades beneficiadas a la instancia de
     * Movilidad
     */
    public void addFacultadesBeneficiadas(List<String> facultadesBeneficiadas) {
        try {
            String[] facultadesBeneficiadasSelected = new String[facultadesBeneficiadas.size()];
            facultadesBeneficiadasSelected = facultadesBeneficiadas.toArray(facultadesBeneficiadasSelected);

            for (int i = 0; i < facultadesBeneficiadasSelected.length; i++) {

                Facultad facultadBnf = facultadService.findById(Integer.parseInt(facultadesBeneficiadasSelected[i]));
                if (facultadBnf != null) {
                    listFacultadAdd.add(facultadBnf);

                }
            }
            movilidad.setFacultadList(listFacultadAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo para agregar las Unidades beneficiadas a la instancia de Movilidad
     */
    public void addUnidadesBeneficiadas(List<String> unidadesBeneficiadas) {
        // listUnidadAdd.clear();
        try {
            String[] unidadesBeneficiadasSelected = new String[unidadesBeneficiadas.size()];
            unidadesBeneficiadasSelected = unidadesBeneficiadas.toArray(unidadesBeneficiadasSelected);
            for (int i = 0; i < unidadesBeneficiadasSelected.length; i++) {

                Unidad unidadBnf = unidadService.findById(Integer.parseInt(unidadesBeneficiadasSelected[i]));
                if (unidadBnf != null) {
                    listUnidadAdd.add(unidadBnf);
                }
            }
            movilidad.setUnidadList(listUnidadAdd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para unir en una lista las Sub listas de facultad y unidad
     */
    private List<PojoFacultadesUnidades> getListFacultadesUnidades(List<Facultad> facs, List<Unidad> unidades) {

        List<PojoFacultadesUnidades> lista = new ArrayList<PojoFacultadesUnidades>();
        for (Facultad fac : facs) {
            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();
            pojo.setValue(fac.getIdFacultad() + ",1");
            pojo.setLabel(fac.getNombreFacultad());
            lista.add(pojo);
        }
        for (Unidad uni : unidades) {
            PojoFacultadesUnidades pojo = new PojoFacultadesUnidades();
            pojo.setValue(uni.getIdUnidad() + ",2");
            pojo.setLabel(uni.getNombreUnidad());
            lista.add(pojo);
        }
        return lista;
    }

/**
 * Metodo para realizar operaciones previas al guardado de una movilidad
 */    
    public void preGuardadoMovilidad() {
        //comprobando si las personas tienen asignada su escuela o unidad
        // if (personaMovilidadGenerico.getIdEscuelaDepto() != null || personaMovilidadGenerico.getIdUnidad() != null) {
        if (personaFacultadGenerico.getIdEscuelaDepto() != null || personaFacultadGenerico.getIdUnidad() != null) {
            if (fueEditadoDocente == true) {
                RequestContext context = RequestContext.getCurrentInstance();
                context.execute("PF('dlgEdicionDocente').show();");
            } else {
                preGuardadoReferente();
            }
        } else {
            //mensage facultad referente es un campo requerido
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Requerido!", "Facultad/Unidad, para la persona Referente es un campo requerido"));
        }
       

    }

    public void preGuardadoReferente() {
        if (fueEditadoReferente == true) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dlgEdicionReferente').show();");
        } else {
            guardadoMovilidad();
        }

    }

    public void siModificarDocente() {
        siEditarDocente = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgEdicionDocente').hide();");
        preGuardadoReferente();

    }

    public void noModificarDocente() {
        siEditarDocente = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgEdicionDocente').hide();");
        preGuardadoReferente();
    }

    public void siModificarReferente() {
        siEditarReferente = true;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgEdicionReferente').hide();");
        guardadoMovilidad();
    }

    public void noModificarReferente() {
        siEditarReferente = false;
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgEdicionReferente').hide();");
        guardadoMovilidad();
    }

/**
 * Metodo para realizar el guardado de una movilidad
 */
    public void guardadoMovilidad() {
        String msg = "Movilidad guardada exitosamente!!";
        try {
            //PERSONAS 
            if (siEditarDocente == false && existePersonaMovilidad == true) { //Si no se desea editar un docente existente

            } else {  //si se desea guardar o editar el docente
                //Seteando Datos adicionales de persona en movilidad  
                if (movilidad.getIdTipoMovilidad().getIdTipoMovilidad() == 1) { //Si es Entrante
                    personaMovilidadGenerico.setActivo(true);
                    personaMovilidadGenerico.setExtranjero(true);
                    personaMovilidadGenerico.setDuiPersona("00000000-0");
 
                } else {   //Si es saliente
                    personaMovilidadGenerico.setActivo(true);
                    personaMovilidadGenerico.setExtranjero(false);
                    personaMovilidadGenerico.setIdOrganismo(institucionUES); //revisar esto
                    personaMovilidadGenerico.setPasaporte("--");
 
                }
                //Telefonos de persona en movilidad Saliente
                //Fijo
                personaMovilidadGenerico.getTelefonoList().clear();
                telFijoPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                telFijoPersonaMovilidad.setIdTipoTelefono(tipoTelefonoFijo);
                personaMovilidadGenerico.getTelefonoList().add(telFijoPersonaMovilidad);
                //Celular
                telCelPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                telCelPersonaMovilidad.setIdTipoTelefono(tipoTelefonoCel);
                personaMovilidadGenerico.getTelefonoList().add(telCelPersonaMovilidad);

                //guardado-actualizado de persona en movilidad
                if (existePersonaMovilidad == false && actualizar == false) { //se esta digitando la persona directamente
                    personaService.save(personaMovilidadGenerico);
                } else if ((existePersonaMovilidad == true && siEditarDocente == true) || actualizar == true) {
                    personaService.merge(personaMovilidadGenerico);
                }
            }

            if (siEditarReferente == false && existeReferente == true) { //Si no se quiere modificar los datos de una persona existente

            } else {
                //Si el referente pertenece a una unidad
                if (unidadRftFactTmp != null) {
                    personaFacultadGenerico.setIdUnidad(unidadRftFactTmp);
                    personaFacultadGenerico.setIdEscuelaDepto(null);
                    escuelaDepartamentoReferenteFactBnfSelected = null;
                }

                //datos adicionales de persona Referente Facultad Beneficiada
                personaFacultadGenerico.setActivo(true);
                personaFacultadGenerico.setExtranjero(false);
                personaFacultadGenerico.setIdOrganismo(organismoService.findById(1));
                personaFacultadGenerico.setPasaporte("--");

                //Telefonos Referente de la facultad
                personaFacultadGenerico.getTelefonoList().clear();
                //Fijo
                telFijoPersonaFacultad.setIdPersona(personaFacultadGenerico);
                telFijoPersonaFacultad.setIdTipoTelefono(tipoTelefonoFijo);
                personaFacultadGenerico.getTelefonoList().add(telFijoPersonaFacultad);
                //Cel
                telCelPersonaFacultad.setIdPersona(personaFacultadGenerico);
                telCelPersonaFacultad.setIdTipoTelefono(tipoTelefonoCel);
                personaFacultadGenerico.getTelefonoList().add(telCelPersonaFacultad);

                //Guardado-actualizado referente de la facultad
                if ((existeReferente == false && actualizar == false) || (existeReferente == false && desvinculadoRfte == true)) {
                    personaService.save(personaFacultadGenerico);
                } else if ((existeReferente == true && siEditarReferente == true) || actualizar == true) {
                    personaService.merge(personaFacultadGenerico);
                }
            }

            //Seteando datos de la movilidad
            if (movilidad.getEntregaDeInforme() == null) {
                movilidad.setEntregaDeInforme(false);
            }
            if (movilidad.getObsequio() == null) {
                movilidad.setObsequio(false);
            }
            if (movilidad.getPagoDeCurso() == null) {
                movilidad.setPagoDeCurso(new BigDecimal(0.00));
            } else {
                TipoCambio aux = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
                BigDecimal d = movilidad.getPagoDeCurso();
                d = d.multiply(aux.getDolaresPorUnidad());
                movilidad.setPagoDeCurso(d);
            }
            if (movilidad.getViaticos() == null) {
                movilidad.setViaticos(new BigDecimal(0.00));
            } else {
                TipoCambio aux = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
                BigDecimal d = movilidad.getViaticos();
                d = d.multiply(aux.getDolaresPorUnidad());
                movilidad.setViaticos(d);
            }

            if (movilidad.getVoletoAereo() == null) {
                movilidad.setVoletoAereo(new BigDecimal(0.00));
            } else {
                TipoCambio aux = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
                BigDecimal d = movilidad.getVoletoAereo();
                d = d.multiply(aux.getDolaresPorUnidad());
                movilidad.setVoletoAereo(d);
            }

            if (movilidad.getCostoConsultoria() == null) {
                movilidad.setCostoConsultoria(new BigDecimal(0.00));
            } else {
                TipoCambio aux = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
                BigDecimal d = movilidad.getCostoConsultoria();
                d = d.multiply(aux.getDolaresPorUnidad());
                movilidad.setCostoConsultoria(d);
            }

            //Agregando a la movilidad las facultades o unidades beneficiadas
            getArreglosFacultadesUnidadesBeneficiadas();

            //Primer guardado-merge de la Movilidad   
            if (existeMovilidad == true) {
                movilidadService.merge(movilidad);
            } else {
                movilidad.setFechaIngreso(new Date());
                movilidadService.save(movilidad);

            }

            //Ligando personas a la movilidad
            //Ligando persona en movilidad
            if (actualizarPersonaMov == false) {
                //Guardando en tabla intermedia de persona movilidad
                PersonaMovilidadPK personaEnMovilidadPK = new PersonaMovilidadPK();
                personaEnMovilidadPK.setIdPersona(personaMovilidadGenerico.getIdPersona());
                personaEnMovilidadPK.setIdMovilidad(movilidad.getIdMovilidad());

                PersonaMovilidad personaEnMovilidad = new PersonaMovilidad();
                personaEnMovilidad.setMovilidad(movilidad);
                personaEnMovilidad.setPersona(personaMovilidadGenerico);
                personaEnMovilidad.setIdTipoPersona(tipoPersonaMovilidad);
                personaEnMovilidad.setPersonaMovilidadPK(personaEnMovilidadPK);
                movilidad.getPersonaMovilidadList().add(personaEnMovilidad);
            }
            //Ligando referente de la facultad
            if (desvinculadoRfte == true) {
                PersonaMovilidadPK personaMovilidadReferenteFactPK = new PersonaMovilidadPK();
                personaMovilidadReferenteFactPK.setIdPersona(personaFacultadGenerico.getIdPersona());
                personaMovilidadReferenteFactPK.setIdMovilidad(movilidad.getIdMovilidad());

                PersonaMovilidad personaMovilidadReferenteFact = new PersonaMovilidad();
                personaMovilidadReferenteFact.setMovilidad(movilidad);
                personaMovilidadReferenteFact.setPersona(personaFacultadGenerico);
                personaMovilidadReferenteFact.setIdTipoPersona(tipoPersonaReferenteFact);
                personaMovilidadReferenteFact.setPersonaMovilidadPK(personaMovilidadReferenteFactPK);
                movilidad.getPersonaMovilidadList().add(personaMovilidadReferenteFact);
            }

            //Actualizando la movilidad
            movilidadService.merge(movilidad);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar!!", msg));
            //cargarMovilidadPersona();
            regresar();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Persona comprobarEmail(String emailIngresado) {
        Persona persona = null;
        if ((persona = personaService.existePersonaByMail(emailIngresado)) != null) {

            return persona;
        }
        return null;
    }

    /**
     * Metodo para redireccionar a vista de crear nueva movilidad
     */
    public void irnuevaMovilidad() throws IOException {
        cargarMovilidadPersona();
        tituloRegistroEdicion = "Registro de movilidad";
        mostrarEscuelaDocente = true; //desabilita el select escuelaDepartamento del docente
        mostrarEscuelaReferente = true;
        FacesContext.getCurrentInstance().getExternalContext().redirect("registrarMovilidad.xhtml");
    }

    /**
     * Metodo para obtener los datos de la movilidad a modificar
     *
     * @param idMovilidad
     *
     */
    public void preActualizar(Integer idMovilidad) {
        Movilidad movilidadAux = new Movilidad();
        //Integer tipoMovilidad;
        List<Integer> facultadTmp = new ArrayList<Integer>();
        List<Integer> unidadTmp = new ArrayList<Integer>();
        List<String> facultadesUnidadesTmp = new ArrayList<String>();
        EscuelaDepartamento escuelaDepto = new EscuelaDepartamento();
        EscuelaDepartamento escuelaDeptoReferente = new EscuelaDepartamento();
        //mostrarBotonEditarDocente = true;

        mostrarBotonDesvincular = true;
        tituloRegistroEdicion = "Actualizaci&oacute;n de movilidad";
        txtBotonGuardar = "Actualizar";
        txtBotonRegresar = "Cancelar";
        iconRegresarCancelar = "ui-icon-cancel";
        iconGuardarActualizar = "ui-icon-pencil";

        //deshabilita el selectOnmenu de tipo de movilidad
        isHabilidado = Boolean.TRUE;
        actualizar = true;
        actualizarPersonaMov = true;
        habilitarCamposDocente();
        habilitarCamposReferente();
        existePersonaMovilidad = true;
        existeReferente = true;
        siEditarDocente = true;
        siEditarReferente = true;

        try {
            if ((movilidadAux = movilidadService.findById(idMovilidad)) != null) {
                movilidad = movilidadAux;
                existeMovilidad = true;
                //Estableciendo el tipo de cambio por defecto
                tipoCambioSelected.setIdTipoCambio(2);

                if (movilidad.getCostoConsultoria() != null) {
                    consultoria = true;
                    onchangeConsultoria();
                }

                programaMovilidad = programaMovilidadService.findById(movilidad.getIdProgramaMovilidad().getIdProgramaMovilidad());

                onchangeListPaisOrigen();
                onchangeListPaisDestino();

                //Cargando lista de facultade-unidades Beneficiadas
                facultadTmp = facultadService.getFacultadesMovilidad(movilidad.getIdMovilidad());
                unidadTmp = unidadService.getUnidadesMovilidad(movilidad.getIdMovilidad());

                for (Integer f : facultadTmp) {
                    facultadesUnidadesTmp.add(String.valueOf(f) + ",1");
                }
                for (Integer u : unidadTmp) {
                    facultadesUnidadesTmp.add(String.valueOf(u) + ",2");
                }

                listFacultadesUnidadesBeneficiadasSelected = facultadesUnidadesTmp;

                //Cargando las Personas
                //Persona en movilidad
                personaMovilidadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "DOCENTE EN MOVILIDAD");
                existePersonaMovilidad = true;

                //escuelaDepto = personaMovilidadGenerico.getIdEscuelaDepto();
                if (movilidad.getIdTipoMovilidad().getIdTipoMovilidad() == 2) { //movilidad Saliente
                    mostrarEntrante = false;
                    mostrarSaliente = true;
                    mascaraTelefonoMovilidad = "+503 #### ####";

                    //Cargando lista de facultades y unidades
                    listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);

                    //Si la persona pertenece a una escuala o departamento
                    if (personaMovilidadGenerico.getIdEscuelaDepto() != null) {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) + ",1";

                        listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadPersonaMovilidad.substring(0, facultadPersonaMovilidad.indexOf(",1"))));
                        // escuelaDepartamentoPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();

                    } else {
                        if (personaMovilidadGenerico.getIdUnidad() != null) {
                            facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                            listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                            escuelaDepartamentoPersonaMovilidad = null;
                        }

                    }

                } else {//movilidad Entrante
                    mostrarEntrante = true;
                    mostrarSaliente = false;
                    mascaraTelefonoMovilidad = "";

                    //institucionPersonaMovilidadSelected = personaMovilidadGenerico.getIdOrganismo().getIdOrganismo();
                    onchangeListInstitucionPersonaMovilidad();
                    if (personaMovilidadGenerico.getIdEscuelaDepto() != null) {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) + ",1";

                        listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadPersonaMovilidad.substring(0, facultadPersonaMovilidad.indexOf(",1"))));
                        //escuelaDepartamentoPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();

                    } else {
                        if (personaMovilidadGenerico.getIdUnidad() != null) {
                            facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                            listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                            escuelaDepartamentoPersonaMovilidad = null;
                        }
                    }

                }
                //obteniendo telefonos de la persona en movilidad
                for (Telefono tlfx : personaMovilidadGenerico.getTelefonoList()) {
                    if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaMovilidad = tlfx;
                    }
                    if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaMovilidad = tlfx;
                    }
                    //    if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                    //        faxPersonaMovilidad = tlfx;
                    //    }
                }

                //Persona Referente Facultad
                if ((personaFacultadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "REFERENTE FACULTAD BENEFICIADA")) != null) {;
                    isHabilitadoRfte = true;
                    existeReferente = true;
                    desvinculadoRfte = false;
                    habilitarBuscadorReferente = true; 

                    //escuelaDeptoReferente = personaFacultadGenerico.getIdEscuelaDepto();
                    if (personaFacultadGenerico.getIdEscuelaDepto() != null) {
                        facultadDeReferente = Integer.toString(personaFacultadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) + ",1";
                        listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadDeReferente.substring(0, facultadDeReferente.indexOf(",1"))));
                        //escuelaDepartamentoReferenteFactBnfSelected = personaFacultadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();
                    } else {
                        if (personaFacultadGenerico.getIdUnidad() != null) {
                            facultadDeReferente = Integer.toString(personaFacultadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                            listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                            //escuelaDepartamentoReferenteFactBnfSelected = null;
                        }

                    }
                    //obteniendo telefonos de la persona en movilidad
                    for (Telefono tlfx : personaFacultadGenerico.getTelefonoList()) {
                        if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                            telFijoPersonaFacultad = tlfx;
                        }
                        if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                            telCelPersonaFacultad = tlfx;
                        }
                        //  if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                        //      faxPersonaFacultad = tlfx;
                        //  }
                    }

                } else {

                    personaFacultadGenerico = new Persona();
                    facultadDeReferente = null;
                    escuelaDepartamentoReferenteFactBnfSelected = null;
                    telFijoPersonaFacultad = new Telefono();
                    telCelPersonaFacultad = new Telefono();
                    isHabilitadoRfte = Boolean.FALSE;

                    //desabilitando campos del refrente  
                    mostrarBotonEditarReferente = false; //ocultar boton de editar referente
                    mostrarBotonDesvincular = false;     //ocultar el boton de desvincular el referente
                    //deshabilitarCamposDocente();
                    desabilitarCamposReferente();
                    habilitarBuscadorReferente = false; //habilita el buscador de referente
                }
               

            }

            FacesContext.getCurrentInstance().getExternalContext().redirect("registrarMovilidad.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Persona getPersonaMovilidad(List<PersonaMovilidad> lista, String tipo) {
        Persona p = new Persona();
        for (PersonaMovilidad per : lista) {
            if (per.getIdTipoPersona().getNombreTipoPersona().equalsIgnoreCase(tipo)) {
                return personaService.getByID(per.getPersona().getIdPersona());

            }
        }
        return null;
    }

/**
 * Metodo para desvincular una persona referente de una movilidad
 */    

    public void desvincularReferente() {
        try {
            movilidadService.desvincularReferente(movilidad.getIdMovilidad(), personaFacultadGenerico.getIdPersona());
            existeReferente = false;
            desvinculadoRfte = true;
            //recargando la vista de actualizar movilidad
            preActualizar(movilidad.getIdMovilidad());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

/**
 * Metodo para lanzar modal que solicita confirmacion de la operacion de 
 * desvinculacion de un referente
 */    

    public void confirmarDesvincularReferente() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgConfirmarDesvincularReferente').show();");
    }

    /**
     * Meetodo para Consultar una Movilidad
     *
     */
    public void consultarMovilidad(Integer idMovilidad) {
        //Movilidad movilidad;
        Integer tipoMovilidad;
        List<Integer> facultadTmp = new ArrayList<Integer>();
        List<Integer> unidadTmp = new ArrayList<Integer>();
        List<String> facultadesUnidadesTmp = new ArrayList<String>();
        EscuelaDepartamento escuelaDepto = null;
        EscuelaDepartamento escuelaDeptoReferente = null;

        try {
            if ((movilidad = movilidadService.findById(idMovilidad)) != null) {
                existeMovilidad = true;

                //tipoMovilidad = movilidad.getIdTipoMovilidad().getIdTipoMovilidad();
                tipoMovilidadConsultar = movilidad.getIdTipoMovilidad().getNombreTipoMovilidad();

                //programaMovilidadSelected.setIdProgramaMovilidad(movilidad.getIdProgramaMovilidad().getIdProgramaMovilidad());
                programaMovilidad = programaMovilidadService.findById(movilidad.getIdProgramaMovilidad().getIdProgramaMovilidad());
                categoriaMovilidadConsultar = movilidad.getIdCategoria().getNombreCategoria();

                //Cargando datos de detalle de la movilidad
                //paisOrigenSelected = movilidad.getIdPaisOrigen();
                paisOrigenConsultar = paisService.findById(movilidad.getIdPaisOrigen()).getNombrePais();

                //institucionOrigenSelected = movilidad.getIdUniversidadOrigen();
                institucionOrigenConsultar = organismoService.findById(movilidad.getIdUniversidadOrigen()).getNombreOrganismo();

                //paisDestinoSelected = movilidad.getIdPaisDestino();
                paisDestinoconsultar = paisService.findById(movilidad.getIdPaisDestino()).getNombrePais();
                //institucionDestinoSelected = movilidad.getIdUniversidadDestino();
                institucionDestinoConsultar = organismoService.findById(movilidad.getIdUniversidadDestino()).getNombreOrganismo();

                if (movilidad.getFechaInicio() != null) {
                    fInicioConsultar = sdf.format(movilidad.getFechaInicio());
                }

                if (movilidad.getFechaFin() != null) {
                    fFinConsultar = sdf.format(movilidad.getFechaFin());
                }

                //Tipo de cambio
                tipoCambioSelected = tipoCambioService.findById(2);
                listTipoCambio.clear();
                listTipoCambio.add(tipoCambioSelected);

                //this.movilidad.setViaticos(movilidad.getViaticos());
                //this.movilidad.setPagoDeCurso(movilidad.getPagoDeCurso());
                //this.movilidad.setVoletoAereo(movilidad.getVoletoAereo());
                if (movilidad.getFechaEntregaMined() != null) {
                    fEntregaConsultar = sdf.format(movilidad.getFechaEntregaMined());
                }

                //etapaMovilidadSelected = movilidad.getIdEtapaMovilidad().getIdEtapa();
                etapaMovilidadConsultar = etapamovilidadService.findById(movilidad.getIdEtapaMovilidad().getIdEtapa()).getNombreEtapa();
                if (movilidad.getEntregaDeInforme() != null) {
                    if (movilidad.getEntregaDeInforme() == Boolean.TRUE) {
                        entregaInformeConsultar = "SI";
                    } else {
                        entregaInformeConsultar = "NO";
                    }
                }

                //Total de viaticos mas curso
                totalViaticosCurso = movilidad.getViaticos().floatValue() + movilidad.getPagoDeCurso().floatValue();

                //Total viaticos curos y voleto aereo
                totalViaticosCursoBoletoAereo = totalViaticosCurso + movilidad.getVoletoAereo().floatValue();

                facultadTmp = facultadService.getFacultadesMovilidad(movilidad.getIdMovilidad());
                unidadTmp = unidadService.getUnidadesMovilidad(movilidad.getIdMovilidad());

                for (Integer f : facultadTmp) {
                    facultadesUnidadesTmp.add(String.valueOf(f) + ",1");
                }
                for (Integer u : unidadTmp) {
                    facultadesUnidadesTmp.add(String.valueOf(u) + ",2");
                }
                //facultadesUnidadesBeneficiadasSelected = new String[facultadesUnidadesTmp.size()];
                //facultadesUnidadesTmp.toArray(facultadesUnidadesBeneficiadasSelected);
                listFacultadesUnidadesBeneficiadasSelected = facultadesUnidadesTmp;

                //this.movilidad.setOtrosBeneficiados(movilidad.getOtrosBeneficiados());
                //Cargando las Personas
                //Persona en movilidad
                personaMovilidadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "DOCENTE EN MOVILIDAD"); //REVISAR ESTO
                //existePersonaMovilidad = true;
                //escuelaDepto = personaMovilidadGenerico.getIdEscuelaDepto();
                if (movilidad.getIdTipoMovilidad().getIdTipoMovilidad() == 2) { //movilidad Saliente
                    mostrarEntrante = false;
                    mostrarSaliente = true;

                    if (personaMovilidadGenerico.getIdEscuelaDepto() != null) {
                        facultadPersonaMovConsultar = personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getNombreFacultad();
                        escuelaDeptoPersonaMovConsultar = personaMovilidadGenerico.getIdEscuelaDepto().getNombreEscuelaDepto();

                    } else {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                        facultadPersonaMovConsultar = personaMovilidadGenerico.getIdUnidad().getNombreUnidad();
                        escuelaDeptoPersonaMovConsultar = "";
                    }

                } else {//movilidad Entrante
                    mostrarEntrante = true;
                    mostrarSaliente = false;

                    institucionPersonaMovConsultar = personaMovilidadGenerico.getIdOrganismo().getNombreOrganismo();
                    if (personaMovilidadGenerico.getIdEscuelaDepto() != null) {
                        facultadPersonaMovConsultar = personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getNombreFacultad();

                        escuelaDeptoPersonaMovConsultar = personaMovilidadGenerico.getIdEscuelaDepto().getNombreEscuelaDepto();

                    } else {
                        if (personaMovilidadGenerico.getIdUnidad() != null) {
                            facultadPersonaMovConsultar = personaMovilidadGenerico.getIdUnidad().getNombreUnidad();
                            escuelaDeptoPersonaMovConsultar = "";
                        }

                    }

                }
                //obteniendo telefonos de la persona en movilidad
                for (Telefono tlfx : personaMovilidadGenerico.getTelefonoList()) {
                    if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaMovilidad = tlfx;
                    }
                    if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaMovilidad = tlfx;
                    }
                    //    if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                    //        faxPersonaMovilidad = tlfx;
                    //    }
                }

                //Persona Referente Facultad
                personaFacultadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "REFERENTE FACULTAD BENEFICIADA"); 
                //existeReferente = true;
                //escuelaDeptoReferente = personaFacultadGenerico.getIdEscuelaDepto();
                if (personaFacultadGenerico.getIdEscuelaDepto() != null) {
                    facultadPersonaRefConsultar = personaFacultadGenerico.getIdEscuelaDepto().getIdFacultad().getNombreFacultad();
                    escuelaDepartamentoRefConsultar = personaFacultadGenerico.getIdEscuelaDepto().getNombreEscuelaDepto();
                } else {
                    if (personaFacultadGenerico.getIdUnidad() != null) {
                        facultadPersonaRefConsultar = personaFacultadGenerico.getIdUnidad().getNombreUnidad();
                        escuelaDepartamentoRefConsultar = "";
                    }
                }

                //obteniendo telefonos de la persona en movilidad
                for (Telefono tlfx : personaFacultadGenerico.getTelefonoList()) {
                    if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telFijoPersonaFacultad = tlfx;
                    }
                    if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telCelPersonaFacultad = tlfx;
                    }
                    //  if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
                    //      faxPersonaFacultad = tlfx;
                    //  }
                }

                //Actualizando el Panel de la persona en movilidad
                RequestContext.getCurrentInstance().update("panelPersonaEnMovilidad");

            }

            FacesContext.getCurrentInstance().getExternalContext().redirect("consultarMovilidad.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/**
 * Metodo para regresar a la pagina de Administarcion de movilidades
 */    

    public void regresar() {
        String msg = "Falta Referente de Facultad ";
        try {

            if (actualizar == true) {
                if ((movilidadService.isVinculadoReferente(movilidad.getIdMovilidad(), personaFacultadGenerico.getIdPersona())) != null) {
                    cargarMovilidadPersona();
                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

                    String outcome = "movilidadAdm.xhtml";
                    FacesContext.getCurrentInstance().getExternalContext().redirect("movilidadAdm.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Requerido", msg));
                }

            } else {
                cargarMovilidadPersona();
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

                String outcome = "movilidadAdm.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect("movilidadAdm.xhtml");
            }

        } catch (Exception e) {
        }
    }

    public void addNewCategoriaMovilidadIfIsNecesary() {
        if (movilidad.getIdCategoria().getNombreCategoria().equalsIgnoreCase("Agregar Nuevo")) {
            categoriaMovilidadMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('categoriaMovilidadDialog').show()");
        }
    }

    public void addNewPaisIfIsNecesary() {
        if (paisService.findById(movilidad.getIdPaisOrigen()).getNombrePais().equalsIgnoreCase("Agregar Nuevo")) {
            paisMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('paisDialog').show()");
        }
    }

    public void changePaisOrigen() {
        if (!paisService.findById(movilidad.getIdPaisOrigen()).getNombrePais().equalsIgnoreCase("Agregar Nuevo")) {
            onchangeListPaisOrigen();
        } else {
            addNewPaisIfIsNecesary();
        }

    }

    public void addNewPaisDestinoIfIsNecesary() {
        if (paisService.findById(movilidad.getIdPaisDestino()).getNombrePais().equalsIgnoreCase("Agregar Nuevo")) {
            paisMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('paisDialog').show()");
        }
    }

    public void changePaisDestino() {
        if (!paisService.findById(movilidad.getIdPaisDestino()).getNombrePais().equalsIgnoreCase("Agregar Nuevo")) {
            onchangeListPaisDestino();
        } else {
            addNewPaisDestinoIfIsNecesary();
        }
    }

    /**
     * Metodo para listar los organismos para un determinado pais
     * @param idPais
     * @return 
     */
    public List<Organismo> listaOrganismos(Integer idPais) {
        List<Organismo> organismosListAux = organismoService.getAllByNameAsc();
        Organismo organismoNew1 = new Organismo();
        List<Organismo> copy = new ArrayList<Organismo>();
        for (Organismo organismoNew : organismosListAux) {
            if ((organismoNew.getIdPais() != null && organismoNew.getIdPais().equals(idPais)) || (organismoNew.getNombreOrganismo().equalsIgnoreCase("Agregar Nuevo"))) {
                if (!organismoNew.getNombreOrganismo().equalsIgnoreCase("Agregar Nuevo")) {
                    copy.add(organismoNew);
                } else {
                    organismoNew1 = organismoNew;
                }

            }

        }
        copy.add(organismoNew1);
        organismosListAux.clear();
        return copy;
    }

    public void addNewInstitucionOrigenIfIsNecesary() {
        if (organismoService.findById(movilidad.getIdUniversidadOrigen()).getNombreOrganismo().equalsIgnoreCase("Agregar Nuevo")) {
            organismoCooperanteMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('organismoDialog').show()");

        }
    }

    public void addNewInstitucionDestinoIfIsNecesary() {
        if (organismoService.findById(movilidad.getIdUniversidadDestino()).getNombreOrganismo().equalsIgnoreCase("Agregar Nuevo")) {
            organismoCooperanteMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('organismoDestDialog').show()");
        }
    }

    public void updateListInstitucionOrigen() {
        listOrganismosOrigen.clear();
        onchangeListPaisOrigen();
        RequestContext contextOrigen = RequestContext.getCurrentInstance();
        contextOrigen.update("formAdmin:acordion:institucionOrigen");
    }

    public void updateListInstitucionDestino() {
        listOrganismosDestino.clear();
        onchangeListPaisDestino();
        RequestContext contextDestino = RequestContext.getCurrentInstance();
        contextDestino.update("formAdmin:acordion:institucionDestino");
    }

    public void addNewtipoCambioIfIsNecesary() {
        if (tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio()).getNombreDivisa().equalsIgnoreCase("Agregar Nuevo")) {
            tipoCambioMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('tipocambioDialog').show()");
            tipoCambioSelected = new TipoCambio();
        }
    }

    public void addNewEtapaMovilidadIfIsNecesary() {
        if (movilidad.getIdEtapaMovilidad().getNombreEtapa().equalsIgnoreCase("Agregar Nuevo")) {
            etapaMovilidadMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('etapaMovilidadDialog').show()");
        }
    }

    public void addNewProgramaMovilidadIfIsNecesary() {
        if (movilidad.getIdProgramaMovilidad().getNombreProgramaMovilidad().equalsIgnoreCase("Agregar Nuevo")) {
            programaMovilidadMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('programaMovilidadDialog').show()");
        }
    }

    public void preEliminarMovilidad(Integer IdMovilidad) {
        try {
            movilidad = movilidadService.findById(IdMovilidad);
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarDialog').show()");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cancelarEliminarMovilidad() {
        try {

            RequestContext ajax = RequestContext.getCurrentInstance();
            cargarMovilidadPersona();
            ajax.execute("PF('eliminarDialog').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para realizar la eliminacion de una movilidad y 
     * de todas sus relaciones
     */
    
    public void eliminarMovilidad() {
        try {

            //eliminando documentos de la movilidad
            documentoService.eliminarDocumentosMovilidad(movilidad);
            //Eliminando tablas intermedias
            facultadService.eliminarIntermediaMovilidadFacultad(movilidad);
            unidadService.eliminarIntermediaMovilidadUnidad(movilidad);
            personaMovilidadService.eliminarIntemediaPersonaMovilidad(movilidad);
            //Eliminando la movilidad
            movilidadService.delete(movilidad);
            cargarMovilidadPersona();

            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarDialog').hide()");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //GETTER Y SETTER
    public List<ProgramaMovilidad> getListProgramaMovilidad() {
        return listProgramaMovilidad;
    }

    public void setListProgramaMovilidad(List<ProgramaMovilidad> listProgramaMovilidad) {
        this.listProgramaMovilidad = listProgramaMovilidad;
    }

    public ProgramaMovilidad getProgramaMovilidadSelected() {
        return programaMovilidadSelected;
    }

    public void setProgramaMovilidadSelected(ProgramaMovilidad programaMovilidadSelected) {
        this.programaMovilidadSelected = programaMovilidadSelected;
    }

    public ProgramaMovilidad getProgramaMovilidad() {
        return programaMovilidad;
    }

    public void setProgramaMovilidad(ProgramaMovilidad programaMovilidad) {
        this.programaMovilidad = programaMovilidad;
    }

    public Movilidad getMovilidad() {
        return movilidad;
    }

    public void setMovilidad(Movilidad movilidad) {
        this.movilidad = movilidad;
    }

    public List<TipoMovilidad> getListTipoMovilidad() {
        return listTipoMovilidad;
    }

    public void setListTipoMovilidad(List<TipoMovilidad> listTipoMovilidad) {
        this.listTipoMovilidad = listTipoMovilidad;
    }

    public List<CategoriaMovilidad> getListCategoriaMovilidad() {
        return listCategoriaMovilidad;
    }

    public void setListCategoriaMovilidad(List<CategoriaMovilidad> listCategoriaMovilidad) {
        this.listCategoriaMovilidad = listCategoriaMovilidad;
    }

    public Integer getTipoMovilidadSelected() {
        return tipoMovilidadSelected;
    }

    public void setTipoMovilidadSelected(Integer tipoMovilidadSelected) {
        this.tipoMovilidadSelected = tipoMovilidadSelected;
    }

    public Integer getCategoriaMovilidadSelected() {
        return categoriaMovilidadSelected;
    }

    public void setCategoriaMovilidadSelected(Integer categoriaMovilidadSelected) {
        this.categoriaMovilidadSelected = categoriaMovilidadSelected;
    }

    public List<Pais> getListPaisOrigenMovilidad() {
        return listPaisOrigenMovilidad;
    }

    public void setListPaisOrigenMovilidad(List<Pais> listPaisOrigenMovilidad) {
        this.listPaisOrigenMovilidad = listPaisOrigenMovilidad;
    }

    public Integer getPaisOrigenSelected() {
        return paisOrigenSelected;
    }

    public void setPaisOrigenSelected(Integer paisOrigenSelected) {
        this.paisOrigenSelected = paisOrigenSelected;
    }

    public Integer getInstitucionOrigenSelected() {
        return institucionOrigenSelected;
    }

    public void setInstitucionOrigenSelected(Integer institucionOrigenSelected) {
        this.institucionOrigenSelected = institucionOrigenSelected;
    }

    public Integer getPaisDestinoSelected() {
        return paisDestinoSelected;
    }

    public void setPaisDestinoSelected(Integer paisDestinoSelected) {
        this.paisDestinoSelected = paisDestinoSelected;
    }

    public Integer getInstitucionDestinoSelected() {
        return institucionDestinoSelected;
    }

    public void setInstitucionDestinoSelected(Integer institucionDestinoSelected) {
        this.institucionDestinoSelected = institucionDestinoSelected;
    }

    public List<Pais> getListPaisDestinoMovilidad() {
        return listPaisDestinoMovilidad;
    }

    public void setListPaisDestinoMovilidad(List<Pais> listPaisDestinoMovilidad) {
        this.listPaisDestinoMovilidad = listPaisDestinoMovilidad;
    }

    public Date getFechaInicioSelected() {
        return fechaInicioSelected;
    }

    public void setFechaInicioSelected(Date fechaInicioSelected) {
        this.fechaInicioSelected = fechaInicioSelected;
    }

    public Date getFechaFinSelected() {
        return fechaFinSelected;
    }

    public void setFechaFinSelected(Date fechaFinSelected) {
        this.fechaFinSelected = fechaFinSelected;
    }

    public Boolean getEntregaInformeSelected() {
        return entregaInformeSelected;
    }

    public void setEntregaInformeSelected(Boolean entregaInformeSelected) {
        this.entregaInformeSelected = entregaInformeSelected;
    }

    public List<Persona> getListPersonaMovilidad() {
        return listPersonaMovilidad;
    }

    public void setListPersonaMovilidad(List<Persona> listPersonaMovilidad) {
        this.listPersonaMovilidad = listPersonaMovilidad;
    }

    public Boolean getMostrarSaliente() {
        return mostrarSaliente;
    }

    public void setMostrarSaliente(Boolean mostrarSaliente) {
        this.mostrarSaliente = mostrarSaliente;
    }

    public Boolean getMostrarEntrante() {
        return mostrarEntrante;
    }

    public void setMostrarEntrante(Boolean mostrarEntrante) {
        this.mostrarEntrante = mostrarEntrante;
    }

    public Persona getPersonaMovilidadGenerico() {
        return personaMovilidadGenerico;
    }

    public void setPersonaMovilidadGenerico(Persona personaMovilidadGenerico) {
        this.personaMovilidadGenerico = personaMovilidadGenerico;
    }

    public Telefono getTelFijoPersonaMovilidad() {
        return telFijoPersonaMovilidad;
    }

    public void setTelFijoPersonaMovilidad(Telefono telFijoPersonaMovilidad) {
        this.telFijoPersonaMovilidad = telFijoPersonaMovilidad;
    }

    public Telefono getTelCelPersonaMovilidad() {
        return telCelPersonaMovilidad;
    }

    public void setTelCelPersonaMovilidad(Telefono telCelPersonaMovilidad) {
        this.telCelPersonaMovilidad = telCelPersonaMovilidad;
    }

//    public Telefono getFaxPersonaMovilidad() {
//        return faxPersonaMovilidad;
//    }
//    public void setFaxPersonaMovilidad(Telefono faxPersonaMovilidad) {
//        this.faxPersonaMovilidad = faxPersonaMovilidad;
//    }
    public List<Organismo> getListOrganismosOrigen() {
        return listOrganismosOrigen;
    }

    public void setListOrganismosOrigen(List<Organismo> listOrganismosOrigen) {
        this.listOrganismosOrigen = listOrganismosOrigen;
    }

    public List<Organismo> getListOrganismosDestino() {
        return listOrganismosDestino;
    }

    public void setListOrganismosDestino(List<Organismo> listOrganismosDestino) {
        this.listOrganismosDestino = listOrganismosDestino;
    }

    public Date getFechaEntregaMinedSelected() {
        return fechaEntregaMinedSelected;
    }

    public void setFechaEntregaMinedSelected(Date fechaEntregaMinedSelected) {
        this.fechaEntregaMinedSelected = fechaEntregaMinedSelected;
    }

    public List<EtapaMovilidad> getListEtapaMovilidad() {
        return listEtapaMovilidad;
    }

    public void setListEtapaMovilidad(List<EtapaMovilidad> listEtapaMovilidad) {
        this.listEtapaMovilidad = listEtapaMovilidad;
    }


    public List<PojoFacultadesUnidades> getListFacultadUnidad() {
        return listFacultadUnidad;
    }

    public void setListFacultadUnidad(List<PojoFacultadesUnidades> listFacultadUnidad) {
        this.listFacultadUnidad = listFacultadUnidad;
    }

    public Integer getEtapaMovilidadSelected() {
        return etapaMovilidadSelected;
    }

    public void setEtapaMovilidadSelected(Integer etapaMovilidadSelected) {
        this.etapaMovilidadSelected = etapaMovilidadSelected;
    }

    public List<Unidad> getListUnidad() {
        return listUnidad;
    }

    public void setListUnidad(List<Unidad> listUnidad) {
        this.listUnidad = listUnidad;
    }

    
    public Persona getPersonaFacultadGenerico() {
        return personaFacultadGenerico;
    }

    public void setPersonaFacultadGenerico(Persona personaFacultadGenerico) {
        this.personaFacultadGenerico = personaFacultadGenerico;
    }

    public List<PojoFacultadesUnidades> getListFacultadUnidadReferenteFactBnf() {
        return listFacultadUnidadReferenteFactBnf;
    }

    public void setListFacultadUnidadReferenteFactBnf(List<PojoFacultadesUnidades> listFacultadUnidadReferenteFactBnf) {
        this.listFacultadUnidadReferenteFactBnf = listFacultadUnidadReferenteFactBnf;
    }

    public String getFacultadDeReferente() {
        return facultadDeReferente;
    }

    public void setFacultadDeReferente(String facultadDeReferente) {
        this.facultadDeReferente = facultadDeReferente;
    }

    public List<EscuelaDepartamento> getListEscuelaDepartamentoRefFact() {
        return listEscuelaDepartamentoRefFact;
    }

    public void setListEscuelaDepartamentoRefFact(List<EscuelaDepartamento> listEscuelaDepartamentoRefFact) {
        this.listEscuelaDepartamentoRefFact = listEscuelaDepartamentoRefFact;
    }

    public Integer getEscuelaDepartamentoReferenteFactBnfSelected() {
        return escuelaDepartamentoReferenteFactBnfSelected;
    }

    public void setEscuelaDepartamentoReferenteFactBnfSelected(Integer escuelaDepartamentoReferenteFactBnfSelected) {
        this.escuelaDepartamentoReferenteFactBnfSelected = escuelaDepartamentoReferenteFactBnfSelected;
    }

    public Telefono getTelFijoPersonaFacultad() {
        return telFijoPersonaFacultad;
    }

    public void setTelFijoPersonaFacultad(Telefono telFijoPersonaFacultad) {
        this.telFijoPersonaFacultad = telFijoPersonaFacultad;
    }

    public Telefono getTelCelPersonaFacultad() {
        return telCelPersonaFacultad;
    }

    public void setTelCelPersonaFacultad(Telefono telCelPersonaFacultad) {
        this.telCelPersonaFacultad = telCelPersonaFacultad;
    }

//    public Telefono getFaxPersonaFacultad() {
//        return faxPersonaFacultad;
//    }
//    public void setFaxPersonaFacultad(Telefono faxPersonaFacultad) {
//        this.faxPersonaFacultad = faxPersonaFacultad;
//    }
    public List<Persona> getListPersonaReferenteFacultad() {
        return listPersonaReferenteFacultad;
    }

    public void setListPersonaReferenteFacultad(List<Persona> listPersonaReferenteFacultad) {
        this.listPersonaReferenteFacultad = listPersonaReferenteFacultad;
    }

    public List<PojoFacultadesUnidades> getListFacultadesUnidadesPersonaMovilidad() {
        return listFacultadesUnidadesPersonaMovilidad;
    }

    public void setListFacultadesUnidadesPersonaMovilidad(List<PojoFacultadesUnidades> listFacultadesUnidadesPersonaMovilidad) {
        this.listFacultadesUnidadesPersonaMovilidad = listFacultadesUnidadesPersonaMovilidad;
    }

    public String getFacultadPersonaMovilidad() {
        return facultadPersonaMovilidad;
    }

    public void setFacultadPersonaMovilidad(String facultadPersonaMovilidad) {
        this.facultadPersonaMovilidad = facultadPersonaMovilidad;
    }

    public List<EscuelaDepartamento> getListEscuelaDepartamentoPersonaMovilidad() {
        return listEscuelaDepartamentoPersonaMovilidad;
    }

    public void setListEscuelaDepartamentoPersonaMovilidad(List<EscuelaDepartamento> listEscuelaDepartamentoPersonaMovilidad) {
        this.listEscuelaDepartamentoPersonaMovilidad = listEscuelaDepartamentoPersonaMovilidad;
    }

    public Integer getEscuelaDepartamentoPersonaMovilidad() {
        return escuelaDepartamentoPersonaMovilidad;
    }

    public void setEscuelaDepartamentoPersonaMovilidad(Integer escuelaDepartamentoPersonaMovilidad) {
        this.escuelaDepartamentoPersonaMovilidad = escuelaDepartamentoPersonaMovilidad;
    }

    public String getDocSearchReferente() {
        return docSearchReferente;
    }

    public void setDocSearchReferente(String docSearchReferente) {
        this.docSearchReferente = docSearchReferente;
    }

    public List<Organismo> getListOrganismoPersonaMovilidad() {
        return listOrganismoPersonaMovilidad;
    }

    public void setListOrganismoPersonaMovilidad(List<Organismo> listOrganismoPersonaMovilidad) {
        this.listOrganismoPersonaMovilidad = listOrganismoPersonaMovilidad;
    }

    public Integer getInstitucionPersonaMovilidadSelected() {
        return institucionPersonaMovilidadSelected;
    }

    public void setInstitucionPersonaMovilidadSelected(Integer institucionPersonaMovilidadSelected) {
        this.institucionPersonaMovilidadSelected = institucionPersonaMovilidadSelected;
    }

    public String getDocSearchPersonaMovilidad() {
        return docSearchPersonaMovilidad;
    }

    public void setDocSearchPersonaMovilidad(String docSearchPersonaMovilidad) {
        this.docSearchPersonaMovilidad = docSearchPersonaMovilidad;
    }

    public List<Movilidad> getListMovilidad() {
        return listMovilidad;
    }

    public void setListMovilidad(List<Movilidad> listMovilidad) {
        this.listMovilidad = listMovilidad;
    }

    public List<PojoMovilidadAdm> getListPojoMovilidadAdm() {
        return listPojoMovilidadAdm;
    }

    public void setListPojoMovilidadAdm(List<PojoMovilidadAdm> listPojoMovilidadAdm) {
        this.listPojoMovilidadAdm = listPojoMovilidadAdm;
    }

    public List<String> getListFacultadesUnidadesBeneficiadasSelected() {
        return listFacultadesUnidadesBeneficiadasSelected;
    }

    public void setListFacultadesUnidadesBeneficiadasSelected(List<String> listFacultadesUnidadesBeneficiadasSelected) {
        this.listFacultadesUnidadesBeneficiadasSelected = listFacultadesUnidadesBeneficiadasSelected;
    }

    public Boolean getIsEditable() {
        return isEditable;
    }

    public void setIsEditable(Boolean isEditable) {
        this.isEditable = isEditable;
    }

    public float getTotalViaticosCurso() {
        return totalViaticosCurso;
    }

    public float getTotalViaticosCursoBoletoAereo() {
        return totalViaticosCursoBoletoAereo;
    }

    public String getTipoMovilidadConsultar() {
        return tipoMovilidadConsultar;
    }

    public String getCategoriaMovilidadConsultar() {
        return categoriaMovilidadConsultar;
    }

    public String getInstitucionPersonaMovConsultar() {
        return institucionPersonaMovConsultar;
    }

    public String getFacultadPersonaMovConsultar() {
        return facultadPersonaMovConsultar;
    }

    public String getEscuelaDeptoPersonaMovConsultar() {
        return escuelaDeptoPersonaMovConsultar;
    }

    public String getPaisOrigenConsultar() {
        return paisOrigenConsultar;
    }

    public String getInstitucionOrigenConsultar() {
        return institucionOrigenConsultar;
    }

    public String getPaisDestinoconsultar() {
        return paisDestinoconsultar;
    }

    public String getInstitucionDestinoConsultar() {
        return institucionDestinoConsultar;
    }

    public String getEtapaMovilidadConsultar() {
        return etapaMovilidadConsultar;
    }

    public String getEntregaInformeConsultar() {
        return entregaInformeConsultar;
    }

//    public String getObsequioConsultar() {
//        return obsequioConsultar;
//    }
    public String getFacultadPersonaRefConsultar() {
        return facultadPersonaRefConsultar;
    }

    public String getEscuelaDepartamentoRefConsultar() {
        return escuelaDepartamentoRefConsultar;
    }

    public String getfInicioConsultar() {
        return fInicioConsultar;
    }

    public void setfInicioConsultar(String fInicioConsultar) {
        this.fInicioConsultar = fInicioConsultar;
    }

    public String getfFinConsultar() {
        return fFinConsultar;
    }

    public String getfEntregaConsultar() {
        return fEntregaConsultar;
    }

    public String getMascaraTelefonoMovilidad() {
        return mascaraTelefonoMovilidad;
    }

    public void setMascaraTelefonoMovilidad(String mascaraTelefonoMovilidad) {
        this.mascaraTelefonoMovilidad = mascaraTelefonoMovilidad;
    }

    public Boolean getMostrarEscuelaDocente() {
        return mostrarEscuelaDocente;
    }

    public void setMostrarEscuelaDocente(Boolean mostrarEscuelaDocente) {
        this.mostrarEscuelaDocente = mostrarEscuelaDocente;
    }

    public Boolean getMostrarEscuelaReferente() {
        return mostrarEscuelaReferente;
    }

    public void setMostrarEscuelaReferente(Boolean mostrarEscuelaReferente) {
        this.mostrarEscuelaReferente = mostrarEscuelaReferente;
    }

    public Boolean getEscuelaDocenteRequerido() {
        return escuelaDocenteRequerido;
    }

    public void setEscuelaDocenteRequerido(Boolean escuelaDocenteRequerido) {
        this.escuelaDocenteRequerido = escuelaDocenteRequerido;
    }

    public Boolean getEscuelaReferenteRequerido() {
        return escuelaReferenteRequerido;
    }

    public void setEscuelaReferenteRequerido(Boolean escuelaReferenteRequerido) {
        this.escuelaReferenteRequerido = escuelaReferenteRequerido;
    }

    public List<TipoCambio> getListTipoCambio() {
        return listTipoCambio;
    }

    public void setListTipoCambio(List<TipoCambio> listTipoCambio) {
        this.listTipoCambio = listTipoCambio;
    }

    public TipoCambio getTipoCambioSelected() {
        return tipoCambioSelected;
    }

    public void setTipoCambioSelected(TipoCambio tipoCambioSelected) {
        this.tipoCambioSelected = tipoCambioSelected;
    }

    public Boolean getFlagSearchDuiSaliente() {
        return flagSearchDuiSaliente;
    }

    public void setFlagSearchDuiSaliente(Boolean flagSearchDuiSaliente) {
        this.flagSearchDuiSaliente = flagSearchDuiSaliente;
    }

    public Boolean getFlagSearchEmailSaliente() {
        return flagSearchEmailSaliente;
    }

    public void setFlagSearchEmailSaliente(Boolean flagSearchEmailSaliente) {
        this.flagSearchEmailSaliente = flagSearchEmailSaliente;
    }

    public Boolean getFlagSearchNombreSaliente() {
        return flagSearchNombreSaliente;
    }

    public void setFlagSearchNombreSaliente(Boolean flagSearchNombreSaliente) {
        this.flagSearchNombreSaliente = flagSearchNombreSaliente;
    }

    public Boolean getFlagSearchDuiReferente() {
        return flagSearchDuiReferente;
    }

    public void setFlagSearchDuiReferente(Boolean flagSearchDuiReferente) {
        this.flagSearchDuiReferente = flagSearchDuiReferente;
    }

    public Boolean getFlagSearchEmailReferente() {
        return flagSearchEmailReferente;
    }

    public void setFlagSearchEmailReferente(Boolean flagSearchEmailReferente) {
        this.flagSearchEmailReferente = flagSearchEmailReferente;
    }

    public Boolean getFlagSearchNombreReferente() {
        return flagSearchNombreReferente;
    }

    public void setFlagSearchNombreReferente(Boolean flagSearchNombreReferente) {
        this.flagSearchNombreReferente = flagSearchNombreReferente;
    }

    public Boolean getDisableAutoSaliente() {
        return disableAutoSaliente;
    }

    public void setDisableAutoSaliente(Boolean disableAutoSaliente) {
        this.disableAutoSaliente = disableAutoSaliente;
    }

    public Boolean getDisableAutoReferente() {
        return disableAutoReferente;
    }

    public void setDisableAutoReferente(Boolean disableAutoReferente) {
        this.disableAutoReferente = disableAutoReferente;
    }

    public Boolean getFlagSearchDuiEntrante() {
        return flagSearchDuiEntrante;
    }

    public void setFlagSearchDuiEntrante(Boolean flagSearchDuiEntrante) {
        this.flagSearchDuiEntrante = flagSearchDuiEntrante;
    }

    public Boolean getFlagSearchEmailEntrante() {
        return flagSearchEmailEntrante;
    }

    public void setFlagSearchEmailEntrante(Boolean flagSearchEmailEntrante) {
        this.flagSearchEmailEntrante = flagSearchEmailEntrante;
    }

    public Boolean getFlagSearchNombreEntrante() {
        return flagSearchNombreEntrante;
    }

    public void setFlagSearchNombreEntrante(Boolean flagSearchNombreEntrante) {
        this.flagSearchNombreEntrante = flagSearchNombreEntrante;
    }

    public Boolean getDisableAutoEntrante() {
        return disableAutoEntrante;
    }

    public void setDisableAutoEntrante(Boolean disableAutoEntrante) {
        this.disableAutoEntrante = disableAutoEntrante;
    }

    public String getTipoBusquedaSaliente() {
        return tipoBusquedaSaliente;
    }

    public void setTipoBusquedaSaliente(String tipoBusquedaSaliente) {
        this.tipoBusquedaSaliente = tipoBusquedaSaliente;
    }

    public String getTipoBusquedaEntrante() {
        return tipoBusquedaEntrante;
    }

    public void setTipoBusquedaEntrante(String tipoBusquedaEntrante) {
        this.tipoBusquedaEntrante = tipoBusquedaEntrante;
    }

    public String getTipoBusquedaReferente() {
        return tipoBusquedaReferente;
    }

    public void setTipoBusquedaReferente(String tipoBusquedaReferente) {
        this.tipoBusquedaReferente = tipoBusquedaReferente;
    }

    public Boolean getMostrarBuscadorEntrante() {
        return mostrarBuscadorEntrante;
    }

    public void setMostrarBuscadorEntrante(Boolean mostrarBuscadorEntrante) {
        this.mostrarBuscadorEntrante = mostrarBuscadorEntrante;
    }

    public Boolean getMostrarBuscadorSaliente() {
        return mostrarBuscadorSaliente;
    }

    public void setMostrarBuscadorSaliente(Boolean mostrarBuscadorSaliente) {
        this.mostrarBuscadorSaliente = mostrarBuscadorSaliente;
    }

    public Boolean getIsHabilidado() {
        return isHabilidado;
    }

    public void setIsHabilidado(Boolean isHabilidado) {
        this.isHabilidado = isHabilidado;
    }

    public String getMsgPersonaMovExiste() {
        return msgPersonaMovExiste;
    }

    public void setMsgPersonaMovExiste(String msgPersonaMovExiste) {
        this.msgPersonaMovExiste = msgPersonaMovExiste;
    }

    public String getMsgPersonaRefExiste() {
        return msgPersonaRefExiste;
    }

    public void setMsgPersonaRefExiste(String msgPersonaRefExiste) {
        this.msgPersonaRefExiste = msgPersonaRefExiste;
    }

    public Persona getPersonaMovAux() {
        return personaMovAux;
    }

    public void setPersonaMovAux(Persona personaMovAux) {
        this.personaMovAux = personaMovAux;
    }

    public Persona getPersonaReftAux() {
        return personaReftAux;
    }

    public void setPersonaReftAux(Persona personaReftAux) {
        this.personaReftAux = personaReftAux;
    }

    public String getTituloRegistroEdicion() {
        return tituloRegistroEdicion;
    }

    public void setTituloRegistroEdicion(String tituloRegistroEdicion) {
        this.tituloRegistroEdicion = tituloRegistroEdicion;
    }

    public Boolean getMostrarBotonDesvincular() {
        return mostrarBotonDesvincular;
    }

    public void setMostrarBotonDesvincular(Boolean mostrarBotonDesvincular) {
        this.mostrarBotonDesvincular = mostrarBotonDesvincular;
    }

    public Boolean getIsHabilitadoRfte() {
        return isHabilitadoRfte;
    }

    public void setIsHabilitadoRfte(Boolean isHabilitadoRfte) {
        this.isHabilitadoRfte = isHabilitadoRfte;
    }

    public Boolean getActivarDocente() {
        return activarDocente;
    }

    public void setActivarDocente(Boolean activarDocente) {
        this.activarDocente = activarDocente;
    }

    public Boolean getActivarReferente() {
        return activarReferente;
    }

    public void setActivarReferente(Boolean activarReferente) {
        this.activarReferente = activarReferente;
    }

    //-----------------------------------------------------------------
    public Boolean getMostrarBotonEditarDocente() {
        return mostrarBotonEditarDocente;
    }

    public void setMostrarBotonEditarDocente(Boolean mostrarBotonEditarDocente) {
        this.mostrarBotonEditarDocente = mostrarBotonEditarDocente;
    }

    public Boolean getMostrarBotonEditarReferente() {
        return mostrarBotonEditarReferente;
    }

    public void setMostrarBotonEditarReferente(Boolean mostrarBotonEditarReferente) {
        this.mostrarBotonEditarReferente = mostrarBotonEditarReferente;
    }

    public Boolean getHabilitarBuscadorReferente() {
        return habilitarBuscadorReferente;
    }

    public void setHabilitarBuscadorReferente(Boolean habilitarBuscadorReferente) {
        this.habilitarBuscadorReferente = habilitarBuscadorReferente;
    }

    public Boolean getMostrarBotonNuevoDocente() {
        return mostrarBotonNuevoDocente;
    }

    public void setMostrarBotonNuevoDocente(Boolean mostrarBotonNuevoDocente) {
        this.mostrarBotonNuevoDocente = mostrarBotonNuevoDocente;
    }

    public Boolean getMostrarBotonNuevoReferente() {
        return mostrarBotonNuevoReferente;
    }

    public void setMostrarBotonNuevoReferente(Boolean mostrarBotonNuevoReferente) {
        this.mostrarBotonNuevoReferente = mostrarBotonNuevoReferente;
    }

    public String getTxtBotonGuardar() {
        return txtBotonGuardar;
    }

    public void setTxtBotonGuardar(String txtBotonGuardar) {
        this.txtBotonGuardar = txtBotonGuardar;
    }

    public String getTxtBotonRegresar() {
        return txtBotonRegresar;
    }

    public void setTxtBotonRegresar(String txtBotonRegresar) {
        this.txtBotonRegresar = txtBotonRegresar;
    }

    public Boolean getConsultoria() {
        return consultoria;
    }

    public void setConsultoria(Boolean consultoria) {
        this.consultoria = consultoria;
    }

    public Boolean getDisableConsultoria() {
        return disableConsultoria;
    }

    public void setDisableConsultoria(Boolean disableConsultoria) {
        this.disableConsultoria = disableConsultoria;
    }

    public Boolean getFacultadDocenteRequerido() {
        return facultadDocenteRequerido;
    }

    public void setFacultadDocenteRequerido(Boolean facultadDocenteRequerido) {
        this.facultadDocenteRequerido = facultadDocenteRequerido;
    }

    public String getIconRegresarCancelar() {
        return iconRegresarCancelar;
    }

    public String getIconGuardarActualizar() {
        return iconGuardarActualizar;
    }

}
