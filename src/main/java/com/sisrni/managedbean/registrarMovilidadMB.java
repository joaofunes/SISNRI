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
import com.sisrni.model.TipoMovilidad;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.TipoTelefono;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.pojo.rpt.PojoMovilidadAdm;
import com.sisrni.service.CategoriaMovilidadService;
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
import com.sisrni.service.TipoMovilidadService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.UnidadService;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Usuario
 */
@Named(value = "registrarMovilidadMB")
@ViewScoped
public class registrarMovilidadMB {

    //Variables
    private Boolean existeReferente;
    private Boolean existePersonaMovilidad;
    private Boolean existeMovilidad;
    private Boolean isEditable;
    private Boolean mostrarEscuelaDocente;
    private Boolean mostrarEscuelaReferente;
    private Boolean escuelaDocenteRequerido;

    private float totalViaticosCurso;
    private float totalViaticosCursoBoletoAereo;

    private Integer tipoMovilidadSelected;
    private Integer categoriaMovilidadSelected;
    private Integer paisOrigenSelected;
    private Integer institucionOrigenSelected;
    private Integer paisDestinoSelected;
    private Integer institucionDestinoSelected;
    private Boolean entregaInformeSelected;
    // private Boolean obsequioSelected;
    private Integer etapaMovilidadSelected;
    private Integer escuelaDepartamentoReferenteFactBnfSelected;
    private Integer escuelaDepartamentoPersonaMovilidad;
    private Integer institucionPersonaMovilidadSelected;

    private static final String FIJO = "FIJO";
    private static final String FAX = "FAX";
    private static final String CELULAR = "CELULAR";

    //private String[] facultadesUnidadesBeneficiadasSelected;
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

    private PersonaMovilidad personaMovilidadReferenteFact;
    private PersonaMovilidadPK personaMovilidadReferenteFactPK;

    private PersonaMovilidad personaEnMovilidad;
    private PersonaMovilidadPK personaEnMovilidadPK;

    //private Persona personaFacultadSelected;
    private Persona personaFacultadGenerico;

    private TipoPersona tipoPersonaReferenteFact;

    private TipoPersona tipoPersonaSaliente;

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
    private List<Persona> listPersonaReferenteFacultad;  //para lista de personas referentes de facultad beneficiada
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

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    //Services
    @Autowired
    @Qualifier(value = "programaMovilidadService")
    private ProgramaMovilidadService programaMovilidadService;

    @Autowired
    @Qualifier(value = "tipoMovilidadService")
    private TipoMovilidadService tipoMovilidadService;

    @Autowired
    @Qualifier(value = "categoriaMovilidadService")
    private CategoriaMovilidadService categoriaMovilidadService;

    @Autowired
    @Qualifier(value = "paisService")
    private PaisService paisService;

    @Autowired
    @Qualifier(value = "movilidadService")
    private MovilidadService movilidadService;

    @Autowired
    @Qualifier(value = "personaService")
    private PersonaService personaService;

    @Autowired
    @Qualifier(value = "personaMovilidadService")
    private PersonaMovilidadService personaMovilidadService;

    @Autowired
    @Qualifier(value = "organismoService")
    private OrganismoService organismoService;

    @Autowired
    @Qualifier(value = "etapamovilidadService")
    private EtapaMovilidadService etapamovilidadService;

    @Autowired
    @Qualifier(value = "facultadService")
    private FacultadService facultadService;

    @Autowired
    @Qualifier(value = "unidadService")
    private UnidadService unidadService;

    @Autowired
    @Qualifier(value = "escuelaDepartamentoService")
    private EscuelaDepartamentoService escuelaDepartamentoService;

    @Autowired
    @Qualifier(value = "tipoTelefonoService")
    private TipoTelefonoService tipoTelefonoService;

    @Autowired
    @Qualifier(value = "telefonoService")
    private TelefonoService telefonoService;

    @Autowired
    @Qualifier(value = "tipoPersonaService")
    private TipoPersonaService tipoPersonaService;

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
        existeReferente = false;    // sea false no existe
        existePersonaMovilidad = false;
        existeMovilidad = false;
        isEditable = true;
        mostrarEscuelaDocente = false;
        mostrarEscuelaReferente = false;
        escuelaDocenteRequerido = false;

        programaMovilidadSelected = new ProgramaMovilidad();
        movilidad = new Movilidad();
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

        //personaFacultadSelected = new Persona();
        personaFacultadGenerico = new Persona();

