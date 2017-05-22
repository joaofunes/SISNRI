/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sisrni.managedbean;

import com.sisrni.model.AreaConocimiento;
import com.sisrni.model.Documento;
import com.sisrni.model.Facultad;
import com.sisrni.model.Organismo;
import com.sisrni.model.Pais;
import com.sisrni.model.Persona;
import com.sisrni.model.PersonaProyecto;
import com.sisrni.model.PersonaProyectoPK;
import com.sisrni.model.PropuestaConvenio;
import com.sisrni.model.Proyecto;
import com.sisrni.model.Telefono;
import com.sisrni.model.TipoFacultad;
import com.sisrni.model.TipoPersona;
import com.sisrni.model.TipoProyecto;
import com.sisrni.model.TipoTelefono;
import com.sisrni.model.TipoCambio;
import com.sisrni.model.EscuelaDepartamento;
import com.sisrni.model.Unidad;
import com.sisrni.pojo.rpt.PojoFacultadesUnidades;
import com.sisrni.service.AreaConocimientoService;
import com.sisrni.service.DocumentoService;
import com.sisrni.service.EscuelaDepartamentoService;
import com.sisrni.service.FacultadService;
import com.sisrni.service.OrganismoService;
import com.sisrni.service.PaisService;
import com.sisrni.service.PersonaProyectoService;
import com.sisrni.service.PersonaService;
import com.sisrni.service.PropuestaConvenioService;
import com.sisrni.service.ProyectoService;
import com.sisrni.service.RegionService;
import com.sisrni.service.TelefonoService;
import com.sisrni.service.TipoFacultadService;
import com.sisrni.service.TipoPersonaService;
import com.sisrni.service.TipoProyectoService;
import com.sisrni.service.TipoTelefonoService;
import com.sisrni.service.TipoCambioService;
import com.sisrni.service.UnidadService;
import com.sisrni.utils.JsfUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Lillian
 */
@Named(value = "proyectosMB")
@Scope(WebApplicationContext.SCOPE_APPLICATION)

public class ProyectosMB {

    @Inject
    TipoProyectoMB tipoProyectoMB;
    @Inject
    TipoCambioMB tipoCambioMB;
    @Inject
    PaisMB paisMB;
    @Inject
    OrganismoCooperanteMB organismoCooperanteMB;
    @Inject
    AreaConocimientoMB areaConocimientoMB;

    @Autowired
    private AreaConocimientoService areaConocimientoService;
    @Autowired
    private ProyectoService proyectoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private TipoProyectoService tipoProyectoService;
    @Autowired
    private FacultadService facultadService;
    @Autowired
    private UnidadService unidadService;
    @Autowired
    private TelefonoService telefonoService;
    @Autowired
    private PropuestaConvenioService propuestaConvenioService;
    @Autowired
    private TipoTelefonoService tipoTelefonoService;
    @Autowired
    private OrganismoService organismoService;
    @Autowired
    private TipoPersonaService tipoPersonaService;
    @Autowired
    private PersonaProyectoService personaProyectoService;
    @Autowired
    private PaisService paisService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private TipoFacultadService tipoFacultadService;
    @Autowired
    private EscuelaDepartamentoService escuelaDepartamentoService;
    @Autowired
    private TipoCambioService tipoCambioService;
    @Autowired
    private DocumentoService documentoService;

//Definicion de objetos    
    private Proyecto proyecto;
    private PropuestaConvenio propuestaConvenio;
    private Persona persona;
    private Persona personaAsistente;
    private Persona personaExterna;
    private Persona personaBecario;
    private Facultad facultad;
    private Facultad facultadB;
    private EscuelaDepartamento unidadProyecto;
    private Organismo organismo;
    private Facultad facultadAsistente;
    private Facultad facultadCoordinador;
    private PersonaProyecto personaProyecto;
    private PersonaProyectoPK personaProyectoExtPK;
    private String numDocumentoSol;
    private String numDocumentoAsis;
    private String numDocumentoRefExt;
    private String numDocumentoBecario;
    private int yearActual;
    private String anio;
    private Date fechaActual;

//Definicion de listas
    private List<Facultad> facultadList;
    private List<AreaConocimiento> areaConocimientoList;
    private List<Organismo> organismoProyList;
    private List<TipoProyecto> tipoproyectolist;
    private List<Unidad> unidadList;
    private List<EscuelaDepartamento> unidadSolList;
    private List<EscuelaDepartamento> unidadAsisList;
    private List<PropuestaConvenio> propuestaConvenioList;
    private List<Organismo> organismoList;
    private List<Telefono> listadoTelefonoSol;
    private List<Telefono> listadoTelefonoAsis;
    private List<Telefono> listadoTelefonoRefExt;
    private List<Pais> paisList;
    private List<Pais> paisCooperanteList;
    private List<Facultad> facultadBeneficiada;
    private List<Facultad> facultadBeneficiadaList;
    private List<Proyecto> proyectoList;
    private List<TipoCambio> tipoCambioList;
//Definicion de selected
    private TipoProyecto proyectoSelected;
    private String[] areaConocimientoSelected;
    private String[] organismoProySelected;
    private List<AreaConocimiento> areasConocimiento;
    private List<Organismo> organismosProyecto;
    private List<Facultad> facultadesBeneficiadaList;
    private Facultad facultadSelected;
    private String[] facultadBeneficiadaSelected;
    private EscuelaDepartamento escuelaDeptoSelected;
    private EscuelaDepartamento escuelaDeptoSelectedSol;
    private EscuelaDepartamento escuelaDeptoSelectedAsis;
    private PersonaProyecto personaProyectoAsis;
    private PersonaProyecto personaProyectoExt;
    private PersonaProyectoPK personaProyectoPK;
    private PersonaProyectoPK personaProyectoAsisPK;
    private PropuestaConvenio propuestaConvenioSelected;
    private String facultadSelectedSol;
    private String facultadSelectedAsis;
    private String facultadSelectedExt;
    private EscuelaDepartamento unidadSelectedSol;
    private EscuelaDepartamento unidadSelectedAsis;
    private EscuelaDepartamento unidadSelectedBecario;
    private Organismo organismoSelectedRefExt;
    private Pais paisSelected;
    private Pais paisCooperanteSelected;
    private TipoCambio tipoCambioSelected;
//Telefono
//private Telefono telefono;
    private Telefono telefonoSolFijo;
    private Telefono telefonoSolFax;
    private Telefono telefonoSolCel;
    private Telefono telefonoAsisFijo;
    private Telefono telefonoAsisFax;
    private Telefono telefonoAsisCel;
    private Telefono telefonoRefextFijo;
    private Telefono telefonoRefextFax;
    private Telefono telefonoRefextCel;
    private TipoTelefono tipoTelefonoFax;
    private TipoTelefono tipoTelefonoFijo;
    private TipoTelefono tipoTelefonoCel;

    private TipoPersona tipoPersonaSol;
    private TipoPersona tipoPersonaAsis;
    private TipoPersona tipoPersonaRefext;

    private TipoFacultad tipoFacultad;
    private TipoFacultad tipoFacultadB;

    private static final String FIJO = "FIJO";
    private static final String FAX = "FAX";
    private static final String CELULAR = "CELULAR";
    private TipoCambio codigoMonedaDolar;
    private TipoCambio codigoMonedaEuro;
    private TipoCambio tipoCambio;
    private int existeSol;
    private int existeAsis;
    private int existeRefExt;
    Pais regiones;
    public boolean mostrarmonto;
    private List<PojoFacultadesUnidades> facultadesUnidadesList;
    private List<PojoFacultadesUnidades> facultadesUnidadesSolList;
    private List<PojoFacultadesUnidades> facultadesUnidadesAsisList;
    private String facultadSelectedPojoP;
    private String facultadSelectedPojoSol;
    private String facultadSelectedPojoAsis;
    private List<EscuelaDepartamento> escuelaDeptoList;
    private List<EscuelaDepartamento> escuelaDeptoListSol;
    private List<EscuelaDepartamento> escuelaDeptoListAsis;
    public boolean actualizar;
    public Integer organismoInteger;
    public Boolean tabAsis;
    public Boolean tabAsisMostrar;
    private Boolean asistenteNull;
    public Boolean consultar;
    public String fu;
    public String fuSol;
    public String fuAsis;
    public String edSol;
    public String edAsis;
    public String orRefExt;
    public Boolean mostrarEscuelaSol;
    public Boolean mostrarEscuelaAsis;
    public Boolean validarFecha;
    public Long montoProyecto;
    public Boolean requiredEditar;
    private Date fechaI;
    private Date fechaF;
    //busca de persona interna
    private Boolean disableAutoInterno;
    private Boolean flagSearchDuiInterno;
    private Boolean flagSearchNombreInterno;
    private Boolean flagSearchEmailInterno;
    //busca de persona asistente
    private Boolean disableAutoAsistente;
    private Boolean flagSearchDuiAsistente;
    private Boolean flagSearchNombreAsistente;
    private Boolean flagSearchEmailAsistente;
    //Busca persona externa
    private Boolean disableAutoExterno;
    private Boolean flagSearchDuiExterno;
    private Boolean flagSearchNombreExterno;
    private Boolean flagSearchEmailExterno;

    private String tipoBusquedaInterna;
    private String tipoBusquedaAsistente;
    private String tipoBusquedaExterna;
    private List<Persona> listAll;
    private List<PojoFacultadesUnidades> listaFacultadUnidad;

    private Persona personaAux;
    private Persona personaAsistenteAux;
    private Persona personaExternaAux;

    private List<Persona> personaSinBuscar;
    private List<Persona> personaAsistenteSinBuscar;
    private List<Persona> personaExternaSinBuscar;

    private Boolean reemplazarSol;
    private Boolean reemplazarAsis;
    private Boolean reemplazarRefExt;

    private Boolean siBuscoPersona;
    private Boolean siBuscoPersonaAsistente;
    private Boolean siBuscoPersonaExterna;

    private Boolean desvincularPersona;
    private Boolean desvincularAsistente;
    private Boolean desvincularExterna;

    private Boolean buscoYEncontroCoordinador;
    private Boolean buscoYEncontroAsistente;
    private Boolean buscoYEncontroRefExt;

    private Boolean siEditoPersona;
    private Boolean siEditoPersonaAsistente;
    private Boolean siEditoPersonaExterna;

    private Boolean activarBotonNuevaPersona;
    private Boolean activarBotonNuevaPersonaAsistente;
    private Boolean activarBotonNuevaPersonaExterna;

    private Boolean disablePersona;
    private Boolean disablePersonaAsistente;
    private Boolean disablePersonaExterna;

// nuevos elementos en combo box
    private TipoProyecto newTipoProyecto;

    //Mascara de telefonos de personas externas
    private String codigoPais;
    private String mascaraTelefono;

    /**
     * Creates a new instance of ProyectosMB
     */
    @PostConstruct
    public void init() {
        inicializador();
    }

    public void inicializador() {
        //selected
        proyectoSelected = new TipoProyecto();
        facultadSelected = new Facultad();
        escuelaDeptoSelected = new EscuelaDepartamento();
        escuelaDeptoSelectedSol = new EscuelaDepartamento();
        escuelaDeptoSelectedAsis = new EscuelaDepartamento();
        propuestaConvenioSelected = new PropuestaConvenio();
        facultadSelectedSol = "";
        facultadSelectedAsis = "";
        facultadSelectedExt = "";
        unidadSelectedSol = new EscuelaDepartamento();
        unidadSelectedAsis = new EscuelaDepartamento();
        unidadSelectedBecario = new EscuelaDepartamento();
        organismoSelectedRefExt = new Organismo();
        areasConocimiento = new ArrayList<AreaConocimiento>();
        organismosProyecto = new ArrayList<Organismo>();
        facultadesBeneficiadaList = new ArrayList<Facultad>();
        paisSelected = new Pais();
        paisCooperanteSelected = new Pais();
        tipoCambioSelected = new TipoCambio();
        //Listas
        tipoproyectolist = tipoProyectoService.findAll();
        areaConocimientoList = areaConocimientoService.findAll();
        organismoProyList = organismoService.findAll();
        //facultadList = new ArrayList<Facultad>();
        facultadList = facultadService.getFacultadesByUniversidad(1);
        propuestaConvenioList = propuestaConvenioService.findAll();
        organismoList = organismoService.findAll();
        listadoTelefonoSol = telefonoService.findAll();
        listadoTelefonoAsis = telefonoService.findAll();
        listadoTelefonoRefExt = telefonoService.findAll();
        paisList = paisService.findAll();
        paisCooperanteList = paisService.findAll();
        facultadBeneficiadaList = facultadService.findAll();
        facultadBeneficiada = new ArrayList<Facultad>();
        unidadList = unidadService.findAll();
        escuelaDeptoList = new ArrayList<EscuelaDepartamento>();
        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
        escuelaDeptoListAsis = new ArrayList<EscuelaDepartamento>();
        proyectoList = proyectoService.getAllProyecto();
        tipoCambioList = tipoCambioService.findAll();
        //Objetos
        proyecto = new Proyecto();
        propuestaConvenio = new PropuestaConvenio();
        facultad = new Facultad();
        facultadB = new Facultad();
        facultadAsistente = new Facultad();
        facultadCoordinador = new Facultad();
        //telefono = new Telefono();
        telefonoSolFijo = new Telefono();
        telefonoSolFax = new Telefono();
        telefonoSolCel = new Telefono();
        telefonoAsisFijo = new Telefono();
        telefonoAsisFax = new Telefono();
        telefonoAsisCel = new Telefono();
        telefonoRefextFijo = new Telefono();
        telefonoRefextFax = new Telefono();
        telefonoRefextCel = new Telefono();
        persona = new Persona();
        personaAsistente = new Persona();
        personaExterna = new Persona();
        personaBecario = new Persona();
        organismo = new Organismo();
        personaProyecto = new PersonaProyecto();
        personaProyectoAsis = new PersonaProyecto();
        personaProyectoExt = new PersonaProyecto();
        personaProyectoPK = new PersonaProyectoPK();
        personaProyectoAsisPK = new PersonaProyectoPK();
        personaProyectoExtPK = new PersonaProyectoPK();
        tipoCambio = new TipoCambio();
        regiones = new Pais();
        //String
        numDocumentoAsis = "";
        numDocumentoSol = "";
        numDocumentoRefExt = "";
        numDocumentoBecario = "";
        facultadesUnidadesList = getListFacultadesUnidades(facultadList, unidadList);
        facultadesUnidadesSolList = getListFacultadesUnidades(facultadList, unidadList);
        facultadesUnidadesAsisList = getListFacultadesUnidades(facultadList, unidadList);
        // tipos telefonos
        tipoTelefonoFax = tipoTelefonoService.getTipoByDesc("FAX");
        tipoTelefonoFijo = tipoTelefonoService.getTipoByDesc("FIJO");
        tipoTelefonoCel = tipoTelefonoService.getTipoByDesc("CELULAR");
        // tipo persona
        tipoPersonaSol = tipoPersonaService.getTipoPersonaByNombre("REFERENTE INTERNO");
        tipoPersonaAsis = tipoPersonaService.getTipoPersonaByNombre("ASISTENTE DE COORDINADOR");
        tipoPersonaRefext = tipoPersonaService.getTipoPersonaByNombre("REFERENTE EXTERNO");
        //tipo facultad
        tipoFacultad = tipoFacultadService.getTipoFacultadByNombre("INICIATIVA");
        tipoFacultadB = tipoFacultadService.getTipoFacultadByNombre("BENEFICIADA");
        //bandera
        existeSol = 0;
        existeAsis = 0;
        existeRefExt = 0;
        yearActual = getYearOfDate(new Date());
        anio = "";
        fechaActual = new Date();
        facultadSelectedPojoP = "";
        facultadSelectedPojoSol = "";
        facultadSelectedPojoAsis = "";
        actualizar = false;
        organismoInteger = 0;
        tabAsis = false;
        tabAsisMostrar = Boolean.FALSE;
        asistenteNull = false;
        consultar = false;
        fu = "";
        fuSol = "";
        fuAsis = "";
        edSol = "";
        edAsis = "";
        orRefExt = "";
        mostrarEscuelaSol = false;
        mostrarEscuelaAsis = false;
        validarFecha = false;
        requiredEditar = false;

        disableAutoInterno = Boolean.TRUE;
        flagSearchDuiInterno = Boolean.FALSE;
        flagSearchNombreInterno = Boolean.FALSE;
        flagSearchEmailInterno = Boolean.FALSE;

        disableAutoAsistente = Boolean.TRUE;
        flagSearchDuiAsistente = Boolean.FALSE;
        flagSearchNombreAsistente = Boolean.FALSE;
        flagSearchEmailAsistente = Boolean.FALSE;

        disableAutoExterno = Boolean.TRUE;
        flagSearchDuiExterno = Boolean.FALSE;
        flagSearchNombreExterno = Boolean.FALSE;
        flagSearchEmailExterno = Boolean.FALSE;

        tipoBusquedaInterna = null;
        tipoBusquedaAsistente = null;
        tipoBusquedaExterna = null;

        organismoProySelected = new String[0];
        areaConocimientoSelected = new String[0];
        facultadBeneficiadaSelected = new String[0];

        personaAux = new Persona();
        personaAsistenteAux = new Persona();
        personaExternaAux = new Persona();

        personaSinBuscar = new ArrayList<Persona>();
        personaAsistenteSinBuscar = new ArrayList<Persona>();
        personaExternaSinBuscar = new ArrayList<Persona>();

        siBuscoPersona = Boolean.FALSE;
        siBuscoPersonaAsistente = Boolean.FALSE;
        siBuscoPersonaExterna = Boolean.FALSE;

        desvincularPersona = Boolean.FALSE;
        desvincularAsistente = Boolean.FALSE;
        desvincularExterna = Boolean.FALSE;

        reemplazarSol = Boolean.FALSE;
        reemplazarAsis = Boolean.FALSE;
        reemplazarRefExt = Boolean.FALSE;

        buscoYEncontroCoordinador = Boolean.FALSE;
        buscoYEncontroAsistente = Boolean.FALSE;
        buscoYEncontroRefExt = Boolean.FALSE;

        siEditoPersona = Boolean.FALSE;
        siEditoPersonaAsistente = Boolean.FALSE;
        siEditoPersonaExterna = Boolean.FALSE;

        activarBotonNuevaPersona = Boolean.FALSE;
        activarBotonNuevaPersonaAsistente = Boolean.FALSE;
        activarBotonNuevaPersonaExterna = Boolean.FALSE;

        disablePersona = Boolean.TRUE;
        disablePersonaAsistente = Boolean.TRUE;
        disablePersonaExterna = Boolean.TRUE;

        // nuevos elementos de los combo box
        newTipoProyecto = new TipoProyecto();

        //Mascara de telenonos de personas externas
        codigoPais = "";
        mascaraTelefono = "";

    }

    public void mostrarTab() {
        tabAsis = tabAsisMostrar ? Boolean.TRUE : Boolean.FALSE;
    }

    public void preGuardarProyecto() {
        if (siEditoPersona == true) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlg').show();");
        } else if (tabAsis == true) {
            preGuardarProyectoAsistente();
        } else {
            preGuardarProyectoExterno();
        }
    }

    public void preGuardarProyectoAsistente() {
        if ((siEditoPersonaAsistente == true && tabAsis == true) || (siEditoPersona == true && desvincularAsistente == true)) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlgAsistente').show();");
        } else {
            preGuardarProyectoExterno();
        }
//        if (tabAsis == false) {
//            preGuardarProyectoExterno();
//        }
    }

    public void preGuardarProyectoExterno() {
        if (siEditoPersonaExterna == true || (desvincularExterna == true && siEditoPersonaExterna == true)) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dataChangeDlgExterno').show();");
        } else {
            guardarProyecto();
        }