        personaMovilidadReferenteFact = new PersonaMovilidad();
        personaMovilidadReferenteFactPK = new PersonaMovilidadPK();

        personaEnMovilidad = new PersonaMovilidad();
        personaEnMovilidadPK = new PersonaMovilidadPK();

        fechaInicioSelected = null;
        fechaFinSelected = null;
        fechaEntregaMinedSelected = null;
        etapaMovilidadSelected = null;
        escuelaDepartamentoReferenteFactBnfSelected = null;
        escuelaDepartamentoPersonaMovilidad = null;
        institucionPersonaMovilidadSelected = null;

        //mostrarEntrante = true;
        //mostrarSaliente = true;
        telFijoPersonaMovilidad = new Telefono();
        telCelPersonaMovilidad = new Telefono();
        //faxPersonaMovilidad = new Telefono();

        telFijoPersonaFacultad = new Telefono();
        telCelPersonaFacultad = new Telefono();
        //faxPersonaFacultad = new Telefono();

        listMovilidad = movilidadService.findAll(); //para vista movilidadAdm.xhtml
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

        listFacultadBnfUes = facultadService.getFacultadesByUniversidad(1); //revisar esto
        listUnidadBnfUes = unidadService.getUnidadesByUniversidad(1);     //revisar esto

        listFacultadByInst = new ArrayList<Facultad>();
        listUnidadByInst = new ArrayList<Unidad>();