//            guardarProyecto();
    }
    //metodo que se utiliza para reemplazar los datos de la vista a los ya existentes de una persona

    public void reemplazarPersona() {
        reemplazarSol = Boolean.TRUE;
//        RequestContext.getCurrentInstance().update(":formAdmin");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeDlg').hide();");
        preGuardarProyectoAsistente();
    }

    public void noReemplazarPersona() {
//        persona = personaSinBuscar.get(0);
        reemplazarSol = Boolean.FALSE;
//        RequestContext.getCurrentInstance().update(":formAdmin");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeDlg').hide();");
        preGuardarProyectoAsistente();
    }

    public void reemplazarPersonaAsistente() {
        reemplazarAsis = Boolean.TRUE;
//        RequestContext.getCurrentInstance().update(":formAdmin");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeDlgAsistente').hide();");
        preGuardarProyectoExterno();
    }

    public void noReemplazarPersonaAsistente() {
//        personaAsistente = personaAsistenteSinBuscar.get(0);
        reemplazarAsis = Boolean.FALSE;
//        RequestContext.getCurrentInstance().update(":formAdmin");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeDlgAsistente').hide();");
        preGuardarProyectoExterno();
    }

    public void reemplazarPersonaExterno() {
        reemplazarRefExt = Boolean.TRUE;
//        RequestContext.getCurrentInstance().update(":formAdmin");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeDlgExterno').hide();");
        guardarProyecto();
    }

    public void noReemplazarPersonaExterno() {
//        personaExterna = personaExternaSinBuscar.get(0);
        reemplazarRefExt = Boolean.FALSE;
//        RequestContext.getCurrentInstance().update(":formAdmin");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dataChangeDlgExterno').hide();");
        guardarProyecto();
    }

    public void guardarProyecto() {
        try {
            //validacion al guardar proyecto vacio
            if ((siBuscoPersona == false && actualizar == false) || (siBuscoPersonaExterna == false && actualizar == false)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Requerido", "Debe ingresar toda la información correspondiente"));
            } else {
                //guardar persona solicitante
                if ((reemplazarSol == false && siEditoPersona == true) || (reemplazarSol == false && siEditoPersona == false && actualizar == false && activarBotonNuevaPersona == false)) {
//                personaService.merge(persona);

                } else {
                    String facultadArregloSol[] = facultadSelectedPojoSol.split(",");
                    if (facultadArregloSol[1].equals("1")) {
                        EscuelaDepartamento escuelaselec = escuelaDepartamentoService.findById(escuelaDeptoSelectedSol.getIdEscuelaDepto());
                        persona.setIdEscuelaDepto(escuelaselec);
                    } else {
                        Unidad unidadSelectedSoli = unidadService.findById(Integer.parseInt(facultadArregloSol[0]));
                        persona.setIdUnidad(unidadSelectedSoli);
                    }
                    persona.setPasaporte("-");
                    persona.setExtranjero(Boolean.FALSE);
                    persona.setActivo(Boolean.TRUE);
                    persona.setIdOrganismo(organismoService.findById(1));
                    //guardar telefono fijo solicitante
                    telefonoSolFijo.setIdPersona(persona);
                    telefonoSolFijo.setIdTipoTelefono(tipoTelefonoFijo);
                    persona.getTelefonoList().add(telefonoSolFijo);
                    //guardar telefono celular solicitante
                    telefonoSolCel.setIdPersona(persona);
                    telefonoSolCel.setIdTipoTelefono(tipoTelefonoCel);
                    persona.getTelefonoList().add(telefonoSolCel);
                    if ((reemplazarSol == true && existeSol == 1) || actualizar == true) {
                        personaService.merge(persona);
                    } else {
                        personaService.save(persona);
                    }
                }
                //guardar persona Asistente
                if (tabAsis == true) {
                    if ((reemplazarAsis == false && siEditoPersonaAsistente == true) || (reemplazarAsis == false && siEditoPersonaAsistente == false && actualizar == false && activarBotonNuevaPersonaAsistente == false) || (reemplazarAsis == false && desvincularAsistente == false && actualizar == false && activarBotonNuevaPersonaAsistente == false)) {
//                    personaService.merge(personaAsistente);
                    } else {
                        String facultadArregloAsis[] = facultadSelectedPojoAsis.split(",");
                        if (facultadArregloAsis[1].equals("1")) {
                            EscuelaDepartamento escuelaselecAsis = escuelaDepartamentoService.findById(escuelaDeptoSelectedAsis.getIdEscuelaDepto());
                            personaAsistente.setIdEscuelaDepto(escuelaselecAsis);
                        } else {
                            Unidad unidadSelectedAsist = unidadService.findById(Integer.parseInt(facultadArregloAsis[0]));
                            personaAsistente.setIdUnidad(unidadSelectedAsist);
                        }
                        personaAsistente.setPasaporte("-");
                        personaAsistente.setExtranjero(Boolean.FALSE);
                        personaAsistente.setActivo(Boolean.TRUE);
                        //guardar telefono fijo asistente
                        telefonoAsisFijo.setIdPersona(personaAsistente);
                        telefonoAsisFijo.setIdTipoTelefono(tipoTelefonoFijo);
                        personaAsistente.getTelefonoList().add(telefonoAsisFijo);
                        //guardar telefono celular asistente
                        telefonoAsisCel.setIdPersona(personaAsistente);
                        telefonoAsisCel.setIdTipoTelefono(tipoTelefonoCel);
                        personaAsistente.getTelefonoList().add(telefonoAsisCel);
                        if ((existeAsis == 1 && reemplazarAsis == true) || (actualizar == true && asistenteNull == false && existeAsis == 1)) {
                            personaService.merge(personaAsistente);
                        } else {
                            personaService.save(personaAsistente);
                        }
                    }
                }
                //Guardar informacion de referente externo
                if ((reemplazarRefExt == false && siEditoPersonaExterna == true) || (reemplazarRefExt == false && siEditoPersonaExterna == false && actualizar == false && activarBotonNuevaPersonaExterna == false)) {
//                personaService.merge(personaExterna);
                } else {
                    Organismo organismoExt = organismoService.findById(organismoSelectedRefExt.getIdOrganismo());
                    personaExterna.setIdOrganismo(organismoExt);
                    personaExterna.setDuiPersona("-");
                    personaExterna.setExtranjero(Boolean.TRUE);
                    personaExterna.setActivo(Boolean.TRUE);
                    //guardar telefono fijo persona externa
                    telefonoRefextFijo.setIdPersona(personaExterna);
                    telefonoRefextFijo.setIdTipoTelefono(tipoTelefonoFijo);
                    personaExterna.getTelefonoList().add(telefonoRefextFijo);
                    //guardar telefono fijo persona externa
                    telefonoRefextCel.setIdPersona(personaExterna);
                    telefonoRefextCel.setIdTipoTelefono(tipoTelefonoCel);
                    personaExterna.getTelefonoList().add(telefonoRefextCel);
                    if ((existeRefExt == 1 && reemplazarRefExt == true) || actualizar == true) {
                        personaService.merge(personaExterna);
                    } else {
                        personaService.save(personaExterna);
                    }
                }
                //Guardando datos del proyecto
                proyecto.setFechaInicio(fechaI);
                proyecto.setFechaFin(fechaF);
                SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
                formateador.format(fechaActual);
                proyecto.setFechoIngreso(fechaActual);
                proyecto.setIdTipoProyecto(tipoProyectoService.findById(proyectoSelected.getIdTipoProyecto()));
                PropuestaConvenio propuesta;
                if (propuestaConvenioSelected.getIdPropuesta() == null) {
                } else {
                    propuesta = propuestaConvenioService.findById(propuestaConvenioSelected.getIdPropuesta());
                    proyecto.setIdPropuestaConvenio(propuesta);
                }
                Pais paiscooperante = paisService.findById(paisCooperanteSelected.getIdPais());
                proyecto.setIdPaisCooperante(paiscooperante);
                proyecto.setAnioGestion(Integer.parseInt(anio.trim()));
                //seteando monto
                if (tipoCambioSelected.getIdTipoCambio() == 2) {
                } else {
                    TipoCambio aux = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
                    BigDecimal d = proyecto.getMontoProyecto();
                    d = d.multiply(aux.getDolaresPorUnidad());
                    proyecto.setMontoProyecto(d);
                }
                //Intermedia de proyecto y area de conocimiento
                for (int i = 0; i < areaConocimientoSelected.length; i++) {
                    AreaConocimiento area = areaConocimientoService.findById(Integer.parseInt(areaConocimientoSelected[i]));
                    if (area != null) {
                        areasConocimiento.add(area);
                    }
                }
                proyecto.setAreaConocimientoList(areasConocimiento);
                // guardar organismo
                for (int i = 0; i < organismoProySelected.length; i++) {
                    Organismo organismo = organismoService.findById(Integer.parseInt(organismoProySelected[i]));
                    if (organismo != null) {
                        organismosProyecto.add(organismo);
                    }
                }
                proyecto.setOrganismoList(organismosProyecto);
                //guardar facultades beneficiadas
                for (int i = 0; i < facultadBeneficiadaSelected.length; i++) {
                    Facultad facultades = facultadService.findById(Integer.parseInt(facultadBeneficiadaSelected[i]));
                    if (facultades != null) {
                        facultadesBeneficiadaList.add(facultades);
                    }
                }
                proyecto.setFacultadList(facultadesBeneficiadaList);
                //si selecciona facultad o unidad
                String facultadArreglo[] = facultadSelectedPojoP.split(",");
                if (facultadArreglo[1].equals("1")) {
                    Facultad facSelected = facultadService.findById(Integer.parseInt(facultadArreglo[0]));
                    proyecto.setIdFacultad(facSelected);
                } else {
                    proyecto.setIdUnidad(Integer.parseInt(facultadArreglo[0]));
                }
                if (actualizar == true) {
                    proyectoService.merge(proyecto);
                    //
                    if ((tabAsis == true && asistenteNull == true) || (tabAsis == true && reemplazarAsis == true)) {
                        String facultadArregloAsis[] = facultadSelectedPojoAsis.split(",");
                        if (facultadArregloAsis[1].equals("1")) {
                            EscuelaDepartamento escuelaselecAsis = escuelaDepartamentoService.findById(escuelaDeptoSelectedAsis.getIdEscuelaDepto());
                            personaAsistente.setIdEscuelaDepto(escuelaselecAsis);
                        } else {
                            Unidad unidadSelectedAsist = unidadService.findById(Integer.parseInt(facultadArregloAsis[0]));
                            personaAsistente.setIdUnidad(unidadSelectedAsist);
                        }
                        personaAsistente.setPasaporte("-");
                        personaAsistente.setExtranjero(Boolean.FALSE);
                        personaAsistente.setActivo(Boolean.TRUE);
                        //guardar telefono fijo asistente
                        telefonoAsisFijo.setIdPersona(personaAsistente);
                        telefonoAsisFijo.setIdTipoTelefono(tipoTelefonoFijo);
                        personaAsistente.getTelefonoList().add(telefonoAsisFijo);
                        //guardar telefono celular asistente
                        telefonoAsisCel.setIdPersona(personaAsistente);
                        telefonoAsisCel.setIdTipoTelefono(tipoTelefonoCel);
                        personaAsistente.getTelefonoList().add(telefonoAsisCel);

                        if ((existeAsis == 1 && asistenteNull == false) || (actualizar == true && asistenteNull == false)) {
                            personaService.merge(personaAsistente);
                        } else {
                            personaService.save(personaAsistente);
                        }
                        //
                        //guardar en tabla intermedia persona_proyecto Asistente          
                        personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
                        personaProyectoAsisPK.setIdProyecto(proyecto.getIdProyecto());
                        personaProyectoAsis.setProyecto(proyecto);
                        personaProyectoAsis.setPersona(personaAsistente);
                        personaProyectoAsis.setIdTipoPersona(tipoPersonaAsis);
                        personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
                        proyecto.getPersonaProyectoList().add(personaProyectoAsis);
                        if (asistenteNull == true) {
                            proyectoService.merge(proyecto);
                        }
                    } else if ((tabAsis == true && asistenteNull == true) || (buscoYEncontroAsistente == true && tabAsis == true)) {
                        //guardar en tabla intermedia persona_proyecto Asistente          
                        personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
                        personaProyectoAsisPK.setIdProyecto(proyecto.getIdProyecto());
                        personaProyectoAsis.setProyecto(proyecto);
                        personaProyectoAsis.setPersona(personaAsistente);
                        personaProyectoAsis.setIdTipoPersona(tipoPersonaAsis);
                        personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
                        proyecto.getPersonaProyectoList().add(personaProyectoAsis);

                        proyectoService.merge(proyecto);
                    }
                    //si desvinculo el coordinador interno
                    if (desvincularPersona == true) {
                        //guardar en tabla intermedia persona_proyecto de un solicitante 
                        personaProyectoPK.setIdPersona(persona.getIdPersona());
                        personaProyectoPK.setIdProyecto(proyecto.getIdProyecto());
                        personaProyecto.setProyecto(proyecto);
                        personaProyecto.setPersona(persona);
                        personaProyecto.setIdTipoPersona(tipoPersonaSol);
                        personaProyecto.setPersonaProyectoPK(personaProyectoPK);
                        proyecto.getPersonaProyectoList().add(personaProyecto);
                        proyectoService.merge(proyecto);
                        if ((existeSol == 1) || (reemplazarSol == true)) {
                            personaService.merge(persona);
                        } else {
                            personaService.save(persona);
                        }
                    }
                    //si desvinculo el coordinador asistente
                    if (desvincularAsistente == true && tabAsis == true) {
                        //guardar en tabla intermedia persona_proyecto de un solicitante 
                        personaProyectoPK.setIdPersona(personaAsistente.getIdPersona());
                        personaProyectoPK.setIdProyecto(proyecto.getIdProyecto());
                        personaProyecto.setProyecto(proyecto);
                        personaProyecto.setPersona(personaAsistente);
                        personaProyecto.setIdTipoPersona(tipoPersonaAsis);
                        personaProyecto.setPersonaProyectoPK(personaProyectoPK);
                        proyecto.getPersonaProyectoList().add(personaProyecto);
                        proyectoService.merge(proyecto);
                        if ((existeAsis == 1) || (reemplazarAsis == true)) {
                            personaService.merge(personaAsistente);
                        } else {
                            personaService.save(persona);
                        }
                    }
                    if (desvincularExterna == true) {
                        //guardar en tabla intermedia persona_proyecto Referente externo
                        personaProyectoExtPK.setIdPersona(personaExterna.getIdPersona());
                        personaProyectoExtPK.setIdProyecto(proyecto.getIdProyecto());
                        personaProyectoExt.setProyecto(proyecto);
                        personaProyectoExt.setPersona(personaExterna);
                        personaProyectoExt.setIdTipoPersona(tipoPersonaRefext);
                        personaProyectoExt.setPersonaProyectoPK(personaProyectoExtPK);
                        proyecto.getPersonaProyectoList().add(personaProyectoExt);
                        proyectoService.merge(proyecto);
                        if ((existeRefExt == 1) || (reemplazarRefExt == true)) {
                            personaService.merge(personaExterna);
                        } else {
                            personaService.save(personaExterna);
                        }
                    }
                } else {
                    proyectoService.save(proyecto);

                    //guardar en tabla intermedia persona_proyecto de un solicitante 
                    personaProyectoPK.setIdPersona(persona.getIdPersona());
                    personaProyectoPK.setIdProyecto(proyecto.getIdProyecto());
                    personaProyecto.setProyecto(proyecto);
                    personaProyecto.setPersona(persona);
                    personaProyecto.setIdTipoPersona(tipoPersonaSol);
                    personaProyecto.setPersonaProyectoPK(personaProyectoPK);
                    proyecto.getPersonaProyectoList().add(personaProyecto);
                    if (tabAsis == true) {
                        //guardar en tabla intermedia persona_proyecto Asistente          
                        personaProyectoAsisPK.setIdPersona(personaAsistente.getIdPersona());
                        personaProyectoAsisPK.setIdProyecto(proyecto.getIdProyecto());
                        personaProyectoAsis.setProyecto(proyecto);
                        personaProyectoAsis.setPersona(personaAsistente);
                        personaProyectoAsis.setIdTipoPersona(tipoPersonaAsis);
                        personaProyectoAsis.setPersonaProyectoPK(personaProyectoAsisPK);
                        proyecto.getPersonaProyectoList().add(personaProyectoAsis);
                    }
                    //guardar en tabla intermedia persona_proyecto Referente externo
                    personaProyectoExtPK.setIdPersona(personaExterna.getIdPersona());
                    personaProyectoExtPK.setIdProyecto(proyecto.getIdProyecto());
                    personaProyectoExt.setProyecto(proyecto);
                    personaProyectoExt.setPersona(personaExterna);
                    personaProyectoExt.setIdTipoPersona(tipoPersonaRefext);
                    personaProyectoExt.setPersonaProyectoPK(personaProyectoExtPK);
                    proyecto.getPersonaProyectoList().add(personaProyectoExt);
                    //Actualizar el proyecto
                    proyectoService.merge(proyecto);
                }
                regresar();
            }
        } catch (Exception e) {
            if (actualizar) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al modificar el registro."));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Error al guardar el registro"));
            }
        }

    }

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

    public void onFacultadSolicitanteChange() {
        facultadSelectedPojoSol.trim();
        String facultadArreglo[] = facultadSelectedPojoSol.split(",");
        if (facultadArreglo[1].equals("1")) {
            escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadArreglo[0]));

        } else {
            int bandSol = 1;
        }
    }

    public void onFacultadAsistenteChange() {
        facultadSelectedPojoAsis.trim();
        String facultadArreglo[] = facultadSelectedPojoAsis.split(",");
        if (facultadArreglo[1].equals("1")) {
            escuelaDeptoListAsis = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(Integer.parseInt(facultadArreglo[0]));

        } else {
            int bandAsis = 1;
        }
    }

//Buscando a persona  Solicitante existente
    public void searchByDocInterno(String numDocumentoSol) {
        try {
            if (numDocumentoSol != null) {
                List<Persona> auxSol = new ArrayList<Persona>();
                //auxSol=persona;
                auxSol = personaService.getReferenteInternoByEmail(numDocumentoSol);
                if (auxSol != null) {
                    existeSol = 1;
                    persona = auxSol.get(0);
                    if (persona.getIdUnidad() == null || persona.getIdEscuelaDepto() == null) {
                        facultadSelected = new Facultad();
                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
                    }
                    if (persona.getIdEscuelaDepto() != null) {
                        facultadSelectedPojoSol = persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
                        //para consultar facultad
                        Facultad facSol = facultadService.findById(persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                        fuSol = facSol.getNombreFacultad();
                        escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                        escuelaDeptoSelectedSol = persona.getIdEscuelaDepto();
                        EscuelaDepartamento escDepSol = escuelaDepartamentoService.findById(persona.getIdEscuelaDepto().getIdEscuelaDepto());
                        fuSol = escDepSol.getNombreEscuelaDepto();
                    }
                    if (persona.getIdUnidad() != null) {
                        facultadSelectedPojoSol = persona.getIdUnidad().getIdUnidad() + ",2";
                        Unidad unidadSol = unidadService.findById(persona.getIdUnidad().getIdUnidad());
                        fuSol = unidadSol.getNombreUnidad();
                        escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
                    }
                    onChangeInterno();
                } else {
                    persona = new Persona();
                    escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChangeInterno() {
        try {
            listadoTelefonoSol = persona.getTelefonoList();

            for (Telefono us : listadoTelefonoSol) {

                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                    telefonoSolFijo = us;
                }
                if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                    telefonoSolCel = us;
                }
            }
        } catch (Exception e) {
        }
    }//Fin Buscando a persona  Solicitante existente 

//Buscando si existe persona asistente
    public void searchByDocAsistente(String numDocumentoAsis) {
        try {

            if (numDocumentoAsis != null) {
                List<Persona> auxAsis = new ArrayList<Persona>();
                //auxAsis=personaAsistente;
                auxAsis = personaService.getReferenteInternoByEmail(numDocumentoAsis);
                if (auxAsis != null) {
                    existeAsis = 1;
                    personaAsistente = auxAsis.get(0);
                    if (personaAsistente.getIdUnidad() == null || personaAsistente.getIdEscuelaDepto() == null) {
                        facultadSelected = new Facultad();
                        escuelaDeptoListAsis = new ArrayList<EscuelaDepartamento>();
                    }
                    if (personaAsistente.getIdEscuelaDepto() != null) {
                        facultadSelectedPojoAsis = personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
                        Facultad facAsis = facultadService.findById(personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                        fuAsis = facAsis.getNombreFacultad();
                        escuelaDeptoListAsis = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                        escuelaDeptoSelectedAsis = personaAsistente.getIdEscuelaDepto();
                        EscuelaDepartamento escDepAsis = escuelaDepartamentoService.findById(personaAsistente.getIdEscuelaDepto().getIdEscuelaDepto());
                        edAsis = escDepAsis.getNombreEscuelaDepto();
                    }
                    if (personaAsistente.getIdUnidad() != null) {
                        facultadSelectedPojoAsis = personaAsistente.getIdUnidad().getIdUnidad() + ",2";
                        Unidad unidadAsis = unidadService.findById(personaAsistente.getIdUnidad().getIdUnidad());
                        fuAsis = unidadAsis.getNombreUnidad();
                        escuelaDeptoListAsis = new ArrayList<EscuelaDepartamento>();
                    }
                    onChangeAsistente();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChangeAsistente() {
        try {
            listadoTelefonoAsis = telefonoService.getTelefonosByPersona(personaAsistente);

            for (Telefono us : listadoTelefonoAsis) {

                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                    telefonoAsisFijo = us;
                }
                if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                    telefonoAsisCel = us;
                }
            }
        } catch (Exception e) {
        }
    }
    //Fin busca de persona asistente

//Buscando si existe persona Referente externo
    public void searchByDocReferenteExterno(String numDocumentoRefExt) {
        try {

            if (numDocumentoRefExt != null) {
                List<Persona> auxExt = new ArrayList<Persona>();
                //auxExt=personaExterna;
                auxExt = personaService.getReferenteExternoByEmail(numDocumentoRefExt);
                if (auxExt != null) {
                    existeRefExt = 1;
                    personaExterna = auxExt.get(0);
                    onChangeReferenteExterno();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onChangeReferenteExterno() {
        try {
            listadoTelefonoRefExt = telefonoService.getTelefonosByPersona(personaExterna);

            for (Telefono us : listadoTelefonoRefExt) {

                if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                    telefonoRefextFijo = us;
                }
                if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                    telefonoRefextCel = us;
                }
            }
        } catch (Exception e) {
        }
    }
    //Fin busca de persona Referente externo

    //Busca de persona interna
    public void habilitarAutoInterno() {

        flagSearchDuiInterno = Boolean.FALSE;
        flagSearchNombreInterno = Boolean.FALSE;
        flagSearchEmailInterno = Boolean.FALSE;
        personaAux = new Persona();
        activarBotonNuevaPersona = Boolean.FALSE;
        buscoYEncontroCoordinador = Boolean.FALSE;
        siEditoPersona = Boolean.FALSE;
        disablePersona = Boolean.TRUE;
        limpiarCoordinador();

        if (disableAutoInterno) {
            disableAutoInterno = Boolean.FALSE;
        }
        if (tipoBusquedaInterna.equalsIgnoreCase("doc")) {
            flagSearchDuiInterno = Boolean.TRUE;
            siBuscoPersona = Boolean.TRUE;
        }
        if (tipoBusquedaInterna.equalsIgnoreCase("nombre")) {
            flagSearchNombreInterno = Boolean.TRUE;
            siBuscoPersona = Boolean.TRUE;
        }
        if (tipoBusquedaInterna.equalsIgnoreCase("email")) {
            flagSearchEmailInterno = Boolean.TRUE;
            siBuscoPersona = Boolean.TRUE;
        }

    }

    //Busca de persona interna
    public void habilitarAutoAsistente() {

        flagSearchDuiAsistente = Boolean.FALSE;
        flagSearchNombreAsistente = Boolean.FALSE;
        flagSearchEmailAsistente = Boolean.FALSE;
        siBuscoPersonaAsistente = Boolean.FALSE;
        activarBotonNuevaPersonaAsistente = Boolean.FALSE;
        siEditoPersonaAsistente = Boolean.FALSE;
        buscoYEncontroAsistente = Boolean.FALSE;
        disablePersonaAsistente = Boolean.TRUE;
        personaAsistenteAux = new Persona();
        limpiarAsistente();

        if (disableAutoAsistente) {
            disableAutoAsistente = Boolean.FALSE;
        }
        if (tipoBusquedaAsistente.equalsIgnoreCase("doc")) {
            flagSearchDuiAsistente = Boolean.TRUE;
            siBuscoPersonaAsistente = Boolean.TRUE;
        }
        if (tipoBusquedaAsistente.equalsIgnoreCase("nombre")) {
            flagSearchNombreAsistente = Boolean.TRUE;
            siBuscoPersonaAsistente = Boolean.TRUE;
        }
        if (tipoBusquedaAsistente.equalsIgnoreCase("email")) {
            flagSearchEmailAsistente = Boolean.TRUE;
            siBuscoPersonaAsistente = Boolean.TRUE;
        }

    }

    public void habilitarAutoExterno() {

        flagSearchDuiExterno = Boolean.FALSE;
        flagSearchNombreExterno = Boolean.FALSE;
        flagSearchEmailExterno = Boolean.FALSE;
        siBuscoPersonaExterna = Boolean.FALSE;
        activarBotonNuevaPersonaExterna = Boolean.FALSE;
        siEditoPersonaExterna = Boolean.FALSE;
        buscoYEncontroRefExt = Boolean.FALSE;
        disablePersonaExterna = Boolean.TRUE;
        personaExternaAux = new Persona();
        limpiarExterno();

        if (disableAutoExterno) {
            disableAutoExterno = Boolean.FALSE;
        }
        if (tipoBusquedaExterna.equalsIgnoreCase("doc")) {
            flagSearchDuiExterno = Boolean.TRUE;
            siBuscoPersonaExterna = Boolean.TRUE;
        }
        if (tipoBusquedaExterna.equalsIgnoreCase("nombre")) {
            flagSearchNombreExterno = Boolean.TRUE;
            siBuscoPersonaExterna = Boolean.TRUE;
        }
        if (tipoBusquedaExterna.equalsIgnoreCase("email")) {
            flagSearchEmailExterno = Boolean.TRUE;
            siBuscoPersonaExterna = Boolean.TRUE;
        }

    }

    //buscar persona interna
    public List<Persona> methodSearch(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaInterna.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteInternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    activarBotonNuevaPersona = Boolean.TRUE;
                    buscoYEncontroCoordinador = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersona");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteInterno");
                }
                limpiarCoordinador();
                return list;
            } else if (tipoBusquedaInterna.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteInternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    activarBotonNuevaPersona = Boolean.TRUE;
                    buscoYEncontroCoordinador = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersona");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteInterno");
                }
                limpiarCoordinador();
                return list;

            } else if (tipoBusquedaInterna.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                personaAux = personaService.getPersonaByDui(query);
                boolean add = list.add(personaAux);
                if (list.isEmpty()) {
                    activarBotonNuevaPersona = Boolean.TRUE;
                    buscoYEncontroCoordinador = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersona");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteInterno");
                }
                limpiarCoordinador();
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //buscar persona interna
    public List<Persona> methodSearchAsistente(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaAsistente.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteInternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    activarBotonNuevaPersonaAsistente = Boolean.TRUE;
                    buscoYEncontroAsistente = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaAsistente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteAsistente");
                }
                limpiarAsistente();
                return list;
            } else if (tipoBusquedaAsistente.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteInternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    activarBotonNuevaPersonaAsistente = Boolean.TRUE;
                    buscoYEncontroAsistente = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaAsistente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteAsistente");
                }
                limpiarAsistente();
                return list;
            } else if (tipoBusquedaAsistente.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                personaAsistenteAux = personaService.getPersonaByDui(query);
                boolean add = list.add(personaAsistenteAux);
                if (list.isEmpty()) {
                    activarBotonNuevaPersonaAsistente = Boolean.TRUE;
                    buscoYEncontroAsistente = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaAsistente");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteAsistente");
                }
                limpiarAsistente();
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Persona> methodSearchExterno(String query) {
        try {
            List<Persona> list = new ArrayList<Persona>();
            if (tipoBusquedaExterna.equalsIgnoreCase("nombre")) {
                listAll = personaService.getReferenteExternoByName(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    activarBotonNuevaPersonaExterna = Boolean.TRUE;
                    buscoYEncontroRefExt = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaExterna");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteExterno");
                }
                limpiarExterno();
                return list;
            } else if (tipoBusquedaExterna.equalsIgnoreCase("email")) {
                listAll = personaService.getReferenteExternoByEmail(query);
                for (Persona us : listAll) {
                    list.add(us);
                }
                if (list.isEmpty()) {
                    activarBotonNuevaPersonaExterna = Boolean.TRUE;
                    buscoYEncontroRefExt = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaExterna");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteExterno");
                }
                limpiarExterno();
                return list;
            } else if (tipoBusquedaExterna.equalsIgnoreCase("doc")) {
                query = query.substring(0, 8) + "-" + query.substring(9);
                personaExternaAux = personaService.getPersonaByDui(query);
                boolean add = list.add(personaExternaAux);
                if (list.isEmpty()) {
                    activarBotonNuevaPersonaExterna = Boolean.TRUE;
                    buscoYEncontroRefExt = Boolean.FALSE;
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaExterna");
                    RequestContext.getCurrentInstance().update("formAdmin:acordion:GroupSolicitanteExterno");
                }
                limpiarExterno();
                return list;
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void limpiarCoordinador() {
        persona = new Persona();
        telefonoSolFijo = new Telefono();
        telefonoSolCel = new Telefono();
        facultadSelectedPojoSol = "";
        escuelaDeptoSelectedSol = new EscuelaDepartamento();
    }

    public void limpiarAsistente() {
        personaAsistente = new Persona();
        telefonoAsisFijo = new Telefono();
        telefonoAsisCel = new Telefono();
        facultadSelectedPojoAsis = "";
        escuelaDeptoSelectedAsis = new EscuelaDepartamento();
    }

    public void limpiarExterno() {
        personaExterna = new Persona();
        telefonoRefextFijo = new Telefono();
        telefonoRefextCel = new Telefono();
        organismoSelectedRefExt = new Organismo();
    }

    /**
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona solicitante interna
     */
    public void cargarNombreSoliInterno() {
        try {
            if (personaAux.getIdPersona() != null) {
                persona = personaAux;
                listadoTelefonoSol = persona.getTelefonoList();
                existeSol = 1;
                buscoYEncontroCoordinador = Boolean.TRUE;
                for (Telefono us : listadoTelefonoSol) {

                    if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telefonoSolFijo = us;
                    }
                    if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telefonoSolCel = us;
                    }
                }
                if (persona.getIdUnidad() == null || persona.getIdEscuelaDepto() == null) {
                    facultadSelected = new Facultad();
                    escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
                }
                if (persona.getIdEscuelaDepto() != null) {
                    facultadSelectedPojoSol = persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";
                    //para consultar facultad
                    Facultad facSol = facultadService.findById(persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    fuSol = facSol.getNombreFacultad();
                    escuelaDeptoListSol = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(persona.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    escuelaDeptoSelectedSol = persona.getIdEscuelaDepto();
                    EscuelaDepartamento escDepSol = escuelaDepartamentoService.findById(persona.getIdEscuelaDepto().getIdEscuelaDepto());
                    fuSol = escDepSol.getNombreEscuelaDepto();
                }
                if (persona.getIdUnidad() != null) {
                    facultadSelectedPojoSol = persona.getIdUnidad().getIdUnidad() + ",2";
                    Unidad unidadSol = unidadService.findById(persona.getIdUnidad().getIdUnidad());
                    fuSol = unidadSol.getNombreUnidad();
                    escuelaDeptoListSol = new ArrayList<EscuelaDepartamento>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para setear entidad persona en base al nombre solicitado de la
     * persona solicitante interna
     */
    public void cargarNombreSoliAsistente() {
        try {
            if (personaAsistenteAux.getIdPersona() != null) {
                personaAsistente = personaAsistenteAux;
                existeAsis = 1;
                asistenteNull = false;
                buscoYEncontroAsistente = Boolean.TRUE;
                listadoTelefonoAsis = personaAsistente.getTelefonoList();

                for (Telefono us : listadoTelefonoAsis) {

                    if (us.getIdTipoTelefono().getNombre().equals(FIJO)) {
                        telefonoAsisFijo = us;
                    }
                    if (us.getIdTipoTelefono().getNombre().equals(CELULAR)) {
                        telefonoAsisCel = us;
                    }
                }
                if (personaAsistente.getIdUnidad() == null || personaAsistente.getIdEscuelaDepto() == null) {
                    facultadSelected = new Facultad();
                    escuelaDeptoListAsis = new ArrayList<EscuelaDepartamento>();
                }
                if (personaAsistente.getIdEscuelaDepto() != null) {
                    facultadSelectedPojoAsis = personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad() + ",1";

                    escuelaDeptoListAsis = escuelaDepartamentoService.getEscuelasOrDeptoByFacultadId(personaAsistente.getIdEscuelaDepto().getIdFacultad().getIdFacultad());
                    escuelaDeptoSelectedAsis = personaAsistente.getIdEscuelaDepto();
                }
                if (personaAsistente.getIdUnidad() != null) {
                    facultadSelectedPojoAsis = personaAsistente.getIdUnidad().getIdUnidad() + ",2";
                    escuelaDeptoListAsis = new ArrayList<EscuelaDepartamento>();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarNombreSoliExterno() {
        try {
            if (personaExternaAux == null) {
                personaExternaAux = new Persona();
            }

            if (personaExternaAux.getIdPersona() != null) {
                personaExterna = personaExternaAux;
                existeRefExt = 1;
                buscoYEncontroRefExt = Boolean.TRUE;
                List<Telefono> telefonosByPersona = telefonoService.getTelefonosByPersona(personaExterna);
                organismoSelectedRefExt = personaExterna.getIdOrganismo();
                for (Telefono tel : telefonosByPersona) {
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(FIJO)) {
                        telefonoRefextFijo = tel;
                    }
                    if (tel.getIdTipoTelefono().getNombre().equalsIgnoreCase(CELULAR)) {
                        telefonoRefextCel = tel;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Integer getYearOfDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer year = cal.get(Calendar.YEAR);
        return year;
    }

    public void regresar() {
        try {
            if (actualizar == true) {
                //verifica que este una persona interna vinculada al proyecto antes de cancelar la edicion
                if ((proyectoService.isVinculadoPersona(proyecto.getIdProyecto(), persona.getIdPersona()) != null) && (proyectoService.isVinculadoPersona(proyecto.getIdProyecto(), personaExterna.getIdPersona()) != null)) {
                    inicializador();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Requerido", "El proyecto debe tener Coordinador Interno y Externo"));
                }
            } else {
                inicializador();
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                String outcome = "proyectoAdm.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect("proyectoAdm.xhtml");
            }
        } catch (Exception e) {
        }
    }

    public void confirmarDesvincularInterno() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmarDesvincular').show();");
    }

    public void desvincularInterno() {
        try {
            String msg = "El coordinador se ha desvinculado de forma correcta, debe agregar un nuevo coordinador interno";
            desvincularPersona = Boolean.TRUE;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmarDesvincular').hide();");
            proyectoService.desvincularInterno(proyecto.getIdProyecto(), persona.getIdPersona());
            telefonoSolFijo = new Telefono();
            telefonoSolCel = new Telefono();
            facultadSelectedPojoSol = "";
            escuelaDeptoSelectedSol = new EscuelaDepartamento();
            persona = new Persona();
            personaAux = new Persona();
//            asistenteNull = Boolean.TRUE;
            tipoBusquedaInterna = null;
            RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersona");
            RequestContext.getCurrentInstance().update("formAdmin:acordion:grup1");
//            preUpdateProyecto(proyecto.getIdProyecto());
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al desvincular el coordinador");
        }
    }

    public void confirmarDesvincularAsistente() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmarDesvincularAsistente').show();");
    }

    public void desvincularAsistente() {
        try {
            String msg = "El coordinador tecnico se ha desvinculado de forma correcta";
            desvincularAsistente = Boolean.TRUE;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmarDesvincularAsistente').hide();");
            proyectoService.desvincularInterno(proyecto.getIdProyecto(), personaAsistente.getIdPersona());
            telefonoAsisFijo = new Telefono();
            telefonoAsisCel = new Telefono();
            escuelaDeptoSelectedAsis = new EscuelaDepartamento();
            facultadSelectedPojoAsis = "";
            personaAsistente = new Persona();
            asistenteNull = Boolean.TRUE;
            personaAsistenteAux = new Persona();
            tipoBusquedaAsistente = null;
            preUpdateProyecto(proyecto.getIdProyecto());
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al desvincular el coordinador tecnico");
        }
    }

    public void confirmarDesvincularExterno() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('confirmarDesvincularExterno').show();");
    }

    public void desvincularExterno() {
        try {
            String msg = "El coordinador externo se ha desvinculado de forma correcta, debe agregar un nuevo coordinador externo";
            desvincularExterna = Boolean.TRUE;
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmarDesvincularExterno').hide();");
            proyectoService.desvincularInterno(proyecto.getIdProyecto(), personaExterna.getIdPersona());
            telefonoRefextFijo = new Telefono();
            telefonoRefextCel = new Telefono();
            organismoSelectedRefExt = new Organismo();
            personaExterna = new Persona();
            personaExternaAux = new Persona();
            tipoBusquedaExterna = null;
            RequestContext.getCurrentInstance().update("formAdmin:acordion:idbotonesPersonaExterna");
            RequestContext.getCurrentInstance().update("formAdmin:acordion:grup2");
//            preUpdateProyecto(proyecto.getIdProyecto());
            JsfUtil.addSuccessMessage(msg);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Error al desvincular el Coordinador Externo");
        }
    }

    public void preUpdateProyecto(Integer id) {
        try {
            Proyecto proyectoactual = proyectoService.findById(id);
            if (proyectoactual != null) {
                proyecto = proyectoactual;
                persona = getPersonaProyecto(proyecto.getPersonaProyectoList(), "REFERENTE INTERNO");
                personaAsistente = getPersonaProyecto(proyecto.getPersonaProyectoList(), "ASISTENTE DE COORDINADOR");
                if (personaAsistente == null) {
                    asistenteNull = true;
                    personaAsistente = new Persona();
                    tabAsis = false;
                } else {
                    asistenteNull = false;
                    tabAsis = true;
                }
                personaExterna = getPersonaProyecto(proyecto.getPersonaProyectoList(), "REFERENTE EXTERNO");
                organismoSelectedRefExt = personaExterna.getIdOrganismo();
//                Organismo orgExt = organismoService.findById(personaExterna.getIdOrganismo().getIdOrganismo());
//                orRefExt = orgExt.getNombreOrganismo();
                if (proyecto.getIdFacultad() == null) {
                    facultadSelectedPojoP = proyecto.getIdUnidad() + ",2";
//                    Unidad unidadProyecto = unidadService.findById(proyecto.getIdUnidad());
//                    fu = unidadProyecto.getNombreUnidad();
                } else {
                    facultadSelectedPojoP = proyecto.getIdFacultad().getIdFacultad() + ",1";
//                    Facultad facultadProyecto = facultadService.findById(proyecto.getIdFacultad().getIdFacultad());
//                    fu = facultadProyecto.getNombreFacultad();
                }
                searchByDocInterno(persona.getEmailPersona());
                searchByDocAsistente(personaAsistente.getEmailPersona());
                searchByDocReferenteExterno(personaExterna.getEmailPersona());
                proyectoSelected = proyecto.getIdTipoProyecto();
                tipoCambioSelected.setIdTipoCambio(2);
                fechaI = proyecto.getFechaInicio();
                fechaF = proyecto.getFechaFin();
                if (proyecto.getIdPropuestaConvenio() == null) {
                } else {
                    propuestaConvenioSelected = proyecto.getIdPropuestaConvenio();
                }
                paisCooperanteSelected = proyecto.getIdPaisCooperante();
                cargarOrganismos(proyecto.getOrganismoList());
                cargarAreas(proyecto.getAreaConocimientoList());
                cargarFacultadesBeneficiadas(proyecto.getFacultadList());
                anio = proyecto.getAnioGestion().toString() + " ";
                actualizar = Boolean.TRUE;
                if (consultar != true) {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("registrarProyecto.xhtml");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("consultarProyecto.xhtml");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //metodo para cargar facultades beneficiadas

    public void cargarFacultadesBeneficiadas(List<Facultad> facultadesBeneficiadaList) {
        Integer sizeFac = facultadesBeneficiadaList.size();
        List<String> facList = new ArrayList<String>();
        for (Facultad fac : facultadesBeneficiadaList) {
            facList.add(fac.getIdFacultad() + "");
        }
        facultadBeneficiadaSelected = new String[sizeFac];
        facultadBeneficiadaSelected = facList.toArray(facultadBeneficiadaSelected);
    }

    //metodo para cargar areas de conocimiento
    public void cargarAreas(List<AreaConocimiento> areaConocimientos) {
        Integer sizeAreas = areaConocimientos.size();
        List<String> areasList = new ArrayList<String>();
        for (AreaConocimiento ar : areaConocimientos) {
            areasList.add(ar.getIdAreaConocimiento() + "");
        }
        areaConocimientoSelected = new String[sizeAreas];
        areaConocimientoSelected = areasList.toArray(areaConocimientoSelected);
    }

    //metodo para cargar organismos
    public void cargarOrganismos(List<Organismo> organismosProyecto) {
        Integer tamanio = organismosProyecto.size();
        List<String> list = new ArrayList<String>();
        for (Organismo org : organismosProyecto) {
            list.add(org.getIdOrganismo() + "");
        }
        organismoProySelected = new String[tamanio];
        organismoProySelected = list.toArray(organismoProySelected);

    }
    //metodo para obtener a las personas del proyecto ingresado

    public Persona getPersonaProyecto(List<PersonaProyecto> lista, String tipo) {
        Persona p = null;
        for (PersonaProyecto per : lista) {
            if (per.getIdTipoPersona().getNombreTipoPersona().equalsIgnoreCase(tipo)) {
                return personaService.getByID(per.getPersona().getIdPersona());

            }
        }
        return p;
    }

    public String preUpdateProyecto() {
        try {
            requiredEditar = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("registrarProyecto.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void mostrarCampo() {
        String arreglo[] = facultadSelectedPojoSol.split(",");
        if (arreglo[1].equals("2")) {
            mostrarEscuelaSol = true;
        } else {
            mostrarEscuelaSol = false;
            onFacultadSolicitanteChange();
        }
    }

    public void mostrarCampoAsis() {
        String arreglo2[] = facultadSelectedPojoAsis.split(",");
        if (arreglo2[1].equals("2")) {
            mostrarEscuelaAsis = true;
        } else {
            mostrarEscuelaAsis = false;
            onFacultadAsistenteChange();
        }
    }

    public void preConsultarProyecto(Integer id) {
        consultar = true;
        preUpdateProyecto(id);
    }

    public void onload() {
        try {
            inicializador();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarPersona() {
        try {
            siEditoPersona = Boolean.TRUE;
            disablePersona = Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarPersonaAsistente() {
        try {
            siEditoPersonaAsistente = Boolean.TRUE;
            disablePersonaAsistente = Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarPersonaExterna() {
        try {
            siEditoPersonaExterna = Boolean.TRUE;
            disablePersonaExterna = Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nuevaPersona() {
        try {
            activarBotonNuevaPersona = Boolean.TRUE;
            disablePersona = Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nuevaPersonaAsistente() {
        try {
            activarBotonNuevaPersonaAsistente = Boolean.TRUE;
            disablePersonaAsistente = Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void nuevaPersonaExterna() {
        try {
            activarBotonNuevaPersonaExterna = Boolean.TRUE;
            disablePersonaExterna = Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// metodos para agregar nuevos elementos por cada combo box

    public void addNewTipoProyectoIfNecessary(AjaxBehaviorEvent event) {
        if (proyectoSelected.getNombreTipoProyecto().equals("Agregar Nuevo")) {
            tipoProyectoMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('tipoproyectoDialog').show()");
            proyectoSelected = new TipoProyecto();
        }
    }

    public void addNewTipoMoneda() {
        TipoCambio tipocambio = tipoCambioService.findById(tipoCambioSelected.getIdTipoCambio());
        if (tipocambio.getNombreDivisa().equals("Agregar Nuevo")) {
            tipoCambioMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('tipocambioDialog').show()");
            tipoCambioSelected = new TipoCambio();
        }
    }

    public void addNewPais() {
        Pais pais = paisService.findById(paisCooperanteSelected.getIdPais());
        if (pais.getNombrePais().equals("Agregar Nuevo")) {
            paisMB.init();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('paisDialog').show()");
            paisCooperanteSelected = new Pais();
        }
    }

    public void addNewOrganismo() {
        for (int i = 0; i < organismoProySelected.length; i++) {
            Organismo organismo = organismoService.findById(Integer.parseInt(organismoProySelected[i]));
            if (organismo.getNombreOrganismo().equalsIgnoreCase("Agregar Nuevo")) {
                organismoCooperanteMB.init();
                RequestContext ajax = RequestContext.getCurrentInstance();
                ajax.execute("PF('organismoDialog').show()");
                organismoProySelected[i] = "";
            }
        }
    }

    public void addNewArea() {
        for (int i = 0; i < areaConocimientoSelected.length; i++) {
            AreaConocimiento area = areaConocimientoService.findById(Integer.parseInt(areaConocimientoSelected[i]));
            if (area.getNombreArea().equalsIgnoreCase("Agregar Nueva")) {
                areaConocimientoMB.init();
                RequestContext ajax = RequestContext.getCurrentInstance();
                ajax.execute("PF('areaDialog').show()");
                areaConocimientoSelected[i] = "";
            }
        }
    }

    //Metodo que se encarga de mostrar mascara de personas externas en los telefonos
    public void onchangeListInstitucionPersona() {
        Organismo org = organismoService.findById(organismoSelectedRefExt.getIdOrganismo());
        codigoPais = paisService.findById(org.getIdPais()).getCodigoPais();
        mascaraTelefono = telefonoService.getMask(codigoPais);
    }

    public void preEliminar(Integer proyectoId) {
        try {
            proyecto = proyectoService.findById(proyectoId);
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarProyectoDialogo').show()");
        } catch (Exception e) {
        }
    }

    //elimina un objeto proyecto y todas las intermedias vinculadas
    public void eliminarProyecto() {
        try {
//            Documento doc = documentoService.findProyectowithDocumento(proyecto.getIdProyecto());
            documentoService.eliminarDocumento(proyecto);
            proyectoService.eliminarIntermediaPersona(proyecto);
            proyectoService.eliminarIntermediaFacultad(proyecto);
            proyectoService.eliminarIntermediaArea(proyecto);
            proyectoService.eliminarIntermediaOrganismo(proyecto);
            proyectoService.delete(proyecto);
            inicializador();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarProyectoDialogo').hide()");
        } catch (Exception e) {
        }
    }

    public void cancelareEiminarProyecto() {
        try {
            inicializador();
            RequestContext ajax = RequestContext.getCurrentInstance();
            ajax.execute("PF('eliminarProyectoDialogo').hide()");
        } catch (Exception e) {
        }
    }

    public TipoProyecto getProyectoSelected() {
        return proyectoSelected;
    }

    public void setProyectoSelected(TipoProyecto proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }

    public List<TipoProyecto> getTipoproyectolist() {
        return tipoproyectolist;
    }

    public void setTipoproyectolist(List<TipoProyecto> tipoproyectolist) {
        this.tipoproyectolist = tipoproyectolist;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Telefono getTelefonoSolFijo() {
        return telefonoSolFijo;
    }

    public void setTelefonoSolFijo(Telefono telefonoSolFijo) {
        this.telefonoSolFijo = telefonoSolFijo;
    }

    public Telefono getTelefonoSolFax() {
        return telefonoSolFax;
    }

    public void setTelefonoSolFax(Telefono telefonoSolFax) {
        this.telefonoSolFax = telefonoSolFax;
    }

    public Telefono getTelefonoAsisFijo() {
        return telefonoAsisFijo;
    }

    public void setTelefonoAsisFijo(Telefono telefonoAsisFijo) {
        this.telefonoAsisFijo = telefonoAsisFijo;
    }

    public Telefono getTelefonoAsisFax() {
        return telefonoAsisFax;
    }

    public void setTelefonoAsisFax(Telefono telefonoAsisFax) {
        this.telefonoAsisFax = telefonoAsisFax;
    }

    public Telefono getTelefonoRefextFijo() {
        return telefonoRefextFijo;
    }

    public void setTelefonoRefextFijo(Telefono telefonoRefextFijo) {
        this.telefonoRefextFijo = telefonoRefextFijo;
    }

    public Telefono getTelefonoRefextFax() {
        return telefonoRefextFax;
    }

    public void setTelefonoRefextFax(Telefono telefonoRefextFax) {
        this.telefonoRefextFax = telefonoRefextFax;
    }

    public Persona getPersonaAsistente() {
        return personaAsistente;
    }

    public void setPersonaAsistente(Persona personaAsistente) {
        this.personaAsistente = personaAsistente;
    }

    public Persona getPersonaExterna() {
        return personaExterna;
    }

    public void setPersonaExterna(Persona personaExterna) {
        this.personaExterna = personaExterna;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Facultad getFacultadAsistente() {
        return facultadAsistente;
    }

    public void setFacultadAsistente(Facultad facultadAsistente) {
        this.facultadAsistente = facultadAsistente;
    }

    public String getFacultadSelectedAsis() {
        return facultadSelectedAsis;
    }

    public void setFacultadSelectedAsis(String facultadSelectedAsis) {
        this.facultadSelectedAsis = facultadSelectedAsis;
    }

    public String getFacultadSelectedExt() {
        return facultadSelectedExt;
    }

    public void setFacultadSelectedExt(String facultadSelectedExt) {
        this.facultadSelectedExt = facultadSelectedExt;
    }

    public EscuelaDepartamento getUnidadSelectedSol() {
        return unidadSelectedSol;
    }

    public void setUnidadSelectedSol(EscuelaDepartamento unidadSelectedSol) {
        this.unidadSelectedSol = unidadSelectedSol;
    }

    public EscuelaDepartamento getUnidadSelectedAsis() {
        return unidadSelectedAsis;
    }

    public void setUnidadSelectedAsis(EscuelaDepartamento unidadSelectedAsis) {
        this.unidadSelectedAsis = unidadSelectedAsis;
    }

    public Organismo getOrganismo() {
        return organismo;
    }

    public void setOrganismo(Organismo organismo) {
        this.organismo = organismo;
    }

    public TipoTelefono getTipoTelefonoFax() {
        return tipoTelefonoFax;
    }

    public void setTipoTelefonoFax(TipoTelefono tipoTelefonoFax) {
        this.tipoTelefonoFax = tipoTelefonoFax;
    }

    public TipoTelefono getTipoTelefonoFijo() {
        return tipoTelefonoFijo;
    }

    public void setTipoTelefonoFijo(TipoTelefono tipoTelefonoFijo) {
        this.tipoTelefonoFijo = tipoTelefonoFijo;
    }

    public PersonaProyecto getPersonaProyecto() {
        return personaProyecto;
    }

    public void setPersonaProyecto(PersonaProyecto personaProyecto) {
        this.personaProyecto = personaProyecto;
    }

    public TipoPersona getTipoPersonaSol() {
        return tipoPersonaSol;
    }

    public void setTipoPersonaSol(TipoPersona tipoPersonaSol) {
        this.tipoPersonaSol = tipoPersonaSol;
    }

    public TipoPersona getTipoPersonaAsis() {
        return tipoPersonaAsis;
    }

    public void setTipoPersonaAsis(TipoPersona tipoPersonaAsis) {
        this.tipoPersonaAsis = tipoPersonaAsis;
    }

    public TipoPersona getTipoPersonaRefext() {
        return tipoPersonaRefext;
    }

    public void setTipoPersonaRefext(TipoPersona tipoPersonaRefext) {
        this.tipoPersonaRefext = tipoPersonaRefext;
    }

    public List<Organismo> getOrganismoList() {
        return organismoList;
    }

    public void setOrganismoList(List<Organismo> organismoList) {
        this.organismoList = organismoList;
    }

    public String[] getAreaConocimientoSelected() {
        return areaConocimientoSelected;
    }

    public void setAreaConocimientoSelected(String[] areaConocimientoSelected) {
        this.areaConocimientoSelected = areaConocimientoSelected;
    }

    public List<AreaConocimiento> getAreaConocimientoList() {
        return areaConocimientoList;
    }

    public void setAreaConocimientoList(List<AreaConocimiento> areaConocimientoList) {
        this.areaConocimientoList = areaConocimientoList;
    }

    public Facultad getFacultadSelected() {
        return facultadSelected;
    }

    public void setFacultadSelected(Facultad facultadSelected) {
        this.facultadSelected = facultadSelected;
    }

    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    public String getFacultadSelectedSol() {
        return facultadSelectedSol;
    }

    public void setFacultadSelectedSol(String facultadSelectedSol) {
        this.facultadSelectedSol = facultadSelectedSol;
    }

    public EscuelaDepartamento getUnidadProyecto() {
        return unidadProyecto;
    }

    public void setUnidadProyecto(EscuelaDepartamento unidadProyecto) {
        this.unidadProyecto = unidadProyecto;
    }

    public EscuelaDepartamento getEscuelaDeptoSelected() {
        return escuelaDeptoSelected;
    }

    public void setEscuelaDeptoSelected(EscuelaDepartamento escuelaDeptoSelected) {
        this.escuelaDeptoSelected = escuelaDeptoSelected;
    }

    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    public List<EscuelaDepartamento> getUnidadSolList() {
        return unidadSolList;
    }

    public void setUnidadSolList(List<EscuelaDepartamento> unidadSolList) {
        this.unidadSolList = unidadSolList;
    }

    public List<EscuelaDepartamento> getUnidadAsisList() {
        return unidadAsisList;
    }

    public void setUnidadAsisList(List<EscuelaDepartamento> unidadAsisList) {
        this.unidadAsisList = unidadAsisList;
    }

    public PropuestaConvenio getPropuestaConvenio() {
        return propuestaConvenio;
    }

    public void setPropuestaConvenio(PropuestaConvenio propuestaConvenio) {
        this.propuestaConvenio = propuestaConvenio;
    }

    public List<PropuestaConvenio> getPropuestaConvenioList() {
        return propuestaConvenioList;
    }

    public void setPropuestaConvenioList(List<PropuestaConvenio> propuestaConvenioList) {
        this.propuestaConvenioList = propuestaConvenioList;
    }

    public PropuestaConvenio getPropuestaConvenioSelected() {
        return propuestaConvenioSelected;
    }

    public void setPropuestaConvenioSelected(PropuestaConvenio propuestaConvenioSelected) {
        this.propuestaConvenioSelected = propuestaConvenioSelected;
    }

    public List<AreaConocimiento> getAreasConocimiento() {
        return areasConocimiento;
    }

    public void setAreasConocimiento(List<AreaConocimiento> areasConocimiento) {
        this.areasConocimiento = areasConocimiento;
    }

    public String getNumDocumentoSol() {
        return numDocumentoSol;
    }

    public void setNumDocumentoSol(String numDocumentoSol) {
        this.numDocumentoSol = numDocumentoSol;
    }

    public String getNumDocumentoAsis() {
        return numDocumentoAsis;
    }

    public void setNumDocumentoAsis(String numDocumentoAsis) {
        this.numDocumentoAsis = numDocumentoAsis;
    }

    public String getNumDocumentoRefExt() {
        return numDocumentoRefExt;
    }

    public void setNumDocumentoRefExt(String numDocumentoRefExt) {
        this.numDocumentoRefExt = numDocumentoRefExt;
    }

    public List<Telefono> getListadoTelefonoSol() {
        return listadoTelefonoSol;
    }

    public void setListadoTelefonoSol(List<Telefono> listadoTelefonoSol) {
        this.listadoTelefonoSol = listadoTelefonoSol;
    }

    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    public Persona getPersonaBecario() {
        return personaBecario;
    }

    public void setPersonaBecario(Persona personaBecario) {
        this.personaBecario = personaBecario;
    }

    public String getNumDocumentoBecario() {
        return numDocumentoBecario;
    }

    public void setNumDocumentoBecario(String numDocumentoBecario) {
        this.numDocumentoBecario = numDocumentoBecario;
    }

    public EscuelaDepartamento getUnidadSelectedBecario() {
        return unidadSelectedBecario;
    }

    public void setUnidadSelectedBecario(EscuelaDepartamento unidadSelectedBecario) {
        this.unidadSelectedBecario = unidadSelectedBecario;
    }

    public int getYearActual() {
        return yearActual;
    }

    public void setYearActual(int yearActual) {
        this.yearActual = yearActual;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Pais getRegiones() {
        return regiones;
    }

    public void setRegiones(Pais regiones) {
        this.regiones = regiones;
    }

    public List<Organismo> getOrganismoProyList() {
        return organismoProyList;
    }

    public void setOrganismoProyList(List<Organismo> organismoProyList) {
        this.organismoProyList = organismoProyList;
    }

    public String[] getOrganismoProySelected() {
        return organismoProySelected;
    }

    public void setOrganismoProySelected(String[] organismoProySelected) {
        this.organismoProySelected = organismoProySelected;
    }

    public List<Organismo> getOrganismosProyecto() {
        return organismosProyecto;
    }

    public void setOrganismosProyecto(List<Organismo> organismosProyecto) {
        this.organismosProyecto = organismosProyecto;
    }

    public List<Pais> getPaisCooperanteList() {
        return paisCooperanteList;
    }

    public void setPaisCooperanteList(List<Pais> paisCooperanteList) {
        this.paisCooperanteList = paisCooperanteList;
    }

    public Pais getPaisCooperanteSelected() {
        return paisCooperanteSelected;
    }

    public void setPaisCooperanteSelected(Pais paisCooperanteSelected) {
        this.paisCooperanteSelected = paisCooperanteSelected;
    }

    public boolean isMostrarmonto() {
        return mostrarmonto;
    }

    public void setMostrarmonto(boolean mostrarmonto) {
        this.mostrarmonto = mostrarmonto;
    }

    public List<Facultad> getFacultadBeneficiada() {
        return facultadBeneficiada;
    }

    public void setFacultadBeneficiada(List<Facultad> facultadBeneficiada) {
        this.facultadBeneficiada = facultadBeneficiada;
    }

    public List<Facultad> getFacultadBeneficiadaList() {
        return facultadBeneficiadaList;
    }

    public void setFacultadBeneficiadaList(List<Facultad> facultadBeneficiadaList) {
        this.facultadBeneficiadaList = facultadBeneficiadaList;
    }

    public String[] getFacultadBeneficiadaSelected() {
        return facultadBeneficiadaSelected;
    }

    public void setFacultadBeneficiadaSelected(String[] facultadBeneficiadaSelected) {
        this.facultadBeneficiadaSelected = facultadBeneficiadaSelected;
    }

    public Telefono getTelefonoSolCel() {
        return telefonoSolCel;
    }

    public void setTelefonoSolCel(Telefono telefonoSolCel) {
        this.telefonoSolCel = telefonoSolCel;
    }

    public Telefono getTelefonoAsisCel() {
        return telefonoAsisCel;
    }

    public void setTelefonoAsisCel(Telefono telefonoAsisCel) {
        this.telefonoAsisCel = telefonoAsisCel;
    }

    public Telefono getTelefonoRefextCel() {
        return telefonoRefextCel;
    }

    public void setTelefonoRefextCel(Telefono telefonoRefextCel) {
        this.telefonoRefextCel = telefonoRefextCel;
    }

    public Facultad getFacultadB() {
        return facultadB;
    }

    public void setFacultadB(Facultad facultadB) {
        this.facultadB = facultadB;
    }

    public Facultad getFacultadCoordinador() {
        return facultadCoordinador;
    }

    public void setFacultadCoordinador(Facultad facultadCoordinador) {
        this.facultadCoordinador = facultadCoordinador;
    }

    public List<PojoFacultadesUnidades> getFacultadesUnidadesList() {
        return facultadesUnidadesList;
    }

    public void setFacultadesUnidadesList(List<PojoFacultadesUnidades> facultadesUnidadesList) {
        this.facultadesUnidadesList = facultadesUnidadesList;
    }

    public List<PojoFacultadesUnidades> getFacultadesUnidadesSolList() {
        return facultadesUnidadesSolList;
    }

    public void setFacultadesUnidadesSolList(List<PojoFacultadesUnidades> facultadesUnidadesSolList) {
        this.facultadesUnidadesSolList = facultadesUnidadesSolList;
    }

    public List<PojoFacultadesUnidades> getFacultadesUnidadesAsisList() {
        return facultadesUnidadesAsisList;
    }

    public void setFacultadesUnidadesAsisList(List<PojoFacultadesUnidades> facultadesUnidadesAsisList) {
        this.facultadesUnidadesAsisList = facultadesUnidadesAsisList;
    }

    public String getFacultadSelectedPojoP() {
        return facultadSelectedPojoP;
    }

    public void setFacultadSelectedPojoP(String facultadSelectedPojoP) {
        this.facultadSelectedPojoP = facultadSelectedPojoP;
    }

    public List<EscuelaDepartamento> getEscuelaDeptoList() {
        return escuelaDeptoList;
    }

    public void setEscuelaDeptoList(List<EscuelaDepartamento> escuelaDeptoList) {
        this.escuelaDeptoList = escuelaDeptoList;
    }

    public List<EscuelaDepartamento> getEscuelaDeptoListSol() {
        return escuelaDeptoListSol;
    }

    public void setEscuelaDeptoListSol(List<EscuelaDepartamento> escuelaDeptoListSol) {
        this.escuelaDeptoListSol = escuelaDeptoListSol;
    }

    public List<EscuelaDepartamento> getEscuelaDeptoListAsis() {
        return escuelaDeptoListAsis;
    }

    public void setEscuelaDeptoListAsis(List<EscuelaDepartamento> escuelaDeptoListAsis) {
        this.escuelaDeptoListAsis = escuelaDeptoListAsis;
    }

    public String getFacultadSelectedPojoSol() {
        return facultadSelectedPojoSol;
    }

    public void setFacultadSelectedPojoSol(String facultadSelectedPojoSol) {
        this.facultadSelectedPojoSol = facultadSelectedPojoSol;
    }

    public String getFacultadSelectedPojoAsis() {
        return facultadSelectedPojoAsis;
    }

    public void setFacultadSelectedPojoAsis(String facultadSelectedPojoAsis) {
        this.facultadSelectedPojoAsis = facultadSelectedPojoAsis;
    }

    public EscuelaDepartamento getEscuelaDeptoSelectedSol() {
        return escuelaDeptoSelectedSol;
    }

    public void setEscuelaDeptoSelectedSol(EscuelaDepartamento escuelaDeptoSelectedSol) {
        this.escuelaDeptoSelectedSol = escuelaDeptoSelectedSol;
    }

    public EscuelaDepartamento getEscuelaDeptoSelectedAsis() {
        return escuelaDeptoSelectedAsis;
    }

    public void setEscuelaDeptoSelectedAsis(EscuelaDepartamento escuelaDeptoSelectedAsis) {
        this.escuelaDeptoSelectedAsis = escuelaDeptoSelectedAsis;
    }

    public List<Proyecto> getProyectoList() {
        return proyectoList;
    }

    public void setProyectoList(List<Proyecto> proyectoList) {
        this.proyectoList = proyectoList;
    }

    public Organismo getOrganismoSelectedRefExt() {
        return organismoSelectedRefExt;
    }

    public void setOrganismoSelectedRefExt(Organismo organismoSelectedRefExt) {
        this.organismoSelectedRefExt = organismoSelectedRefExt;
    }

    public Integer getOrganismoInteger() {
        return organismoInteger;
    }

    public void setOrganismoInteger(Integer organismoInteger) {
        this.organismoInteger = organismoInteger;
    }

    public List<Facultad> getFacultadesBeneficiadaList() {
        return facultadesBeneficiadaList;
    }

    public void setFacultadesBeneficiadaList(List<Facultad> facultadesBeneficiadaList) {
        this.facultadesBeneficiadaList = facultadesBeneficiadaList;
    }

    public Boolean getTabAsis() {
        return tabAsis;
    }

    public void setTabAsis(Boolean tabAsis) {
        this.tabAsis = tabAsis;
    }

    public Boolean getAsistenteNull() {
        return asistenteNull;
    }

    public void setAsistenteNull(Boolean asistenteNull) {
        this.asistenteNull = asistenteNull;
    }

    public Boolean getConsultar() {
        return consultar;
    }

    public void setConsultar(Boolean consultar) {
        this.consultar = consultar;
    }

    public String getFu() {
        return fu;
    }

    public void setFu(String fu) {
        this.fu = fu;
    }

    public String getFuSol() {
        return fuSol;
    }

    public void setFuSol(String fuSol) {
        this.fuSol = fuSol;
    }

    public String getFuAsis() {
        return fuAsis;
    }

    public void setFuAsis(String fuAsis) {
        this.fuAsis = fuAsis;
    }

    public String getEdSol() {
        return edSol;
    }

    public void setEdSol(String edSol) {
        this.edSol = edSol;
    }

    public String getEdAsis() {
        return edAsis;
    }

    public void setEdAsis(String edAsis) {
        this.edAsis = edAsis;
    }

    public String getOrRefExt() {
        return orRefExt;
    }

    public void setOrRefExt(String orRefExt) {
        this.orRefExt = orRefExt;
    }

    public Boolean getMostrarEscuelaSol() {
        return mostrarEscuelaSol;
    }

    public void setMostrarEscuelaSol(Boolean mostrarEscuelaSol) {
        this.mostrarEscuelaSol = mostrarEscuelaSol;
    }

    public Boolean getMostrarEscuelaAsis() {
        return mostrarEscuelaAsis;
    }

    public void setMostrarEscuelaAsis(Boolean mostrarEscuelaAsis) {
        this.mostrarEscuelaAsis = mostrarEscuelaAsis;
    }

    public boolean isActualizar() {
        return actualizar;
    }

    public void setActualizar(boolean actualizar) {
        this.actualizar = actualizar;
    }

    public Boolean getTabAsisMostrar() {
        return tabAsisMostrar;
    }

    public void setTabAsisMostrar(Boolean tabAsisMostrar) {
        this.tabAsisMostrar = tabAsisMostrar;
    }

    public Boolean getValidarFecha() {
        return validarFecha;
    }

    public void setValidarFecha(Boolean validarFecha) {
        this.validarFecha = validarFecha;
    }

    public Long getMontoProyecto() {
        return montoProyecto;
    }

    public void setMontoProyecto(Long montoProyecto) {
        this.montoProyecto = montoProyecto;
    }

    public TipoCambio getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(TipoCambio tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public List<TipoCambio> getTipoCambioList() {
        return tipoCambioList;
    }

    public void setTipoCambioList(List<TipoCambio> tipoCambioList) {
        this.tipoCambioList = tipoCambioList;
    }

    public TipoCambio getTipoCambioSelected() {
        return tipoCambioSelected;
    }

    public void setTipoCambioSelected(TipoCambio tipoCambioSelected) {
        this.tipoCambioSelected = tipoCambioSelected;
    }

    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
    }

    public Date getFechaF() {
        return fechaF;
    }

    public void setFechaF(Date fechaF) {
        this.fechaF = fechaF;
    }

    public Boolean getDisableAutoInterno() {
        return disableAutoInterno;
    }

    public void setDisableAutoInterno(Boolean disableAutoInterno) {
        this.disableAutoInterno = disableAutoInterno;
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

    public Boolean getDisableAutoAsistente() {
        return disableAutoAsistente;
    }

    public void setDisableAutoAsistente(Boolean disableAutoAsistente) {
        this.disableAutoAsistente = disableAutoAsistente;
    }

    public Boolean getFlagSearchDuiAsistente() {
        return flagSearchDuiAsistente;
    }

    public void setFlagSearchDuiAsistente(Boolean flagSearchDuiAsistente) {
        this.flagSearchDuiAsistente = flagSearchDuiAsistente;
    }

    public Boolean getFlagSearchNombreAsistente() {
        return flagSearchNombreAsistente;
    }

    public void setFlagSearchNombreAsistente(Boolean flagSearchNombreAsistente) {
        this.flagSearchNombreAsistente = flagSearchNombreAsistente;
    }

    public Boolean getFlagSearchEmailAsistente() {
        return flagSearchEmailAsistente;
    }

    public void setFlagSearchEmailAsistente(Boolean flagSearchEmailAsistente) {
        this.flagSearchEmailAsistente = flagSearchEmailAsistente;
    }

    public String getTipoBusquedaAsistente() {
        return tipoBusquedaAsistente;
    }

    public void setTipoBusquedaAsistente(String tipoBusquedaAsistente) {
        this.tipoBusquedaAsistente = tipoBusquedaAsistente;
    }

    public Persona getPersonaAux() {
        return personaAux;
    }

    public void setPersonaAux(Persona personaAux) {
        this.personaAux = personaAux;
    }

    public Persona getPersonaAsistenteAux() {
        return personaAsistenteAux;
    }

    public void setPersonaAsistenteAux(Persona personaAsistenteAux) {
        this.personaAsistenteAux = personaAsistenteAux;
    }

    public Persona getPersonaExternaAux() {
        return personaExternaAux;
    }

    public void setPersonaExternaAux(Persona personaExternaAux) {
        this.personaExternaAux = personaExternaAux;
    }

    public Boolean getSiBuscoPersona() {
        return siBuscoPersona;
    }

    public void setSiBuscoPersona(Boolean siBuscoPersona) {
        this.siBuscoPersona = siBuscoPersona;
    }

    public Boolean getSiBuscoPersonaAsistente() {
        return siBuscoPersonaAsistente;
    }

    public void setSiBuscoPersonaAsistente(Boolean siBuscoPersonaAsistente) {
        this.siBuscoPersonaAsistente = siBuscoPersonaAsistente;
    }

    public Boolean getSiBuscoPersonaExterna() {
        return siBuscoPersonaExterna;
    }

    public void setSiBuscoPersonaExterna(Boolean siBuscoPersonaExterna) {
        this.siBuscoPersonaExterna = siBuscoPersonaExterna;
    }

    public Boolean getBuscoYEncontroCoordinador() {
        return buscoYEncontroCoordinador;
    }

    public void setBuscoYEncontroCoordinador(Boolean buscoYEncontroCoordinador) {
        this.buscoYEncontroCoordinador = buscoYEncontroCoordinador;
    }

    public Boolean getBuscoYEncontroAsistente() {
        return buscoYEncontroAsistente;
    }

    public void setBuscoYEncontroAsistente(Boolean buscoYEncontroAsistente) {
        this.buscoYEncontroAsistente = buscoYEncontroAsistente;
    }

    public Boolean getBuscoYEncontroRefExt() {
        return buscoYEncontroRefExt;
    }

    public void setBuscoYEncontroRefExt(Boolean buscoYEncontroRefExt) {
        this.buscoYEncontroRefExt = buscoYEncontroRefExt;
    }

    public Boolean getSiEditoPersona() {
        return siEditoPersona;
    }

    public void setSiEditoPersona(Boolean siEditoPersona) {
        this.siEditoPersona = siEditoPersona;
    }

    public Boolean getSiEditoPersonaAsistente() {
        return siEditoPersonaAsistente;
    }

    public void setSiEditoPersonaAsistente(Boolean siEditoPersonaAsistente) {
        this.siEditoPersonaAsistente = siEditoPersonaAsistente;
    }

    public Boolean getSiEditoPersonaExterna() {
        return siEditoPersonaExterna;
    }

    public void setSiEditoPersonaExterna(Boolean siEditoPersonaExterna) {
        this.siEditoPersonaExterna = siEditoPersonaExterna;
    }

    public Boolean getActivarBotonNuevaPersona() {
        return activarBotonNuevaPersona;
    }

    public void setActivarBotonNuevaPersona(Boolean activarBotonNuevaPersona) {
        this.activarBotonNuevaPersona = activarBotonNuevaPersona;
    }

    public Boolean getActivarBotonNuevaPersonaAsistente() {
        return activarBotonNuevaPersonaAsistente;
    }

    public void setActivarBotonNuevaPersonaAsistente(Boolean activarBotonNuevaPersonaAsistente) {
        this.activarBotonNuevaPersonaAsistente = activarBotonNuevaPersonaAsistente;
    }

    public Boolean getActivarBotonNuevaPersonaExterna() {
        return activarBotonNuevaPersonaExterna;
    }

    public void setActivarBotonNuevaPersonaExterna(Boolean activarBotonNuevaPersonaExterna) {
        this.activarBotonNuevaPersonaExterna = activarBotonNuevaPersonaExterna;
    }

    public Boolean getDisablePersona() {
        return disablePersona;
    }

    public void setDisablePersona(Boolean disablePersona) {
        this.disablePersona = disablePersona;
    }

    public Boolean getDisablePersonaAsistente() {
        return disablePersonaAsistente;
    }

    public void setDisablePersonaAsistente(Boolean disablePersonaAsistente) {
        this.disablePersonaAsistente = disablePersonaAsistente;
    }

    public Boolean getDisablePersonaExterna() {
        return disablePersonaExterna;
    }

    public void setDisablePersonaExterna(Boolean disablePersonaExterna) {
        this.disablePersonaExterna = disablePersonaExterna;
    }

    public Boolean getDesvincularPersona() {
        return desvincularPersona;
    }

    public void setDesvincularPersona(Boolean desvincularPersona) {
        this.desvincularPersona = desvincularPersona;
    }

    public Boolean getDesvincularAsistente() {
        return desvincularAsistente;
    }

    public void setDesvincularAsistente(Boolean desvincularAsistente) {
        this.desvincularAsistente = desvincularAsistente;
    }

    public Boolean getDesvincularExterna() {
        return desvincularExterna;
    }

    public void setDesvincularExterna(Boolean desvincularExterna) {
        this.desvincularExterna = desvincularExterna;
    }
// getter y setter de elementos de combobox nuevos

    public TipoProyecto getNewTipoProyecto() {
        return newTipoProyecto;
    }

    public void setNewTipoProyecto(TipoProyecto newTipoProyecto) {
        this.newTipoProyecto = newTipoProyecto;
    }

    public String getMascaraTelefono() {
        return mascaraTelefono;
    }

    public void setMascaraTelefono(String mascaraTelefono) {
        this.mascaraTelefono = mascaraTelefono;
    }

}