        listFacultadUnidad = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);
        listFacultadAdd = new ArrayList<Facultad>();

        listUnidad = unidadService.findAll();
        listUnidadAdd = new ArrayList<Unidad>();

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
        tipoPersonaSaliente = tipoPersonaService.getTipoPersonaByNombre("DOCENTE EN MOVILIDAD");

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
            onchangeListFacultadReferente();
        } else {
            mostrarEscuelaReferente = false;

            onchangeListFacultadReferente();
        }
    }

    public void onchangeProgramaMovilidadList() {
        try {
            if (programaMovilidadSelected != null) {
                programaMovilidad = programaMovilidadService.findById(programaMovilidadSelected.getIdProgramaMovilidad());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeTipoMovilidadList() {
        try {
            if (tipoMovilidadSelected != null) {
                tipoMovilidad = tipoMovilidadService.findById(tipoMovilidadSelected);

                if (tipoMovilidadSelected == 1) { //movilidad entrante

                    mostrarEntrante = true;
                    mostrarSaliente = false;
                    mascaraTelefonoMovilidad = "";

                } else {  //movilidad Saliente
                    mostrarEntrante = false;
                    mostrarSaliente = true;
                    listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);//revisar esto
                    mascaraTelefonoMovilidad = "(503)-9999-9999";
                }

            } else {
                tipoMovilidad = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onchangeCategoriaMovilidadList() {
        try {
            if (categoriaMovilidadSelected != null) {
                categoriaMovilidad = categoriaMovilidadService.findById(categoriaMovilidadSelected);
            } else {
                categoriaMovilidad = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  public void onchangeListPersonaMovilidad() {
    //      try {
    //          if (personaMovilidad != null) {
    //              personaMovilidadGenerico = personaMovilidad;
    //              // personaMovilidad = new Persona();
    //          }
    //      } catch (Exception e) {
    //
    //      }
    //  }
    public void onchangeListInstitucionPersonaMovilidad() {
        listFacultadByInst = facultadService.getFacultadesByUniversidad(institucionPersonaMovilidadSelected);
        listUnidadByInst = unidadService.getUnidadesByUniversidad(institucionPersonaMovilidadSelected);
        listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(listFacultadByInst, listUnidadByInst);//revisar esto
    }

    /**
     * Metodo para obtener una lista de las escuelas/departamentos de una
     * facultad para una persona que se encuentra en movilidad
     */
    public void onchangeListFacultadPersonaMovilidad() {
        int result = -1;
        Integer id;
        if ((result = facultadPersonaMovilidad.indexOf(",1")) > -1) {
            id = Integer.parseInt(facultadPersonaMovilidad.substring(0, result));
            listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(id);

        } else if ((result = facultadPersonaMovilidad.indexOf(",2")) > -1) {
            id = Integer.parseInt(facultadPersonaMovilidad.substring(0, result));
            //agregando la unidad seleccionada a una variable temporral
            unidadPersonMovTmp = unidadService.findById(id);
            escuelaDepartamentoPersonaMovilidad = null;
            listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();

        }
    }

    public void onchangeListPaisOrigen() {
        try {
            if (paisOrigenSelected != null) {
                listOrganismosOrigen = organismoService.getOrganismosPorPaisYTipo(paisOrigenSelected, 1); //REVISAR ESTO
            }
        } catch (Exception e) {

        }
    }

    public void onchangeListPaisDestino() {
        try {
            if (paisOrigenSelected != null) {
                //nota: validar si no se recibe ningun organismo
                listOrganismosDestino = organismoService.getOrganismosPorPaisYTipo(paisDestinoSelected, 1);
            }
        } catch (Exception e) {

        }
    }

    public void onchangeListFacultadReferente() {
        int result = -1;
        Integer id;
        if ((result = facultadDeReferente.indexOf(",1")) > -1) {
            id = Integer.parseInt(facultadDeReferente.substring(0, result));
            listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(id);

        } else if ((result = facultadDeReferente.indexOf(",2")) > -1) {
            id = Integer.parseInt(facultadDeReferente.substring(0, result));
            //Afrefando la unidad seleccionada a una variable temporal
            unidadRftFactTmp = unidadService.findById(id);
            listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();

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

    // public void onchangeListReferenteFacultad() {
    //     EscuelaDepartamento escuelaDepartamentoRft = new EscuelaDepartamento();
    //     Facultad facultadRft = new Facultad();
    //     Unidad unidadRft = new Unidad();
    //     try {
    //        if (personaFacultadSelected != null) {
    //            personaFacultadGenerico = personaFacultadSelected;
    //            existeReferente = true;
    //            //personaFacultadSelected = new Persona();
    //
    //            //Obteniendo escuela_departamento y facultad de la persona seleccionada
    //            if ((escuelaDepartamentoRft = personaFacultadSelected.getIdEscuelaDepto()) != null) {
    //                listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(escuelaDepartamentoRft.getIdFacultad().getIdFacultad());
    //               escuelaDepartamentoReferenteFactBnfSelected = escuelaDepartamentoRft.getIdEscuelaDepto();
    //
    //               facultadDeReferente = escuelaDepartamentoRft.getIdFacultad().getIdFacultad().toString() + ",1";
    //           }
    //           if ((unidadRft = personaFacultadSelected.getIdUnidad()) != null) {
    //               facultadDeReferente = unidadRft.getIdUnidad().toString() + ",2";
    //               listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
    //           }
    // 
    //           // escuelaDepartamentoReferenteFactBnfSelected = personaFacultadSelected.getIdEscuelaDepto().getIdEscuelaDepto();
    //           //obteniendo telefonos de la persona seleccionada
    //           List<Telefono> listTelefonosPersonaFacultad = telefonoService.getTelefonosByPersona(personaFacultadSelected);
    //           for (Telefono tlfx : listTelefonosPersonaFacultad) {
    //               if (tlfx.getIdTipoTelefono().getNombre().equals(FIJO)) {
    //                   telFijoPersonaFacultad = tlfx;
    //               }
    //               if (tlfx.getIdTipoTelefono().getNombre().equals(CELULAR)) {
    //                   telCelPersonaFacultad = tlfx;
    //               }
    //               if (tlfx.getIdTipoTelefono().getNombre().equals(FAX)) {
    //                   faxPersonaFacultad = tlfx;
    //                }
    //             }
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //      }
    //  }
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
        listFacultadAdd.clear();
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

    //   public void addFacultadesBeneficiadas() {
    //       try {
    //           for (int i = 0; i < facultadesBeneficiadasSelected.length; i++) {
//
//                Facultad facultadBnf = facultadService.findById(Integer.parseInt(facultadesBeneficiadasSelected[i]));
//                if (facultadBnf != null) {
//                    listFacultadAdd.add(facultadBnf);
//                }
//            }
//            movilidad.setFacultadList(listFacultadAdd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * Metodo para agregar las Unidades beneficiadas a la instancia de Movilidad
     */
    public void addUnidadesBeneficiadas(List<String> unidadesBeneficiadas) {
        listUnidadAdd.clear();
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

    //   public void addUnidadesBeneficiadas() {
    //       try {
    //           for (int i = 0; i < unidadesBeneficiadasSelected.length; i++) {
//
//                Unidad unidadBnf = unidadService.findById(Integer.parseInt(unidadesBeneficiadasSelected[i]));
//                if (unidadBnf != null) {
//                    listUnidadAdd.add(unidadBnf);
//                }
//            }
//            movilidad.setUnidadList(listUnidadAdd);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
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
     * REVISAR ESTO
     *
     * @param movilidad
     * @return
     */
    /**
     * Metodo para guardar Persona Referente Facultad beneficiada
     */
    public void guardarPersonaFacultadBeneficiada() {

    }

    public void crearMovilidad() {
        try {
            if (existeMovilidad == true) {
                categoriaMovilidad = categoriaMovilidadService.findById(categoriaMovilidadSelected);
                tipoMovilidad = tipoMovilidadService.findById(tipoMovilidadSelected);
            }

            if (movilidad.getEntregaDeInforme() == null) {
                movilidad.setEntregaDeInforme(false);
            }
            if (movilidad.getObsequio() == null) {
                movilidad.setObsequio(false);
            }
            if (movilidad.getPagoDeCurso() == null) {
                movilidad.setPagoDeCurso(new BigDecimal(0.00));
            }
            if (movilidad.getViaticos() == null) {
                movilidad.setViaticos(new BigDecimal(0.00));
            }
            if (movilidad.getVoletoAereo() == null) {
                movilidad.setVoletoAereo(new BigDecimal(0.00));
            }
            movilidad.setIdProgramaMovilidad(programaMovilidad);
            movilidad.setIdTipoMovilidad(tipoMovilidad);
            movilidad.setIdCategoria(categoriaMovilidad);
            movilidad.setIdPaisOrigen(paisOrigenSelected);
            movilidad.setIdUniversidadOrigen(institucionOrigenSelected);
            movilidad.setIdUniversidadDestino(institucionDestinoSelected);
            movilidad.setIdPaisDestino(paisDestinoSelected);

            movilidad.setFechaInicio(fechaInicioSelected);
            movilidad.setFechaFin(fechaFinSelected);

            movilidad.setFechaEntregaMined(fechaEntregaMinedSelected);
            movilidad.setIdEtapaMovilidad(etapamovilidadService.findById(etapaMovilidadSelected));
            movilidad.setEntregaDeInforme(entregaInformeSelected);
            //movilidad.setObsequio(obsequioSelected);

            //Seteando algunos datos de la persona referente de la facultad beneficiada
            getArreglosFacultadesUnidadesBeneficiadas();

            //Si el referente pertenece a una unidad
            if (unidadRftFactTmp != null) {
                personaFacultadGenerico.setIdUnidad(unidadRftFactTmp);
                personaFacultadGenerico.setIdEscuelaDepto(null);
            }
            //si el referente pertenece a una escuela-departamento y por tanto a una facultad 
            if (escuelaDepartamentoReferenteFactBnfSelected != null) {
                personaFacultadGenerico.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamentoReferenteFactBnfSelected));
            }

            //datos adicionales de persona Referente Facultad Beneficiada
            personaFacultadGenerico.setActivo(true);
            personaFacultadGenerico.setExtranjero(false);
            personaFacultadGenerico.setIdOrganismo(organismoService.findById(1));  //revisar esto
            personaFacultadGenerico.setPasaporte("00000000-0");                    //revisar esto

            //Seteando Datos adicionales de persona en movilidad
            if (tipoMovilidadSelected == 1) { //Si es Entrante
                //personaMovilidadGenerico.setIdOrganismo(idOrganismo);
                personaMovilidadGenerico.setActivo(true);
                personaMovilidadGenerico.setExtranjero(true);
                personaMovilidadGenerico.setIdOrganismo(organismoService.findById(institucionPersonaMovilidadSelected));
                personaMovilidadGenerico.setDuiPersona("00000000-0");
                //si a la persona se le asigno una unidad
                if (unidadPersonMovTmp != null) {
                    personaMovilidadGenerico.setIdUnidad(unidadPersonMovTmp);
                    personaMovilidadGenerico.setIdEscuelaDepto(null);
                }
                //si a la persona se le asigno una escuela o departamento, y por tanto una facultad
                if (escuelaDepartamentoPersonaMovilidad != null) {
                    personaMovilidadGenerico.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamentoPersonaMovilidad));
                }

            } else {   //Si es saliente
                personaMovilidadGenerico.setActivo(true);
                personaMovilidadGenerico.setExtranjero(false);
                personaMovilidadGenerico.setIdOrganismo(organismoService.findById(1)); //revisar esto
                personaMovilidadGenerico.setPasaporte("00000000-0");
                //si a la persona se le asigno una unidad
                if (unidadPersonMovTmp != null) {
                    personaMovilidadGenerico.setIdUnidad(unidadPersonMovTmp);
                    personaMovilidadGenerico.setIdEscuelaDepto(null);
                }
                //si a la persona se le asigno una escuela o departamento, y por tanto una facultad
                if (escuelaDepartamentoPersonaMovilidad != null) {
                    personaMovilidadGenerico.setIdEscuelaDepto(escuelaDepartamentoService.findById(escuelaDepartamentoPersonaMovilidad));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void guardarMovilidadPersona() {
        String msg = "Movilidad guardada exitosamente!!";
        try {
            crearMovilidad();

            if (existeMovilidad == true) {
                movilidadService.merge(movilidad);
            } else {
                movilidadService.save(movilidad);
            }

            if (existePersonaMovilidad == false) {

                //Guardando telefonos de persona en movilidad
                telFijoPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                telFijoPersonaMovilidad.setIdTipoTelefono(tipoTelefonoFijo);
                personaMovilidadGenerico.getTelefonoList().add(telFijoPersonaMovilidad);
                //telefonoService.save(telFijoPersonaMovilidad);

                telCelPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                telCelPersonaMovilidad.setIdTipoTelefono(tipoTelefonoCel);
                personaMovilidadGenerico.getTelefonoList().add(telCelPersonaMovilidad);
                //telefonoService.save(telCelPersonaMovilidad);

                //   faxPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                //   faxPersonaMovilidad.setIdTipoTelefono(tipoTelefonoFax);
                //   personaMovilidadGenerico.getTelefonoList().add(faxPersonaMovilidad);
                //telefonoService.save(faxPersonaMovilidad);
                //Guardado de persona en Movilidad
                personaService.save(personaMovilidadGenerico);

                //Guardando en la tabla intermedia persona_movilidad para la persona en movilidad
                personaEnMovilidadPK.setIdPersona(personaMovilidadGenerico.getIdPersona());
                personaEnMovilidadPK.setIdMovilidad(movilidad.getIdMovilidad());

                personaEnMovilidad.setMovilidad(movilidad);
                personaEnMovilidad.setPersona(personaMovilidadGenerico);
                personaEnMovilidad.setIdTipoPersona(tipoPersonaSaliente);
                personaEnMovilidad.setPersonaMovilidadPK(personaEnMovilidadPK);

                personaMovilidadService.saveOrUpdate(personaEnMovilidad);
            } else {
                //Guardando telefonos de persona en movilidad
                telFijoPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                telFijoPersonaMovilidad.setIdTipoTelefono(tipoTelefonoFijo);
                personaMovilidadGenerico.getTelefonoList().add(telFijoPersonaMovilidad);

                telCelPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                telCelPersonaMovilidad.setIdTipoTelefono(tipoTelefonoCel);
                personaMovilidadGenerico.getTelefonoList().add(telCelPersonaMovilidad);

                //     faxPersonaMovilidad.setIdPersona(personaMovilidadGenerico);
                //     faxPersonaMovilidad.setIdTipoTelefono(tipoTelefonoFax);
                //     personaMovilidadGenerico.getTelefonoList().add(faxPersonaMovilidad);
                personaService.merge(personaMovilidadGenerico);
                //telefonoService.merge(telFijoPersonaMovilidad);
                //telefonoService.merge(telCelPersonaMovilidad);
                //telefonoService.merge(faxPersonaMovilidad);

                personaEnMovilidadPK.setIdPersona(personaMovilidadGenerico.getIdPersona());
                personaEnMovilidadPK.setIdMovilidad(movilidad.getIdMovilidad());

                personaEnMovilidad.setMovilidad(movilidad);
                personaEnMovilidad.setPersona(personaMovilidadGenerico);
                personaEnMovilidad.setIdTipoPersona(tipoPersonaSaliente);
                personaEnMovilidad.setPersonaMovilidadPK(personaEnMovilidadPK);

                personaMovilidadService.merge(personaEnMovilidad);
            }

            if (existeReferente == false) {
                //Guardando persona referente facultad beneficiada
                //personaService.save(personaFacultadGenerico);

                //Guardando telefonos de persona referente facultad beneficiada
                telFijoPersonaFacultad.setIdPersona(personaFacultadGenerico);
                telFijoPersonaFacultad.setIdTipoTelefono(tipoTelefonoFijo);
                personaFacultadGenerico.getTelefonoList().add(telFijoPersonaFacultad);

                telCelPersonaFacultad.setIdPersona(personaFacultadGenerico);
                telCelPersonaFacultad.setIdTipoTelefono(tipoTelefonoCel);
                personaFacultadGenerico.getTelefonoList().add(telCelPersonaFacultad);

                //    faxPersonaFacultad.setIdPersona(personaFacultadGenerico);
                //    faxPersonaFacultad.setIdTipoTelefono(tipoTelefonoFax);
                //    personaFacultadGenerico.getTelefonoList().add(faxPersonaFacultad);
                personaService.save(personaFacultadGenerico);

                //Guardando en la tabla intermedia persona_movilidad para el referente de la facultad beneficiada
                personaMovilidadReferenteFactPK.setIdPersona(personaFacultadGenerico.getIdPersona());
                personaMovilidadReferenteFactPK.setIdMovilidad(movilidad.getIdMovilidad());

                personaMovilidadReferenteFact.setMovilidad(movilidad);
                personaMovilidadReferenteFact.setPersona(personaFacultadGenerico);
                personaMovilidadReferenteFact.setIdTipoPersona(tipoPersonaReferenteFact);
                personaMovilidadReferenteFact.setPersonaMovilidadPK(personaMovilidadReferenteFactPK);
                //movilidad.getPersonaMovilidadList().add(personaMovilidadReferenteFact);
                //movilidadService.merge(movilidad);
                personaMovilidadService.saveOrUpdate(personaMovilidadReferenteFact);
            } else {
                //Telefonos
                telFijoPersonaFacultad.setIdPersona(personaFacultadGenerico);
                telFijoPersonaFacultad.setIdTipoTelefono(tipoTelefonoFijo);
                personaFacultadGenerico.getTelefonoList().add(telFijoPersonaFacultad);

                telCelPersonaFacultad.setIdPersona(personaFacultadGenerico);
                telCelPersonaFacultad.setIdTipoTelefono(tipoTelefonoCel);
                personaFacultadGenerico.getTelefonoList().add(telCelPersonaFacultad);

                //  faxPersonaFacultad.setIdPersona(personaFacultadGenerico);
                //  faxPersonaFacultad.setIdTipoTelefono(tipoTelefonoFax);
                //  personaFacultadGenerico.getTelefonoList().add(faxPersonaFacultad);
                //Actualizando la persona 
                personaService.merge(personaFacultadGenerico);
                //telefonoService.merge(telFijoPersonaFacultad);
                //telefonoService.merge(telCelPersonaFacultad);
                //telefonoService.merge(faxPersonaFacultad);

                personaMovilidadReferenteFactPK.setIdPersona(personaFacultadGenerico.getIdPersona());
                personaMovilidadReferenteFactPK.setIdMovilidad(movilidad.getIdMovilidad());

                personaMovilidadReferenteFact.setMovilidad(movilidad);
                personaMovilidadReferenteFact.setPersona(personaFacultadGenerico);
                personaMovilidadReferenteFact.setIdTipoPersona(tipoPersonaReferenteFact);
                personaMovilidadReferenteFact.setPersonaMovilidadPK(personaMovilidadReferenteFactPK);
                //movilidad.getPersonaMovilidadList().add(personaMovilidadReferenteFact);
                //movilidadService.merge(movilidad);
                personaMovilidadService.merge(personaMovilidadReferenteFact);

            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar!!", msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        regresar();
    }

    /**
     * Metodo para redireccionar avista de crear nueva movilidad
     */
    public void irnuevaMovilidad() throws IOException {
        cargarMovilidadPersona();
        FacesContext.getCurrentInstance().getExternalContext().redirect("registrarMovilidad.xhtml");
    }

    /**
     * Metodo para obtener los datos de la movilidad a modificar
     *
     * @param idMovilidad
     *
     */
    public void preActualizar(Integer idMovilidad) {
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

                tipoMovilidad = movilidad.getIdTipoMovilidad().getIdTipoMovilidad();
                tipoMovilidadSelected = movilidad.getIdTipoMovilidad().getIdTipoMovilidad();
                programaMovilidadSelected.setIdProgramaMovilidad(movilidad.getIdProgramaMovilidad().getIdProgramaMovilidad());
                programaMovilidad = programaMovilidadService.findById(programaMovilidadSelected.getIdProgramaMovilidad());
                categoriaMovilidadSelected = movilidad.getIdCategoria().getIdCategoriaMovilidad();

                //Cargando datos de detalle de la movilidad
                paisOrigenSelected = movilidad.getIdPaisOrigen();
                institucionOrigenSelected = movilidad.getIdUniversidadOrigen();
                onchangeListPaisOrigen();
                paisDestinoSelected = movilidad.getIdPaisDestino();
                institucionDestinoSelected = movilidad.getIdUniversidadDestino();
                onchangeListPaisDestino();

                fechaInicioSelected = movilidad.getFechaInicio();
                fechaFinSelected = movilidad.getFechaFin();
                this.movilidad.setViaticos(movilidad.getViaticos());
                this.movilidad.setPagoDeCurso(movilidad.getPagoDeCurso());
                this.movilidad.setVoletoAereo(movilidad.getVoletoAereo());
                fechaEntregaMinedSelected = movilidad.getFechaEntregaMined();
                etapaMovilidadSelected = movilidad.getIdEtapaMovilidad().getIdEtapa();
                entregaInformeSelected = movilidad.getEntregaDeInforme();
                // obsequioSelected = movilidad.getObsequio();

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

                this.movilidad.setOtrosBeneficiados(movilidad.getOtrosBeneficiados());

                //Cargando las Personas
                //Persona en movilidad
                personaMovilidadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "DOCENTE EN MOVILIDAD");  //REVISAR ESTO
                existePersonaMovilidad = true;
                escuelaDepto = personaMovilidadGenerico.getIdEscuelaDepto();
                if (tipoMovilidad == 2) { //movilidad Saliente
                    mostrarEntrante = false;
                    mostrarSaliente = true;
                    mascaraTelefonoMovilidad = "(503)-9999-9999";

                    listFacultadesUnidadesPersonaMovilidad = getListFacultadesUnidades(listFacultadBnfUes, listUnidadBnfUes);//revisar esto

                    if (escuelaDepto != null) {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) + ",1";

                        listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadPersonaMovilidad.substring(0, facultadPersonaMovilidad.indexOf(",1"))));
                        escuelaDepartamentoPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();

                    } else {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                        listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                        escuelaDepartamentoPersonaMovilidad = null;
                    }

                } else {//movilidad Entrante
                    mostrarEntrante = true;
                    mostrarSaliente = false;
                    mascaraTelefonoMovilidad = "";

                    institucionPersonaMovilidadSelected = personaMovilidadGenerico.getIdOrganismo().getIdOrganismo();
                    onchangeListInstitucionPersonaMovilidad();
                    if (escuelaDepto != null) {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) + ",1";

                        listEscuelaDepartamentoPersonaMovilidad = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadPersonaMovilidad.substring(0, facultadPersonaMovilidad.indexOf(",1"))));
                        escuelaDepartamentoPersonaMovilidad = personaMovilidadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();

                    } else {
                        facultadPersonaMovilidad = Integer.toString(personaMovilidadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                        listEscuelaDepartamentoPersonaMovilidad = new ArrayList<EscuelaDepartamento>();
                        escuelaDepartamentoPersonaMovilidad = null;
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
                personaFacultadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "REFERENTE FACULTAD BENEFICIADA"); //REVISAR ESTO
                existeReferente = true;
                escuelaDeptoReferente = personaFacultadGenerico.getIdEscuelaDepto();
                if (escuelaDeptoReferente != null) {
                    facultadDeReferente = Integer.toString(personaFacultadGenerico.getIdEscuelaDepto().getIdFacultad().getIdFacultad()) + ",1";
                    listEscuelaDepartamentoRefFact = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadDeReferente.substring(0, facultadDeReferente.indexOf(",1"))));
                    escuelaDepartamentoReferenteFactBnfSelected = personaFacultadGenerico.getIdEscuelaDepto().getIdEscuelaDepto();
                } else {
                    facultadDeReferente = Integer.toString(personaFacultadGenerico.getIdUnidad().getIdUnidad()) + ",2";
                    listEscuelaDepartamentoRefFact = new ArrayList<EscuelaDepartamento>();
                    escuelaDepartamentoReferenteFactBnfSelected = null;
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
        return p;
    }

    /**
     * Consultar Movilidad
     *
     * @return
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

                tipoMovilidad = movilidad.getIdTipoMovilidad().getIdTipoMovilidad();
                tipoMovilidadConsultar = movilidad.getIdTipoMovilidad().getNombreTipoMovilidad();

                programaMovilidadSelected.setIdProgramaMovilidad(movilidad.getIdProgramaMovilidad().getIdProgramaMovilidad());
                programaMovilidad = programaMovilidadService.findById(programaMovilidadSelected.getIdProgramaMovilidad());
                categoriaMovilidadConsultar = movilidad.getIdCategoria().getNombreCategoria();

                //Cargando datos de detalle de la movilidad
                paisOrigenSelected = movilidad.getIdPaisOrigen();
                paisOrigenConsultar = paisService.findById(paisOrigenSelected).getNombrePais();

                institucionOrigenSelected = movilidad.getIdUniversidadOrigen();
                institucionOrigenConsultar = organismoService.findById(institucionOrigenSelected).getNombreOrganismo();

                paisDestinoSelected = movilidad.getIdPaisDestino();
                paisDestinoconsultar = paisService.findById(paisDestinoSelected).getNombrePais();
                institucionDestinoSelected = movilidad.getIdUniversidadDestino();
                institucionDestinoConsultar = organismoService.findById(institucionDestinoSelected).getNombreOrganismo();

                if (movilidad.getFechaInicio() != null) {
                    fInicioConsultar = sdf.format(movilidad.getFechaInicio());
                }

                if (movilidad.getFechaFin() != null) {
                    fFinConsultar = sdf.format(movilidad.getFechaFin());
                }

                this.movilidad.setViaticos(movilidad.getViaticos());
                this.movilidad.setPagoDeCurso(movilidad.getPagoDeCurso());
                this.movilidad.setVoletoAereo(movilidad.getVoletoAereo());
                if (movilidad.getFechaEntregaMined() != null) {
                    fEntregaConsultar = sdf.format(movilidad.getFechaEntregaMined());
                }

                etapaMovilidadSelected = movilidad.getIdEtapaMovilidad().getIdEtapa();
                etapaMovilidadConsultar = etapamovilidadService.findById(etapaMovilidadSelected).getNombreEtapa();
                entregaInformeSelected = movilidad.getEntregaDeInforme();
                if (entregaInformeSelected == true) {
                    entregaInformeConsultar = "SI";
                } else {
                    entregaInformeConsultar = "NO";
                }
              //  obsequioSelected = movilidad.getObsequio();
                //  if (obsequioSelected == true) {
                //      obsequioConsultar = "SI";
                //  } else {
                //      obsequioConsultar = "NO";
                //  }

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

                this.movilidad.setOtrosBeneficiados(movilidad.getOtrosBeneficiados());

                //Cargando las Personas
                //Persona en movilidad
                personaMovilidadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "DOCENTE EN MOVILIDAD"); //REVISAR ESTO
                existePersonaMovilidad = true;
                escuelaDepto = personaMovilidadGenerico.getIdEscuelaDepto();
                if (tipoMovilidad == 2) { //movilidad Saliente
                    mostrarEntrante = false;
                    mostrarSaliente = true;

                    if (escuelaDepto != null) {
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
                    if (escuelaDepto != null) {
                        facultadPersonaMovConsultar = personaMovilidadGenerico.getIdEscuelaDepto().getIdFacultad().getNombreFacultad();

                        escuelaDeptoPersonaMovConsultar = personaMovilidadGenerico.getIdEscuelaDepto().getNombreEscuelaDepto();

                    } else {
                        facultadPersonaMovConsultar = personaMovilidadGenerico.getIdUnidad().getNombreUnidad();
                        escuelaDeptoPersonaMovConsultar = "";
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
                personaFacultadGenerico = getPersonaMovilidad(movilidad.getPersonaMovilidadList(), "REFERENTE FACULTAD BENEFICIADA"); //REVISAR ESTO
                existeReferente = true;
                escuelaDeptoReferente = personaFacultadGenerico.getIdEscuelaDepto();
                if (escuelaDeptoReferente != null) {
                    facultadPersonaRefConsultar = personaFacultadGenerico.getIdEscuelaDepto().getIdFacultad().getNombreFacultad();
                    escuelaDepartamentoRefConsultar = personaFacultadGenerico.getIdEscuelaDepto().getNombreEscuelaDepto();
                } else {
                    facultadPersonaRefConsultar = personaFacultadGenerico.getIdUnidad().getNombreUnidad();
                    escuelaDepartamentoRefConsultar = "";
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

    public void regresar() {
        try {

            cargarMovilidadPersona();
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

            String outcome = "movilidadAdm.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect("movilidadAdm.xhtml");
        } catch (Exception e) {
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

//    public Boolean getObsequioSelected() {
//        return obsequioSelected;
//    }
//    public void setObsequioSelected(Boolean obsequioSelected) {
//        this.obsequioSelected = obsequioSelected;
//    }
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

    /* public String[] getFacultadesUnidadesBeneficiadasSelected() {
     return facultadesUnidadesBeneficiadasSelected;
     }

     public void setFacultadesUnidadesBeneficiadasSelected(String[] facultadesUnidadesBeneficiadasSelected) {
     this.facultadesUnidadesBeneficiadasSelected = facultadesUnidadesBeneficiadasSelected;
     }
     */
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

}
